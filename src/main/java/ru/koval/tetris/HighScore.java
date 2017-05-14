package ru.koval.tetris;

import java.io.*;
import java.util.Objects;

public class HighScore implements Serializable, Comparable<HighScore> {
  private static final long serialVersionUID = 1L;
  private int score, level;
  private String name;

  public HighScore(int s, int l, String n) {
    score = s;
    setLevel(l);
    setName(n);
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getScore() {
    return score;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getLevel() {
    return level;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  //Decides whether this HighScore is greater than, less than, or equal to the argument
  public int compareTo(HighScore h) {
    return Integer.compare(this.score, h.score);
  }

  //This is called when there is an empty file in order prevent exceptions
  private static void initializeFile() {
    HighScore[] h = {new HighScore(0, 0, " "), new HighScore(0, 0, " "), new HighScore(0, 0, " "),
      new HighScore(0, 0, " "), new HighScore(0, 0, " "), new HighScore(0, 0, " "),
      new HighScore(0, 0, " "), new HighScore(0, 0, " "), new HighScore(0, 0, " "),
      new HighScore(0, 0, " ")};
    try (OutputStream fileOutputStream = new FileOutputStream("HighScores.dat")) {
      try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
        objectOutputStream.writeObject(h);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //Reads the .dat file and returns the constants
  public static HighScore[] getHighScores() {
    if (!new File("HighScores.dat").exists())
      initializeFile();
    try (InputStream fileInputStream = new FileInputStream("HighScores.dat")) {
      try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
        return (HighScore[]) objectInputStream.readObject();
      }
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return new HighScore[0];
  }

  //Adds a new HighScore to the .dat file and maintains the proper order
  public static void addHighScore(HighScore h) {
    HighScore[] highScores = getHighScores();
    highScores[highScores.length - 1] = h;
    for (int i = highScores.length - 2; i >= 0; i--) {
      if (highScores[i + 1].compareTo(highScores[i]) > 0) {
        HighScore temp = highScores[i];
        highScores[i] = highScores[i + 1];
        highScores[i + 1] = temp;
      }
    }
    try (OutputStream fileOutputStream = new FileOutputStream("HighScores.dat")) {
      try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
        objectOutputStream.writeObject(highScores);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    HighScore highScore = (HighScore) o;
    return score == highScore.score &&
      level == highScore.level &&
      Objects.equals(name, highScore.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(score, level, name);
  }
}
