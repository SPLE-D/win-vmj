package Event.userauthentication.core.service;
import java.util.*;

import Event.userauthentication.core.model.UserAuthentication;
import id.ac.ui.cs.prices.winvmj.core.VMJExchange;

public interface UserAuthenticationService {
	UserAuthentication createUserAuthentication(Map<String, Object> requestBody);
	HashMap<String, Object> getUserAuthentication(String idStr);
    HashMap<String, Object> updateUserAuthentication(Map<String, Object> requestBody);
    List<HashMap<String,Object>> getAllUserAuthentication();
    List<HashMap<String,Object>> deleteUserAuthentication(Map<String, Object> requestBody);
	List<HashMap<String, Object>> transformListToHashMap(List<UserAuthentication> List);
}
