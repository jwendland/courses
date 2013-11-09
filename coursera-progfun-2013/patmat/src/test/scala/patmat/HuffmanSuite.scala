package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)

  }

  test("times") {
    val counts = times("ababacdcabed".toList)
    assert(counts.contains(('a', 4)))
    assert(counts.contains(('b', 3)))
    assert(counts.contains(('c', 2)))
    assert(counts.contains(('d', 2)))
    assert(counts.contains(('e', 1)))
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("combine of singleton or nil") {
    val single = List(Leaf('e', 1))
    assert(combine(single) === List(Leaf('e',1)))
  }

  test("decodedSecret") {
    println("decodedSecret: " + decodedSecret.toString())
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
      assert(decode(frenchCode, encode(frenchCode)("salutmonsieur".toList)) === "salutmonsieur".toList)
    }
  }

  test("codeBits") {
    val table = List(('a', List(0,0)), ('b', List(0,1)), ('c', List(1,0)), ('d', List(1,1)))
    def testCode = codeBits(table)_
    assert(testCode('a') === List(0,0))
    assert(testCode('b') === List(0,1))
    assert(testCode('c') === List(1,0))
    assert(testCode('d') === List(1,1))
  }

  test("convert") {
    new TestTrees {
      val shouldTable = List(('a', List(0, 0)), ('b', List(0, 1)), ('d', List(1)))
      assert(convert(t2) === shouldTable)
    }
  }

  test("quickencode and decode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, quickEncode(t1)("ab".toList)) === "ab".toList)
      assert(decode(frenchCode, quickEncode(frenchCode)("salutmonsieur".toList)) === "salutmonsieur".toList)
    }
  }
}
