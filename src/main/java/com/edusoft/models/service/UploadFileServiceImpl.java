package com.edusoft.models.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private final static String UPLOADS_FOLDER = "C:/fileDownloadExample/";

	
	
	@Override
	public Resource load(String filename) throws MalformedURLException {
		String carpeta = null;
		Path archivo = getPath(filename, carpeta);
		log.info("archivo: " + archivo);

		Resource recurso = new UrlResource(archivo.toUri());

		if (!recurso.exists() || !recurso.isReadable()) {
			throw new RuntimeException("Error: no se puede cargar el archivo: " + archivo.toString());
			
		}
		return recurso;
	}


	@Override
	public String copy(MultipartFile file,String carpeta ) throws IOException {
		 
		
		String fileName = file.getOriginalFilename();
		
		int startIndex = fileName.replaceAll("\\\\", "/").lastIndexOf("/");
		
		fileName = fileName.substring(startIndex + 1);

		String uniqueFilename = fileName;
		Path rootPath = getPath(uniqueFilename , carpeta);

		log.info("rootPath: " + rootPath);

		Files.copy(file.getInputStream(), rootPath);

		return uniqueFilename;
	}
	
	

	@Override
	public boolean delete(String filename) {
		String carpeta=null;
		if (filename != null && filename.length() > 0) {
			Path rootPath = getPath(filename, carpeta);
			File archivo = rootPath.toFile();

			if (archivo.exists() && archivo.canRead()) {
				if (archivo.delete()) {
					return true;
				}
			}
		}
		return false;
	}

	
        
        /**
         * 
         * @param filename
         * @param carpeta Guarda el archivo en la carpeta especificada
         * @return 
         */
	public Path getPath(String filename, String carpeta) {
		
		return Paths.get(UPLOADS_FOLDER + carpeta).resolve(filename).toAbsolutePath();
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());

	}

	//Crea la carpeta si no existe
	@Override
	public void init() throws IOException {
		
		try {
		   Files.createDirectory(Paths.get(UPLOADS_FOLDER));
		}catch(IOException e) {}
	}
}
