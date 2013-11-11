package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  property("min1") = forAll { a: Int =>
    val h = insert(a, empty)
    findMin(h) == a
  }

  property("gen1") = forAll { (h: H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h))==m
  }

  property("min2") = forAll { (a: Int, b: Int) =>
    val h = insert(b, insert(a, empty))
    findMin(h) == (if (a < b) a else b)
  }

  property("delete single mininum") = forAll { a: Int =>
    deleteMin((insert(a, empty))) == empty
  }

  property("mold minimum") = forAll { (h1: H, h2: H) =>
    val mh1 = findMin(h1)
    val mh2 = findMin(h2)
    val mm = findMin(meld(h1, h2))
    if (mh1 < mh2) mh1 == mm else mh2 == mm
  }

  property("meld not empty") = forAll { (h1: H, h2: H) =>
    !(isEmpty(h1) || isEmpty(h2)) && !isEmpty(meld(h1, h2))
  }

  property("insert/delete 2") = forAll { (a: Int, b: Int) =>
    isEmpty((deleteMin(deleteMin(insert(a, insert(b, empty))))))
  }

  def isSorted(h: H): Boolean = {
    if (isEmpty(h)) true
    else {
      val m = findMin(h)
      val r = deleteMin(h)
      if (isEmpty(r)) true
      else (m <= findMin(r)) && isSorted(r)
    }
  }

  property("sorted") = forAll { (h1: H, h2: H) =>
    val h = meld(h1, h2)
    isSorted(h)
  }

  property("insert order irrelevant") = forAll { (a: Int, b: Int, c: Int) =>
    val m1 = findMin(insert(a, insert(b, insert(c, empty))))
    val m2 = findMin(insert(a, insert(c, insert(b, empty))))
    val m3 = findMin(insert(b, insert(a, insert(c, empty))))
    val m4 = findMin(insert(b, insert(c, insert(a, empty))))
    val m5 = findMin(insert(c, insert(b, insert(a, empty))))
    val m6 = findMin(insert(c, insert(a, insert(b, empty))))
    m1 == m2 && m1 == m3 && m1 == m4 && m1 == m5 && m1 == m6
  }

  def heap2List(h: H): List[Int] = {
    if (isEmpty(h)) Nil
    else findMin(h)::heap2List(deleteMin(h))
  }

  property("nothing gets lost") = forAll { l: List[Int] =>
    val h = l.foldLeft(empty)((h, i) => insert(i, h))
    val l2 = heap2List(h)

    isSorted(h) && l2.size == l.size && l.foldLeft(true)((b, i) => b && l2.contains(i))
  }


  lazy val genHeap: Gen[H] = for {
    a <- arbitrary[A]
    h <- oneOf(empty, genHeap)
  } yield insert(a, h)

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

}
