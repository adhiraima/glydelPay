/**
 * 
 */
package vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author adhiraima
 *
 */
public class AccountVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public long accNumber;
	public long glydelCrn;
	public Integer balance;
	public Date lastTrxnDate;
	public boolean isActive;

}
