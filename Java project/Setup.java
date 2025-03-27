import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javafx.scene.control.Label;


public class Setup extends JFrame{
    Setup(){
        // JFrame fr = new JFrame();
        this.setTitle("MeetKode");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1280,960);
        this.setVisible(true);

        ImageIcon ic = new ImageIcon("log4.png");
        this.setIconImage(ic.getImage());

        JLabel lb = new JLabel();
        lb.setText("Welcome to Meet Kode Solve some questions");

        this.add(lb);
    }
}
