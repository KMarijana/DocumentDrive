import { KorisnikRegistracija } from './../model/korisnikRegistracija.model';
import { Korisnik } from './../model/korisnik.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

// klasa koja služi za povezivanje sa aplikativnim slojem, vrši se prosleđivanje odgovarajućih parametara metodama kreiranim u java spring boot-u
export class KorisnikService {

  DRIVE_URL = "http://localhost:8080/v1";
  constructor(private httpClient: HttpClient) {
  }

  prijava(korisnik:Korisnik):Observable<Korisnik>{
    return this.httpClient.post<Korisnik>(this.DRIVE_URL + "/login", {"email":korisnik.email, "lozinka":korisnik.lozinka});
  }

  registracija(korisnikReg:KorisnikRegistracija):Observable<KorisnikRegistracija>{
    return this.httpClient.post<KorisnikRegistracija>(this.DRIVE_URL + "/registracija", korisnikReg);
  }
}
