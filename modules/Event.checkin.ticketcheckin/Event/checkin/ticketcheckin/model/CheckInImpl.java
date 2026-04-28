package Event.checkin.ticketcheckin.model;

import java.util.*;
import java.lang.*;
import id.ac.ui.cs.prices.winvmj.core.Route;
import id.ac.ui.cs.prices.winvmj.core.VMJExchange;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;

import Event.checkin.core.model.CheckInDecorator;
import Event.checkin.core.model.CheckIn;
import Event.checkin.core.model.CheckInComponent;

@Entity(name="checkin_ticketcheckin")
@Table(name="checkin_ticketcheckin")
public class CheckInImpl extends CheckInDecorator {

	protected boolean isValid;
	protected boolean isUsed;
	public CheckInImpl() {
        super();
		Random r = new Random();
		this. = Math.abs(r.nextInt());
        this.objectName = CheckInImpl.class.getName();
    }

	public CheckInImpl(CheckInComponent record, boolean isValid, boolean isUsed) {
		super(record, CheckInImpl.class.getName());
		this.isValid = isValid;
		this.isUsed = isUsed;
		this.objectName = CheckInImpl.class.getName();
	}

	public boolean getIsValid() {
		return this.isValid;
	}

	public void setIsValid(boolean isValid) {
		this.isValid = isValid;
	}
	public boolean getIsUsed() {
		return this.isUsed;
	}

	public void setIsUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public boolean checkIn(String uniqueCode) {
		// TODO: implement this method
		throw new UnsupportedOperationException();
	}

	public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> map = record.toHashMap();
        map.put("checkInId", checkInId);
		map.put("isValid", getIsValid());
		map.put("isUsed", getIsUsed());

        return map;
    }

}
