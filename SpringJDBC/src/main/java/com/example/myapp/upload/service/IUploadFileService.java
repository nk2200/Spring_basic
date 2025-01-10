package com.example.myapp.upload.service;

import java.util.List;

import com.example.myapp.upload.model.UploadFile;

public interface IUploadFileService {
	void uploadFile(UploadFile file);
	List<UploadFile> getAllFileList();
	UploadFile getFile(int fileId);
	void deleteFile(int fileId);
	String getUuidFileName(int fileId);
}
