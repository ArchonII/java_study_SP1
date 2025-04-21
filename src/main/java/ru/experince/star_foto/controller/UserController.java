package ru.experince.star_foto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.experince.star_foto.Entity.ApodImage;
import ru.experince.star_foto.Entity.User;
import ru.experince.star_foto.Repository.UserRepository;
import ru.experince.star_foto.server.ApodClient;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequiredArgsConstructor
@Tag(name = "Controller for work with users", description = "API для управления пользователями")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private ApodClient client;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Qualifier("UserRepository")
    @GetMapping("/apod/now")
    @Operation(summary = "", description = "")
    @ApiResponse(responseCode = "200", description = "")
    @ApiResponse(responseCode = "401", description = "пользователь не найден")
    public ResponseEntity<String> getApodImg (@RequestParam String in_user) {

        try {
            List<User> user = userRepository.findByUnameLike(in_user);

            if (user.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            //TODO return starfield image url
            ApodImage img = client.getApodnow(user.get(0).getId());
            return new ResponseEntity<>(img.getUrl(), HttpStatus.OK);
        }

        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/apod/one/{date}")
    @Operation(summary = "", description = "")
    @ApiResponse(responseCode = "200", description = "")
    @ApiResponse(responseCode = "401", description = "пользователь не найден")
    public ResponseEntity<String> getApodImgDate(@RequestParam LocalDateTime dt, @RequestParam String in_user) {

        try {
            List<User> user = userRepository.findByUnameLike(in_user);

            if (user.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            //TODO return starfield image url
            ApodImage img = client.getApodDate(user.get(0).getId(), dt.format(DateTimeFormatter.ISO_DATE));
            return new ResponseEntity<>(img.getUrl(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @GetMapping("/apod/list/{count}")
    @Operation(summary = "", description = "")
    @ApiResponse(responseCode = "200", description = "")
    @ApiResponse(responseCode = "401", description = "пользователь не найден")
    public ResponseEntity<List<String>> getApodImgListRand(@RequestParam int count, @RequestParam String in_user) {

        try {
            List<User> user = userRepository.findByUnameLike(in_user);

            if (user.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            //TODO return starfield image url
            List<ApodImage> list_src = client.getApodCount(user.get(0).getId(), count);
            List<String> list_res = new ArrayList<>();

            return new ResponseEntity<>(list_res, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user/create")
    @Operation(summary = "", description = "")
    public ResponseEntity<User> createUser (@RequestParam String user) {

        try {
            User _u = userRepository
                    .save(new User(user));
            return new ResponseEntity<>(_u, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}