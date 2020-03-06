package com.ishanitech.ipalika.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ishanitech.ipalika.config.properties.FileStorageProperties;
import com.ishanitech.ipalika.exception.FileStorageException;

import lombok.extern.slf4j.Slf4j;

/**
 * {@code FileUtils} is a file utility class whose function is to do all the operation related to files management.
 * @author <b> Umesh Bhujel
 * @since 1.0
 */

@Slf4j
@Component
public class FileUtilService {
	private final Path storageLocation;

	public FileUtilService(FileStorageProperties storageProperties) {
		this.storageLocation = Paths.get(storageProperties.getUpload().getDirectory()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.storageLocation);
		} catch(IOException ex) {
			throw new FileStorageException("Couldn't create directory to store your file!");
		}
	}

	/**
	 * Stores the file to local storage.
	 * @param image {@code Multipart} file
	 * @return string filename
	 * @since 1.0
	 * @author <b> Umesh Bhujel
	 */
	public String storeFile(MultipartFile image) {
		String fileName = StringUtils.cleanPath(image.getOriginalFilename());
//		Date presentDate = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
//        String fileName = "JPEG_" + dateFormat.format(presentDate) + ".JPG";
        
		try {
			if(fileName.contains("..")) {
				throw new FileStorageException(String.format("Sorry your file %s contains invalid characters for pathname.!", fileName));
			}
			
			Path targetLocation = this.storageLocation.resolve(fileName);
			Files.copy(image.getInputStream(), targetLocation);
			return fileName;
		} catch(IOException ex) {
			throw new FileStorageException(String.format("Couldn't store the file %s!", fileName));
		}
	}
	
	public String storeEditedFile(MultipartFile image) {
		String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        
		try {
			if(fileName.contains("..")) {
				throw new FileStorageException(String.format("Sorry your file %s contains invalid characters for pathname.!", fileName));
			}
			
			Path targetLocation = this.storageLocation.resolve(fileName);
			Files.copy(image.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		} catch(IOException ex) {
			throw new FileStorageException(String.format("Couldn't store the file %s!", fileName));
		}
	}
	
	
	
	@Transactional
    public void deleteFile(String fileName)
    {
         try { 
             File file = new File(this.storageLocation.resolve(fileName).toString());
             log.info(file.getName());
             if(file.delete()) { 
                System.out.println(file.getName() + " is deleted!");
             } else {
                System.out.println("Delete operation is failed.");
                }
          }
            catch(Exception e)
            {
                System.out.println("Failed to Delete image !!");
            }
    }
	
}
