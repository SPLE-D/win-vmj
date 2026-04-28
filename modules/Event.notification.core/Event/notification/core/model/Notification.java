package Event.notification.core.model;
import id.ac.ui.cs.prices.winvmj.core.Route;
import id.ac.ui.cs.prices.winvmj.core.VMJExchange;
import java.util.*;
import java.lang.*;

public interface Notification {
	    public AttendeeManagementImpl getAttendeemanagementimpl();
	    public void setAttendeemanagementimpl(AttendeeManagementImpl attendeemanagementimpl);
	    public int getId();
	    public void setId(int id);
	HashMap<String, Object> toHashMap();
}
