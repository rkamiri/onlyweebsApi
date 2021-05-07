package fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.ImageDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.UsersDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ArticleEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ImageEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UsersEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

@Repository
public class UploadRepository {

    private final ImageDao imageDao;
    private final UsersDao usersDao;

    public UploadRepository(ImageDao imageDao, UsersDao usersDao) {
        this.imageDao = imageDao;
        this.usersDao = usersDao;
    }

    public void saveImage(MultipartFile serverFile, Long userid) throws IOException {
        UsersEntity user = this.usersDao.getOne(userid);
        ImageEntity imageEntity = user.getImage();
        if (!(imageEntity.getId() != 1 && imageEntity.getId() != 2 && imageEntity.getId() != 3))
            imageEntity = new ImageEntity();
        imageEntity.setContent(scale(serverFile.getBytes(), 150, 150));
        imageEntity.setName(serverFile.getOriginalFilename());
        user.setImage(imageEntity);
        this.usersDao.save(user);
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
            imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ImageIO.write(imageBuff, "jpg", buffer);
            return buffer.toByteArray();
        } catch (IOException e) {
            return new byte[0];
        }
    }

    public void saveArticleImage(MultipartFile serverFile) throws IOException {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setContent(scale(serverFile.getBytes(), 640, 360));
        imageEntity.setName(serverFile.getOriginalFilename());
        this.imageDao.save(imageEntity);
    }

    public ImageEntity getLastImage() {
        List<ImageEntity> imageEntities = this.imageDao.findAll();
        Long id = 1L;
        for (ImageEntity imageEntity : imageEntities) {
            if (imageEntity.getId() >= id) id = imageEntity.getId();
        }
        return this.imageDao.getOne(id);
    }
}
