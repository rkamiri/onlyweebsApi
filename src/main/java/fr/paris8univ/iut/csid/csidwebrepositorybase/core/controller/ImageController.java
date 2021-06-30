package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.ImageDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    public void uploadUserImage(MultipartHttpServletRequest request, @PathVariable Long userid) throws IOException {
        Iterator<String> itr = request.getFileNames();
        MultipartFile file = request.getFile(itr.next());
        this.imageService.saveImage(file, userid);
    }

    @PostMapping("/article-image")
    public void uploadArticleImage(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> itr = request.getFileNames();
        MultipartFile file = request.getFile(itr.next());
        this.imageService.saveArticleImage(file);
    }

    @GetMapping("/image/{id}")
    public ImageDto getImage(@PathVariable(value = "id") Long imageId) {
        return imageService.findById(imageId);
    }

    @GetMapping("/logo")
    public ResponseEntity<byte[]> getLogo() throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get("src/main/resources/ow-smol-text.webp"));
        CacheControl cacheControl = CacheControl.maxAge(3600000, TimeUnit.SECONDS).mustRevalidate();
        MediaType contentType = MediaType.valueOf("image/webp");
        return ResponseEntity.status(200).contentType(contentType).cacheControl(cacheControl).body(bytes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImageContent(@PathVariable(value = "id") Long imageId) {
        CacheControl cacheControl = CacheControl.maxAge(3600000, TimeUnit.SECONDS).mustRevalidate();
        ImageDto imageDto = this.imageService.findById(imageId);
        byte[] content = imageDto.getContent();
        MediaType contentType = MediaType.valueOf("image/jpg");
        return ResponseEntity.status(200).contentType(contentType).cacheControl(cacheControl).body(content);
    }
}
