package fr.paris8univ.iut.csid.csidwebrepositorybase.core.service;

import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.ImageEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.entity.UserEntity;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.model.ImageDto;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.ImageRepository;
import fr.paris8univ.iut.csid.csidwebrepositorybase.core.repository.UsersRepository;
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
    private final UsersRepository usersRepository;

    public ImageService(ImageRepository imageRepository, UsersRepository usersRepository) {
        this.imageRepository = imageRepository;
        this.usersRepository = usersRepository;
    }

    public void saveImage(MultipartFile serverFile, Long userid) throws IOException {
        UserEntity user = this.usersRepository.getOne(userid);
        ImageEntity imageEntity = user.getImage();
        if (!(imageEntity.getId() != 1 && imageEntity.getId() != 2 && imageEntity.getId() != 3))
            imageEntity = new ImageEntity();
        imageEntity.setContent(scale(serverFile.getBytes(), 150, 150));
        imageEntity.setName(serverFile.getOriginalFilename());
        user.setImage(imageEntity);
        this.usersRepository.save(user);
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
