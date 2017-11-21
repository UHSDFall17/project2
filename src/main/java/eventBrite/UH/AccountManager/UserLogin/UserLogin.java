package eventBrite.UH.AccountManager;

import java.util.Scanner;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import eventBrite.UH.EventTools.EventTypes.Return;
import eventBrite.UH.EventTools.MailNotifier;

class UserLogin
{
	private Scanner 		sc;
	private PasswordHash 	passwdHash;
	private	String 			email;
	private	String 			password;
	private UserInfo 		userInfo;

	public UserLogin(PasswordHash passwdHash)
	{
		sc 		 		= new Scanner(System.in);
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
		System.out.println("Login to your Account: [Continue/cancel]\n");
		String resp = sc.nextLine();

		if(resp.toUpperCase().equals("CANCEL"))
			return Return.RESET;

		if(!resp.toUpperCase().equals("CONTINUE"))
		{
			Return.printError(Return.EWRONGINPUT);
			return loadLoginPage();
		}
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

		// userInfo = DB.getUserInfoByEmail(email);

		if(userInfo == null)
			return Return.EACCOUNTNOTFOUND;

		try
		{
			if(!passwdHash.validatePassword(password, userInfo.getPassword()))
				return Return.EWRONGPASSWD;
		}
		catch (NoSuchAlgorithmException | InvalidKeySpecException e)
		{
			System.out.println(e);
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