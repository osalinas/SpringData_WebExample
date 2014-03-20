package mx.com.anzen.model;

import java.util.Date;

public abstract class Account {
	public abstract Long getId();
	public abstract void setId(Long id);
	public abstract Date getExpireat();
	public abstract void setExpireat(Date expireat);
//	public abstract Customer getCustomer();
//	public abstract void setCustomer(Customer customer);
}
