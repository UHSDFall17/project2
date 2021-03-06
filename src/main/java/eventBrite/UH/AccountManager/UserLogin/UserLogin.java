package eventBrite.UH.AccountManager;

import java.sql.SQLException;
import java.util.Scanner;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import eventBrite.UH.DatabaseManager.DBUserInfo;
import eventBrite.UH.EventTools.EventTypes.Return;
import eventBrite.UH.EventTools.MailNotifier;
import eventBrite.UH.EventTools.EventInputScanner;

class UserLogin
{
	private PasswordHash 	passwdHash;
	private	String 			email;
	private	String 			password;
	private UserInfo 		userInfo;

	public UserLogin(PasswordHash passwdHash)
	{
		this.passwdHash = passwdHash;
		reset();
	}

	public UserInfo getUserInfo() {return userInfo;}
	public Return login()
	{
		Return ret = loadLoginPage();

		if((ret != Return.SUCCESS) && (ret != Return.RESET))
		{
			Return.printError(ret);
			return login();
		}

		if(ret == Return.RESET)
			reset();

		return ret;
	}

	private Return loadLoginPage()
	{
		Scanner sc = EventInputScanner.getScanner();
		if(EventInputScanner.continueOrReset("Login to your Account") == Return.RESET)
			return Return.RESET;

		System.out.println("Input your email:");
		email = sc.nextLine();
		System.out.println("Input your password:");
		password = sc.nextLine();

		return verifyLoginInfo();
	}

	private Return verifyLoginInfo()
	{
		if(!MailNotifier.checkEmailAddressFormat(email))
			return Return.EEMAILFORMAT;

		try 
		{
			userInfo = DBUserInfo.getUserInfoByUserEmail(email);
		} 
		catch (SQLException | ClassNotFoundException e) 
		{
			return Return.EACCOUNTNOTFOUND;
		}

		try
		{
			if(!passwdHash.validatePassword(password, userInfo.getPassword()))
				return Return.EWRONGPASSWD;
		}
		catch (Exception e)
		{
			System.out.println(e);
			return Return.EXCEPTIONRAISED;
		}

		return Return.SUCCESS;
	}

	private void reset()
	{
		email 	 = null;
		password = null;
		userInfo = null;
	}
}
