package org.stryukovsky.compilators
package analyzer

import scala.collection.mutable

private object ParseRPN {
  def toRPN(source: String): mutable.Queue[Char] = {
    val stack = new mutable.Stack[Char]
    val result = new mutable.Queue[Char]
    var numberStarted = false
    source.foreach {
      case char if char.isDigit =>
        numberStarted = true
        result.addOne(char)
      case char if Utils.isOperator(char) =>
        if (numberStarted && Utils.isOperation(char)) {
          result.addOne(' ')
        }
        numberStarted = false
        if (stack.isEmpty) {
          stack.push(char)
        } else char match {
          case ch if ch == Utils.operatorParenthesisOpen => handleOpeningParenthesis(stack, char)
          case ch if ch == Utils.operatorParenthesisClose => handleClosingParenthesis(stack, result)
          case _ => handleMathOperation(stack, result, char)
        }
      case _ =>
    }
    while (stack.nonEmpty)
      result.addOne(stack.pop())
    result
  }

  private def handleOpeningParenthesis(stack: mutable.Stack[Char], char: Char): Unit = {
    stack.push(char)
  }

  private def handleMathOperation(stack: mutable.Stack[Char], result: mutable.Queue[Char], char: Char): Unit = {
    val currentPriority = Utils.priorityMapping(char)
    while (stack.nonEmpty && Utils.priorityMapping(stack.head) >= currentPriority)
      result.addOne(stack.pop())
    stack.push(char)
  }

  private def handleClosingParenthesis(stack: mutable.Stack[Char], result: mutable.Queue[Char]): Unit = {
    while (stack.nonEmpty && stack.head != Utils.operatorParenthesisOpen) {
      val operator = stack.pop()
      result.addOne(operator)
    }
    stack.pop()
  }
}
