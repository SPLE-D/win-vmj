package Event.userauthentication.core.model;

import java.util.*;
import java.lang.*;
import id.ac.ui.cs.prices.winvmj.core.Route;
import id.ac.ui.cs.prices.winvmj.core.VMJExchange;

import javax.persistence.OneToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.CascadeType;
//add other required packages

@MappedSuperclass
public abstract class UserAuthenticationDecorator extends UserAuthenticationComponent{
    @OneToOne(cascade=CascadeType.ALL)
	protected UserAuthenticationComponent record;

	public UserAuthenticationDecorator () {
		super();
		Random r = new Random();
		this. = Math.abs(r.nextInt());
	}

	public UserAuthenticationDecorator (, UserAuthenticationComponent record) {
		this. =  ;
		this.record = record;
	}
	
	public UserAuthenticationDecorator (UserAuthenticationComponent record, String objectName) {
		Random r = new Random();
		this. = Math.abs(r.nextInt());
		this.record = record;
		this.objectName=objectName;
	}



	public void login() {
		return record.login();
	}

	public void logout() {
		return record.logout();
	}

	public void register() {
		return record.register();
	}

	public HashMap<String, Object> toHashMap() {
        return this.record.toHashMap();
    }

}
