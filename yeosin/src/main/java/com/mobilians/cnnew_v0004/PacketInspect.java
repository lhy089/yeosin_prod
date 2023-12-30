 package com.mobilians.cnnew_v0004;

import java.io.UnsupportedEncodingException;

public class PacketInspect {
   private String configPath;
   private String logDir = "";

   public PacketInspect(String configPath, String logDir) {
      this.configPath = configPath;
      this.logDir = logDir;
   }

   public AckParam checkRequest(AckParam ack) throws NullPointerException {
      return this.checkRequest(ack, false);
   }

   public AckParam checkRequest(AckParam ack, boolean isEng) throws NullPointerException {
      FileMaker fm = new FileMaker(this.configPath, this.logDir);
      ack.setResultCd("0000");
      if (ack.getMode().equals("45")) {
         fm.close();
         return ack;
      } else if (!ack.getMode().equals("CN47") && !ack.getMode().equals("CN45") && !ack.getMode().equals("CN48") && !ack.getMode().equals("CN46") && !ack.getMode().equals("CN49")) {
         ack.setResultCd("9101");
         if (isEng) {
            ack.setResultMsg("[9101] Error in payment request data. \\n\\n Mode check requested");
         } else {
            ack.setResultMsg("[9101] 결제요청자료 오류입니다.\\n\\n Mode[기능식별자] 확인 요망");
         }

         fm.errorln("Error : Mode[기능식별자] 확인 요망 - [" + ack.getMode() + "] ");
         fm.close();
         return ack;
      } else if (CommonUtil.strLength(ack.getRecordKey()) >= 2 && CommonUtil.strLength(ack.getRecordKey()) <= 20) {
         if (CommonUtil.strLength(ack.getSvcId()) >= 1 && CommonUtil.strLength(ack.getSvcId()) <= 12) {
            if (CommonUtil.strLength(ack.getTradeId()) >= 1 && CommonUtil.strLength(ack.getTradeId()) <= 50) {
               if (ack.getMode().equals("CN49") || ack.getMode().equals("CN45") || ack.getMode().equals("CN48") || CommonUtil.strLength(ack.getMobilId()) >= 10 && CommonUtil.strLength(ack.getMobilId()) <= 20) {
                  if (CommonUtil.strLength(ack.getPrdtPrice()) >= 1 && CommonUtil.strLength(ack.getPrdtPrice()) <= 32) {
                     if (ack.getMode().equals("CN49") && (CommonUtil.strLength(ack.getBillKey()) < 1 || CommonUtil.strLength(ack.getBillKey()) > 40)) {
                        ack.setResultCd("9111");
                        if (isEng) {
                           ack.setResultMsg("[9111] Error in payment request data. \\n\\n billKey check requested ");
                        } else {
                           ack.setResultMsg("[9111] 결제요청자료 오류입니다. \\n\\n billKey[자동결제키] 확인 요망");
                        }

                        fm.errorln("Error : billKey[자동결제키] 확인 요망- [" + ack.getBillKey() + "] ");
                        fm.close();
                        return ack;
                     } else {
                        if (ack.getMode().equals("CN45") || ack.getMode().equals("CN48")) {
                           label280: {
                              if (CommonUtil.strLength(ack.getPrdtNm()) >= 1 && CommonUtil.strLength(ack.getPrdtNm()) <= 50) {
                                 if (CommonUtil.strLength(ack.getAuthType()) != 2) {
                                    ack.setResultCd("9112");
                                    if (isEng) {
                                       ack.setResultMsg("[9112] Error in payment request data. \\n\\n authType check requested");
                                    } else {
                                       ack.setResultMsg("[9112] 결제요청자료 오류입니다. \\n\\n authType[인증구분]  확인 요망");
                                    }

                                    fm.errorln("Error : authType[인증구분]  확인 요망- [" + ack.getAuthType() + "] ");
                                    fm.close();
                                    return ack;
                                 }

                                 if (CommonUtil.strLength(ack.getCardNo()) >= 1 && CommonUtil.strLength(ack.getCardNo()) <= 16) {
                                    if (CommonUtil.strLength(ack.getExprDt()) >= 1 && CommonUtil.strLength(ack.getExprDt()) <= 8) {
                                       if (CommonUtil.isEmpty(ack.getSocialNo()) || CommonUtil.strLength(ack.getSocialNo()) >= 6 && CommonUtil.strLength(ack.getSocialNo()) <= 13) {
                                          if (!CommonUtil.isEmpty(ack.getCardPw()) && (CommonUtil.strLength(ack.getCardPw()) < 1 || CommonUtil.strLength(ack.getCardPw()) > 4)) {
                                             ack.setResultCd("9121");
                                             if (isEng) {
                                                ack.setResultMsg("[9121] Error in payment request data. \\n\\n cardPw check requested");
                                             } else {
                                                ack.setResultMsg("[9121] 결제요청자료 오류입니다. \\n\\n cardPw[카드비밀번호]  확인 요망");
                                             }

                                             fm.errorln("Error : cardPw[카드비밀번호]  확인 요망- [" + ack.getCardPw() + "] ");
                                             fm.close();
                                             return ack;
                                          }

                                          if (CommonUtil.strLength(ack.getCurrency()) != 3) {
                                             ack.setResultCd("9113");
                                             if (isEng) {
                                                ack.setResultMsg("[9113] Error in payment request data. \\n\\n currency check requested");
                                             } else {
                                                ack.setResultMsg("[9113] 결제요청자료 오류입니다. \\n\\n currency[통화코드]  확인 요망");
                                             }

                                             fm.errorln("Error : currency[통화코드]  확인 요망- [" + ack.getCurrency() + "] ");
                                             fm.close();
                                             return ack;
                                          }

                                          if (CommonUtil.strLength(ack.getTaxPrice()) > 10) {
                                             ack.setResultCd("9115");
                                             if (isEng) {
                                                ack.setResultMsg("[9115] Error in payment request data. \\n\\n taxPrice check requested");
                                             } else {
                                                ack.setResultMsg("[9115] 결제요청자료 오류입니다. \\n\\n taxPrice[부가세 금액]  확인 요망");
                                             }

                                             fm.errorln("Error : taxPrice[부가세 금액]  확인 요망- [" + ack.getTaxPrice() + "] ");
                                             fm.close();
                                             return ack;
                                          }

                                          if (CommonUtil.strLength(ack.getTaxFreePrice()) > 10) {
                                             ack.setResultCd("9116");
                                             if (isEng) {
                                                ack.setResultMsg("[9116] Error in payment request data. \\n\\n taxFreePrice check requested");
                                             } else {
                                                ack.setResultMsg("[9116] 결제요청자료 오류입니다. \\n\\n taxFreePrice[비과세 금액]  확인 요망");
                                             }

                                             fm.errorln("Error : taxFreePrice[비과세 금액]  확인 요망- [" + ack.getTaxFreePrice() + "] ");
                                             fm.close();
                                             return ack;
                                          }

                                          if (CommonUtil.strLength(ack.getNoIntFlag()) != 1) {
                                             ack.setResultCd("9117");
                                             if (isEng) {
                                                ack.setResultMsg("[9117] Error in payment request data. \\n\\n noIntFlag check requested");
                                             } else {
                                                ack.setResultMsg("[9117] 결제요청자료 오류입니다. \\n\\n noIntFlag[무이자여부]  확인 요망");
                                             }

                                             fm.errorln("Error : noIntFlag[무이자여부]  확인 요망- [" + ack.getNoIntFlag() + "] ");
                                             fm.close();
                                             return ack;
                                          }

                                          if (CommonUtil.strLength(ack.getInterest()) != 2) {
                                             ack.setResultCd("9120");
                                             if (isEng) {
                                                ack.setResultMsg("[9120] Error in payment request data. \\n\\n interest check requested");
                                             } else {
                                                ack.setResultMsg("[9120] 결제요청자료 오류입니다. \\n\\n interest[할부기간]  확인 요망");
                                             }

                                             fm.errorln("Error : interest[할부기간]  확인 요망- [" + ack.getInterest() + "] ");
                                             fm.close();
                                             return ack;
                                          }

                                          if (CommonUtil.strLength(ack.getPointDiv()) != 1) {
                                             ack.setResultCd("9103");
                                             if (isEng) {
                                                ack.setResultMsg("[9123] Error in payment request data. \\n\\n pointDiv check requested");
                                             } else {
                                                ack.setResultMsg("[9123] 결제요청자료 오류입니다. \\n\\n pointDiv[포인트사용여부]  확인 요망");
                                             }

                                             fm.errorln("Error : pointDiv[포인트사용여부]  확인 요망- [" + ack.getPointDiv() + "] ");
                                             fm.close();
                                             return ack;
                                          }

                                          if (!ack.getMode().equals("CN48") || CommonUtil.strLength(ack.getAutoBillDate()) >= 1 && CommonUtil.strLength(ack.getAutoBillDate()) <= 2) {
                                             break label280;
                                          }

                                          ack.setResultCd("9124");
                                          if (isEng) {
                                             ack.setResultMsg("[9124] Error in payment request data. \\n\\n autoBillDate check requested");
                                          } else {
                                             ack.setResultMsg("[9124] 결제요청자료 오류입니다. \\n\\n autoBillDate[자동결제시작일]  확인 요망");
                                          }

                                          fm.errorln("Error : autoBillDate[자동결제시작일]  확인 요망- [" + ack.getAutoBillDate() + "] ");
                                          fm.close();
                                          return ack;
                                       }

                                       ack.setResultCd("9122");
                                       if (isEng) {
                                          ack.setResultMsg("[9122] Error in payment request data. \\n\\n socialNo check requested");
                                       } else {
                                          ack.setResultMsg("[9122] 결제요청자료 오류입니다. \\n\\n socialNo[생년월일]  확인 요망");
                                       }

                                       fm.errorln("Error : socialNo[생년월일]  확인 요망- [" + ack.getSocialNo() + "] ");
                                       fm.close();
                                       return ack;
                                    }

                                    ack.setResultCd("9119");
                                    if (isEng) {
                                       ack.setResultMsg("[9119] Error in payment request data. \\n\\n exprDt check requested");
                                    } else {
                                       ack.setResultMsg("[9119] 결제요청자료 오류입니다. \\n\\n exprDt[유효년월]  확인 요망");
                                    }

                                    fm.errorln("Error : exprDt[유효년월]  확인 요망- [" + ack.getExprDt() + "] ");
                                    fm.close();
                                    return ack;
                                 }

                                 ack.setResultCd("9118");
                                 if (isEng) {
                                    ack.setResultMsg("[9118] Error in payment request data. \\n\\n cardNo check requested");
                                 } else {
                                    ack.setResultMsg("[9118] 결제요청자료 오류입니다. \\n\\n cardNo[카드번호]  확인 요망");
                                 }

                                 fm.errorln("Error : cardNo[카드번호]  확인 요망- [" + ack.getCardNo() + "] ");
                                 fm.close();
                                 return ack;
                              }

                              ack.setResultCd("9125");
                              if (isEng) {
                                 ack.setResultMsg("[9125] Error in payment request data. \\n\\n prdtNm check requested");
                              } else {
                                 ack.setResultMsg("[9125] 결제요청자료 오류입니다. \\n\\n prdtNm[상품명]  확인 요망");
                              }

                              fm.errorln("Error : prdtNm[상품명]  확인 요망- [" + ack.getPrdtNm() + "] ");
                              fm.close();
                              return ack;
                           }
                        }

                        fm.close();
                        return ack;
                     }
                  } else {
                     ack.setResultCd("9110");
                     if (isEng) {
                        ack.setResultMsg("[9110] Error in payment request data. \\n\\n prdtPrice check requested ");
                     } else {
                        ack.setResultMsg("[9110] 결제요청자료 오류입니다. \\n\\n prdtPrice[거래금액] 확인 요망");
                     }

                     fm.errorln("Error : prdtPrice[거래금액] 확인 요망- [" + ack.getPrdtPrice() + "] ");
                     fm.close();
                     return ack;
                  }
               } else {
                  ack.setResultCd("9109");
                  if (isEng) {
                     ack.setResultMsg("[9109] Error in payment request data. \\n\\n mobilId check requested ");
                  } else {
                     ack.setResultMsg("[9109] 결제요청자료 오류입니다. \\n\\n mobilId[모빌리언스거래번호] 확인 요망");
                  }

                  fm.errorln("Error : mobilId[모빌리언스거래번호] 확인 요망- [" + ack.getMobilId() + "] ");
                  fm.close();
                  return ack;
               }
            } else {
               ack.setResultCd("9107");
               if (isEng) {
                  ack.setResultMsg("[9107] Error in payment request data. \\n\\n tradeId check requested ");
               } else {
                  ack.setResultMsg("[9107] 결제요청자료 오류입니다. \\n\\n tradeId[거래번호] 확인 요망");
               }

               fm.errorln("Error : tradeId[거래번호] 확인 요망- [" + ack.getTradeId() + "] ");
               fm.close();
               return ack;
            }
         } else {
            ack.setResultCd("9103");
            if (isEng) {
               ack.setResultMsg("[9103] Error in payment request data. \\n\\n svcId check requested");
            } else {
               ack.setResultMsg("[9103] 결제요청자료 오류입니다. \\n\\n svcId[서비스아이디]  확인 요망");
            }

            fm.errorln("Error : svcId[서비스아이디]  확인 요망- [" + ack.getSvcId() + "] ");
            fm.close();
            return ack;
         }
      } else {
         ack.setResultCd("9102");
         if (isEng) {
            ack.setResultMsg("[9102] Error in payment request data. \\n\\n recordKey[URL] check requested");
         } else {
            ack.setResultMsg("[9102] 결제요청자료 오류입니다. \\n\\n recordKey[URL] 확인 요망");
         }

         fm.errorln("Error : recordKey[URL] 확인 요망- [" + ack.getRecordKey() + "] ");
         fm.close();
         return ack;
      }
   }

   public void writeClientLog(AckParam ack) throws UnsupportedEncodingException {
      FileMaker fm = new FileMaker(this.configPath, this.logDir);
      StringBuilder sb = new StringBuilder();
      sb.append("iNFO : SEND DATA ===> mode[" + ack.getMode() + "] ");
      sb.append("recordKey[" + ack.getRecordKey() + "] ");
      sb.append("svcId[" + ack.getSvcId() + "] ");
      sb.append("mobilId[" + ack.getMobilId() + "] ");
      sb.append("tradeId[" + ack.getTradeId() + "] ");
      sb.append("prdtPrice[" + ack.getPrdtPrice() + "] ");
      sb.append("billKey[" + ack.getBillKey() + "] ");
      sb.append("prdtNm[" + ack.getPrdtNm() + "] ");
      sb.append("authType[" + ack.getAuthType() + "] ");
      sb.append("currency[" + ack.getCurrency() + "] ");
      sb.append("taxDiv[" + ack.getTaxDiv() + "] ");
      sb.append("taxPrice[" + ack.getTaxPrice() + "] ");
      sb.append("taxFreePrice[" + ack.getTaxFreePrice() + "] ");
      sb.append("nointFlag[" + ack.getNoIntFlag() + "] ");
      sb.append("cardNo[" + ack.getCardNo() + "] ");
      sb.append("exprDt[" + ack.getExprDt() + "] ");
      sb.append("interest[" + ack.getInterest() + "] ");
      sb.append("cardPw[" + ack.getCardPw() + "] ");
      sb.append("socialNo[" + ack.getSocialNo() + "] ");
      sb.append("pointDiv[" + ack.getPointDiv() + "] ");
      sb.append("userId[" + ack.getUserId() + "] ");
      sb.append("userNm[" + ack.getUserNm() + "] ");
      sb.append("userEmail[" + ack.getUserEmail() + "] ");
      sb.append("userIp[" + ack.getUserIp() + "] ");
      sb.append("autoBillDate[" + ack.getAutoBillDate() + "] ");
      sb.append("autoBillEndDate[" + ack.getAutoBillEndDate() + "] ");
      fm.writeln(sb.toString());
      fm.close();
   }

   public void writeServerLog(AckParam ack) {
      FileMaker fm = new FileMaker(this.configPath, this.logDir);
      StringBuilder sb = new StringBuilder();
      sb.append("iNFO : RECV DATA ===> mode[" + ack.getMode() + "] ");
      sb.append("resultCd[" + ack.getResultCd() + "] ");
      sb.append("resultMsg[" + ack.getResultMsg() + "] ");
      sb.append("recordKey[" + ack.getRecordKey() + "] ");
      sb.append("svcId[" + ack.getSvcId() + "] ");
      sb.append("mobilId[" + ack.getMobilId() + "] ");
      sb.append("tradeId[" + ack.getTradeId() + "] ");
      sb.append("prdtPrice[" + ack.getPrdtPrice() + "] ");
      sb.append("signDate[" + ack.getSignDate() + "] ");
      sb.append("interest[" + ack.getInterest() + "] ");
      sb.append("cardCode[" + ack.getCardCode() + "] ");
      sb.append("cardName[" + ack.getCardName() + "] ");
      sb.append("apprNo[" + ack.getApprNo() + "] ");
      sb.append("cardNum[" + ack.getCardNum() + "] ");
      fm.writeln(sb.toString());
      fm.close();
   }
}
    