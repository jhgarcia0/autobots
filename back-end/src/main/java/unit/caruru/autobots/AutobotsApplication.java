package unit.caruru.autobots;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AutobotsApplication {
    @Autowired
    @Qualifier("applicationName")
    private String applicationName;

    @GetMapping("/")
    public String sayHello(@RequestParam(value="name", defaultValue = "applicationName") String name){
        if (name.equals("applicationName")){
            name = applicationName;
        }
        return String.format("Welcome to %s", name);
    }

    public static void main(String[] args) {
        SpringApplication.run(AutobotsApplication.class,args);
    }
}
