package com.document.drive.DocumentDrive.service;

import com.document.drive.DocumentDrive.dto.KorisnikDto;
import com.document.drive.DocumentDrive.dto.KorisnikPrijavaDto;
import com.document.drive.DocumentDrive.model.Korisnik;


public interface IKorisnikService {

    //deklaracija metode za prijavljivanje
    KorisnikDto prijava(String email, String lozinka);

    //deklaracija metode za registraciju korisnika
    Korisnik registracija(KorisnikDto korisnikDto);

}
