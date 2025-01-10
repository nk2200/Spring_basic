package com.example.myapp.upload.controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.myapp.upload.model.UploadFile;
import com.example.myapp.upload.service.IUploadFileService;

@Controller
public class UploadFileController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	IUploadFileService service;

	@GetMapping("/file/new")
	public String uploadFile() {
		logger.info("파일 입력	양식을 요청합니다.");
		return "file/form";
	}
	
	//파일 업로드
	@PostMapping("/file/new")
	public String uploadFile(String category, MultipartFile mfile, HttpSession session) {
		logger.info(category);
		logger.info(mfile.getOriginalFilename());
		//서버의 어플리케이션에 배포되는 디렉토리에 파일 저장-> 절대경로 알아와서 저장
		System.out.println(session.getServletContext().getRealPath("/upload"));
//		String uploadDir = session.getServletContext().getRealPath("/upload");
		String uploadDir = "C:/dev/workspace/upload";
		try {
			if (mfile != null && !mfile.isEmpty()) {
				String fileName = mfile.getOriginalFilename();
				String fileExt = fileName.substring(fileName.lastIndexOf("."));
				UUID uuid = UUID.randomUUID();
				String uuidFileName = uuid+fileExt; //uuid.파일확장자명 이렇게 uuidFileName생성
				
				File saveFilePath = new File(uploadDir, uuidFileName);
				mfile.transferTo(saveFilePath); //mfile에서 saveFilePath로 파일 내보내기(파일저장)
				
				//파일 기본정보 uploadfile 객체에 저장
				UploadFile newFile = new UploadFile();
				newFile.setCategoryName(category);
				newFile.setFileName(mfile.getOriginalFilename());
				newFile.setUuidFileName(uuidFileName);
				newFile.setFileSize(mfile.getSize());
				newFile.setFileContentType(mfile.getContentType());
				service.uploadFile(newFile);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "redirect:/file/list";
	}

	@GetMapping("/file/list")
	public String getFileList(Model model) {
		List<UploadFile> fileList = service.getAllFileList();
		model.addAttribute("fileList", fileList);
		return "file/list";
	}
	
	//파일 다운로드
	//FileInputStream을 이용해서 파일의 바이트 정보를 읽어들여야함
	@GetMapping("/file/{fileId}")
	  public ResponseEntity<byte[]>	getBinaryFile(@PathVariable	int	fileId)	{
		  UploadFile file =	service.getFile(fileId);
		  if(file!=null) {
			  FileInputStream fis = null;
			  DataInputStream dis = null;
			  
			  try {
				  String uuidFileName = file.getUuidFileName();
				  File downFile = new File("C:/dev/workspace/upload/", uuidFileName);
				  fis = new FileInputStream(downFile);
				  byte[] buffer = new byte[fis.available()];
				  dis = new DataInputStream(fis);
				  dis.readFully(buffer);
				  HttpHeaders headers = new HttpHeaders();
				  String[] mtype = file.getFileContentType().split("/");
				  headers.setContentType(new MediaType(mtype[0],mtype[1]));
				  headers.setContentLength(file.getFileSize());
				  try {
					  String encFileName = URLEncoder.encode(file.getFileName(),"UTF-8");
					  headers.setContentDispositionFormData("attachment", encFileName);
					  
				  }catch(UnsupportedEncodingException e) {
					  throw new RuntimeException(e);
				  }
				  return new ResponseEntity<byte[]>(buffer,headers,HttpStatus.OK);
			  }catch(Exception e) {
				  return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
			  }finally {
				  try {dis.close();}catch(Exception e) {}
			  }
	 
		}else	{
		 return	new	ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		  
		}
	  
	}

	//파일 삭제 - 파일정보도 삭제, 실제 파일도 삭제
	@GetMapping("/file/delete/{fileId}")
	public String deleteFile(@PathVariable int fileId) {
		String uploadDir = "C:/dev/workspace/upload/";
		String uuidFileName = service.getUuidFileName(fileId);
		File file = new File(uploadDir, uuidFileName);
		boolean isDeleted = file.delete(); //실제 파일삭제
		if(isDeleted) {
			//실제 파일 삭제 됬으면 테이블의 정보도 삭제
			service.deleteFile(fileId);
		}else {
			throw new RuntimeException("파일이 삭제되지 않았습니다.");
		}
		return "redirect:/file/list";
	}
}
