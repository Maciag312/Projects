import scala.Boolean

def exponential(x: Int, n: Int) : Int = {
  if(n==0) 1
  else if(n%2==0) {
      exponential(x * x, n / 2)
  }
  else{
      x*exponential(x*x, (n-1)/2)
  }
}

exponential(2,4)

exponential(2,5)

exponential(3,4)

exponential(1,0)
// 2,5

def reverseRec[A](list: List[A]) : List[A] = {
  if(list.isEmpty) List()
  else
  list.last +: reverseRec(list.tail)
}

reverseRec(List(1,2,3,4))
reverseRec(List("Ala", "ma ", 1, "kota"))



def reverseTailRec[A](list: List[A]) : List[A] = {
  def accumulator(l: List[A], result: List[A]) : List[A] = {
    if(l.isEmpty)
      result
    else{
      accumulator(l.tail, l.head +: result)
    }
  }
  accumulator(list, List())
}
// 2,5
reverseTailRec(List("Ala", "ma ", 1, "kota"))
reverseTailRec(List(3,4,1,2))
reverseTailRec(List(0.2,0.3,1,2))
reverseRec(List())
reverseTailRec(List())


def concatTwoList[A](list1: List[A], list2: List[A]): List[A] ={
  if(list1.isEmpty && list2.isEmpty) List()
  else if(list1.isEmpty) list2
  else if(list2.isEmpty) list1
  else List(list1.head,list2.head) ::: concatTwoList(list1.tail,list2.tail)
}

concatTwoList(List(1,3,5), List(2,4,6))
concatTwoList(List(8,8,6), List(1,2))
concatTwoList(List(1,2), List(1,2,3,4,5,6,7))
concatTwoList(List(), List(1,2,3,4,5,6,7))
concatTwoList(List(), List())
concatTwoList(List(1,2), List())

//2
//def equal(d: Double, d2: Double, eps: Double): Boolean = {
//  def equal1(first: Double): Boolean ={
//    def equal2(second: Double): Boolean = {
//      Math.abs(first-second)<eps
//    }
//    equal2(d2)
//  }
//  equal1(d)
//}


def equal(eps: Double)(numbs:(Double,Double)): Boolean = {
  Math.abs(numbs._1-numbs._2) < eps
}


equal(0.1)(1, 0.99)

equal(0.1)(1, 0.89)

equal(0.1)(1, 0.99)

equal(0.1)(2, 1.91)
equal(0.1)(1.91, 2)

reverseTailRec(List(1,2,4))
