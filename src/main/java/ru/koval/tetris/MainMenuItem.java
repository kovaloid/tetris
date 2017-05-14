package ru.koval.tetris;

import java.awt.Color;
import java.io.Serializable;

public class MainMenuItem implements Serializable {
  private static final long serialVersionUID = 1L;
  private String title;
  private int x, y;
  private Color c = Color.white;

  public MainMenuItem(String t, int x, int y) {
    setTitle(t);
    this.setX(x);
    this.setY(y);
  }

  //The text turns yellow if it is selected
  public void setSelected(boolean selected) {
    c = (selected) ? Color.yellow : Color.white;
  }

  //The setters and getters
  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getX() {
    return x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getY() {
    return y;
  }

  public void setC(Color c) {
    this.c = c;
  }

  public Color getC() {
    return c;
  }
}
