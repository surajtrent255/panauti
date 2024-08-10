package com.ishanitech.ipalika.controller;

import com.ishanitech.ipalika.config.properties.FileStorageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RequestMapping("/resource")
@RestController
public class ImageServeController {
    @Autowired
    private FileStorageProperties fileStorageProperties;
    private static final String IMAGE_DIRECTORY = "E:\\ishanitech\\duplicatbackup\\Godawari_Backups\\files";
    private static final String IMAGE_DIRECTORY_RESIZED = "E:\\ishanitech\\duplicatbackup\\Godawari_Backups\\files\\resized";

    @GetMapping("/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        Path imagePath = Paths.get(fileStorageProperties.getUpload().getDirectory(), imageName);
        if (!Files.exists(imagePath)) {
            return ResponseEntity.notFound().build();
        }

        byte[] imageBytes = new byte[0];
        try {
            imageBytes = Files.readAllBytes(imagePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, Files.probeContentType(imagePath));

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/resized/{imageName}")
    public ResponseEntity<byte[]> getImageResized(@PathVariable String imageName) throws IOException {
//        Path imagePath = Paths.get(IMAGE_DIRECTORY+"\\resized", imageName);
        Path imagePath = Paths.get(fileStorageProperties.getUpload().getDirectory()+"/resized", imageName);

        if (!Files.exists(imagePath)) {
            return ResponseEntity.notFound().build();
        }

        byte[] imageBytes = new byte[0];
        try {
            imageBytes = Files.readAllBytes(imagePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, Files.probeContentType(imagePath));

        return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
    }
}
