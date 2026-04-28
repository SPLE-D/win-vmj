package Event.userauthentication.core.service;
import java.util.*;
import java.lang.*;
import com.google.gson.Gson;
import java.util.*;
import java.util.logging.Logger;
import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import id.ac.ui.cs.prices.winvmj.core.Route;
import id.ac.ui.cs.prices.winvmj.core.VMJExchange;
import id.ac.ui.cs.prices.winvmj.core.exceptions.*;
import Event.userauthentication.UserAuthenticationFactory;
import Event.userauthentication.core.model.UserAuthentication;
import id.ac.ui.cs.prices.winvmj.auth.annotations.Restricted;
//add other required packages

public class UserAuthenticationServiceImpl extends UserAuthenticationServiceComponent{

    public UserAuthentication createUserAuthentication(Map<String, Object> requestBody){
		
		//to do: fix association attributes
		
		UserAuthentication userauthentication = UserAuthenticationFactory.createUserAuthentication("Event.userauthentication.core.model.UserAuthenticationImpl", );
		Repository.saveObject(userauthentication);
		return userauthentication;
	}

	public UserAuthentication createUserAuthentication(Map<String, Object> requestBody, int id){
		
		//to do: fix association attributes
		UserAuthentication userauthentication = UserAuthenticationFactory.createUserAuthentication("Event.userauthentication.core.model.UserAuthenticationImpl",);
		Repository.saveObject(userauthentication);
		return userauthentication;
	}

    public HashMap<String, Object> updateUserAuthentication(Map<String, Object> requestBody){
		String idStr = (String) requestBody.get("");
		int id = Integer.parseInt(idStr);
		UserAuthentication userauthentication = Repository.getObject(id);
		
		
		Repository.updateObject(userauthentication);
		
		//to do: fix association attributes
		
		return userauthentication.toHashMap();
		
	}

    public HashMap<String, Object> getUserAuthentication(String idStr){
		int id = Integer.parseInt(idStr);
		UserAuthentication userauthentication = Repository.getObject(id);
		return userauthentication.toHashMap();
	}

	public HashMap<String, Object> getUserAuthenticationById(int id){
		List<HashMap<String, Object>> userauthenticationList = getAllUserAuthentication();
		for (HashMap<String, Object> userauthentication : userauthenticationList){
			int record_id = ((Double) userauthentication.get("")).intValue();
			if (record_id == id){
				return userauthentication;
			}
		}
		return null;
	}

    public List<HashMap<String,Object>> getAllUserAuthentication(){
		List<UserAuthentication> List = Repository.getAllObject("userauthentication_impl");
		return transformListToHashMap(List);
	}

    public List<HashMap<String,Object>> transformListToHashMap(List<UserAuthentication> List){
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
        for(int i = 0; i < List.size(); i++) {
            resultList.add(List.get(i).toHashMap());
        }

        return resultList;
	}

    public List<HashMap<String,Object>> deleteUserAuthentication(Map<String, Object> requestBody){
		String idStr = ((String) requestBody.get(""));
		int id = Integer.parseInt(idStr);
		Repository.deleteObject(id);
		return getAllUserAuthentication();
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
