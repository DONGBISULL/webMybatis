package kr.or.ddit.encrypt;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * 단방향 암호화 (해시 함수): 평문 복호화가 불가능한 방식 
 * 		다양한 입력 데이터가 일정 갯수의 해시 문자로 만들어짐  ex) SHA-512  MessageDigest
 *  	512 의미 : 암호화 정도 64바이트
 * 양방향 암호화 : 평문 복호화 가능 Cipher
 *	- 대칭키 방식 : 하나의 비밀키에 의해 암복호화 수행(--> 하나의 키를 주고 받아야지 복호화 가능) ex) AES
 *	- 비대칭키 방식 : 한 쌍(개인키 , 공개키)의 키로 암호화와 복호화 수행  ex)RSA
 *
 */
/*
 * 암호화할 때 쓰는 키 
 * --> 똑같은 키로 해독해야 함 
	비밀키와 IVSPEC값을 보내줘야 함
 *
 */
public class EncryptingDesc { //encryting encoding 같이 사용
		public static void main(String[] args) throws NoSuchAlgorithmException {
			String plain = "java";
			//키가 한 쌍이 필요함
			String encoded = sha512Encrypting(plain);
			System.out.println(encoded);
		
		}
		
		public static String sha512Encrypting(String plain) {
			MessageDigest md;
			try {
				md = MessageDigest.getInstance("SHA-512");
				byte[ ] input = plain.getBytes();
				byte[] encrypted = 	md.digest(input);
				String encoded =	Base64.getEncoder().encodeToString(encrypted);
				//	System.out.println(encoded);
				return encoded ;
			} catch (NoSuchAlgorithmException e) {
						throw new RuntimeException(e);
			}
			//System.out.println(saved.equals(encoded));
		}
		
		
		public static void aesSample(String plain) {
			try {
				KeyGenerator keyGen = KeyGenerator.getInstance("AES");
				keyGen.init(128);
				SecretKey key = keyGen.generateKey();
				String ivText = "asdjasdas";
				MessageDigest md = MessageDigest.getInstance("md5");
				byte[] iv = md.digest(ivText.getBytes());
				
				IvParameterSpec ivSpec = new IvParameterSpec(iv);//항상 16바이트짜리 문자 필요
				
				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); //cbc --초기화 벡터가 있어야 함
				cipher.init(Cipher.ENCRYPT_MODE,key , ivSpec) ; //q암호화 시킬때 사용한 초기화 벡터 --> 복호화할 때도 똑같이 필요함 
				
				//aes --> block 암호화 사용
				//block으로 쪼개서 암호화(가벼움) ==> 22방식 
				
				byte[] input = plain.getBytes();
				byte[] encrypted =	cipher.doFinal(input); // 암호화
				
				String encoded =	Base64.getEncoder().encodeToString(encrypted); //인코딩
				System.out.println(encoded);
				
				//복호화 
				byte[] decoded = Base64.getDecoder().decode(encoded); // encrypted 랑 같아져야 함 
				
				cipher.init(Cipher.DECRYPT_MODE, key , ivSpec);
				byte[] decripted = cipher.doFinal(decoded); //plain 과 같아야 함
				System.out.println(new String(decripted));
			} catch (NoSuchPaddingException e) {
			 
				e.printStackTrace();
			} 	catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidAlgorithmParameterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
					//CBC 
					//임의로 만들어내는 block 초기화 block?? --> transfer() 체이닝할 것 
					//cipher block chaining 암호문을 가져감--> 계속 암호가 이어짐 복원하기 위해서 앞에 것들이 계속 필요해짐
			
		}
		
		
		public static void rsaSample(String plain) {
			try {
				KeyPairGenerator pairGen = KeyPairGenerator.getInstance("RSA");
				pairGen.initialize(2048);
				KeyPair pair = pairGen.generateKeyPair();
				PrivateKey privateKey = pair.getPrivate();
				PublicKey  publicKey = pair.getPublic();
				
				Cipher cipher = Cipher.getInstance("RSA");
				cipher.init(Cipher.ENCRYPT_MODE ,privateKey); //인증서 안에 내 개인키가 존재한다고 생각하면 됨 
				byte[] input = plain.getBytes();
				byte[] encrypted =  cipher.doFinal(input );
				
				String encoded = 	Base64.getEncoder().encodeToString(encrypted);
				System.out.println(encoded); // 속도가 AES보다 느림 --> 전자책 RSA 방식으로 하면 시간 더 걸림 
				
				byte[] decoded = Base64.getDecoder().decode(encoded);
				cipher.init(Cipher.DECRYPT_MODE, publicKey);//퍼블릭 키로 
				byte[] decrypted = cipher.doFinal(decoded);//input 이랑 똑같음 
				System.out.println(new String(decrypted));//plain
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
}
