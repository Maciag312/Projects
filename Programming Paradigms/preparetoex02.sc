def calculatLength[A](list: List[A]): Int = {
  def accumulator(list: List[A], value: Int): Int = {
    if(list.isEmpty)
      value
    else
      accumulator(list.tail, value + 1)
  }
  accumulator(list, 0);
}


calculatLength(List(1,2,3,4,5,6))

def palindrome(str: String) : Boolean = {
  def accumulator(pos: Int, pos2: Int) : Boolean = {
    if(str.charAt(pos)!=str.charAt(pos2))
      false
    else if(pos==0) // finish
      true
    else// isnt finish
      accumulator(pos-1, pos2+1)
  }
  accumulator(str.length()/2-1,str.length()-(str.length()/2))
}

def palindromeList2[A](list: List[A]) : Boolean = {
  def accumulator(l: List[A]) : Boolean = {
    if(l.head!=l.last)
      false
    else if(l.size<=2)
      true
    else// isnt finish
      accumulator(l.drop(1).dropRight(1))
  }
  accumulator(list)
}


def reverse[A](l: List[A], result: List[A]): List[A]= {
  if(l.isEmpty) result
  else reverse(l.tail, l.head :: result)
}

def palindromeList[A](list: List[A]): Boolean = {
  def acc(l: List[A], lr: List[A]): Boolean = {
    if(l.isEmpty)
      true
    else if(l.head!=lr.head)
      false
    else
      acc(l.tail, lr.tail)
  }
  acc(list.dropRight(list.size/2-1), reverse(list, List()).dropRight(list.size/2-1))
}
def ReplaceBy[A](list: List[A], n: Int, x: A):List[A] = {
  if(n<0)
    list
  def accumulator(k: Int, lis: List[A], l: List[A]) : List[A] ={
    if(lis.isEmpty)
      l
    else if(k!=n)
      accumulator(k+1,lis.tail ,l :+ lis.head)
    else
      accumulator(k+1,lis.tail ,l :+ x)
  }
  accumulator(0, list, List())
}



ReplaceBy(List(0,1,2,3),2, "s")
palindromeList(List(1,0,1))
palindromeList(List(1,0,2))
palindromeList(List(1,0,0,1))
palindromeList(List("k", "a", "j", "a", "k"))
palindromeList(List("d", "a", "j", "a", "k"))
palindromeList(List("k", "d", "j", "a", "k"))

//Ex. 2
//Use curried representation and write system which convert pressure units.
//Input: pressure in atm (atmosphere)
//Outputs: PSI, bar, kg/cm2
//Ex. 4
//Define the replaceNth function, which replace the n-th element of the list
// by given value.
//replaceNth: [A](xs: List[A], n: Int, x: A)List[A]
//  Remember to do not use standard library functions here.
//  Ex. 6
//Write a function which check that numbers in provided list are correctly sorted.
// Please use tail recursion.

def getAtmPressureCurring(multiplier: Double)(pressure: Double): Double ={
  pressure*multiplier
}

val getBarPressure= getAtmPressureCurring(1.0132501) _
val getkgSqrCmIPressure= getAtmPressureCurring(1.0332275548) _
val getPSIPressure= getAtmPressureCurring(14.695948775) _

getPSIPressure(2)

def isSorted(ascending: Boolean,list: List[Int]): Boolean ={
  def acc(tailL: List[Int], head: Int): Boolean ={
    if (tailL.head<head&&ascending)
        false
    else if(tailL.head>head&&(!ascending))
        false
    else if(tailL.size==1)
      true
    else
      acc(tailL.tail, tailL.head)
  }
  acc(list.tail, list.head)
}

isSorted(true, List(1,2,3,3,8))
isSorted(true, List(1,2,5,3,8))
isSorted(true, List(1,2,3,4,5,-1))
isSorted(false, List(3,1,-1, -12))

def replaceNth[A](list: List[A], n: Int, x: A): List[A] ={
  def accu(l: List[A], result: List[A], kth: Int): List[A] ={
    if (kth == n)
      accu(l.tail, result :+ x, kth+1)
    else if(kth < n)
      accu(l.tail, result :+ l.head, kth+1)
    else result ::: l
  }
  accu(list,List(),0)
}

def combines[A](list: List[A])(list2: List[A]): Unit ={
  list:::list2
}

replaceNth(List(1,2,3,4), 2, 8)
