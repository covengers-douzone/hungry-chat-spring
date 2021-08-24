package com.covengers.springapi.api;

import com.covengers.springapi.dto.JsonResult;
import com.covengers.springapi.dto.Sms;
import com.covengers.springapi.model.User;
import com.covengers.springapi.service.SmsService;
import com.covengers.springapi.service.UserServiceNew;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserControllerNew {
    private final UserServiceNew userServiceNew;
    private final SmsService smsService;

    @GetMapping("/users")
    ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userServiceNew.getUsers());
    }

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody User user){
        // 200 ok 보다는 201 created 가 좀 더 정확한 응답. 아래와 같이 바꾼다. created 는 uri 주소가 필요함
        //URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/join").toUriString());
        //return ResponseEntity.created(uri).body(userServiceNew.saveUser(user));
        System.out.println(user.getUsername());
        System.out.println("유저를 확인합니다 !!!!!!!!!!!!!!!!!!!!! : " + userServiceNew.getUser(user.getUsername()));
        System.out.println(userServiceNew.getUser(user.getUsername()) == null);

        if(userServiceNew.getUser(user.getUsername()) == null){
            userServiceNew.saveUser(user);
            return ResponseEntity.ok().body(new JsonResult(user));
        }
        return ResponseEntity.badRequest().body(new JsonResult( "이미 존재하는 아이디입니다.", 404));
    }



    @PostMapping(value = "/sms")
    public ResponseEntity<?> sendSms(@RequestBody Sms sms) throws Exception {
        if (sms.getRecipients() == null || sms.getText() == null){
            return ResponseEntity.badRequest().build();
        }
        Boolean result = smsService.sms(sms);
        System.out.println(sms.getRecipients() + ":" + sms.getText());
        if (result) {
            return ResponseEntity.ok().build();
        }
//        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        return ResponseEntity.badRequest().build();
    }
}
