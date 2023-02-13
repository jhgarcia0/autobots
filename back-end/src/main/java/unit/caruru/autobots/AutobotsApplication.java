package unit.caruru.autobots;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AutobotsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutobotsApplication.class, args);
    }
    @GetMapping("/")
    public String sayHello(@RequestParam(value="name", defaultValue = "World!") String name){
        return String.format("Hello, %s", name);
    }
}
