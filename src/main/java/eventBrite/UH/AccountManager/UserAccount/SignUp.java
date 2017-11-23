package eventBrite.UH.AccountManager;

import java.util.Scanner;
import java.util.ArrayList;
import eventBrite.UH.EventTools.MailNotifier;
import eventBrite.UH.EventTools.EventTypes.Return;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import eventBrite.UH.EventTools.EventInputScanner;

class SignUp 
{
	private Scanner 		sc;
	private PasswordHash 	passwdHash;
	private UserInfo 		userInfo;

	public UserInfo getUserInfo() {return userInfo;}

	public SignUp(PasswordHash passwdHash) 
	{
		userInfo = null;
		sc = new Scanner(System.in);
		this.passwdHash = passwdHash;
	}
	
	public Return signUp()
	{
		Return ret = loadSignUpPage();
		if((ret != Return.SUCCESS) && (ret != Return.RESET))
		{
			Return.printError(ret);
			return signUp();
		}

		if(ret == Return.RESET)
			reset();

		return ret;
	}

	private void reset() {userInfo = null;}

	private Return loadSignUpPage()
	{
		if(EventInputScanner.continueOrReset("Create a new account") == Return.RESET)
			return Return.RESET;

		System.out.println("++++++++++++++++++++");
		System.out.println("       SIGNUP       ");
		System.out.println("++++++++++++++++++++");
		System.out.print("Enter Your FirstName: ");
		String firstName = sc.next();
		System.out.print("Enter Your LastName: ");
		String lastName = sc.next();
		System.out.print("Enter Your Email: ");
		String email = sc.next();
		System.out.print("Enter Your Password: ");
		String password = sc.next();
		System.out.print("Confirm Your Password: ");
		String passwordConfirm = sc.next();     
		System.out.println("NOTE*** Make sure to not share your password with anyone! ");
		System.out.println("++++++++++++++++++++");

		return createUserInfo(firstName, lastName, email, password, passwordConfirm);
	}

	private Return createUserInfo(
		String firstName, 
		String lastName, 
		String email, 
		String password,
		String passwordConfirm)
	{
		String hashedPassword = null;

		if(!MailNotifier.checkEmailAddressFormat(email))
			return Return.EEMAILFORMAT;
		if(!password.equals(passwordConfirm))
			return Return.EPASSWDMATCH;
		try
		{
			hashedPassword = passwdHash.generatePasswordHash(password);
		}	
		catch (NoSuchAlgorithmException | InvalidKeySpecException e)
		{	
			System.out.println(e);
			return Return.EXCEPTIONRAISED;
		}
		userInfo = new UserInfo(firstName, lastName, email, hashedPassword);
		return Return.SUCCESS;
	}	
}
