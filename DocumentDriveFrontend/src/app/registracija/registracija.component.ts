import { DriveService } from './../deljeno/services/drive.service';
import { KorisnikService } from './../deljeno/services/korisnik.service';
import { Component, OnInit } from '@angular/core';
import { KorisnikRegistracija } from '../deljeno/model/korisnikRegistracija.model';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'app-registracija',
  templateUrl: './registracija.component.html',
  styleUrls: ['./registracija.component.css'],
})
export class RegistracijaComponent implements OnInit {

  korisnikReg:KorisnikRegistracija;

  constructor(
   private korisnikService: KorisnikService,
   private router:Router
  ) {
    this.korisnikReg = {};
  }

  ngOnInit(): void {
  }


  registrujSe() {
    this.korisnikService.registracija(this.korisnikReg).subscribe(
      // rezultat koji vraca
      (r) => {
          localStorage.setItem("korisnik", JSON.stringify(r));
          if(this.korisnikReg.lozinka===this.korisnikReg.potvrdaLozinke){
          // preusmeravanje na login stranicu
            this.router.navigate(["login"]);
          }
        else
          alert("Lozinka nije ista");

      },
      // poruka o gresci se pojavljuje u alert prozoru
      (e) => alert("Morate popuniti sva polja")
    )
  }

  prijaviSe() {
    // preusmeravanje na login stranicu
    this.router.navigate(["login"]);
  }
}
