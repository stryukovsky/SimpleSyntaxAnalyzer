package org.stryukovsky.compilators
package analyzer

object Utils {
  private object Operation {
    final val add: (Double, Double) => Double = (x: Double, y: Double) => x + y
    final val sub: (Double, Double) => Double = (x: Double, y: Double) => x - y
    final val mul: (Double, Double) => Double = (x: Double, y: Double) => x * y
    final val div: (Double, Double) => Double = (x: Double, y: Double) => x / y
    final val pow: (Double, Double) => Double = (x: Double, y: Double) => Math.pow(x, y)
  }

  final val operatorAdd = '+'
  final val operatorSub = '-'
  final val operatorMul = '*'
  final val operatorDiv = '/'
  final val operatorPow = '^'
  final val operatorParenthesisOpen = '('
  final val operatorParenthesisClose = ')'

  final val operationsMapping: Map[Char, (Double, Double) => Double] = Map(
    operatorAdd -> Operation.add,
    operatorSub -> Operation.sub,
    operatorMul -> Operation.mul,
    operatorDiv -> Operation.div,
    operatorPow -> Operation.pow
  )

  final val priorityMapping = Map(
    operatorParenthesisOpen -> 1,
    operatorParenthesisClose -> 1,
    operatorAdd -> 2,
    operatorSub -> 2,
    operatorMul -> 3,
    operatorDiv -> 3,
    operatorPow -> 4,
  )

  val operators: Array[Char] = priorityMapping.keys.toArray

  def isOperator(char: Char) = operators.contains(char)

  val operations: Array[Char] = operationsMapping.keys.toArray

  def isOperation(char: Char) = operations.contains(char)
}
