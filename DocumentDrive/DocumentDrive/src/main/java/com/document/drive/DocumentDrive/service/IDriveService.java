package com.document.drive.DocumentDrive.service;

import com.document.drive.DocumentDrive.dto.DatotekaDto;
import com.document.drive.DocumentDrive.dto.SadrzajDriveDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.nio.file.Path;

public interface IDriveService {

    // dekalaracija metode za putanje za preuzimanje datoteke
    Path putanjaZaPreuzimanje(long datotekaId);

    // dekalaracija metode za ucitavanje datotke
    DatotekaDto ucitaj(long korisnikId, long folderId, MultipartFile datoteka);

    //dekalaracija metode za link za deljenje sadrzaja
    String linkZaDeljenje(long korisnikId) throws UnsupportedEncodingException;

    // dekalaracija metode za ucitavanje sadrzaja
    SadrzajDriveDto ucitajSadrzaj(long korisnikId);
}

