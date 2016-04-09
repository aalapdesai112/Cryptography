import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

class AddUserDetails extends JFrame implements ActionListener
{
JTextField tf1;
JPasswordField tf2;
JPasswordField tf3;

JButton b1,b2,b3;
JLabel l1,l2,l3,l4;
Statement s;
ImageIcon i1;

AddUserDetails()
{
Container cp=getContentPane();
cp.setLayout(null);
cp.setBackground(new Color(160,254,254));

l1=new JLabel("User-Name:");
l1.setBounds(100,300,140,30);

l2=new JLabel("Password:");
l2.setBounds(100,360,140,30);		

l3=new JLabel("Re-Enter Password:");
l3.setBounds(100,420,140,30);	
	
i1=new ImageIcon("images/UserAccount.jpg");
l4=new JLabel(i1);
l4.setBounds(0,0,215,249);

tf1=new JTextField(20);
tf1.setBounds(250,300,200,25);

tf2=new JPasswordField(20);
tf2.setBounds(250,360,200,25);

tf3=new JPasswordField(20);
tf3.setBounds(250,420,200,25);

b1=new JButton("Submit");
b1.setBounds(115,480,120,30);
b1.setBackground(Color.white);
b1.setForeground(Color.black);

b2=new JButton("Reset");
b2.setBounds(255,480,120,30);
b2.setBackground(Color.white);
b2.setForeground(Color.black);

b3=new JButton("Back");
b3.setBounds(395,480,120,30);
b3.setBackground(Color.white);
b3.setForeground(Color.black);

cp.add(l1);
cp.add(tf1);
cp.add(l2);
cp.add(tf2);
cp.add(l3);
cp.add(tf3);

cp.add(b1);
cp.add(b2);
cp.add(b3);
cp.add(l4);

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
String s3=tf3.getText();

try
{

if(s1.equals("") && s2.equals("") && s3.equals(""))
{
JOptionPane.showMessageDialog(this,"ERROR!!!!\nDetails Cannot be NULL.\nPlease Enter All the Details.","Error",JOptionPane.ERROR_MESSAGE);
}

else if(s1.equals("") && s2.equals(""))
{
JOptionPane.showMessageDialog(this,"ERROR!!!!\nUser-name and password Cannot be null\nPlease Enter the User-name and Password","Error",JOptionPane.ERROR_MESSAGE);
}

else if(s1.length()>0 && s2.length()>0 && s3.length()>0 && s2.equals(s3))
{
s.execute("insert into User values('"+s1+"','"+s2+"')");
System.out.println("Values Inserted in the Table");
JOptionPane.showMessageDialog(this,"Your Account is Created SuccessFully","Done",JOptionPane.INFORMATION_MESSAGE);
tf1.setText("");
tf2.setText("");
tf3.setText("");
}

else if(s1.equals(""))
{
JOptionPane.showMessageDialog(this,"ERROR!!!!\nUser-name Cannot be null\nPlease Enter the User-name","Error",JOptionPane.ERROR_MESSAGE);
}

else if(s2.equals(""))
{
JOptionPane.showMessageDialog(this,"ERROR!!!!\nPassword Cannot be null\nPlease Enter the Password","Error",JOptionPane.ERROR_MESSAGE);
}

else if(s3.equals(""))
{
JOptionPane.showMessageDialog(this,"ERROR!!!!\nPassword Cannot be null\nPlease Re-Enter the Password","Error",JOptionPane.ERROR_MESSAGE);
}

else if(s2!=s3)
{
JOptionPane.showMessageDialog(this,"ERROR!!!!\nPassword entered does not match!\nPlease Enter the Same Password in both the Fields","Error",JOptionPane.ERROR_MESSAGE);
}
else{}
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

public class AddUserDetails1
{
public static void main(String args[])
{
JFrame.setDefaultLookAndFeelDecorated(true);
JDialog.setDefaultLookAndFeelDecorated(true);

JFrame ff=new AddUserDetails();
ff.setTitle("New User Account");
ff.setSize(600,600);
ff.setLocation(100,100);
ff.setVisible(true);
ff.setResizable(false);
ff.setCursor(JFrame.HAND_CURSOR);
}
}