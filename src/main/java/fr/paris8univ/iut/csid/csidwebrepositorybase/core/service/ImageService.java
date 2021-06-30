package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.ImageRepository;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.dao.UsersDao;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ImageEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UsersEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.ImageDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final UsersDao usersDao;

    public ImageService(ImageRepository imageRepository, UsersDao usersDao) {
        this.imageRepository = imageRepository;
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

    public void saveArticleImage(MultipartFile serverFile) throws IOException {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setContent(scale(serverFile.getBytes(), 640, 360));
        imageEntity.setName(serverFile.getOriginalFilename());
        this.imageRepository.save(imageEntity);
    }

    public ImageDto findById(Long imageId) {
        return new ImageDto(this.imageRepository.findById(imageId).orElseGet(ImageEntity::new));
    }

    public byte[] scale(byte[] fileData, int width, int height) {
        ByteArrayInputStream in = new ByteArrayInputStream(fileData);
        try {
            BufferedImage img = ImageIO.read(in);
            java.awt.Image scaledImage = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
            BufferedImage imageBuff = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0, 0, 0), null);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            ImageIO.write(imageBuff, "jpg", buffer);
            return buffer.toByteArray();
        } catch (IOException e) {
            return new byte[0];
        }
    }
}
