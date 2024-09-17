package org.example.fileuploaddownload.controller;

import org.example.fileuploaddownload.dto.ApiResponse;
import org.example.fileuploaddownload.dto.FileDto;
import org.example.fileuploaddownload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }
    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @PostMapping(value = "/upload")
    public String upload(@RequestParam("file") MultipartFile file, Model model) {
        ApiResponse<FileDto> fileDtoApiResponse = this.fileService.uploadFile(file);
        String msg=fileDtoApiResponse.getMessage();
        model.addAttribute("msg", msg);
        return "msg";
    }
    @GetMapping(value = "/get")
    public String get(Model model){
        ApiResponse<List<FileDto>> files = this.fileService.getFiles();
        List<FileDto> content = files.getContent();
        model.addAttribute("files", content);
        return "read";
    }
    @GetMapping(value = "/download")
    public ResponseEntity<Resource> download(Model model, @RequestParam("id")Integer id){

        try {
            ApiResponse<FileDto> file = this.fileService.getFile(id);
            ByteArrayResource resource=new ByteArrayResource(file.getContent().getContent());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+file.getContent().getName()+"\"")
                    .contentType(MediaType.parseMediaType(file.getContent().getType()))
                    .body(resource);

        }catch (Exception e){
            System.out.println("download error-> "+e.getMessage());
            return null;
        }
    }
    @PostMapping(value = "/delete")
    public String delete(@RequestParam("id")Integer id,Model model){
        ApiResponse<FileDto> fileDtoApiResponse = this.fileService.deleteFile(id);
        FileDto content = fileDtoApiResponse.getContent();
        String msg=fileDtoApiResponse.getMessage();
        model.addAttribute("msg", msg);
        return "msg";
    }
}
