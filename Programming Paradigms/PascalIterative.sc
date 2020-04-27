
def psacalaTri(nrow: Int): List[Int] = nrow match {
    case 0 => List(1)
    case n => 1 :: ((psacalaTri(n - 1) zip psacalaTri(n - 1).tail) map { case (a, b) => a + b }) ::: List(1)
}
psacalaTri(0)
psacalaTri(1)
psacalaTri(2)
psacalaTri(3)
psacalaTri(4)
psacalaTri(5)
psacalaTri(6)
psacalaTri(7)
psacalaTri(8)

def PascaIterative(nrow: Int): Array[Int] = {
  var counter = nrow
  var counter2 = 2
  val ar = new Array[Int](nrow + 1)
  if (nrow == 0) Array(1)
  else {
    ar(0) = 1
    ar(1) = 1
    var wiped = 1
    var holder = 1
    while (counter > 1) {
      counter -= 1;
      ar(counter2) = 1
      for (i <- 0 to counter2 - 2) {
        holder = ar(i + 1)
        ar(i + 1) = wiped + ar(i + 1)
        wiped = holder
      }
      counter2 += 1
    }
    ar
  }
}
PascaIterative(0)
PascaIterative(1)
PascaIterative(2)
PascaIterative(3)
PascaIterative(4)



def repeter[A](el: A, collector: LazyList[A], nth: Int): LazyList[A] = {
  nth match {
    case 0 => collector
    case n => repeter(el, el#::collector, nth-1)
  }
}





def lrepeat[A](k: Int)(lxs: LazyList[A]): LazyList[A] = {
  (k,lxs) match {
    case (_,LazyList()) => LazyList();
    case (0,_) => lxs
    case (n,_) if n < 0 => lrepeat(k+1)(lxs.head#::lxs)
    case (_,_) => lrepeat(k-2*k+1)(LazyList(lxs.head)) #::: lrepeat(k)(lxs.tail)
  }
}
lrepeat(5)(LazyList(1,2,3)).toList

//def lrepeat2[A](k: Int)(lxs: LazyList[A]): LazyList[A] = {
//  (k,lxs) match {
//    case (0,_) => lxs.flatMap(a=>a)
//    case (n,_) => ((lxs) zip lrepeat2(n-1)(lxs).flatMap(a=>LazyList(a._1, a._2)))
//  }
//}
//lrepeat2(3)(LazyList(LazyList(1), LazyList(2), LazyList(3))).toList
//def repeter2[A](k: Int)(lxs: LazyList[A]): LazyList[A] = {
//  k match {
//    case 0 => lxs
//    case n => repeter2(k-1)(lxs.head#::lxs)
//  }
//}
//def lrepeat[A](k: Int)(lxs: LazyList[A]): LazyList[A] = {
//  lxs match {
//    case LazyList() => LazyList()
//    case list => repeter2(k-1)(LazyList(lxs.head)) #::: lrepeat(k)(lxs.tail)
//  }
//}

lrepeat(5)(LazyList("3f",2,3)).toList

def fibonacciSeq(): LazyList[Int] = {
   LazyList(0,1) #::: fibonacciSeq().zip(fibonacciSeq().tail).map(a=>a._1+a._2)
}
fibonacciSeq().take(15).toList

//
//sealed trait lBT[+A]
//case object LEmpty extends lBT[Nothing]
//case class LNode[+A](elem:A, left: ()=>lBT[A], right:()=>lBT[A]) extends lBT[A]

//lazy val transforem = () =>  LNode
//
//def generatee[A](l: lBT[A]): lBT[Int] = l match {
//  case LEmpty =>
//  case LNode(v,l,r) =>{
//    lazy val left = l
//    lazy val right = r;
//    generatee(left.apply());
//    generatee(right.apply());
//  }
//
//}

//def hindusirepeat[A](k: Int)(list: LazyList[Int]): LazyList[A] = {
//  list.flatMap(list.fi)
//}
def duplicate(l: List[Int], t: List[Int]): List[Int] = {
  var duplicated: List[Int]  = List()
  var coutner = l.size;
  while(coutner>0){
    coutner-=1;
    var numb = l(l.size-coutner-1)
    var counter2 = t(t.size-coutner-1)
    while(counter2<t.size) {
      counter2-=1;
      duplicated = duplicated.appended(numb)
    }
  }
  duplicated
}

//duplicate(List(1,2,3), List(3))
//def gen(n: LNode[Int]): LNode[Int] = {
//  lazy val left = n.left;
//  lazy val right = n.right;
//  LNode(n, gen(), gen();
//}


sealed trait lBT[+A]
case object LEmpty extends lBT[Nothing]
case class LNode[+A](elem:A, left:()=>lBT[A], right:()=>lBT[A]) extends lBT[A]

def generate(n : Int) : lBT[Int] = {
  LNode(n, () => generate(2*n), () => generate(2*n+1))
}

val lazyTree = generate(5)
lazyTree match{
  case LEmpty => println("..")
  case LNode(a, b, c) => b.apply()
  case _ => println("..")
}



// gen(2) = 2
// gen(2).left.left = 8
//def generate[A](n: Int): lBT[Int] = {
//  lazy val g1 = generate(n*2)
//  lazy val g2 = generate(n*2)
//
//  LNode(n, ()=>g1, ()=>g2)
//}
//
//
//generate(5)