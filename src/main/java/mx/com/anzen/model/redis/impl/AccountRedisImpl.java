package mx.com.anzen.model.redis.impl;

import java.io.Serializable;
import java.util.Date;

import mx.com.anzen.model.Account;

public class AccountRedisImpl extends Account implements Serializable{

	private static final long serialVersionUID = 4025070088948670372L;
	private Long id;
	private Date expireat;

	public AccountRedisImpl(Long id, Date expireat) {
		this.id = id;
		this.expireat = expireat;
	}

	@Override
	public Long getId()
	{
		return this.id;
	}

	@Override
	public void setId(Long id)
	{
		this.id = id;
	}

	@Override
	public Date getExpireat()
	{
		return this.expireat;
	}

	@Override
	public void setExpireat(Date expireat)
	{
		this.expireat = expireat;
	}

}
