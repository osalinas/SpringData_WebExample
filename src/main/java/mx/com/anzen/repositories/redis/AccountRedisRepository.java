package mx.com.anzen.repositories.redis;

import mx.com.anzen.model.Account;
import mx.com.anzen.repositories.redis.AbstractRedisCrudRepository;

public class AccountRedisRepository extends AbstractRedisCrudRepository<Account, String> {

	@Override
	public String getKey(Account t)
	{
		return t.getId().toString();
	}

}
