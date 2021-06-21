package com.document.drive.DocumentDrive.service.impl;

import com.document.drive.DocumentDrive.dto.DatotekaUFolderuDto;
import com.document.drive.DocumentDrive.model.Folder;
import com.document.drive.DocumentDrive.repository.DatotekaRepository;
import com.document.drive.DocumentDrive.repository.DriveRepository;
import com.document.drive.DocumentDrive.repository.FolderRepository;
import com.document.drive.DocumentDrive.service.IFolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

@Service
public class FolderService implements IFolderService {

    //instance klasa
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private DriveRepository driveRepository;
    @Autowired
    private DatotekaRepository datotekaRepository;


    // metoda za kreiranje foldera na korisnikovom drajvu
    @Transactional
    @Override
    public Folder kreirajFolder(long driveId, String naziv) {

        var drive =driveRepository.getOne(driveId);
        var putanja = drive.getGlavnaPutanja()+"/"+naziv;

        // kreiranje novog foldera na hard disku
        drive.getFolderi().add(new Folder(naziv.trim(),
                new Timestamp(System.currentTimeMillis()),
                putanja, drive));
        File file = new File(putanja);
        file.mkdirs();
        return driveRepository.saveAndFlush(drive).getFolderi().get(0);

    }

    // metoda za brisanje foldera, prvo se brise sadrzaj foldera ako postoji a nakon toga će se obrisati folder
    @Transactional
    @Override
    public void obrisiFolder(long folderId) throws IOException {
        var datoteke= datotekaRepository.findAllByFolderId(folderId);

        for (int i =0; i<datoteke.size(); i++) {
            File datotekaUFolderu = new File(datoteke.get(i).getPutanja());
            if (datotekaUFolderu.exists())
                datotekaUFolderu.delete();
            datotekaRepository.obrisiDatoteku(datoteke.get(i).getId());
        }

        // ako u folderu postoji neka datoteka, prvo se ona brise
        var folder = folderRepository.getOne(folderId);
        File file = new File(folder.getPutanja());
        if (file.exists())
            file.delete();

        // brisanje foldera
        folderRepository.obrisiFolder(folderId);
        folderRepository.flush();
    }


    // metoda koja sluzi za prikaz sadržaja unutar folder
    @Transactional
    @Override
    public DatotekaUFolderuDto prikaziSadrzajFoldera(long folderId) {
        var folder = folderRepository.prikaziFolder(folderId);

        var datoteke = folder.getDatoteke();
        // provera da li je folder prazan

        if (datoteke.size() > 0) {
            var datoteka = datoteke.get(0);
            // ako folder nije prazan vraca podatke o datoteci
            return new DatotekaUFolderuDto(
                    folderId,
                    datoteka.getId(),
                    datoteka.getVelicina(),
                    datoteka.getNaziv(),
                    datoteka.getDatumKreiranja()
            ); //folder je prazan vraca samo njegov id
        } else return new DatotekaUFolderuDto(folderId);
    }

}
