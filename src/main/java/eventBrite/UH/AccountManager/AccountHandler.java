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
			return accountFactory.create(AccountType.GUEST, null);

		SignUp userSignUp 	= new SignUp(passwdHash);
		Return ret 			= userSignUp.signUp();

		if(ret == Return.SUCCESS)
			return accountFactory.create(AccountType.MEMBER, userSignUp.getUserInfo());;
		
		return null;
	}	
}
