package cz.cvut.fel.pjv.loggers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
/**
 * Logger is a class for error handling purposes.
 *
 * @author Tomas Kloucek
 * @author Vladyslav Babyc
 *
 */
public class Logger {

    /**
     * This method logs errors.
     *
     * @param called which class was called
     * @param functionName which function was called
     * @param problem what was the problem
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */

    public static void log(Class called, String functionName, String problem) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("log.txt", true));
            bw.write(LocalDateTime. now() + " - " + called.getCanonicalName() + " - " + functionName + " - " + problem);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
