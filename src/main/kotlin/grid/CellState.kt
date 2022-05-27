package grid

enum class CellState {
    UNKNOWN, FLAG, ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT;
}

val CellState.isHint
    get() = when (this) {
        CellState.ZERO, CellState.ONE, CellState.TWO, CellState.THREE, CellState.FOUR, CellState.FIVE, CellState.SIX, CellState.SEVEN, CellState.EIGHT -> true
        else -> false
    }

val CellState.hintNumber
    get() = when (this) {
        CellState.ZERO -> 0
        CellState.ONE -> 1
        CellState.TWO -> 2
        CellState.THREE -> 3
        CellState.FOUR -> 4
        CellState.FIVE -> 5
        CellState.SIX -> 6
        CellState.SEVEN -> 7
        CellState.EIGHT -> 8
        CellState.UNKNOWN, CellState.FLAG -> throw IllegalArgumentException()
    }

fun CellState.hintFollows(predicate: (Int) -> Boolean) = isHint && predicate(hintNumber)


val Int.asState
    get() = when (this) {
        0 -> CellState.ZERO
        1 -> CellState.ONE
        2 -> CellState.TWO
        3 -> CellState.THREE
        4 -> CellState.FOUR
        5 -> CellState.FIVE
        6 -> CellState.SIX
        7 -> CellState.SEVEN
        8 -> CellState.EIGHT
        else -> throw IllegalArgumentException()
    }