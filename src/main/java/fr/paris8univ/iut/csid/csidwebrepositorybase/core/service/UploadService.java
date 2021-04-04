package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

 import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Image;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.UploadRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UploadService {

    public final UploadRepository uploadRepository;

    public UploadService(UploadRepository uploadRepository) {
        this.uploadRepository = uploadRepository;
    }

    public void saveImage(MultipartFile serverFile, Long userid) throws IOException {
        this.uploadRepository.saveImage(serverFile, userid);
    }

    public Image findById(Long imageId) {
        return new Image(this.uploadRepository.findById(imageId));
    }
}