package ru.experince.star_foto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import ru.experince.star_foto.Entity.Apod;
import ru.experince.star_foto.Entity.ApodImage;
import ru.experince.star_foto.server.ApodClient;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ApiInnerTest {

    @Autowired
    private ApodClient client_i;

    @Test
    void getApod1() {
        try {
            ApodImage res = client_i.getApodnow(1);
            assertEquals(LocalDate.now(), res.getDatefetch());
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals(1, 2);
        }

    }


}