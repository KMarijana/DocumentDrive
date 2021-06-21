import { DriveService } from './../deljeno/services/drive.service';
import { KorisnikService } from './../deljeno/services/korisnik.service';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Korisnik } from '../deljeno/model/korisnik.model';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

  korisnik:Korisnik;
  korisnikId:number;

  constructor(
    private korisnikService: KorisnikService,
    private router:Router,
  )
  {
    this.korisnik = {};
  }

  ngOnInit(): void {
    localStorage.clear();
  }

  prijaviSe() {
    this.korisnikService.prijava(this.korisnik).subscribe(
      // rezultat koji vraca
      (r) => {
        // brisanje prethodnog korisnika, i dodavanje novog korisnika
        localStorage.clear();
        localStorage.setItem("korisnik", JSON.stringify(r));

        // preusmeravanje na drive stranicu
        this.router.navigate(["drive"]);
      },
      // poruka o gresci se pojavljuje u alert prozoru
      (e) => alert("Email ili lozinka ne odgovaraju")
    )
  }

  registrujSe() {
    // preusmeravanje na registruj stranicu
    this.router.navigate(["registruj"]);
  }

}
