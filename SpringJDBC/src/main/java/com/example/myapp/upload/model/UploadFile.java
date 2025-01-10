package com.example.myapp.upload.model;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter
@ToString(exclude= {"fileData"}) //filedata 포함하면 디버깅 시간 오래걸림
public class UploadFile {
	private	int	fileId;
	private	String	categoryName;
	private	String	fileName;
	private String uuidFileName;
	private	long	fileSize;
	private	String	fileContentType;
	private	Timestamp fileUploadDate;
//	private	byte[]	fileData;
}
