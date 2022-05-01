package net.microsoft.java.web.util;

import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @description:
 * @Date on 2022/4/30
 * @author: suche
 **/

public class AwtTest {


    public static void main(String[] args) {

    }

    @Test
    public void AwtButtonClickTest() {
        Frame f = new Frame();
        Button b = new Button("LL");
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("A");
            }
        });


        f.add(b);
        final boolean[] flag = {true};
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Window window = e.getWindow();
                flag[0] = false;
                window.dispose();


            }
        });
        while (flag[0]) {
            f.setVisible(true);
        }
    }
}
