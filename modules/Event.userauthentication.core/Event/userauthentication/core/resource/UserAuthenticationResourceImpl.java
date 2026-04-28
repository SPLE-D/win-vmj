package Event.userauthentication.core.resource;
import java.util.*;

import id.ac.ui.cs.prices.winvmj.core.Route;
import id.ac.ui.cs.prices.winvmj.core.VMJExchange;
import id.ac.ui.cs.prices.winvmj.core.exceptions.*;
import Event.userauthentication.UserAuthenticationFactory;
import id.ac.ui.cs.prices.winvmj.auth.annotations.Restricted;
import Event.userauthentication.core.model.UserAuthentication;
import Event.userauthentication.core.service.UserAuthenticationServiceImpl;
//add other required packages


public class UserAuthenticationResourceImpl extends UserAuthenticationResourceComponent{
	
	private UserAuthenticationServiceImpl userauthenticationServiceImpl = new UserAuthenticationServiceImpl();

	
    @Route(url="call/userauthentication/save")
    public List<HashMap<String,Object>> saveUserAuthentication(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		UserAuthentication userauthentication = createUserAuthentication(vmjExchange);
		return userauthenticationServiceImpl.getAllUserAuthentication();
	}

    public UserAuthentication createUserAuthentication(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("POST")) {
		    Map<String, Object> requestBody = vmjExchange.getPayload(); 
			UserAuthentication result = userauthenticationServiceImpl.createUserAuthentication(requestBody);
			return result;
		}
		throw new NotFoundException("Route tidak ditemukan");
	}
	
	public UserAuthentication createUserAuthentication(VMJExchange vmjExchange, int id){
		if (vmjExchange.getHttpMethod().equals("POST")) {
		    Map<String, Object> requestBody = vmjExchange.getPayload(); 
			UserAuthentication result = userauthenticationServiceImpl.createUserAuthentication(requestBody, id);
			return result;
		}
		throw new NotFoundException("Route tidak ditemukan");
	}

	
    @Route(url="call/userauthentication/update")
    public HashMap<String, Object> updateUserAuthentication(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		if (vmjExchange.getHttpMethod().equals("OPTIONS")){
			return null;
		}
		return userauthenticationServiceImpl.updateUserAuthentication(requestBody);
		
	}

	
    @Route(url="call/userauthentication/detail")
    public HashMap<String, Object> getUserAuthentication(VMJExchange vmjExchange){
		String idStr = vmjExchange.getGETParam("");
		return userauthenticationServiceImpl.getUserAuthentication(idStr);
	}

	
    @Route(url="call/userauthentication/list")
    public List<HashMap<String,Object>> getAllUserAuthentication(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		return userauthenticationServiceImpl.getAllUserAuthentication();
	}

	
    @Route(url="call/userauthentication/delete")
    public List<HashMap<String,Object>> deleteUserAuthentication(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		
		return userauthenticationServiceImpl.deleteUserAuthentication(requestBody);
	}


	
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
}
