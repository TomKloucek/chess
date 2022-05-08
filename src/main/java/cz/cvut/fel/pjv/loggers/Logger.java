package cz.cvut.fel.pjv.loggers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {

    public static void log(Class called, String functionName, String problem) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("log.txt", true));
            bw.write(LocalDateTime. now() + " - " + called.getCanonicalName() + " - " + functionName + " - " + problem);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
