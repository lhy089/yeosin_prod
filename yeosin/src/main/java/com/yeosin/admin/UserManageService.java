package com.yeosin.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yeosin.apply.ApplyDto;
import com.yeosin.apply.ExamDto;
import com.yeosin.apply.GradeDto;
import com.yeosin.user.EduCompletionDto;
import com.yeosin.user.EduCompletionHisDto;
import com.yeosin.user.UserDto;

@Service
public class UserManageService {

	@Autowired	
	private UserManageDao userManageDao;
	
	@Autowired	
	private ApplyManageDao applyManageDao;
	
	
	// 지역 리스트 조회(조회조건)
	public List<UserDto> getUserInfo(UserDto userDto) throws Exception {
		return userManageDao.getUserInfo(userDto);
	}
	
	public int countUserListTotal(UserDto userDto) throws Exception{
		return userManageDao.countUserListTotal(userDto);
	}
	
	public List<EduCompletionDto> getEduCompletionList(EduCompletionDto eduCompletionInfo) throws Exception {
		return userManageDao.getEduCompletionList(eduCompletionInfo);
	}
	
	public int insertEduCompletionHis(EduCompletionHisDto eduCompletionHis) throws Exception {
		return userManageDao.insertEduCompletionHis(eduCompletionHis);
	}
	
	public List<EduCompletionHisDto> getEduCompletionHisList() throws Exception {
		return userManageDao.getEduCompletionHisList();
	}
	
	//관리자 회원 수정
	public UserDto getUserInfoByUserId(String userId) throws Exception{
		return userManageDao.getUserInfoByUserId(userId);
	}
	
	//TODO :: 함수 위치 변경, 로직 나누기
	public String xlsxExcelReader(MultipartFile file, String examId) throws Exception{
		// 반환할 객체를 생성
		List<ApplyDto> list = new ArrayList<>();
		String result = "";

		XSSFWorkbook workbook = null;
		List<GradeDto> gradeList = new ArrayList<GradeDto>();

		try {
			// HSSFWorkbook은 엑셀파일 전체 내용을 담고 있는 객체
			workbook = new XSSFWorkbook(file.getInputStream());

			// 탐색에 사용할 Sheet, Row, Cell 객체
			XSSFSheet curSheet;
			XSSFRow curRow;
			XSSFCell curCell;
			ApplyDto excelData;

			// Sheet 탐색 for문
			for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
				// 현재 sheet 반환
				curSheet = workbook.getSheetAt(sheetIndex);
				// row 탐색 for문
				for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
					// row 0은 헤더정보이기 때문에 무시
					if (rowIndex != 0) {
						System.out.println("rowIndex :::::::::::: " + rowIndex);
						curRow = curSheet.getRow(rowIndex);
						excelData = new ApplyDto();
						excelData.setUserDto(new UserDto());
						excelData.setGradeDto(new GradeDto());
						String value;

						// row의 첫번째 cell값이 비어있지 않는 경우만 cell탐색
						if (curRow.getCell(0) != null) {
							if (!"".equals(curRow.getCell(0).toString())) {
								// cell 탐색 for문
								for (int cellIndex = 0; cellIndex < curRow.getLastCellNum(); cellIndex++) {
									System.out.println("curRow.getLastCellNum() :: " + curRow.getLastCellNum());
									curCell = curRow.getCell(cellIndex);
									if(curCell == null) continue;
									if (true) {
										value = "";
										// cell 스타일이 다르더라도 String으로 반환 받음
										switch (curCell.getCellType()) {
										case HSSFCell.CELL_TYPE_FORMULA:
											value = curCell.getCellFormula();
											break;
										case HSSFCell.CELL_TYPE_NUMERIC:
											curCell.setCellType(HSSFCell.CELL_TYPE_STRING);
											value = curCell.getStringCellValue();
											break;
										case HSSFCell.CELL_TYPE_STRING:
											value = curCell.getStringCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_BLANK:
											value = curCell.getBooleanCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_ERROR:
											value = curCell.getErrorCellValue() + "";
											break;
										default:
											value = new String();
											break;
										} // end switch
										System.out.println("curCell.getCellType() :: " + curCell.getCellType() + ", cellIndex :: " + cellIndex + ", value :: " + value);
										// 현재 colum index에 따라서 vo입력
										switch (cellIndex) {
										case 3: // 수험번호
											excelData.setStudentCode(value);
											break;
										case 4: // 이름
											excelData.getUserDto().setUserName(value);
											break;
										case 5: // 생년월일
											excelData.getUserDto().setBirthDate(value);
											break;
										case 6: // 평가종류
											excelData.setSubjectId(value.contains("대출") ? "LP01" : "LS01");
											break;
										case 7: // 점수
											excelData.getGradeDto().setAllScore(Double.parseDouble(value));
											break;
										case 8: // 합격여부
											excelData.getGradeDto().setIsPass(excelData.getGradeDto().getAllScore()>=60 ? "Y" : "N");
											System.out.println(">> score :: " + excelData.getGradeDto().getAllScore() + ", isPass :: " + excelData.getGradeDto().getIsPass());
											break;
										default:
											break;
										}
									}
								}
								list.add(excelData);
							}
						}
					}

				}
			}
			
			ExamDto examInfo = applyManageDao.getExamInfoByExamId(examId);
			String passCertIdForm = examInfo.getExamYear().substring(2) + String.format("%02d", Integer.parseInt(examInfo.getExamDegree()));
			int cnt = 1;

			System.out.println(">> passCertIdForm :::::::::::: " + passCertIdForm);
			for(ApplyDto excel : list) {
				String passCertId = passCertIdForm;
				GradeDto gradeInfo = new GradeDto();
				gradeInfo.setReceiptId(excel.getStudentCode());
				gradeInfo.setIsQuit("");
				gradeInfo.setAllScore(excel.getGradeDto().getAllScore());
				gradeInfo.setIsPass(excel.getGradeDto().getIsPass());
				gradeInfo.setIsQual("Y");
				gradeInfo.setPassCertId("");
				if("Y".equals(excel.getGradeDto().getIsPass())) {
					if("LP01".equals(excel.getSubjectId())) passCertId += "1";
					else passCertId += "2";
					
					passCertId += String.format("%05d", cnt++);
					gradeInfo.setPassCertId(passCertId);
				}
//				System.out.println("cnt ::: " + cnt + ", gradeInfo.getReceiptId :::::::::::: " + gradeInfo.getReceiptId());
				gradeList.add(gradeInfo);
			}
			
			System.out.println("gradeList.size :: " + gradeList.size());
			Map<String,String> map = new HashMap<>();
			map.put("examId", examId);
			map.put("gradeStatus", "Y");
			applyManageDao.insertExcelData(gradeList);
			applyManageDao.updateGradeStatus(map);
			result = "SUCCESS";
		} catch (Exception e) {
			Map<String,String> map = new HashMap<>();
			map.put("examId", examId);
			map.put("gradeStatus", "N");
//			applyManageDao.deleteExcelData(gradeList);
			applyManageDao.updateGradeStatus(map);
			result = "FAILED";
			System.out.println(e.toString());
		}
		return result;
	}
	
	public String xlsExcelReader(MultipartFile file, String examId) throws Exception {
		// 반환할 객체를 생성
		List<ApplyDto> list = new ArrayList<>();
		String result = "";
		HSSFWorkbook workbook = null;
		List<GradeDto> gradeList = new ArrayList<GradeDto>();
		
		System.out.println(file.getContentType());
		try {
			// HSSFWorkbook은 엑셀파일 전체 내용을 담고 있는 객체
			workbook = new HSSFWorkbook(file.getInputStream());

			// 탐색에 사용할 Sheet, Row, Cell 객체
			HSSFSheet curSheet;
			HSSFRow curRow;
			HSSFCell curCell;
			ApplyDto excelData;

			// Sheet 탐색 for문
			for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
				// 현재 sheet 반환
				curSheet = workbook.getSheetAt(sheetIndex);
				// row 탐색 for문
				for (int rowIndex = 0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
					// row 0은 헤더정보이기 때문에 무시
					if (rowIndex != 0) {
						System.out.println("rowIndex :::::::::::: " + rowIndex);
						curRow = curSheet.getRow(rowIndex);
						excelData = new ApplyDto();
						excelData.setUserDto(new UserDto());
						excelData.setGradeDto(new GradeDto());
						String value;

						// row의 첫번째 cell값이 비어있지 않는 경우만 cell탐색
						if (curRow.getCell(0) != null) {
							if (!"".equals(curRow.getCell(0).toString())) {
								// cell 탐색 for문
								for (int cellIndex = 0; cellIndex < curRow.getLastCellNum(); cellIndex++) {
									System.out.println("curRow.getLastCellNum() :: " + curRow.getLastCellNum());
									curCell = curRow.getCell(cellIndex);
									if(curCell == null) continue;
									if (true) {
										value = "";
										// cell 스타일이 다르더라도 String으로 반환 받음
										switch (curCell.getCellType()) {
										case HSSFCell.CELL_TYPE_FORMULA:
											value = curCell.getCellFormula();
											break;
										case HSSFCell.CELL_TYPE_NUMERIC:
											curCell.setCellType(HSSFCell.CELL_TYPE_STRING);
											value = curCell.getStringCellValue();
											break;
										case HSSFCell.CELL_TYPE_STRING:
											value = curCell.getStringCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_BLANK:
											value = curCell.getBooleanCellValue() + "";
											break;
										case HSSFCell.CELL_TYPE_ERROR:
											value = curCell.getErrorCellValue() + "";
											break;
										default:
											value = new String();
											break;
										} // end switch
										System.out.println("curCell.getCellType() :: " + curCell.getCellType() + ", cellIndex :: " + cellIndex + ", value :: " + value);
										// 현재 colum index에 따라서 vo입력
										switch (cellIndex) {
										case 3: // 수험번호
											excelData.setStudentCode(value);
											break;
										case 4: // 이름
											excelData.getUserDto().setUserName(value);
											break;
										case 5: // 생년월일
											excelData.getUserDto().setBirthDate(value);
											break;
										case 6: // 평가종류
											excelData.setSubjectId(value.contains("대출") ? "LP01" : "LS01");
											break;
										case 7: // 점수
											excelData.getGradeDto().setAllScore(Double.parseDouble(value));
											break;
										case 8: // 합격여부
											excelData.getGradeDto().setIsPass(excelData.getGradeDto().getAllScore()>=60 ? "Y" : "N");
											System.out.println(">> score :: " + excelData.getGradeDto().getAllScore() + ", isPass :: " + excelData.getGradeDto().getIsPass());
											break;
										default:
											break;
										}
									}
								}
								list.add(excelData);
							}
						}
					}

				}
			}

			ExamDto examInfo = applyManageDao.getExamInfoByExamId(examId);
			String passCertIdForm = examInfo.getExamYear().substring(2) + String.format("%02d", Integer.parseInt(examInfo.getExamDegree()));
			int cnt = 1;

			System.out.println(">> passCertIdForm :::::::::::: " + passCertIdForm);
			for(ApplyDto excel : list) {
				String passCertId = passCertIdForm;
				GradeDto gradeInfo = new GradeDto();
				gradeInfo.setReceiptId(excel.getStudentCode());
				gradeInfo.setIsQuit("");
				gradeInfo.setAllScore(excel.getGradeDto().getAllScore());
				gradeInfo.setIsPass(excel.getGradeDto().getIsPass());
				gradeInfo.setIsQual("Y");
				gradeInfo.setPassCertId("");
				if("Y".equals(excel.getGradeDto().getIsPass())) {
					if("LP01".equals(excel.getSubjectId())) passCertId += "1";
					else passCertId += "2";

					passCertId += String.format("%05d", cnt++);
					gradeInfo.setPassCertId(passCertId);
				}
				//							System.out.println("cnt ::: " + cnt + ", gradeInfo.getReceiptId :::::::::::: " + gradeInfo.getReceiptId());
				gradeList.add(gradeInfo);
			}

			System.out.println("gradeList.size :: " + gradeList.size());
			Map<String,String> map = new HashMap<>();
			map.put("examId", examId);
			map.put("gradeStatus", "Y");
			applyManageDao.insertExcelData(gradeList);
			applyManageDao.updateGradeStatus(map);
			result = "SUCCESS";
		} catch (Exception e) {
			Map<String,String> map = new HashMap<>();
			map.put("examId", examId);
			map.put("gradeStatus", "N");
			//						applyManageDao.deleteExcelData(gradeList);
			applyManageDao.updateGradeStatus(map);
			result = "FAILED";
			System.out.println(e.toString());
		}
		return result;
	}
	
	

}

