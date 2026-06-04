package Event.eventcreation.core.resource;
import java.util.*;


import id.ac.ui.cs.prices.winvmj.core.Route;
import id.ac.ui.cs.prices.winvmj.core.VMJExchange;
import id.ac.ui.cs.prices.winvmj.core.exceptions.*;
import Event.eventcreation.EventCreationFactory;
import Event.eventcreation.core.model.EventCreation;
import Event.eventcreation.core.service.EventCreationServiceImpl;
import id.ac.ui.cs.prices.winvmj.core.Restricted;
//add other required packages


public class EventCreationResourceImpl extends EventCreationResourceComponent{
	
	private EventCreationServiceImpl eventcreationServiceImpl = new EventCreationServiceImpl();

	@Restricted(permission = "saveEvent")
    @Route(url="call/eventcreation/save")
    public List<HashMap<String,Object>> saveEventCreation(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		EventCreation eventcreation = createEventCreation(vmjExchange);
		return eventcreationServiceImpl.getAllEventCreation();
	}

    public EventCreation createEventCreation(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("POST")) {
		    Map<String, Object> requestBody = vmjExchange.getPayload(); 
			EventCreation result = eventcreationServiceImpl.createEventCreation(requestBody);
			return result;
		}
		throw new NotFoundException("Route tidak ditemukan");
	}
	
	public EventCreation createEventCreation(VMJExchange vmjExchange, int id){
		if (vmjExchange.getHttpMethod().equals("POST")) {
		    Map<String, Object> requestBody = vmjExchange.getPayload(); 
			EventCreation result = eventcreationServiceImpl.createEventCreation(requestBody, id);
			return result;
		}
		throw new NotFoundException("Route tidak ditemukan");
	}

	@Restricted(permission = "updateEvent")
    @Route(url="call/eventcreation/update")
    public HashMap<String, Object> updateEventCreation(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		if (vmjExchange.getHttpMethod().equals("OPTIONS")){
			return null;
		}
		return eventcreationServiceImpl.updateEventCreation(requestBody);
		
	}

	@Restricted(permission = "detailEvent")
    @Route(url="call/eventcreation/detail")
    public HashMap<String, Object> getEventCreation(VMJExchange vmjExchange){
		String idStr = vmjExchange.getGETParam("eventId");
		return eventcreationServiceImpl.getEventCreation(idStr);
	}

	
    @Route(url="call/eventcreation/list")
    public List<HashMap<String,Object>> getAllEventCreation(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		return eventcreationServiceImpl.getAllEventCreation();
	}

	@Restricted(permission = "deleteEvent")
    @Route(url="call/eventcreation/delete")
    public List<HashMap<String,Object>> deleteEventCreation(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		
		return eventcreationServiceImpl.deleteEventCreation(requestBody);
	}


}
