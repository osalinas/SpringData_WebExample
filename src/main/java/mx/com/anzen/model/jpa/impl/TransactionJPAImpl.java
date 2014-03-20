package mx.com.anzen.model.jpa.impl;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import mx.com.anzen.model.Transaction;

@Entity(name="transactions")
public class TransactionJPAImpl extends Transaction implements Serializable {
	
	private static final long serialVersionUID = 6873413739558068188L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public String id;
	public String name;
	public String description;

	@Override
	public String getId()
	{
		
		return this.id;
	}

	@Override
	public void setId(String id)
	{
		this.id = id;
		
	}

	@Override
	public String getName()
	{
		return this.name;
	}

	@Override
	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String getDescription()
	{
		return this.description;
	}

	@Override
	public void setDescription(String description)
	{
		this.description = description;
	}
	
}
