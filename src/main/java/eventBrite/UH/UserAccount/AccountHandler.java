package eventBrite.UH.UserAccount;

import eventBrite.UH.EventTools.MailNotifier;
import eventBrite.UH.EventTools.EventTypes;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

class AccountHandler
{
	private PasswordHash passwdHash;

	public AccountHandler() 
	{
		passwdHash = new PasswordHash();
	}

	public PasswordHash getPasswdHash() {return passwdHash;}

	public EventTypes.Return createUserAccount(
		String firstName, 
		String lastName, 
		String email, 
		String password,
		String passwordConfirm)
	{
		if(!MailNotifier.checkEmailAddressFormat(email))
			return EventTypes.Return.EEMAILFORMAT;
		if(!password.equals(passwordConfirm))
			return EventTypes.Return.EPASSWDMATCH;
		try
		{
			passwdHash.generatePasswordHash(password);
		}	
		catch (NoSuchAlgorithmException | InvalidKeySpecException e)
		{	
			System.out.println(e);
			return EventTypes.Return.EXCEPTIONRAISED;
		}
		// Create User Account and put it in the dataBase
		return EventTypes.Return.SUCCESS;
	}	
}
