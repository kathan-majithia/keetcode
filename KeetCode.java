import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;

class Analysis{ // Class to do the analysis
    static JFrame frame;
    static String result; // Res of compiler
    static String expected; // Excepted result
    Analysis(int q){
        String ex[] = {"2,0,6,9,9,7,","2,8,1,0,","2,3,4,5,6","4,0,13,10,","dc-ba,j-Ih-gfE-dCba,Qedo1ct-eeLg=ntse-T!,7_28]"};

        expected = ex[q];
        result = "";
        // System.out.println(""expected);
    }

    void compile(int q){
        String files[] = {"easy1.txt","easy2.txt","medium1.txt","medium2.txt","hard1.txt"};
        String mains[] = {"maineasy1.txt","maineasy2.txt","mainmedium1.txt","mainmedium2.txt","mainhard1.txt"};
   

        String content = "";
        frame = new JFrame("Run TestCase");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(720, 720);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        JButton save = new JButton();
        JTextArea ca = new JTextArea();
        ca.setBackground(Color.WHITE); 
        ca.setForeground(Color.BLACK);
        ca.setPreferredSize(new Dimension(960,850));
        ca.setFont(new Font("Arial",Font.PLAIN, 24));
        ca.setEditable(false);

        String sourcecode = "";

        // Here content of both files will be merged and will be paste into the sourcecode
        try{
            java.util.List<String> lines;
            lines = Files.readAllLines(Paths.get(mains[q]));
            for(String s: lines){
                sourcecode += s;
                sourcecode += "\n";
            }
        }
        catch(Exception e){
            System.out.println("SUS 1");
        }

        try{
            java.util.List<String> lines;
            lines = Files.readAllLines(Paths.get(files[q]));
            for(String s: lines){
                sourcecode += s;
                sourcecode += "\n";
            }
        }
        catch(Exception e){
            System.out.println("SUS 1");
        }


        // Temp files for execution of the c++ code
        String cppf = "kctemp.cpp";
        String exef = "kctemp.out";

        try{
            FileWriter fw = new FileWriter(cppf);
            fw.write(sourcecode);
            fw.close();

            // Run g++ filename -o f.out in cmd in the background

            Process comp = Runtime.getRuntime().exec("g++ " + cppf + " -o " + exef);
            comp.waitFor(); // Continue only if compiling is done

            Process exe = Runtime.getRuntime().exec(exef);
            BufferedReader red = new BufferedReader(new InputStreamReader(exe.getInputStream()));

            String line;

            StringBuilder output = new StringBuilder();

            while((line = red.readLine()) != null){
                output.append(line).append("\n");
            }

            new File(cppf).delete();
            new File(exef).delete();

            result = output.toString().trim();
            // Result has the output of cout statements of the cmd

            // System.out.println("Code : " + sourcecode);
            System.out.println("Output : " + output);

            String sami1[] = {"38","[3,0,1]","5","rem","ab-cd"};
            String samo1[] = {"2","2","2","rem","dc-ba"};
            String sami2[] = {"0","[9,6,4,2,3,5,7,0,1]","8","rem","a-bC-dEf-ghIj"};
            String samo2[] = {"0","8","3","rem","j-Ih-gfE-dCba"};

            String yo1="",yo2=""; // Will have cmd containing output
            
            int i=0;
            for(;result.charAt(i) != ',';i++)
                yo1 += result.charAt(i);
            i++;
            for(;result.charAt(i) != ',';i++)
                yo2 += result.charAt(i);

            content += "Input 1 : " + sami1[q] + "\n\n" + "Excepted Output 1 : " + samo1[q] +
            "\n\n" + "Your Output 1 : " + yo1 + "\n\n" + "Input 2 : " + sami2[q] + "\n\n" +
            "Excepted Output 2 : " + samo2[q] + "\n\n" + "Your Output 2 : " + yo2 + "\n\n";
            
            
            
        }catch(IOException | InterruptedException e){

            // If c++ code has some kind of error then it will display error message

            System.out.println("Code contains error.");
            frame.setSize(480, 150);
            content = "Code contains error";
            ca.setForeground(Color.RED);
            // System.out.println("Code : " + sourcecode);

            // System.out.println("Output : " + output);

        }

        ca.setText(content);
        
        frame.add(ca,BorderLayout.CENTER);
        
        save.setPreferredSize(new Dimension(200,50));
        save.setBackground(Color.WHITE);
        save.setFont(new Font("Arial",Font.BOLD,16));

        // Storage st = new Storage();

        save.addActionListener(e -> frame.dispose());
        // save.addActionListener(e -> System.out.println(ca.getText()));

        save.setText("Close");
        frame.add(save,BorderLayout.SOUTH);

        ImageIcon ic = new ImageIcon("logo.png");
        frame.setIconImage(ic.getImage());

        frame.setVisible(true);
    }

    void submit(int q){

        // This will call will code is being submitted

        frame = new JFrame("Submit");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 150);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        JButton save = new JButton();
        
        JLabel st = new JLabel();

        // ca.setPreferredSize(new Dimension(960,850));
        st.setFont(new Font("Arial",Font.BOLD, 24));
        // ca.setEditable(false);
        st.setBackground(Color.WHITE);
        System.out.println("Exp : " + expected + " , " + result);


        // If your code is correct

        if(result.equals(expected)){
            st.setText("Submission status : Accepted");
        java.util.List<String> sol;
        String solt = "";

        // It will check whether the problem was solved or not

        try{
        sol = Files.readAllLines(Paths.get("solved.txt"));
        for(String s: sol)
            solt = s;
        
        StringBuilder sb = new StringBuilder(solt);
        if (solt.charAt(q) == '0'){

            // If no , then increase the progress bar
            try{
            FileWriter fileWriter = new FileWriter("pro.txt");
            if (q <= 1)
                KeetCode.progress += 10;
            else if(q <= 3)
                KeetCode.progress += 20;
            else
                KeetCode.progress += 40;

            fileWriter.write(String.valueOf(KeetCode.progress));
            fileWriter.close();

            System.out.println("New progress : " + KeetCode.progress);

        }
            catch(Exception e){}

        }

        // Change status of the problem to solved

        sb.setCharAt(q,'1');

        solt = sb.toString();

        System.out.println("Solved : " + solt);
        }
        catch(Exception e){}

        try{
        FileWriter fileWriter = new FileWriter("solved.txt");
        fileWriter.write(solt);
        fileWriter.close();
        // System.out.println("New progress : " + KeetCode.progress);

        }
        catch(Exception e){}

            
            st.setForeground(Color.GREEN);
        }
        else{

            // If the code is wrong

            st.setText("Submission status : Rejected");

            st.setForeground(Color.RED);

            java.util.List<String> sol;
            String solt = "";

        try{
        sol = Files.readAllLines(Paths.get("solved.txt"));
        for(String s: sol)
            solt = s;
        
        StringBuilder sb = new StringBuilder(solt);
        if (solt.charAt(q) == '1'){

            // If code was solved , but now unsolved then decrease the progress bar.

            try{
            FileWriter fileWriter = new FileWriter("pro.txt");
            if (q <= 1)
                KeetCode.progress -= 10;
            else if(q <= 3)
                KeetCode.progress -= 20;
            else
                KeetCode.progress -= 40;

            fileWriter.write(String.valueOf(KeetCode.progress));
            fileWriter.close();

            System.out.println("New progress : " + KeetCode.progress);

        }
            catch(Exception e){}

        }
        sb.setCharAt(q,'0');

        solt = sb.toString();

        System.out.println("Solved : " + solt);
        }
        catch(Exception e){}

        try{
        FileWriter fileWriter = new FileWriter("solved.txt");
        fileWriter.write(solt);
        fileWriter.close();
        // System.out.println("New progress : " + KeetCode.progress);

        }
        catch(Exception e){}

            
            st.setForeground(Color.RED);
        }
            
        // }

        frame.add(st);
        save.setPreferredSize(new Dimension(200,50));
        save.setBackground(Color.WHITE);
        save.setFont(new Font("Arial",Font.BOLD,16));

        save.addActionListener(e -> frame.dispose());
        // save.addActionListener(e -> System.out.println(ca.getText()));

        save.setText("Close");
        frame.add(save,BorderLayout.SOUTH);

        ImageIcon ic = new ImageIcon("logo.png");
        frame.setIconImage(ic.getImage());

        frame.setVisible(true);
        
    }
}

class Storage{
    Storage(){

    }

    void store(String fn,JFrame fr,String text){

        // Store content of the textarea code to the dedicated file with formating

        try{
            FileWriter fw = new FileWriter(fn);
            fw.write(text);
            fw.close();

            // System.out.println("Code is loaded into the file : " + fn);

        }
        catch(IOException e){
            System.out.println(e);
        }
        fr.dispose();
    }


}


class CodePrompt{
    static JFrame frame;
    CodePrompt(){

    }

    void prompt(int q){
        
        frame = new JFrame("Code Prompt");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(960, 960);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        JButton save = new JButton();
        JTextArea ca = new JTextArea();
        ca.setBackground(Color.BLACK); 
        ca.setForeground(Color.YELLOW);
        ca.setPreferredSize(new Dimension(960,850));
        ca.setFont(new Font("Consolas",Font.PLAIN, 18));
        ca.setCaretColor(Color.WHITE); 


        String files[] = {"easy1.txt","easy2.txt","medium1.txt","medium2.txt","hard1.txt"};
        ArrayList<String> str = new ArrayList<>();
        String content = "";

        // Read content from the existing file of the dedicated question

        try( BufferedReader red = new BufferedReader(new FileReader(files[q]))){
            String line;
            while((line = red.readLine()) != null)
                str.add(line);
            }
            catch(IOException e){
            System.out.println("ERROR");
        }
        for(String s : str){
            content += s;
            content += "\n";
        }
        ca.setText(content);
        
        frame.add(ca,BorderLayout.CENTER);
        
        save.setPreferredSize(new Dimension(200,50));
        save.setBackground(Color.WHITE);
        save.setFont(new Font("Arial",Font.BOLD,16));

        Storage st = new Storage();

        save.addActionListener(e -> st.store(files[q],frame,ca.getText()));
        // save.addActionListener(e -> System.out.println(ca.getText()));

        save.setText("Save & Close");
        frame.add(save,BorderLayout.SOUTH);

        ImageIcon ic = new ImageIcon("logo.png");
        frame.setIconImage(ic.getImage());

        frame.setVisible(true);
    }
}

class Questions{
    Questions(){

    }

    public void ques(JFrame fr,int q){
        
        System.out.println(q);
        
        
        KeetCode kt = new KeetCode();
        CodePrompt cp = new CodePrompt();
        Analysis al = new Analysis(q);
        
        String files[] = {"ques1.txt","ques2.txt","ques3.txt","ques4.txt","ques5.txt"};

        ArrayList<String> str = new ArrayList<>();

        // String content[] = {""};
        String content= "";
        JTextArea textArea = new JTextArea();

        java.util.List<String> sol;
        String solt = "";

        try{

        // Check if the question is solved or unsolved    
        sol = Files.readAllLines(Paths.get("solved.txt"));
        for(String s: sol)
            solt = s;
        
        if(solt.charAt(q) == '1')
            content += "\t\tSolved\n\n";
        

        System.out.println("Solved : " + solt);
        }
        catch(Exception e){}

        try( BufferedReader red = new BufferedReader(new FileReader(files[q]))){
            String line;
            while((line = red.readLine()) != null)
                str.add(line);
            }
            catch(IOException e){
            System.out.println("ERROR");
        }
        
        // JTextArea jta = new JTextArea(plain);
        // textArea.setLineWrap(true);
        for(String s : str){
            content += s;
            content += "\n";
        }
        textArea.setText(content);
        // textArea.setText(content);
        textArea.setEditable(false);
        textArea.setFont(new Font("Itim",Font.PLAIN,30));
        KeetCode.rightPanel.add(textArea, BorderLayout.CENTER);
        
        
        KeetCode.frame.add(KeetCode.leftPanel, BorderLayout.WEST);
        KeetCode.frame.add(KeetCode.rightPanel, BorderLayout.CENTER);
        
        JPanel butp = new JPanel();
        butp.setLayout(new FlowLayout());

        JButton writebut = new JButton("Write/Edit Code");
        JButton runbut = new JButton("Run Code");
        JButton subbut = new JButton("Submit Code");
        writebut.setPreferredSize(new Dimension(200, 30)); 
        runbut.setPreferredSize(new Dimension(200, 30)); 
        subbut.setPreferredSize(new Dimension(200, 30)); 
        
        butp.add(writebut);
        butp.add(runbut);
        butp.add(subbut);
        writebut.setBackground(Color.WHITE);
        runbut.setBackground(Color.WHITE);
        subbut.setBackground(Color.WHITE);
        writebut.addActionListener(e -> cp.prompt(q));
        runbut.addActionListener(e -> al.compile(q));
        subbut.addActionListener(e -> al.submit(q));

        // KeetCode.rightPanel.add(writebut);

        KeetCode.rightPanel.add(butp, BorderLayout.SOUTH);

        KeetCode.frame.add(KeetCode.rightPanel, BorderLayout.CENTER);
        KeetCode.frame.setVisible(true);

        ImageIcon ic = new ImageIcon("logo.png");
        KeetCode.frame.setIconImage(ic.getImage());

        fr.dispose();
    }
}

public class KeetCode {


    static JFrame frame;
    static JPanel leftPanel,rightPanel;
    static int progress;
    KeetCode(){
        Questions q = new Questions();
        // Fetch existing progress from the file

        java.util.List<String> pro;
        try{
        pro = Files.readAllLines(Paths.get("pro.txt"));
        for(String s: pro)
            progress = Integer.parseInt(s);
        }
        catch(Exception e){}
        

        frame = new JFrame("KeetCode");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 960);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // String[] buttonLabels = {"Easy 1", "Easy 2", "Medium 1", "Medium 2", "Hard 1", "Submissions"};
        // Color[] buttonColors = {Color.GREEN, Color.GREEN, Color.YELLOW, Color.YELLOW, Color.RED, Color.CYAN};
        
        String[] buttonLabels = {"Easy 1", "Easy 2", "Medium 1", "Medium 2", "Hard 1"};
        Color[] buttonColors = {Color.GREEN, Color.GREEN, Color.YELLOW, Color.YELLOW, Color.RED};
        
        JButton[] button = new JButton[6];
        
        for (int i = 0; i < buttonLabels.length; i++) {
            button[i] = new JButton(buttonLabels[i]);
            button[i].setPreferredSize(new Dimension(250, 30)); 
            button[i].setBackground(buttonColors[i]);
            button[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            final int j = i;
            button[j].addActionListener(e -> q.ques(frame,j));
            leftPanel.add(Box.createRigidArea(new Dimension(0, 50))); 
            leftPanel.add(button[i]);
        }
        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        JProgressBar progressBar = new JProgressBar();
        progressBar.setValue(progress); 
        progressBar.setStringPainted(true);
        progressBar.setForeground(Color.GREEN);
        leftPanel.add(Box.createVerticalGlue()); 
        leftPanel.add(progressBar);

    }

    public static void main(String[] args) {

        KeetCode kc = new KeetCode();
        
        JTextArea textArea = new JTextArea(
            "                                 Welcome to Keetcode\n\n\tAlso known as Kathan's Leetcode\n\n" +
            "           It is a simulation of Leetcode using Java Swing\n\n" +
            "     Limited questions are there and only available language is C++.\n\n" +
            "         You can click on the question and solve the question.\n\n" +
            "             At the bottom left, there is also a progress bar.\n\n\t\tENJOY!!!");
        textArea.setEditable(false);
        textArea.setFont(new Font("Itim",Font.PLAIN,34));
        rightPanel.add(textArea, BorderLayout.CENTER);
        
        ImageIcon ic = new ImageIcon("logo.png");
        frame.setIconImage(ic.getImage());
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(rightPanel, BorderLayout.CENTER);
        frame.setVisible(true);

    }
}