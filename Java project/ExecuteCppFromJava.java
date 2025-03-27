
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteCppFromJava {
    public static void main(String[] args) {
        String cppCode = "#include <iostream>\n" +
                         "using namespace std;" + 
                         "int main() {\n" +
                         "    cout <<15; \n" +
                         "    return 0;\n" +
                         "}\n";

        String cppFileName = "Hello.cpp";
        String exeFileName = "Hello.out";

        // Step 1: Write the C++ code to a file
        try (FileWriter writer = new FileWriter(cppFileName)) {
            writer.write(cppCode);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Step 2: Compile the C++ code
        try {
            ProcessBuilder compileProcess = new ProcessBuilder("g++", cppFileName, "-o", exeFileName);
            compileProcess.redirectErrorStream(true);
            Process compile = compileProcess.start();
            compile.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return;
        }

        // Step 3: Execute the compiled C++ program and capture output
        StringBuilder output = new StringBuilder();
        try {
            ProcessBuilder runProcess = new ProcessBuilder(exeFileName);
            runProcess.redirectErrorStream(true);
            Process run = runProcess.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(run.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }

            run.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return;
        }

        // Print the output captured from the C++ program
        System.out.println("Output from C++ program:");
        System.out.println(output.toString());

        // Clean up: Delete the generated files
        // new File(cppFileName).delete();
        // new File(exeFileName).delete();
    }
}