package day5

import java.io.File
import java.util.*
import kotlin.collections.ArrayList

fun day5Solution () {
    val filename = "day5/resources/d5_input.txt"
    val testFilename = "day5/resources/d5_input_test.txt"


    val lines = File(filename).readLines()

    val boxes = lines.filter { it.contains("[")}.map { "\\[\\D]|[ ]{2,4}|\\n+".toRegex().findAll(it) }.map { it -> it.map { it.value.replace(Regex("\\[|]"), "") }.toList() }
    val moves = lines.filter { it.contains("move") }.map { it -> "[0-9]+".toRegex().findAll(it).map { it.value }.toList()}

    val largestSize = getSizeOfLargestSubArray(boxes)
    val arrayOfStacksPart1 = ArrayList<ArrayDeque<String>>(largestSize)
    val arrayOfStacksPart2 = ArrayList<ArrayDeque<String>>(largestSize)


    for(int in 0 until largestSize) {
        arrayOfStacksPart1.add(ArrayDeque<String>())
        arrayOfStacksPart2.add(ArrayDeque<String>())
    }

    for(i in boxes.indices) {
        for (j in boxes[i].indices){
            if(!boxes[i][j].contains(" ")) {
                arrayOfStacksPart1[j].addLast(boxes[i][j])
                arrayOfStacksPart2[j].addLast(boxes[i][j])
            }
        }
    }
//    Part 1
    for(command in moves) {
        val numberOfBoxesToMove = command[0].toInt()
        val fromStack = command[1].toInt().minus(1)
        val toStack = command[2].toInt().minus(1)

        for(int in 0 until numberOfBoxesToMove) {
            arrayOfStacksPart1[toStack].addFirst(arrayOfStacksPart1[fromStack].peekFirst())
            arrayOfStacksPart1[fromStack].removeFirst()

        }
    }
//    Part 2
    for(command in moves) {
        val numberOfBoxesToMove = command[0].toInt()
        val fromStack = command[1].toInt().minus(1)
        val toStack = command[2].toInt().minus(1)
        val boxOrder = ArrayList<String>()

        for(int in 0 until numberOfBoxesToMove) {
            if(numberOfBoxesToMove <= 1){
                arrayOfStacksPart2[toStack].addFirst(arrayOfStacksPart2[fromStack].peekFirst())
                arrayOfStacksPart2[fromStack].removeFirst()
            } else {
                boxOrder.add(arrayOfStacksPart2[fromStack].peekFirst())
                arrayOfStacksPart2[fromStack].removeFirst()
                if(boxOrder.size == numberOfBoxesToMove) {
                    boxOrder.reverse()
                    for(box in boxOrder) {
                        arrayOfStacksPart2[toStack].addFirst(box)
                    }
                }
            }
        }
    }

    println("~~~ Day 5 ~~~")
    println("Part 1 ~ First box on top of each stack after single box movements: ${arrayOfStacksPart1.joinToString { it.peekFirst() }.replace(Regex(",|[ ]"), "")}")
    println("Part 2 ~ First box on top of each stack after multi box movements: ${arrayOfStacksPart2.joinToString { it.peekFirst() }.replace(Regex(",|[ ]"), "")}")
    println("~~~~~~~~~~~~~")
}

fun getSizeOfLargestSubArray(inputList: List<List<String>>): Int {
    var largestSubArray = 0
    for(list in inputList) {
        if(list.size > largestSubArray){
            largestSubArray = list.size
        }
    }
    return largestSubArray
}

