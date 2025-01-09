package com.example.myapp.upload.dao;

import java.util.List;

import com.example.myapp.upload.model.UploadFile;

public interface IUploadFileRepository {

	int getMaxFileId();

	void uploadFile(UploadFile file);

	List<UploadFile> getAllFileList();

	UploadFile getFile(int fileId);

	void deleteFile(int fileId);
}
