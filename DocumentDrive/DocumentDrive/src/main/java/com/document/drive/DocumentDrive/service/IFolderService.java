package com.document.drive.DocumentDrive.service;

import com.document.drive.DocumentDrive.dto.DatotekaUFolderuDto;
import com.document.drive.DocumentDrive.model.Folder;

import javax.transaction.Transactional;
import java.io.IOException;

public interface IFolderService {

    // dekalaracija metode za kreiranje foldera
    @Transactional
    Folder kreirajFolder(long driveId, String naziv);

    // dekalaracija metode za brisanje foldera
    @Transactional
    void obrisiFolder(long folderId) throws IOException;

    // dekalaracija metode za prikaz sadrzaja (datoteke) u kreiranom folderu
    @Transactional
    DatotekaUFolderuDto prikaziSadrzajFoldera(long folderId);

}
