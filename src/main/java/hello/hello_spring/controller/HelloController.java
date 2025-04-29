package hello.hello_spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") // /hello 에서 던진걸 받아 localhost:8080/hello
    public String hello(Model model) {
        model.addAttribute("data", "Hello!!");
        return "hello"; //templates의 hello로 가라
    }
    //http://localhost:8080/hello-mvc?name=spring!!!!!!!!
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { //name에 파라미터를 넘겨야함
        model.addAttribute("name", name);
        return "hello-template";
    }
    //http://localhost:8080/hello-string?name=spring!!!!!!!!
    @GetMapping("hello-string")
    @ResponseBody // http에서 body부분에 내가 데이터를 직접 넣어 주겠다
    public String helloString(@RequestParam("name") String name) {
        return "Hello " + name; // 화면이 아님 문자 그대로 내려줌
    }
    //http://localhost:8080/hello-api?name=spring!!!!!!!!
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); // 객체가 오면 json형식으로
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name; // key = name

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
