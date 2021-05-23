package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Image;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.ImageService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/image")
public class ImageController {

    public final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/user-image/{userid}")
    public void UploadFile(MultipartHttpServletRequest request, @PathVariable Long userid) throws IOException {
        Iterator<String> itr = request.getFileNames();
        MultipartFile file = request.getFile(itr.next());
        this.imageService.saveImage(file, userid);
    }

    @PostMapping("/article-image")
    public void UploadArticleImage(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> itr = request.getFileNames();
        MultipartFile file = request.getFile(itr.next());
        this.imageService.saveArticleImage(file);
    }

    @GetMapping("/image/{id}")
    public Image downloadImage(@PathVariable(value = "id") Long imageId) {
        return imageService.findById(imageId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImageContent(@PathVariable(value = "id") Long imageId) {
        CacheControl cacheControl = CacheControl.maxAge(3600000, TimeUnit.SECONDS).mustRevalidate();
        Image image = this.imageService.findById(imageId);
        val content = image.getContent();
        MediaType contentType = MediaType.valueOf("image/jpg");
        return ResponseEntity.status(200).contentType(contentType).cacheControl(cacheControl).body(content);
    }
}
