package com.tournament.io

import java.io.File

import com.tournament.game._
import com.tournament.io.LineReader._
import scala.io.Source

object Reader {

  private val Separator = ";"

  def parseRow(line: String): Array[String] = line.split(Separator)

  def readGame(file: File): Either[String, Game[_]] = {

    val iterator = Source.fromFile(file).getLines()

    val typeNameOrErr =
      if (!iterator.hasNext) Left(s"Empty file: ${file.getName}.")
      else Right(iterator.take(1).next())

    val rows = iterator.map(parseRow)

    for {
      tpe <- typeNameOrErr
      game <- tpe.toLowerCase match {
        case Basketball.name =>
          rows.map(Basketball.reader).toSeq.sequence.map(BasketballGame.apply)
        case Handball.name =>
          rows.map(Handball.reader).toSeq.sequence.map(HandballGame.apply)
        case _ => Left(s"Unknown game type, file: ${file.getPath}.")
      }
    } yield game
  }
}
