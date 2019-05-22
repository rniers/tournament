package com.tournament.io

import java.io.File

import com.tournament.game.{BasketballGame, HandballGame}
import org.scalatest._


class ReaderSpec extends FlatSpec with Matchers {

  "Reader" should "read basketball game" in {

    val basketball = new File(getClass.getResource("/basketball.txt").getPath)
    val game = Reader.readGame(basketball)

    game.isRight shouldBe true
    game.right.get.isInstanceOf[BasketballGame] shouldBe true
    game.right.get.stats.size shouldEqual 6

  }

  it should "read handball game" in {

    val handball = new File(getClass.getResource("/handball.txt").getPath)
    val game = Reader.readGame(handball)

    game.isRight shouldBe true
    game.right.get.isInstanceOf[HandballGame] shouldBe true
    game.right.get.stats.size shouldEqual 6
  }

  it should "return error on empty files" in {
    val empty = new File(getClass.getResource("/empty.txt").getPath)
    val game = Reader.readGame(empty)

    game.isLeft shouldBe true
    game.left.get shouldEqual "Empty file: empty.txt."
  }

  it should "return error on malformed files" in {

    val malformed = new File(getClass.getResource("/malformed.txt").getPath)
    val game = Reader.readGame(malformed)

    game.isLeft shouldBe true
    game.left.get shouldEqual "java.lang.NumberFormatException: For input string: \"lalala\""
  }
}
