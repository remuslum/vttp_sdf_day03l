package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import people.Person;

public class CSVManagement {
    
    //filename: dirPath + file separator + filename
    public List<Person> readCSV(String filename) throws IOException {
        // Use BufferedReader
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        List<Person> people = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            System.out.println(line);

            //lineData[0], lineData[1], lineData[2]
            String[] lineData = line.split(",");

            // Put the extracted line data into a Person object
            Person p = new Person(lineData[0], lineData[1].trim(), Integer.parseInt(lineData[2].trim()));
            people.add(p);

        }
        br.close();
        fr.close();

        return people;
    }

    public void writeCSV(String filename, List<Person> person) throws IOException{
        // Use BufferedWriter
        FileWriter fw = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(fw);

        for (Person p: person) {
            bw.append(p.getName());
            bw.append(",");
            bw.append(p.getArea());
            bw.append(",");
            bw.append(String.valueOf(p.getYearOfBirth()));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        fw.close();
    }
}
