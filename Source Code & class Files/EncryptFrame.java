import java.io.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.security.Key;

public class EncryptFrame extends JFrame implements ActionListener
{
JLabel l1,l2;
JTextField t1;
JButton b1,b2,b3,b4,b5;
DES des;

public EncryptFrame(String t)
{
super(t);
Container cp=getContentPane();
cp.setLayout(null);
cp.setBackground(new Color(250,230,142));

des=new DES();

l1=new JLabel("Select File to be Encrypted");
l1.setBounds(180,10,250,30);

l2=new JLabel("Select File Path:");
l2.setBounds(20,60,100,30);

t1=new JTextField("",30);
t1.setBounds(120,60,300,25);
t1.setEditable(false);

b1=new JButton("Browse");
b1.setBounds(430,60,100,25);
b1.setBackground(Color.white);
b1.setForeground(Color.black);

b2=new JButton("Encrypt");
b2.setBounds(105,100,80,30);
b2.setBackground(Color.white);
b2.setForeground(Color.black);

b3=new JButton("Back");
b3.setBounds(195,100,80,30);
b3.setBackground(Color.white);
b3.setForeground(Color.black);

b4=new JButton("Exit");
b4.setBounds(375,100,80,30);
b4.setBackground(Color.white);
b4.setForeground(Color.black);

b5=new JButton("Clear");
b5.setBounds(285,100,80,30);
b5.setBackground(Color.white);
b5.setForeground(Color.black);

cp.add(l1);
cp.add(l2);
cp.add(t1);
cp.add(b1);
cp.add(b2);
cp.add(b3);
cp.add(b4);
cp.add(b5);

b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
b4.addActionListener(this);
b5.addActionListener(this);

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
File file = getFileDialogOpen("*.*");
if (file==null)	
return;
t1.setText(file.getAbsolutePath());
}

if(e.getSource() == b2)
{
String s1=t1.getText();
if(s1.equals(""))
{
JOptionPane.showMessageDialog(null,"Select File Path","Error",JOptionPane.ERROR_MESSAGE);
}
else
{
File file = new File(t1.getText());
byte data[] = readByteFile(file);

//encrypt and save as new data and key as new files						
data = des.encrypt(data);
if (writeByteFile(file.getAbsolutePath() + ".enc",data) && writeObjectFile(file.getAbsolutePath() + ".key",des.getKey()))
{
//remove old file
file.delete();

JOptionPane.showMessageDialog(null,"Given File SuccessFully Encrypted","Done",JOptionPane.INFORMATION_MESSAGE);
				
JOptionPane.showMessageDialog(null,"File encrypted as: " + file.getName() + ".enc\n" +"Encryption key: " + file.getName() + ".key\n","Details",JOptionPane.INFORMATION_MESSAGE);	
t1.setText("");			
}
}			
}

if(e.getSource() == b3)
{
this.setVisible(false);
JFrame ff=new CryptoFrame("CRYPTOGRAPHY SYSTEM");
ff.setVisible(true);
ff.setSize(600,400);
ff.setResizable(false);
ff.setLocation(150,150);
ff.setCursor(JFrame.HAND_CURSOR);
}

if(e.getSource() == b4)
{
System.exit(0);
}

if(e.getSource() == b5)
{
t1.setText("");
}

}
protected File getFileDialogOpen(String filter)
{
FileDialog fd = new FileDialog(this,"Select File",FileDialog.LOAD);
fd.setFile(filter);
fd.setVisible(true);

if (fd.getFile() == null) 
return null;
File file = new File(fd.getDirectory()+fd.getFile());
		
if (!file.canRead())
{
JOptionPane.showMessageDialog(null,"Selected file cannot be read.","Error",JOptionPane.ERROR_MESSAGE);
return null;
}
return file;
}

protected byte[] readByteFile(File file)
{		
byte data[] = null;
try
{
FileInputStream fis = new FileInputStream(file);			
			
int c,i=0;
data = new byte[(int)file.length()];
while ((c = fis.read()) != -1)
data[i++] = (byte)c;
fis.close();
}
catch(IOException e)
{
JOptionPane.showMessageDialog(null,file.getName() + ":File not found or cannot be read.","Error",JOptionPane.ERROR_MESSAGE);
return null;
}				
return data;
}
	
protected boolean writeByteFile(String fileName, byte[] data)
{
File file = new File(fileName);
if (!file.canWrite())
{
try 
{
file.createNewFile();
}
catch(IOException e)
{
JOptionPane.showMessageDialog(null,"Unable to create file " + file.getName() + " for writing.","Error",JOptionPane.ERROR_MESSAGE);
return false;
}
}		
				
try
{			
FileOutputStream fos = new FileOutputStream(file);
fos.write(data);
fos.close();
return true;
}
catch(IOException e)
{
JOptionPane.showMessageDialog(null,"Unable to write to file " + file.getName(),"Error",JOptionPane.ERROR_MESSAGE);
return false;
}
}	

protected Object readObjectFile(File file)
{		
Object obj;
try
{
ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
obj = (Object)ois.readObject();
ois.close();			
}
catch(IOException e)
{
JOptionPane.showMessageDialog(null,file.getName() + " : File Not found or cannot be read.","Error",JOptionPane.ERROR_MESSAGE);
return null;
}
catch(ClassNotFoundException e)
{
JOptionPane.showMessageDialog(null,file.getName() + " Does not contain a readable object.","Error",JOptionPane.ERROR_MESSAGE);
return null;
}			
return obj;
}
	
protected boolean writeObjectFile(String fileName, Object data)
{
File file = new File(fileName);
if (!file.canWrite())
{
try
{
file.createNewFile();
}
			
catch(IOException e)
{
JOptionPane.showMessageDialog(null,"Unable to create file " + file.getName() + " for writing.","Error",JOptionPane.ERROR_MESSAGE);
return false;
}
}		
				
try
{
ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
oos.writeObject(data);
oos.close();
return true;
}
catch(IOException e)
{
JOptionPane.showMessageDialog(null,"Unable to write to file " + file.getName(),"Error",JOptionPane.ERROR_MESSAGE);
return false;
}	
}

public static void main(String args[])
{
JFrame.setDefaultLookAndFeelDecorated(true);
JDialog.setDefaultLookAndFeelDecorated(true);
JFrame ef=new EncryptFrame("ENCRYPTION");
ef.setVisible(true);
ef.setSize(580,250);
ef.setResizable(false);
ef.setLocation(150,150);
ef.setCursor(JFrame.HAND_CURSOR);
}
}