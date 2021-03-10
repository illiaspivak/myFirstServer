package sk.kosickaakademia.spivak.server;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(path = "/add")
    public ResponseEntity<String> addTwoNumbers(@RequestBody String input){
        try {
            JSONObject object = (JSONObject) new JSONParser().parse(input);
            int a = Integer.parseInt((String.valueOf(object.get("a"))));
            int b = Integer.parseInt((String.valueOf(object.get("b"))));
            int result = a+b;
            JSONObject res = new JSONObject();
            res.put("result",result);
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(res.toJSONString());
        } catch (ParseException e){
            e.printStackTrace();
        } catch (NumberFormatException e){
            JSONObject obj = new JSONObject();
            obj.put("error","Incorrect body of the request");
            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(obj.toJSONString());
        }
        return null;
    }
}
