package mx.com.anzen.model.solr.impl;

import java.io.Serializable;

import mx.com.anzen.model.Transaction;

import org.apache.solr.client.solrj.beans.Field;

public class TransactionSolrImpl extends Transaction implements Serializable {

	private static final long serialVersionUID = -2675990048944733416L;

	@Field
	private String id;
	@Field
	private String name;
	@Field
	private String description;

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
