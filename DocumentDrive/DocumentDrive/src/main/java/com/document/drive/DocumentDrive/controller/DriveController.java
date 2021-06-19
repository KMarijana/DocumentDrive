package com.document.drive.DocumentDrive.controller;

import com.document.drive.DocumentDrive.service.IDriveService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;

@RestController
//localhost:8080/v1/
@CrossOrigin
@RequestMapping("v1")
public class DriveController {

    //instanca interfejsa IDriveService
    @Autowired
    private IDriveService driveService;


    // koristeci post metodu za pristup pozivaju se prethodno kreirane metode
    @PostMapping("podeliLink") //putanja
    @ResponseBody
    public String linkZaDeljenje(@RequestBody long korisnikId) throws UnsupportedEncodingException {
        return driveService.linkZaDeljenje(korisnikId);
    }

}