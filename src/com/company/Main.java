package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class Main {

    public static void main(String[] args) throws IOException {
        if(args.length == 0){System.out.println("Введите директорию для вывода файлов");
            return;}
        else if(args.length == 1){
            System.out.println("Введите путь до .cvs файла/файлов");
            return;
        }

        Map<String, ArrayList<String>> dictionary = new HashMap<String, ArrayList<String>>();

        String line;
        String[] header = {""};

        for (int i = 1; i<args.length; i++){
            try (BufferedReader br = new BufferedReader(new FileReader(args[i]))) {
                header = br.readLine().split(";");
                for (var k : header) {
                    dictionary.put(k, new ArrayList<String>());
                }
                while ((line = br.readLine()) != null) {
                    String[] elementbuf = line.split(";");
                    for (int k = 0; k < header.length; k++) {
                        dictionary.get(header[k]).add(elementbuf[k]);
                    }
                }
            }catch (Exception ex){
                System.out.println("Неправильный путь до файлов");
                System.in.read();
                return;
            }

            for (var j: header) {
                try(FileWriter fw = new FileWriter(args[0] + j + ".txt", false))
                {
                    for (var k: dictionary.get(j)) {
                        fw.write(k+";");
                    }
                    fw.flush();
                }
            }
        }
    }
}