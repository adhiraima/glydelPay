/**
 * 
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import models.Account;
import play.libs.Json;
import play.mvc.*;
import vo.AccountVO;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.node.ObjectNode;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

import models.Statement;
/**
 * @author adhiraima
 *
 */
public class AccountController extends Controller {
	
	
	public static Result getAccount(Long accNumber) {
		ObjectNode jsonResult = Json.newObject();
		Account account = Account.find(accNumber);
		if (null != account ) {
			AccountVO vo = account.getVO();
			JSONSerializer serializer = new JSONSerializer().include("*.values").exclude("*.class");
			return ok(serializer.serialize(vo));
		} else {
			jsonResult.put("errorMessage", "Account with account #"+accNumber+" not found!!");
			return notFound(jsonResult);
		}
	}

	public static Result getAccountByCRN(Long glydelCrn) {
		ObjectNode jsonResult = Json.newObject();
		Account account = Account.findByCRN(glydelCrn);
		if (null != account ) {
			AccountVO vo = account.getVO();
			JSONSerializer serializer = new JSONSerializer().include("*.values").exclude("*.class");
			return ok(serializer.serialize(vo));
		} else {
			jsonResult.put("errorMessage", "Account with Glydel CRN #"+glydelCrn+" not found!!");
			return notFound(jsonResult);
		}
	}
	
	public static Result listAccounts() {
		List<Account> accounts = Account.findAll();
		List<AccountVO> vos = new ArrayList<AccountVO>();
		for (Account account : accounts)
			vos.add(account.getVO());
		JSONSerializer serializer = new JSONSerializer().include("*.values").exclude("*.class");
		return ok(serializer.serialize(vos));
	}
	
	public static Result addAccount() {
		System.out.println("coming to add account");
		ObjectNode jsonResult = Json.newObject();
        Account account = new JSONDeserializer<Account>().deserialize(
                        request().body().asJson().toString(), Account.class);
        if (null != account) {
        	try {
        		account.setLastTrxnDate(new Date());
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
	
	public static Result updateAccount() {
		ObjectNode jsonResult = Json.newObject();
        Account account = new JSONDeserializer<Account>().deserialize(
                        request().body().asJson().toString(), Account.class);
        Account existing = Account.find(account.getAccNumber());
        if (null != existing && existing.getAccNumber() == account.getAccNumber() ) {
        	try {
        		account.setLastTrxnDate(new Date());
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
	
	public static Result getBalance(Long accNumber) {
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
	
	public static Result credit(Long accNumber, Integer amount) {
		ObjectNode jsonResult = Json.newObject();
		Account account = Account.find(accNumber);
		if (null != account ) {
			account = Account.creditAccount(account, amount);
			Ebean.update(account);
			Statement statement = new Statement();
			statement.setAccount(account);
			statement.setDate(new Date());
			statement.setTrxnType("CREDIT");
			statement.setAmount(amount);
			Ebean.save(statement);
			jsonResult.put("successMessage", "Account #"+accNumber+" is successfully credited with "+amount+" amount");
			return ok(jsonResult);
		} else {
			jsonResult.put("errorMessage", "Account with account #"+accNumber+" not found!!");
			return notFound(jsonResult);
		}
	}
	
	public static Result debit(Long accNumber, Integer amount) {
		ObjectNode jsonResult = Json.newObject();
		Account account = Account.find(accNumber);
		if (null != account ) {
			account = Account.debitAccount(account, amount);
			Ebean.update(account);
			Statement statement = new Statement();
			statement.setAccount(account);
			statement.setDate(new Date());
			statement.setTrxnType("DEBIT");
			statement.setAmount(amount);
			Ebean.save(statement);
			jsonResult.put("successMessage", "Account #"+accNumber+" is successfully debited by "+amount+" amount");
			return ok(jsonResult);
		} else {
			jsonResult.put("errorMessage", "Account with account #"+accNumber+" not found!!");
			return notFound(jsonResult);
		}
	}
	
	public static Result deleteAccount(Long accNumber) {
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
