package com.tim.aqe;

import com.fasterxml.jackson.databind.ObjectMapper;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.GenericWebApplicationContext;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

/**
 * Created by thodkins on 28/08/2017.
 */
public class QuestionBuilderGroovyShell {

    public String buildQuestion(String filename) throws IOException {
        ApplicationContext appContext = new GenericWebApplicationContext();
        //        new ClassPathXmlApplicationContext();

        Resource resource =
                appContext.getResource(filename);

        File file = resource.getFile();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(file, Map.class);

        String text = map.get("text");
        Binding binding = new Binding();
        binding.setVariable("input", text);
        GroovyShell shell = new GroovyShell(binding);

        String script = map.get("script");
        Object result = shell.evaluate(script);
        return ((String) result).toString();

    }
}
