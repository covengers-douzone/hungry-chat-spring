package com.covengers.springapi.api;

import com.covengers.springapi.dto.JsonResult;
import com.covengers.springapi.dto.Sms;
import com.covengers.springapi.model.User;
import com.covengers.springapi.service.SmsService;
import com.covengers.springapi.service.UserServiceNew;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if(userServiceNew.getUser(user.getUsername()) == null){
            if(userServiceNew.findByPhoneNumber(user.getPhoneNumber()) != null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResult("이미 등록된 번호입니다.", 400));
            }

            userServiceNew.saveUser(user);
            User member = new User();
            member.setName(user.getName());
            member.setUsername(user.getUsername());
            return ResponseEntity.ok().body(new JsonResult(member));
        }
        return ResponseEntity.badRequest().body(new JsonResult("이미 존재하는 아이디입니다.", 400));
    }

    @PostMapping(value = "/sms")
    public ResponseEntity<?> sendSms(@RequestBody Sms sms) throws Exception {
        System.out.println(sms.getRecipients() + ":" + sms.getText());
        Boolean result = smsService.sms(sms);
        if (result) {
            return ResponseEntity.ok().body(new JsonResult(sms));
        }
        //503 Service Unavailable
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new JsonResult("다시 시도해주세요", 503));
    }

    @PostMapping("/useridsearch")
    ResponseEntity<?> userIdSearch(@RequestBody User user)throws Exception {
        System.out.println("userinfo: "+userServiceNew.findByNameAndPhoneNumber(user.getName(), user.getPhoneNumber()));

        Map<String, String> map = new HashMap<>();
        map.put("username", userServiceNew.findByNameAndPhoneNumber(user.getName(), user.getPhoneNumber()));
        System.out.println("map: " + map);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/passwordupdate")
    ResponseEntity<?> userPasswordUpdate(@RequestBody User data)throws Exception {

        Boolean result = userServiceNew.pwUpdate(data);
        if(result){
            System.out.println("result: " + userServiceNew.pwUpdate(data));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new JsonResult("잠시후 다시 시도해 주세요.", 500));
    }


}
