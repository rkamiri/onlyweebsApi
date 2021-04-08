package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.HasImageDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.ImageDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.HasImageEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ImageEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

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
        List<ImageEntity> entityList = this.imageDao.findAll();
        ie.setContent(scale(serverFile.getBytes(), 150, 150));
        ie.setName(serverFile.getOriginalFilename());
        this.imageDao.save(ie);
        Long imageId = entityList.get(entityList.size()-1).getId()+1;
        if (this.hasImageDao.findById(userid).isPresent()) {
            HasImageEntity hie = this.hasImageDao.findById(userid).get();
            hie.setImageid(imageId);
            this.hasImageDao.save(hie);
        }
    }

    public ImageEntity findById(Long imageId) {
        return this.imageDao.findById(imageId).orElseGet(ImageEntity::new);
    }

    public byte[] scale(byte[] fileData, int width, int height) {
        ByteArrayInputStream in = new ByteArrayInputStream(fileData);
        try {
            BufferedImage img = ImageIO.read(in);
            Image scaledImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ImageIO.write(imageBuff, "jpg", buffer);
            return buffer.toByteArray();
        } catch (IOException e) {
            return new byte[10];
        }
    }
}
