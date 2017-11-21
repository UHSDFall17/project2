package eventBrite.UH.AccountManager;

import eventBrite.UH.EventTools.EventTypes.Return;
import eventBrite.UH.EventTools.EventTypes.AccountType;

public class AccountHandler
{
	private static 	AccountHandler 		accountHandler = new AccountHandler();
	private			AccountFactory		accountFactory = AccountFactory.getInstance();

	private 		PasswordHash 		passwdHash;

	private AccountHandler() 
	{
		passwdHash = new PasswordHash();
	}
	
	public static AccountHandler getInstance() {return accountHandler;}

	public UserAccount login()
	{
		UserLogin 	userLogin 	= new UserLogin(passwdHash);
		Return 		ret 	  	= userLogin.login();

		if(ret == Return.SUCCESS)
			return accountFactory.create(AccountType.MEMBER, userLogin.getUserInfo());

		return null;
	}

	public UserAccount create(AccountType accountType)
	{
		if(accountType == AccountType.GUEST)
			return accountFactory.create(accountType, null);

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
		return accountFactory.create(AccountType.MEMBER, userSignUp.getUserInfo());
	}	
}
