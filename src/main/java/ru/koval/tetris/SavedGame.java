package ru.koval.tetris;

import java.awt.Color;
import java.io.*;
import java.util.Date;

public class SavedGame implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private Piece currentPiece;
  private Color[][] blocks;
  private Piece[] nextPieces;
  private int points;
  private Piece hold;
  private boolean held;
  private int level;
  private int linesToGo;
  private Date date;
  private Color color = Color.white;
  private Color deleteColor = Color.white;

  public SavedGame(String name, Piece currentPiece, Color[][] blocks, Piece[] nextPieces,
                   int points, Piece hold, boolean held, int level, int linesToGo, Date date) {
    this.name = name;
    this.currentPiece = currentPiece;
    this.blocks = blocks;
    this.nextPieces = nextPieces;
    this.points = points;
    this.hold = hold;
    this.held = held;
    this.level = level;
    this.linesToGo = linesToGo;
    this.date = new Date(date.getTime());
  }

  public Color[][] getBlocks() {
    return blocks;
  }

  public Piece[] getNextPieces() {
    return nextPieces;
  }

  public int getPoints() {
    return points;
  }

  public Piece getHold() {
    return hold;
  }

  public boolean getHeld() {
    return held;
  }

  public int getLevel() {
    return level;
  }

  public int getLinesToGo() {
    return linesToGo;
  }

  public String getName() {
    return name;
  }

  public Piece getCurrentPiece() {
    return currentPiece;
  }

  public Date getDate() {
    return new Date(date.getTime());
  }

  //If this saved game is selected, it turns yellow
  public void setSelected(boolean b) {
    if (b)
      color = Color.yellow;
    else
      color = Color.white;
  }

  public Color getColor() {
    return color;
  }

  public void setDeleteSelected(boolean b) {
    if (b)
      deleteColor = Color.yellow;
    else
      deleteColor = Color.white;
  }

  public Color getDeleteColor() {
    return deleteColor;
  }

  //Writes the array of saved games to a .dat file
  public static void saveGames(SavedGame[] savedGames) {
    try (OutputStream fileOutputStream = new FileOutputStream("SavedGames.dat")) {
      try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
        objectOutputStream.writeObject(savedGames);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  //Reads the saved games from the .dat file
  public static SavedGame[] readGames() {
    SavedGame[] games = new SavedGame[0];
    try (InputStream fileInputStream = new FileInputStream("SavedGames.dat")) {
      try (ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
        games = (SavedGame[]) objectInputStream.readObject();
      }
    } catch (FileNotFoundException | EOFException ignored) {
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return games;
  }
}
