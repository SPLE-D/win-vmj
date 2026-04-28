package Event.checkin.qrcheckin.resource;
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
import Event.checkin.qrcheckin.service.CheckInServiceImpl;

public class CheckInResourceImpl extends CheckInResourceDecorator {
	protected CheckInServiceComponent recordComponent;
	private CheckInServiceImpl checkinqrcheckinServiceImpl = new CheckInServiceImpl(recordComponent);

    public CheckInResourceImpl (CheckInResourceComponent record) {
        super(record);
    }

    
    @Route(url="call/qrcheckin/save")
    public List<HashMap<String,Object>> saveCheckIn(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		CheckIn checkinqrcheckin = createCheckIn(vmjExchange);
		return getAllCheckIn(vmjExchange);
	}

    public CheckIn createCheckIn(VMJExchange vmjExchange){
		if (vmjExchange.getHttpMethod().equals("POST")) {
		    Map<String, Object> requestBody = vmjExchange.getPayload(); 
			CheckIn result = checkinqrcheckinServiceImpl.createCheckIn(requestBody);
			return result;
		}
		throw new NotFoundException("Route tidak ditemukan");
	}

    public CheckIn createCheckIn(VMJExchange vmjExchange, UUID id){
		if (vmjExchange.getHttpMethod().equals("POST")) {
		    Map<String, Object> requestBody = vmjExchange.getPayload(); 
			CheckIn result = checkinqrcheckinServiceImpl.createCheckIn(requestBody, id);
			return result;
		}
		throw new NotFoundException("Route tidak ditemukan");
	}

	
    @Route(url="call/qrcheckin/update")
    public HashMap<String, Object> updateCheckIn(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		if (vmjExchange.getHttpMethod().equals("OPTIONS")){
			return null;
		}
		return checkinqrcheckinServiceImpl.updateCheckIn(requestBody);
	}

	
    @Route(url="call/qrcheckin/detail")
    public HashMap<String, Object> getCheckIn(VMJExchange vmjExchange){
		return record.getCheckIn(vmjExchange);
	}

	
    @Route(url="call/qrcheckin/list")
    public List<HashMap<String,Object>> getAllCheckIn(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload();
		return checkinqrcheckinServiceImpl.getAllCheckIn();
	}

    public List<HashMap<String,Object>> transformCheckInListToHashMap(List<CheckIn> CheckInQRCheckInList){
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
        for(int i = 0; i < CheckInQRCheckInList.size(); i++) {
            resultList.add(CheckInQRCheckInList.get(i).toHashMap());
        }

        return resultList;
	}

	
    @Route(url="call/qrcheckin/delete")
    public List<HashMap<String,Object>> deleteCheckIn(VMJExchange vmjExchange){
		Map<String, Object> requestBody = vmjExchange.getPayload(); 
		if (vmjExchange.getHttpMethod().equals("OPTIONS")) {
			return null;
		}
		
		return checkinqrcheckinServiceImpl.deleteCheckIn(requestBody);
	}

	public boolean checkIn(String QRCode) {
		// TODO: implement this method
		throw new UnsupportedOperationException();
	}
	
}
