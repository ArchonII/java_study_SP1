package ru.experince.star_foto.server;

import io.swagger.v3.core.util.Json;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.experince.star_foto.Entity.Apod;
import ru.experince.star_foto.Entity.ApodImage;

import java.util.List;

@FeignClient(name = "outerapod", url = "https://api.nasa.gov/planetary/")
public interface ApodClientOuter {
    @RequestMapping(method = RequestMethod.GET, value = "/apod")
    Apod getApod(@RequestParam(value="api_key") String key);

    @RequestMapping(method = RequestMethod.GET, value = "/apod")
    Apod getApod(@RequestParam(value="api_key") String key, @RequestParam(value="date") String date);

//    @RequestMapping(method = RequestMethod.GET, value = "/apod")
//    Json getApodDate();

}
