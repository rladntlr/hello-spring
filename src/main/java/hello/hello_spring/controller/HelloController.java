package hello.hello_spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") // /hello 에서 던진걸 받아 localhost:8080/hello
    public String hello(Model model) {
        model.addAttribute("data", "Hello!!");
        return "hello"; //templates의 hello로 가라
    }
}
