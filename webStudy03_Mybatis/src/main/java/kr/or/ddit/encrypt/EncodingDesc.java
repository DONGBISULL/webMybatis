package kr.or.ddit.encrypt;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

/**
 * encoding(부호화) /decoding(복호화) : 데이터의 전송이나 저장시에 해당매체가 인식할 수 있는 데이터 표현 방식으로 표현을 변화시키는 과정
 *	  URL encoding(Percent encoding) Base64 --> 영어 대소문자 + 숫자 10개 / 
 * encrypting(암호화) : 허가되지 않은 사용자로부터 데이터를 보호할 목적으로 변환하는 과정
 *
 */
public class EncodingDesc {

	public static void main(String[] args) {
		String plain = "집에 너무 가고 싶고요 복습할 시간이 없어요 ...";
		try {
			String encoded = URLEncoder.encode(plain, "utf-8");
			System.out.println(encoded);
			
			String decoded  = URLDecoder.decode(encoded ,"utf-8");
			System.out.println(decoded);
			String resPath = "D:/contents/Koala.jpg";
			File file = new File(resPath);
			try(
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();//배열 관리 가능한 스트림 
					){
				byte[] buffer = new byte[1024];
				int pointer = -1 ;
				while((pointer=fis.read())!=-1) {
					baos.write(buffer, 0, pointer); //메모리에 복사
				}
				
				byte[] readData=	baos.toByteArray(); //읽어들인 데이터(이미지)
				System.out.println(readData.length);
				
				encoded = 	Base64.getEncoder().encodeToString(readData);
				System.out.println(encoded);
				byte[] decodedArray = 	Base64.getDecoder().decode(encoded);
				System.out.println(decodedArray);
			
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
