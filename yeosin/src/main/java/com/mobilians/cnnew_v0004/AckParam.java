package com.mobilians.cnnew_v0004;

import java.io.Serializable;

public class AckParam implements Serializable {
   private String length = "";
   private String mode = "";
   private String taxVer = "";
   private String recordKey = "";
   private String svcId = "";
   private String mobilId = "";
   private String tradeId = "";
   private String prdtPrice = "";
   private String billKey = "";
   private String partCancelYn = "";
   private String rmk = "";
   private String resultCd = "";
   private String resultMsg = "";
   private String signDate = "";
   private String trdDate = "";
   private String trdTime = "";
   private String interest = "";
   private String mrctOtp = "";
   private String cardCode = "";
   private String cardName = "";
   private String apprNo = "";
   private String cardNum = "";
   private String pkLength = "";
   private String ver = "";
   private String cryptGb = "1";
   private String keySeq = "";
   private String cipherKey;
   private String prdtNm = "";
   private String authType = "01";
   private String currency = "KRW";
   private String taxDiv = "";
   private String taxPrice = "";
   private String taxFreePrice = "";
   private String noIntFlag = "";
   private String cardNo = "";
   private String exprDt = "";
   private String cardPw = "";
   private String socialNo = "";
   private String pointDiv = "";
   private String userId = "";
   private String userNm = "";
   private String userEmail = "";
   private String userIp = "";
   private String autoBillDate = "";
   private String autoBillEndDate = "";
   private String svcKind = "00";
   private String posMode = "";
   private String cavv = "";
   private String xid = "";
   private String sessKey = "";
   private String encData = "";
   private String userPhoneNo = "";
   private String essPhoneNo = "";
   private String essEmail = "";
   private String mrctFld = "";
   private String subBizNum = "";
   private String notiType = "00";
   private String notiInfo = "";
   private String kvpCardCode = "";
   private String cardKind = "";
   private String cnclSeq = "";
   private String payMethod = "";
   private String couponPrice = "";
   private String crcCd = "";
   private String taxatDiv = "";
   private String taxAmt = "";
   private String taxFreeAmt = "";
   private String taxatAmt = "";
   private String remainTaxAmt = "";
   private String deposit = "";
   private String ptnCnclTrdNo = "";
   private String remainAmt = "";
   private String partCnclSeq = "";
   private String taxType = "";
   private String ornId = "";
   private String instMonCnt = "";
   private String cardPass = "";
   private String otcNo = "";
   private String easyPayCpCode = "";
   private String authYn = "";
   private String stlMonCnt = "";
   private String spUserId = "";
   private String spMthdId = "";
   private String ssnEnc = "";
   private String prdtCd = "";
   private String ptnId = "";
   private String cnJoinAmt = "";
   private String mpiTransId = "";
   private String cnMpointYn = "";
   private String cnOilInfo = "";
   private String cnSsVoucher = "";
   private String cnSsExceptAmt = "";
   private String ornNm = "";
   private String ptnNm = "";
   private String interestFreeYn = "";
   private String joinTypeCd = "";
   private String billYn = "";
   private String pointApprNo = "";
   private String trdDt = "";
   private String trdTm = "";
   McashSeed seed = new McashSeed();

   public AckParam() {
   }

   public AckParam(String mode, String recordKey, String svcId, String mobilId, String tradeId, String svcKind, String prdtNm, String authType, String currency, String taxType, String prdtPrice, String taxPrice, String taxFreePrice, String ornId, String noIntFlag, String cardNo, String exprDt, String instMonCnt, String cardPass, String posMode, String cavv, String xid, String ssnEnc, String pointDiv, String sessKey, String encData, String userPhoneNo, String userId, String userNm, String userEmail, String userIp, String essPhoneNo, String essEmail, String mrctFld, String subBizNum, String autoBillDate, String autoBillEndDate, String notiType, String notiInfo, String kvpCardCode, String otcNo, String easyPayCpCode, String authYn, String stlMonCnt, String spUserId, String spMthdId, String deposit) {
      this.mode = CommonUtil.nullChk(mode).trim();
      this.recordKey = CommonUtil.nullChk(recordKey).trim();
      this.svcId = CommonUtil.nullChk(svcId).trim();
      this.mobilId = CommonUtil.nullChk(mobilId).trim();
      this.tradeId = CommonUtil.nullChk(tradeId).trim();
      this.svcKind = CommonUtil.nullChk(svcKind).trim();
      this.prdtNm = CommonUtil.nullChk(prdtNm).trim();
      this.authType = CommonUtil.nullChk(authType).trim();
      this.currency = CommonUtil.nullChk(currency).trim();
      this.taxType = CommonUtil.nullChk(taxType).trim();
      this.prdtPrice = CommonUtil.nullChk(prdtPrice).trim();
      this.taxPrice = CommonUtil.nullChk(taxPrice).trim();
      this.taxFreePrice = CommonUtil.nullChk(taxFreePrice).trim();
      this.ornId = CommonUtil.nullChk(ornId).trim();
      this.noIntFlag = CommonUtil.nullChk(noIntFlag).trim();
      this.cardNo = CommonUtil.nullChk(cardNo).trim();
      this.exprDt = CommonUtil.nullChk(exprDt).trim();
      this.instMonCnt = CommonUtil.nullChk(instMonCnt).trim();
      this.cardPass = CommonUtil.nullChk(cardPass).trim();
      this.posMode = CommonUtil.nullChk(posMode).trim();
      this.cavv = CommonUtil.nullChk(cavv).trim();
      this.xid = CommonUtil.nullChk(xid).trim();
      this.ssnEnc = CommonUtil.nullChk(ssnEnc).trim();
      this.pointDiv = CommonUtil.nullChk(pointDiv).trim();
      this.sessKey = CommonUtil.nullChk(sessKey).trim();
      this.encData = CommonUtil.nullChk(encData).trim();
      this.userPhoneNo = CommonUtil.nullChk(userPhoneNo).trim();
      this.userId = CommonUtil.nullChk(userId).trim();
      this.userNm = CommonUtil.nullChk(userNm).trim();
      this.userEmail = CommonUtil.nullChk(userEmail).trim();
      this.userIp = CommonUtil.nullChk(userIp).trim();
      this.essPhoneNo = CommonUtil.nullChk(essPhoneNo).trim();
      this.essEmail = CommonUtil.nullChk(essEmail).trim();
      this.mrctFld = CommonUtil.nullChk(mrctFld).trim();
      this.subBizNum = CommonUtil.nullChk(subBizNum).trim();
      this.autoBillDate = CommonUtil.nullChk(autoBillDate).trim();
      this.autoBillEndDate = CommonUtil.nullChk(autoBillEndDate).trim();
      this.notiType = CommonUtil.nullChk(notiType).trim();
      this.notiInfo = CommonUtil.nullChk(notiInfo).trim();
      this.kvpCardCode = CommonUtil.nullChk(kvpCardCode).trim();
      this.otcNo = CommonUtil.nullChk(otcNo).trim();
      this.easyPayCpCode = CommonUtil.nullChk(easyPayCpCode).trim();
      this.authYn = CommonUtil.nullChk(authYn).trim();
      this.stlMonCnt = CommonUtil.nullChk(stlMonCnt).trim();
      this.spUserId = CommonUtil.nullChk(spUserId).trim();
      this.spMthdId = CommonUtil.nullChk(spMthdId).trim();
      this.deposit = CommonUtil.nullChk(deposit).trim();
   }

   public AckParam(String mode, String taxVer, String recordKey, String svcId, String tradeId, String svcKind, String prdtNm, String authType, String currency, String taxatDiv, String prdtPrice, String taxAmt, String taxFreeAmt, String taxatAmt, String cardCode, String noIntFlag, String cardNo, String exprDt, String interest, String cardPw, String socialNo, String pointDiv, String userId, String userNm, String userEmail, String userIp, String autoBillDate, String subBizNum) {
      this.mode = CommonUtil.nullChk(mode).trim();
      this.taxVer = CommonUtil.nullChk(taxVer).trim();
      this.recordKey = CommonUtil.nullChk(recordKey).trim();
      this.svcId = CommonUtil.nullChk(svcId).trim();
      this.tradeId = CommonUtil.nullChk(tradeId).trim();
      this.svcKind = CommonUtil.nullChk(svcKind).trim();
      this.prdtNm = CommonUtil.nullChk(prdtNm).trim();
      this.authType = CommonUtil.nullChk(authType).trim();
      this.currency = CommonUtil.nullChk(currency).trim();
      this.taxType = CommonUtil.nullChk(taxatDiv).trim();
      this.prdtPrice = CommonUtil.nullChk(prdtPrice).trim();
      this.taxPrice = CommonUtil.nullChk(taxAmt).trim();
      this.taxFreePrice = CommonUtil.nullChk(taxFreeAmt).trim();
      this.taxatAmt = CommonUtil.nullChk(taxatAmt).trim();
      this.ornId = CommonUtil.nullChk(cardCode).trim();
      this.noIntFlag = CommonUtil.nullChk(noIntFlag).trim();
      this.cardNo = CommonUtil.nullChk(cardNo).trim();
      this.exprDt = CommonUtil.nullChk(exprDt).trim();
      this.instMonCnt = CommonUtil.nullChk(interest).trim();
      this.interest = CommonUtil.nullChk(interest).trim();
      this.cardPass = CommonUtil.nullChk(cardPw).trim();
      this.socialNo = CommonUtil.nullChk(socialNo).trim();
      this.pointDiv = CommonUtil.nullChk(pointDiv).trim();
      this.userId = CommonUtil.nullChk(userId).trim();
      this.userNm = CommonUtil.nullChk(userNm).trim();
      this.userEmail = CommonUtil.nullChk(userEmail).trim();
      this.userIp = CommonUtil.nullChk(userIp).trim();
      this.autoBillDate = CommonUtil.nullChk(autoBillDate).trim();
      this.subBizNum = CommonUtil.nullChk(subBizNum).trim();
   }

   public AckParam(String mode, String recordKey, String svcId, String mobilId, String tradeId, String prdtPrice, String billKey) {
      this.mode = CommonUtil.nullChk(mode).trim();
      this.recordKey = CommonUtil.nullChk(recordKey).trim();
      this.svcId = CommonUtil.nullChk(svcId).trim();
      this.mobilId = CommonUtil.nullChk(mobilId).trim();
      this.tradeId = CommonUtil.nullChk(tradeId).trim();
      this.prdtPrice = CommonUtil.nullChk(prdtPrice).trim();
      this.billKey = CommonUtil.nullChk(billKey).trim();
   }

   public AckParam(String mode, String taxVer, String recordKey, String svcId, String mobilId, String tradeId, String prdtPrice, String partCancelYn, String Rmk, String CrcCd, String TaxatDiv, String TaxAmt, String TaxFreeAmt, String TaxatAmt, String RemainTaxAmt, String Deposit) {
      this.mode = CommonUtil.nullChk(mode).trim();
      this.taxVer = CommonUtil.nullChk(taxVer).trim();
      this.recordKey = CommonUtil.nullChk(recordKey).trim();
      this.svcId = CommonUtil.nullChk(svcId).trim();
      this.mobilId = CommonUtil.nullChk(mobilId).trim();
      this.tradeId = CommonUtil.nullChk(tradeId).trim();
      this.prdtPrice = CommonUtil.nullChk(prdtPrice).trim();
      this.partCancelYn = CommonUtil.nullChk(partCancelYn).trim();
      this.rmk = CommonUtil.nullChk(Rmk).trim();
      this.crcCd = CommonUtil.nullChk(CrcCd).trim();
      this.currency = CommonUtil.nullChk(CrcCd).trim();
      this.taxType = CommonUtil.nullChk(TaxatDiv).trim();
      this.taxAmt = CommonUtil.nullChk(TaxAmt).trim();
      this.taxFreeAmt = CommonUtil.nullChk(TaxFreeAmt).trim();
      this.taxatAmt = CommonUtil.nullChk(TaxatAmt).trim();
      this.remainTaxAmt = CommonUtil.nullChk(RemainTaxAmt).trim();
      this.deposit = CommonUtil.nullChk(Deposit).trim();
   }

   public String getLength() {
      return this.length;
   }

   public void setLength(String length) {
      this.length = length;
   }

   public String getRmk() {
      return this.rmk;
   }

   public void setRmk(String rmk) {
      this.rmk = rmk;
   }

   public String getPartCancelYn() {
      return this.partCancelYn;
   }

   public void setPartCancelYn(String partCancelYn) {
      this.partCancelYn = partCancelYn;
   }

   public String getPkLength() {
      return this.pkLength;
   }

   public void setPkLength(String pkLength) {
      this.pkLength = pkLength;
   }

   public String getMode() {
      return this.mode;
   }

   public void setMode(String mode) {
      this.mode = mode;
   }

   public String getTaxVer() {
      return this.taxVer;
   }

   public void setTaxVer(String taxVer) {
      this.taxVer = taxVer;
   }

   public String getRecordKey() {
      return this.recordKey;
   }

   public void setRecordKey(String recordKey) {
      this.recordKey = recordKey;
   }

   public String getSvcId() {
      return this.svcId;
   }

   public void setSvcId(String svcId) {
      this.svcId = svcId;
   }

   public String getMobilId() {
      return this.mobilId;
   }

   public void setMobilId(String mobilId) {
      this.mobilId = mobilId;
   }

   public String getTradeId() {
      return this.tradeId;
   }

   public void setTradeId(String tradeId) {
      this.tradeId = tradeId;
   }

   public String getPrdtPrice() {
      return this.prdtPrice;
   }

   public void setPrdtPrice(String prdtPrice) {
      this.prdtPrice = prdtPrice;
   }

   public String getBillKey() {
      return this.billKey;
   }

   public void setBillKey(String billKey) {
      this.billKey = billKey;
   }

   public McashSeed getSeed() {
      return this.seed;
   }

   public void setSeed(McashSeed seed) {
      this.seed = seed;
   }

   public String getResultCd() {
      return this.resultCd;
   }

   public void setResultCd(String resultCd) {
      this.resultCd = resultCd;
   }

   public String getResultMsg() {
      return this.resultMsg;
   }

   public void setResultMsg(String resultMsg) {
      this.resultMsg = resultMsg;
   }

   public String getSignDate() {
      return this.signDate;
   }

   public void setSignDate(String signDate) {
      this.signDate = signDate;
   }

   public String getTrdDate() {
      return this.trdDate;
   }

   public void setTrdDate(String trdDate) {
      this.trdDate = trdDate;
   }

   public String getTrdTime() {
      return this.trdTime;
   }

   public void setTrdTime(String trdTime) {
      this.trdTime = trdTime;
   }

   public String getInterest() {
      return this.interest;
   }

   public void setInterest(String interest) {
      this.interest = interest;
   }

   public String getMrctOtp() {
      return this.mrctOtp;
   }

   public void setMrctOtp(String mrctOtp) {
      this.mrctOtp = mrctOtp;
   }

   public String getCardCode() {
      return this.cardCode;
   }

   public void setCardCode(String cardCode) {
      this.cardCode = cardCode;
   }

   public String getCardName() {
      return this.cardName;
   }

   public void setCardName(String cardName) {
      this.cardName = cardName;
   }

   public String getApprNo() {
      return this.apprNo;
   }

   public void setApprNo(String apprNo) {
      this.apprNo = apprNo;
   }

   public String getCardNum() {
      return this.cardNum;
   }

   public void setCardNum(String cardNum) {
      this.cardNum = cardNum;
   }

   public String getVer() {
      return this.ver;
   }

   public void setVer(String ver) {
      this.ver = ver;
   }

   public String getCryptGb() {
      return this.cryptGb;
   }

   public void setCryptGb(String cryptGb) {
      this.cryptGb = cryptGb;
   }

   public String getKeySeq() {
      return this.keySeq;
   }

   public void setKeySeq(String keySeq) {
      this.keySeq = keySeq;
   }

   public String getCipherKey() {
      return this.cipherKey;
   }

   public void setCipherKey(String cipherKey) {
      this.cipherKey = cipherKey;
   }

   public String getPrdtNm() {
      return this.prdtNm;
   }

   public void setPrdtNm(String prdtNm) {
      this.prdtNm = prdtNm;
   }

   public String getAuthType() {
      return this.authType;
   }

   public void setAuthType(String authType) {
      this.authType = authType;
   }

   public String getCurrency() {
      return this.currency;
   }

   public void setCurrency(String currency) {
      this.currency = currency;
   }

   public String getTaxDiv() {
      return this.taxDiv;
   }

   public void setTaxDiv(String taxDiv) {
      this.taxDiv = taxDiv;
   }

   public String getTaxPrice() {
      return this.taxPrice;
   }

   public void setTaxPrice(String taxPrice) {
      this.taxPrice = taxPrice;
   }

   public String getTaxFreePrice() {
      return this.taxFreePrice;
   }

   public void setTaxFreePrice(String taxFreePrice) {
      this.taxFreePrice = taxFreePrice;
   }

   public String getNoIntFlag() {
      return this.noIntFlag;
   }

   public void setNoIntFlag(String noIntFlag) {
      this.noIntFlag = noIntFlag;
   }

   public String getCardNo() {
      return this.cardNo;
   }

   public void setCardNo(String cardNo) {
      this.cardNo = cardNo;
   }

   public String getExprDt() {
      return this.exprDt;
   }

   public void setExprDt(String exprDt) {
      this.exprDt = exprDt;
   }

   public String getCardPw() {
      return this.cardPw;
   }

   public void setCardPw(String cardPw) {
      this.cardPw = cardPw;
   }

   public String getSocialNo() {
      return this.socialNo;
   }

   public void setSocialNo(String socialNo) {
      this.socialNo = socialNo;
   }

   public String getPointDiv() {
      return this.pointDiv;
   }

   public void setPointDiv(String pointDiv) {
      this.pointDiv = pointDiv;
   }

   public String getUserId() {
      return this.userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getUserNm() {
      return this.userNm;
   }

   public void setUserNm(String userNm) {
      this.userNm = userNm;
   }

   public String getUserEmail() {
      return this.userEmail;
   }

   public void setUserEmail(String userEmail) {
      this.userEmail = userEmail;
   }

   public String getUserIp() {
      return this.userIp;
   }

   public void setUserIp(String userIp) {
      this.userIp = userIp;
   }

   public String getAutoBillDate() {
      return this.autoBillDate;
   }

   public void setAutoBillDate(String autoBillDate) {
      this.autoBillDate = autoBillDate;
   }

   public String getAutoBillEndDate() {
      return this.autoBillEndDate;
   }

   public void setAutoBillEndDate(String autoBillEndDate) {
      this.autoBillEndDate = autoBillEndDate;
   }

   public String getSvcKind() {
      return this.svcKind;
   }

   public void setSvcKind(String svcKind) {
      this.svcKind = svcKind;
   }

   public String getPosMode() {
      return this.posMode;
   }

   public void setPosMode(String posMode) {
      this.posMode = posMode;
   }

   public String getCavv() {
      return this.cavv;
   }

   public void setCavv(String cavv) {
      this.cavv = cavv;
   }

   public String getXid() {
      return this.xid;
   }

   public void setXid(String xid) {
      this.xid = xid;
   }

   public String getSessKey() {
      return this.sessKey;
   }

   public void setSessKey(String sessKey) {
      this.sessKey = sessKey;
   }

   public String getEncData() {
      return this.encData;
   }

   public void setEncData(String encData) {
      this.encData = encData;
   }

   public String getUserPhoneNo() {
      return this.userPhoneNo;
   }

   public void setUserPhoneNo(String userPhoneNo) {
      this.userPhoneNo = userPhoneNo;
   }

   public String getEssPhoneNo() {
      return this.essPhoneNo;
   }

   public void setEssPhoneNo(String essPhoneNo) {
      this.essPhoneNo = essPhoneNo;
   }

   public String getEssEmail() {
      return this.essEmail;
   }

   public void setEssEmail(String essEmail) {
      this.essEmail = essEmail;
   }

   public String getMrctFld() {
      return this.mrctFld;
   }

   public void setMrctFld(String mrctFld) {
      this.mrctFld = mrctFld;
   }

   public String getSubBizNum() {
      return this.subBizNum;
   }

   public void setSubBizNum(String subBizNum) {
      this.subBizNum = subBizNum;
   }

   public String getNotiType() {
      return this.notiType;
   }

   public void setNotiType(String notiType) {
      this.notiType = notiType;
   }

   public String getNotiInfo() {
      return this.notiInfo;
   }

   public void setNotiInfo(String notiInfo) {
      this.notiInfo = notiInfo;
   }

   public String getKvpCardCode() {
      return this.kvpCardCode;
   }

   public void setKvpCardCode(String kvpCardCode) {
      this.kvpCardCode = kvpCardCode;
   }

   public String getCardKind() {
      return this.cardKind;
   }

   public void setCardKind(String cardKind) {
      this.cardKind = cardKind;
   }

   public String getCnclSeq() {
      return this.cnclSeq;
   }

   public void setCnclSeq(String cnclSeq) {
      this.cnclSeq = cnclSeq;
   }

   public String getPayMethod() {
      return this.payMethod;
   }

   public void setPayMethod(String payMethod) {
      this.payMethod = payMethod;
   }

   public String getCouponPrice() {
      return this.couponPrice;
   }

   public void setCouponPrice(String couponPrice) {
      this.couponPrice = couponPrice;
   }

   public String getCrcCd() {
      return this.crcCd;
   }

   public void setCrcCd(String crcCd) {
      this.crcCd = crcCd;
   }

   public String getTaxatDiv() {
      return this.taxatDiv;
   }

   public void setTaxatDiv(String taxatDiv) {
      this.taxatDiv = taxatDiv;
   }

   public String getTaxAmt() {
      return this.taxAmt;
   }

   public void setTaxAmt(String taxAmt) {
      this.taxAmt = taxAmt;
   }

   public String getTaxFreeAmt() {
      return this.taxFreeAmt;
   }

   public void setTaxFreeAmt(String taxFreeAmt) {
      this.taxFreeAmt = taxFreeAmt;
   }

   public String getTaxatAmt() {
      return this.taxatAmt;
   }

   public void setTaxatAmt(String taxatAmt) {
      this.taxatAmt = taxatAmt;
   }

   public String getRemainTaxAmt() {
      return this.remainTaxAmt;
   }

   public void setRemainTaxAmt(String remainTaxAmt) {
      this.remainTaxAmt = remainTaxAmt;
   }

   public String getDeposit() {
      return this.deposit;
   }

   public void setDeposit(String deposit) {
      this.deposit = deposit;
   }

   public String getPtnCnclTrdNo() {
      return this.ptnCnclTrdNo;
   }

   public void setPtnCnclTrdNo(String ptnCnclTrdNo) {
      this.ptnCnclTrdNo = ptnCnclTrdNo;
   }

   public String getRemainAmt() {
      return this.remainAmt;
   }

   public void setRemainAmt(String remainAmt) {
      this.remainAmt = remainAmt;
   }

   public String getPartCnclSeq() {
      return this.partCnclSeq;
   }

   public void setPartCnclSeq(String partCnclSeq) {
      this.partCnclSeq = partCnclSeq;
   }

   public String getTaxType() {
      return this.taxType;
   }

   public void setTaxType(String taxType) {
      this.taxType = taxType;
   }

   public String getOrnId() {
      return this.ornId;
   }

   public void setOrnId(String ornId) {
      this.ornId = ornId;
   }

   public String getInstMonCnt() {
      return this.instMonCnt;
   }

   public void setInstMonCnt(String instMonCnt) {
      this.instMonCnt = instMonCnt;
   }

   public String getCardPass() {
      return this.cardPass;
   }

   public void setCardPass(String cardPass) {
      this.cardPass = cardPass;
   }

   public String getOtcNo() {
      return this.otcNo;
   }

   public void setOtcNo(String otcNo) {
      this.otcNo = otcNo;
   }

   public String getEasyPayCpCode() {
      return this.easyPayCpCode;
   }

   public void setEasyPayCpCode(String easyPayCpCode) {
      this.easyPayCpCode = easyPayCpCode;
   }

   public String getAuthYn() {
      return this.authYn;
   }

   public void setAuthYn(String authYn) {
      this.authYn = authYn;
   }

   public String getStlMonCnt() {
      return this.stlMonCnt;
   }

   public void setStlMonCnt(String stlMonCnt) {
      this.stlMonCnt = stlMonCnt;
   }

   public String getSpUserId() {
      return this.spUserId;
   }

   public void setSpUserId(String spUserId) {
      this.spUserId = spUserId;
   }

   public String getSpMthdId() {
      return this.spMthdId;
   }

   public void setSpMthdId(String spMthdId) {
      this.spMthdId = spMthdId;
   }

   public String getSsnEnc() {
      return this.ssnEnc;
   }

   public void setSsnEnc(String ssnEnc) {
      this.ssnEnc = ssnEnc;
   }

   public String getPrdtCd() {
      return this.prdtCd;
   }

   public void setPrdtCd(String prdtCd) {
      this.prdtCd = prdtCd;
   }

   public String getPtnId() {
      return this.ptnId;
   }

   public void setPtnId(String ptnId) {
      this.ptnId = ptnId;
   }

   public String getCnJoinAmt() {
      return this.cnJoinAmt;
   }

   public void setCnJoinAmt(String cnJoinAmt) {
      this.cnJoinAmt = cnJoinAmt;
   }

   public String getMpiTransId() {
      return this.mpiTransId;
   }

   public void setMpiTransId(String mpiTransId) {
      this.mpiTransId = mpiTransId;
   }

   public String getCnMpointYn() {
      return this.cnMpointYn;
   }

   public void setCnMpointYn(String cnMpointYn) {
      this.cnMpointYn = cnMpointYn;
   }

   public String getCnOilInfo() {
      return this.cnOilInfo;
   }

   public void setCnOilInfo(String cnOilInfo) {
      this.cnOilInfo = cnOilInfo;
   }

   public String getCnSsVoucher() {
      return this.cnSsVoucher;
   }

   public void setCnSsVoucher(String cnSsVoucher) {
      this.cnSsVoucher = cnSsVoucher;
   }

   public String getCnSsExceptAmt() {
      return this.cnSsExceptAmt;
   }

   public void setCnSsExceptAmt(String cnSsExceptAmt) {
      this.cnSsExceptAmt = cnSsExceptAmt;
   }

   public String getOrnNm() {
      return this.ornNm;
   }

   public void setOrnNm(String ornNm) {
      this.ornNm = ornNm;
   }

   public String getPtnNm() {
      return this.ptnNm;
   }

   public void setPtnNm(String ptnNm) {
      this.ptnNm = ptnNm;
   }

   public String getInterestFreeYn() {
      return this.interestFreeYn;
   }

   public void setInterestFreeYn(String interestFreeYn) {
      this.interestFreeYn = interestFreeYn;
   }

   public String getJoinTypeCd() {
      return this.joinTypeCd;
   }

   public void setJoinTypeCd(String joinTypeCd) {
      this.joinTypeCd = joinTypeCd;
   }

   public String getBillYn() {
      return this.billYn;
   }

   public void setBillYn(String billYn) {
      this.billYn = billYn;
   }

   public String getPointApprNo() {
      return this.pointApprNo;
   }

   public void setPointApprNo(String pointApprNo) {
      this.pointApprNo = pointApprNo;
   }

   public String getTrdDt() {
      return this.trdDt;
   }

   public void setTrdDt(String trdDt) {
      this.trdDt = trdDt;
   }

   public String getTrdTm() {
      return this.trdTm;
   }

   public void setTrdTm(String trdTm) {
      this.trdTm = trdTm;
   }

   public String getSocketStr(String UserEncode) {
      StringBuilder strBuffer = new StringBuilder();
      StringBuilder appdBuffer = new StringBuilder();
      int lengCnt = 0;
      String szPrdtPrice = "";
      String szTaxPrice = "";
      String szTaxFreePrice = "";
      String szSsnEnc = "";
      String szCardNo = "";
      String szTaxatAmt = "";
      String szRemainTaxAmt = "";
      String szDeposit = "";
      String szCnJoinAmt = "";
      String szCnSsExceptAmt = "";
      szSsnEnc = CommonUtil.paddingN(this.socialNo, 6);
      szPrdtPrice = CommonUtil.paddingN(this.prdtPrice, 10);
      szTaxPrice = CommonUtil.paddingN(this.taxPrice, 10);
      szTaxFreePrice = CommonUtil.paddingN(this.taxFreePrice, 10);
      szTaxatAmt = CommonUtil.paddingN(this.taxatAmt, 10);
      szRemainTaxAmt = CommonUtil.paddingN(this.remainTaxAmt, 10);
      szDeposit = CommonUtil.paddingN(this.deposit, 10);
      szCnJoinAmt = CommonUtil.paddingN(this.cnJoinAmt, 10);
      szCnSsExceptAmt = CommonUtil.paddingN(this.cnSsExceptAmt, 10);
      if (this.svcId.length() < 8) {
         this.setCipherKey(CommonUtil.paddingC(this.svcId, 8) + CommonUtil.paddingC(this.svcId, 8));
      }

      if ("CPLX".equals(this.taxVer) && "20".equals(this.taxType)) {
         this.ver = "CPLX";
      }

      szPrdtPrice = McashSeed.encodeString(szPrdtPrice, this.cipherKey.getBytes());
      szTaxPrice = McashSeed.encodeString(szTaxPrice, this.cipherKey.getBytes());
      szTaxFreePrice = McashSeed.encodeString(szTaxFreePrice, this.cipherKey.getBytes());
      szSsnEnc = McashSeed.encodeString(szSsnEnc, this.cipherKey.getBytes());
      szCardNo = McashSeed.encodeString(this.cardNo, this.cipherKey.getBytes());
      szTaxatAmt = McashSeed.encodeString(szTaxatAmt, this.cipherKey.getBytes());
      szRemainTaxAmt = McashSeed.encodeString(szRemainTaxAmt, this.cipherKey.getBytes());
      szDeposit = McashSeed.encodeString(szDeposit, this.cipherKey.getBytes());
      appdBuffer.append(CommonUtil.paddingC(this.mode, 4));
//      int lengCnt = lengCnt + 4;
      lengCnt = lengCnt + 4;
      appdBuffer.append(CommonUtil.paddingC(this.ver, 4));
      lengCnt += 4;
      appdBuffer.append(CommonUtil.paddingC(this.cryptGb, 1));
      ++lengCnt;
      appdBuffer.append(CommonUtil.paddingC(this.getKeySeq(), 1));
      ++lengCnt;
      appdBuffer.append(CommonUtil.paddingC(this.recordKey, 20));
      lengCnt += 20;
      appdBuffer.append(CommonUtil.paddingC(this.svcId, 32));
      lengCnt += 32;
      if ("CN45".equals(this.mode) || "CN48".equals(this.mode)) {
         appdBuffer.append(CommonUtil.paddingC(this.tradeId, 80));
         lengCnt += 80;
         appdBuffer.append(CommonUtil.paddingC(this.svcKind, 2));
         lengCnt += 2;
         appdBuffer.append(CommonUtil.paddingC(this.prdtNm, 255));
         lengCnt += 255;
         appdBuffer.append(CommonUtil.paddingC(this.authType, 2));
         lengCnt += 2;
         appdBuffer.append(CommonUtil.paddingC(this.currency, 3));
         lengCnt += 3;
         appdBuffer.append(CommonUtil.paddingC(this.taxType, 2));
         lengCnt += 2;
         appdBuffer.append(CommonUtil.paddingC(szPrdtPrice, 32));
         lengCnt += 32;
         appdBuffer.append(CommonUtil.paddingC(szTaxPrice, 32));
         lengCnt += 32;
         appdBuffer.append(CommonUtil.paddingC(szTaxFreePrice, 32));
         lengCnt += 32;
         appdBuffer.append(CommonUtil.paddingC(this.ornId, 3));
         lengCnt += 3;
         appdBuffer.append(CommonUtil.paddingC(this.noIntFlag, 1));
         ++lengCnt;
         appdBuffer.append(CommonUtil.paddingC(szCardNo, 64));
         lengCnt += 64;
         appdBuffer.append(CommonUtil.paddingC(this.exprDt, 8));
         lengCnt += 8;
         appdBuffer.append(CommonUtil.paddingC(this.instMonCnt, 2));
         lengCnt += 2;
         appdBuffer.append(CommonUtil.paddingC(this.cardPass, 4));
         lengCnt += 4;
         appdBuffer.append(CommonUtil.paddingC(this.posMode, 3));
         lengCnt += 3;
         appdBuffer.append(CommonUtil.paddingC(this.cavv, 28));
         lengCnt += 28;
         appdBuffer.append(CommonUtil.paddingC(this.xid, 28));
         lengCnt += 28;
         appdBuffer.append(CommonUtil.paddingC(szSsnEnc, 32));
         lengCnt += 32;
         appdBuffer.append(CommonUtil.paddingC(this.pointDiv, 2));
         lengCnt += 2;
         appdBuffer.append(CommonUtil.paddingC(this.sessKey, 512));
         lengCnt += 512;
         appdBuffer.append(CommonUtil.paddingC(this.encData, 4096));
         lengCnt += 4096;
         appdBuffer.append(CommonUtil.paddingC(this.userPhoneNo, 20));
         lengCnt += 20;
         appdBuffer.append(CommonUtil.paddingC(this.userId, 50));
         lengCnt += 50;
         appdBuffer.append(CommonUtil.paddingC(this.userNm, 20));
         lengCnt += 20;
         appdBuffer.append(CommonUtil.paddingC(this.userEmail, 50));
         lengCnt += 50;
         appdBuffer.append(CommonUtil.paddingC(this.userIp, 20));
         lengCnt += 20;
         appdBuffer.append(CommonUtil.paddingC(this.essPhoneNo, 15));
         lengCnt += 15;
         appdBuffer.append(CommonUtil.paddingC(this.essEmail, 50));
         lengCnt += 50;
         appdBuffer.append(CommonUtil.paddingC(this.mrctFld, 1024));
         lengCnt += 1024;
         if ("CN45".equals(this.mode)) {
            appdBuffer.append(CommonUtil.paddingC("", 267));
            lengCnt += 267;
            appdBuffer.append(CommonUtil.paddingC(this.subBizNum, 12));
            lengCnt += 12;
         } else {
            appdBuffer.append(CommonUtil.paddingC(this.autoBillDate, 2));
            lengCnt += 2;
            appdBuffer.append(CommonUtil.paddingC(this.autoBillEndDate, 8));
            lengCnt += 8;
            appdBuffer.append(CommonUtil.paddingC(this.notiType, 2));
            lengCnt += 2;
            appdBuffer.append(CommonUtil.paddingC(this.notiInfo, 255));
            lengCnt += 255;
            appdBuffer.append(CommonUtil.paddingC("", 12));
            lengCnt += 12;
         }

         appdBuffer.append(CommonUtil.paddingC(this.kvpCardCode, 20));
         lengCnt += 20;
         appdBuffer.append(CommonUtil.paddingC(this.otcNo, 40));
         lengCnt += 40;
         appdBuffer.append(CommonUtil.paddingC(this.easyPayCpCode, 4));
         lengCnt += 4;
         appdBuffer.append(CommonUtil.paddingC(this.authYn, 1));
         ++lengCnt;
         appdBuffer.append(CommonUtil.paddingC(this.stlMonCnt, 2));
         lengCnt += 2;
         appdBuffer.append(CommonUtil.paddingC(this.spUserId, 44));
         lengCnt += 44;
         appdBuffer.append(CommonUtil.paddingC(this.spMthdId, 44));
         lengCnt += 44;
         appdBuffer.append(CommonUtil.paddingC(this.prdtCd, 100));
         lengCnt += 100;
         appdBuffer.append(CommonUtil.paddingC(szDeposit, 32));
         lengCnt += 32;
         appdBuffer.append(CommonUtil.paddingC(szCnJoinAmt, 32));
         lengCnt += 32;
         appdBuffer.append(CommonUtil.paddingC(this.mpiTransId, 36));
         lengCnt += 36;
         appdBuffer.append(CommonUtil.paddingC(this.cnMpointYn, 1));
         ++lengCnt;
         appdBuffer.append(CommonUtil.paddingC(this.cnOilInfo, 23));
         lengCnt += 23;
         appdBuffer.append(CommonUtil.paddingC(this.cnSsVoucher, 22));
         lengCnt += 22;
         appdBuffer.append(CommonUtil.paddingC(szCnSsExceptAmt, 32));
         lengCnt += 32;
         appdBuffer.append(CommonUtil.paddingC(szTaxatAmt, 32));
         lengCnt += 32;
      }

      if ("CN46".equals(this.mode) || "CN49".equals(this.mode)) {
         appdBuffer.append(CommonUtil.paddingC(this.mobilId, 20));
         lengCnt += 20;
         appdBuffer.append(CommonUtil.paddingC(this.tradeId, 80));
         lengCnt += 80;
         appdBuffer.append(CommonUtil.paddingC(szPrdtPrice, 32));
         lengCnt += 32;
         if ("CN46".equals(this.mode)) {
            appdBuffer.append(CommonUtil.paddingC("", 40));
            lengCnt += 40;
         } else {
            appdBuffer.append(CommonUtil.paddingC(this.billKey, 40));
            lengCnt += 40;
         }
      }

      if ("CN47".equals(this.mode)) {
         appdBuffer.append(CommonUtil.paddingC(this.tradeId, 80));
         lengCnt += 80;
         appdBuffer.append(CommonUtil.paddingC(this.mobilId, 20));
         lengCnt += 20;
         appdBuffer.append(CommonUtil.paddingC(szPrdtPrice, 32));
         lengCnt += 32;
         appdBuffer.append(CommonUtil.paddingC(this.partCancelYn, 1));
         ++lengCnt;
         appdBuffer.append(CommonUtil.paddingC(this.rmk, 255));
         lengCnt += 255;
         appdBuffer.append(CommonUtil.paddingC(this.currency, 3));
         lengCnt += 3;
         appdBuffer.append(CommonUtil.paddingC(this.taxType, 2));
         lengCnt += 2;
         appdBuffer.append(CommonUtil.paddingC(szTaxPrice, 32));
         lengCnt += 32;
         appdBuffer.append(CommonUtil.paddingC(szTaxFreePrice, 32));
         lengCnt += 32;
         appdBuffer.append(CommonUtil.paddingC(szTaxatAmt, 32));
         lengCnt += 32;
         appdBuffer.append(CommonUtil.paddingC(szRemainTaxAmt, 32));
         lengCnt += 32;
         appdBuffer.append(CommonUtil.paddingC(szDeposit, 32));
         lengCnt += 32;
      }

      this.pkLength = String.valueOf(lengCnt);
      strBuffer.append(CommonUtil.paddingN(this.pkLength, 6));
      strBuffer.append(appdBuffer);
      return strBuffer.toString();
   }

   public String setSocketStr(String tmp, String serverEncode) {
      int count = 0;
      byte[] bt = new byte[1024];

      try {
         bt = tmp.getBytes("EUC-KR");
         String szPrdtprice = "";
         String szAmt = "";
         String szCouponprice = "";
         String szDeposit = "";
         this.pkLength = CommonUtil.trim(new String(bt, count, 6));
//         int count = count
         count = count + 6;
         this.mode = CommonUtil.trim(new String(bt, count, 4));
         count += 4;
         this.ver = CommonUtil.trim(new String(bt, count, 4));
         count += 4;
         this.cryptGb = CommonUtil.trim(new String(bt, count, 1));
         ++count;
         this.keySeq = CommonUtil.trim(new String(bt, count, 1));
         ++count;
         this.recordKey = CommonUtil.trim(new String(bt, count, 20));
         count += 20;
         this.svcId = CommonUtil.trim(new String(bt, count, 32));
         count += 32;
         this.resultCd = CommonUtil.trim(new String(bt, count, 4));
         count += 4;
         this.resultMsg = CommonUtil.trim(new String(bt, count, 100, serverEncode));
         count += 100;
         if ("CN55".equals(this.mode) || "CN58".equals(this.mode)) {
            this.tradeId = CommonUtil.trim(new String(bt, count, 80));
            count += 80;
            szPrdtprice = new String(bt, count, 32);
            count += 32;
            if (szPrdtprice != null && !"".equals(szPrdtprice.trim())) {
               szAmt = McashSeed.decodeString(szPrdtprice, this.cipherKey.getBytes()).trim();
               this.prdtPrice = String.valueOf(Integer.parseInt(szAmt));
            }

            this.mobilId = CommonUtil.trim(new String(bt, count, 20));
            count += 20;
            this.billKey = CommonUtil.trim(new String(bt, count, 40));
            count += 40;
            this.ptnId = CommonUtil.trim(new String(bt, count, 3));
            count += 3;
            this.cardKind = CommonUtil.trim(new String(bt, count, 2));
            count += 2;
            this.mrctOtp = CommonUtil.trim(new String(bt, count, 32));
            count += 32;
         }

         if ("CN56".equals(this.mode) || "CN59".equals(this.mode)) {
            this.tradeId = CommonUtil.trim(new String(bt, count, 80));
            count += 80;
            szPrdtprice = new String(bt, count, 32);
            count += 32;
            if (szPrdtprice != null && !"".equals(szPrdtprice.trim())) {
               szAmt = McashSeed.decodeString(szPrdtprice, this.cipherKey.getBytes()).trim();
               this.prdtPrice = String.valueOf(Integer.parseInt(szAmt));
            }

            this.mobilId = CommonUtil.trim(new String(bt, count, 20));
            count += 20;
            this.trdDt = CommonUtil.trim(new String(bt, count, 8));
            count += 8;
            this.trdTm = CommonUtil.trim(new String(bt, count, 6));
            count += 6;
            this.instMonCnt = CommonUtil.trim(new String(bt, count, 2));
            count += 2;
            this.mrctOtp = CommonUtil.trim(new String(bt, count, 32));
            count += 32;
            this.ornId = CommonUtil.trim(new String(bt, count, 3));
            count += 3;
            this.ornNm = CommonUtil.trim(new String(bt, count, 50));
            count += 50;
            this.ptnId = CommonUtil.trim(new String(bt, count, 3));
            count += 3;
            this.ptnNm = CommonUtil.trim(new String(bt, count, 50));
            count += 50;
            this.apprNo = CommonUtil.trim(new String(bt, count, 20));
            count += 20;
            this.mrctFld = CommonUtil.trim(new String(bt, count, 1024));
            count += 1024;
            this.cardNum = CommonUtil.trim(new String(bt, count, 20));
            count += 20;
            this.payMethod = CommonUtil.trim(new String(bt, count, 10));
            count += 10;
            this.couponPrice = CommonUtil.trim(new String(bt, count, 10));
            count += 10;
            this.interestFreeYn = CommonUtil.trim(new String(bt, count, 1));
            ++count;
            this.joinTypeCd = CommonUtil.trim(new String(bt, count, 4));
            count += 4;
            this.billYn = CommonUtil.trim(new String(bt, count, 1));
            ++count;
            this.pointApprNo = CommonUtil.trim(new String(bt, count, 8));
            count += 8;
            this.cardCode = this.ornId;
            this.cardName = this.ornNm;
            this.interest = this.instMonCnt;
            this.signDate = this.trdDt + this.trdTm;
         }

         if ("CN57".equals(this.mode)) {
            this.tradeId = CommonUtil.trim(new String(bt, count, 80));
            count += 80;
            szPrdtprice = new String(bt, count, 32);
            count += 32;
            if (szPrdtprice != null && !"".equals(szPrdtprice.trim())) {
               szAmt = McashSeed.decodeString(szPrdtprice, this.cipherKey.getBytes()).trim();
               this.prdtPrice = String.valueOf(Integer.parseInt(szAmt));
            }

            this.mobilId = CommonUtil.trim(new String(bt, count, 20));
            count += 20;
            this.signDate = CommonUtil.trim(new String(bt, count, 8));
            count += 8;
            this.cnclSeq = CommonUtil.trim(new String(bt, count, 6));
            count += 6;
            this.ptnCnclTrdNo = CommonUtil.trim(new String(bt, count, 50));
            count += 50;
            this.payMethod = CommonUtil.trim(new String(bt, count, 10));
            count += 10;
            szCouponprice = CommonUtil.trim(new String(bt, count, 10));
            count += 10;
            this.partCancelYn = CommonUtil.trim(new String(bt, count, 1));
            ++count;
            this.remainAmt = CommonUtil.trim(new String(bt, count, 12));
            count += 12;
            this.partCnclSeq = CommonUtil.trim(new String(bt, count, 4));
            count += 4;
         }

         return "0000";
      } catch (Exception var9) {
         var9.printStackTrace();
         System.out.println("MCASH-Error = setSocketStr(UserEncode) " + var9.toString());
         this.resultCd = "-1";
         this.resultMsg = "SYSTEM ERROR";
         return "-1";
      }
   }
}