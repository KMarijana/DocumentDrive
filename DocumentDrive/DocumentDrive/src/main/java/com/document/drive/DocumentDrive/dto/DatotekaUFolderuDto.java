package com.document.drive.DocumentDrive.dto;

import java.sql.Timestamp;

public class DatotekaUFolderuDto {
    // privatni atributi klase
    private long folderId;
    private long datotekaId;
    private long velicina;
    private String naziv;
    private Timestamp datumKreiranja;

    // konstruktor sa parametrima i defaulni konstruktor

    public DatotekaUFolderuDto(long folderId, long datotekaId, long velicina, String naziv, Timestamp datumKreiranja) {
        this.folderId = folderId;
        this.datotekaId = datotekaId;
        this.velicina = velicina;
        this.naziv = naziv;
        this.datumKreiranja = datumKreiranja;
    }

    public DatotekaUFolderuDto(long folderId) {
        this.folderId = folderId;
    }

    // get i set metode

    public long getFolderId() {
        return folderId;
    }

    public void setFolderId(long folderId) {
        this.folderId = folderId;
    }

    public long getDatotekaId() {
        return datotekaId;
    }

    public void setDatotekaId(long datotekaId) {
        this.datotekaId = datotekaId;
    }

    public long getVelicina() {
        return velicina;
    }

    public void setVelicina(long velicina) {
        this.velicina = velicina;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Timestamp getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Timestamp datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }
}
