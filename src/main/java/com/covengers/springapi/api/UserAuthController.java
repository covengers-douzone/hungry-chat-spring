package com.covengers.springapi.api;

import com.covengers.springapi.dto.JsonResult;
import com.covengers.springapi.model.User;
import com.covengers.springapi.service.UserServiceNew;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserAuthController {
    private final UserServiceNew userServiceNew;

    @ApiOperation(value = "Settings로부터의 회원 비밀번호 변경")
    @PostMapping("/passwordupdate")
    ResponseEntity<?> userPasswordUpdate(@RequestBody User data) throws Exception {

        Boolean result = userServiceNew.pwUpdate(data);
        if(result){
            System.out.println("result: " + userServiceNew.pwUpdate(data));
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new JsonResult("잠시후 다시 시도해 주세요.", 500));
    }
}
