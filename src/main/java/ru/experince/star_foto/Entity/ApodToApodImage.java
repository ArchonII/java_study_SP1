package ru.experince.star_foto.Entity;

public class ApodToApodImage {
    public static ApodImage ApodToImg(Apod src, User _user) {
        return new ApodImage(src.getTitle(), src.getUrl(), src.getDate(), _user);
    }

//    public static Apod ApodToImg(ApodImage src) {
//
//    }


}
