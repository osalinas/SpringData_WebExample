package mx.com.anzen.repositories.redis;

import java.io.Serializable;

import org.springframework.data.repository.Repository;

public interface RedisRepository<T, KEY extends Serializable> extends Repository<T, KEY> {
	/**
	 * Returns the number of entities available.
	 * 
	 * @return the total number of entities
	 */
	long count();
}
