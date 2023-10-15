package org.stryukovsky.compilators
package analyzer

import scala.annotation.tailrec
import scala.collection.mutable

private object Calculator {
  def calculate(rpn: mutable.Queue[Char]): Double = {
    val stack = new mutable.Stack[Double]
    while (rpn.nonEmpty) {
      rpn.head match {
        case ch if ch.isDigit =>
          val number = buildNumber(rpn)
          stack.push(number)
        case ch if Utils.isOperator(ch) =>
          val result: Double = invokeMathOperation(rpn, stack)
          stack.push(result)
        case _ => rpn.dequeue()
      }
    }
    stack.pop()
  }

  private def invokeMathOperation(rpn: mutable.Queue[Char], stack: mutable.Stack[Double]): Double = {
    val operator = rpn.dequeue()
    val operands = (stack.pop(), stack.pop())
    val operation = Utils.operationsMapping(operator)
    operation(operands._2, operands._1)
  }

  @tailrec
  private def buildNumberRecursion(rpn: mutable.Queue[Char], accumulator: StringBuilder): Unit = {
    if (rpn.nonEmpty && rpn.head.isDigit) {
      accumulator.append(rpn.dequeue())
      buildNumberRecursion(rpn, accumulator)
    }
  }
  private def buildNumber(rpn: mutable.Queue[Char]): Double = {
    val resultContainer = new StringBuilder()
    buildNumberRecursion(rpn, resultContainer)
    resultContainer.toString().toDouble
  }

}
