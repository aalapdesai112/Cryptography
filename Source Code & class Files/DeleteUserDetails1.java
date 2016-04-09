import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

class DeleteUserDetails extends JFrame implements ActionListener
{
JTextField tf1;
JPasswordField tf2;
JButton b1,b2,b3;;
JLabel l1,l2,l3;
Statement s;
ImageIcon i1;

DeleteUserDetails()
{
Container cp=getContentPane();
cp.setLayout(null);
cp.setBackground(new Color(160,254,254));

l1=new JLabel("User-Name:");
l1.setBounds(100,370,80,30);

l2=new JLabel("Password:");
l2.setBounds(100,430,80,30);

i1=new ImageIcon("images/img2.jpg");		
l3=new JLabel(i1);
l3.setBounds(0,0,350,280);

tf1=new JTextField(15);
tf1.setBounds(185,370,220,25);

tf2=new JPasswordField(15);
tf2.setBounds(185,430,220,25);
	
b1=new JButton("Delete");
b1.setBounds(70,490,120,30);
b1.setBackground(Color.white);
b1.setForeground(Color.black);

b2=new JButton("Reset");
b2.setBounds(210,490,120,30);
b2.setBackground(Color.white);
b2.setForeground(Color.black);

b3=new JButton("Back");
b3.setBounds(350,490,120,30);
b3.setBackground(Color.white);
b3.setForeground(Color.black);


cp.add(l1);
cp.add(tf1);
cp.add(l2);
cp.add(tf2);

cp.add(b1);
cp.add(b2);
cp.add(b3);
cp.add(l3);

b1.addActionListener(this);		
b2.addActionListener(this);
b3.addActionListener(this);

addWindowListener(new WindowAdapter()
{
public void windowClosing(WindowEvent evt)
{
System.exit(0);
}
});

try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

Connection c=DriverManager.getConnection("jdbc:odbc:newcrypto");

s=c.createStatement();
}
catch(Exception e)
{
e.printStackTrace();
}
}
public void actionPerformed(ActionEvent e)
{
if(e.getSource() == b1)
{
String s1=tf1.getText();
String s2=tf2.getText();

try
{
ResultSet rs=s.executeQuery("Select * from User where username ='"+s1+ "' and password ='"+s2+"'");

if(rs.next())
{
s.execute("delete from User where username ='"+s1+ "' and password ='"+s2+"'");
JOptionPane.showMessageDialog(this,"Your Account is deleted SuccessFully","Done",JOptionPane.INFORMATION_MESSAGE);
System.out.println("Values Deleted from the table");
tf1.setText("");
tf2.setText("");
}

else if(s1.equals("") && s2.equals("")) 
{
JOptionPane.showMessageDialog(this,"ERROR!!!!\nUser-name and Password Cannot be null\nPlease Enter the User-name and Password","Error",JOptionPane.ERROR_MESSAGE);
}

else if(s1.equals(""))
{
JOptionPane.showMessageDialog(this,"ERROR!!!!\nUser-name Cannot be null.\nPlease Enter the User-name.","Error",JOptionPane.ERROR_MESSAGE);
}

else if(s2.equals("")) 
{
JOptionPane.showMessageDialog(this,"ERROR!!!!\nPassword Cannot be null\nPlease Enter the Password","Error",JOptionPane.ERROR_MESSAGE);
}

else
{
JOptionPane.showMessageDialog(this,"ERROR!!!!\nNo such User-name and password Exist or Database is empty.\nPlease Enter the Valid User-name and Password or insert data in Database.","Error",JOptionPane.ERROR_MESSAGE);
}

}

catch(Exception e1)
{
e1.printStackTrace();
}

}

if(e.getSource() ==b2)
{
tf1.setText("");
tf2.setText("");
}

if(e.getSource() == b3)
{
JFrame hm=new AdminLogin1();
hm.setVisible(true);
hm.setSize(850,700);
hm.setTitle("Login Screen For Data Cryptography System");
hm.setResizable(false);
hm.setCursor(JFrame.HAND_CURSOR);
this.setVisible(false);
}

}
}

public class DeleteUserDetails1
{
public static void main(String args[])
{
JFrame.setDefaultLookAndFeelDecorated(true);
JDialog.setDefaultLookAndFeelDecorated(true);

JFrame fd=new DeleteUserDetails();
fd.setTitle("Delete User Account");
fd.setSize(600,600);
fd.setLocation(100,100);
fd.setVisible(true);
fd.setResizable(false);
fd.setCursor(JFrame.HAND_CURSOR);
}
}