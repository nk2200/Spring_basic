package com.example.myapp.upload.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.myapp.upload.service.IUploadFileService;

@Controller
public class UploadFileController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IUploadFileService service;
	
	
}
