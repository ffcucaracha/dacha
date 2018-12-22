package hello.dao;

import hello.model.FilesUpload;

import java.util.List;

public interface FilesUploadDAO {
	void save(FilesUpload filesUpload);
	FilesUpload getFileById(int id);
	List<FilesUpload> getAllFilesFromPaper(int paper_id);
}
