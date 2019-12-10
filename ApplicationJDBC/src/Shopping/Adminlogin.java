package Shopping;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;
public class Adminlogin {

	public void Admin()throws SQLException,ClassNotFoundException {
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
		ResultSet rr=smt.executeQuery("Select * from adminlogin");
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
				System.out.println("1.Add product\n2.Display\n3.Remove the product\n4.Update the product\n5.Logout");
				System.out.println("Enter the choice");
				m=s.nextInt();
				switch(m)
				{
				case 1:
					System.out.println(" Enter the product Id:");
					int pid=s.nextInt();
					System.out.println(" Enter the product Name");
					String name=s.next();
					System.out.println(" Enter the minsellquantity:");
					int min=s.nextInt();
					System.out.println(" Enter the price:");
					double price=s.nextDouble();
					PreparedStatement ps= (PreparedStatement) con.prepareStatement("insert into addproduct(productid,productname,minsellquantity,price)values(?,?,?,?);");
					ps.setInt(1,pid);
					ps.setString(2,name);
					ps.setInt(3,min);
					ps.setDouble(4,price);
				ps.executeUpdate();
				System.out.println("Successfull...!");
					System.out.println("Product added successfully");
				break;
				case 2:
					// TODO Auto-generated method stub
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
					System.out.println("Enter the id you want to remove");
					int id=s.nextInt();
					PreparedStatement pd=(PreparedStatement) con.prepareStatement("delete from addproduct where productid=?;");
					pd.setInt(1,id);
					pd.executeUpdate();
					System.out.println("Successfully Deleted...");
					break;
				case 4:
					Statement smt3=con.createStatement();
					ResultSet rr2=smt3.executeQuery("Select * from addproduct");
					while(rr2.next())
					{
						System.out.println("#####********************************##");
						System.out.println("product id---->"+rr2.getInt(1)+"\n"+"product name--->"+rr2.getString(2)+"\n"+"minsellquantity-->"+rr2.getInt(3)+"\n"+"price-->"+rr2.getDouble(4));
						System.out.println("#####********************************##");
					}
					int qua=0;
					System.out.println(" Enter the product Id:");
					int proid=s.nextInt();
					System.out.println(" Enter the minsellquantity:");
					int promin=s.nextInt();
					Statement smt4=con.createStatement();
					ResultSet rs4=smt4.executeQuery("Select * from addproduct");
					while(rs4.next())
					{
						int idd=rs4.getInt(1);
						if(idd==proid)
						{
					int q=rs4.getInt(3);
					qua=q+promin;
						}
					}
					
					PreparedStatement pss=(PreparedStatement) con.prepareStatement("update addproduct set minsellquantity=? where productid=?;");
				
					pss.setInt(1,qua);
					//pss.setString(2,proname);
					pss.setInt(2,proid);
				//	pss.setDouble(4,proprice);
				pss.executeUpdate();
				System.out.println("Product updated successfully");
				break;
					
				case 5:
					return;
						
				}
			}while(m!=0);
		}
			
		}
		
	
	}


