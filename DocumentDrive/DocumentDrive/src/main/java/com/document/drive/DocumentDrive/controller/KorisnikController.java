package com.document.drive.DocumentDrive.controller;

import com.document.drive.DocumentDrive.dto.KorisnikDto;
import com.document.drive.DocumentDrive.dto.KorisnikPrijavaDto;
import com.document.drive.DocumentDrive.model.Korisnik;
import com.document.drive.DocumentDrive.service.IKorisnikService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//localhost:8080/v1/login
@CrossOrigin
@RequestMapping("v1")
public class KorisnikController {
    //instanca interfejsa IKorisnikService
    @Autowired
    private IKorisnikService korisnikService;

    // koristeci post metodu za pristup pozivaju se prethodno kreirane metode
    @PostMapping("login") //putanja: localhost:8080/v1/login
    @ResponseBody
    public ResponseEntity<KorisnikDto> prijava(@RequestBody @Valid KorisnikPrijavaDto korisnik) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(korisnikService.prijava(korisnik.getEmail().trim(), korisnik.getLozinka().trim()));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PostMapping("registracija") //putanja: localhost:8080/v1/registracija
    @ResponseBody
    public ResponseEntity<Korisnik> registracija(@RequestBody @Valid KorisnikDto korisnikDto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(korisnikService.registracija(korisnikDto));
        }
        catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
