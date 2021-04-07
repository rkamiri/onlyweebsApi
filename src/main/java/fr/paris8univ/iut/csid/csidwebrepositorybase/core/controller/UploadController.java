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
public class UploadController {

    public final UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/image/{userid}")
    public void UploadFile(MultipartHttpServletRequest request, @PathVariable Long userid) throws IOException {
        Iterator<String> itr = request.getFileNames();
        MultipartFile file = request.getFile(itr.next());
        this.uploadService.saveImage(file, userid);
    }

    @GetMapping("/image/{id}")
    public Image downloadImage(@PathVariable(value = "id") Long imageId) {
        return uploadService.findById(imageId);
    }
}
