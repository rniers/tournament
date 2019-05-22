package com.tournament.game

trait Game[T <: Player] {

  /** Statistics records for the game
    *
    * @return
    */
  def stats: Seq[T]

  /** Winning team name
    *
    * @return
    */
  def teamWon: String

  /** Sequence of pairs:
    * player nickname -> player overall rating for the current game
    *
    * @return
    */
  def ratings: Seq[(String, Int)] = {
    stats.map { p =>
      val bonus = if (p.teamName == teamWon) 10 else 0
      p.nickname -> (p.playerRating + bonus)
    }
  }
}

trait WinWithMaxPointsSum[T <: Player] extends Game[T] {

  def points(p: T): Int

  override def teamWon: String = {
    stats
      .groupBy(_.teamName)
      .mapValues(_.map(points).sum)
      .maxBy(_._2)
      ._1
  }
}

case class BasketballGame(stats: Seq[BasketballPlayer]) extends WinWithMaxPointsSum[BasketballPlayer] {
  override def points(p: BasketballPlayer): Int = p.score
}

case class HandballGame(stats: Seq[HandballPlayer]) extends WinWithMaxPointsSum[HandballPlayer] {
  override def points(p: HandballPlayer): Int = p.goalsMade
}
