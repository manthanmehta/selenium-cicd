package com.manselenium.util;

import org.openqa.selenium.remote.Browser;

public class demo extends  Config{

    public static void main(String args[]){
        Config.initialize();
     String browser= Config.get("browser");
        System.out.println(browser);

    }
}
