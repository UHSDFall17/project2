package eventBrite.UH.AccountManager;

import eventBrite.UH.EventTools.EventTypes.AccountType;

class AccountFactory
{
	private static AccountFactory accountFactory = new AccountFactory();

	public static AccountFactory getInstance() {return accountFactory;}

	private AccountFactory() {}
	
	public UserAccount create(AccountType accountType, UserInfo userInfo)
	{
		switch (accountType)
		{
			case GUEST:
				return new GuestAccount(userInfo);
			case MEMBER:
				return new MemberAccount(userInfo);
			case ADMIN:
				break;
			default:
				break;
		}
		return null;
	}
}