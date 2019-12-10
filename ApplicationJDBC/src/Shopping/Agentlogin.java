package Shopping;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;

public class Agentlogin {

	public void Login() throws SQLException {
		//Connection con=new Connection();
				java.sql.Connection con=null;//Connection object
				//2)create a Connection
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping","root","");
				Scanner s=new Scanner(System.in);
				int m;int f=0;
				System.out.println("Enter the user name");
				String username=s.next();
				System.out.println("Enter the password");
				String password=s.next();
				Statement smt=con.createStatement();
				ResultSet rr=smt.executeQuery("Select * from agentlogin");
				while(rr.next())
				{
					if(username.equals(rr.getString(1))&&password.equals(rr.getString(2)))
					{
						f=1;
					}
				}
				if(f==1)	
				{
					System.out.println("Sucessfully verified");
					do {
						System.out.println("1.BuySell\n2.View Product\n3.LogOut");
						System.out.println("Enter the choice");
						m=s.nextInt();
						switch(m)
						{
						case 1:
							System.out.println(" Enter the product Id:");
							int pid=s.nextInt();
							System.out.println(" Enter the Quantity");
							int qnty=s.nextInt();
							int flag=0;
							Statement smt2=con.createStatement();
							ResultSet rr2=smt2.executeQuery("Select * from addproduct");
							double price=0;int q;int q1=0;
						//	double price=rr2.getDouble(4);
							while(rr2.next())
							{
								if(pid==rr2.getInt(1))
								{
								price=rr2.getDouble(4);
								int quantity=rr2.getInt(3);
								if(qnty<=quantity)
								{
									double sum=price*qnty;
									System.out.println(" Cost is-->"+sum);
									flag=1;
								}
								else
								{
									System.out.println("Stack over flow");
									flag=0;
								}
							}
							}
						
							if(flag==1)
							{
							Statement smt4=con.createStatement();
							ResultSet rs4=smt4.executeQuery("Select * from addproduct");
							int qua=0;
							while(rs4.next())
							{
								int idd=rs4.getInt(1);
								if(idd==pid)
								{
									int qq=rs4.getInt(3);
									qua=qq-qnty;
								}
							}
							
							PreparedStatement pss=(PreparedStatement) con.prepareStatement("update addproduct set minsellquantity=? where productid=?;");
							pss.setInt(1,qua);
							pss.setInt(2,pid);
							pss.executeUpdate();
						//	System.out.println("Updated sucessfully");
							}
						break;
						case 2:
							Statement smt1=con.createStatement();
							ResultSet rs=smt1.executeQuery("Select * from addproduct");
							System.out.println("#####********************************##");
							System.out.println("productid"+"\t"+"product name"+"\t"+"minsellquantity"+"\t"+"price");
							
							while(rs.next())
							{
								System.out.println("product id---->"+rs.getInt(1)+"\n"+"product name--->"+rs.getString(2)+"\n"+"minsellquantity-->"+rs.getInt(3)+"\n"+"price-->"+rs.getDouble(4));
							}
							System.out.println("#####********************************##");
							break;
						case 3:
							return;
						}
	}while(m!=0);


				}
				else
				{
					System.out.println("Incorrect user or and password");
				}
				}
	}

