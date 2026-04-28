package Event.eventcreation.core.model;

import java.util.*;
import java.lang.*;
import id.ac.ui.cs.prices.winvmj.core.Route;
import id.ac.ui.cs.prices.winvmj.core.VMJExchange;

import javax.persistence.OneToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.CascadeType;
//add other required packages

@MappedSuperclass
public abstract class EventCreationDecorator extends EventCreationComponent{
    @OneToOne(cascade=CascadeType.ALL)
	protected EventCreationComponent record;

	public EventCreationDecorator () {
		super();
		Random r = new Random();
		this.EventId = Math.abs(r.nextInt());
	}

	public EventCreationDecorator (String EventId, EventCreationComponent record) {
		this.EventId =  EventId;
		this.record = record;
	}
	
	public EventCreationDecorator (EventCreationComponent record, String objectName) {
		Random r = new Random();
		this.EventId = Math.abs(r.nextInt());
		this.record = record;
		this.objectName=objectName;
	}


	public String getEventId() {
		return record.getEventId();
	}
	public void setEventId(String EventId) {
		record.setEventId(EventId);
	}

	public void createEvent() {
		return record.createEvent();
	}

	public void updateEvent() {
		return record.updateEvent();
	}

	public void deleteEvent() {
		return record.deleteEvent();
	}

	public HashMap<String, Object> toHashMap() {
        return this.record.toHashMap();
    }

}
