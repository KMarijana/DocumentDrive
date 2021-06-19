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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "korisnik_id", referencedColumnName = "korisnik_id")
    private Korisnik korisnik;


    // Defaultni konstruktor i konstruktor sa parametrima

    public Drive() {
    }

    public Drive(String glavnaPutanja,  Korisnik korisnik) {
        this.glavnaPutanja = glavnaPutanja;
        this.korisnik = korisnik;
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

    //metoda toString za ispis
    @Override
    public String toString() {
        return "Drive{" +
                "id=" + id +
                ", glavnaPutanja='" + glavnaPutanja + '\'' +
                ", korisnik=" + korisnik +
                '}';
    }
}
