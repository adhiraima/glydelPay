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
import javax.persistence.OneToOne;

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
	
	@OneToOne(targetEntity=Account.class)
	@Column(name="acc_number")
	private Account account;
	
	@Column(name="trxn_type")
	private String trxnType;
	
	@Column(name="amount")
	private Integer amount;
	
	@Column(name="trxn_date")
	private Date date;
}
