package grid

abstract class AbstractGrid<T>(private val cells: MutableMap<Position, T>) :
    MutableMap<Position, T> by cells {

    // region Position extensions
    val Position.neighbouringCells get() = filterKeys(neighbours::contains)
    val Position.neighbouringUnknowns get() = neighbouringCells.filter { (_, state) -> state == RealCell.UNKNOWN }
    val Position.numNeighbouringUnknowns get() = neighbouringUnknowns.count()
    val Position.neighbouringFlags get() = neighbouringCells.filter { (_, state) -> state == RealCell.FLAGGED }
    val Position.numNeighbouringFlags get() = neighbouringFlags.count()

    val Position.topNeighbour get() = this@AbstractGrid[top]
    val Position.topRightNeighbour get() = this@AbstractGrid[topRight]
    val Position.topLeftNeighbour get() = this@AbstractGrid[topLeft]

    val Position.rightNeighbour get() = this@AbstractGrid[right]
    val Position.leftNeighbour get() = this@AbstractGrid[left]

    val Position.bottomNeighbour get() = this@AbstractGrid[bottom]
    val Position.bottomRightNeighbour get() = this@AbstractGrid[bottomRight]
    val Position.bottomLeftNeighbour get() = this@AbstractGrid[bottomLeft]
    // endregion

    // region Operators
    operator fun set(p: Position, state: T) =
        if (p in this) cells[p] = state else throw IllegalArgumentException("Position $p is not in the grid")
    //endregion
}

operator fun<T> MutableMap<Position, T>.set(row: Int, col: Int, state: T) = set(row pos col, state)
operator fun<T> MutableMap<Position, T>.get(row: Int, col: Int) = get(row pos col)