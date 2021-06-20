package com.document.drive.DocumentDrive.repository;

import com.document.drive.DocumentDrive.model.Datoteka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatotekaRepository extends JpaRepository<Datoteka, Long> {

    // query za rad sa bazom podataka, gde se brise datoteka u zavisnosti od unetog id-a
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "DELETE FROM datoteka WHERE datoteka_id = ?1", nativeQuery = true)
    void obrisiDatoteku(long datotekaId);

    List<Datoteka> findAllByFolderId(long folderId);
}
