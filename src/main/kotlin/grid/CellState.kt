package grid

sealed interface ICellState {
    val isHint: Boolean
    val hintNumber: Int
}

enum class CellState : ICellState {
    UNKNOWN, FLAG, ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT;

    override val isHint
        get() = when (this) {
            ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT -> true
            else -> false
        }

    override val hintNumber
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
            UNKNOWN, FLAG -> throw IllegalArgumentException()
        }
}


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