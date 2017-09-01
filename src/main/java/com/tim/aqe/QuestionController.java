package com.tim.aqe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.script.ScriptException;
import java.io.IOException;


/**
 * Created by thodkins on 30/08/2017.
 */
@RestController
public class QuestionController {
    //private static String questions2010_part1_script = "file:/Users/thodkins/aqe/code/aqe/src/main/resources/scripts/groovy_man.groovy";
    private static String questions2010_part1_script = "classpath:scripts/groovy_man.groovy";
    @Autowired
    private QuestionBuilderJSR223 qb;


    @CrossOrigin
    @RequestMapping(value = "api/2010", method=RequestMethod.GET)
    public String getTestQuestions() {
        //QuestionBuilderJSR223 qb = new QuestionBuilderJSR223();
        String response = null;
        try {
            response =  qb.buildQuestion(questions2010_part1_script);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        //if(response != null){
            return response;
        /*}
        else{
            return "##ERROR##";
        }*/
    }


}
