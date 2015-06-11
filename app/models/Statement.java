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
import javax.persistence.ManyToOne;

import vo.StatementVO;

import com.avaje.ebean.Model;
/**
 * @author adhiraima
 *
 */

@Entity(name="account_summary")
public class Statement extends Model {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="trxn_id")
	private long trxnId;
	
	@ManyToOne(targetEntity=Account.class)
	@Column(name="acc_number")
	private Account account;
	
	@Column(name="trxn_type")
	private String trxnType;
	
	@Column(name="amount")
	private Integer amount;
	
	@Column(name="trxn_date")
	private Date date;
	
	public Statement() {
		super();
	}
	
	public Statement(Account account, String trxnType, Integer amount, Date date) {
		super();
		this.account = account;
		this.trxnType = trxnType;
		this.amount = amount;
		this.date = date;
	}

	@SuppressWarnings("deprecation")
	private static Finder<Long, Statement> find 
		= new Finder<Long, Statement>(Long.class, Statement.class);

	public long getTrxnId() {
		return trxnId;
	}

	public void setTrxnId(long trxnId) {
		this.trxnId = trxnId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getTrxnType() {
		return trxnType;
	}

	public void setTrxnType(String trxnType) {
		this.trxnType = trxnType;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public static Statement findEntry(long trxnId) {
		return find.byId(trxnId);
	}
	
	public static List<Statement> findFiltered(String fromDate, 
			String toDate, long accNumber) {
		return find.where().eq("account.accNumber", accNumber)
				.between("date", fromDate, toDate).findList();
	}
	
	public static List<Statement> findFiltered(long accNumber) {
		return find.where().eq("account.accNumber", accNumber)
				.findList();
	}
	
	public StatementVO getVO() {
		StatementVO vo = new StatementVO();
		vo.trxnId = this.getTrxnId();
		vo.account = this.getAccount();
		vo.amount = this.getAmount();
		vo.date = this.getDate();
		vo.trxnType = this.getTrxnType();
		return vo;
	}
}
