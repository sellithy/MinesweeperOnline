package grid

import grid.ImaginaryCellState.*
import grid.RealCellState.*

sealed interface ICellState

enum class RealCellState: ICellState { UNKNOWN, FLAG, ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT }
enum class ImaginaryCellState: ICellState { OUTSIDE }

val ICellState.isHint
    get() = when (this) {
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT -> true
        UNKNOWN, FLAG, OUTSIDE -> false
    }

val ICellState.hintNumber
    get() = when (this) {
        ZERO -> 0
        ONE -> 1
        TWO -> 2
        THREE -> 3
        FOUR -> 4
        FIVE -> 5
        SIX -> 6
        SEVEN -> 7
        EIGHT -> 8
        UNKNOWN, FLAG, OUTSIDE -> throw IllegalArgumentException()
    }

fun ICellState.hintFollows(predicate: (Int) -> Boolean) = isHint && predicate(hintNumber)