/**
 * 
 */
package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Account;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import vo.AccountVO;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.node.ObjectNode;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * @author adhiraima
 *
 */
public class AccountController extends Controller {
	
	
	public static Result get(long accNumber) {
		ObjectNode jsonResult = Json.newObject();
		Account account = Account.find(accNumber);
		if (null != account ) {
			AccountVO vo = account.getVO();
			JSONSerializer serializer = new JSONSerializer().include("*").exclude("*.class");
			return ok(serializer.serialize(vo));
		} else {
			jsonResult.put("errorMessage", "Account with account #"+accNumber+" not found!!");
			return notFound(jsonResult);
		}
	}
	
	public static Result list() {
		List<Account> accounts = Account.findAll();
		List<AccountVO> vos = new ArrayList<AccountVO>();
		for (Account account : accounts)
			vos.add(account.getVO());
		JSONSerializer serializer = new JSONSerializer().include("*.values").exclude("*.class");
		return ok(serializer.serialize(vos));
	}
	
	public static Result add() {
		ObjectNode jsonResult = Json.newObject();
        Account account = new JSONDeserializer<Account>().deserialize(
                        request().body().asJson().toString(), Account.class);
        if (null != account) {
        	try {
        		account.setActive(Boolean.TRUE);
	        	Ebean.save(account);
	        	jsonResult.put("successMessage", "Account created successfully!!");
	        	return ok(jsonResult);
        	} catch (Exception e) {
        		jsonResult.put("errorMessageMessage", "A problem occoured creating the account!!");
        		return status(INTERNAL_SERVER_ERROR);
        	}
        } else {
        	jsonResult.put("errorMessage", "The request is not in the expected format!!");
        	return badRequest(jsonResult);
        }
		
	}
	
	public static Result update() {
		ObjectNode jsonResult = Json.newObject();
        Account account = new JSONDeserializer<Account>().deserialize(
                        request().body().asJson().toString(), Account.class);
        Account existing = Account.find(account.getAccNumber());
        if (null != existing && existing.getAccNumber() == account.getAccNumber() ) {
        	try {
	        	Ebean.update(account);
	        	jsonResult.put("successMessage", "Account updated successfully!!");
	        	return ok(jsonResult);
        	} catch (Exception e) {
        		jsonResult.put("errorMessageMessage", "A problem occoured updating the account!!");
        		return status(INTERNAL_SERVER_ERROR);
        	}
        } else {
        	jsonResult.put("errorMessage", "The request is not in the expected format!!");
        	return badRequest(jsonResult);
        }
	}
	
	public static Result getBalance(long accNumber) {
		ObjectNode jsonResult = Json.newObject();
		Account account = Account.find(accNumber);
		if (null != account ) {
			jsonResult.put("balance", account.getBalance());
			return ok(jsonResult);
		} else {
			jsonResult.put("errorMessage", "Account with account #"+accNumber+" not found!!");
			return notFound(jsonResult);
		}
	}
	
	public static Result credit(long accNumber, int amount) {
		ObjectNode jsonResult = Json.newObject();
		Account account = Account.find(accNumber);
		if (null != account ) {
			account = Account.creditAccount(account, amount);
			Ebean.update(account);
			jsonResult.put("successMessage", "Account #"+accNumber+" is successfully credited with "+amount+" amount");
			return ok(jsonResult);
		} else {
			jsonResult.put("errorMessage", "Account with account #"+accNumber+" not found!!");
			return notFound(jsonResult);
		}
	}
	
	public static Result debit(long accNumber, int amount) {
		ObjectNode jsonResult = Json.newObject();
		Account account = Account.find(accNumber);
		if (null != account ) {
			account = Account.debitAccount(account, amount);
			Ebean.update(account);
			jsonResult.put("successMessage", "Account #"+accNumber+" is successfully debited by "+amount+" amount");
			return ok(jsonResult);
		} else {
			jsonResult.put("errorMessage", "Account with account #"+accNumber+" not found!!");
			return notFound(jsonResult);
		}
	}
	
	public static Result delete(long accNumber) {
		ObjectNode jsonResult = Json.newObject();
		Account account = Account.find(accNumber);
		if (null != account ) {
			account.setActive(Boolean.FALSE);
			jsonResult.put("successMessage", "Account #"+accNumber+" is successfully deactivated!!");
			Ebean.update(account);
			return ok(jsonResult);
		} else {
			jsonResult.put("errorMessage", "Account with account #"+accNumber+" not found!!");
			return notFound(jsonResult);
		}
	}

}
