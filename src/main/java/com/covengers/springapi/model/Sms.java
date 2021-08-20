package com.covengers.springapi.model;

import lombok.Data;

@Data
public class Sms {
    private String recipients;
    private String text;
}
