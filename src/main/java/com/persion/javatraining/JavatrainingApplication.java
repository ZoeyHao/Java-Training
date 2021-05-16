package com.persion.javatraining;

import com.persion.javatraining.week1.homework02.MyCalssLoader;
import com.persion.javatraining.week2.homework06.MyHttpClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavatrainingApplication {

    public static void main(String[] args) {
        //SpringApplication.run(JavatrainingApplication.class, args);
        //week1();
        week2();
    }

    public static void week1() {
        new MyCalssLoader().main();
    }

    public static void week2() {
        new MyHttpClient().main();
    }

}
