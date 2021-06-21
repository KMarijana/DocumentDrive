package com.document.drive.DocumentDrive.service.impl;

import com.document.drive.DocumentDrive.repository.DatotekaRepository;
import com.document.drive.DocumentDrive.service.IDatotekaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DatotekaService implements IDatotekaService {
    //instanca klase datotekaRepository
    @Autowired
    private DatotekaRepository datotekaRepository;

    // metoda za brisanje datoteke, u zavisnosti od prosleđenog paramtera
    @Transactional
    @Override
    public void obrisiDatoteku(long datotekaId) {
        datotekaRepository.obrisiDatoteku(datotekaId);
        datotekaRepository.flush();

    }

}
