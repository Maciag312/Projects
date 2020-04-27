def sumAllNumbers(toNumber: Int): Int = {
  def rec(n: Int): Int = n match {
    case 0 => 0
    case _ => n+rec(n-1)
  }
  rec(toNumber)
}

val multiplication:(Int, Int)=>Int=(a,b) => a*b
val multiplicationDoubles:(Double, Double)=>Double=(a,b) => a*b
val multiplicationStrings:(String, String)=>String=(a,b) => a.concat(b)
val multiplicationStrings2:(String, String)=>String=(a,b) => a.concat(" new function ".concat(b))

def multiplyEveryElementOfList[A](list: List[A], multiplier: A, operationOfMultiplication: (A,A)=>A): List[A] = {
  def rec(old: List[A], newl: List[A]): List[A] = old match {
    case List() => print(newl); newl
    case _ => rec(old.tail, operationOfMultiplication(old.head,multiplier)::newl)
  }
  rec(list, List())
}

multiplyEveryElementOfList(List(3,4,2,2), 2, multiplication)
multiplyEveryElementOfList(List("one", "two", "three", "for"), " times two", multiplicationStrings2)
multiplyEveryElementOfList(List(3.3,4.4,2.5,2.3), 2.34, multiplicationDoubles)