import java.io.*;
import java.util.*;
import java.nio.file.*;

public class Doj {
    public static void main(String[] args) {
        // C++ code to be executed
        String content = "";
        try{
        List<String> lines;
        // System.out.println("do : ");
        lines = Files.readAllLines(Paths.get("mainhard1.txt"));
        for(String s : lines){
            content += s;
            content += "\n";
        }
    }
    catch(Exception e){
        System.out.println("SUS 1" + content);
    }
    try{
        List<String> lines;
        lines = Files.readAllLines(Paths.get("hard1.txt"));
        for(String s : lines){
            content += s;
            content += "\n";
        }
    }
    catch(Exception e){
        System.out.println("SUS 2" + content);
    }
        
        // File paths

        String cppFilePath = "temp.cpp";
        String exeFilePath = "temp.out";


        // Ans : 2,8,1,0

        try {
            // Write C++ code to a file
            FileWriter fileWriter = new FileWriter(cppFilePath);
            fileWriter.write(content);
            fileWriter.close();

            // Compile the C++ code
            Process compileProcess = Runtime.getRuntime().exec("g++ " + cppFilePath + " -o " + exeFilePath);
            compileProcess.waitFor(); // Wait for compilation to finish

            // Execute the compiled code
            Process executeProcess = Runtime.getRuntime().exec(exeFilePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(executeProcess.getInputStream()));

            // Capture the output
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Clean up the temporary files
            new File(cppFilePath).delete();
            new File(exeFilePath).delete(); // Remove executable file if on Unix-based systems

            // Print the output or store it to a variable
            String result = output.toString();
            System.out.println("Output of C++ code: " + result);
            System.out.println(content);

        } catch (IOException | InterruptedException e) {
            System.out.println("Code contains error.");
            System.out.println(content);

        }
    }
}