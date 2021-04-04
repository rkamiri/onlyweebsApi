package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.ImageDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ImageEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.Image;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

@Repository
public class UploadRepository {

    public final ImageDao imageDao;

    public UploadRepository(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    public void saveImage(MultipartFile serverFile) throws IOException {
        ImageEntity ie = new ImageEntity();
        ie.setContent(serverFile.getBytes());
        ie.setName(serverFile.getOriginalFilename());
        this.imageDao.save(ie);
    }

    private static byte[] readFileToBytes(String filePath) throws IOException {
        File file = new File(filePath);
        byte[] bytes = new byte[(int) file.length()];
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            fis.read(bytes);
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        return bytes;
    }

    public ImageEntity findById(Long imageId) {
        return this.imageDao.findById(imageId).orElseGet(ImageEntity::new);
    }
}
