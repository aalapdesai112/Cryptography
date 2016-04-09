import java.io.*;
import java.awt.*;
import java.util.Date;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.security.Key;

public class CryptoFrame extends JFrame implements ActionListener
{
JButton b1,b2,b3,b4;
JLabel l1,l2;
Font f1,f2;
Date date;

public CryptoFrame(String t)
{
super(t);
Container cp=getContentPane();
cp.setLayout(null);
cp.setBackground(new Color(186,237,203));
f1=new Font("Arial",Font.BOLD+Font.ITALIC,30);
f2=new Font("Century SchoolBook",Font.BOLD,25);

date=new Date();

l1=new JLabel("DES File Encryption and Decryption");
l1.setBounds( 30,80,550,50);
l1.setFont(f1);
l1.setForeground(new Color(14,73,88));

l2=new JLabel("Select your Choice");
l2.setBounds(150,150,250,30);
l2.setFont(f2);
l2.setForeground(new Color(14,73,88));

b1=new JButton("Encryption");
b1.setBounds(100,200,150,50);
b1.setBackground(Color.white);
b1.setForeground(Color.black);

b2=new JButton("Decryption");
b2.setBounds(270,200,150,50);
b2.setBackground(Color.white);
b2.setForeground(Color.black);

b3=new JButton("About Us");
b3.setBounds(5,5,100,30);
b3.setBackground(Color.white);
b3.setForeground(Color.black);

b4=new JButton("Log-Out");
b4.setBounds(487,5,100,30);
b4.setBackground(Color.white);
b4.setForeground(Color.black);

cp.add(l1);
cp.add(l2);
cp.add(b1);
cp.add(b2);
cp.add(b3);
cp.add(b4);


b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
b4.addActionListener(this);

addWindowListener(new WindowAdapter()
{
public void windowClosing(WindowEvent we)
{
System.exit(0);
}
});
}
public void actionPerformed(ActionEvent e)
{
if(e.getSource() == b1)
{
this.setVisible(false);
JFrame ef=new EncryptFrame("ENCRYPTION");
ef.setVisible(true);
ef.setSize(580,250);
ef.setLocation(150,150);
ef.setResizable(false);
ef.setCursor(JFrame.HAND_CURSOR);
}

if(e.getSource() == b2)
{
this.setVisible(false);
JFrame df=new DecryptFrame("DECRYPTION");
df.setVisible(true);
df.setSize(650,300);
df.setLocation(150,150);
df.setResizable(false);
df.setCursor(JFrame.HAND_CURSOR);
}
if(e.getSource() == b3)
{
this.setVisible(false);
JFrame auf=new AboutUsFrame("ABOUT US");
auf.setVisible(true);
auf.setSize(320,200);
auf.setLocation(150,150);
auf.setLocation(150,150);
auf.setResizable(false);
auf.setCursor(JFrame.HAND_CURSOR);
}

if(e.getSource() ==  b4)
{
JOptionPane.showMessageDialog(this,"Bye!!!!! \nYou are Logging out at: " +date.toString()+"\n","Done",JOptionPane.INFORMATION_MESSAGE);

this.setVisible(false);
JFrame f = new AdminLogin1();
f.setSize(850,700);
f.setVisible(true);
f.setCursor(JFrame.HAND_CURSOR);
f.setTitle("Login Screen For Data Cryptography System");
f.setResizable(false);
}

}
public static void main(String args[])
{
JFrame.setDefaultLookAndFeelDecorated(true);
JDialog.setDefaultLookAndFeelDecorated(true);
JFrame ff=new CryptoFrame("CRYPTOGRAPHY SYSTEM");
ff.setVisible(true);
ff.setSize(600,400);
ff.setLocation(150,150);
ff.setCursor(JFrame.HAND_CURSOR);
ff.setResizable(false);
}
}