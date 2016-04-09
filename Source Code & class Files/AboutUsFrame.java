import java.io.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.security.Key;

public class AboutUsFrame extends JFrame implements ActionListener
{
JButton b1;
Font f1;
public AboutUsFrame(String t)
{
super(t);
Container cp=getContentPane();
cp.setLayout(null);

f1=new Font("Century SchoolBook",Font.BOLD+Font.ITALIC,15);
cp.setFont(f1);

JLabel l1=new JLabel("This Project is made by:");
l1.setBounds(10,10,250,30);
l1.setFont(f1);

JLabel l2=new JLabel("Group A");
l2.setBounds(10,50,150,30);
l2.setFont(f1);

JLabel l3=new JLabel("Reference: http://www.google.co.in//");
l3.setBounds(10,90,280,30);
l3.setFont(f1);

cp.setBackground(new Color(175,250,250));

b1=new JButton("Back");
b1.setBounds(105,132,100,30);
b1.setBackground(Color.white);
b1.setForeground(Color.black);

cp.add(l1);
cp.add(l2);
cp.add(l3);
cp.add(b1);

b1.addActionListener(this);

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
JFrame ff=new CryptoFrame("CRYPTOGRAPHY SYSTEM");
ff.setVisible(true);
ff.setSize(600,400);
ff.setResizable(false);
ff.setLocation(150,150);
ff.setCursor(JFrame.HAND_CURSOR);
}
}
public static void main(String args[])
{
JFrame.setDefaultLookAndFeelDecorated(true);
JFrame auf=new AboutUsFrame("ABOUT US");
auf.setVisible(true);
auf.setSize(320,200);
auf.setLocation(150,150);
auf.setResizable(false);
auf.setCursor(JFrame.HAND_CURSOR);
}
}