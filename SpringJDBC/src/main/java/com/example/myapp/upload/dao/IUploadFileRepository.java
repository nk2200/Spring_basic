package com.example.myapp.upload.dao;

import java.util.List;

import com.example.myapp.upload.model.UploadFile;

public interface IUploadFileRepository {

	int getMaxFileId(); //파일 아이디 증가위해

	void uploadFile(UploadFile file);

	List<UploadFile> getAllFileList();

	UploadFile getFile(int fileId);

	void deleteFile(int fileId);
	String getUuidFileName(int fileId);
}
