package day2

import java.io.File

fun day2Solution () {
    val filename = "day2/resources/d2_input.txt"
    val testFilename = "day2/resources/d2_input_test.txt"

    var totalPoints = 0

    File(filename).forEachLine {
        val gameChars = it.toCharArray()
        totalPoints += (getOutcomeOfRound(gameChars[0], gameChars[2]).Points + StrategyMove.valueOf(gameChars[2].toString()).Play.Points)
    }
    println(totalPoints)
}

fun getOutcomeOfRound(player1Move: Char, player2Move: Char): GameOutcome {
    if(StrategyMove.valueOf(player1Move.toString()).Play == Moves.ROCK
        && StrategyMove.valueOf(player2Move.toString()).Play == Moves.SCISSORS ) {
        return GameOutcome.LOSE
    } else if (StrategyMove.valueOf(player1Move.toString()).Play == Moves.SCISSORS
        && StrategyMove.valueOf(player2Move.toString()).Play == Moves.PAPER ) {
        return GameOutcome.LOSE
    } else if (StrategyMove.valueOf(player1Move.toString()).Play == Moves.PAPER
        && StrategyMove.valueOf(player2Move.toString()).Play == Moves.ROCK ){
        return GameOutcome.LOSE
    } else if (StrategyMove.valueOf(player1Move.toString()).Play == StrategyMove.valueOf(player2Move.toString()).Play) {
        return GameOutcome.DRAW
    }

    return GameOutcome.WIN
}

enum class StrategyMove(val Play: Moves) {
    A(Moves.ROCK),
    B(Moves.PAPER),
    C(Moves.SCISSORS),
    X(Moves.ROCK),
    Y(Moves.PAPER),
    Z(Moves.SCISSORS),
}

enum class Moves(val Points: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3)
}

enum class GameOutcome(val Points: Int) {
    WIN(6),
    DRAW(3),
    LOSE(0)
}