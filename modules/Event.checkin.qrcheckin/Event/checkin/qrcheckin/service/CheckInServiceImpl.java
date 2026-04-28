package Event.checkin.qrcheckin.service;

import java.util.*;
import java.lang.*;

import id.ac.ui.cs.prices.winvmj.core.VMJExchange;

import Event.checkin.core.service.CheckInServiceDecorator;
import Event.checkin.core.model.CheckInImpl;
import Event.checkin.core.service.CheckInServiceComponent;
import Event.checkin.core.model.CheckIn;
import Event.checkin.core.model.CheckInDecorator;
import Event.checkin.CheckInFactory;

public class CheckInServiceImpl extends CheckInServiceDecorator {
    public CheckInServiceImpl (CheckInServiceComponent record) {
        super(record);
    }

 	public CheckIn createCheckIn(Map<String, Object> requestBody){
		String checkInId = (String) requestBody.get("checkInId");
		boolean attended = (boolean) requestBody.get("attended");
		CheckIn checkinqrcheckin = record.createCheckIn(requestBody);
		CheckIn checkinqrcheckindeco = CheckInFactory.createCheckIn("Event.checkin.qrcheckin", checkinqrcheckin, checkInId, attended);
		Repository.saveObject(checkinqrcheckindeco);
		return checkinqrcheckindeco;
	}

	public CheckIn createCheckIn(Map<String, Object> requestBody, int id){
		CheckIn savedCheckIn = Repository.getObject(id);
		String checkInId = (String) requestBody.get("checkInId");
		boolean attended = (boolean) requestBody.get("attended");
		UUID recordCheckInCheckInId = ((CheckInDecorator) savedCheckIn).getCheckInId();
		CheckIn CheckIn = record.createCheckIn(requestBody, recordCheckInCheckInId);
		CheckIn checkinqrcheckin = CheckInFactory.createCheckIn("Event.checkin.qrcheckin.model.CheckInImpl", CheckIn, checkInId, attended);
		return checkinqrcheckin;
	}

    public HashMap<String, Object> updateCheckIn(Map<String, Object> requestBody){
		String idStr = (String) requestBody.get("checkInId");
		
		CheckIn checkinqrcheckin = Repository.getObject(id);
		checkinqrcheckin = createCheckIn(requestBody, id);
		
		Repository.updateObject(checkinqrcheckin);
		checkinqrcheckin = Repository.getObject(id);
		
		//to do: fix association attributes
		
		return checkinqrcheckin.toHashMap();
	}

	public HashMap<String, Object> getCheckIn(String idStr){
		int id = Integer.parseInt(idStr);
		CheckIn checkinqrcheckin = Repository.getObject(id);
		return checkinqrcheckin.toHashMap();
	}

	public HashMap<String, Object> getCheckInById(int id){
		List<HashMap<String, Object>> checkinList = getAllCheckIn();
		for (HashMap<String, Object> checkin : checkinList){
			int checkin_id = ((Double) checkin.get("checkinid")).intValue();
			if (checkin_id == id){
				return checkin;
			}
		}
		return null;
	}

    public List<HashMap<String,Object>> getAllCheckIn(){
		List<CheckIn> List = Repository.getAllObject("checkin_qrcheckin");
		return transformListToHashMap(List);
	}

    public List<HashMap<String,Object>> transformListToHashMap(List<CheckIn> List){
		List<HashMap<String,Object>> resultList = new ArrayList<HashMap<String,Object>>();
        for(int i = 0; i < List.size(); i++) {
            resultList.add(List.get(i).toHashMap());
        }

        return resultList;
	}

    public List<HashMap<String,Object>> deleteCheckIn(Map<String, Object> requestBody){
		String idStr = ((String) requestBody.get("checkInId"));
		int id = Integer.parseInt(idStr);
		Repository.deleteObject(id);
		return getAllCheckIn();
	}

	
	public boolean checkIn(String QRCode) {
		// TODO: implement this method
		throw new UnsupportedOperationException();
	}
}
