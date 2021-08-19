package com.covengers.springapi.service;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Service
public class SmsService{
    private static String CODENUMBER = "-9999";

    public Boolean sms(HttpServletRequest request) throws Exception {
        String api_key = "NCS24FKUXYQBKKP5";
        String api_secret = "OPTNWRSSEW1D8ANZMB2DN6QICTIF9BMG";

        Message coolsms = new Message(api_key, api_secret);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", (String)request.getParameter("to"));
        params.put("from", "01025271985");
        params.put("type", "SMS");
        params.put("text", (String)request.getParameter("text"));
        params.put("app_version", "test app 1.2"); // application name and version

        System.out.println(params);
        try {
            CODENUMBER = (String)request.getParameter("text");
            System.out.println("인증번호는 " + CODENUMBER +" 입니다.");
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());

        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
        return true;
    }
}
