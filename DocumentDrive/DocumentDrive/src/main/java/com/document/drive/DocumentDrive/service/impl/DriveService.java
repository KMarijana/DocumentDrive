package com.document.drive.DocumentDrive.service.impl;

import com.document.drive.DocumentDrive.model.Drive;
import com.document.drive.DocumentDrive.repository.DriveRepository;
import com.document.drive.DocumentDrive.repository.KorisnikRepository;
import com.document.drive.DocumentDrive.service.IDriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Base64;

@Service
public class DriveService implements IDriveService {

    @Autowired
    private DriveRepository driveRepository;
    @Autowired
    private KorisnikRepository korisnikRepository;


    // metoda pomoću koje se kreira link za deljenje drive-a
    @Override
    public String linkZaDeljenje(long korisnikId) throws UnsupportedEncodingException {
        //random generisan string
        String link = generateRandomString() + korisnikId;

        System.out.println(link);
        return URLEncoder.encode(link, StandardCharsets.UTF_8.toString());
    }

    // metoda za generisanje random string koji će se koristiti za link u metodi linkZaDeljenje()
    private String generateRandomString() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[24];
        random.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        return encoder.encodeToString(bytes);
    }



}
