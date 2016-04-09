import javax.crypto.*;
import java.security.*;

/*
Performs encryption and decryption DES algorithm
 */
public class DES
{
	
/** 	Stores a generated DES key */
private Key key;
	
/**
 *	Gets the DES Key used for the encryption
 *	and return DES Key generated, or null if none generated
*/

public Key getKey()
{
return key;
}
	
/**
 *	Performs encryption using DES algorithm
 *	and takes parameter data i.e The data to be encrypted
 *	and return The encrypted data, or null if enryption fails
 */
public byte[] encrypt(byte[] data)
{
key = null;
try
{
Security.addProvider(new com.sun.crypto.provider.SunJCE());
KeyGenerator kg = KeyGenerator.getInstance("DES");
Cipher cipher = Cipher.getInstance("DES");

key = kg.generateKey();
cipher.init(Cipher.ENCRYPT_MODE, key);
return (cipher.doFinal(data));
}
catch(Exception e)
{
return null;
}
}
	
/**
*	Performs decryption using DES algorithm and a Key
*	takes parameter as data i.e The data to be decrypted
*	and takes another parameter key i.e The key to be used for decryption
*	and the returns The decrypted data, or null if decryption fails
*/
	
public byte[] decrypt(byte[] data, Key key)
{
try
{
Security.addProvider(new com.sun.crypto.provider.SunJCE());
KeyGenerator kg = KeyGenerator.getInstance("DES");
Cipher cipher = Cipher.getInstance("DES");

cipher.init(Cipher.DECRYPT_MODE, key);
return(cipher.doFinal(data));
}
catch(Exception e)
{
return null;
}
}		
}
 
