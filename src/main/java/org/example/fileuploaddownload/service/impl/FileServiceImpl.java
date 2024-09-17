package org.example.fileuploaddownload.service.impl;

import org.example.fileuploaddownload.dto.ApiResponse;
import org.example.fileuploaddownload.dto.FileDto;
import org.example.fileuploaddownload.model.FileEntity;
import org.example.fileuploaddownload.repository.FileRepository;
import org.example.fileuploaddownload.service.FileService;
import org.example.fileuploaddownload.service.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Component
public class FileServiceImpl implements FileService {
    @Autowired
    private final FileRepository fileRepository;
    @Autowired
    private final FileMapper fileMapper;

    public FileServiceImpl(FileRepository fileRepository, FileMapper fileMapper) {
        this.fileRepository = fileRepository;
        this.fileMapper = fileMapper;
    }

    public ApiResponse<FileDto> result(FileDto dto){
        return ApiResponse.<FileDto>builder()
                .code(0)
                .count(this.fileRepository.fayllar().size())
                .success(true)
                .message("OK")
                .content(dto)
                .build();
    }
    public ApiResponse<List<FileDto>> results(List<FileDto> dtos){
        return ApiResponse.<List<FileDto>>builder()
                .code(0)
                .count(this.fileRepository.fayllar().size())
                .success(true)
                .message("OK")
                .content(dtos)
                .build();
    }
    public ApiResponse<FileDto>errorDB(Exception e){
        return ApiResponse.<FileDto>builder()
                .code(-3)
                .count(0)
                .success(false)
                .message("error database ->"+e.getMessage())
                .build();
    }
    public ApiResponse<FileDto>notFound(){
        return ApiResponse.<FileDto>builder()
                .code(-1)
                .count(this.fileRepository.fayllar().size())
                .success(false)
                .message("not found")
                .build();
    }
    public ApiResponse<List<FileDto>>errorDBs(Exception e){
        return ApiResponse.<List<FileDto>>builder()
                .code(-3)
                .count(0)
                .success(false)
                .message("error database ->"+e.getMessage())
                .build();
    }
    public ApiResponse<List<FileDto>>notFounds(){
        return ApiResponse.<List<FileDto>>builder()
                .code(-1)
                .count(this.fileRepository.fayllar().size())
                .success(false)
                .message("files is empty")
                .build();
    }


    @Override
    public ApiResponse<FileDto> uploadFile(MultipartFile file) {
        try {
            String name = this.fileMapper.convertKrillToLatin(Objects.requireNonNull(file.getOriginalFilename()));
            FileEntity entity= this.fileMapper.toEntity(
              FileDto.builder()
                      .name(name)
                      .type(file.getContentType())
                      .content(file.getBytes())
                      .build()
            );
            entity.setCreatedAt(LocalDateTime.now());
            FileEntity save = this.fileRepository.save(entity);
            return result(fileMapper.toDto(save));
        }catch (Exception e){
            return errorDB(e);
        }
    }



    @Override
    public ApiResponse<FileDto> deleteFile(Integer id) {
        try {
            if (this.fileRepository.fayllar().isEmpty()){
                return notFound();
            }
            FileEntity fayl = this.fileRepository.fayl(id);
            fayl.setDeletedAt(LocalDateTime.now());
            FileEntity save = this.fileRepository.save(fayl);
            return result(fileMapper.toDto(save));
        }catch (Exception e){
            return errorDB(e);
        }
    }

    @Override
    public ApiResponse<List<FileDto>> getFiles() {
        try {
            if (this.fileRepository.fayllar().isEmpty()){
                return notFounds();
            }
            return results(fileMapper.toDtos(this.fileRepository.fayllar()));
        }catch (Exception e){
            return errorDBs(e);
        }
    }

    @Override
    public ApiResponse<FileDto> getFile(Integer id) {
        try {
            if (this.fileRepository.fayllar().isEmpty()){
                return notFound();
            }
            return result(fileMapper.toDto(this.fileRepository.fayl(id)));
        }catch (Exception e){
            return errorDB(e);
        }
    }
}
