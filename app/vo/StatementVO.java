/**
 * 
 */
package vo;

import java.io.Serializable;
import java.util.Date;

import models.Account;

/**
 * @author adhiraima
 *
 */
public class StatementVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public long trxnId;
	public Account account;
	public String trxnType;
	public Integer amount;
	public Date date;
}
