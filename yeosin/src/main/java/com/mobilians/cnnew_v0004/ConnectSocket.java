package com.mobilians.cnnew_v0004;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;

public class ConnectSocket {
   private SocketAddress socketAddress;
   private Socket sk;
   private BufferedWriter bw;
   private OutputStreamWriter osw;
   private BufferedReader br;
   private InputStreamReader inr;
   private String result = "";
   private String resultcd = "";
   private String configPath = "Mcash.properties";

   public ConnectSocket(String configPath) {
      this.configPath = configPath;
   }

   public void Connect(String fmServerIP, String fmswitchIp, int fmServerPort, int recvTimeOut, String logDir) {
      try {
         FileMaker fm = new FileMaker(this.configPath, logDir);
         int count = 2;

         while(count > 0) {
            try {
               this.socketAddress = new InetSocketAddress(fmServerIP, fmServerPort);
               this.sk = new Socket();
               this.sk.connect(this.socketAddress, 6000);
               this.resultcd = "0000";
               fm.writeln("INFO : connect ok Serverip[" + fmServerIP + "] Port[" + fmServerPort + "]");
               fm.close();
               if (recvTimeOut > 0) {
                  this.sk.setSoTimeout(recvTimeOut);
               }

               return;
            } catch (Exception var11) {
               if (count == 1) {
                  try {
                     this.socketAddress = new InetSocketAddress(fmswitchIp, fmServerPort);
                     this.sk = new Socket();
                     this.sk.connect(this.socketAddress, 3000);
                     this.resultcd = "0000";
                     fm.writeln("INFO : connect ok Serverip[" + fmswitchIp + "] Port[" + fmServerPort + "]");
                     fm.close();
                     if (recvTimeOut > 0) {
                        this.sk.setSoTimeout(recvTimeOut);
                     }

                     return;
                  } catch (Exception var10) {
                     this.resultcd = "9902";
                  }
               }

               --count;
            }
         }
      } catch (Exception var12) {
         this.resultcd = "9902";
      }

   }

   public void setSocket(String tmp) {
      try {
         this.osw = new OutputStreamWriter(this.sk.getOutputStream());
         this.bw = new BufferedWriter(this.osw);
         this.bw.write(tmp);
         this.bw.newLine();
         this.bw.flush();
         this.resultcd = "0000";
      } catch (Exception var3) {
         this.resultcd = "9903";
      }

   }

   public void setSocket(String tmp, String EncodeMethod, String logDir) {
      try {
         FileMaker fm = new FileMaker(this.configPath, logDir);
         fm.writeln("DEBUG : SEND setSocket length[" + tmp.getBytes().length + "] PACKET[" + tmp.toString() + "]");
         this.osw = new OutputStreamWriter(this.sk.getOutputStream(), EncodeMethod);
         this.bw = new BufferedWriter(this.osw);
         this.bw.write(tmp);
         this.bw.newLine();
         this.bw.flush();
         this.resultcd = "0000";
         fm.close();
      } catch (Exception var5) {
         this.resultcd = "9903";
      }

   }

   public void getSocket(String EncodeMethod, String logDir) {
      try {
         this.inr = new InputStreamReader(this.sk.getInputStream(), EncodeMethod);
         this.br = new BufferedReader(this.inr);
         this.result = this.br.readLine();
         FileMaker fm = new FileMaker(this.configPath, logDir);
         fm.writeln("DEBUG : RECV getSocket length[" + this.result.getBytes().length + "] PACKET[" + this.result.toString() + "]");
         this.resultcd = "0000";
         fm.close();
      } catch (SocketTimeoutException var4) {
         this.resultcd = "9905";
      } catch (IOException var5) {
         this.resultcd = "9904";
      }

   }

   public void close() {
      try {
         if (this.osw != null) {
            this.osw.close();
         }

         if (this.bw != null) {
            this.bw.close();
         }

         if (this.br != null) {
            this.br.close();
         }

         if (this.inr != null) {
            this.inr.close();
         }

         if (this.sk != null) {
            this.sk.close();
         }

         this.resultcd = "0000";
      } catch (Exception var2) {
         this.resultcd = "9999";
      }

   }

   public String getResult() {
      return this.result;
   }

   public String getResultcd() {
      return this.resultcd;
   }
}