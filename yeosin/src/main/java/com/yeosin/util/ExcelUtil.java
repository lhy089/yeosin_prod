/*
 * Excel Download
 */
package com.yeosin.util;

import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderFormatting;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ExcelUtil {

	/**
	 * EXCEL파일을 다운로드함.
	 *
	 * @param wb the wb
	 */
	public static void downLoadFile(HSSFWorkbook wb, String fileName, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OutputStreamWriter out = null;
		try {
			if("".equals(fileName)) {
				fileName = "리스트";
			}
			if (wb == null || "".equals(fileName)) {
				System.out.println("엑셀 파일 다운로드에 실패했습니다.");
				throw new NullPointerException("");
			}
			
			String strClient = request.getHeader("user-agent");
			
			if(strClient==null){ 
			    strClient = request.getParameter("userAgent");
			}
			
			String resCharset = "utf-8";
			if ( ( strClient !=null && strClient.indexOf("MSIE") > -1 ) ||  ( strClient !=null && strClient.indexOf("rv:11.0")  >= 0) ) {
				if(! "utf-8".equalsIgnoreCase(resCharset)){
					Pattern kp = Pattern.compile("[\uac00-\ud7af\u1100-\u11ff]");
					Pattern cp = Pattern.compile("[\u3040-\u318f\u3100-\u312f\u3040-\u309F\u30A0-\u30FF\u31F0-\u31FF\u3300-\u337f\u3400-\u4dbf\u4e00-\u9fff\uf900-\ufaff\uff65-\uff9f]");
					Matcher km = kp.matcher(fileName);
					Matcher cm = cp.matcher(fileName);

					if (km.find() && !  cm.find()){
						request.setCharacterEncoding("euc-kr");
						//response.setCharacterEncoding(resCharset);
						fileName = new String(fileName.getBytes("euc-kr"), resCharset); //iso-8859-1,MS949,8859_1,Cp970
					}else{
						request.setCharacterEncoding("utf-8");
						//response.setCharacterEncoding("utf-8");
						fileName = java.net.URLEncoder.encode(fileName, "utf-8").replace('+',' ');
					}
				}else{
					request.setCharacterEncoding("utf-8");
					//response.setCharacterEncoding("utf-8");
					fileName = java.net.URLEncoder.encode(fileName, "utf-8").replace('+',' ');
				}
			}else if (strClient.indexOf("Chrome") > -1) { //크롬
	             StringBuffer sb = new StringBuffer();
	             for (int i = 0; i < fileName.length(); i++) {
	                 char c = fileName.charAt(i);
	                 if (c > '~') {
	                     sb.append(java.net.URLEncoder.encode("" + c, "UTF-8"));
	                 } else {
	                     sb.append(c);
	                 }
	             }
	             fileName = sb.toString();
			}else{ //기타(사파리,파이어 폭스,오페라)
				fileName = new String(fileName.getBytes("utf-8"), "latin1");
			}
			
			// 클라이언트 리스펀스 설정
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xls");
			response.setContentType("application/vnd.ms-excel");
			out = new OutputStreamWriter(response.getOutputStream(), "EUC-KR");

			wb.write(response.getOutputStream()); // 엑셀파일을 출력.
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != out) {
				out.close();
			}
		}
	}
	
	public static HSSFWorkbook excelDownloadByDetailList(String column, String data) throws Exception  {
		HSSFWorkbook wb = new HSSFWorkbook();

		   Sheet s = wb.createSheet();
		   Row r = null; 
		   Cell c = null;
		   // create styles
		   CellStyle cellStyle = wb.createCellStyle();
		   CellStyle headerCellStyle = wb.createCellStyle();
		   // create 2 fonts objects
		   Font defaultFont = wb.createFont();
		   Font headerFont = wb.createFont();
		   DataFormat df = wb.createDataFormat();

		   // Set font 2 to 10 point type, red and bold
		   headerFont.setFontHeightInPoints((short) 12);
		   headerFont.setColor( IndexedColors.BLACK.getIndex() );
		   headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		   headerFont.setFontName("돋움");
		   
		   defaultFont.setFontHeightInPoints((short) 12);
		   defaultFont.setColor( IndexedColors.BLACK.getIndex() );
		   defaultFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		   defaultFont.setFontName("돋움");
		   
		   // Set cell style and formatting	   
		   cellStyle.setDataFormat(df.getFormat("text"));
		   cellStyle.setFont(defaultFont);
		   cellStyle.setBorderBottom(BorderFormatting.BORDER_THIN);
		   cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		   cellStyle.setBorderRight(BorderFormatting.BORDER_THIN);
		   
		   headerCellStyle.setDataFormat(df.getFormat("text"));
		   headerCellStyle.setFont(headerFont);
		   headerCellStyle.setBorderBottom(BorderFormatting.BORDER_DOUBLE);
		   headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		   headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		   headerCellStyle.setBorderRight(BorderFormatting.BORDER_THIN);
		   /*headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		   headerCellStyle.setFillBackgroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);*/
		  
		   String[] columns = column.split(",");
		   String[] dataList = data.split("▧");
		   
		   r = s.createRow(0);
		   for(int i=0; i < columns.length; i++){
			   r.setHeightInPoints((2*s.getDefaultRowHeightInPoints()));
			   c = r.createCell(i);
			   c.setCellStyle(headerCellStyle);
			   c.setCellValue(columns[i]);
		   }
		   
		   for(int i=0; i < dataList.length; i++){
			   String[] cols = dataList[i].split("▒");
			   r = s.createRow(i+1);
			   for(int j=0; j < cols.length; j++){
				   r.setHeightInPoints((2*s.getDefaultRowHeightInPoints()));
				   c = r.createCell(j);
				   c.setCellStyle(cellStyle);
				   c.setCellValue(cols[j]);
			   }
		   }
		   
		   return wb;
	}
}
