package mx.com.anzen.model.jpa.impl;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import mx.com.anzen.model.Account;

@Entity(name="accounts")
public class AccountJPAImpl extends Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
//	@ManyToOne()
//	private Customer customer;
	@Temporal(TemporalType.DATE)
	public Date expireat;
	
	public AccountJPAImpl() {
	}
	
	public AccountJPAImpl(Date expiresAt) {
		this.expireat = expiresAt;
	}

	@Override
	public Long getId()
	{
		return this.id;
	}

	@Override
	public Date getExpireat()
	{
		return this.expireat;
	}

	@Override
	public void setId(Long id)
	{
		this.id = id;
	}

	@Override
	public void setExpireat(Date expireat)
	{
		this.expireat = expireat;
	}

//	@Override
//	public Customer getCustomer()
//	{
//		return this.customer;
//	}
//
//	@Override
//	public void setCustomer(Customer customer)
//	{
//		this.customer = customer;
//	}

}
