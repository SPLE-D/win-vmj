package Event.userauthentication.core.service;
import java.util.*;
import java.lang.*;

import id.ac.ui.cs.prices.winvmj.hibernate.RepositoryUtil;
import id.ac.ui.cs.prices.winvmj.core.VMJExchange;
import Event.userauthentication.core.model.UserAuthentication;
//add other required packages

public abstract class UserAuthenticationServiceComponent implements UserAuthenticationService{
	protected RepositoryUtil<UserAuthentication> Repository;

    public UserAuthenticationServiceComponent(){
        this.Repository = new RepositoryUtil<UserAuthentication>(Event.userauthentication.core.model.UserAuthenticationComponent.class);
    }	

    public abstract UserAuthentication createUserAuthentication(Map<String, Object> requestBody);
	public abstract UserAuthentication createUserAuthentication(Map<String, Object> requestBody, int id);
	public abstract HashMap<String, Object> updateUserAuthentication(Map<String, Object> requestBody);
    public abstract HashMap<String, Object> getUserAuthentication(String idStr);
    public abstract List<HashMap<String,Object>> getAllUserAuthentication();
    public abstract List<HashMap<String,Object>> transformListToHashMap(List<UserAuthentication> List);
    public abstract List<HashMap<String,Object>> deleteUserAuthentication(Map<String, Object> requestBody);
	public abstract HashMap<String, Object> getUserAuthenticationById(int id);

	public abstract void login();

	public abstract void logout();

	public abstract void register();
}
