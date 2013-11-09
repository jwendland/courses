package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }
  
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   * 
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   * 
   *   val s1 = singletonSet(1)
   * 
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   * 
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   * 
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val u1 = union(s1, s2)
    val u2 = union(s2, s3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
      assert(!contains(s1, 2), "2 is not in singletonSet(1)")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersect contains only common elements") {
    new TestSets {
      val i = intersect(u1, u2)
      assert(contains(i, 2), "Intersect 1")
      assert(!contains(i, 1), "Intersect 2")
      assert(!contains(i, 3), "Intersect 3")
    }
  }

  test("diff contains only elements in s not in t") {
    new TestSets {
      val d = diff(u1, u2)
      assert(contains(d, 1), "Intersect 1")
      assert(!contains(d, 2), "Intersect 2")
      assert(!contains(d, 3), "Intersect 3")
    }
  }

  test("filter contains elements for which predicate is true") {
    new TestSets {
      val bigSet = union(union(union(u1, u2), singletonSet(4)), singletonSet(5))
      val filtered = filter(bigSet, x => x >= 4)
      assert(contains(filtered, 4), "Filter 1")
      assert(contains(filtered, 5), "Filter 2")
      assert(!contains(filtered, 6), "Filter 3")
      assert(!contains(filtered, 1), "Filter 4")
      assert(!contains(filtered, 3), "Filter 5")
    }
  }

  test("forall tests whether all elements in set satisfy predicate") {
    new TestSets {
      val bigSet = union(union(union(u1, u2), singletonSet(4)), singletonSet(5))
      assert(forall(bigSet, x => x > 0), "Forall 1")
      assert(forall(bigSet, x => x < 6), "Forall 2")
      assert(!forall(bigSet, x => x < 5), "Forall 3")
      assert(!forall(bigSet, x => x > 1), "Forall 4")
      assert(!forall(bigSet, x => x == 3), "Forall 5")
    }
  }

  test("exists tests whether at least on element satisfies predicate") {
    new TestSets {
      val bigSet = union(union(union(u1, u2), singletonSet(4)), singletonSet(5))
      assert(exists(bigSet, x => x == 3), "Exists 1")
      assert(exists(bigSet, x => x == 1), "Exists 2")
      assert(exists(bigSet, x => x == 5), "Exists 3")
      assert(!exists(bigSet, x => x == 0), "Exists 4")
      assert(!exists(bigSet, x => x == 6), "Exists 5")
      assert(exists(bigSet, x => x > 4), "Exists 6")
      assert(exists(bigSet, x => x > 3), "Exists 7")
    }
  }

  test("map works") {
    new TestSets {
      val bigSet = union(u1, u2)
      assert(exists(map(bigSet, x => x*2), x => x == 6), "Exists 6")
      assert(exists(map(bigSet, x => x*2), x => x == 4), "Exists 4")
      assert(exists(map(bigSet, x => x*2), x => x == 2), "Exists 2")
      assert(!exists(map(bigSet, x => x*2), x => x == 1), "!Exists 1")
      assert(!exists(map(bigSet, x => x*2), x => x == 3), "!Exists 3")
      // {1,3,4,5,7,1000}
      val set2: Set = x => List(1,3,4,5,7,1000).contains(x)
      assert(FunSets.toString(map(set2, x => x-1)) === "{0,2,3,4,6,999}")

    }
  }
}
