import com.sun.source.tree.Tree


sealed trait Tree[T]
case class Leaf[T](value: T) extends Tree[T]
case class BranchOneChild[T, U](child: Tree[T],tran: T=>U) extends Tree[U]
case class BranchChildren[T, U, V](child_1: Tree[T], child_2: Tree[U],comb: (T,U)=>V) extends Tree[V]


def evaluate[T,U](tr: Tree[T]): T =  tr match {
  case Leaf(v) => v
  case BranchChildren(a,b,c: ((T,T)=>T)) => c(evaluate(a),evaluate(b))
  case BranchOneChild(ch,t: ((U)=>(T))) => t(evaluate(ch))
}
val AditionCombiner:(Double, Double)=> Double = (a,b) => a+b
val SubtrackingCombiner:(Double, Double)=> Double = (a,b) => a-b

val DoubleTransformer=(a: Int) => a.toDouble
val WithoutTransforem=(a: Double)=>(-a)
val leaf = Leaf(1)
val leaf2 = Leaf(2.4)
val branchOne = BranchOneChild(leaf,DoubleTransformer)
val branchChild2 = BranchOneChild(leaf2, WithoutTransforem)

val branchCh = BranchChildren(branchOne,branchChild2,AditionCombiner)
val branchCh2 = BranchChildren(branchChild2,branchChild2,SubtrackingCombiner)

evaluate(branchCh)
evaluate(branchCh2)
evaluate(Leaf(3.2))



def divide[A](s: Stream[A]): (Stream[A],Stream[A]) = {
  if(s.size==1)
    (Stream(s.head), Stream[A]())
  else if(s.isEmpty)
    (Stream[A](), Stream[A]())
  else {
    val tup = divide(s.tail.tail)
    (s.head #:: tup._1, s.tail.head #:: tup._2)
  }
}
println(divide(Stream(1,2,3,4,5))._1.toList, divide(Stream(1,2,3,4,5))._2.toList)
//

def combine4[A](s1: List[A], s2:List[A], s3: List[A], fun: (List[A], A,A)=>(List[A])): List[Any] =  {
  if(s1.isEmpty&&s2.isEmpty) s3
  else if(s2.isEmpty) s3:::s1
  else if(s1.isEmpty) s3:::s2
  else combine4(s1.tail, s2.tail, fun(s3, s1.head, s2.head), fun)
}
val function:(List[Int], Int, Int)=>List[Int] =(l, a,b) => l:::List(a,b)

combine4(List(1,2,3,4), List(20,30,40,50,60,70),  List(), function)

def combineStream[A](s1: Stream[A], s2:Stream[A], s3: Stream[A], fun: (Stream[A], A,A)=>(Stream[A])): Stream[Any] =  {
  println("s1"+s1+" s2"+s2)
  println("s3"+s3)
  if(s1.isEmpty&&s2.isEmpty) s3
  else if(s2.isEmpty) s3#::s1
  else if(s1.isEmpty) s3#::s2
  else combineStream(s1.tail, s2.tail, fun(s3, s1.head, s2.head), fun)
}
val functionStream:(Stream[Any], Any, Any)=>Stream[Any] =(l, a,b) => l#::Stream(a,b)


def combine[A](s1 : Stream[A], s2 : Stream[A], operation:(A, A) => A) :  Stream[A] = {
  (s1.isEmpty, s2.isEmpty) match{
    case (true, _) => s2
    case (_, true) => s1
    case (_, _) => operation(s1.head, s2.head) #:: combine(s1.tail, s2.tail, operation)
  }
}

combine((0 to 13).toStream, Stream(), (x : Int, y : Int) => x+y).toList
combine((0 to 13).toStream, Stream(2,3,5,6), (x : Int, y : Int) => x+y).toList
combine((-8 to 3).toStream, Stream(2,3,4,5,6), (x : Int, y : Int) => x+y).toList
combine((-8 to 3).toStream, Stream(2,3,4,5,6), (x : Int, y : Int) => x+y).toList
combine(Stream(), (9 to 19).toStream, (x : Int, y : Int) => x+y).toList
combine(Stream("a", "h"), Stream("e","c","d"), (x:String, y:String) => x+" con "+y).toList
combine((0 to 13).toStream, Stream(2,3,5,6), (x : Int, y : Int) => x-y).toList
combine((0 to 13).toStream, Stream(2,3,5,6), (x : Int, y : Int) => x*y).toList
combine(Stream.from(3), Stream.from(0), (x : Int, y : Int) => x*y).take(10).toList