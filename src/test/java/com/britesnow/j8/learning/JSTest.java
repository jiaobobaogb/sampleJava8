package com.britesnow.j8.learning;

import java.io.File;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import org.junit.Test;

public class JSTest {

    public void testJS() throws Exception {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("nashorn");

        engine.eval("var i = 0;");
        engine.eval("i++;");
        engine.eval("print(i)");

        ScriptEngine engine1 = engineManager.getEngineByName("nashorn");
        try {
            engine1.eval("print(i)");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        engine.eval("function test(){print('test');}");
        engine.eval("test();");
        engine.eval("print(new Date());");

        ScriptObjectMirror testFunc = (ScriptObjectMirror) engine.eval("test");
        testFunc.call(null);

        Object jsDate = engine.eval("new Date()");
        System.out.println(jsDate);

        Invocable invocable = (Invocable) engine;
        invocable.invokeMethod(jsDate, "setDate", 1);
        System.out.println(jsDate);
        invocable.invokeFunction("test");

    }
    
    @Test
    public void testJava() throws Exception {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("nashorn");
        File javaExtendJS = new File("src/test/resources/jstest/sample/javaextend.js");
        engine.eval(new FileReader(javaExtendJS));
    }

    public void testJavaType() throws Exception {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("nashorn");
        File streamJS = new File("src/test/resources/jstest/sample/stream.js");
        engine.eval(new FileReader(streamJS));
    }
}
