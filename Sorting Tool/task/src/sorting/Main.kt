package sorting

import java.util.*

val scanner = Scanner(System.`in`)

fun main() {
    val numbers = mutableListOf<Int>()
    while (scanner.hasNext()) numbers.add(scanner.nextInt())
    println("Total numbers: ${numbers.size}.")
    val max = numbers.maxOrNull()!!
    println("The greatest number: $max (${numbers.filter { it == max }.size} time(s)).")
}
