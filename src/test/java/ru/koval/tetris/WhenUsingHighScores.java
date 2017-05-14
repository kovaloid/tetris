package ru.koval.tetris;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class WhenUsingHighScores {

  private static final String FILE_NAME = "HighScores.dat";

  @After
  public void beforeTest() {
    new File(FILE_NAME).delete();
  }

  @After
  public void afterTest() {
    new File(FILE_NAME).delete();
  }

  @Test
  public void shouldAddHighScoreAndGetIt() {
    int score = 5;
    int level = 6;
    String name = "name";

    HighScore.addHighScore(new HighScore(score, level, name));
    HighScore[] highScores = HighScore.getHighScores();

    Assert.assertEquals("Score", score, highScores[0].getScore());
    Assert.assertEquals("Level", level, highScores[0].getLevel());
    Assert.assertEquals("Name", name, highScores[0].getName());
  }
}
