package com.example.device_managment_client;

import com.github.kevinsawicki.http.HttpRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDateTime;

@SpringBootApplication
public class DeviceManagmentClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceManagmentClientApplication.class, args);
        run();
    }

    public static void run(){
        String ip;
        try {
           ip = "" + InetAddress.getLocalHost();
            HttpRequest.get("http://DeviceStatusResponse:8080/status/setOpenDate/"+ ip + "/" + LocalDateTime.now()).code();
        }catch (Exception e){
            System.out.println(e);
        }

    }

    @GetMapping("/shutdown")
    public void shutdown(){
        Runtime runtime = Runtime.getRuntime();
        try {
            Process proc = runtime.exec("shutdown -s -t 0");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
