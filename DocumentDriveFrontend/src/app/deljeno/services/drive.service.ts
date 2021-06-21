import { DatotekaUFolderu } from './../model/datotekaUFolderu.model';
import { FolderNovi } from './../model/folderNovi.model';
import { HttpClient, HttpEvent, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Drive } from '../model/drive.model';
import { Korisnik } from '../model/korisnik.model';

@Injectable({
  providedIn: 'root',
})

// klasa koja služi za povezivanje sa aplikativnim slojem, vrši se prosleđivanje odgovarajućih parametara metodama kreiranim u java spring boot-u
export class DriveService {
  DRIVE_URL = 'http://localhost:8080/v1';
  korisnik: Korisnik;

  constructor(private httpClient: HttpClient) {
    this.korisnik = JSON.parse(localStorage.getItem('korisnik')) as Korisnik;

  }

  ucitajSadrzajGlavnogFoldera(): Observable<Drive> {
    console.log(this.korisnik.id);

    return this.httpClient.post<Drive>(
      this.DRIVE_URL + '/ucitajSadrzaj',
      this.korisnik.id
    );
  }

  obrisiDatoteku(datotekaId: number) {
    return this.httpClient.post(this.DRIVE_URL + '/obrisiDatoteku', datotekaId);
  }

  obrisiFolder(folderId: number) {
    return this.httpClient.post(this.DRIVE_URL + '/obrisiFolder', folderId);
  }

  ucitajDatoteku(folderId: number, fajl: File): Observable<HttpEvent<any>> {

    const formData = new FormData();
    formData.append('korisnikId', this.korisnik.id.toString());
    formData.append('folderId', folderId.toString());
    formData.append('datoteka', fajl, fajl.name);

    return this.httpClient.post(this.DRIVE_URL + '/ucitajDatoteku', formData, {
      observe: 'events',
    });
  }

  podeliLink() {
    return this.httpClient.post(
      this.DRIVE_URL + '/podeliLink',
      this.korisnik.id,
      { responseType: 'text' }
    );
  }

  kreirajFolder(naziv: string): Observable<FolderNovi> {
    return this.httpClient.post(this.DRIVE_URL + '/kreirajFolder', {
      driveId: this.korisnik.id,
      naziv: naziv
    });
  }

  ucitajSadrzajFoldera(folderId:number):Observable<DatotekaUFolderu> {
    return this.httpClient.post<DatotekaUFolderu>(this.DRIVE_URL+"/prikaziSadrzajFoldera", folderId);
  }

  preuzmiDatoteku(datotekaId: number): any {
    let params = new HttpParams();
    params = params.append("datotekaId", datotekaId!.toString());

    return this.httpClient.get(this.DRIVE_URL + '/preuzmiDatoteku', {
      params: params,
      observe: 'response',
      responseType: 'arraybuffer'
    });
  }
}
