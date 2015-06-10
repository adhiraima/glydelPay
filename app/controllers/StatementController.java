/**
 * 
 */
package controllers;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * @author adhiraima
 *
 */
public class StatementController extends Controller {
	
	public static Result getStatement(long accNumber) {
		return ok();
	}
	
	public static Result getTruncatedStatement(long accNumber, String fromDate, String toDate) {
		return ok();
	}
	
	public static Result addEntry() {
		return ok();
	}

}
