package com.document.drive.DocumentDrive.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "Datoteka")
@Entity
@JsonIgnoreProperties({"folder"})
public class Datoteka {

    // privatni atributi klase Datoteka
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "datoteka_id")
    private long id;

    @Basic
    @Column(name = "naziv")
    private String naziv;

    @Basic
    @Column(name = "velicina")
    private long velicina;

    @Basic
    @Column(name = "datum_kreiranja")
    private Timestamp datumKreiranja;

    @Basic
    @Column(name = "putanja")
    private String putanja;


    //naredba za povezivanje tabela u bazi
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "folder_id")
    private Folder folder;


    // Defaultni konstruktor
    public Datoteka() {
    }

    // Konstruktor sa parametrima
    public Datoteka(String naziv, long velicina, Timestamp datumKreiranja, String putanja, Folder folder) {
        this.naziv = naziv;
        this.velicina = velicina;
        this.datumKreiranja = datumKreiranja;
        this.putanja = putanja;
        this.folder = folder;
    }

    // get i set metode atributa
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public long getVelicina() {
        return velicina;
    }

    public void setVelicina(long velicina) {
        this.velicina = velicina;
    }

    public Timestamp getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Timestamp datum_kreiranja) {
        this.datumKreiranja = datum_kreiranja;
    }

    public String getPutanja() {
        return putanja;
    }

    public void setPutanja(String putanja) {
        this.putanja = putanja;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    // toString metoda za ipisi
    @Override
    public String toString() {
        return "Datoteka{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", velicina=" + velicina +
                ", datumKreiranja=" + datumKreiranja +
                ", putanja='" + putanja + '\'' +
                '}';
    }
}
