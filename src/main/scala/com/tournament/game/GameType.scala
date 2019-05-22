package com.tournament.game

import com.tournament.io.LineReader

sealed trait GameType {
  type P <: Player

  /** Function that converts row input to the concrete
    * [[Player]] intstance
    *
    * @param reader
    * @return
    */
  def reader(implicit reader: LineReader[P]): Array[String] => Either[String, P]

  /** String representation of the game type
    *
    * @return
    */
  def name: String
}

object Basketball extends GameType {
  override type P = BasketballPlayer

  override val name: String = "basketball"

  override def reader(
    implicit reader: LineReader[BasketballPlayer]
  ): Array[String] => Either[String, BasketballPlayer] =
    reader.read
}

object Handball extends GameType {
  override type P = HandballPlayer

  override def reader(implicit reader: LineReader[P]): Array[String] => Either[String, HandballPlayer] = reader.read

  override val name: String = "handball"
}
