package com.document.drive.DocumentDrive.controller;

import com.document.drive.DocumentDrive.dto.DatotekaDto;
import com.document.drive.DocumentDrive.dto.DatotekaUFolderuDto;
import com.document.drive.DocumentDrive.dto.FolderDto;
import com.document.drive.DocumentDrive.dto.SadrzajDriveDto;
import com.document.drive.DocumentDrive.model.Folder;
import com.document.drive.DocumentDrive.service.IDatotekaService;
import com.document.drive.DocumentDrive.service.IDriveService;
import com.document.drive.DocumentDrive.service.IFolderService;
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
    //instanca interfejsa IFolderService
    @Autowired
    private IFolderService folderService;
    //instanca interfejsa IDatotekaService
    @Autowired
    private IDatotekaService datotekaService;
    //instanca interfejsa IDriveService
    @Autowired
    private IDriveService driveService;


    // koristeci post metodu za pristup pozivaju se prethodno kreirane metode

    @PostMapping("kreirajFolder") // putanja
    @ResponseBody
    public ResponseEntity<Folder> kreirajFolder(@RequestBody @Valid FolderDto folderDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(folderService.kreirajFolder(folderDto.getDriveId(), folderDto.getNaziv().trim()));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("prikaziSadrzajFoldera") // putanja
    @ResponseBody
    public ResponseEntity<DatotekaUFolderuDto> prikaziSadrzajFoldera(@RequestBody long folderId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(folderService.prikaziSadrzajFoldera(folderId));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping("podeliLink") //putanja
    @ResponseBody
    public String linkZaDeljenje(@RequestBody long korisnikId) throws UnsupportedEncodingException {
        return driveService.linkZaDeljenje(korisnikId);
    }

    @PostMapping("obrisiFolder") // putanja
    @ResponseBody
    void delete(@RequestBody long folderId) throws IOException {
        folderService.obrisiFolder(folderId);
    }


    @PostMapping("ucitajSadrzaj") // putanja
    @ResponseBody
    public ResponseEntity<SadrzajDriveDto> ucitajSadrzaj(@RequestBody long korisnikId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(driveService.ucitajSadrzaj(korisnikId));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PostMapping("obrisiDatoteku") // putanja
    @ResponseBody
    void deleteDatoteka(@RequestBody long datotekaId) {
        datotekaService.obrisiDatoteku(datotekaId);
    }

    @PostMapping("ucitajDatoteku") // putanja
    public @ResponseBody
    ResponseEntity<DatotekaDto> ucitaj(@RequestParam("korisnikId") long korisnikId,
                                        @RequestParam("folderId") long folderId,
                                        @RequestParam("datoteka") MultipartFile file)
                                        {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    driveService.ucitaj(korisnikId, folderId, file));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    // koristeci get metodu, poziva se metoda za preuzimanje datoteke
    @GetMapping(value = "preuzmiDatoteku", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public ResponseEntity<Resource> preuzmi(@RequestParam long datotekaId) throws IOException {
        // ucitava datoteku sa hard diska kao niz bajtova
        final var putanjaZaPreuzimanje = driveService.putanjaZaPreuzimanje(datotekaId);
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(putanjaZaPreuzimanje));

        // kreira zaglavlje u kojem se nalazi ime datoteke
        HttpHeaders odgovorZaglavlje = new HttpHeaders();
        odgovorZaglavlje.set("File-Name", putanjaZaPreuzimanje.getFileName().toString());

        return ResponseEntity.ok()
                .headers(odgovorZaglavlje)
                .contentLength(putanjaZaPreuzimanje.toFile().length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }


}