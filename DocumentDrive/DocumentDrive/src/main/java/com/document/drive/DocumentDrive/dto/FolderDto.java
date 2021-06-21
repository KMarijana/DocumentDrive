package com.document.drive.DocumentDrive.dto;

public class FolderDto {
    //privatni atributi
    private long driveId;
    private String naziv;

    // konstruktor sa parametrima
    public FolderDto(long driveId, String naziv) {
        this.driveId = driveId;
        this.naziv = naziv;
    }

    // get i set metode
    public long getDriveId() {
        return driveId;
    }

    public void setDriveId(long driveId) {
        this.driveId = driveId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    // toString metoda za ispis
    @Override
    public String toString() {
        return "FolderDto{" +
                "driveId=" + driveId +
                ", naziv='" + naziv + '\'' +
                '}';
    }
}
