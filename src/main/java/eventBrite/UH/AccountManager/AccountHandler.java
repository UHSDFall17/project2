package eventBrite.UH.AccountManager;

import eventBrite.UH.EventTools.EventTypes.Return;
import eventBrite.UH.EventTools.EventTypes.AccountType;

public class AccountHandler
{
	private static AccountHandler accountHandler = new AccountHandler();
	private PasswordHash passwdHash;

	private AccountHandler() 
	{
		passwdHash = new PasswordHash();
	}
	
	public static AccountHandler getInstance() {return accountHandler;}
	public PasswordHash getPasswdHash() {return passwdHash;}
	public UserAccount create() 
	{
		Return ret;
		SignUp userSignUp = new SignUp(passwdHash);

		while(true)
		{
			ret = userSignUp.signUpPage();
			if(ret == Return.SUCCESS)
				break;
			else
			{
				if(userSignUp.signUpError(ret) == Return.RESET)
				{
					// Repload Main Page after cleaning everything
					break;
				}	
			}
		}

		// userInfo is not created in userSignUP if ret is not Return.SUCCESS
		if(ret != Return.SUCCESS)
			return null;
		
		//Create Account In data Base  userSignUp.getUserINfo();
		return AccountFactory.getInstance().create(AccountType.MEMBER, userSignUp.getUserInfo());
	}	
}
