package com.aliano.util;

import java.util.HashMap;
import java.util.Map;

public class EChartsColorUtil {

    public static Map<String,String> createItemStyle(Integer sale){
        String color = "";
        if(sale<=3){
            color = "#96dee8";
        }
        if(sale>3 && sale<=6){
            color = "#c4ebad";
        }
        if(sale>6 && sale<=9){
            color = "#6be6c1";
        }
        if(sale>9 && sale<=12){
            color = "#3fb1e3";
        }
        if(sale>12){
            color = "#a0a7e6";
        }
        Map<String,String> map = new HashMap<>();
        map.put("color", color);
        return map;
    }

}
