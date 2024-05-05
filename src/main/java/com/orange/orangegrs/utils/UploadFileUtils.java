package com.orange.orangegrs.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class UploadFileUtils {


    private final Path rootLocation;

    public UploadFileUtils(){
        // Chemin relatif du répertoire où vous souhaitez stocker les images
        String relativePath = "src/main/resources/static/images/compteurs/";
        // Convertir le chemin relatif en chemin absolu
        this.rootLocation = Paths.get(System.getProperty("user.dir"), relativePath);
        // Créer le répertoire s'il n'existe pas déjà
        try {
            Files.createDirectories(this.rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Impossible de créer le répertoire de stockage des fichiers", e);
        }
    }

    public String uploadImageToFileSystem(MultipartFile file) throws IOException{
        System.out.println("upload file method utils upload file to file system");
        try{
            Path filePath = rootLocation.resolve(file.getOriginalFilename());
            file.transferTo(filePath.toFile());
            return file.getOriginalFilename();
        }catch (IOException e) {
            throw new IOException("Impossible de stocker le fichier " + file.getOriginalFilename(), e);
        } catch (IllegalStateException e) {
            throw new IllegalStateException("Impossible de transférer le fichier " + file.getOriginalFilename(), e);
        } catch (MaxUploadSizeExceededException maxup) {
            throw new MaxUploadSizeExceededException(maxup.getMaxUploadSize());
        }
    }
}
