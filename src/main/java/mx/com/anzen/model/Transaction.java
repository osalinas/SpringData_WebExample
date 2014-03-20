package mx.com.anzen.model;

public abstract class Transaction {
	
	public abstract String getId();
	public abstract void setId(String id);
	public abstract String getName();
	public abstract void setName(String name);
	public abstract String getDescription();
	public abstract void setDescription(String description);

}
