package com.ishanitech.ipalika.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ishanitech.ipalika.config.properties.FileStorageProperties;
import com.ishanitech.ipalika.exception.FileStorageException;

/**
 * {@code FileUtils} is a file utility class whose function is to do all the operation related to files management.
 * @author <b> Umesh Bhujel
 * @since 1.0
 */

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
	
}