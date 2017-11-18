package eventBrite.UH.AccountManager;

import java.util.Scanner;
import java.util.ArrayList;

public class SignUp {
  public static void SignupPage(){
        
        Scanner in = new Scanner(System.in);
        System.out.println("++++++++++++++++++++");
        System.out.println("       SIGNUP       ");
        System.out.println("++++++++++++++++++++");
        System.out.print("Enter Your UserName: ");
        String uName = in.next();
        System.out.print("Enter Your FirstName: ");
        String fName = in.next();
        System.out.print("Enter Your LastName: ");
        String lName = in.next();
        System.out.print("Enter Your Password: ");
        String pWord = in.next();
        System.out.println("NOTE*** Make sure to not share your password with anyone! ");
        System.out.println("++++++++++++++++++++");

        System.out.println(" ****Account Created**** ");
        System.out.println("UserName: " + uName);
        System.out.println("FirstName: " + fName);
        System.out.println("LastName: " + lName);
        System.out.println("Password: " + pWord);
        
        
        
       }
 }
