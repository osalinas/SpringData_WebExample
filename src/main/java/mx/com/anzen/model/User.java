package mx.com.anzen.model;

import java.io.Serializable;

public interface User extends Serializable {
	public Long getId();
	public void setId(Long id);
	public String getFirstName();
	public void setFirstName(String firstName);
	public String getLastName();
	public void setLastName(String lastName);
}
