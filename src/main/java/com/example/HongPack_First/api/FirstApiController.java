package com.example.HongPack_First.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//Json을 반환하는 RestApi용 Controller / 일반 Controller는 뷰 페이지를 반환한다.
public class FirstApiController {
    @GetMapping("/api/hello")
    public String hello() {
        return "Hello World";
    }
}
