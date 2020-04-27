//sealed trait Tree[+T]
//case object Leaf extends Tree[Nothing]
//case class Branch[T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T]
sealed trait Expr[A]
case class Value[A](v: A)
case class Sum[A](v: Value[A], v_1: Value[A],f: (A,A)=>A)
case class Neg[A](v: Value[A],f: A=>A)

sealed trait Tree[+T]
case object Leaf extends Tree[Nothing]
case class Branch[T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T]



def add[A]()

type BST[T] = (Tree[T], (T,T) => Boolean)

def insert[T](a: T, bst: BST[T]): BST[T] = {
  val(tr, isLower) = bst
  def insertTree(tree: Tree[T]): Tree[T] = tree match {
    case Leaf => Branch(a, Leaf, Leaf)
    case Branch(v,l,r) => if(v==a) tree else if(isLower(a, v)) Branch(v, insertTree(l), r) else  Branch(v, l, insertTree(r))
  }
  (insertTree(tr), isLower)
}


def build[T](list: List[T],isLower: (T,T)=>Boolean): BST[T] = {
  list.foldLeft((Leaf: Tree[T], isLower))((tree:BST[T], element)=>insert(element, tree))
}

def contains[T](bst: BST[T], a: T): Boolean = {
  var found = false;
  def iterate(tree: Tree[T]): Unit = tree match {
    case Leaf =>
    case Branch(v,l,r) => {
      iterate(l)
      if(a==v) found=true;
      iterate(r)
    }
  }
  iterate(bst._1)
  found
}
def show[T](bst: BST[T]): Unit = {
  def iterate(tree: Tree[T]): Unit = tree match {
    case Leaf =>
    case Branch(v,l,r) => {
      iterate(l)
      println(v)
      iterate(r)
    }
  }
  iterate(bst._1)
}



val tre2 = build(List(1,2,3,8,4), (a: Int, b: Int) => a<b)
var tre3: BST[Int] = (Leaf, (a: Int, b: Int) => a<b)
tre3 = insert(1, tre3)
tre3 = insert(2, tre3)
tre3 = insert(2, tre3)

tre3 = insert(3, tre3)
tre3 = insert(8, tre3)
tre3 = insert(4, tre3)

show(tre3)

show(tre2)

contains(tre3, 2)
contains(tre3, 11)
contains(tre2, 9)
contains(tre2, 3)
//object BSTTree{
//  def isGreater(branch: Branch[Int], b: Int): Tree[Int] ={
//    if(b>=branch.value) branch.right else branch.left
//  }
//  def insert(a: Int, tree: Tree[Int]): Tree[Int] = tree match {
//    case Leaf => Branch(a, Leaf, Leaf)
//    case Branch(v,l,r) => if(a>v) Branch(v, l, insert(a, r)) else if(a<v) Branch(v, insert(a,l), r) else tree
//  }
//  def apply[A](): Tree[A] = Leaf
//
//
//  def show(tree: Tree[Int]): Unit = tree match {
//    case Leaf =>
//    case Branch(v, l, r) => {
//      print(v)
//      show(l)
//      show(r)
//
//    }
//  }
//  def contains(ele: Int, tree: Tree[Int]): Boolean = {
//    var isFound = false;
//    def helper(tr: Tree[Int]): Unit = tr match {
//      case Leaf =>
//      case Branch(v, l, r) => {
//        if (v == ele) {print(isFound)
//          if (!isFound) isFound = true
//          print(isFound)
//        }
//        helper(l)
//        helper(r)
//      }
//    }
//    helper(tree)
//    isFound
//  }
//}
//
//
//var tree2: Tree[Int] = BSTTree()
//tree2 = BSTTree.insert(1,tree2)
//tree2 = BSTTree.insert(2,tree2)
//tree2 = BSTTree.insert(2,tree2)
//tree2 = BSTTree.insert(8,tree2)
//tree2 = BSTTree.insert(7,tree2)
//BSTTree.contains(7, tree2)
//
//BSTTree.show(tree2)

//List(1,2,3,4).foldRight(Leaf)((x: Int, branch: Branch[Int]) => {
//  if (x >= branch.value)
//    else
//
//}


//val BST = (tree: Tree[Int]) =>  {
//  def isGreater(a: Int, b: Int): Boolean ={
//    a>b
//  }
//  def insert[Int](t: Tree[Int], ele: Int): Unit = t match {
//    case Leaf =>
//    case Branch(value, l, r) => {
//      if(isGreater(ele,value))
//    }
//  }
//}

//case class BST2(tree: Tree[Int], )
//def size[A](t: Tree[A]): Boolean = t match {
//  case Leaf(_) =>
//  case Branch(l, r) => 1 + size(l) + size(r)
//}


// whether first element is lower than second on
