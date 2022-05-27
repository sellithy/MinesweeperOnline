package grid

val Int.asState
    get() = when (this) {
        0 -> RealCellState.ZERO
        1 -> RealCellState.ONE
        2 -> RealCellState.TWO
        3 -> RealCellState.THREE
        4 -> RealCellState.FOUR
        5 -> RealCellState.FIVE
        6 -> RealCellState.SIX
        7 -> RealCellState.SEVEN
        8 -> RealCellState.EIGHT
        else -> throw IllegalArgumentException()
    }

val Map<Position, RealCellState>.positions get() = keys

infix fun Int.pos(that: Int) = Position(this, that)