package intro

import org.scalatest.FunSuite
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class NullExerciseSuite extends FunSuite {

  /**
   * What's the problem we are trying to solve?
   *
   * NullpointerExceptions and constant null checks.
   *
   * A contrived example:
   */

  def length(s: String): Int = s.length()

  length(null)

  /**
   * First exercise: delete the code above ^^^^.
   *
   * We use the Option type from here on instead:
   */

  val noValue = None

  val aValue = Some("value")

  val maybe:Option[String] = someFunction()
  /**
   * The type ^^^^^^^^^^^^ annotation is just for clarity
   * 
   * The absence of a value is now explicit: None
   * 
   */

  /**
   * You want to say "hello" but nobody is there:
   *
   * Use Some(_) and None() where appropriate.
   *
   */
  test("Use None to express the absence of a value and use Some for values") {
    val hello: Option[String] = ???
    assert(hello.isDefined)

    val name: Option[String] = ???
    assert(name.isEmpty)
  }

  /**
   * Now you can write code like this:
   */

  val name = Some("John")

  if (name.isDefined) name.get else "No name defined"

  /**
   * But that is a bit clumsy. You might ask how is that better than checking for null?
   *
   * Check out the scala doc if we can do better:
   *
   * @see http://www.scala-lang.org/api/current/index.html#scala.Option
   *
   * We are looking for a method that gives us a default value
   * if the Option is not defined == None
   *
   */
  test("If an Option is not defined there can be a default value") {
    assert("Hello World" === "Hello " + maybe.get)
  }
  
  
  /**
   * What if just want to ignore the non-existing values 
   * without checking for None all the time?
   * 
   */
  test("Option.map and flatMap help unpacking the goods") {
    
    val names = List(Some("Peter"), None, Some("Mary"))
    
    assert(names.flatMap(n => n.map("Hello " + _)) === ???)
  }
  

  /**
   * That looked quite complicated to the untrained eye...
   * 
   * For comprehensions help 'unpacking' Options 
   * so that we can use the values they 'contain'.
   * 
   * And as before: they are "None-safe"
   *
   * Tip: you can use Console.out.println() to find out 
   * what the result looks like
   */
  test("For comprehensions can be easier to read than flatMap & Co") {
    val names = List(Some("Peter"), Some("Paul"), None, Some("Mary"))

    val greetings = for {
      mayBeAName <- names
      certainlyAName <- mayBeAName
    } yield "Hello " + certainlyAName

    assert(??? == greetings.size)
  }
  
  /**
   * That's all. You made it. 
   * 
   * The code below is just for demonstration purposes. 
   */
  def someFunction() = None
}