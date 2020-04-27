import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.util.Random


// used by 'time' method
//implicit val baseTime = System.currentTimeMillis
//
//// 2 - create a Future
//val f = Future {
//  Thread.sleep(500)
//  1 + 1
//}
//
//// 3 - this is blocking (blocking is bad)
//val result = Await.result(f, 1 second)
//println(result)
//Thread.sleep(1000)


println("starting calculation ...")
val f = Future {
  Thread.sleep(5000)
  42
}

println("before onComplete")
f.onComplete {
  case Success(value) => println(s"Got the callback, meaning = $value")
  case Failure(e) => e.printStackTrace
}
// do the rest of your work
println("A ..."); Thread.sleep(100)
println("B ..."); Thread.sleep(100)
println("C ..."); Thread.sleep(1000)
println("D ..."); Thread.sleep(1000)
println("E ..."); Thread.sleep(1000)
println("F ..."); Thread.sleep(1000)
Thread.sleep(2000)