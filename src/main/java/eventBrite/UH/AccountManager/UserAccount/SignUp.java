package eventBrite.UH.AccountManager;

import java.util.Scanner;
import java.util.ArrayList;
import eventBrite.UH.EventTools.MailNotifier;
import eventBrite.UH.EventTools.EventTypes.Return;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

class SignUp 
{
	private UserInfo 		userInfo;
	private PasswordHash 	passwdHash;
	private Scanner 		in;

	public UserInfo getUserInfo() {return userInfo;}

	public SignUp(PasswordHash passwdHash) 
	{
		userInfo = null;
		in = new Scanner(System.in);
		this.passwdHash = passwdHash;
	}
	
	public Return signUpPage()
	{

		System.out.println("++++++++++++++++++++");
		System.out.println("       SIGNUP       ");
		System.out.println("++++++++++++++++++++");
		System.out.print("Enter Your FirstName: ");
		String firstName = in.next();
		System.out.print("Enter Your LastName: ");
		String lastName = in.next();
		System.out.print("Enter Your Email: ");
		String email = in.next();
		System.out.print("Enter Your Password: ");
		String password = in.next();
		System.out.print("Confirm Your Password: ");
		String passwordConfirm = in.next();     
		System.out.println("NOTE*** Make sure to not share your password with anyone! ");
		System.out.println("++++++++++++++++++++");

		return createUserInfo(firstName, lastName, email, password, passwordConfirm);
	}

	public Return signUpError(Return ret)
	{
		Return.printError(ret);
		System.out.println("Do you want to go back to main page or continue your account creation");
		System.out.println("Continue?[Y/N]:");
		String resp = in.next();
		if("N" == resp.toUpperCase() || "NO" == resp.toUpperCase())
			return Return.RESET;
		return Return.CONTINUE;
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
