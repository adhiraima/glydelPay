/**
 * 
 */
package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import vo.AccountVO;

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
	
	
	
	public Account(long glydelCrn, Integer balance, Date lastTrxnDate,
			boolean isActive) {
		super();
		this.glydelCrn = glydelCrn;
		this.balance = balance;
		this.lastTrxnDate = lastTrxnDate;
		this.isActive = isActive;
	}

	@SuppressWarnings("deprecation")
	private static Finder<Long, Account> find 
		= new Finder<Long, Account>(Long.class, Account.class);

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
	
	public static Account find(long accNumber) {
		return find.byId(accNumber);
	}
	
	public static List<Account> findAll() {
		return find.where().eq("isActive", Boolean.TRUE).findList();
	}
	
	public AccountVO getVO() {
		AccountVO vo = new AccountVO();
		vo.accNumber = this.getAccNumber();
		vo.balance = this.getBalance();
		vo.glydelCrn = this.getGlydelCrn();
		vo.isActive =this.isActive();
		vo.lastTrxnDate = this.getLastTrxnDate();
		return vo;
	}
	
	public static Account debitAccount(Account account, Integer amount) {
		account.setBalance(account.getBalance() - amount);
		return account;
	}
	
	public static Account creditAccount(Account account, Integer amount) {
		account.setBalance(account.getBalance() + amount);
		return account;
	}
}
