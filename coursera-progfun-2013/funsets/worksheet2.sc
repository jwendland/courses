abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  def contains(x: Int) =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true
  def incl(x: Int) =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this
  override def toString = "{" + left + elem + right + "}"

  def union(other: IntSet): IntSet =
    ((left union right) union other) incl elem
}

object Empty extends IntSet {
  def incl(x: Int) = new NonEmpty(x, Empty, Empty)
  def contains(x: Int) = false
  override def toString = "."

  def union(other: IntSet): IntSet = other
}

val t1 = new NonEmpty(3, Empty, Empty)
val t2 = t1 incl 4
val t3 = t1 incl 2
val t4 = t2 union t3
val t5 = t4 union new NonEmpty(1, Empty, Empty)




