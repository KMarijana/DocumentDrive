package com.document.drive.DocumentDrive.repository;

import com.document.drive.DocumentDrive.model.Drive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriveRepository extends JpaRepository<Drive, Long> {
}
