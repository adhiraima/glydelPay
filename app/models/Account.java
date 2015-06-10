/**
 * 
 */
package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.avaje.ebean.Model;

/**
 * @author adhiraima
 *
 */
@Entity(name="account")
public class Account extends Model {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="acc_number")
	private long accNumber;
	
	@Column(name="glydel_crn")
	private long glydelCrn;
	
	@Column(name="balance")
	private Integer balance;
	
	@Column(name="last_trxn_date")
	private Date lastTrxnDate;
	
	@Column(name="is_active")
	private boolean isActive;

	public long getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(long accNumber) {
		this.accNumber = accNumber;
	}

	public long getGlydelCrn() {
		return glydelCrn;
	}

	public void setGlydelCrn(long glydelCrn) {
		this.glydelCrn = glydelCrn;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public Date getLastTrxnDate() {
		return lastTrxnDate;
	}

	public void setLastTrxnDate(Date lastTrxnDate) {
		this.lastTrxnDate = lastTrxnDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
