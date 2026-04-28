package Event.eventcreation.core.model;

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
@Table(name="eventcreation_comp")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class EventCreationComponent implements EventCreation{
	@Id
	protected String EventId; 
	protected String EventId;
	public EDate startDate;
	public EDate endDate;
	public int capacity;
	public String name;
	public String location;
	@ManyToOne(targetEntity=Event.attendeemanagement.core.model.AttendeeManagementComponent.class)
	public AttendeeManagement attendeemanagementimpl;
	protected String objectName = EventCreationComponent.class.getName();

	public EventCreationComponent() {

	} 

	public EventCreationComponent(
        String EventId, EDate startDate, EDate endDate, int capacity, String name, String location, AttendeeManagementImpl attendeemanagementimpl
    ) {
        this.EventId = EventId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.capacity = capacity;
        this.name = name;
        this.location = location;
        this.attendeemanagementimpl = attendeemanagementimpl;
    }

	public String getEventId() {
		return this.EventId;
	}

	public void setEventId(String EventId) {
		this.EventId = EventId;
	}
	public abstract EDate getStartDate();
	public abstract void setStartDate(EDate startDate);
	
	public abstract EDate getEndDate();
	public abstract void setEndDate(EDate endDate);
	
	public abstract int getCapacity();
	public abstract void setCapacity(int capacity);
	
	public abstract String getName();
	public abstract void setName(String name);
	
	public abstract String getLocation();
	public abstract void setLocation(String location);
	
	public abstract AttendeeManagementImpl getAttendeemanagementimpl();
	public abstract void setAttendeemanagementimpl(AttendeeManagementImpl attendeemanagementimpl);
	
 
	public abstract void createEvent();

	public abstract void updateEvent();

	public abstract void deleteEvent();

	@Override
    public String toString() {
        return "{" +
            " EventId='" + getEventId() + "'" +
            " startDate='" + getStartDate() + "'" +
            " endDate='" + getEndDate() + "'" +
            " capacity='" + getCapacity() + "'" +
            " name='" + getName() + "'" +
            " location='" + getLocation() + "'" +
            " attendeemanagementimpl='" + getAttendeemanagementimpl() + "'" +
            "}";
    }
	
}
