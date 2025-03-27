
// import javax.swing.ImageIcon;
// import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.*;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

class GUIjava implements ActionListener{

    JButton jb;
    JTextField ta;
    JProgressBar jpb = new JProgressBar();

    GUIjava(){


        jpb.setValue(0);
        jpb.setBounds(0,0,420,50);
        jpb.setStringPainted(true);

        jb = new JButton();
        jb.setBounds(200, 100, 250, 100);
        // jb.addActionListener(e -> System.out.println("Pressed"));
        jb.addActionListener(this);
        jb.setText("Problem List");
        JPanel menu = new JPanel();
        menu.setBackground(Color.BLUE);
        menu.setBounds(0,0,200,960);
        JLabel lb = new JLabel();
        lb.setText("Welcome to Meet Kode Solve some questions");
        menu.add(lb);
        menu.add(jb);
        menu.add(jpb);

        ta = new JTextField();
        ta.setPreferredSize(new Dimension(250,100));
        ta.setFont(new Font("Consolas",Font.PLAIN,35));
        ta.setBackground(Color.black);
        ta.setForeground(Color.YELLOW);
        // ta.setEditable(false);
        ta.setText(("Code"));
        menu.add(ta);
        // menu.add(jb);

        JFrame fr = new JFrame();
        fr.setTitle("MeetKode");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setResizable(false);
        fr.setSize(1280,960);
        fr.setLayout(null);
        fr.setVisible(true);
        
        fr.add(menu);
        ImageIcon ic = new ImageIcon("log4.png");
        fr.setIconImage(ic.getImage());
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == jb){
            System.out.println(ta.getText());
        }
    }

    public static void main(String[] args) {
        
        GUIjava gj = new GUIjava();
        
        // fr.add(lb);
        

        // Label
    }

    
}