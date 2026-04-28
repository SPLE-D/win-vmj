package Event.notification.core.model;

import java.lang.*;
import java.util.*;
import id.ac.ui.cs.prices.winvmj.core.Route;
import id.ac.ui.cs.prices.winvmj.core.VMJExchange;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity(name="notification_impl")
@Table(name="notification_impl")
public class NotificationImpl extends NotificationComponent {

	public NotificationImpl(AttendeeManagementImpl attendeemanagementimpl, int id) {
		this.attendeemanagementimpl = attendeemanagementimpl;
		this.id = id;
	}

	public NotificationImpl(AttendeeManagementImpl attendeemanagementimpl) {
		Random r = new Random();
		this.id = Math.abs(r.nextInt());
		this.attendeemanagementimpl = attendeemanagementimpl;
	}

	public NotificationImpl() { }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean sendNotification(String content) {
		// TODO: implement this method
		throw new UnsupportedOperationException();
	}
	
	public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> notificationMap = new HashMap<String,Object>();
		notificationMap.put("attendeemanagementimpl",getAttendeemanagementimpl());
		notificationMap.put("id",getId());

        return notificationMap;
    }

}
