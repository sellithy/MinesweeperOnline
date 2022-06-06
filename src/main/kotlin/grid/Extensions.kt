package grid

val Int.asState
    get() = when (this) {
        0 -> RealCell.ZERO
        1 -> RealCell.ONE
        2 -> RealCell.TWO
        3 -> RealCell.THREE
        4 -> RealCell.FOUR
        5 -> RealCell.FIVE
        6 -> RealCell.SIX
        7 -> RealCell.SEVEN
        8 -> RealCell.EIGHT
        else -> throw IllegalArgumentException()
    }

val Map<Position, RealCell>.positions get() = keys

infix fun Int.pos(that: Int) = Position(this, that)