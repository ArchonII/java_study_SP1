package ru.experince.star_foto_2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.experince.star_foto_2.Entity.Apod;
import ru.experince.star_foto_2.Entity.ApodImage;
import ru.experince.star_foto_2.Entity.ApodToApodImage;
import ru.experince.star_foto_2.Entity.User;
import ru.experince.star_foto_2.Server.ApodRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ApodImageTest {

    @Autowired
    private ApodRepository apod_rep;

    @Test
    void ConvertTest1() {
        Apod ap = new Apod("Hunter Wells", LocalDate.of(2025, 3, 20),
                "Recorded from 2024 March 10, to 2025 March 1, this composited series of images reveals a pattern in the seasonal drift of the Sun's daily motion through planet Earth's sky. Known to some as an analemma, the figure-eight curve was captured in exposures taken on the indicated dates only at 18:38 UTC from the exact same location south of Stephenville, Texas. The Sun's position on the 2024 solstice dates of June 20 and December 21 would be at the top and bottom of the curve and correspond to the astronomical beginning of summer and winter in the north. Points that lie along the curve half-way between the solstices would mark the equinoxes. The 2024 equinox on September 22, and in 2025 the equinox on March 20 (today) are the start of northern fall and spring. And since one of the exposures was made on 2024 April 8 from the Stephenville location at 18:38:40 UTC, this analemma project also reveals the solar corona in planet Earth's sky during a total solar eclipse.",
                "https://apod.nasa.gov/apod/image/2503/HunterWells_submission3-3.png",
                "image", "v1",
                "The Solar Eclipse Analemma Project",
                "https://apod.nasa.gov/apod/image/2503/HunterWells_submission3-3labelled1024.jpg"
        );
        User u = new User("admin");
        ApodImage res = ApodToApodImage.ApodToImg(ap, u);
        System.out.println("id_ " + res.getId());
        System.out.println(res.getUser().getUname());
        assertEquals(ap.getDate(),res.getDategen());
    }

    @Test
    void GetApodImageFromDB() {
        LocalDate dt = LocalDate.of(2025,4,17);
        ApodImage res = apod_rep.findByDatefetchAndUserId(dt, 1).get(0);
        assertEquals(dt, res.getDatefetch());
        assertEquals(1, res.getUser().getId());
        assertEquals("Virgo Cluster Galaxies", res.getTitle());

    }



}
