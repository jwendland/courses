def sum(f: Int => Int)(a: Int, b: Int): Int =
  if (a > b) 0 else f(a) + sum(f)(a + 1, b)

sum(x => x)(1, 5)
def si = sum(x => x)_
si(1, 5)

def product(f: Int => Int)(a: Int, b: Int): Int =
  if (a > b) 1 else f(a) * product(f)(a + 1, b)

product(x => x)(1, 5)

def factorial(x: Int): Int = product(x => x)(1, x)

factorial(5)

def general(u: Int, op: (Int, Int) => Int)(f: Int => Int)(a: Int, b: Int): Int =
  if (a > b) u
  else op(f(a), general(u, op)(f)(a + 1, b))

def sum2 = general(0, (x,y) => x+y)_

sum2(x => x)(1, 5) == sum(x => x)(1, 5)

def prod2 = general(1, (x,y) => x*y)_

prod2(x => x)(1, 5) == product(x => x)(1, 5)





