package org.example.fileuploaddownload.repository;

import org.example.fileuploaddownload.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<FileEntity,Integer> {

    @Query(
            value = "select f from FileEntity  f where f.deletedAt is null "
    )
    public List<FileEntity>fayllar();

    @Query(
            value = "select * from file where id=:id and deleted_at is null ",
            nativeQuery = true
    )
    public FileEntity fayl(@Param("id")Integer id);

}
