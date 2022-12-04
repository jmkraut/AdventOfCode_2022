package day4

import java.io.File

fun day4Solution () {
    val filename = "day4/resources/d4_input.txt"
    val testFilename = "day4/resources/d4_input_test.txt"

    var pairsTotalPart1 = 0
    var pairsTotalPart2 = 0

    File(filename).forEachLine {

        val assignmentPairs = it.replace(",", "-").split("-")

        val pair1Part1 = ArrayList<Int>()
        val pair2Part1 = ArrayList<Int>()

        pair1Part1.add(assignmentPairs[0].toInt())
        pair1Part1.add(assignmentPairs[1].toInt())

        pair2Part1.add(assignmentPairs[2].toInt())
        pair2Part1.add(assignmentPairs[3].toInt())

        if (pair1Part1[0] <= pair2Part1[0] && pair1Part1[1] >= pair2Part1[1] || (pair2Part1[0] <= pair1Part1[0] && pair2Part1[1] >= pair1Part1[1])) {
            pairsTotalPart1++
        } else if((pair1Part1[0] == pair1Part1[1] && (pair1Part1[0] <= pair2Part1[0] && pair1Part1[1] >= pair2Part1[1])) || (pair2Part1[0] == pair2Part1[1] && (pair2Part1[0] <= pair1Part1[0] && pair2Part1[1] >= pair1Part1[1]))) {
            pairsTotalPart1++
        }

        val pair1Part2 = intArrayOf(*(pair1Part1[0]..pair1Part1[1]).toList().toIntArray())
        val pair2Part2 = intArrayOf(*(pair2Part1[0]..pair2Part1[1]).toList().toIntArray())

        val result = pair1Part2.firstOrNull { number ->
            pair2Part2.contains(number)
        }

        if(result != null) {
            pairsTotalPart2++
        }
    }

    println("~~~ Day 4 ~~~")
    println("Part 1 ~ Number of fully overlapping pairs is: $pairsTotalPart1")
    println("Part 2 ~ Number of overlapping pairs is: $pairsTotalPart2")
    println("~~~~~~~~~~~~~")
}

