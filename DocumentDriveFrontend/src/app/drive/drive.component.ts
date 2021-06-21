import { DatotekaUFolderu } from './../deljeno/model/datotekaUFolderu.model';
import { Folder } from './../deljeno/model/folder.model';
import { DriveService } from './../deljeno/services/drive.service';
import { Component, OnInit } from '@angular/core';
import { Drive } from '../deljeno/model/drive.model';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { HttpEvent, HttpEventType } from '@angular/common/http';

@Component({
  selector: 'app-drive',
  templateUrl: './drive.component.html',
  styleUrls: ['./drive.component.css'],
})
export class DriveComponent implements OnInit {
  // atributi
  sadrzajDrive: Drive;
  folder: Folder;
  link = '';
  newFolderName = '';
  currentFolderId: number;
  fileEvent: any;
  currentFolderName = '';
  folderSadrzaj: DatotekaUFolderu = null;

  // konstruktor
  constructor(
    private driveService: DriveService,
    private modalService: NgbModal,
    private router: Router
  ) {
    this.sadrzajDrive = {};
    this.currentFolderId = -1;
  }

  // ucitava se sadržaj korisnikovog drajva koji se prijavio na svoj nalog, i prikazuju mu se folderi i datoteke ako postoje
  ngOnInit(): void {
    this.driveService.ucitajSadrzajGlavnogFoldera().subscribe(
      (r) => {
        this.sadrzajDrive = r;
        this.folderSadrzaj = null;
        console.log(this.sadrzajDrive);
        console.log(this.folderSadrzaj);
      },
      (e) => console.log(e)
    );
  }

  // služi da unutar kreiranog foldera prikaze njegov sadržaj, odnosno datoteku
  prikaziFolder(poz: number) {
    this.driveService
      .ucitajSadrzajFoldera(this.sadrzajDrive.folderi[poz].id)
      .subscribe(
        (fajl) => {
          this.sadrzajDrive = null;
          this.folderSadrzaj = fajl;
          console.log(fajl);

          this.currentFolderId = fajl!.folderId!;
          console.log("current id "+this.currentFolderId);

        },
        (e) => console.log(e)
      );
  }

  // brisanje izabrane datoteke pomoću njenog id-a
  obrisiDatoteku(pozicija: number) {
    this.driveService
      .obrisiDatoteku(
        this.folderSadrzaj !== null
          ? this.folderSadrzaj.datotekaId
          : this.sadrzajDrive.datoteke[pozicija].id
      )
      .subscribe(
        (r) => {
          this.refreshCurrentFolder();
        },
        (e) => console.log(e)
      );
  }

  // brisanje foldera i njegovog sadržaja koristeći id foldera
  obrisiFolder(pozicija: number) {
    this.driveService
      .obrisiFolder(this.sadrzajDrive.folderi[pozicija].id)
      .subscribe(
        (r) => this.refreshCurrentFolder(),
        (e) => console.log(e)
      );
  }

  // događaj za dodavanje fajla
  dodajFajl(event) {
    this.fileEvent = event;
  }

  // ucitavanje (upload) datoteke na drajv, daoteka se moze i ne mora ucitati neki folder
  ucitajDatoteku() {
    const files: FileList = this.fileEvent.target.files;

    this.driveService
      .ucitajDatoteku(this.currentFolderId, files[0])
      .subscribe((event: HttpEvent<any>) => {
        if (event.type === HttpEventType.Response) {
          this.refreshCurrentFolder();
        }
      });
  }

  // moteoda za kreiranje linka za deljenje datoteke
  podeliLink() {
    this.driveService.podeliLink().subscribe(
      (r) => {
        this.link = r as string;
      },

      (e) => console.log(e)
    );
  }

  // preuzimanje datoteke
  download(poz: number) {
    const file: Drive = this.sadrzajDrive;
    this.driveService
      .preuzmiDatoteku(
        this.folderSadrzaj !== null
          ? this.folderSadrzaj.datotekaId
          : file.datoteke[poz].id
      )
      .subscribe(
        (data) => {
          const nazivFajla = data.headers.get('File-Name');

          // cuvanje datoteke u binarnom obliku
          const blob = new Blob([data.body], {
            type: 'application/octet-stream',
          });

          // pravljenje nevidljivog a taga (linka)
          const element = document.createElement('a');
          //blob na putanju linka
          element.href = URL.createObjectURL(blob);
          // dodeljivanje imena fajlu
          element.download = nazivFajla;
          // dodavanje a taga u body
          document.body.appendChild(element);
          // autoclick na a tag radi radi zapocinjanja preuzimanja datotke na hard disku
          element.click();
        },
        (e) => console.log(e)
      );
  }

  // odjavljivanje korisnika sa naloga, briše se iz memorije i vrši se preusmeravanje na login stranicu
  odjaviSe() {
    localStorage.clear();
    sessionStorage.clear();
    this.router.navigate(['login']);
  }

  // kada se klikne na ikoncu za kreirnaje novog foldera otvara se prozor (modal) gde se upisuje naziv foldera
  openNewFolderModal(newFolder) {
    this.modalService.open(newFolder, { ariaLabelledBy: 'folder' }).result.then(
      (result) => {
        // proverava da li je korisnik kliknuo dugme save
        if (result === 'Save') {
          console.log(this.newFolderName);

          this.driveService.kreirajFolder(this.newFolderName).subscribe(
            (_) => {
              this.refreshCurrentFolder();
            },
            (e) => console.log(e)
          );
        }
      },
      (_) => {}
    );
  }

  // klikom na ikonicu ucitaj dokument vrši su učitavanje datoteke sa korisnikovg računara
  openUploadModal(upload) {
    this.modalService.open(upload, { ariaLabelledBy: 'upload' }).result.then(
      (result) => {
        // ako je korisnik kliknuo dugme za učitavanje, datoteka se ucitava na korisnikov drajv
        if (result === 'Upload') {
          this.ucitajDatoteku();
        }
      },
      (_) => {}
    );
  }

  // info modal otvara prozor u kome su prikazane informacije o softveru
  openInfoModal(info) {
    this.modalService.open(info, { ariaLabelledBy: 'info' }).result.then(
      (result) => {},
      (_) => {}
    );
  }

  // kada se klikne na ikonicu za deljenje fajla ili foldera, poziva se metoda za generisanje linka
  openShareModal(share) {
    let modal = this.modalService.open(share, { ariaLabelledBy: 'share' });

    modal.shown.subscribe((_) => this.podeliLink());
  }

  // metoda za osvežavanje sadržaja foldera odnosno korisnikovog drive-a
  refreshCurrentFolder() {
    this.driveService.ucitajSadrzajGlavnogFoldera().subscribe(
      (r) => {
        this.sadrzajDrive = r;
        this.folderSadrzaj = null;
      },
      (e) => console.log(e)
    );
  }
}
