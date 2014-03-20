package mx.com.anzen.model.jpa.impl;

import java.io.Serializable;

import mx.com.anzen.model.Customer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="customers")
public class CustomerJPAImpl implements Serializable, Customer {

	private static final long serialVersionUID = -8227103144076050394L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	public String firstname;
	public String lastname;
	
	public CustomerJPAImpl() {
		// TODO Auto-generated constructor stub
	}

	public CustomerJPAImpl(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Long getId()
	{
		return this.id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getFirstname()
	{
		return firstname;
	}

	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}

	public String getLastname()
	{
		return lastname;
	}

	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}

	@Override
	public String toString()
	{
		return String.format("Customer[id=%d, firstName='%s', lastName='%s']",
				id, firstname, lastname);
	}

}
