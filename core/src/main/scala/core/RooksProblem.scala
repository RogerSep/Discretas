package core

import scala.reflect.ClassTag

trait RooksProblem {

  val permutations2x2 = List(
    Array(
      Array(
        "♜", "·"
      ),
      Array(
        "·", "♜"
      )
    ),

    Array(
      Array(
        "·", "♜"
      ),
      Array(
        "♜", "·"
      )
    )
  )

  def fillPosition(chessboardSize: Int)(x: Int): Int =
    if (x < chessboardSize / 2) x
    else fillPosition(chessboardSize)(chessboardSize - x - 1)

  def findPermutations(chessboardSize: Int): List[Array[Array[String]]] = {
    if (chessboardSize == 0) {
      List()
    } else if (chessboardSize == 1) {
      List(Array(Array("♜")))
    } else if (chessboardSize == 2) {
      permutations2x2
    } else if (chessboardSize % 2 != 0) {
      val permutations = findPermutations(chessboardSize - 1)
      permutations.map { permutation =>
        val centerRow = (0 until chessboardSize)
          .map (x => if (x == chessboardSize / 2) "♜" else "·").toArray

        permutation
          .map(_.insert(chessboardSize / 2, "·"))
          .insert(chessboardSize / 2, centerRow)
      }
    } else {
      val permutations = findPermutations(chessboardSize - 2)
      val solution = for {
        pos <- 0 until chessboardSize
        permutation <- permutations
        firstRow = Array.fill(pos)("·") ++ Array("♜") ++ Array.fill(chessboardSize - pos - 1)("·")
      } yield {
        permutation
          .map(_
            .insert(fillPosition(chessboardSize)(pos), "·")
            .insert((chessboardSize - pos - 1).max(pos), "·")
          )
          .insert(0, firstRow)
          .insert(chessboardSize - 1, firstRow.reverse)
      }

      solution.toList
    }
  }

  def show(permutations: List[Array[Array[String]]]): String = {
    val builder = new StringBuilder

    permutations.foreach { p =>
      builder.append("\n")
      p.foreach { r =>
        builder.append("\n")
        r.foreach( c => builder.append(s"$c ") )
      }
    }

    builder.append(s"\n${permutations.size} permutations found")

    builder.toString()
  }

  implicit class ArrayWithInsert[T: ClassTag](arr: Array[T]) {
    def insert(position: Int, value: T): Array[T] = {
      arr.take(position) ++
      Array(value) ++
      arr.drop(position)
    }
  }

}
