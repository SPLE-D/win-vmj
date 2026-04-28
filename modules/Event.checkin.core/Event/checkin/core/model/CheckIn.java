package Event.checkin.core.model;
import id.ac.ui.cs.prices.winvmj.core.Route;
import id.ac.ui.cs.prices.winvmj.core.VMJExchange;
import java.util.*;
import java.lang.*;

public interface CheckIn {
	    public String getCheckInId();
	    public void setCheckInId(String checkInId);
	    public boolean getAttended();
	    public void setAttended(boolean attended);
	HashMap<String, Object> toHashMap();
}
