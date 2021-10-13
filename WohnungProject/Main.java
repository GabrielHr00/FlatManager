import javax.crypto.CipherInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        String[] wohn = sc.nextLine().split("\\s+");
        EigentumsWohnung ew = new EigentumsWohnung(Integer.parseInt(wohn[0]), Double.parseDouble(wohn[1]), Integer.parseInt(wohn[2]), Integer.parseInt(wohn[3]), wohn[4], new Address(wohn[5], wohn[6], Integer.parseInt(wohn[7]), Integer.parseInt(wohn[8])), Double.parseDouble(wohn[9]), Double.parseDouble(wohn[10]));

        System.out.println(ew.toString());
//        long startTime = new Date().getTime();
//
//        TimeUnit.SECONDS.sleep(5);
//
//        long endTime = new Date().getTime();
//
//        System.out.println("Start: " + startTime);
//        System.out.println("End: " + endTime);
//        System.out.println("Difference: " + (endTime - startTime));
//
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(dtf.format(now));
//
//        TimeUnit.SECONDS.sleep(5);
//
//        now = LocalDateTime.now();
//        System.out.println(dtf.format(now));


        //Fehlermeldungen
    /*
    "Error: Parameter ungueltig."
    "Error: Baujahr ungueltig."
    "Error: Wohnung bereits vorhanden. (id=<id>)"
    "Error: Wohnung nicht vorhanden. (id=<id>)"
     */
    }
}