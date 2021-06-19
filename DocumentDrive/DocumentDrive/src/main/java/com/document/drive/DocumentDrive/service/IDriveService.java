package com.document.drive.DocumentDrive.service;


import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.nio.file.Path;

public interface IDriveService {

    //dekalaracija metode za link za deljenje sadrzaja
    String linkZaDeljenje(long korisnikId) throws UnsupportedEncodingException;


}

