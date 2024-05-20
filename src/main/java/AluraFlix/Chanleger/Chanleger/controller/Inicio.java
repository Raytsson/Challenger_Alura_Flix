package AluraFlix.Chanleger.Chanleger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Inicio {

    @GetMapping
    public String hello(){
        return "Hello world! ";
    }
}
