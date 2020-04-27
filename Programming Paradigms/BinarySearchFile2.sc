


sealed trait Tree[+T]
case object Leaf extends Tree[Nothing]
case class Branch[T](value: T, left: Tree[T], right: Tree[T]) extends Tree[T]

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
def isBigger(a: Int, b: Int) = {a>b}
def contains[T](bst: BST[T], a: T): Boolean = {
  def iterate(tree: Tree[T]): Boolean = tree match {
    case Leaf => false
    case Branch(v,l,r) => {
      if(a==v) true
      else if(isBigger(a.asInstanceOf[Int],v.asInstanceOf[Int]))
        iterate(r)
      else
        iterate(l)
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
contains(tre3, 2)
contains(tre3, 8)
contains(tre3, 11)