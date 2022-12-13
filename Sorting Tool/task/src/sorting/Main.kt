package sorting

import java.io.File
import java.util.*
import kotlin.system.exitProcess

val scanner = Scanner(System.`in`)

class SortingTool {
    fun sortData(sortingType: String, dataType: String, inputFile: String = "", outputFile: String = "") {
        val elements = mutableListOf<String>()

        val inputFromFile = inputFile != ""
        val outputToFile = outputFile != ""

        when (dataType) {
            "long" -> {
                if (inputFromFile) {
                    val file = File(inputFile)
                    val scanner = Scanner(file)
                    while (scanner.hasNext()) elements.add(scanner.next())
                    scanner.close()
                } else {
                    while (scanner.hasNext()) elements.add(scanner.next())
                }
                val invalids = elements.filter { it.toIntOrNull() == null }
                elements.removeAll(invalids)
                for (invalid in invalids) println("\"$invalid\" is not a long. It will be skipped.")
                elements.sortBy { it.toInt() }
            }

            "word" -> {
                if (inputFromFile) {
                    val file = File(inputFile)
                    val scanner = Scanner(file)
                    while (scanner.hasNext()) elements.add(scanner.next())
                    scanner.close()
                } else {
                    while (scanner.hasNext()) elements.add(scanner.next())
                }
                elements.sortBy { it }
            }

            "line" -> {
                if (inputFromFile) {
                    val file = File(inputFile)
                    val scanner = Scanner(file)
                    while (scanner.hasNext()) elements.add(scanner.nextLine())
                    scanner.close()
                } else {
                    while (scanner.hasNext()) elements.add(scanner.nextLine())
                }
                elements.sortBy { it }
            }
        }

        val output = when (sortingType) {
            "natural" -> {
                val elementType = if (dataType == "long") "numbers" else dataType + 's'
                "Total $elementType: ${elements.size}.\nSorted data:${
                    if (dataType == "line") elements.joinToString(
                        " ",
                        "\n"
                    ) else elements.joinToString(" ", " ")
                }"
            }

            "byCount" -> {
                val countedData = mutableMapOf<String, Int>()
                for (element in elements) {
                    val prevCount = countedData.putIfAbsent(element, 1)
                    if (prevCount != null) {
                        countedData[element] = prevCount + 1
                    }
                }
                val sortedData = countedData.toList().sortedBy { it.second }.toMap()
                val elementType = if (dataType == "long") "numbers" else dataType + 's'
                sortedData.map { "${it.key}: ${it.value} time(s), ${it.value / elements.size * 100}%" }
                    .joinToString("\n", "Total $elementType: ${elements.size}.\n")
            }

            else -> ""
        }

        if (outputToFile) {
            val file = File(outputFile)
            file.writeText(output)
        } else println(output)
    }
}

fun main(args: Array<String>) {
    var dataType = "word"
    var sortingType = "natural"
    var inputFile = ""
    var outputFile = ""

    for (i in args.indices)
        when (args[i]) {
            "-sortingType" -> {
                if (args.lastIndex == i || !(listOf("natural", "byCount").any { it == args[i + 1] })) {
                    println("No sorting type defined!")
                    exitProcess(1)
                }
                sortingType = args[i + 1]
            }

            "-dataType" -> {
                if (args.lastIndex == i || !(listOf("long", "word", "line").any { it == args[i + 1] })) {
                    println("No data type defined!")
                    exitProcess(1)
                }
                dataType = args[i + 1]
            }

            "-inputFile" -> inputFile = args[i + 1]

            "-outputFile" -> outputFile = args[i + 1]

            else -> if (args[i][0] == '-') println("\"-abc\" is not a valid parameter. It will be skipped.")
        }

    SortingTool().sortData(sortingType, dataType, inputFile, outputFile)
}
