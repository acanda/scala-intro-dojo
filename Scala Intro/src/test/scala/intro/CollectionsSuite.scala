package intro

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

import org.scalatest.FunSuite

@RunWith(classOf[JUnitRunner])
class CollectionsSuite extends FunSuite {

  example {
    /**
     * Simple example to show a simple list:
     */
    List("this", "is", "a", "list", "of", "Strings")
  }

  /**
   * Can you correct the list so that it satisfies the conditions?
   */
  test("should contain numbers from 1 to 3") {
    val list = List(0) // fix

    assert(list.size === 3, "List should have 3 elements")

    // Notice how we access the first element and the rest of the list
    assert(list.head === 1, "First element should be 1")
    assert(list.tail === List(2, 3), "Elements past first one should be 2,3")
  }

  example {
    /**
     * You can access elements in a collection by treating it as a
     * function and passing the index as a parameter. Indexes are 0 based.
     *
     * For example:
     */
    val skywalkers = List("Luke", "Leia", "Anakin")
    println(skywalkers(1)) // output: "Leia"
  }

  /**
   * Can you get the correct flavors?
   * Bonus: access the first element using "head"
   */
  test("accessing elements") {
    val flavors = List("Banana", "Strawberry", "Chocolate", "Vanilla")
    val thirdElement = ???
    val firstElement = ??? // bonus: use specific method to get the first element

    assert("Chocolate" === thirdElement, "Write an expression to get Chocolate")
    assert("Banana" === firstElement, "Write an expression to get Banana")
  }

  example {
    /**
     * Immutable List: adding elements.
     *
     * NOTE: the default implementation of List is IMMUTABLE:
     *   adding/removing an element will give you a *new* collection.
     *
     * You can add an element in two ways: appending and pre-pending.
     */

    // Appending: 
    List(1, 2, 3) :+ 4 // output: List(1,2,3,4)

    // Prepending: 
    4 :: List(1, 2, 3) // output: List(4,1,2,3)

    // Alternate way to prepend: 
    4 +: List(1, 2, 3)

    /**
     * The colon ":" points to the position the new element will
     * be added to the list.
     */
  }

  /**
   * Let's consider the TDD cycle. We found a developer moving from
   * "test/fail" to "implement/pass". We would like to illustrate
   * what happened before and after that, which is: refactoring!
   *
   * Create two collections, one with "refactor" at the beginning
   * and the other with "refactor" at the end.
   */
  test("adding an element to an immutable list") {
    val incompleteTdd = List("test/fail", "implement/pass")
    val refactorLast = incompleteTdd // fix this, append "refactor" 
    val refactorFirst = incompleteTdd // fix this, prepend "refactor"

    // Note that original "incompleteTdd" collection was not modified!
    assert(incompleteTdd.size === 2, "The original tdd collection should contain 2 elements")

    // Instead, *new* collections were returned

    assert(refactorLast.size === 3, "TDD consists of 3 steps")
    assert(refactorLast(2) === "refactor", "Should end with 'refactor'")

    assert(refactorFirst.size === 3, "TDD consists of 3 steps")
    assert(refactorFirst.head === "refactor", "Should start with 'refactor'")
  }

  example {
    /**
     * Immutable List: adding two Lists together
     *
     * You can add collections together with "++"
     */
    List(1, 2) ++ List(3, 4) // output: List(1,2,3,4)
  }

  /**
   * Can you add these collections to form a proper jazz orchestra?
   */
  test("adding two lists") {
    val sax = List("alt sax", "tenor sax", "baritone sax")
    val brass = List("trumpet", "trombone")
    val rhythm = List("piano", "bass", "guitar", "drums")
    val jazzOrchestra = List() // fix this

    // Note that the original collections are not changed
    assert(sax.size === 3, "Should contain 3 sax types (sopran sax is rarely used)")
    assert(brass.size === 2, "Trumpet and trombone")
    assert(rhythm.size === 4, "The rhythm section should contain 4 members")

    // The resulting collection is a new one
    assert(jazzOrchestra.size === 9, "The orchestra should have 9 types of instruments")
  }

  example {
    /**
     * Filtering is done by passing an anonymous function which
     * takes as a parameter an element of the same type as the list.
     *
     * NOTE: the type declaration in the closure is automatically
     * type-inferenced. No need to declare types.
     *
     * We introduce Range(s), just for illustration purposes.
     * Ranges can be instantiated like this:
     */
    (1 to 10) // includes 10
    (1 until 10) // excluding 10
  }

  /**
   * Here we filter all EVEN numbers.
   * Can you find the bugs in our filter?
   */
  test("filtering a range for even numbers") {
    val numbers = (1 to 8)
    val evenNrs = numbers.filter(n => n % 3 == 1) // fix

    assert(evenNrs.size === 4, "Should find 4 even numbers")
    assert(!evenNrs.contains(1), "Should not contain 1")
    assert(evenNrs.contains(2), "Should contain 2")
  }

  example {
    /**
     * For simple expressions in a closure, you can skip parameter
     * declaration. Instead, you can use the "wildcard-argument".
     */
    Seq(1, 2, 3).filter(n => n % 2 == 0)
    // Can be written as:
    Seq(1, 3, 4).filter(_ % 2 == 0)
  }

  /**
   * Here we filter numbers greater than 4.
   * Can you find the bug?
   */
  test("filtering numbers greater than 4") {
    val numbers = (1 until 8)
    val greaterThanFour = numbers.filter(_ > 44) // fix

    assert(numbers.contains(8) === false, "Should not contain 8 (note the *until*)")

    assert(greaterThanFour.size === 3, "Should find 3 numbers")
    assert(greaterThanFour.contains(5), "Should contain 5")
    assert(!greaterThanFour.contains(4), "Should not contain 4")
  }

  /**
   * We want to select all names containing "e" (all cases!)
   *
   * HINT: String has methods "contains" and "toLowerCase"
   *
   * Can you use the wildcard-argument described above to
   * solve this?
   */
  test("filtering names") {
    val names =
      List(
        "Abraham",
        "Matthew",
        "Franz",
        "Roland",
        "Raphael",
        "Ernest")
    val withE = names.filter(???) // fix

    assert(withE.size === 3)
    assert(withE.head === "Matthew", "First name should be Matthew")
    assert(withE.contains("Ernest"), "Should contain Ernest")
    assert(withE.contains("Raphael"), "Should contain Raphael")
  }

  example {
    /**
     * With the method "exists" you can see if there
     * is an element that satisfies a condition.
     * Example:
     */
    Seq("blah", "", "foo").exists(str => str.isEmpty)
    // output: true

    /**
     * With "count" you can count how many satisfy that
     * condition.
     * Example:
     */
    Seq("blah", "", "foo").count(str => !str.isEmpty)
    // output: 2
  }

  /**
   * Help! Is there a doctor in the audience? Find out.
   * Also find out how many developers are in the list.
   *
   */
  test("find a doctor and how many developers") {
    val people = List("Fritz(dev)", "Franz(dev)", "Fred(doc)", "Fran(dev)")
    val isThereADoctor = ??? // use "exists" on the list
    val howManyDevs = ??? // use "count" on the list

    assert(true === isThereADoctor, "There should be doctor")
    assert(3 === howManyDevs, "There should be 3 developers")
  }

  example {
    /**
     * Mapping lets you apply, or "map", a function to a
     * collection of values.
     *
     * For example:
     */
    List(1, 2, 3, 4).map(n => n * 2)
    // output: List(2,4,6,8)

    /**
     * If you need multiple lines, you can use curly brackets
     * instead of normal parenthesis. Like:
     */
    List(1, 2, 3, 4).map { n =>
      // ...
      // ...
    }
  }

  /**
   * Can you extract the domain names from the e-mail addresses?
   * Can you extract their secret bases from the domains?
   *
   * HINT: String has the method "split", and the method
   * dropRight(n) which you can use to drop the last n elements
   * of a collection (String is a collection)
   */
  test("mapping") {
    val emails =
      List(
        "batman@batcave.com",
        "superman@solitude.com",
        "heman@grayskull.com")
    val domains = emails.map { e =>
      ??? // implement
    }
    val bases = domains.map(d => ???) // implement

    assert(List("batcave.com", "solitude.com", "grayskull.com") === domains)
    assert(List("batcave", "solitude", "grayskull") === bases)
  }

  example {
    /**
     * Reducing a collection is to apply one operation to all
     * its members to obtain a final result.
     *
     * A typical example is to sum all numbers.
     *
     * For example:
     */
    List(1, 2, 3, 4).reduce((n1, n2) => n1 + n2)
    // output: 10

    /**
     * Or using two wildcard-arguments:
     */
    List(1, 2, 3, 4).reduce(_ + _)
    // output: 10   
  }

  /**
   * Implement your own "strJoin" function using "reduce".
   * It becomes a list of string and a "joiner" string.
   */
  test("reducing") {
    def strJoin(strings: List[String], joiner: String): String = {
      ??? // implement
    }

    assert(strJoin(List("rock", "paper", "scissors"), joiner = "/") === "rock/paper/scissors")
    assert(strJoin(List("Hello", "world"), joiner = " ") === "Hello world")
  }

  example {
    /**
     * Immutable Maps
     *
     * The quickest way to create a Map is like this:
     */
    val myMap = Map("key1" -> "value1", "key2" -> "value2")

    /**
     * To access an element, you do like this:
     */
    myMap("key1") // output: "value1"

    /**
     * (the best way is with the "get" method shown below, but
     * you can ignore that for now; it returns an Option, which you'll
     * see, or saw, in another tutorial)
     *
     * You can add an element simply with "+".
     * Example:
     */
    Map("a" -> 1, "b" -> 2) + ("c" -> 3)

    /**
     *   Or you can combine two maps like this:
     */
    Map("a" -> 1, "b" -> 2) ++ Map("c" -> 3, "d" -> 4)

    /**
     *   NOTE that since Maps are immutable by default, you don't modify
     * the existing map: you get a new one.
     *
     * You can ask all the keys of a Map with the "keys" method.
     * Similarly, you can ask the *values* of a Map with...
     * (yes, you guessed right)
     */
  }

  /**
   * Our friend Fred went for dinner at the pub, and then
   * bought an ice-cream on the street. Change the code to reflect this.
   * We also want to know the name of the items he consumed which have
   * an "e" on it.
   *
   * HINT: filters the keys for the consumed items
   * HINT: you can use the method "sum" to add a list of numbers
   * Bonus: implement your own sum with "reduce"
   */
  test("immutable Maps") {
    val atThePub = Map(
      "pizza" -> 7.50,
      "beer" -> 2.20,
      "cafe" -> 3.40)

    val pubAndStreet = atThePub // add "ice-cream" costing 2.30
    val pubAndStreetCost = -1 // don't add manually!
    val consumedItemsWithE = List("") // all things consumed in the pub and the street

    // Adding "ice-cream" did not modify the original map, since it's immutable
    assert(atThePub.get("ice-cream") === None, "Ice-cream was not taken in the pub!")

    assert(pubAndStreet.size === 4, "Consumed items should be 4")
    assert(pubAndStreet("ice-cream") === 2.30, "Ice-Cream should cost 2.30")
    assert(pubAndStreetCost === (2.30 + 3.40 + 2.20 + 7.50), "Total cost is wrong")

    assert(consumedItemsWithE.toSet === Set("beer", "cafe", "ice-cream"))

    // Extra bonus: find the highest price 
    // HINT: use a combination of "reduce" and "if"
    def mostExpensivePrice(items: Map[String, Double]): Double = ???
    //assert(mostExpensivePrice(pubAndStreet) === 7.50)
  }

  /**
   * There is much more to Scala collections:
   * - mutable collections
   * - Iterators
   * - Streams
   * - Views
   * - etc.
   *
   * But with these exercises you should have gotten a fair
   * introduction to it.
   */

}
