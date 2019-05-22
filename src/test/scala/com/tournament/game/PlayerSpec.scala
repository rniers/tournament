package com.tournament.game

import org.scalatest.{FlatSpec, Matchers}

class PlayerSpec extends FlatSpec with Matchers {

  "Basketball player" should "correctly calculate rating" in {
    val player = BasketballPlayer("name", "nick", 7, "team", 24, 5, 5)

    player.playerRating shouldBe 58
  }


  "Handball player" should "correctly calculate rating" in {
    val player = HandballPlayer("name", "nick", 7, "team", 10, 5)

    player.playerRating shouldBe 15
  }
}
