


trait Toy
class Car() extends Toy {
  override def toString: String = "It's a car"
}
class Truck extends Car {
  override def toString: String = "Its' a Truck"
}
class Doll extends Toy {
  override def toString: String = "It's a doll"
}


val car = new Car

trait Elf[+A] {
  def makeToy(): A
}

class ElfCarMaker(val colour: String) extends Elf[Car] {
  override def makeToy(): Car = {
    println(colour + " Car was made" )
    new Car
  }
}
class ElfTruckMaker(val colour: String) extends Elf[Truck] {
  override def makeToy(): Truck = {
    println(colour + " Truck was made")
    new Truck
  }
}
class ElfDollMaker(val colour: String) extends Elf[Doll] {
  override def makeToy(): Doll = {
    println(colour + " Doll was made")
    new Doll
  }
}
trait ToyList[+T]
case object Empty extends ToyList[Nothing]
case class MadeToy[A](item: A, rest: ToyList[A]) extends ToyList[A]

class Workshop[T]() {
  private var employee: Option[Elf[T]] = None
  private val list: List[T] = List()
  def employ(employee: Elf[T]) {
      this.employee = Some(employee)
    }
  def makeToys(quantity: Int): List[T] = {
      def recurs(list: List[T], coutner: Int): List[T] = {
        if (coutner == 0 || employee.isEmpty) list
        else recurs(employee.get.makeToy()::list, coutner - 1)
      }
      recurs(list, quantity)
    }
}
val dollworkshop = new Workshop[Doll]
dollworkshop.employ(new ElfDollMaker("blue"))
dollworkshop.makeToys(5)
dollworkshop.makeToys(3)