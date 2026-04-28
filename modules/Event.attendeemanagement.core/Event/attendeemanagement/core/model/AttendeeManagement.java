package Event.attendeemanagement.core.model;
import id.ac.ui.cs.prices.winvmj.core.Route;
import id.ac.ui.cs.prices.winvmj.core.VMJExchange;
import java.util.*;
import java.lang.*;

public interface AttendeeManagement {
	    public int getAttendeeId();
	    public void setAttendeeId(int attendeeId);
	    public String getPhoneNumber();
	    public void setPhoneNumber(String phoneNumber);
	    public CheckInImpl getCheckinimpl();
	    public void setCheckinimpl(CheckInImpl checkinimpl);
	HashMap<String, Object> toHashMap();
}
