import java.util.Optional

sealed trait Expr
case class Val(v: Double) extends Expr
case object Sum extends Expr
case object Diff extends Expr
case object Prod extends Expr
case object Div extends Expr

def evaluate(list: List[Expr]): Optional[Double] = {
  var fun: (Double, Expr)=>Double = (a,b: Val) => a+b.v
  var lastexpr: (Double, Double)=>Double = (a: Double, b: Double)=>a+b
  def ev(list: List[Expr]):Optional[Double] = {
    list.last match {
      case Diff =>  lastexpr = (a: Double, b: Double) => a-b
      case Sum => lastexpr = (a: Double, b: Double) => a+b
      case Prod => lastexpr = (a: Double, b: Double) => a*b
      case Div => lastexpr = (a: Double, b: Double) => a/b
      case Val(v) => las
    }
    ev(list.tail)
  }
}

//2
sealed trait Expr
case class Sum(a: Double, b: Double) extends Expr
case class Neg(a: Double) extends Expr

def evaluate(expr: Expr) : Double = expr match{
  case Sum(a,b) => a+b
  case Neg(a) => -a
}

evaluate(Sum(1.2, -5.4))
evaluate(Neg(-3))
evaluate(Neg(3))
//2
sealed trait Bool
case object True extends Bool
case object False extends Bool


def and(b: Bool, b2: Bool): Bool = {
  (b,b2) match {
    case (True, True) => True
    case (_,_) => False
  }
}
def or(b: Bool, b2: Bool): Bool = {
  (b,b2) match {
    case (False,False) => False
    case (_, _) => True
  }
}
def xor(b: Bool, b2: Bool): Bool = {
  (b,b2) match {
    case (True, True) => False
    case (False, False) => False
    case (_,_) => True
  }
}
def nor(b: Bool, b2: Bool): Bool = {
  (b,b2) match {
    case (False,False) => True
    case (_, _) => False
  }
}
def nand(b: Bool, b2: Bool): Bool = {
  (b,b2) match {
    case (True,True) => False
    case (_, _) => True
  }
}

or(True,False)
or(False,False)
or(True, True)
nand(False, True)
and(False, True)
and(True, True)
and(True, False)
and(False, False)
//5 not tested
def foldLeft[A, B, C](l: List[(A,B)], z: C)(f: (C, (A, B)) => C): C =
  l.isEmpty match {
    case true => z
    case false => foldLeft(l.tail, f(z, l.head))(f)
  }

//def foldRight[A,B,C](l:List[(A,B)], z: C)(f: ((A,B), C) => C): C= {
//  l.isEmpty match {
//    case true => z
//    case false => foldRight(l.tail, f(z, l.head))(f)
//  }
//}
//4
def findLuckyNumbers() : List[Int] = {
  def isLucky(value: Int) : Boolean = {
    def sumThisShit(n : Int, result : Int) : Int = {
      if(n == 0 ) result
      else sumThisShit(n/10, result + n%10)
    }
    val right = value%1000
    val left = value/1000 % 1000
    (sumThisShit(left, 0) == sumThisShit(right, 0))

  }
  val list = List.range(0, 999999)
  list.filter(x => isLucky(x))
}


def divide[A](s: Stream[A]): (Stream[A], Stream[A]) = {
  if(s.isEmpty) (Stream(), Stream())
  else if(s.size==1) (Stream(s.head), Stream())
  else{
  val div = divide(s.tail.tail)
  (s.head#::div._1, s.tail.head#::div._2)}
}

print(divide(Stream(1))._1.toList)
print(divide(Stream(1))._2.toList)
