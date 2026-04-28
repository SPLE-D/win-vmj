package Event.userauthentication.core.resource;
import java.util.*;

import id.ac.ui.cs.prices.winvmj.hibernate.RepositoryUtil;
import id.ac.ui.cs.prices.winvmj.core.VMJExchange;
import Event.userauthentication.core.model.UserAuthentication;
//add other required packages

public abstract class UserAuthenticationResourceComponent implements UserAuthenticationResource{
	
	public UserAuthenticationResourceComponent() { }
 
    public abstract List<HashMap<String,Object>> saveUserAuthentication(VMJExchange vmjExchange);
    public abstract UserAuthentication createUserAuthentication(VMJExchange vmjExchange);
	public abstract UserAuthentication createUserAuthentication(VMJExchange vmjExchange, int id);
	public abstract HashMap<String, Object> updateUserAuthentication(VMJExchange vmjExchange);
    public abstract HashMap<String, Object> getUserAuthentication(VMJExchange vmjExchange);
    public abstract List<HashMap<String,Object>> getAllUserAuthentication(VMJExchange vmjExchange);
    public abstract List<HashMap<String,Object>> deleteUserAuthentication(VMJExchange vmjExchange);

	public abstract void login();

	public abstract void logout();

	public abstract void register();
}
