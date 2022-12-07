package sorting

import java.util.*

val scanner = Scanner(System.`in`)

class SortingTool {
    fun longSorting() {
        val numbers = mutableListOf<Int>()
        while (scanner.hasNext()) numbers.add(scanner.nextInt())
        println("Total numbers: ${numbers.size}.")
        val max = numbers.maxOrNull()!!
        println("The greatest number: $max (${numbers.filter { it == max }.size} time(s)).")
    }

    fun lineSorting() {
        val lines = mutableListOf<String>()
        while (scanner.hasNext()) lines.add(scanner.nextLine())
        println("Total lines: ${lines.size}.")
        val longestSize = lines.maxOf { it.length }
        val longest = lines.filter { it.length == longestSize }
        val repeats = lines.filter { it.length == longestSize }.size
        val percentage = (repeats.toDouble() / lines.size.toDouble() * 100).toInt()
        println("The longest line:\n${longest.joinToString("\n")}\n($repeats time(s), $percentage%).")
    }

    fun wordSorting() {
        val words = mutableListOf<String>()
        while (scanner.hasNext()) words.add(scanner.next())
        println("Total words: ${words.size}.")
        val longestSize = words.maxOf { it.length }
        val longest = words.filter { it.length == longestSize }
        val repeats = words.filter { it.length == longestSize }.size
        val percentage = (repeats.toDouble() / words.size.toDouble() * 100).toInt()
        println("The longest word: ${longest.joinToString(" ")} ($repeats time(s), $percentage%).")
    }
}

fun main(args: Array<String>) {
    if (args[0] == "-dataType") when (args[1]) {
        "long" -> SortingTool().longSorting()
        
        "line" -> SortingTool().lineSorting()

        "word" -> SortingTool().wordSorting()
    } else SortingTool().wordSorting()
}
