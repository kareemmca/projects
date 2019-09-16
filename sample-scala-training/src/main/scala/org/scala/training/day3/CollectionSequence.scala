package org.scala.training.day3

object CollectionSequence {

  //********************Lists********************
  val numbers = List(1, 2, 3, 4, 5)

  //********************Arrays********************
  val fiveInts = new Array[Int](5)
  val fiveToOne = Array(5, 4, 3, 2, 1)

  //Get 5th element
  fiveToOne(4)

  //Assign value to 4th element
  fiveToOne(3) = 99

  //********************List Buffers********************
  import scala.collection.mutable.ListBuffer
  val buf = new ListBuffer[Int]

  //Append
  buf += 1
  buf += 2
  buf += 3

  //Prepend
  3 +=: buf //Syntactic sugar for buf.+=:(3)

  //Convert to list
  buf.toList

}