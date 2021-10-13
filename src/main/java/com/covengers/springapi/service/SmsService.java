package com.covengers.springapi.service;

import com.covengers.springapi.dto.Sms;
import net.nurigo.java_sdk.Coolsms;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SmsService{
    private static String CODENUMBER = "-9999";

    public Boolean sms(Sms sms) throws Exception {

        String api_key = "NCSRZPWQB9FFIWJR";
        String api_secret = "SUHXEHWWLXVQDV7FVKQUQYXBOWJVWPYD";

//        Coolsms coolsms = new Coolsms(api_key, api_secret);
        Message coolsms = new Message(api_key, api_secret);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", sms.getRecipients());
        params.put("from", "01074980731");
        params.put("type", "SMS");
        params.put("text", "Covengers! 인증번호는 " + sms.getText() + " 입니다.");
        params.put("app_version", "test app 1.2"); // application name and version

        System.out.println(params);
        try {
            CODENUMBER = sms.getText();
            System.out.println("인증번호는 " + CODENUMBER +" 입니다.");
            JSONObject obj = (JSONObject) coolsms.send(params);

            System.out.println(obj.toString() + ":::::");

        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
        return true;
    }
}
