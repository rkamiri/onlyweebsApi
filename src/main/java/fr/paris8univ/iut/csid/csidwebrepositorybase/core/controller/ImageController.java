package fr.paris8univ.iut.csid.csidwebrepositorybase.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/upload")
public class ImageController {

    @Autowired
    private ImageMetadataFactory metaDataFactory;

    @Autowired
    private FileService fileService;

    @RequestMapping(value="/product/{spn}/image", method= RequestMethod.PUT)
    public ModelAndView handleImageUpload(
            @PathVariable("spn") String spn,
            HttpEntity<byte[]> requestEntity,
            HttpServletResponse response) throws IOException {
        byte[] payload = requestEntity.getBody();
        HttpHeaders headers = requestEntity.getHeaders();

        try {
            ProductImageMetadata metaData = metaDataFactory.newSpnInstance(spn, headers);
            fileService.store(metaData, payload);
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return null;
        } catch (IOException ex) {
            return internalServerError(response);
        } catch (IllegalArgumentException ex) {
            return badRequest(response, "Content-Type missing or unknown.");
        }
    }
}
