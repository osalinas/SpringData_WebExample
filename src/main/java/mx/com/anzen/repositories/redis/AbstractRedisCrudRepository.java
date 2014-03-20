package mx.com.anzen.repositories.redis;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;
import mx.com.anzen.repositories.redis.RedisCrudRepository;

public abstract class AbstractRedisCrudRepository<T, KEY extends Serializable>
		implements RedisCrudRepository<T, KEY> {

	@Autowired
	private RedisTemplate<String, T> redisTemplate;
	private Class<T> entityClass;
	
	public AbstractRedisCrudRepository() {
		this.entityClass = getEntityClass();
	}

	@Override
	public <S extends T> S save(S entity)
	{
		redisTemplate.opsForHash().put(getClassName(), getKey(entity), entity);
		return entity;
	}

	@Override
	public <S extends T> Iterable<S> save(Iterable<S> entities)
	{
		List<S> list = new ArrayList<S>(0);
		for (S s : entities) {
			redisTemplate.opsForHash().put(getClassName(), getKey(s), s);
			list.add(s);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findOne(KEY key)
	{
		return (T) redisTemplate.opsForHash().get(getClassName(), key);
	}

	@Override
	public boolean exists(KEY key)
	{
		return redisTemplate.opsForHash().hasKey(getClassName(), key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<T> findAll()
	{
		List<T> list = (List<T>) redisTemplate.opsForHash().values(
				getClassName());
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<T> findAll(Iterable<KEY> keys)
	{
		Collection<Object> coll = (Collection<Object>) Lists.newArrayList(keys);
		List<Object> list = redisTemplate.opsForHash().multiGet(getClassName(),
				coll);
		return (Iterable<T>) list;
	}

	@Override
	public void delete(KEY key)
	{
		redisTemplate.opsForHash().delete(getClassName(), key);
	}

	@Override
	public void delete(T entity)
	{
		redisTemplate.opsForHash().delete(getClassName(), getKey(entity));
	}

	/**
	 * Delete the entities in
	 * <code>{@link Iterable}&lt;{@link S} extends {@link T}&gt;</code>
	 * 
	 * @param {{@link Iterable}&lt;extends&gt; {@link T}
	 */
	@Override
	public void delete(Iterable<? extends T> entities)
	{
		for (T t : entities) {
			redisTemplate.opsForHash().delete(getClassName(), getKey(t));
		}
	}

	@Override
	public void deleteAll()
	{
		Set<Object> set = redisTemplate.opsForHash().keys(getClassName());
		for (Object obj : set) {
			redisTemplate.opsForHash().delete(getClassName(), obj);
		}
	}

	@Override
	public long count()
	{
		return redisTemplate.opsForHash().size(getClassName());
		// throw new UnsupportedOperationException("");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<T> findAll(Sort sort)
	{

		List<T> list = (List<T>) redisTemplate.opsForHash().values(
				getClassName());

		List<Order> orders = Lists.newArrayList(Lists.newArrayList(sort
				.iterator()));
		if (orders.size() == 0) {
			return list;
		}

		for (final Order order : orders) {
			// final String methodName =
			// "get"+StringUtils.capitalize(order.getProperty());
			Collections.sort(list, new Comparator<T>() {
				@Override
				public int compare(T o1, T o2)
				{
					String methodName = "get"+ StringUtils.capitalize(order.getProperty());
					Method method = null;

					try {
						method = o1.getClass().getDeclaredMethod(methodName,
								new Class<?>[0]);

						Object obj1 = method.invoke(o1, new Object[0]);
						Object obj2 = method.invoke(o2, new Object[0]);
						System.out.println("obj1 > "+obj1.toString()+" ---- obj2 > "+obj2);
						int compareVal = obj1.toString().compareTo(obj2.toString());
						
						System.out.println("Direction = " + order.getDirection());
						if(order.getDirection() == Direction.DESC){
							compareVal *= -1;
						}
						
						System.out.println("cmpareVal = "+compareVal);
						System.out.println("cmpareVal * (-1) = "+ (compareVal * (-1)));
						return compareVal;

					} catch (SecurityException e1) {// >>>>> getDeclaredMethod()
						System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> SecurityException");
						e1.printStackTrace(System.out);
					} catch (NoSuchMethodException e1) {// >>>>> getDeclaredMethod()
						System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> NoSuchMethodException");
						e1.printStackTrace(System.out);
					} catch (IllegalArgumentException e) {// >>>>> invoke()
						System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> IllegalArgumentException");
						e.printStackTrace(System.out);
					} catch (IllegalAccessException e) {// >>>>> invoke()
						System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> IllegalAccessException");
						e.printStackTrace(System.out);
					} catch (InvocationTargetException e) {// >>>>> invoke()
						System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> InvocationTargetException");
						e.printStackTrace(System.out);
					}

					return 0;
				}

			});

		}

		return list;
	}

	@Override
	public Page<T> findAll(Pageable pageable)
	{

		int end = pageable.getPageNumber() * pageable.getPageSize();
		int start = end - pageable.getPageSize();

		ArrayList<T> list = Lists.newArrayList(findAll(pageable.getSort()));
		List<T> subList = list.subList(start, end);
		PageImpl<T> page = new PageImpl<T>(subList);

		return page;
	}

	public RedisTemplate<String, T> getRedisTemplate()
	{
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, T> redisTemplate)
	{
		this.redisTemplate = redisTemplate;
	}

	private String getClassName()
	{
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
//		System.out.println("getClass(): " + pt.getClass());
//		System.out.println("getOwnerType(): " + pt.getOwnerType());
//		System.out.println("getRawType(): " + pt.getRawType());
//		System.out.println("getRawType().getClass(): " + pt.getRawType().getClass());

		Type[] arr = pt.getActualTypeArguments();
		for (Type t1 : arr) {
//			
//			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>><<");
//			System.out.println("getClass(): " + t1);
		}

		return getEntityClass().getName();
	}

	public Class<T> getEntityClass()
	{
		if (!isEntityClassSet()) {
			try {
				this.entityClass = resolveReturnedClassFromGernericType();
			} catch (Exception e) {
				throw new InvalidDataAccessApiUsageException("Unable to resolve EntityClass. Please use according setter!",	e);
			}
		}
		return entityClass;
	}

	public final void setEntityClass(Class<T> entityClass)
	{
		Assert.notNull(entityClass, "EntityClass must not be null.");

		this.entityClass = entityClass;
	}

	private boolean isEntityClassSet()
	{
		return entityClass != null;
	}

	@SuppressWarnings("unchecked")
	private Class<T> resolveReturnedClassFromGernericType()
	{
		ParameterizedType parameterizedType = resolveReturnedClassFromGernericType(getClass());
		return (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}

	private ParameterizedType resolveReturnedClassFromGernericType(Class<?> clazz)
	{
		Object genericSuperclass = clazz.getGenericSuperclass();
		System.out.println("genericSuperclass"+genericSuperclass);
		System.out.println("genericSuperclass instanceof ParameterizedType"+(genericSuperclass instanceof ParameterizedType));
		if (genericSuperclass instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
			Type rawtype = parameterizedType.getRawType();
			if (AbstractRedisCrudRepository.class.equals(rawtype)) {
				return parameterizedType;
			}
		}
		return resolveReturnedClassFromGernericType(clazz.getSuperclass());
	}

	public abstract String getKey(T t);

}
