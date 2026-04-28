package Event.userauthentication.core.model;

import java.util.*;
import java.lang.*;
import id.ac.ui.cs.prices.winvmj.core.Route;
import id.ac.ui.cs.prices.winvmj.core.VMJExchange;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="userauthentication_comp")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UserAuthenticationComponent implements UserAuthentication{
	protected String objectName = UserAuthenticationComponent.class.getName();

	public UserAuthenticationComponent() {

	} 

	public UserAuthenticationComponent(
        
    ) {
    }

 
	public abstract void login();

	public abstract void logout();

	public abstract void register();

	@Override
    public String toString() {
        return "{" +
            "}";
    }
	
}
