import scala.annotation.tailrec




def double(n: Int): Int = n + n

(n: Int) => n+n
val double2 = double _

def sumAndProduct(start: Int, end: Int): Either[Integer, Integer] = {
   Left (start+end)
   Right (start*end)
}


def sumAndProduc2t(start: Int, end: Int): (Int, Int) = {
  val TwoValues = (2,3) : (Int, Int)
  TwoValues
}


def isPrime2(z: Int, x: Int, y: Int): Boolean = {
  var yret = y
  if(x==1) {
    if (y > 1)
       return false
    else
       return true
  }
  if(z%x==0&&x!=0) {
    yret = y + 1
  }
  return isPrime2(z, x-1, yret)

}

def isPrime(z: Int): Boolean = {
  return isPrime2(z, z, 0)
}

def isPrime3(n: Int) : Boolean = {
  def ip(d: Int): Boolean  = {
    if(d==n) true
    else if(n%d == 0) false
    else ip(d+1)
  }
  if(n<2) false
  else ip(2)
}

def sumInterval(start: Int, end: Int): (Int, Int) = {
  if(start>=end) (0,1)
  else {
    val result = sumInterval(start+1, end);
    (result._1+start, result._2*start)
  }

}


double(3)
double2(2)
sumAndProduc2t(2,3)._1
sumAndProduc2t(2,3)._2
//sumAndProduct(2,3).left
//sumAndProduct(2,3).right
isPrime(12)
sumInterval(1,4)
