package com.example.HongPack_First.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String nicetomeetyou(Model model){
        model.addAttribute("username", "dfjlsd");
        return "greetings"; // templets/greetings.mustaches를 브라우저로 전송
    }
    @GetMapping("bye")
    public String bye(Model model){
        model.addAttribute("username","건희");
        return "bye";
    }
}
