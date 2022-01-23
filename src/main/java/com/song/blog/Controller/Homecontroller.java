package com.song.blog.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Homecontroller {

    @GetMapping("/hi")
    public String hi(Model model) {

        model.addAttribute("username","송정");

        return "hello";
    }

    @GetMapping("/bye")
    public String bye(Model model){
        model.addAttribute("username","송정");
        return "goodbye";
    }




}
