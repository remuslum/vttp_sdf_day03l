
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import people.Person;
import service.CSVManagement;

public class App {
    public static void main(String[] args) throws IOException {

        // arg[0] arg[1] arg[2]
        // arg[0] "c:\data"
        // arg[1] myfile.txt
        // macOS: //data/myfile.txt

        String dirPath = args[0];
        String fileName = args[1];
        String dirPathFileName = dirPath + File.separator + fileName;

        // check if a directory exists
        // If it does not exist, create the dictionary
        File directory = new File(dirPath);

        if (directory.exists()) {
            System.out.println("Directory" + directory.toString() + "had already been created");
        } else {
            directory.mkdir();
        }

        // Check if the file exists
        // If file does not exist, create the file
        File file = new File(dirPathFileName);

        if (file.exists()) {
            System.out.println("File " + file.toString() + " already created");
        } else {
            file.createNewFile();
        }

        // Example 1: Use FileWriter
        // 1. Use console to read in a string of text (sentence)
        // 2. Use FileWriter to write the content to the file

        // Console console = System.console(); 
        // String keyBoardInput = console.readLine("Enter/Input a sentence: ");

        // FileWriter fw = new FileWriter(file, true);
        // fw.write(keyBoardInput);
        // fw.flush();
        // fw.close();

        // Use FileReader to read the file
        // FileReader fr = new FileReader(file);
        // int dataRead = fr.read();
        // while (dataRead != -1) {
        //     System.out.print((char) dataRead);
        //     dataRead = fr.read();
        // }
        // fr.close();

        // Example 2: BufferedWriter to write file
        // FileWriter fw = new FileWriter(file, true);
        // BufferedWriter bw = new BufferedWriter(fw);
        // bw.append(keyBoardInput);
        // bw.flush();
        // bw.close();
        // fw.close();

        // Use BufferedReader to read the file content
        // FileReader fr = new FileReader(file);
        // BufferedReader br = new BufferedReader(fr);
        // String line = br.readLine();
        // while (line != null) {
        //     System.out.println(line);
        //     line = br.readLine();
        // }
        
        // br.close();
        // fr.close();

        // Using try block
        // try (FileReader fr = new FileReader(file)) {
        //     try (BufferedReader br = new BufferedReader(fr)) {
        //         String line = "";
        //         while (line != null) {
        //             System.out.println(line);
        //             line = br.readLine();
        //         }
        //     }
        // }

        // Example 3
        // Use FileOutputStream to write to file
        // FileOutputStream fos = new FileOutputStream(file, true);
        // byte[] byteContent = keyBoardInput.getBytes();
        // fos.write(byteContent);
        // fos.flush();
        // fos.close();

        //Use FileInputStream to read the content
        // FileInputStream fis = new FileInputStream(file);
        // int contentRead = fis.read();
        // while (contentRead != -1) {
        //     System.out.print((char) contentRead);
        //     contentRead = fis.read();
        // }
        // fis.close();

        // Example 4 (Decorator Pattern)
        // Use FileOutputStream and DataOutputstream to write to file
        // FileOutputStream fos = new FileOutputStream(file, true);
        // DataOutputStream dos = new DataOutputStream(fos);
        // byte[] content = keyBoardInput.getBytes();
        // dos.write(content);
        // dos.flush();
        // dos.close();


        // Use FileInputStream and DataInputStream to read the content
        // FileInputStream fis = new FileInputStream(file);
        // DataInputStream dis = new DataInputStream(fis);
        // int disContent = 0;
        // while ((disContent = dis.read()) != -1) {
        //     System.out.print((char) disContent);
        // }
        // dis.close();

        CSVManagement manager = new CSVManagement();
        List<Person> people = manager.readCSV(dirPathFileName);

        Console consoleSelection = System.console();
        int selection = 0;
        while (selection != 3) {
            System.out.println("1. Enter new Person details");
            System.out.println("2. Save to new csv file");
            System.out.println("3. Quit Program");
            selection = Integer.parseInt(consoleSelection.readLine(">>>"));

            switch (selection) {
                case 1:
                    Console console1 = System.console();
                    String personName = console1.readLine("Enter Person name: ");
                    String personRegion = console1.readLine("Enter Area: ");
                    String personYear = console1.readLine("Enter Year of Birth");
    
                    Person p = new Person(personName, personRegion, Integer.parseInt(personYear));
                    people.add(p);
                    break;
                case 2:
                    Console console2 = System.console();
                    String newFileName = console2.readLine("Enter a new name for your csv file: ");
                    manager.writeCSV(dirPath + File.separator + newFileName, people);
                    break;
                case 3:
                    break;
            }
        }

    }
}