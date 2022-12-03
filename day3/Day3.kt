package day3

import java.io.File

fun day3Solution () {
    val filename = "day3/resources/d3_input.txt"
    val testFilename = "day3/resources/d3_input_test.txt"

    var rucksackPrioritiesSum = 0

    val elfGroups = ArrayList<String>()

    File(filename).forEachLine { it ->
        elfGroups.add(it)
        val rucksack1 = (it.substring(0 , it.length / 2)).toCharArray()
        val rucksack2 = (it.substring(it.length / 2, it.length)).toCharArray()

        val result = rucksack1.first {
            rucksack2.contains(it)
        }
        rucksackPrioritiesSum += codeTotal(result)
    }

    val setOfElves = ArrayList<CharArray>()
    var rucksackBadgesSum = 0

    for(string in elfGroups) {
        setOfElves.add(string.toCharArray())
        if(setOfElves.size % 3 == 0) {
            val result = setOfElves[0].first {
                setOfElves[1].contains(it) && setOfElves[2].contains(it)
            }
            rucksackBadgesSum += codeTotal(result)
            setOfElves.clear()
        }
    }

    println("~~~ Day 3 ~~~")
    println("Part 1 ~ Sum of priorities of the rucksacks: $rucksackPrioritiesSum")
    println("Part 2 ~ Sum of badges of the rucksacks: $rucksackBadgesSum")
    println("~~~~~~~~~~~~~")
}

fun codeTotal(result: Char): Int {
    val lowercaseCharPriorityCorrectionValue = 96
    val uppercaseCharPriorityCorrectionValue = 38

    return if (result.isUpperCase()) result.code - uppercaseCharPriorityCorrectionValue else result.code - lowercaseCharPriorityCorrectionValue
}
