package grid

data class Position(val row: Int, val column: Int){
    override fun toString() = "($row, $column)"
}

val Position.top get() = Position(row - 1, column)
val Position.topRight get() = Position(row - 1, column + 1)
val Position.topLeft get() = Position(row - 1, column - 1)

val Position.right get() = Position(row, column + 1)
val Position.left get() = Position(row, column - 1)

val Position.bottom get() = Position(row + 1, column)
val Position.bottomRight get() = Position(row + 1, column + 1)
val Position.bottomLeft get() = Position(row + 1, column - 1)

val Position.neighbours get() = sequence {
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
