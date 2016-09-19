/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package send.sms.az.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;
import org.primefaces.model.UploadedFile;
import send.sms.az.model.Entry;

public class ReadTemps {

    private static final Logger LOG = Logger.getLogger(ReadTemps.class.getName());

    public static List<Map<String, Entry>> textToList(UploadedFile file, String type) {
        List<Map<String, Entry>> temps = new ArrayList<>();

        try {
            InputStream is = file.getInputstream();
//            InputStream is = new FileInputStream("D:/test.txt");
            LOG.info("data " + is.read());
            String token1 = "";
            Scanner inFile1 = new Scanner(is);
            if (!type.equals("3")) {
                inFile1 = inFile1.useDelimiter(DelimiterTypes.getType(type));
            }

            // while loop
            int counter = 0;
            while (inFile1.hasNext()) {
                ++counter;
                Map<String, Entry> map = new HashMap<>();
                token1 = inFile1.next();
                System.out.println(token1);
                map.put("0", new Entry(counter + "", token1 + ""));
                temps.add(map);
            }
            inFile1.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            LOG.info("upload error " + ex.getMessage());
        }
//        temps.add(map);
        return temps;
    }

    public static List<Map<String, Entry>> textToList(String source, String type) {
        List<Map<String, Entry>> temps = new ArrayList<>();
        String token1 = "";
        Scanner inFile1 = new Scanner(source.toString());
        System.out.println(DelimiterTypes.getType(type));
        if (!type.equals("3")) {
//            String tse = "[\\r\\n;]+";
            inFile1 = inFile1.useDelimiter(type);
        }
        // while loop
        int counter = 0;
        while (inFile1.hasNext()) {
            ++counter;
            Map<String, Entry> map = new HashMap<>();
            token1 = inFile1.next();
            map.put("0", new Entry(counter + "", token1 + ""));
            temps.add(map);
        }
        inFile1.close();
        return temps;
    }

    public static void main(String[] args) throws IOException {
//        System.out.println(new FileInputStream(new File("D:/te.txt")).getFD() + " ");
//        for (String arg : textToList(new FileInputStream(new File("D:/te.txt")), DelimiterTypes.pipe)) {
//            System.out.println(arg);
//        }
//        String a = "aaaa,\n dfdfgd, dfgfdgs,\n";
//        System.out.println(textToList(a, ",\n").size());

        UploadedFile file = null;
        for (Map<String, Entry> arg : textToList("aasdad,\n,cdgdgfdg\n", "3")) {
            for (Map.Entry<String, Entry> entrySet : arg.entrySet()) {
                Object key = entrySet.getKey();
                Object value = entrySet.getValue();
                System.out.println(key + " " + value);
            }
        }
//        for (String arg : textToList("a,b,c", "1")) {
//            
//        }
    }
}
