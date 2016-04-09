import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

class ChangeUserDetails extends JFrame implements ActionListener
{
public JTextField tf1;
public JPasswordField tf2;
public JPasswordField tf3;

JButton b1,b2,b3;
JLabel l1,l2,l3,l4;
Statement s;
String s1,s2,s3;
ImageIcon i1;

public ChangeUserDetails()
{
Container cp=getContentPane();
cp.setLayout(null);
cp.setBackground(new Color(160,254,254));

l1=new JLabel("User-Name:");
l1.setBounds(100,240,100,30);

l2=new JLabel("Enter Old Password:");
l2.setBounds(100,300,150,30);		

l3=new JLabel("Enter New Password:");
l3.setBounds(100,360,150,30);	

i1=new ImageIcon("images/img1.jpg");
l4=new JLabel(i1);
l4.setBounds(0,0,220,220);

tf1=new JTextField(15);
tf1.setBounds(260,240,200,25);

tf2=new JPasswordField(15);
tf2.setBounds(260,300,200,25);

tf3=new JPasswordField(15);
tf3.setBounds(260,360,200,25);
	
b1=new JButton("Change Password");
b1.setBounds(80,410,170,30);
b1.setBackground(Color.white);
b1.setForeground(Color.black);

b2=new JButton("Reset");
b2.setBounds(270,410,170,30);
b2.setBackground(Color.white);
b2.setForeground(Color.black);

b3=new JButton("Back");
b3.setBounds(460,410,170,30);
b3.setBackground(Color.white);
b3.setForeground(Color.black);

cp.add(l1);
cp.add(tf1);
cp.add(l2);
cp.add(tf2);
cp.add(l3);
cp.add(tf3);
cp.add(l4);

cp.add(b1);
cp.add(b2);
cp.add(b3);

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
s1=tf1.getText();
s2=tf2.getText();
s3=tf3.getText();

try
{
ResultSet rs=s.executeQuery("Select * from User where username ='"+s1+ "' and password ='"+s2+"'");

if(rs.next() && s2.length()>0 && s3.length()>0)
{
s.execute("update User set password='"+s3+"' where username='"+s1+"'");

System.out.println("Password has been changed in the Table");

JOptionPane.showMessageDialog(this,"Your password has been Changed SuccessFully","Done",JOptionPane.INFORMATION_MESSAGE);

tf1.setText("");
tf2.setText("");
tf3.setText("");
}

else if(s1.equals("") && s2.equals("") && s3.equals(""))
{
JOptionPane.showMessageDialog(this,"ERROR!!!!\nDetails Cannot be null\nPlease Enter All the Details.","Error",JOptionPane.ERROR_MESSAGE);
}

else if(s1.equals("") && s2.equals(""))
{
JOptionPane.showMessageDialog(this,"ERROR!!!!\nUser-name And Password Cannot be null\nPlease Enter the User-name and Password.","Error",JOptionPane.ERROR_MESSAGE);
}

else if(s2.equals("") && s3.equals(""))
{
JOptionPane.showMessageDialog(this,"ERROR!!!!\nBoth Password Fields Cannot be null.\nPlease Enter the Password twice for verification.","Error",JOptionPane.ERROR_MESSAGE);
}

else if(s1.equals(""))
{
JOptionPane.showMessageDialog(this,"ERROR!!!!\nUser-name Cannot be null\nPlease Enter the User-name.","Error",JOptionPane.ERROR_MESSAGE);
}

else if(s2.equals(""))
{
JOptionPane.showMessageDialog(this,"ERROR!!!!\nPassword Cannot be null\nPlease Enter the Password.","Error",JOptionPane.ERROR_MESSAGE);
}

else if(s3.equals(""))
{
JOptionPane.showMessageDialog(this,"ERROR!!!!\nNew Password Cannot be null\nPlease Enter the  New Password","Error",JOptionPane.ERROR_MESSAGE);
}

else
{
JOptionPane.showMessageDialog(this,"ERROR!!!!\nNo such User-name and password Exist or Database is empty\nPlease Enter the Valid User-name and Password or insert data in Database","Error",JOptionPane.ERROR_MESSAGE);
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
tf3.setText("");
}

if(e.getSource() == b3)
{
JFrame hm=new AdminLogin1();
hm.setVisible(true);
hm.setSize(850,700);
hm.setTitle("Login Screen For Data Cryptography System");
hm.setCursor(JFrame.HAND_CURSOR);
hm.setResizable(false);
this.setVisible(false);
}

}
}

public class ChangeUserDetails1
{
public static void main(String args[])
{
JFrame.setDefaultLookAndFeelDecorated(true);
JDialog.setDefaultLookAndFeelDecorated(true);

JFrame ffc=new ChangeUserDetails();
ffc.setTitle("Set Password of User");
ffc.setSize(650,500);
ffc.setLocation(100,100);
ffc.setVisible(true);
ffc.setResizable(false);
ffc.setCursor(JFrame.HAND_CURSOR);
}
}