package com.tournament.io

import com.tournament.game.{BasketballPlayer, HandballPlayer, Player}

import scala.util.Try

trait LineReader[T <: Player] {
  def read(line: Array[String]): Either[String, T]
}


object LineReader {
  implicit val basketballReader = new LineReader[BasketballPlayer] {
    override def read(line: Array[String]): Either[String, BasketballPlayer] =  Try {
      BasketballPlayer(line(0), line(1), line(2).toInt, line(3), line(4).toInt, line(5).toInt, line(6).toInt)
    }.toEither.left.map(_.toString)
  }

  implicit val handballReader = new LineReader[HandballPlayer] {
    override def read(line: Array[String]): Either[String, HandballPlayer] = Try {
      HandballPlayer(line(0), line(1), line(2).toInt, line(3), line(4).toInt, line(5).toInt)
    }.toEither.left.map(_.toString)
  }
}

