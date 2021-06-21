package com.document.drive.DocumentDrive.repository;

import com.document.drive.DocumentDrive.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

    //MySql upit koji se koristi pri prijavljivanju
    @Query("SELECT u FROM Korisnik u WHERE u.email = ?1 AND u.lozinka = ?2")
    Korisnik login(String email, String lozinka);

}
