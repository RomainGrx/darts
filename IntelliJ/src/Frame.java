import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Frame extends JFrame{

    public final String NAMEWINDOW = "Cricket party";
    public int WIDTH=500;
    public int HEIGHT=500;

    public Pannel pan;

    public Frame(Player[] players) {

        pan = new Pannel(players);

        this.setTitle(NAMEWINDOW);
        this.setSize(new Dimension(WIDTH+getInsets().right+getInsets().left,HEIGHT+getInsets().top+getInsets().bottom));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());
        this.getContentPane().add(pan, BorderLayout.CENTER);
        this.setVisible(true);

        this.add(pan);

    }

    public void refresh(Player[] players){
        this.pan.players = players;
        this.pan.repaint();
    }



}