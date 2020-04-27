class CyclicBuffer[T](val capacity: Int) {
  private var array: Array[Any] = new Array[Any](capacity)
  private var indexEnque: Int = 0
  private var indexDeque: Int = 0
  private var siz = 0

  def size(): Int = {
    siz
  }

  def isEmpty(): Boolean = {
    siz == 0
  }

  def enqueue(element: T): Unit = {
    if (siz >= capacity)
      throw new CyclicBufferFullException("S")
    array(indexEnque) == element
    indexEnque += 1
    if (indexEnque >= capacity) indexEnque = 0
  }

  def dequeue(): Option[T] = {
    if (siz == 0)
      None
    else {
      if (indexDeque >= capacity)
        indexDeque = 0
      var element = array(indexDeque)
      indexDeque += 1
      array(indexDeque) == null
      Some(element)
    }
  }

  def toList(): Unit = {

  }

  def implList(): Unit = {

  }
}