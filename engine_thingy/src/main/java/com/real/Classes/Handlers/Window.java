package com.real.Classes.Handlers;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Window {
    // Configs

    protected JFrame JavaFrame;
    protected JLabel ImageLabel;

    public Window(int WINDOW_WIDTH, int WINDOW_HEIGHT, String title) {

        // Window

        this.JavaFrame = new JFrame();

        this.JavaFrame.setTitle(title);
        this.JavaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.JavaFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.JavaFrame.setLocationRelativeTo(null);
        this.JavaFrame.getContentPane().setLayout(new FlowLayout());
        this.JavaFrame.setResizable(false);
        this.JavaFrame.setPreferredSize(new java.awt.Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.JavaFrame.addKeyListener(new ProgramLoop());
        this.JavaFrame.setLayout(null);

        // Image Label

        this.ImageLabel = new JLabel();
        this.ImageLabel.setBounds(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
        this.JavaFrame.getContentPane().add(ImageLabel);
        this.JavaFrame.pack();

        this.JavaFrame.setVisible(true);
    };

    // Getter
    
    public JFrame GetJFrame() {
        return this.JavaFrame;
    }

    public JLabel GetImageLabel() {
        return this.ImageLabel;
    }
}
