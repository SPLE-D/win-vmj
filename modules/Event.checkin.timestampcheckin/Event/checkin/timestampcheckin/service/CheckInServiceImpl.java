package Event.checkin.timestampcheckin.service;

import java.util.*;
import java.lang.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import id.ac.ui.cs.prices.winvmj.core.VMJExchange;

import Event.checkin.core.service.CheckInServiceDecorator;
import Event.checkin.core.service.CheckInServiceComponent;
import Event.checkin.core.model.CheckIn;
import Event.checkin.core.model.CheckInDecorator;
import Event.checkin.core.model.CheckInComponent;
import Event.checkin.CheckInFactory;

public class CheckInServiceImpl extends CheckInServiceDecorator {
    public CheckInServiceImpl (CheckInServiceComponent record) {
        super(record);
    }

 	public CheckIn createCheckIn(Map<String, Object> requestBody){
		Date timestamp = parseDate(requestBody.get("timestamp"));
		CheckIn checkin = record.createCheckIn(requestBody);
		CheckIn checkintimestampcheckindeco = CheckInFactory.createCheckIn(
			"Event.checkin.timestampcheckin.model.CheckInImpl",
			(CheckInComponent) checkin,
			timestamp
		);
		Repository.saveObject(checkintimestampcheckindeco);
		return checkintimestampcheckindeco;
	}

	public CheckIn createCheckIn(Map<String, Object> requestBody, int id){
		Date timestamp = parseDate(requestBody.get("timestamp"));
		CheckIn checkin = record.createCheckIn(requestBody, id);
		CheckIn checkintimestampcheckin = CheckInFactory.createCheckIn(
			"Event.checkin.timestampcheckin.model.CheckInImpl",
			(CheckInComponent) checkin,
			timestamp
		);
		return checkintimestampcheckin;
	}

    public HashMap<String, Object> updateCheckIn(Map<String, Object> requestBody){
		String idStr = (String) requestBody.get("checkInId");
		int id = Integer.parseInt(idStr);
		
		CheckIn checkintimestampcheckin = createCheckIn(requestBody, id);
		
		Repository.updateObject(checkintimestampcheckin);
		checkintimestampcheckin = Repository.getObject(id);

		return checkintimestampcheckin.toHashMap();
	}

	public HashMap<String, Object> getCheckIn(String idStr){
		int id = Integer.parseInt(idStr);
		CheckIn checkintimestampcheckin = Repository.getObject(id);
		return checkintimestampcheckin.toHashMap();
	}

	public HashMap<String, Object> getCheckInById(int id){
		List<HashMap<String, Object>> checkinList = getAllCheckIn();
		for (HashMap<String, Object> checkin : checkinList){
			int checkin_id = ((Number) checkin.get("checkInId")).intValue();
			if (checkin_id == id){
				return checkin;
			}
		}
		return null;
	}

    public List<HashMap<String,Object>> getAllCheckIn(){
		List<CheckIn> List = Repository.getAllObject("checkin_timestampcheckin");
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

	
	private Date parseDate(Object value) {
		if (value == null) {
			return new Date();
		}
		if (value instanceof Date) {
			return (Date) value;
		}
		if (value instanceof Number) {
			return new Date(((Number) value).longValue());
		}

		String text = String.valueOf(value);
		try {
			return Date.from(Instant.parse(text));
		} catch (Exception ignored) {
		}
		try {
			LocalDateTime dateTime = LocalDateTime.parse(text);
			return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
		} catch (Exception ignored) {
		}
		try {
			LocalDate date = LocalDate.parse(text);
			return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		} catch (Exception ignored) {
		}
		return new Date();
	}
}
