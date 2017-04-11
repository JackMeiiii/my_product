package com.meihaifeng.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.alibaba.fastjson.JSONObject;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/1/4
 * @description
 */
public class CoordinateTransfrom {

    public static Map<String,Object> getValues() throws FileNotFoundException, ScriptException, NoSuchMethodException {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("javascript");

        //加载js文件
        scriptEngine.eval(new FileReader("E:\\workplace\\cloud-gis-modules\\gis-manage\\src\\main\\webapp\\html\\DOMCore.js"));
        scriptEngine.eval(new FileReader("E:\\workplace\\cloud-gis-modules\\gis-manage\\src\\main\\webapp\\html\\jquery-3.1.1.js"));
        scriptEngine.eval(new FileReader("E:\\workplace\\cloud-gis-modules\\gis-manage\\src\\main\\webapp\\html\\droreMap.js"));
        Invocable invocable = (Invocable) scriptEngine;

        //设置参数变量
        Object param = new JSONObject();
        param =  scriptEngine.get("mapdata");

        String obs1 = String.valueOf(scriptEngine.get("ob1"));
        String obs2 = String.valueOf(scriptEngine.get("ob2"));
        String obs3 = String.valueOf(scriptEngine.get("ob3"));
        String obs4 = String.valueOf(scriptEngine.get("ob4"));
        String obs5 = String.valueOf(scriptEngine.get("ob5"));
        String obs6 = String.valueOf(scriptEngine.get("ob6"));
        String obs7 = String.valueOf(scriptEngine.get("ob7"));
        String obs8 = String.valueOf(scriptEngine.get("ob8"));
        Object obs9 = scriptEngine.get("ob9");



//        String value = String.valueOf(invocable.invokeFunction("transformLat","123","124"));
//        String value1 = String.valueOf(invocable.invokeFunction("initGoMap","gmap"));
        String value2 = String.valueOf(invocable.invokeFunction("init",param));
        String value3 = String.valueOf(invocable.invokeFunction("setData",obs1,obs2,obs3,obs4,obs5,obs6,obs7,obs8,obs9));
        Map<String,Object> map = new HashMap();
        return map;
    }

    public static void main(String args[]) throws FileNotFoundException, ScriptException, NoSuchMethodException {
        CoordinateTransfrom.getValues();
    }
}
