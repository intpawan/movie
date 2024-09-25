package com.movieflix.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        //get name of the file
        String fileName = file.getOriginalFilename();

        // to get the file path
        String filePath = path + File.separator + fileName;

        // create file object
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }

        // copy the file or upload file to the path
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

       // this is how file is uploaded on the backend server
        return fileName;

    }

    @Override
    public InputStream getResource(String path, String filename) throws FileNotFoundException {

        // getting this resources
        String filePath = path + File.separator + filename;
        return new FileInputStream(filePath);

    }
}
