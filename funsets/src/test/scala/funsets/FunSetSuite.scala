package funsets

/**
 * This class is a test suite for the methods in object FunSets.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class FunSetSuite extends munit.FunSuite:

  import FunSets.*

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

  trait TestSets:
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)

  /**
   * This test is currently disabled (by using @Ignore) because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", remove the
   * .ignore annotation.
   */
  test("singleton set one contains one") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets:
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
  }

  test("union contains all elements of each set") {
    new TestSets:
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
  }

  test("intersect") {
    new TestSets:
      val s = intersect(s1, union(s1, s2))  // {1} intersect {1, 2} = {1}
      assert(contains(s, 1), "intersect contains 1")
      assert(!contains(s, 2), "intersect not contains 2")
  }

  test("diff") {
    new TestSets:
      val xs = union(s1, s2)  // {1, 2}
      val ys = union(xs, s3)  // {1, 2, 3}
      val s = diff(ys, xs)    // {1, 2, 3} diff {1, 2} = {3}
      assert(contains(s, 3), "diff contains 3")
      assert(!contains(s, 1), "diff not contains 1")
      assert(!contains(s, 2), "diff not contains 2")
  }

  test("filter") {
    new TestSets:
      val xs = union(s1, s2) // {1, 2}
      val ys = union(xs, s3) // {1, 2, 3}
      val s = filter(ys, (x => x > 1))   // {1, 2, 3} filter (x => x > 1) = {2, 3}
      assert(contains(s, 3), "filter: s contains 3")
      assert(!contains(s, 1), "filter: s not contains 1")

      val t = filter(ys, (x => (x * 2) == 4))   // {1, 2, 3} filter (x => (x * 2) == 4)) = {2}
      assert(contains(t, 2), "filter: t contains 2")
      assert(!contains(t, 1), "filter: t not contains 1")
  }

  test("forall") {
    new TestSets:
      val s = union(union(s1, s2), s3)  // {1, 2, 3}
      assert(forall(s, x => x > -1), " all items are (x > -1) ?")
      assert(!forall(s, x => x > 2), " all items are (x > 2) ?")

      val y = union(s2, s4)
      assert(forall( y, x => ((x % 2) == 0)), " are all items even ?")
  }

  test("exists") {
    new TestSets:
      val s = union(union(s1, s2), s3)  // {1, 2, 3}
      assert(exists(s, x => x > -1), "there is at least one item greater than -1?")
      assert(!exists(s, x => x > 5), "there is at least one item greater than 5?")
      assert(exists(s, x => ((x % 2) == 0)), "there is at least one even?")
  }

  test("map") {
    new TestSets:
      val s = map(union(s2, s3), x => x * 2 )  // {2, 3} -> {4, 6}
      assert(contains(s, 4), "map contains 4")
      assert(contains(s, 6), "map contains 4")
      assert(!contains(s, 2), "map not contains 2")

      val t = map(union(s2, s3), (x => x - 1) ) // {2, 3} -> {1, 2}
      assert(contains(t, 1), "map contains 0")
      assert(!contains(t, 3), "map contains 2")
  }

  import scala.concurrent.duration.*
  override val munitTimeout = 10.seconds
