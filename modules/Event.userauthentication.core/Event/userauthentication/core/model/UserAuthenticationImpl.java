package Event.userauthentication.core.model;

import java.lang.*;
import java.util.*;
import id.ac.ui.cs.prices.winvmj.core.Route;
import id.ac.ui.cs.prices.winvmj.core.VMJExchange;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity(name="userauthentication_impl")
@Table(name="userauthentication_impl")
public class UserAuthenticationImpl extends UserAuthenticationComponent {

	public UserAuthenticationImpl() {
	}

	public UserAuthenticationImpl() {
		Random r = new Random();
		this. = Math.abs(r.nextInt());
	}

	public UserAuthenticationImpl() { }


	public void login() {
		// TODO: implement this method
		throw new UnsupportedOperationException();
	}

	public void logout() {
		// TODO: implement this method
		throw new UnsupportedOperationException();
	}

	public void register() {
		// TODO: implement this method
		throw new UnsupportedOperationException();
	}
	
	public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> userauthenticationMap = new HashMap<String,Object>();

        return userauthenticationMap;
    }

}
