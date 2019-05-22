package com

package object tournament {

  implicit class SeqExt[A, B](seq: Seq[Either[A, B]])  {
    def sequence: Either[A, Seq[B]] =
      seq.foldRight(Right(Nil): Either[A, List[B]]) {
        (e, acc) => for (xs <- acc.right; x <- e.right) yield x :: xs
      }
  }

}
