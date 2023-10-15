package org.stryukovsky.compilators
package analyzer
object Analyzer {
  def analyze(expression: String): Double = {
    val rpn = ParseRPN.toRPN(expression)
    Calculator.calculate(rpn)
  }
}
