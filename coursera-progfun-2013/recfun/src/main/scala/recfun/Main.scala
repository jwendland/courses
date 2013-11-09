package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {

    type Memo = Map[(Int, Int), Int]

    def pascal_i(c: Int, r: Int, m: Memo): (Memo, Int) = {
      if (r == 0 || c == 0 || c == r) (Map((1,1) -> 1), 1)
      else m.get((c, r)) match {
        case Some(p) => (m, p)
        case None    =>
          val (m0, p0) = pascal_i(c, r-1, m)
          val (m1, p1) = pascal_i(c-1, r-1, m0)
          (m1, p0+p1)
      }
    }

    pascal_i(c, r, Map((1, 1) -> 1))._2
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {

    def balance_i(chars: List[Char], open: Int): Boolean = {
      val curopen = chars.head match {
        case '(' => open + 1
        case ')' => open - 1
        case _   => open
      }
      if (curopen < 0)
        false
      else {
        if (chars.size == 1)
          curopen == 0
        else
          balance_i(chars.tail, curopen)
      }
    }

    balance_i(chars, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money <= 0 || coins.isEmpty)
      0
    else
      {0 until money+1 by coins.head}.map { x =>
        val rest = money - x
        if (rest == 0)
          1
        else
          countChange(rest, coins.tail)
      }.sum
  }
}
