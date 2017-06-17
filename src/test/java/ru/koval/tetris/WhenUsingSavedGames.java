package ru.koval.tetris;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Date;

public class WhenUsingSavedGames {

  private static final String FILE_NAME = "SavedGames.dat";

  @After
  public void beforeTest() {
    new File(FILE_NAME).delete();
  }

  @After
  public void afterTest() {
    new File(FILE_NAME).delete();
  }

  @Test
  public void shouldAddSavedGameAndGetIt() {
    String name = "name";
    Piece currentPiece = new Piece(1, new Tetris(new JFrame()));
    Color[][] blocks = new Color[1][1];
    Piece[] nextPieces = new Piece[1];
    int points = 4;
    Piece hold = new Piece(1, new Tetris(new JFrame()));
    int level = 5;
    int linesToGo = 6;
    Date date = new Date();

    SavedGame[] savedGames = new SavedGame[1];
    savedGames[0] = new SavedGame(name, currentPiece, blocks, nextPieces, points, hold, true, level, linesToGo, date);
    SavedGame.saveGames(savedGames);
    SavedGame[] actualSavedGames = SavedGame.readGames();

    Assert.assertEquals("Name", name, actualSavedGames[0].getName());
    Assert.assertEquals("Level", level, actualSavedGames[0].getLevel());
    Assert.assertEquals("LinesToGo", linesToGo, actualSavedGames[0].getLinesToGo());
    Assert.assertEquals("Points", points, actualSavedGames[0].getPoints());
    Assert.assertEquals("Date", date, actualSavedGames[0].getDate());
  }
}
