package org.stryukovsky.compilators
import java.util.Scanner

object Main {
  def main(args: Array[String]): Unit = {
    val scanner = new Scanner(System.in)
    while (true) {
      println("Provide string with math expression")
      val inputSource = scanner.nextLine()
      println(analyzer.Analyzer.analyze(inputSource))
    }
  }
}

// 10 + 15 - (13 + 4 - 45) * 534 / (59 - 12 + 45) * 14 + 13 * (12 - 3 + 5)