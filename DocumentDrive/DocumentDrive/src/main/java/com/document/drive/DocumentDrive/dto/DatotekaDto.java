package com.document.drive.DocumentDrive.dto;

import com.document.drive.DocumentDrive.model.Datoteka;

import java.sql.Timestamp;

public class DatotekaDto {

    // privatni atributi klase
    private long id;
    private long velicina;
    private String naziv;
    private Timestamp datumKreiranja;

    // konstruktor sa parametrima
    public DatotekaDto(Datoteka datoteka) {
        this.id = datoteka.getId();
        this.naziv = datoteka.getNaziv();
        this.datumKreiranja = datoteka.getDatumKreiranja();
        this.velicina = datoteka.getVelicina();
    }

    // defaultni konstruktor
    public DatotekaDto() {
    }

    // get i set metode

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
