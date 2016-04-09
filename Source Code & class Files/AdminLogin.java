import java.io.*;
import java.awt.*;
import java.util.Date;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;

class AdminLogin1 extends JFrame implements ActionListener
{
JButton login,reset,exit,newacc,delacc,changepass;
JTextField t1;
JPasswordField t2;
JLabel l1,l2,img;
ImageIcon i1,i2,i3,i4,i5,i6,i7;
Font f1;
Statement s;
Connection c;
Date date;

public AdminLogin1()
{
Container cp=getContentPane();
cp.setLayout(null);
cp.setBackground(new Color(242,249,172));

date=new Date();

f1=new Font("Arial",Font.BOLD+Font.ITALIC,17);
cp.setFont(f1);

l1=new JLabel("User-Name:");
l1.setBounds(290,90,150,25);
l1.setFont(f1);

l2=new JLabel("Password:");
l2.setBounds(290,150,150,25);
l2.setFont(f1);

t1=new JTextField(30);
t1.setBounds(400,90,200,25);
t1.setForeground(Color.BLACK);

t2=new JPasswordField(30);
t2.setBounds(400,150,200,25);
t2.setForeground(Color.BLACK);

i1=new ImageIcon("images/refresh.gif");
reset=new JButton("RESET",i1);
reset.setBounds(180,210,200,35);
reset.setForeground(Color.white);
reset.setBackground(Color.black);

i2=new ImageIcon("images/switch.gif");
login=new JButton("LOGIN",i2);
login.setBounds(400,210,200,35);
login.setForeground(Color.white);
login.setBackground(Color.black);

i3=new ImageIcon("images/Update.gif");
exit=new JButton("EXIT",i3);
exit.setBounds(620,210,200,35);
exit.setForeground(Color.white);
exit.setBackground(Color.black);

i4=new ImageIcon("images/users.gif");
newacc=new JButton("CREATE ACCOUNT",i4);
newacc.setBounds(180,270,200,35);
newacc.setForeground(Color.white);
newacc.setBackground(Color.black);

i5=new ImageIcon("images/users.gif");
delacc=new JButton("DELETE ACCOUNT",i5);
delacc.setBounds(400,270,200,35);
delacc.setForeground(Color.white);
delacc.setBackground(Color.black);

i6=new ImageIcon("images/edit.gif");
changepass=new JButton("CHANGE PASSWORD",i6);
changepass.setBounds(620,270,200,35);
changepass.setForeground(Color.white);
changepass.setBackground(Color.black);


i7= new ImageIcon("images/img.jpg");
img = new JLabel(i7);
img.setBounds(310,330,380,304);

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

c=DriverManager.getConnection("jdbc:odbc:newcrypto");
if(c!=null)
{
System.out.println("Got Connection with the Database");
s=c.createStatement();
}
}
catch(Exception e)
{
e.printStackTrace();
}

reset.addActionListener(this);
login.addActionListener(this);
exit.addActionListener(this);
newacc.addActionListener(this);
delacc.addActionListener(this);
changepass.addActionListener(this);

cp.add(l1);
cp.add(t1);
cp.add(l2);
cp.add(t2);
cp.add(reset);
cp.add(login);
cp.add(exit);
cp.add(newacc);
cp.add(delacc);
cp.add(changepass);
cp.add(img);

}
public void actionPerformed(ActionEvent e)
{

if(e.getSource() == reset)
{
t1.setText("");
t2.setText("");
}

if(e.getSource() == login)
{
String s1=t1.getText();
String s2=t2.getText();

try
{
ResultSet rs=s.executeQuery("Select * from User where username ='"+s1+ "' and password ='"+s2+"'");

if(rs.next())
{
JOptionPane.showMessageDialog(this,"Correct User-Name and Password.","Done",JOptionPane.INFORMATION_MESSAGE);

SampleDialog1 d1=new SampleDialog1(this,"Login Result",true);

d1.setVisible(true);

this.setVisible(false);

JOptionPane.showMessageDialog(this,"Hi, " +s1+ "\nWelcome to Data Cryptography System.\nYou Logged in at: " +date.toString()+ "\n","Done",JOptionPane.INFORMATION_MESSAGE);
}

else if(s1.equals("") && s2.equals("")) 
{
SampleDialog2 d2=new SampleDialog2(this,"Login Result",true);

d2.setVisible(true);

JOptionPane.showMessageDialog(this,"Error!!!!!\nYou had not Entered the User-name and Password\nPlease enter the User-name and Password","Try-Again",JOptionPane.ERROR_MESSAGE);
}

else if(s1.equals("")) 
{
SampleDialog2 d2=new SampleDialog2(this,"Login Result",true);

d2.setVisible(true);

JOptionPane.showMessageDialog(this,"Error!!!!!!!!\nYou had not Entered the User-Name\nPlease enter the User-name","Try-Again",JOptionPane.ERROR_MESSAGE);
}

else if(s2.equals("")) 
{
SampleDialog2 d2=new SampleDialog2(this,"Login Result",true);

d2.setVisible(true);

JOptionPane.showMessageDialog(this,"Error!!!!!!!\nYou had not Entered the Password\nPlease enter the Password","Try-Again",JOptionPane.ERROR_MESSAGE);			
}

else
{
SampleDialog2 d2=new SampleDialog2(this,"Login Result",true);

d2.setVisible(true);

JOptionPane.showMessageDialog(this,"Error!!!!!!!\nYou Had Entered Invalid User-name or password.\nPlease enter the valid User-name or Password again.","Try-again",JOptionPane.ERROR_MESSAGE);
}
}
catch(Exception e1)
{
e1.printStackTrace();
}
}

if(e.getSource() == newacc)
{
JFrame fn=new AddUserDetails();
fn.setTitle("New User Account");
fn.setSize(600,600);
fn.setLocation(100,100);
fn.setVisible(true);
fn.setResizable(false);
fn.setCursor(JFrame.HAND_CURSOR);

this.setVisible(false);
}

if(e.getSource() == delacc)
{
JFrame fde=new DeleteUserDetails();
fde.setTitle("Delete User Account");
fde.setSize(600,600);
fde.setLocation(100,100);
fde.setVisible(true);
fde.setResizable(false);
fde.setCursor(JFrame.HAND_CURSOR);

this.setVisible(false);
}

if(e.getSource() == changepass)
{
JFrame ffca=new ChangeUserDetails();
ffca.setTitle("Set Password of User");
ffca.setSize(650,500);
ffca.setLocation(100,100);
ffca.setVisible(true);
ffca.setResizable(false);
ffca.setCursor(JFrame.HAND_CURSOR);

this.setVisible(false);
}

if(e.getSource() == exit)
{
int result=JOptionPane.showConfirmDialog(this, "Do you really want to quit ?", "Close Application", JOptionPane.YES_NO_OPTION);
if(result== JOptionPane.YES_OPTION)
{
try
{
c.close();
System.out.println("CONNECTION TO THE DATABASE IS CLOSED");
System.exit(0);
}
catch(Exception ee)
{
System.out.println("ERROR IN CLOSING THE CONNECTION" +ee.getMessage());
}
}
}

}// end of public void actionPerformed() method

}//end of class AdminLogin1

class SampleDialog1 extends JDialog implements ActionListener
{
JButton b1;
SampleDialog1(Frame p,String t,boolean m)
{
super(p,t,m);
setLayout(new FlowLayout());
setSize(300,120);
setLocation(300,300);

ImageIcon i1=new ImageIcon("images/category.gif");

JLabel l3=new JLabel("ACCESS TO THE SYSTEM IS GRANTED");
add(l3);

b1=new JButton("O.K",i1);
add(b1);

b1.addActionListener(this);
}

public void actionPerformed(ActionEvent e)
{
if(e.getSource() == b1)
{
this.setVisible(false);
JFrame ff=new CryptoFrame("CRYPTOGRAPHY SYSTEM");
ff.setVisible(true);
ff.setSize(600,400);
ff.setLocation(195,175);
ff.setCursor(JFrame.HAND_CURSOR);
ff.setResizable(false);
}
}
}

class SampleDialog2 extends JDialog implements ActionListener
{
JButton b2;
SampleDialog2(Frame p,String t,boolean m)
{
super(p,t,m);
setLayout(new FlowLayout());
setSize(300,120);
setLocation(300,300);

ImageIcon i2=new ImageIcon("images/cancel1.gif");

JLabel l4=new JLabel("ACCESS TO THE SYSTEM IS DENIED");
add(l4);

b2=new JButton("O.K",i2);
add(b2);

b2.addActionListener(this);
}
public void actionPerformed(ActionEvent e)
{
if(e.getSource() == b2)
{
dispose();
}
}
}

public class AdminLogin
{
public static void main(String args[])
{

JDialog.setDefaultLookAndFeelDecorated(true);
JFrame.setDefaultLookAndFeelDecorated(true);

JFrame f = new AdminLogin1();
f.setSize(850,700);
f.setVisible(true);
f.setCursor(JFrame.HAND_CURSOR);
f.setTitle("Login Screen For Data Cryptography System");
f.setResizable(false);
}
}