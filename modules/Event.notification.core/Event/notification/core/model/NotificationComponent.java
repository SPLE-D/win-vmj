package Event.notification.core.model;

import java.util.*;
import java.lang.*;
import id.ac.ui.cs.prices.winvmj.core.Route;
import id.ac.ui.cs.prices.winvmj.core.VMJExchange;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="notification_comp")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class NotificationComponent implements Notification{
	@Id
	protected int id; 
	@ManyToOne(targetEntity=Event.attendeemanagement.core.model.AttendeeManagementComponent.class)
	public AttendeeManagement attendeemanagementimpl;
	protected String objectName = NotificationComponent.class.getName();

	public NotificationComponent() {

	} 

	public NotificationComponent(
        AttendeeManagementImpl attendeemanagementimpl, int id
    ) {
        this.attendeemanagementimpl = attendeemanagementimpl;
        this.id = id;
    }

	public abstract AttendeeManagementImpl getAttendeemanagementimpl();
	public abstract void setAttendeemanagementimpl(AttendeeManagementImpl attendeemanagementimpl);
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
 
	public abstract boolean sendNotification(String content);

	@Override
    public String toString() {
        return "{" +
            " attendeemanagementimpl='" + getAttendeemanagementimpl() + "'" +
            " id='" + getId() + "'" +
            "}";
    }
	
}
