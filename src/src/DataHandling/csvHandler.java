package DataHandling;

import java.io.*;
import java.util.ArrayList;

public class csvHandler {


    public ArrayList<String[]> readCSV(String csvFile){

        BufferedReader br = null;
        String line;
        ArrayList<String[]> values = new ArrayList<>();

        try{
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] output = line.split(",");
                values.add(output);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            if(br != null){
                try{
                    br.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return values;
    }

    public void writeCSV(String csvFile, String line, int turn){

        BufferedWriter br = null;

        try{
            br = new BufferedWriter(new FileWriter(csvFile,true));
            br.write(line + "," + turn);
            br.newLine();
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            if(br != null){
                try{
                    br.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void wipeCSV(String csvFile){

        BufferedWriter br = null;

        try{
            br = new BufferedWriter(new FileWriter(csvFile,false));
            br.write("");
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            if(br != null){
                try{
                    br.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
