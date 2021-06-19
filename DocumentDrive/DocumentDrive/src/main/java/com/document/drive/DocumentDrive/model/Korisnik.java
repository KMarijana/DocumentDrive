package com.document.drive.DocumentDrive.model;

import javax.persistence.*;

@Table(name = "Korisnik")
@Entity
public class Korisnik {

    //privatni atributi klase Korisnik
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Korisnik_id")
    private long id;

    @Basic
    @Column(name = "Ime")
    private String ime;

    @Basic
    @Column(name = "Prezime")
    private String prezime;

    @Basic
    @Column(name = "Email")
    private String email;

    @Basic
    @Column(name = "Korisnicko_ime")
    private String korisnickoIme;

    @Basic
    @Column(name = "Lozinka")
    private String lozinka;

    @Basic
    @Column(name = "Potvrda_lozinke")
    private String potvrdaLozinke;

    //naredba za povezivanje tabela u bazi
    @OneToOne(mappedBy = "korisnik", fetch = FetchType.EAGER)
    private Drive drive;

    //podrazumevani konstruktor
    public Korisnik() {}

    //konstruktor sa parametrima
    public Korisnik(String ime, String prezime, String email, String korisnickoIme, String lozinka, String potvrdaLozinke) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.potvrdaLozinke = potvrdaLozinke;
    }

    //get i set metode
    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getPotvrdaLozinke() {
        return potvrdaLozinke;
    }

    public void setPotvrdaLozinke(String potvrdaLozinke) {
        this.potvrdaLozinke = potvrdaLozinke;
    }


    //metoda toString koja sluzi za ispis
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", email='" + email + '\'' +
                ", korisnickoIme='" + korisnickoIme + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", potvrdaLozinke='" + potvrdaLozinke + '\'' +
                '}';
    }
}
