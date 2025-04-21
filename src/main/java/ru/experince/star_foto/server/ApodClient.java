package ru.experince.star_foto.server;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.experince.star_foto.Entity.Apod;
import ru.experince.star_foto.Entity.ApodImage;

import java.util.List;

@FeignClient(name = "innerclient", url = "http://localhost:8083/")
public interface ApodClient {
    @RequestMapping(method = RequestMethod.GET, value = "/inner/apod/now")
    ApodImage getApodnow(@RequestParam(value="id_user") long id_user);

    @RequestMapping(method = RequestMethod.GET, value = "/inner/apod/d/{date}")
    ApodImage getApodDate(@RequestParam(value="id_user") long id_user, @RequestParam(value="date") String date);

    @RequestMapping(method = RequestMethod.GET, value = "/inner/apod/c/{count}")
    List<ApodImage> getApodCount(@RequestParam(value="id_user") long id_user, @RequestParam(value="count") int count);



}