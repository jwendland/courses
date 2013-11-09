class Rational(x: Int, y: Int) {
  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a
    else gcd(b, a % b)
  private val g = gcd(x, y)
  def numer = x / g
  def denom = y / g

  def +(that: Rational): Rational = {
    new Rational(numer * that.denom + denom * that.numer, denom * that.denom)
  }
  def unary_- = new Rational(-numer, denom)
  def -(that: Rational) = this + -that

  def <(that: Rational): Boolean =
    numer * that.denom < denom * that.numer

  def max(that: Rational): Rational =
    if (this < that) that else this
  override def toString = numer + (if (denom != 1) "/" + denom else "")
}
val x = new Rational(1, 3)
val y = new Rational(5, 7)
val z = new Rational(3, 2)
x + x
y + y
x - y - z
x < y
x max y


