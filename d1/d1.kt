package d1;

import java.io.File

fun main(args: Array<String>) {

    val filename = "d1/resources/d1_input.txt"
    val testFilename = "d1/resources/d1_input_test.txt"

    val calorieList = ArrayList<Int>()
    var calories = 0

    File(filename).forEachLine {
        if(it == "") {
            calorieList.add(calories)
            calories = 0
        } else {
            calories += it.toInt()
        }
    }
    calorieList.sortDescending()

    println("Most calories on one elf: " + calorieList.elementAt(0))
    println("Total calories on top three elves: " + (calorieList.elementAt(0) + calorieList.elementAt(1) + calorieList.elementAt(2)))
}