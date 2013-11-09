object main {

  def isPrime(i: Int): Boolean = {
    2 to i-1 forall (i % _ != 0)
  }
  val n = 7
  ((1 until n) flatMap (i =>
    (1 until i) map (j => (i, j)))) filter {
    case (i, j) => if (isPrime(i+j)) true else false
  }




}
