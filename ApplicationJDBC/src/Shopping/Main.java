package Shopping;

import java.sql.SQLException;
import java.util.Scanner;



public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		int n;
		Scanner s=new Scanner(System.in);
		do{
		System.out.println("1.Admin login\n2.Agent Login\n3.Exit");
		n=s.nextInt();
		switch(n)
		{
		case 1:
			Adminlogin obj1=new Adminlogin();
			obj1.Admin();
		break;
		case 2:
			Agentlogin obj2=new Agentlogin();
			obj2.Login();
		case 3:
			return;
		}
		}while(n!=0);
	}
}
	
	
		
