package day2

import java.io.File

fun day2Solution () {
    val filename = "day2/resources/d2_input.txt"
    val testFilename = "day2/resources/d2_input_test.txt"

    var totalPointsPart1 = 0
    var totalPointsPart2 = 0

    File(filename).forEachLine {
        val gameChars = it.toCharArray()
        val player1Move = StrategyMove.valueOf(gameChars[0].toString())
        val player2Move = StrategyMove.valueOf(gameChars[2].toString())

        totalPointsPart1 += getPointsForRoundPart1(player1Move, player2Move)
        totalPointsPart2 += getPointsForRoundPart2(player1Move, player2Move)
    }
    println("~~~ Day 2 ~~~")
    println("Part 1 ~ Total points for first game: $totalPointsPart1")
    println("Part 2 ~ Total points for first game: $totalPointsPart2")
    println("~~~~~~~~~~~~~")
}

fun getPointsForRoundPart1(player1Move: StrategyMove, player2Move: StrategyMove): Int {
    if(player1Move.play == Move.ROCK && player2Move.play == Move.PAPER) return GameOutcome.WIN.points + player2Move.play.points
    if(player1Move.play == Move.PAPER && player2Move.play == Move.SCISSORS) return GameOutcome.WIN.points + player2Move.play.points
    if(player1Move.play == Move.SCISSORS && player2Move.play == Move.ROCK) return GameOutcome.WIN.points + player2Move.play.points

    if(player1Move.play == Move.ROCK && player2Move.play == Move.SCISSORS) return GameOutcome.LOSE.points + player2Move.play.points
    if(player1Move.play == Move.PAPER && player2Move.play == Move.ROCK) return GameOutcome.LOSE.points + player2Move.play.points
    if(player1Move.play == Move.SCISSORS && player2Move.play == Move.PAPER) return GameOutcome.LOSE.points + player2Move.play.points

    return GameOutcome.DRAW.points + player2Move.play.points
}

fun getPointsForRoundPart2(player1Move: StrategyMove, player2Move: StrategyMove): Int {
    if(player2Move == StrategyMove.X) return GameOutcome.LOSE.points + player1Move.playBeats.points
    if(player2Move == StrategyMove.Z) return GameOutcome.WIN.points + player1Move.playLoses.points

    return GameOutcome.DRAW.points + player1Move.play.points
}

enum class StrategyMove(val play: Move, val playBeats: Move, val playLoses: Move) {
    A(Move.ROCK, Move.SCISSORS, Move.PAPER),
    B(Move.PAPER, Move.ROCK, Move.SCISSORS),
    C(Move.SCISSORS, Move.PAPER, Move.ROCK),
    X(Move.ROCK, Move.SCISSORS, Move.PAPER),
    Y(Move.PAPER, Move.ROCK, Move.SCISSORS),
    Z(Move.SCISSORS, Move.PAPER, Move.ROCK)
}

enum class Move(val points: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3)
}

enum class GameOutcome(val points: Int) {
    WIN(6),
    DRAW(3),
    LOSE(0)
}