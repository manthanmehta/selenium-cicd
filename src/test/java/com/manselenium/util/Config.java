package com.manselenium.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Logger log= LoggerFactory.getLogger(Config.class);
    private  static final String DEFAULT_PROPERTIES= "config/default.properties";
    private static Properties properties;

//    public void Config(){
//        properties=loadProperties();
//        System.out.println(properties);
//        for(String key: properties.stringPropertyNames()){
//            if(System.getProperties().contains(key)){
//                properties.setProperty(key, System.getProperty(key));
//            }
//        }
//        log.info("Test Properties");
//        for(String key: properties.stringPropertyNames()){
//            log.info("{}={}", key, properties.getProperty(key));
//        }
//    }


    public static void initialize(){
        properties=loadProperties();
        System.out.println(properties);
        for(String key: properties.stringPropertyNames()){
            if(System.getProperties().contains(key)){
                properties.setProperty(key, System.getProperty(key));
            }
        }
        log.info("Test Properties");
        for(String key: properties.stringPropertyNames()){
            log.info("{}={}", key, properties.getProperty(key));
        }
    }

    public static String get(String key){
        return properties.getProperty(key);
    }


    private static Properties loadProperties(){
        Properties properties= new Properties();
        try(InputStream stream =ResourceLoader.getResource(DEFAULT_PROPERTIES)){
            properties.load(stream);
        } catch (IOException e) {
          log.error("unable to read the property file {}", DEFAULT_PROPERTIES, e);
        }
        return properties;
    }



}
