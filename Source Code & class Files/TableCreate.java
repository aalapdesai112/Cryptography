import java.sql.*; 
public class TableCreate
{
public static void main(String args[])
{
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

Connection c=DriverManager.getConnection("jdbc:odbc:newcrypto");

Statement s=c.createStatement();

s.execute("create Table User(username char,password char)");

System.out.println("Table Created");
}

catch(Exception e)
{
e.printStackTrace();
}
}
}