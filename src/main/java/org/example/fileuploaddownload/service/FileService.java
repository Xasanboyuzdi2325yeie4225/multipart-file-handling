package org.example.fileuploaddownload.service;

import org.example.fileuploaddownload.dto.ApiResponse;
import org.example.fileuploaddownload.dto.FileDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface FileService {
    ApiResponse<FileDto>uploadFile(MultipartFile file);
//    ApiResponse<FileDto>downloadFile(Integer id);
    ApiResponse<FileDto>deleteFile(Integer id);
    ApiResponse<List<FileDto>>getFiles();
    ApiResponse<FileDto> getFile(Integer id);
}
