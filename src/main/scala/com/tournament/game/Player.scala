package com.tournament.game

/**
  * Type that represent single player statistics
  * for a single game
  */
sealed trait Player {

  def playerName: String
  def nickname: String
  def number: Int
  def teamName: String

  def playerRating: Int
}

case class BasketballPlayer(playerName: String,
                            nickname: String,
                            number: Int,
                            teamName: String,
                            score: Int,
                            rebounds: Int,
                            assists: Int)
    extends Player {

  override def playerRating: Int = score * 2 + rebounds + assists
}

case class HandballPlayer(playerName: String,
                          nickname: String,
                          number: Int,
                          teamName: String,
                          goalsMade: Int,
                          goalsReceived: Int)
    extends Player {

  override def playerRating: Int = goalsMade * 2 - goalsReceived
}
