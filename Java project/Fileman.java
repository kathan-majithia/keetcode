import java.util.*;
import java.io.*;

public class Fileman {
    public static void main(String[] args) {
        ArrayList <String> str = new ArrayList<>();
        try( BufferedReader red = new BufferedReader(new FileReader("ques1.txt"))){
            String line;
            while ((line = red.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e){
            System.out.println("ERROR");
        }
    }
}
