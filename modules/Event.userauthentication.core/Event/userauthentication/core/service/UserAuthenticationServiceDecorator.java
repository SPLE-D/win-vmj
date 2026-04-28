package Event.userauthentication.core.service;
import java.util.*;
import java.lang.*;

import id.ac.ui.cs.prices.winvmj.core.VMJExchange;
import Event.userauthentication.core.model.UserAuthentication;

public abstract class UserAuthenticationServiceDecorator extends UserAuthenticationServiceComponent{
	protected UserAuthenticationServiceComponent record;

    public UserAuthenticationServiceDecorator(UserAuthenticationServiceComponent record) {
        this.record = record;
    }

	public UserAuthentication createUserAuthentication(Map<String, Object> requestBody){
		return record.createUserAuthentication(requestBody);
	}
	
	public UserAuthentication createUserAuthentication(Map<String, Object> requestBody, int id){
		return record.createUserAuthentication(requestBody, id);
	}

	public HashMap<String, Object> getUserAuthentication(String idStr){
		return record.getUserAuthentication(idStr);
	}

	public List<HashMap<String,Object>> getAllUserAuthentication(){
		return record.getAllUserAuthentication();
	}

    public HashMap<String, Object> updateUserAuthentication(Map<String, Object> requestBody){
		return record.updateUserAuthentication(requestBody);
	}

    public List<HashMap<String,Object>> transformListToHashMap(List<UserAuthentication> List){
		return record.transformListToHashMap(List);
	}

    public List<HashMap<String,Object>> deleteUserAuthentication(Map<String, Object> requestBody){
		return record.deleteUserAuthentication(requestBody);
	}

	public HashMap<String, Object> getUserAuthenticationById(int id){
        return record.getUserAuthenticationById(id);
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
