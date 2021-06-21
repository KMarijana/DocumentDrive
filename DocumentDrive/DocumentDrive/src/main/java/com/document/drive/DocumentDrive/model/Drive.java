package com.document.drive.DocumentDrive.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Drive")
@JsonIgnoreProperties({"korisnik","folder"})
public class Drive {

    // privatni atributi klase Drive
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drive_id")
    private long id;

    @Column(name = "glavna_putanja")
    private String glavnaPutanja;

    //naredbe za povezivanje tabela u bazi
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "korisnik_id", referencedColumnName = "korisnik_id")
    private Korisnik korisnik;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "drive_id", referencedColumnName = "drive_id")
    private List<Folder> folderi = new ArrayList<>();



    // Defaultni konstruktor i konstruktor sa parametrima

    public Drive() {
    }

    public Drive(String glavnaPutanja, List<Folder> folderi, Korisnik korisnik) {
        this.glavnaPutanja = glavnaPutanja;
        this.korisnik = korisnik;
        this.folderi = folderi;
    }

    // get i set metode atributa

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public String getGlavnaPutanja() {
        return glavnaPutanja;
    }

    public void setGlavnaPutanja(String glavnaPutanja) {
        this.glavnaPutanja = glavnaPutanja;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Folder> getFolderi() {
        return folderi;
    }

    public void setFolderi(List<Folder> folderi) {
        this.folderi = folderi;
    }

    // toString metoda za ispis
    @Override
    public String toString() {
        return "Drive{" +
                "id=" + id +
                '}';
    }
}
