import java.io.*;
import java.awt.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.security.Key;

public class DecryptFrame extends JFrame implements ActionListener
{
JLabel l1,l2,l3;
TextField t1,t2;
JButton b1,b2,b3,b4,b5,b6;
DES des;

public DecryptFrame(String t)
{
super(t);
Container cp=getContentPane();
cp.setLayout(null);
cp.setBackground(new Color(235,210,230));

des=new DES();

l1=new JLabel("Select File to be Decrypted and Decryption Key");
l1.setBounds(230,10,350,30);

l2=new JLabel("Select Encrypted File(.enc) Path:");
l2.setBounds(15,60,190,30);

t1=new TextField("",30);
t1.setBounds(215,60,300,25);
t1.setEditable(false);

b1=new JButton("Browse");
b1.setBounds(520,60,100,25);
b1.setBackground(Color.white);
b1.setForeground(Color.black);

l3=new JLabel("Select Decryption key(.key) Path:");
l3.setBounds(15,100,190,30);

t2=new TextField("",30);
t2.setBounds(215,100,300,25);
t2.setEditable(false);

b2=new JButton("Browse");
b2.setBounds(520,100,100,25);
b2.setBackground(Color.white);
b2.setForeground(Color.black);

b3=new JButton("Decrypt");
b3.setBounds(200,150,80,30);
b3.setBackground(Color.white);
b3.setForeground(Color.black);

b4=new JButton("Back");
b4.setBounds(290,150,80,30);
b4.setBackground(Color.white);
b4.setForeground(Color.black);

b5=new JButton("Exit");
b5.setBounds(470,150,80,30);
b5.setBackground(Color.white);
b5.setForeground(Color.black);

b6=new JButton("Clear");
b6.setBounds(380,150,80,30);
b6.setBackground(Color.white);
b6.setForeground(Color.black);

cp.add(l1);
cp.add(l2);
cp.add(l3);
cp.add(t1);
cp.add(t2);
cp.add(b1);
cp.add(b2);
cp.add(b3);
cp.add(b4);
cp.add(b5);
cp.add(b6);

b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
b4.addActionListener(this);
b5.addActionListener(this);
b6.addActionListener(this);

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
File file = getFileDialogOpen("*.*");
if (file==null)	
return;
t2.setText(file.getAbsolutePath());
}

if(e.getSource() == b3)
{
String s1=t1.getText();
String s2=t2.getText();

if(s1.equals("") || s2.equals(""))
{
JOptionPane.showMessageDialog(null,"Encrypted file or Key file not Selected.\nPlease Select Encrypted file(.enc) or Key file(.key) only.","Error",JOptionPane.ERROR_MESSAGE);
}

else
{
File file = new File(t1.getText());
File keyFile = new File(t2.getText());
			
//get encrypted file and key
if (!file.exists())
{
JOptionPane.showMessageDialog(null,"Encrypted file not found or cannot be accessed.","Error",JOptionPane.ERROR_MESSAGE);
return;
}
if (!keyFile.exists())
{
JOptionPane.showMessageDialog(null,"Key file not found or cannot be accessed.","Error",JOptionPane.ERROR_MESSAGE);
return;
}
			
//use key to decrypt data
byte data[] = readByteFile(file);
Key key = (Key)readObjectFile(keyFile);
data = des.decrypt(data,key);

//restore original file and remove encrypted file and key
String filename = file.getAbsolutePath().
substring(0,file.getAbsolutePath().length()-3);
if (writeByteFile(filename,data))
{
file.delete();
keyFile.delete();
JOptionPane.showMessageDialog(null,"File sucessfully decrypted.","Done",JOptionPane.INFORMATION_MESSAGE);	
t1.setText("");
t2.setText("");
}		
}
}

if(e.getSource() == b4)
{
this.setVisible(false);
JFrame ff=new CryptoFrame("CRYPTOGRAPHY SYSTEM");
ff.setVisible(true);
ff.setSize(600,400);
ff.setResizable(false);
ff.setLocation(150,150);
ff.setCursor(JFrame.HAND_CURSOR);
}

if(e.getSource() == b5)
{
System.exit(0);
}

if(e.getSource() == b6)
{
t1.setText("");
t2.setText("");
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
JOptionPane.showMessageDialog(null,file.getName() + ":File Not found or cannot be read.","Error",JOptionPane.ERROR_MESSAGE);
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
JOptionPane.showMessageDialog(null,file.getName() + " :File Not found or cannot be read.","Error",JOptionPane.ERROR_MESSAGE);
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

JFrame df=new DecryptFrame("DECRYPTION");
df.setVisible(true);
df.setSize(650,300);
df.setResizable(false);
df.setLocation(150,150);
df.setCursor(JFrame.HAND_CURSOR);
}
}