package com.example.device_managment_client;

import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping({"/"})
public class DeviceController {



    @GetMapping("/getTemp")
    public String getTemperature() {
        String temperatures = "";

        Components components = JSensors.get.components();
        Cpu cpu = components.cpus.get(0);

        List<Temperature> temps = cpu.sensors.temperatures;
        for (int i = 0; i < temps.size(); i++) {
            temperatures = temperatures + temps.get(i).name + ":" + temps.get(i).value + " ";
        }
        return temperatures;
    }

    @GetMapping("/getDuration/{fromDateTime}")
    public String getOpenDuration(@PathVariable LocalDateTime fromDateTime) {
        LocalDateTime toDateTime = LocalDateTime.now();

        LocalDateTime tempDateTime = LocalDateTime.from(fromDateTime);
        String result = "";

        long years = tempDateTime.until(toDateTime, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears(years);
        result += years + " years ";

        long months = tempDateTime.until(toDateTime, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths(months);

        long days = tempDateTime.until(toDateTime, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays(days);


        long hours = tempDateTime.until(toDateTime, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours(hours);

        long minutes = tempDateTime.until(toDateTime, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes(minutes);

        if ( years != 0) { result += years + " years "; }
        if ( months != 0) { result +=  months + " months "; }
        if ( days != 0) { result += days + " days "; }
        if ( hours != 0) { result +=  hours + " hours "; }
        if ( minutes != 0) { result += minutes + " minutes "; }

        return result;
    }


    @GetMapping("/memoryInfo")
    public int getMemoryInfo() {
        return (int) ((100 * (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())) / Runtime.getRuntime().totalMemory());
    }

    static String cmdTop = "top -n 2 -b -d 0.2";
    @GetMapping("/cpuInfo")
    public int getCpuInfo() {
        double cpu = 176;
        try
        {
            // start up the command in child process
            String cmd = cmdTop;
            Process child = Runtime.getRuntime().exec(cmd);

            // hook up child process output to parent
            InputStream lsOut = child.getInputStream();
            InputStreamReader r = new InputStreamReader(lsOut);
            BufferedReader in = new BufferedReader(r);

            // read the child process' output
            String line;
            int emptyLines = 0;
            while(emptyLines<3)
            {
                line = in.readLine();
                if (line.length()<1) emptyLines++;
            }
            in.readLine();
            in.readLine();
            line = in.readLine();
            System.out.println("Parsing line "+ line);
            String delims = "%";
            String[] parts = line.split(delims);
            System.out.println("Parsing fragment " + parts[0]);
            delims =" ";

            parts = parts[0].split(delims);
            cpu = Double.parseDouble(parts[parts.length-1]);
        }
        catch (Exception e)
        { // exception thrown
            System.out.println("Command failed!");
        }
        return (int) cpu;
    }

    @PostMapping("/setFile")
    public void file(@RequestBody File file){
    }

}
