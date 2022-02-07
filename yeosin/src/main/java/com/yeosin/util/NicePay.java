package com.yeosin.util;


import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Hex;

public class NicePay {
   public void getEncData() {
	   /*
	   *******************************************************
	   * <결제요청 파라미터>
	   * 결제시 Form 에 보내는 결제요청 파라미터입니다.
	   * 샘플페이지에서는 기본(필수) 파라미터만 예시되어 있으며, 
	   * 추가 가능한 옵션 파라미터는 연동메뉴얼을 참고하세요.
	   *******************************************************
	   */
	   String merchantKey 		= "1q8Rl7lwsYz1YaneFJ/mUIwNgh9y/12OcHoMVtR0CqnVnUf5WAPGxF95+jOo29PhSl1RGjSxnzhRB3xvmFEK7w=="; // 상점키
	   String merchantID 		= "kmama0001m"; 				// 상점아이디
	   String goodsName 		= "나이스페이"; 					// 결제상품명
	   String price 			= "1004"; 						// 결제상품금액	
	   String buyerName 		= "나이스"; 						// 구매자명
	   String buyerTel 			= "01000000000"; 				// 구매자연락처
	   String buyerEmail 		= "happy@day.co.kr"; 			// 구매자메일주소
	   String moid 				= "mnoid1234567890"; 			// 상품주문번호	
	   String returnURL 		= "http://127.0.0.1/www/apply/pay/payResult_utf.jsp"; // 결과페이지(절대경로) - 모바일 결제창 전용

	   /*
	   *******************************************************
	   * <해쉬암호화> (수정하지 마세요)
	   * SHA-256 해쉬암호화는 거래 위변조를 막기위한 방법입니다. 
	   *******************************************************
	   */

	   String ediDate 			= getyyyyMMddHHmmss();	
	   String hashString 		= this.encrypt(ediDate + merchantID + price + merchantKey);
   }
   
   	MessageDigest md;
	String strSRCData = "";
	String strENCData = "";
	String strOUTData = "";
	
	public final synchronized String getyyyyMMddHHmmss(){
		SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
		return yyyyMMddHHmmss.format(new Date());
	}

	public String encrypt(String strData){
		String passACL = null;
		MessageDigest md = null;
		try{
			md = MessageDigest.getInstance("SHA-256");
			md.reset();
			md.update(strData.getBytes());
			byte[] raw = md.digest();
			passACL = encodeHex(raw);
		}catch(Exception e){
			System.out.print("암호화 에러" + e.toString());
		}
		return passACL;
	}
	
	public String encodeHex(byte [] b){
		char [] c = Hex.encodeHex(b);
		return new String(c);
	}
}