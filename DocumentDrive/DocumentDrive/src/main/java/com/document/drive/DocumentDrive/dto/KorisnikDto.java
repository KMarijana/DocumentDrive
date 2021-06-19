package com.document.drive.DocumentDrive.dto;

public class KorisnikDto {

    //privatni atributi klase
    private long id;
    private String ime;
    private String prezime;
    private String email;
    private String korisnickoIme;
    private String lozinka;
    private String potvrdaLozinke;

    //podrazumevani konstruktor
    public KorisnikDto(){}

    //konstruktor sa parametrima
    public KorisnikDto(String email, String lozinka) {
        this.email = email;
        this.lozinka = lozinka;
    }

    public KorisnikDto(long id, String ime, String prezime, String email) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
    }

    //get i set metode atributa
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
}

