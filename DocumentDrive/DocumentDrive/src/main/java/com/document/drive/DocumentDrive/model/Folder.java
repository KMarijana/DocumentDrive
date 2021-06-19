package com.document.drive.DocumentDrive.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Table(name = "Folder")
@Entity
@JsonIgnoreProperties({"datoteka","drive"})
public class Folder {

    // privatni atributi klase Folder
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "folder_id")
    private long id;

    @Basic
    @Column(name = "naziv")
    private String naziv;

    @Basic
    @Column(name = "datum_kreiranja")
    private Timestamp datumKreiranja;

    @Basic
    @Column(name = "putanja")
    private String putanja;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "drive_id", referencedColumnName = "drive_id")
    private Drive drive;

    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL)
    private List<Datoteka> datoteke = new ArrayList<>();


    // defaultni konstruktor i konstruktor sa parametrima
    public Folder() {
    }

    public Folder(String naziv, Timestamp datumKreiranja, String putanja, Drive drive) {
        this.naziv = naziv;
        this.datumKreiranja = datumKreiranja;
        this.putanja = putanja;
        this.drive = drive;
    }


    // get i set metode atribura
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

    public Timestamp getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Timestamp datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public String getPutanja() {
        return putanja;
    }

    public void setPutanja(String putanja) {
        this.putanja = putanja;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public List<Datoteka> getDatoteke() {
        return datoteke;
    }

    public void setDatoteke(List<Datoteka> datoteke) {
        this.datoteke = datoteke;
    }

    // toString metoda za ispis
    @Override
    public String toString() {
        return "Folder{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", datumKreiranja=" + datumKreiranja +
                ", putanja='" + putanja + '\'' +
                '}';
    }
}
