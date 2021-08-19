package com.covengers.springapi.service;

import com.covengers.springapi.model.Sms;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SmsService{
    private static String CODENUMBER = "-9999";

    public Boolean sms(Sms sms) throws Exception {

        String api_key = "NCS24FKUXYQBKKP5";
        String api_secret = "OPTNWRSSEW1D8ANZMB2DN6QICTIF9BMG";

        Message coolsms = new Message(api_key, api_secret);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", sms.getRecipients());
        params.put("from", "01025271985");
        params.put("type", "SMS");
        params.put("text", sms.getText());
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
