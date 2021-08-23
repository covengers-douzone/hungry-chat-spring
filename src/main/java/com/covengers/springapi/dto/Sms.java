package com.covengers.springapi.dto;

import lombok.Data;

@Data
public class Sms {
    private String recipients;
    private String text;
}
