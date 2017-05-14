package ru.koval.tetris;

import java.awt.Toolkit;
import javax.swing.*;

public class Game {
  public static void main(String[] args) {
    //Makes a frame and adds the tetris panel
    JFrame frame = new JFrame("Tetris");
    Tetris tetris = new Tetris(frame);
    frame.add(tetris);
    //Set the properties of the frame
    frame.addWindowListener(new TetrisWindowAdapter(tetris));
    frame.setJMenuBar(new TetrisMenuBar(tetris));
    frame.addKeyListener(new TetrisKeyAdapter(tetris));
    frame.setSize(485, 728);
    frame.setResizable(false);
    //Centers the frame on the screen
    frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - tetris.getWidth() / 2, 2);
  }
}
