package Event.userauthentication.core.resource;
import java.util.*;

import id.ac.ui.cs.prices.winvmj.core.Route;
import id.ac.ui.cs.prices.winvmj.core.VMJExchange;
import Event.userauthentication.core.model.UserAuthentication;

public abstract class UserAuthenticationResourceDecorator extends UserAuthenticationResourceComponent{
	protected UserAuthenticationResourceComponent record;

    public UserAuthenticationResourceDecorator(UserAuthenticationResourceComponent record) {
        this.record = record;
    }

    public List<HashMap<String,Object>> saveUserAuthentication(VMJExchange vmjExchange){
		return record.saveUserAuthentication(vmjExchange);
	}

    public UserAuthentication createUserAuthentication(VMJExchange vmjExchange){
		return record.createUserAuthentication(vmjExchange);
	}
	
	public UserAuthentication createUserAuthentication(VMJExchange vmjExchange, int id){
		return record.createUserAuthentication(vmjExchange, id);
	}

    public HashMap<String, Object> updateUserAuthentication(VMJExchange vmjExchange){
		return record.updateUserAuthentication(vmjExchange);
	}

    public HashMap<String, Object> getUserAuthentication(VMJExchange vmjExchange){
		return record.getUserAuthentication(vmjExchange);
	}

    public List<HashMap<String,Object>> getAllUserAuthentication(VMJExchange vmjExchange){
		return record.getAllUserAuthentication(vmjExchange);
	}

    public List<HashMap<String,Object>> deleteUserAuthentication(VMJExchange vmjExchange){
		return record.deleteUserAuthentication(vmjExchange);
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
}
