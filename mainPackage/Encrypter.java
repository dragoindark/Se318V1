//Faruk Burak Gürel@dragoindark
package mainPackage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypter {
	//This method hashes the given string with sha256 than it hashes the outcome string with md5, returns the final version of the hash
	public String shaWithMD5Encrypter(String target) {
		try {
			MessageDigest sha=MessageDigest.getInstance("SHA-256");
			MessageDigest md5=MessageDigest.getInstance("MD5");
			byte[] shaHash=sha.digest(target.getBytes());
			String shaOutput=this.hashToString(shaHash);
			md5.reset();
			byte[] mdHash=md5.digest(shaOutput.getBytes());
			return this.hashToString(mdHash);
			
			
		}catch(NoSuchAlgorithmException e) {
			System.out.println(e);
			return null;
		}
	}
	//this method returns the string of hash byte after changing the offput of the bytes in memory for conversion
	public String hashToString(byte[] hashByte) {
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<hashByte.length;i++) {
			sb.append(Integer.toString((hashByte[i]& 0xff)+0x100,16).substring(1));
		}
		return sb.toString();
	}
}
