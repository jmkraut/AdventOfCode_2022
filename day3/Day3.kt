package day3

import java.io.File

fun day3Solution () {
    val filename = "day3/resources/d3_input.txt"
    val testFilename = "day3/resources/d3_input_test.txt"

    var rucksackPrioritiesSum = 0

    File(filename).forEachLine { it ->
        val rucksack1 = (it.substring(0 , it.length / 2)).toCharArray()
        val rucksack2 = (it.substring(it.length / 2, it.length)).toCharArray()

        val result = rucksack1.first {
            isCharValueInCharArray(it, rucksack2)
        }

        rucksackPrioritiesSum += getCharPriorityValue(result)
    }

    println("~~~ Day 3 ~~~")
    println("Part 1 ~ Sum of priorities of the rucksacks: $rucksackPrioritiesSum")
    println("~~~~~~~~~~~~~")
}

fun isCharValueInCharArray(characterToCheck: Char, arrayToCheck: CharArray): Boolean {
    for(char in arrayToCheck) {
        if(char == characterToCheck) {
            return true
        }
    }
    return false
}

fun getCharPriorityValue(char: Char): Int {
    val lowercaseCharPriorityCorrectionValue = 96
    val uppercaseCharPriorityCorrectionValue = 38

    if(char.isUpperCase()) {
        return char.code - uppercaseCharPriorityCorrectionValue
    }
    return char.code - lowercaseCharPriorityCorrectionValue
}