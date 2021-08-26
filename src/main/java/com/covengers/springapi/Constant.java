package com.covengers.springapi;

public class Constant {
    public static String AUTH_HEADER = "Authorization";
    public static String TOKEN_NOT_FOUND = "토큰이 존재하지 않습니다.";
    public static String TOKEN_EXPIRED = "토큰이 만료되었습니다.";
    public static String AUTH_INVALID = "토큰이 유효하지 않습니다.";
    public static final String SECRET_KEY = "asjcvklaeiejqrhwjkfghadslgbdafjhasfagwbajhdbadjfaks;dfj";
    public static final long EXPIRE_TIME = 60*500; // 30 sec
    public static final String SUBJECT = "tokenName";
}
