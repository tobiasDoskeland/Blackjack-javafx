package blackjack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ToFile {
    
    private final String filepath;

    public ToFile(String filepath){
        if(filepath == null || filepath.isBlank()) throw new IllegalArgumentException("filepath cant be null or empty");

        this.filepath = filepath;


    }


    public void writeToFile(List<ArrayList<String>> gameresults){
        try (FileWriter fileWriter = new FileWriter(this.filepath)) {
            for (ArrayList<String> result : gameresults) {
                fileWriter.write(String.join(";", result));
                fileWriter.write("\n");
            }
        } catch (IOException e) {
            return;
        }

    }


    public List<ArrayList<String>> loadFile(){
        List<ArrayList<String>> results = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while((line = br.readLine()) != null){
                if(line.isBlank()){
                    continue;
                }
                String[] words = line.split(";");
                results.add(new ArrayList<>(Arrays.asList(words)));
            }

        } catch (IOException e) {
            //file dosent exist, return empty list
        }
        return results;
    }

    public static void main(String[] args) {
        ToFile f = new ToFile("test.txt");
        ArrayList<String> a1 = new ArrayList<>(List.of("Dealer: 17", "Spiller: 19", "Bet: 50", "Result: 100"));
        ArrayList<String> a2 = new ArrayList<>(List.of("Dealer: 21", "Spiller: 15", "Bet: 50", "Result: 0"));

        List<ArrayList<String>> l = List.of(a1,a2);
        f.writeToFile(l);

        System.out.println(f.loadFile());
    }

}
