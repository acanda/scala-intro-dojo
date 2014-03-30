package intro

import org.scalatest.FunSuite

/**
 * Workshop exercise showing classes and objects
 */
class ClassPatternSuite extends FunSuite {

  //Traits are ALMOST like 'interfaces', they can't be instantiated directly
  trait Language {
    def isFunctional: Boolean
  }

  //This is how you can write a class, with a default constructor that takes a name
  class LispDialect(name: String) extends Language {
    def isFunctional = true
  }

  //objects are singletons
  object Scala extends Language {
    def isFunctional = true
  }

  //you can override a method with a val
  object Java extends Language {
    val isFunctional = false
  }

  /**
   * How do you think an Object is accessed?
   */
  test("difference between class and object")({
    val classInstance = new LispDialect("Racket")
    assert(classInstance != null)

    val objectInstance = ???
    assert(objectInstance != null)
  })

  /**
   * Class can contain various members. They can be variables, values and methods (procedures or functions).
   * Return types are normally inferred by compiler, but it is a good style to used them.
   * Types of the parameters have to be supplied to compiler in method signatures.
   */
  class Coder(val lang: Language = Java, var experience: Int = 0) {

    // return type can be specified, don't need braces if it's just one line
    def function(s: String, i: Int): Int = s.toInt + i

    // return type can also be inferred
    def position =
      if (experience > 4) "Senior"
      else "Junior"

    //WARNING: Entering land of side effects !!!

    // no '=' sign after definition, will return type 'Unit' which is like 'void'
    def procedure() {
      println("I'm just a side effect, since I return no value")
    }

    // we can return 'Unit' even using the equals sign 
    def stillAProcedure(param: String): Unit = {
      println("I'm still a side effect, I return Unit, even if you pass me a parameter" + param)
    }

    //some pattern matching, we'll see it in a minute

  }

  /**
   * Congratulations, you just earned a year of experience
   */
  test("re-assign variable") {
    val coder = new Coder

    ???

    assert(coder.experience == 1)
  }

  /**
   * We need a Ruby coder with 4 years of experience, and we need him now!!!
   */
  test("construct an instance") {
    val coder = new Coder(???, ???)

    assert(coder.lang === ???)
    assert(coder.experience === 4)

  }

  /**
   * Can you say what the function returns?
   * And what is the return type?
   */
  test("return type of function") {
    val coder = new Coder(Scala)

    val result = coder.position
    assert(result === ???)
    assert(result.getClass === classOf[????])
  }

  /**
   * Class default constructor is in the the class signature and body
   * Parameters of the default constructor can have various visibilities. In our example:
   *  - firstName is private value
   *  - lastName is public value
   *  - age is public variable
   * For some of them compiler generates accessors/mutators, but these does not follow Java rules.
   * They are named by fields without get/set prefixes. Using @BeanProperty annotation you can say
   * the compiler to generate also the getter/setter pair of the methods. In case of values only
   * accessor is generated.
   */
  class FamousScalaCoder(firstName: String, val lastName: String, var age: Int) extends Coder(Scala, 10) {

    val fullName =
      if (firstName == lastName) firstName
      else firstName + lastName // expression, part of default constructor

    println("And " + fullName + " was born...") // part of default constructor, too

    def this(nickname: String, age: Int) = { // auxiliary constructor
      this(nickname, nickname, age) // it has to call the default one as a 1st statement
    }

    def this(nickname: String) = { // another auxiliary constructor
      this(nickname, -1) // it is allowed to call another auxiliary constructor as a 1st statement
    }
  }

  /**
   * Can you change the above mentioned class and fill the ??? in asserts to make test pass?
   */
  test("using default constructor and accessors/muttators")({
    val odersky = new FamousScalaCoder("Odersky", 55)
    odersky.age += 1

    assert(odersky.age === ???)
    assert(odersky.experience === ???)
  })

  /**
   * We need a class ScalaCoder where you can pass the years of experience as a parameter, can you take care of that?
   */
  test("extending a class passing arguments to the superclass' default constructor ") {

    //create a class where you can pass 
    val you: Coder = ???
    assert(you.lang === Scala)
  }

  /*
   * PATTERN MATCHING
   */

  /**
   * Pattern matching
   *
   * Pattern matching is like the 'switch-case' construct but much more powerful. It's a common feature of functional languages
   * and Scala has a very neat version of it
   *
   */

  def enjoysWork(coder: Coder) = coder.lang match {
    case Scala => "Sure!"
    case Java => "Less and less"
    case l: LispDialect => "Work? what work?"
    case _ => "not sure"
  }

  test("identify the output of a match") {
    case class OS(name: String, version: Int)

    def rate(os: OS) = os match {
      case OS("Android", 4) => 7
      case OS("Android", v) if v <= 4 => 6
      case OS("Firefox OS" | "Mint", _) => 5
      case OS("OS X", 10 | 9) => 4
      case OS("Windows", 8) | OS("Windows ME", _) => 3
      case _ => 0
    }

    assert(7 == rate(???))
    assert(rate(OS("Windows", 8)) === ???)
    assert(rate(OS("OS X", 9)) === ???)
    assert(rate(OS("OS X", 11)) === ???)
    assert(rate(OS("Mint", 13)) === ???)
    assert(rate(OS("Mint", 14)) === ???)
    assert(6 == rate(???))

  }

  test("write a matcher to get this results") {

    def matcher(l: List[Int]) = l match {

      case List(a, b, c) => 1

      case _ => ???
    }

    assert(matcher(List()) == 0)
    assert(matcher(List(0, 0, 0)) == 1)
    assert(matcher(List(1, 1, 1)) == 1)
    assert(matcher(List(1, 2, 3, 4)) == 2)
    assert(matcher(List(1, 2, 4, 4, 5, 6)) == 3)
    assert(matcher(List(3, 3, 3)) == 1)
    assert(matcher(List(3, 3, 3, 4)) == 2)
  }

}
