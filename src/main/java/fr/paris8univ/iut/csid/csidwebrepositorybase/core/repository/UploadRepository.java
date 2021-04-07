package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.HasImageDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.ImageDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.HasImageEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ImageEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

@Repository
public class UploadRepository {

    private final ImageDao imageDao;
    private final HasImageDao hasImageDao;

    public UploadRepository(ImageDao imageDao, HasImageDao hasImageDao) {
        this.imageDao = imageDao;
        this.hasImageDao = hasImageDao;
    }

    public void saveImage(MultipartFile serverFile, Long userid) throws IOException {
        ImageEntity ie = new ImageEntity();
        ie.setContent(serverFile.getBytes());
        ie.setName(serverFile.getOriginalFilename());
        this.imageDao.save(ie);
        Long imageid = 0L;
        for (ImageEntity e:this.imageDao.findAll()) {
            if (e.getId()>imageid)
                imageid = e.getId();
        }
        if (this.hasImageDao.findById(userid).isPresent()) {
            HasImageEntity hie = this.hasImageDao.findById(userid).get();
            hie.setImageid(imageid);
            this.hasImageDao.save(hie);
        }
    }

    public ImageEntity findById(Long imageId) {
        return this.imageDao.findById(imageId).orElseGet(ImageEntity::new);
    }
}
