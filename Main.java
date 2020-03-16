package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;



class Game extends JFrame implements ActionListener {
    JButton[] btn = new JButton[9];// empty 9 button // class havent create
    Icon icon = new ImageIcon("D:\\Computer\\MMU Notes\\Degree\\year2\\sem 1\\OOAD\\linux.png");
    Random r = new Random();
    Thread thd = new Thread();
    Timer t;
    int gametime = 20000;
    int[] mls;
    int whacked = 0;
    int missed = 0;
    int second  = 30;
    JPanel box;

    public void clearData() {
        Arrays.fill(mls, 0);
        for (int i = 0; i < 9; i++) {
            btn[i].setIcon(null);
        }
    }

    public Game() {
        //panel for menu
        super("Whack a Mole");

        JPanel jp = new JPanel(new GridLayout(1, 2));
        jp.setSize(500, 100);
        jp.setBackground(Color.blue);
        jp.setForeground(Color.white);
        // buttonI
        JButton b = new JButton("Start");
        b.setBounds(new Rectangle(50, 30));
        JLabel l = new JLabel("Click to start the Game");
        l.setForeground(Color.YELLOW);
        jp.add(b);
        jp.add(l);

        mls = new int[9];

        //panel for box
        box = new JPanel(new GridLayout(3, 3));
        box.setSize(500, 400);
        box.setBackground(Color.black);

        // need to ask lecture

        for (int x = 0; x < 9; x++) {
            btn[x] = new JButton();
            btn[x].setIcon(null);
            box.add(btn[x]);
            btn[x].addActionListener(this); // refering to main object(game)
        }

        t = new Timer(700, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearData();
                l.setText("Time : "+ second + "    " +"Whacked: "+whacked + "     " +"Missed: "+ missed);
                second = gametime/1000;
                if (gametime >= 0) {
                    int v = r.nextInt(9);
                    mls[v] = 1;
                    btn[v].setIcon(icon);
                    // buttons named btn[0],btn[1] etc.
                    System.out.print(v);
                    // TimeUnit.SECONDS.sleep(1);
                    gametime -= 700;

                } else {
                    b.setEnabled(true);
                    gametime = 20000;
                    second = 30;
                    t.stop();
                }
            }
        });

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t.start();
                b.setEnabled(false);
                whacked = 0;
            }
        });

        setLayout(new BorderLayout());
        add(jp, BorderLayout.NORTH);
        add(box, BorderLayout.CENTER);
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    // this is for button
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("***");
        JButton temp = (JButton)e.getSource();// to return button event object

        if (mls[Arrays.asList(btn).indexOf(temp)] == 1){
            clearData();
            whacked++;
        }
        else {
            missed++;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        new Game();
    }
}
