package ru.experince.star_foto_2.Server;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.experince.star_foto_2.Entity.Apod;
import ru.experince.star_foto_2.Entity.ApodImage;
import ru.experince.star_foto_2.Entity.ApodToApodImage;
import ru.experince.star_foto_2.Entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequiredArgsConstructor
public class ApodContoller {

    @Autowired
    private ApodRepository apod_rep;
    @Autowired
    private UserRepository user_rep;
    @Autowired
    private ApodClientOuter outer;


    @GetMapping("/inner/apod/now")
    public ResponseEntity<ApodImage> getApod (@RequestParam long id_user) {

        //TODO connect to DB
        Apod img_obj = outer.getApod("DEMO_KEY");
        Optional<User> u = user_rep.findById(id_user);
        ApodImage img;
        try {
            List<ApodImage> cur_apod = apod_rep.findByDatefetchAndUserId(LocalDate.now(), id_user);
            if (cur_apod.isEmpty()) {
                img = apod_rep.save(ApodToApodImage.ApodToImg(img_obj, u.get()));
            }
            else {
                img = cur_apod.get(0);
            }

        } catch (Exception e) {
            String message = e.getMessage();

            throw new RuntimeException(e);
        }


        return new ResponseEntity<ApodImage>(img, HttpStatus.OK);
    }

}
