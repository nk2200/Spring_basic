package com.example.myapp.upload.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myapp.upload.dao.IUploadFileRepository;
import com.example.myapp.upload.model.UploadFile;

@Service
public class UploadFileService implements IUploadFileService{

	@Autowired
	IUploadFileRepository dao;
	@Override
	public void uploadFile(UploadFile file) {
		int fileId = dao.getMaxFileId();
		file.setFileId(fileId);
		dao.uploadFile(file);
	}

	@Override
	public List<UploadFile> getAllFileList() {
		return dao.getAllFileList();
	}

	@Override
	public UploadFile getFile(int fileId) {
		return dao.getFile(fileId);
	}

	@Override
	public void deleteFile(int fileId) {
		dao.deleteFile(fileId);
	}

}
