package com.document.drive.DocumentDrive.service.impl;

import com.document.drive.DocumentDrive.dto.DatotekaDto;
import com.document.drive.DocumentDrive.dto.SadrzajDriveDto;
import com.document.drive.DocumentDrive.model.Datoteka;
import com.document.drive.DocumentDrive.model.Drive;
import com.document.drive.DocumentDrive.model.Folder;
import com.document.drive.DocumentDrive.repository.DatotekaRepository;
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

    //instanciranje klasa
    @Autowired
    private DatotekaRepository datotekaRepository;
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

    // metoda za ucitavanje sadrzaja diska, porsleđuje se korisnikov id i na osnovu toga se ucitava sve sto
    // se nalazi na njegovom drive-u, folderi i datoteke
    @Override
    public SadrzajDriveDto ucitajSadrzaj(long korisnikId) {
        final var korisnikDrive = korisnikRepository.getOne(korisnikId).getDrive();
        var folderi = korisnikDrive.getFolderi();
        var datoteke = korisnikDrive.getFolderi().get(0).getDatoteke();

        // brisanje rootFoldera, zbog frontenda
        folderi.remove(0);
        return new SadrzajDriveDto(folderi, datoteke);
    }

    // metoda za generisanje random string koji će se koristiti za link u metodi linkZaDeljenje()
    private String generateRandomString() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[24];
        random.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        return encoder.encodeToString(bytes);
    }

    // metoda vraća putanju datoteke, na hard disku
    @Override
    public Path putanjaZaPreuzimanje(long datotekaId){
        return Paths.get(datotekaRepository.getOne(datotekaId).getPutanja());
    }


    // metoda za ucitavanje datoteke na drive i u folder
    @Override
    public DatotekaDto ucitaj(long korisnikId, long folderId, MultipartFile datoteka) {
        try {
            Drive korisnikDrive = driveRepository.getOne(korisnikId);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            var glavniFolder = "";
            Folder folder;

            // proverava se da li se datoteka ucitava u folder ili direktno na drive odnosno bez foldera
            if(folderId == -1)
            {
                folder = korisnikDrive.getFolderi().get(0);
                glavniFolder = korisnikDrive.getGlavnaPutanja();
            }
            else
            {
                folder = korisnikDrive.getFolderi().stream().filter(f -> f.getId() == folderId).findFirst().get();
                glavniFolder = folder.getPutanja();
            }

            // kreira putanju i kopira pristiglu datotku na putanju na hard disku
            final var userFolderFile = new java.io.File(glavniFolder + "/" + datoteka.getOriginalFilename());
            try (InputStream inputStream = datoteka.getInputStream()) {
                Files.copy(inputStream, userFolderFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            // kreiranje datoteke sa potrebnim atributima
            Datoteka novaDatoteka = new Datoteka(
                    userFolderFile.getName(),
                    userFolderFile.length(),
                    timestamp,
                    userFolderFile.getPath(),
                    folder);

            folder.getDatoteke().add(novaDatoteka);

            final var drive = driveRepository.saveAndFlush(korisnikDrive);

            // vraca podatke o ucitanoj datoteci
            for (Folder folder1: drive.getFolderi() ) {
                if(folder1.getPutanja().equals(glavniFolder))
                    return new DatotekaDto(folder1.getDatoteke().get(0));

            }

            return new DatotekaDto();


        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
