def insert(i: Int, xs: List[Int]): List[Int] = xs match {
  case List()  => List(i)
  case y :: ys =>
    if (i <= y)
      i :: xs
    else
      y :: insert(i, ys)
}

def isort(xs: List[Int]): List[Int] = xs match {
  case List()  => List()
  case y :: ys => insert(y, isort(ys))
}

isort(List(3,8,1,2,9,0,5,3,6,4))