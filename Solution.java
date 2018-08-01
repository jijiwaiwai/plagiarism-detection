import java.util.*;
import java.io.*;
import java.nio.file.*;

public class Solution{
    public static void main(String args[]) {
        Utility util = new Utility();
        Scanner scanner = new Scanner(System.in);
        
        List<List<String>> synonyms = new ArrayList<>();
        while(true) {
            System.out.print("Enter synonym file name: ");
            String syns = scanner.nextLine();
            String line = null;
            try {
                FileReader fileReader = new FileReader(syns);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                while((line = bufferedReader.readLine()) != null) {
                    //System.out.println(line);
                    synonyms.add(Arrays.asList(line.split(" ")));
                }   
                bufferedReader.close();
                break;
            }
            catch(FileNotFoundException ex) {
                ex.printStackTrace();
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
        }
        
        String file1 = null;
        while(true) {
            System.out.print("Enter file1 name: ");
            file1 = scanner.nextLine();
            try {
                file1 = new String(Files.readAllBytes(Paths.get(file1))); 
                //System.out.println("file1 : " + file1);
                break;
            }
            catch(FileNotFoundException ex) {
                ex.printStackTrace();
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
        }
        
        String file2 = null;
        while(true) {
            System.out.print("Enter file12 name: ");
            file2 = scanner.nextLine();
            try {
                file2 = new String(Files.readAllBytes(Paths.get(file2))); 
                //System.out.println("file2 : " + file2);
                break;
            }
            catch(FileNotFoundException ex) {
                ex.printStackTrace();
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
        }
        
        int tuples = 0;
        
        System.out.print("Enter tuple size(the default value is 3): ");
        if(scanner.hasNextInt()) {
            tuples = scanner.nextInt();
        }else{
            tuples = 3;
        }
        
        
        util.checkPlagiarism(synonyms, file1, file2, tuples);
    }
}