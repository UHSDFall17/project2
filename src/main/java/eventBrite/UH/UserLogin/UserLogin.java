package eventBrite.UH.UserLogin.UserLogin;
package eventBrite.UH.UserAccount.SignUp;
package eventBrite.UH.UserAccount.UserInfo;
import java.util.Scanner;

public class UserLogin {
  public static void LoginPage(){
      
      Scanner input = new Scanner(System.in); 
      System.out.println("++++++++++++++++++++");
    
      System.out.print("Enter Username: ");
      String uName = input.next();
      System.out.print("Enter FirstName: ");
      String fName = input.next();
      System.out.print("Enter LastName: ");   
      String lName = input.next();
      System.out.print("Enter Password: "); 
      String pWord = input.next();
    
      System.out.println("++++++++++++++++++++");
        
       //if(check.auth()) 
      //  System.out.println("You are logged in");


    
  }
  
}
