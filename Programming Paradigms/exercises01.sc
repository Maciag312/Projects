import java.util
import scala.language.postfixOps

def SumList(list: List[Int]) : Int = {
  if(list.isEmpty)
    0
  else
    return list.last + SumList(list.dropRight(1))
}
object LowIntSum {
  def sumTwoSmallest(numbers: List[Int]): Int = {
    val numb = numbers.sorted
    numb(0)+numb(1)
  }
}
var Listt = List(1, 2, 5, 3, 4)
LowIntSum.sumTwoSmallest(Listt)


def connectStrings(listOfString: List[String], seperator:String):String = {
  if(listOfString.isEmpty)
    ""
  else if(listOfString.size==1){
    connectStrings(listOfString.dropRight(1), seperator) + listOfString.last;
  }
  else
    (connectStrings(listOfString.dropRight(1), seperator)) + seperator + listOfString.last

}
val strlist = List("13", "a", "d", "4")
connectStrings(strlist, " ")

def countNumb(listOfInt: List[Int], specific: Int): Int = {
  var count = 0
  if(listOfInt.isEmpty)
    0
  else{
    if(listOfInt.last==specific)
      count = count+1
    count = count + countNumb(listOfInt.dropRight(1), specific)
  }
  count

}
countNumb(List(1,2,3,4,2,3),8)


def nthFibonacci(nth: Int): Int = nth match {
  case 0 => 0
  case 1 => 1
  case n => nthFibonacci(n-1)+nthFibonacci(n-2)
}
nthFibonacci(3)


def factorial(nth: Int): Int = {
    if(nth==1)
      1
    else
      nth*factorial(nth-1)
}


def factorialTailRecursion(n:Int) :Int={
  def innerFunc(x:Int, acc:Int): Int ={
    if(x==1) acc
    else innerFunc(x-1,acc*x)
  }
  innerFunc(n,1)
}
factorial(5)
factorialTailRecursion(5)
/// algorithm 0,1,1,2,3,
/// first + second = third
// third + forth = fifth


def greaterThan(List: List[Int], limit: Int): List[Int] ={
  List.filter(_ > limit)
}
greaterThan(List(1,2,3,5),2)

def concatTwoList(list1: List[Int], list2: List[Int]): List[Int] ={
  if(list1.isEmpty && list2.isEmpty) List()
  else if(list1.isEmpty) list2
  else if(list2.isEmpty) list1
  else List(list1.head,list2.head) ::: concatTwoList(list1.tail,list2.tail)
}

concatTwoList(List(1,3,5), List(2,4,6))


def concat(list: List[Int], element: Int) : List[Int] = {
  list :+ element
}

def retoddandPos(list: List[Int]): (List[Int], List[Int]) = {
  if(list.isEmpty){
    (List(), List())
  }else{
    val head = list.head

    if(list.head>0&&list.head%2==1)
      (retoddandPos(concat(list.tail, list.head))_1, retoddandPos(concat(list.tail, list.head))_2)
    else if(head>0)
      (retoddandPos(list.tail)_1, retoddandPos(concat(list.tail, list.head))_2)
    else (head%2==1)
      (retoddandPos(concat(list.tail, list.head))_1, retoddandPos(list.tail)_2)

  }
}


retoddandPos(List(1,-2,4,-2,5,6,-2))


def seperateOddsAndEven(list: List[Int]): (List[Int], List[Int]) = {
  def accumulator(l: List[Int], lists: (List[Int], List[Int])) : (List[Int], List[Int]) = {
    if(l.isEmpty)
      lists
    else{
      val head = l.head
      if(head%2==0&&head>0){
        accumulator(l.tail, (lists._1 :+ head, lists._2:+ head))
      }
      else if(head%2==0)
        accumulator(l.tail, (lists._1, lists._2 :+ head))
      else if(head>0)
        accumulator(l.tail, (lists._1 :+ head, lists._2))

      else
        accumulator(l.tail, (lists._1, lists._2))

    }
  }
  accumulator(list,(List(), List()))
}

seperateOddsAndEven(List(1,3,2,5,1,3,5,6, -2, 3, -4))

def greaterThan(list: List[Int], x: Int) : List[Int] = {

  def accumulate(l: List[Int], res: List[Int]) : List[Int] = {
    if(l.isEmpty) {
      res
    }
    else {
      val head = l.head;
      if(head>x){
        accumulate(l.tail, res :+ head)
      }
      else{
        accumulate(l.tail, res)
      }
    }

  }
  accumulate(list, List())
}

greaterThan(List(2,3,4,15,51,1,1,2,4,5,1,61,71),20)