package com.document.drive.DocumentDrive.repository;

import com.document.drive.DocumentDrive.model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {

    // query za prikaz foldera u zavisnosti od id-a
    @Query("SELECT f FROM Folder f where f.id = ?1")
    Folder prikaziFolder(long folderId);

    // query za birsanje foldera prema njegovom id-u
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "DELETE FROM folder WHERE folder_id = ?1", nativeQuery = true)
    void obrisiFolder(long folderId);

}
