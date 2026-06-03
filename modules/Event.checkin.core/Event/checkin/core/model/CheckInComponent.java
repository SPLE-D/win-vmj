package Event.checkin.core.model;

import java.util.*;
import java.lang.*;
import id.ac.ui.cs.prices.winvmj.core.Route;
import id.ac.ui.cs.prices.winvmj.core.VMJExchange;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name="checkin_comp")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CheckInComponent implements CheckIn{
	@Id
	protected int checkInId; 
	protected boolean attended;
	protected String objectName = CheckInComponent.class.getName();
	@Column(name = "modulesequence")
	protected String moduleSequence;

	public CheckInComponent() {

	} 

	public CheckInComponent(
        int checkInId, boolean attended
    ) {
        this.checkInId = checkInId;
        this.attended = attended;
    }

	public int getCheckInId() {
		return this.checkInId;
	}

	public void setCheckInId(int checkInId) {
		this.checkInId = checkInId;
	}
	public boolean getAttended() {
		return this.attended;
	}

	public void setAttended(boolean attended) {
		this.attended = attended;
	}
	public String getModuleSequence() {
		return this.moduleSequence;
	}

	public void setModuleSequence(String moduleSequence) {
		this.moduleSequence = moduleSequence;
	}
 
	public abstract boolean checkIn();

	@Override
    public String toString() {
        return "{" +
            " checkInId='" + getCheckInId() + "'" +
            " attended='" + getAttended() + "'" +
            "}";
    }
	
}
