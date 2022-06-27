package grid

import grid.RealCell.*
import grid.ImaginaryCell.*

sealed interface ICellState {
    companion object {
        val values = RealCell.values + ImaginaryCell.values
    }
}

sealed interface ImaginaryCell : ICellState {
    companion object {
        val values = listOf(OUTSIDE)
    }
    object OUTSIDE : ImaginaryCell
}

sealed interface RealCell : ImaginaryCell {
    companion object {
        val values = listOf(UNKNOWN, FLAGGED, ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT)
    }
    object UNKNOWN : RealCell
    object FLAGGED : RealCell
    object ZERO : RealCell
    object ONE : RealCell
    object TWO : RealCell
    object THREE : RealCell
    object FOUR : RealCell
    object FIVE : RealCell
    object SIX : RealCell
    object SEVEN : RealCell
    object EIGHT : RealCell
}

val ICellState.isHint
    get() = when (this) {
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT -> true
        UNKNOWN, FLAGGED, OUTSIDE -> false
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
        UNKNOWN, FLAGGED, OUTSIDE -> throw IllegalArgumentException()
    }

fun ICellState.hintFollows(predicate: (Int) -> Boolean) = isHint && predicate(hintNumber)