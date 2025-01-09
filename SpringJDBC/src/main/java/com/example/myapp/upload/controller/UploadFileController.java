package com.example.myapp.upload.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

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
		logger.info("파일	입력	양식을	요청합니다.");
		return "file/form";
	}

	@PostMapping("/file/new")
	public String uploadFile(String category, MultipartFile mfile) {
		logger.info(category);
		logger.info(mfile.getOriginalFilename());
		try {
			if (mfile != null && !mfile.isEmpty()) {
				UploadFile newFile = new UploadFile();
				newFile.setCategoryName(category);
				newFile.setFileName(mfile.getOriginalFilename());
				newFile.setFileSize(mfile.getSize());
				newFile.setFileContentType(mfile.getContentType());
				newFile.setFileData(mfile.getBytes());
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

	@GetMapping("/file/{fileId}")
	  public	ResponseEntity<byte[]>	getBinaryFile(@PathVariable	int	fileId)	{
	  UploadFile	file	=	service.getFile(fileId);
	  if(file!=null)	{
	  HttpHeaders	headers	=  new HttpHeaders();
	  String[]	mtype = file.getFileContentType().split("/");
	  headers.setContentType(new MediaType(mtype[0],mtype[1]));
	  headers.setContentLength(file.getFileSize());
	 
	 
	try	{
	 String	fname	=	URLEncoder.encode(file.getFileName(),	"UTF-");
	  
	headers.setContentDispositionFormData("attachment",	fname);
	  
	}catch(UnsupportedEncodingException	e)	{
	  
	throw	new	RuntimeException(e);
	  
	}
	  
	return	new	ResponseEntity<byte[]>(file.getFileData(),	headers,	
	HttpStatus.OK);
	  
	 
	}else	{
	 return	new	ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
	  
	}
	  
	}

	@GetMapping("/file/delete/{fileId}")

	public String deleteFile(@PathVariable int fileId) {

		service.deleteFile(fileId);

		return "redirect:/file/list";
	}
}
