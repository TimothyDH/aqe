package com.tim.aqe;
import groovy.lang.GroovyShell;
import org.junit.Test;

import javax.script.ScriptException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by thodkins on 28/08/2017.
 */
public class DesignTests {
    @Test
    public void simpleScriptTest() {
        GroovyShell shell = new GroovyShell();
        String script = "return 'hello world'";

        Object result = shell.evaluate(script);
        assertEquals(result, "hello world");
    }

    @Test
    public void executeFromJsonTest() throws IOException {
        QuestionBuilderGroovyShell qb = new QuestionBuilderGroovyShell();
        String result = qb.buildQuestion("file:/Users/thodkins//aqe/code/aqe/src/main/resources/data/questions2010.json");
        assertEquals(result, "test string with ++PLACEHOLDER++I added this bit");
    }

    @Test
    public void executeFromJsonTestJSR223() throws IOException {
        /*QuestionBuilderJSR223 qb = new QuestionBuilderJSR223();
        String result = null;
        try {
            //result = qb.buildQuestion("file:/Users/thodkins/aqe/code/aqe/src/main/resources/data/questions2010.json");
            result = qb.buildQuestion("file:/Users/thodkins//aqe/code/aqe/src/main/resources/scripts/groovy_man.groovy");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        assertEquals(result, "test string with ++PLACEHOLDER++I added this bit");*/
    }
}
