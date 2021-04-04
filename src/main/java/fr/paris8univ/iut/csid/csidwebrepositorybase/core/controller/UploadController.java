package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Image;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Iterator;

@RestController
@RequestMapping(value = "/upload")
public class UploadController {

    public final UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/image")
    public void UploadFile(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> itr = request.getFileNames();
        MultipartFile file = request.getFile(itr.next());
        this.uploadService.saveImage(file);
    }

    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE, path = "/image/{id}")
    //public Resource downloadImage(@PathVariable(value = "id") Long imageId) {
    public Image downloadImage(@PathVariable(value = "id") Long imageId) {
        return uploadService.findById(imageId);
        //byte[] image = uploadService.findById(imageId).getContent();
        //return new ByteArrayResource(image);
    }

    /*@PostMapping("/image")
    public void UploadFile(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> itr = request.getFileNames();
        MultipartFile file = request.getFile(itr.next());
        String fileName = file.getOriginalFilename();
        File dir = new File("/home/nlmz/ow/back/onlyweebsApi/src/main/resources/uploads/images");
        if (dir.isDirectory()) {
            File serverFile = new File(dir, fileName);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(file.getBytes());
            stream.close();
            this.uploadService.saveImage(serverFile);
        }
    }*/
}
