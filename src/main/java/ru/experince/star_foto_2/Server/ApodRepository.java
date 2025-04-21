package ru.experince.star_foto_2.Server;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.experince.star_foto_2.Entity.ApodImage;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ApodRepository extends JpaRepository<ApodImage, Long> {
//    @Query("select * from apod_image ai where ai.dateFetch = :date_fetch and ai.user = :id_user")
    List<ApodImage> findByDatefetchAndUserId(LocalDate dt_fetch, long id_user);
}
