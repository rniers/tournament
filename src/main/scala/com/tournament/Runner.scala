package com.tournament

import java.io.File

import com.tournament.game.Game
import com.tournament.io.Reader

object Runner {

  /** Calculate Most Valuable Player
    *
    * @param games - set of all games
    * @return
    */
  def mvp(games: Seq[Game[_]]): (String, Int) = {
    games
      .flatMap(_.ratings)
      .groupBy(_._1)
      .mapValues(_.map(_._2).sum)
      .maxBy(_._2)
  }

  def main(args: Array[String]): Unit = {

    case class Config(games: Seq[File] = Seq.empty)

    val parser = new scopt.OptionParser[Config]("Tournament MVP") {
      opt[Seq[File]]('g', "games").required().valueName("<files1>,<file2>...")
        .action((x,c) => c.copy(games = x))
        .text("list of input files")
    }

    parser.parse(args, Config()) match {
      case Some(config) =>

        val gamesOrErr = config.games.map(Reader.readGame).sequence

        gamesOrErr match {
          case Right(games) =>
            val (nickname, score) = mvp(games)

            println(s"MVP is $nickname with score $score")

          case Left(err) =>
            println(s"Error: $err")
        }

      case None =>
    }
  }
}
