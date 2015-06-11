/**
 * 
 */
package controllers;

import java.util.ArrayList;
import java.util.List;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.node.ObjectNode;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import models.Account;
import models.Statement;
import play.libs.Json;
import play.mvc.*;
import vo.StatementVO;

/**
 * @author adhiraima
 *
 */
public class StatementController extends Controller {
	
	public static Result getStatement(long accNumber) {
		List<Statement> statements = Statement.findFiltered(accNumber);
		List<StatementVO> vos = new ArrayList<StatementVO>();
		for (Statement statement : statements) {
			vos.add(statement.getVO());
		}
		JSONSerializer serializer = new JSONSerializer().include("*.values").exclude("*.class");
		return ok(serializer.serialize(vos));
	}
	
	public static Result getTruncatedStatement(long accNumber, String fromDate, String toDate) {
		List<Statement> statements = Statement.findFiltered(fromDate, toDate, accNumber);
		List<StatementVO> vos = new ArrayList<StatementVO>();
		for (Statement statement : statements) {
			vos.add(statement.getVO());
		}
		JSONSerializer serializer = new JSONSerializer().include("*.values").exclude("*.class");
		return ok(serializer.serialize(vos));
	}
	
	public static Result addEntry() {
		ObjectNode jsonResult = Json.newObject();
        Statement statement = new JSONDeserializer<Statement>().deserialize(
                        request().body().asJson().toString(), Statement.class);
        if (null != statement) {
        	try {
	        	Ebean.save(statement);
	        	jsonResult.put("successMessage", "Statement entry created successfully!!");
	        	return ok(jsonResult);
        	} catch (Exception e) {
        		jsonResult.put("errorMessageMessage", "A problem occoured creating the statement entry!!");
        		return status(INTERNAL_SERVER_ERROR);
        	}
        } else {
        	jsonResult.put("errorMessage", "The request is not in the expected format!!");
        	return badRequest(jsonResult);
        }
	}

}
