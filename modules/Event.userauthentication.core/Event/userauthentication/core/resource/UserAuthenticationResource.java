package Event.userauthentication.core.resource;
import java.util.*;

import Event.userauthentication.core.model.UserAuthentication;
import id.ac.ui.cs.prices.winvmj.core.VMJExchange;

public interface UserAuthenticationResource {
    List<HashMap<String,Object>> saveUserAuthentication(VMJExchange vmjExchange);
    HashMap<String, Object> updateUserAuthentication(VMJExchange vmjExchange);
    HashMap<String, Object> getUserAuthentication(VMJExchange vmjExchange);
    List<HashMap<String,Object>> getAllUserAuthentication(VMJExchange vmjExchange);
    List<HashMap<String,Object>> deleteUserAuthentication(VMJExchange vmjExchange);
}
