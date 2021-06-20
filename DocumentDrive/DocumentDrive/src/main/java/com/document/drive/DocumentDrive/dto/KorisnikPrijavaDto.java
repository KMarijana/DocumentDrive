package com.document.drive.DocumentDrive.dto;

public class KorisnikPrijavaDto {
    //privatni atributi klase
    private long id;
    private String email;
    private String lozinka;

    //podrazumevani konstruktor
    public KorisnikPrijavaDto(){}

    //konstruktor sa parametrima
    public KorisnikPrijavaDto(long id, String email, String lozinka) {
        this.id = id;
        this.email = email;
        this.lozinka = lozinka;
    }

    //get i set metode atributa
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }


}
