package com.mobilians.cnnew_v0004;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.Properties;

public class FileMaker {
   private String propertiesName = "Mcash.properties";
   private FileInputStream fis;
   private OutputStreamWriter osw;
   private String logLevel = "";
   private String serverIp = "";
   private String switchIp = "";
   private int serverPort = 0;
   private int recvTimeOut;
   private String fileDir = "";
   private String fileName = "";
   private String resultcd = "";
   private String resultmsg = "";
   private String Ver = "";
   private String Key1 = "";
   private String Key2 = "";
   private String CryptGB = "";
   private String KeySeq = "";
   private String CipherKey = "";
   private String UserEncode = "";
   private boolean isManualConfig = false;

   public FileMaker(String ver, String serverIp, String switchIp, int serverPort, int recvTimeOut, String logDir, String KeySeq, String Key, String UserEncode, String logLevel) {
      this.isManualConfig = true;
      this.Ver = ver;
      this.serverIp = serverIp;
      this.switchIp = switchIp;
      this.serverPort = serverPort;
      this.recvTimeOut = recvTimeOut;
      this.fileDir = logDir;
      this.fileName = this.fileDir + this.makeFileName();
      this.UserEncode = UserEncode;
      if (this.UserEncode == null || "".equals(this.UserEncode)) {
         this.UserEncode = "EUC-KR";
      }

      this.makeFile();
      this.KeySeq = KeySeq;
      if (logLevel == null || logLevel.equals("")) {
         this.logLevel = "2";
      }

      if (this.fileName.equals("")) {
         this.resultcd = "9002";
         this.resultmsg = "[9002]시스템 설정 오류입니다. LogFile 명칭설정 오류";
         System.out.println("[MCASH] Error : LogFile 설정 오류 - [" + this.fileName + "]");
      } else if (this.serverIp.equals("")) {
         this.resultcd = "9003";
         this.resultmsg = "[9003]시스템 설정 오류입니다.";
         System.out.println("[MCASH] Error : SERVER_IP 설정 오류 - [" + this.serverIp + "]");
      } else if (this.switchIp.equals("")) {
         this.resultcd = "9003";
         this.resultmsg = "[9003]시스템 설정 오류입니다. SWITCH_IP 설정 오류";
         System.out.println("[MCASH] Error : SWITCH_IP 설정 오류 - [" + this.switchIp + "]");
      } else if (this.serverPort == 0) {
         this.resultcd = "9003";
         this.resultmsg = "[9003]시스템 설정 오류입니다. SERVER_PORT 설정 오류";
         System.out.println("[MCASH] Error : SERVER_PORT 설정 오류 - [" + this.serverPort + "]");
      } else if (this.Ver.length() != 4) {
         this.resultcd = "9004";
         this.resultmsg = "[9004]시스템 설정 오류입니다.";
         System.out.println("[MCASH] Error : VER 설정 오류 - [" + this.Ver + "]");
      } else if (!this.KeySeq.equals("0") && !this.KeySeq.equals("1") && !this.KeySeq.equals("2")) {
         this.resultcd = "9005";
         this.resultmsg = "[9005]시스템 설정 오류입니다.";
         System.out.println("[MCASH] Error : CURKEY 설정 오류 - [" + this.KeySeq + "]");
      } else {
         if (this.KeySeq.equals("1")) {
            this.Key1 = Key;
            if (this.Key1.equals("") || this.Key1.length() != 8) {
               this.resultcd = "9005";
               this.resultmsg = "[9005]시스템 설정 오류입니다.";
               System.out.println("[MCASH] Error : KEY1 설정 오류 - [" + this.Key1 + "]");
            }
         } else if (this.KeySeq.equals("2")) {
            this.Key2 = Key;
            if (this.Key2.equals("") || this.Key2.length() != 8) {
               this.resultcd = "9005";
               this.resultmsg = "[9005]시스템 설정 오류입니다.";
               System.out.println("[MCASH] Error : KEY2 설정 오류" + this.Key2 + "]");
               return;
            }
         }

      }
   }

   public FileMaker() {
      if (!this.isManualConfig) {
         this.propertiesLoad();
      }

      if (this.resultcd.equals("0000")) {
         this.makeFile();
      }

   }

   public FileMaker(String configDirPath, String logDir) {
      if (logDir != null && !"".equals(logDir)) {
         this.fileDir = logDir;
         this.fileName = this.fileDir + this.makeFileName();
         this.resultcd = "0000";
      } else if (!this.isManualConfig) {
         this.propertiesLoad(configDirPath);
      }

      if (this.resultcd.equals("0000")) {
         this.makeFile();
      }

   }

   public String makeFile() {
      try {
         if (!"".equals(this.fileDir)) {
            File f = new File(this.fileDir);
            if (!f.exists()) {
               f.mkdir();
            }

            this.osw = new OutputStreamWriter(new FileOutputStream(this.fileName, true));
         }

         this.resultcd = "0000";
         return "0000";
      } catch (IOException var2) {
         System.err.println("[MCASH] Error : 디렉토리, 파일 생성 오류 - [" + this.fileName + "]" + var2.toString());
         this.resultcd = "9002";
         this.resultmsg = "[9002]시스템 설정 오류입니다. 파일 생성 오류. 디렉토리 유무 또는  디렉토리 권한 오류";
         return "9002";
      }
   }

   public void propertiesLoad() {
      this.propertiesLoad((String)null);
   }

   public void propertiesLoad(String configDirPath) {
      InputStream is = null;
      Properties dbProps = new Properties();

      try {
         if (configDirPath != null && !"".equals(configDirPath)) {
            try {
               is = new FileInputStream(new File(configDirPath));
               dbProps.load(is);
               is.close();
            } catch (Exception var7) {
            	System.out.println(var7.toString());
               this.resultcd = "9003";
               this.resultmsg = "[9003]시스템 설정 오류입니다.";
               System.out.println("[MCASH] Error : 환경 설정 파일 읽기 오류 - [" + configDirPath + "]");
               return;
            }
         } else {
            is = this.getClass().getClassLoader().getResourceAsStream(this.propertiesName);
            dbProps.load(is);
            is.close();
         }

         this.fileDir = dbProps.getProperty("LogFile", "");
         this.fileName = this.fileDir + this.makeFileName();
         this.serverIp = dbProps.getProperty("SERVER_IP", "");
         if (this.serverIp.equals("")) {
            this.resultcd = "9003";
            this.resultmsg = "[9003]시스템 설정 오류입니다.";
            System.out.println("[MCASH] Error : SERVER_IP 설정 오류 - [" + this.serverIp + "]");
            return;
         }

         this.switchIp = dbProps.getProperty("SWITCH_IP", "");
         if (this.switchIp.equals("")) {
            this.resultcd = "9003";
            this.resultmsg = "[9003]시스템 설정 오류입니다. SWITCH_IP 설정 오류";
            System.out.println("[MCASH] Error : SWITCH_IP 설정 오류 - [" + this.switchIp + "]");
            return;
         }

         try {
            this.serverPort = new Integer(dbProps.getProperty("SERVER_PORT", "0"));
         } catch (Exception var6) {
            this.serverPort = 0;
         }

         if (this.serverPort == 0) {
            this.resultcd = "9003";
            this.resultmsg = "[9003]시스템 설정 오류입니다. SERVER_PORT 설정 오류";
            System.out.println("[MCASH] Error : SERVER_PORT 설정 오류 - [" + this.serverPort + "]");
            return;
         }

         try {
            this.recvTimeOut = new Integer(dbProps.getProperty("RECV_TIMEOUT", "0"));
         } catch (Exception var5) {
            this.recvTimeOut = 0;
         }

         this.Ver = dbProps.getProperty("VER", "");
         if (this.Ver.length() != 4) {
            this.resultcd = "9004";
            this.resultmsg = "[9004]시스템 설정 오류입니다.";
            System.out.println("[MCASH] Error : VER 설정 오류 - [" + this.Ver + "]");
            return;
         }

         this.KeySeq = "0";
         if (!this.KeySeq.equals("0") && !this.KeySeq.equals("1") && !this.KeySeq.equals("2")) {
            this.resultcd = "9005";
            this.resultmsg = "[9005]시스템 설정 오류입니다.";
            System.out.println("[MCASH] Error : CURKEY 설정 오류 - [" + this.KeySeq + "]");
            return;
         }

         if (this.KeySeq.equals("1")) {
            this.Key1 = dbProps.getProperty("KEY1", "");
            if (this.Key1.equals("") || this.Key1.length() != 8) {
               this.resultcd = "9005";
               this.resultmsg = "[9005]시스템 설정 오류입니다.";
               System.out.println("[MCASH] Error : KEY1 설정 오류 - [" + this.Key1 + "]");
            }
         } else if (this.KeySeq.equals("2")) {
            this.Key2 = dbProps.getProperty("KEY2", "");
            if (this.Key2.equals("") || this.Key2.length() != 8) {
               this.resultcd = "9005";
               this.resultmsg = "[9005]시스템 설정 오류입니다.";
               System.out.println("[MCASH] Error : KEY2 설정 오류" + this.Key2 + "]");
            }
         } else {
            this.logLevel = dbProps.getProperty("logLevel", "2");
         }

         this.UserEncode = dbProps.getProperty("UserEncode", "");
         if (this.UserEncode.equals("")) {
            this.UserEncode = "EUC-KR";
         }
      } catch (Exception var8) {
         this.resultcd = "9001";
         this.resultmsg = "[9001]시스템설정 오류입니다.";
         System.out.println("[MCASH] Error : Mcash.properties 경로 설정 오류");
         var8.printStackTrace();
         return;
      }

      this.resultcd = "0000";
   }

   public String makeFileName() {
      Date dt = new Date();
      String year = String.valueOf(dt.getYear() + 1900);
      String month = String.valueOf(dt.getMonth() + 1);
      String day = String.valueOf(dt.getDate());
      if (dt.getMonth() < 9) {
         month = "0" + month;
      }

      if (dt.getDate() < 10) {
         day = "0" + day;
      }

      return "Mcash_" + year + month + day + ".txt";
   }

   public void write(String tmp) {
      try {
         if (!"".equals(this.fileDir)) {
            this.osw.write(tmp);
         }
      } catch (Exception var3) {
         System.err.println("[MCASH] Error : Can't write the log file");
      }

   }

   public void writeln(String tmp) {
      System.out.println("FileMaker class tmp" + tmp);
      tmp = this.dateStr() + " => " + tmp + "\n";

      try {
         if (!"".equals(this.fileDir)) {
            this.osw.write(tmp);
         }
      } catch (Exception var3) {
         System.err.println("[MCASH] Error : Can't write the log file");
      }

   }

   public void errorln(String tmp) {
      tmp = this.dateStr() + " => " + tmp + "\n";

      try {
         if (!"".equals(this.fileDir)) {
            this.osw.write(tmp);
         } else {
            System.out.println(tmp);
         }
      } catch (Exception var3) {
         System.err.println("[MCASH] Error : Can't write the log file");
      }

   }

   public String dateStr() {
      Date dt = new Date();
      String hour = String.valueOf(dt.getHours());
      String minute = String.valueOf(dt.getMinutes());
      String second = String.valueOf(dt.getSeconds());
      if (dt.getHours() < 10) {
         hour = "0" + hour;
      }

      if (dt.getMinutes() < 10) {
         minute = "0" + minute;
      }

      if (dt.getSeconds() < 10) {
         second = "0" + second;
      }

      return hour + ":" + minute + ":" + second;
   }

   public void close() {
      try {
         if (this.fis != null) {
            this.fis.close();
         }
      } catch (IOException var3) {
         System.err.println("[MCASH] Error : Can't close the log file");
      }

      try {
         if (this.osw != null) {
            this.osw.close();
         }
      } catch (IOException var2) {
         System.err.println("[MCASH] Error : Can't close the log file");
      }

   }

   public String getFileName() {
      return this.fileName;
   }

   public String getLogLevel() {
      return this.logLevel;
   }

   public String getServerIp() {
      return this.serverIp;
   }

   public String getswitchIp() {
      return this.switchIp;
   }

   public String getResultcd() {
      return this.resultcd;
   }

   public String getResultmsg() {
      return this.resultmsg;
   }

   public int getServerPort() {
      return this.serverPort;
   }

   public String getVer() {
      return this.Ver;
   }

   public String getCryptGB() {
      return this.CryptGB;
   }

   public String getKeySeq() {
      return this.KeySeq;
   }

   public String getKey1() {
      return this.Key1;
   }

   public String getKey2() {
      return this.Key2;
   }

   public String getCipherKey() {
      return this.CipherKey;
   }

   public void setCipherKey(String tmp) {
      this.CipherKey = tmp;
   }

   public String getUserEncode() {
      return this.UserEncode;
   }

   public void setUserEncode(String userEncode) {
      this.UserEncode = userEncode;
   }

   public int getRecvTimeOut() {
      return this.recvTimeOut;
   }
}