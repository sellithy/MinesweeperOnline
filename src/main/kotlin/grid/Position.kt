package grid

sealed interface IPosition{
    val row: Int
    val column: Int
}

data class Position(override val row: Int, override val column: Int): IPosition{
    override fun toString() = "($row, $column)"
}

val IPosition.top get() = Position(row - 1, column)
val IPosition.topRight get() = Position(row - 1, column + 1)
val IPosition.topLeft get() = Position(row - 1, column - 1)

val IPosition.right get() = Position(row, column + 1)
val IPosition.left get() = Position(row, column - 1)

val IPosition.bottom get() = Position(row + 1, column)
val IPosition.bottomRight get() = Position(row + 1, column + 1)
val IPosition.bottomLeft get() = Position(row + 1, column - 1)

val IPosition.neighbours get() = sequence {
    yield(top)
    yield(topLeft)
    yield(topRight)

    yield(left)
    yield(right)

    yield(bottom)
    yield(bottomLeft)
    yield(bottomRight)
}

infix fun Int.pos(that: Int) = Position(this, that)
