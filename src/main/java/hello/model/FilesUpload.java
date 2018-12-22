package hello.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FILES_UPLOAD")
public class FilesUpload {
	@Id
	@Column(name = "upload_id")
	private int upload_id;

	@Column(name = "paper_id")
	private int paper_id;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_data")
	private byte[] data;

	public long getUpload_id() {
		return upload_id;
	}

	public void setUpload_id(int id) {
		this.upload_id = id;
	}

	public long getPaper_id() {
		return paper_id;
	}

	public void setPaper_id(int id) {
		this.paper_id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getDataType()
	{
		String type = fileName.split("\\.")[1];
		switch(type) {
			case "png":
			case "PNG":
				return "image/png";
			case "jpg":
			case "JPEG":
			case "jpeg":
				return "image/jpg";
			case "pdf":
				return "multipart/mixed";
			default:
				return "multipart/mixed";
		}
	}

	@Override
	public String toString()
	{
		return this.fileName + " тип: " + getDataType();
	}

}
