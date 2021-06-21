package com.document.drive.DocumentDrive.service.impl;

import com.document.drive.DocumentDrive.dto.KorisnikDto;
import com.document.drive.DocumentDrive.model.Drive;
import com.document.drive.DocumentDrive.model.Folder;
import com.document.drive.DocumentDrive.model.Korisnik;
import com.document.drive.DocumentDrive.repository.DriveRepository;
import com.document.drive.DocumentDrive.repository.KorisnikRepository;
import com.document.drive.DocumentDrive.service.IKorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class KorisnikService implements IKorisnikService {

    //instanca klase korisnikRepository
    @Autowired
    private KorisnikRepository korisnikRepository;
    //instanca klase driveRepository
    @Autowired
    private DriveRepository driveRepository;

    private static final String glavnaPutanja = "D:\\DocumentDrive\\";

    //implementacija metode prijava
    @Override
    public KorisnikDto prijava(String email, String lozinka) {
        var login = korisnikRepository.login(email, lozinka);

        return new KorisnikDto(login.getId(), login.getIme(),login.getPrezime(),login.getEmail());
    }

    //implementacija metode registracije korisnika
    @Override
    public Korisnik registracija(KorisnikDto korisnikDto) {

        //instanciranje korisnika
        Korisnik noviKorisnik = new Korisnik(korisnikDto.getIme().trim(),
                korisnikDto.getPrezime().trim(),
                korisnikDto.getEmail().trim(),
                korisnikDto.getKorisnickoIme(),
                korisnikDto.getLozinka(),
                korisnikDto.getPotvrdaLozinke());
        
        //cuva novog korisnika
                noviKorisnik = korisnikRepository.saveAndFlush(noviKorisnik);
                String korisnikFolderPutanja = glavnaPutanja + noviKorisnik.getId() + "/root/";

        var drive = new Drive(korisnikFolderPutanja, new ArrayList<>(), noviKorisnik);
        var folder = new Folder("root", new Timestamp(System.currentTimeMillis()), korisnikFolderPutanja, drive);
        drive.setFolderi(Collections.singletonList(folder));
                driveRepository.saveAndFlush(drive);

        //kreiranje praznog foldera na putanji korisnika
        File file = new File(korisnikFolderPutanja);
        file.mkdirs();

        return noviKorisnik;
    }
}
