package com.document.drive.DocumentDrive.dto;

import com.document.drive.DocumentDrive.model.Datoteka;
import com.document.drive.DocumentDrive.model.Folder;

import java.util.List;

public class SadrzajDriveDto {
    // atributi klase
    private List<Folder> folderi;
    private List<Datoteka> datoteke;

    // konstruktor sa parametrima
    public SadrzajDriveDto(List<Folder> folderi, List<Datoteka> datoteke) {
        this.folderi = folderi;
        this.datoteke = datoteke;
    }

    // get i set metode
    public List<Folder> getFolderi() {
        return folderi;
    }

    public void setFolderi(List<Folder> folderi) {
        this.folderi = folderi;
    }

    public List<Datoteka> getDatoteke() {
        return datoteke;
    }

    public void setDatoteke(List<Datoteka> datoteke) {
        this.datoteke = datoteke;
    }
}
