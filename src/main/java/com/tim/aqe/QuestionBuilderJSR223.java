package com.tim.aqe;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.GenericWebApplicationContext;

import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
/**
 * Created by thodkins on 28/08/2017.
 */
@Service("qbbean")
public class QuestionBuilderJSR223 {

    private ResourceLoader resourceLoader;

    @Autowired
    public QuestionBuilderJSR223(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String buildQuestion(String filename) throws IOException, ScriptException {
        //ApplicationContext appContext =  new ClassP;
        //        new ClassPathXmlApplicationContext();
        //ResourceLoader loader
        Resource resource = resourceLoader.getResource(filename);
         //       appContext.getResource(filename);
        //FileReader script = resource.getInputStream();
        //F//ile file = resource.getFile();
        InputStreamReader script = new InputStreamReader(resource.getInputStream());
        /*ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(file, Map.class);
        String text = map.get("text");
        String script = map.get("script");*/

        //FileReader script  = new FileReader(filename);
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("groovy");
        //engine.put("input", text);
        //double answer = 0;
        //engine.put("answer", answer);
        String result = (String) engine.eval(script);
        //String answer = (String) engine.get("answer");
        //System.out.println(result.toString());
            if (script != null) {
                try {
                    script.close();
                } catch (IOException e) {
                    System.out.println("Failed to close the reader.");
                }
            }
        return result;

    }
}
