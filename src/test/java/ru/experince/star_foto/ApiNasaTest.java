package ru.experince.star_foto;
import io.swagger.v3.core.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.experince.star_foto.Entity.Apod;
import ru.experince.star_foto.server.ApodClientOuter;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ApiNasaTest {

    @Autowired
    private ApodClientOuter client;

    @Test
    void getJson1() {
        try {
            Apod res = client.getApod("DEMO_KEY");
            assertEquals("image", res.getMedia_type());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    void getJson2() {
        try {
            LocalDate t1 = LocalDate.of(2025, 3, 20);
            Apod res = client.getApod("DEMO_KEY", "2025-03-20");
            assertEquals(t1, res.getDate());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}