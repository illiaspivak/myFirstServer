package sk.kosickaakademia.spivak.server;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class JokeController {
    String joke1 = "I invented a new word today. Plagiarism.";
    String joke2 = "I have many jokes about unemployed people, sadly none of them work.";
    String joke3 = "I never make mistakes. …I thought I did once; but I was wrong.";
    List<String> list = new ArrayList<>();

    public JokeController(){
        list.add(joke1);
        list.add(joke2);
        list.add(joke3);
    }

    @GetMapping("/jokes")
    public ResponseEntity<String> getJokes(){
        JSONObject object = new JSONObject();
        /*
        object.put("joke1",joke1);
        object.put("joke2",joke2);
        object.put("joke3",joke3);
        */
        JSONArray jsonArray = new JSONArray();
        for(String s : list)
            jsonArray.add(s);
        object.put("jokes", jsonArray);
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(object.toJSONString());
    }

    @GetMapping("/joke/{id}")
    public ResponseEntity<String> getJokeById(@PathVariable Integer id){
        JSONObject object = new JSONObject();
        int status;
        if(id<1 || id>list.size()){
            object.put("error","Not joke:)");
            status = 404;
        }else{
            object.put("joke",list.get(id-1));
            status = 200;
        }
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(object.toJSONString());
    }

    @GetMapping("/joke")
    public  ResponseEntity<String> getRandomJoke(){
        JSONObject object = new JSONObject();
        int status;
        if(list.size()==0){
            object.put("error","Not joke:)");
            status = 404;
        }else {
            Random random = new Random();
            int index = random.nextInt(list.size());
            object.put("id",index);
            object.put("joke", list.get(index));
            status = 200;
        }
        return ResponseEntity.status(status).contentType(MediaType.APPLICATION_JSON).body(object.toJSONString());
    }

    @GetMapping("/addjoke")
    public List<String> addJoke(@RequestBody String input){
        try {
            list.add(input);
            return list;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
