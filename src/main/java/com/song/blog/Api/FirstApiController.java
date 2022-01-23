package com.song.blog.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // RestAPI용 컨트롤러! JSON을 반환하는 어린이
// RestController와 일반 Controller의 차이
// 반환하는 타입이 다름
// 일반 컨트롤러는 html 페이지를 반환하는 바면
// rest컨트롤러는 데이터, json  등을 반환할 수 있음
public class FirstApiController {

    @GetMapping("/api/hello")
    public String hello() {
        return "hello world";
    }
}
