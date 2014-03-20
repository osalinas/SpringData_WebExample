package mx.com.anzen.model.solr.impl;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

import mx.com.anzen.model.Customer;

public class CustomerSolrImpl implements Customer, Serializable {
	
	private static final long serialVersionUID = -4798004841629010131L;
	
	@Field
	private String id;
	@Field
	private String name;
	@Field
	private String description;
	
	@Override
	public Long getId()
	{
		return Long.getLong(this.id);
	}

	@Override
	public void setId(Long id)
	{
		this.id = Long.toString(id);
	}

	@Override
	public String getFirstname()
	{
		return this.name;
	}

	@Override
	public void setFirstname(String firstname)
	{
		this.name = firstname;
	}

	@Override
	public String getLastname()
	{
		return this.description;
	}

	@Override
	public void setLastname(String lastname)
	{
		this.description = lastname;
	}

}
