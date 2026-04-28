package Event.checkin.ticketcheckin.resource;
import java.util.*;
import java.lang.*;

import id.ac.ui.cs.prices.winvmj.core.Route;
import id.ac.ui.cs.prices.winvmj.core.VMJExchange;
import id.ac.ui.cs.prices.winvmj.core.exceptions.*;

import Event.checkin.core.resource.CheckInResourceDecorator;
import Event.checkin.core.resource.CheckInResourceComponent;
import Event.checkin.core.model.CheckIn;
import Event.checkin.core.model.CheckInImpl;
import Event.checkin.core.service.CheckInServiceComponent;
import Event.checkin.ticketcheckin.service.CheckInServiceImpl;

public class CheckInResourceImpl extends CheckInResourceDecorator {
	protected CheckInServiceComponent recordComponent;
	private CheckInServiceImpl checkinticketcheckinServiceImpl = new CheckInServiceImpl(recordComponent);

    public CheckInResourceImpl (CheckInResourceComponent record) {
        super(record);
    }

    
    @Route(url="call/ticketcheckin/save")
    public List<HashMap<String,Object>> saveCheckIn(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		CheckIn checkinticketcheckin = createCheckIn(vmjExchange);
		return getAllCheckIn(vmjExchange);
	}

    public CheckIn createCheckIn(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("POST")) {
		    Map<String, Object> requestBody = vmjExchange.getPayload(); 
			CheckIn result = checkinticketcheckinServiceImpl.createCheckIn(requestBody);
			return result;
		}
		throw new NotFoundException("Route tidak ditemukan");
	}

    public CheckIn createCheckIn(VMJExchange vmjExchange, UUID id){
		if (vmjExchange.getHttpMethod().equals("POST")) {
		    Map<String, Object> requestBody = vmjExchange.getPayload(); 
			CheckIn result = checkinticketcheckinServiceImpl.createCheckIn(requestBody, id);
			return result;
		}
		throw new NotFoundException("Route tidak ditemukan");
	}

	
    @Route(url="call/ticketcheckin/update")
    public HashMap<String, Object> updateCheckIn(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		if (vmjExchange.getHttpMethod().equals("OPTIONS")){
			return null;
		}
		return checkinticketcheckinServiceImpl.updateCheckIn(requestBody);
	}

	
    @Route(url="call/ticketcheckin/detail")
    public HashMap<String, Object> getCheckIn(VMJExchange vmjExchange){
		return record.getCheckIn(vmjExchange);
	}

	
    @Route(url="call/ticketcheckin/list")
    public List<HashMap<String,Object>> getAllCheckIn(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload();
		return checkinticketcheckinServiceImpl.getAllCheckIn();
	}

    public List<HashMap<String,Object>> transformCheckInListToHashMap(List<CheckIn> CheckInTicketCheckInList){
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
        for(int i = 0; i < CheckInTicketCheckInList.size(); i++) {
            resultList.add(CheckInTicketCheckInList.get(i).toHashMap());
        }

        return resultList;
	}

	
    @Route(url="call/ticketcheckin/delete")
    public List<HashMap<String,Object>> deleteCheckIn(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		
		return checkinticketcheckinServiceImpl.deleteCheckIn(requestBody);
	}

	public boolean checkIn(String uniqueCode) {
		// TODO: implement this method
		throw new UnsupportedOperationException();
	}
	
}
