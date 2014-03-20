package mx.com.anzen.repositories.redis;

import java.io.Serializable;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface RedisCrudRepository<T, KEY extends Serializable> extends RedisRepository<T, KEY>, PagingAndSortingRepository<T, KEY> {
}
