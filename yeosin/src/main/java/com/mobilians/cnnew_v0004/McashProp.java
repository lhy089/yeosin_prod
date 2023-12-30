package com.mobilians.cnnew_v0004;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

public class McashProp {
   public static Properties msgProp = new Properties();

   static {
      try {
         Properties msgp = new Properties();
         InputStream is = McashProp.class.getResourceAsStream("MessageMap.prop");
         msgp.load(is);
         System.out.println(msgp);
         Enumeration en = msgp.keys();
         String key = "";
         String value = "";

         while(en.hasMoreElements()) {
            key = (String)en.nextElement();
            value = new String(msgp.getProperty(key).getBytes("8859_1"));
            msgProp.setProperty(key, value);
         }

         is.close();
      } catch (Exception var5) {
         System.out.println(" server msg load error " + var5);
      }

   }

   public static String getEngMsg(String cd, String msg) {
      try {
         String srcMsg = msgProp.getProperty(cd, msg);
         String[] sv = srcMsg.split("^");
         String matchStr = msg.replaceAll("TEST|\\s|\\.|\\d", "");
         if ("0039".equals(cd) || "0043".equals(cd)) {
            matchStr = matchStr.replaceAll("\\d", "").replaceAll("유효시간", "1").replaceAll("반복적", "2").replaceAll("안전한", "3").replaceAll("승인번호", "4").replaceAll("승인내역", "5").replaceAll("비밀번호", "6").replaceAll("[^\\d]", "").replaceAll("1", "유효시간").replaceAll("2", "반복적").replaceAll("3", "안전한").replaceAll("4", "승인번호").replaceAll("5", "승인내역").replaceAll("6", "비밀번호");
         }

         for(int i = 0; i < sv.length; ++i) {
            if (matchStr.indexOf(sv[i].substring(sv[i].indexOf("|") + 1).replaceAll("TEST|\\s|\\.", "")) > 0) {
               return sv[i].substring(0, sv[i].indexOf("|"));
            }
         }

         return sv[0].substring(0, sv[0].indexOf("|"));
      } catch (Exception var6) {
         System.out.println(var6);
         return msg;
      }
   }
}
    