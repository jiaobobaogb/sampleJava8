package com.britesnow.j8.DevTestBao;

import java.io.File;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import org.junit.Test;

public class JSTest {

	@Test
	public void testJS() throws Exception {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("nashorn");
        System.out.println("begain testJS:");
        
        engine.eval("var i = 1;");
        engine.eval("i+='love';");
        engine.eval("print(i)");
        engine.eval("print(new Date());");

        ScriptEngine engine1 = engineManager.getEngineByName("nashorn");
        try {
            engine1.eval("print(i)");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        engine.eval("function test(){print('love');}");
        engine.eval("test();");

        Object jsDate = engine.eval("new Date()");
        System.out.println("jsDate: "+jsDate);

        Invocable invocable = (Invocable) engine;
        invocable.invokeMethod(jsDate, "setDate", 1);
        System.out.println(jsDate);
        invocable.invokeFunction("test");

    }

}
