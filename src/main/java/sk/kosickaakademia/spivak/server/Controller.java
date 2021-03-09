package sk.kosickaakademia.spivak.server;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class Controller {

    @RequestMapping("/hello")
    public String getHello(){
        return "Hi! What do you think about it?";
    }

    @RequestMapping(path = "/hi/{user}")
    public String getUser(@PathVariable String user){
        return "Hi "+ user + "! Are you hungry?";
    }

    @RequestMapping("/time")
    public String getTime(){
        return  new Date().toString();
    }
}
