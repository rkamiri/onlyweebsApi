package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Image;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Iterator;

@RestController
@RequestMapping(value = "/upload")
public class ImageController {

    public final UploadService uploadService;

    @Autowired
    public ImageController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/image/{userid}")
    public void UploadFile(MultipartHttpServletRequest request, @PathVariable Long userid) throws IOException {
        Iterator<String> itr = request.getFileNames();
        MultipartFile file = request.getFile(itr.next());
        this.uploadService.saveImage(file, userid);
    }

    @PostMapping("/article-image")
    public void UploadArticleImage(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> itr = request.getFileNames();
        MultipartFile file = request.getFile(itr.next());
        this.uploadService.saveArticleImage(file);
    }

    @GetMapping("/image/{id}")
    public Image downloadImage(@PathVariable(value = "id") Long imageId) {
        return uploadService.findById(imageId);
    }
}
