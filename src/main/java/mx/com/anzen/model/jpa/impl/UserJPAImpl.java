package mx.com.anzen.model.jpa.impl;

import mx.com.anzen.model.User;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="users")
public class UserJPAImpl implements User, Serializable {

	private static final long serialVersionUID = 4142785028460760304L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	public String firstName;
	public String lastName;
	
	protected UserJPAImpl()
	{
		
	}
	
	public UserJPAImpl(String firstName, String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getId()
	{
		return this.id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	@Override
	public String toString()
	{
		return String.format("User[id=%d, firstName='%s', lastName='%s']",
				id, firstName, lastName);
	}

	

}
