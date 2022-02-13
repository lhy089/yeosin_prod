package com.yeosin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yeosin.board.FileDto;

@Controller
public class FileController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/download", method=RequestMethod.GET)
	public void downloadFile(FileDto fileInfo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(FileController.class.getResource("").getPath()); 
		try {
			// 다운로드 받을 파일명을 가져온다.
			String fileName = fileInfo.getLocalFileName();
			String encordedFilename = URLEncoder.encode(fileName,"UTF-8").replace("+", "%20");
			// 다운로드 경로 (내려받을 파일경로를 설정한다.)
			String filePath = "C:\\apache-tomcat-8.5.75\\webapps\\ROOT\\resources\\boardFile\\"+fileName;
			//String filePath = request.getServletContext().getRealPath("\\resources\\boardFile\\" + fileName);
			System.out.println("filePath : " + filePath);
			
			// 경로와 파일명으로 파일 객체를 생성한다.
			File file  = new File(filePath);
			// 파일 길이를 가져온다.

			int fSize = fileInfo.getFileSize();
			
			// 파일이 존재
			if (fSize > 0) {
				
				if (file.exists() && file.isFile()) {
					response.setContentType("application/octet-stream; charset=utf-8");
					response.setContentLength((int) file.length());
					response.setHeader("Content-Disposition","attachment;filename=" + encordedFilename + ";filename*= UTF-8''" + encordedFilename);
	                response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
					response.setHeader("Content-Transfer-Encoding", "binary");
					OutputStream out = response.getOutputStream();
					FileInputStream fis = null;
					fis = new FileInputStream(file);
					FileCopyUtils.copy(fis, out);
					if (fis != null)
						fis.close();
					out.flush();
					out.close();
				}
		    } else {
		    	throw new FileNotFoundException("파일이 없습니다.");
		    }
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
 	}
}
