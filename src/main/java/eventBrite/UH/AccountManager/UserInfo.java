package eventBrite.UH.AccountManager;

public class UserInfo{

	private String uName; 
	private String fName;
	private String lName;
	private char pWord;

public UserInfo(String username, String firstname, String lastname, char password) { //constructor

		uName = username;
		pWord = password;
		fName = firstname;
		lName = lastname;
	}
    public String getUsername (){
	return fName;
}

    public char getPassword(){
	return pWord;
}
	
    public String getFirstname(){
	return fName;
}
	
    public String getLastname(){
	return lName;
}
	
public void setUsername(String fName){
	this.fName = fName;
}
	
public void setPassword(char  pWord){
	this.pWord = pWord;
}

public void setFirstname(String fName){
	this.fName = fName;
}
	
public void setLastname(String lName){
	this.lName = lName;
}
}
