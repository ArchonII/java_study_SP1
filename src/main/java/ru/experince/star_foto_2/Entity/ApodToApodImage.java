package ru.experince.star_foto_2.Entity;

import ru.experince.star_foto_2.Entity.User;
import ru.experince.star_foto_2.Entity.ApodImage;
import ru.experince.star_foto_2.Entity.Apod;

public class ApodToApodImage {
    public static ApodImage ApodToImg(Apod src, User _user) {
        return new ApodImage(src.getTitle(), src.getUrl(), src.getDate(), _user);
    }

//    public static Apod ApodToImg(ApodImage src) {
//
//    }


}
