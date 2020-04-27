
//def combineStream[A](s1: Stream[A], s2:Stream[A], s3: Stream[A], fun: (Stream[A], A,A)=>(Stream[A])): Stream[Any] =  s1 match {
//  case Stream.Empty => s3#:::s2
//  case default =>{
//    if(s2.isEmpty)
//      s3#:::s1
//    else
//      combineStream(s1.tail, s2.tail, fun(s3, s1.head, s2.head), fun)
//  }
//}
//val IntCombiner:(Stream[Int], Int, Int)=>Stream[Int] =(l, a,b) => l#:::Stream(a,b)
////def combine[A](s1:Stream[A],s2:Stream[A])
//combineStream(Stream(1,2,3,4,5), Stream(20,30,40,50,60),  Stream(), IntCombiner).toList


//def combineStream[A](s1: Stream[A], s2:Stream[A], s3: Stream[A], fun: (A,A)=>A): Stream[Any] =  s1 match {
//  case Stream.Empty => s3#:::s2
//  case default =>{
//    combineStream(s1.tail, s2.tail, fun(s3, s1.head, s2.head), fun)
//  }
//}


def combineStream[A](s1: Stream[A], s2:Stream[A], s3: Stream[A], fun: (A,A)=>A): Stream[Any] =  s1 match {
  case Stream.Empty => s3#:::s2
  case default =>{
    if(s2.isEmpty)
      s3#:::s1
    else
      combineStream(s1.tail, s2.tail, s3#:::Stream(fun(s1.head,s2.head)), fun)
  }
}
val MultiplicationCombiner:(Int, Int)=>Int=(a,b) => a*b
val AditionCombiner:(Int, Int)=>Int=(a,b) => a+b



combineStream(Stream(1,2,3,4,5,12,13), Stream(20,30,40,50,60,9),  Stream(), MultiplicationCombiner).toList