package com.springboot.bonnwasgeht;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileStorageSystem {
    public static void storeMultiPartFile(MultipartFile file ) throws IOException, IOException {
        Path target = Paths.get("C:\\BonnWasGeht\\BonnWasGeht\\ProgrammierprojektBonnWasGeht\\JavaBackend\\bonnwasgehtspring\\bonnwasgehtspring\\src\\main\\resources\\uploadedImages\\"+file.getOriginalFilename());

        File convFile = new File("C:\\BonnWasGeht\\BonnWasGeht\\ProgrammierprojektBonnWasGeht\\JavaBackend\\bonnwasgehtspring\\bonnwasgehtspring\\src\\main\\resources\\uploadedImages\\"+file.getOriginalFilename() );
        FileOutputStream fos = new FileOutputStream( convFile );
        fos.write( file.getBytes() );
        fos.close();

        FileInputStream fis = new FileInputStream(convFile);
        BufferedImage newBi = ImageIO.read(fis);
        ImageIO.write(newBi, "png", target.toFile());
    }

    public static byte[] getImageAsByteArray(String fileName) throws IOException {
        byte[] picture = Files.readAllBytes(Paths.get("C:\\BonnWasGeht\\BonnWasGeht\\ProgrammierprojektBonnWasGeht\\JavaBackend\\bonnwasgehtspring\\bonnwasgehtspring\\src\\main\\resources\\uploadedImages\\"+fileName));
        return picture;
    }
}
