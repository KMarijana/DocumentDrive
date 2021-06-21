package com.document.drive.DocumentDrive.service;

import javax.transaction.Transactional;

public interface IDatotekaService {

    //deklaracija metode za brisanje datoteke
    @Transactional
    void obrisiDatoteku(long datotekaId);


}
