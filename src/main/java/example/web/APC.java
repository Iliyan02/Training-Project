package example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/policy")
public class APC {

    @GetMapping("/aboutUs")
    private String aboutUs(){ return "about-us";}

    @GetMapping("/privacyPolicy")
    private String privacy(){
        return "privacy-policy";
    }
}
