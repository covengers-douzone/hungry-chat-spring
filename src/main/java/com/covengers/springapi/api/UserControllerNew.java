package com.covengers.springapi.api;


import com.covengers.springapi.dto.Sms;
import com.covengers.springapi.model.User;
import com.covengers.springapi.service.SmsService;
import com.covengers.springapi.service.UserServiceNew;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    ResponseEntity<User> join(@RequestBody User user){
        // 200 ok 보다는 201 created 가 좀 더 정확한 응답. 아래와 같이 바꾼다. created 는 uri 주소가 필요함
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/join").toUriString());
//        return ResponseEntity.created(uri).body(userServiceNew.saveUser(user));
//        return ResponseEntity.ok().body(userServiceNew.getUser(user.getUsername()));
        return ResponseEntity.ok().body(userServiceNew.saveUser(user));
    }

    @PostMapping(value = "/sms")
    public ResponseEntity<?> sendSms(@RequestBody Sms sms) throws Exception {
        Boolean result = smsService.sms(sms);
        if (result) {
            return new ResponseEntity<>("success", HttpStatus.OK);
        }
        return new ResponseEntity<>("fail", HttpStatus.EXPECTATION_FAILED);
//        return new ResponseEntity<>("fail", null, 200);
    }
}