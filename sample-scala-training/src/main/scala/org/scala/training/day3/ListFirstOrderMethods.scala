package org.scala.training.day3

object ListFirstOrderMethods {

  //Creating list
  val fruits: List[String] = List("apples", "oranges", "pears")
  val fruits2: List[String] = "apples" :: "oranges" :: "pears" :: Nil
  val fruits3 = Nil.::("pears").::("oranges").::("apples")
  
  //Head and tail
  fruits.head
  fruits.tail
  
  //Init and last
  fruits.last
  fruits.init
  
  //Reverse the list
  fruits.reverse
  
  //Apply. Not efficient
  fruits.apply(2)
  
  //Concatenation
  List(1, 2) ::: List(3, 4, 5)
  List(3, 4, 5).:::(List(1, 2))
  
  //Length of list
  List(1, 2, 3).length
  
  //Flatten
  List(List(1, 2), List(3), List(), List(4, 5)).flatten
  fruits.map(_.toCharArray).flatten
  
  //Converting lists: toArray, iterator
  fruits.toArray
  val it = fruits.iterator
  it.next
  it.next
  
}