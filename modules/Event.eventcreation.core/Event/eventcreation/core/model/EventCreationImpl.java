package Event.eventcreation.core.model;

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


@Entity(name="eventcreation_impl")
@Table(name="eventcreation_impl")
public class EventCreationImpl extends EventCreationComponent {

	public EventCreationImpl(String EventId, EDate startDate, EDate endDate, int capacity, String name, String location, AttendeeManagementImpl attendeemanagementimpl) {
		this.EventId = EventId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.capacity = capacity;
		this.name = name;
		this.location = location;
		this.attendeemanagementimpl = attendeemanagementimpl;
	}

	public EventCreationImpl(String EventId, EDate startDate, EDate endDate, int capacity, String name, String location, AttendeeManagementImpl attendeemanagementimpl) {
		Random r = new Random();
		this.EventId = Math.abs(r.nextInt());
		this.startDate = startDate;
		this.endDate = endDate;
		this.capacity = capacity;
		this.name = name;
		this.location = location;
		this.attendeemanagementimpl = attendeemanagementimpl;
	}

	public EventCreationImpl() { }

	public String getEventId() {
		return this.EventId;
	}

	public void setEventId(String EventId) {
		this.EventId = EventId;
	}

	public void createEvent() {
		// TODO: implement this method
		throw new UnsupportedOperationException();
	}

	public void updateEvent() {
		// TODO: implement this method
		throw new UnsupportedOperationException();
	}

	public void deleteEvent() {
		// TODO: implement this method
		throw new UnsupportedOperationException();
	}
	
	public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> eventcreationMap = new HashMap<String,Object>();
		eventcreationMap.put("EventId",getEventId());
		eventcreationMap.put("startDate",getStartDate());
		eventcreationMap.put("endDate",getEndDate());
		eventcreationMap.put("capacity",getCapacity());
		eventcreationMap.put("name",getName());
		eventcreationMap.put("location",getLocation());
		eventcreationMap.put("attendeemanagementimpl",getAttendeemanagementimpl());

        return eventcreationMap;
    }

}
