package grid

@Suppress("Unused")
class Grid private constructor(
    private val cells: MutableMap<Position, CellState>, val numMines: Int,
) : MutableMap<Position, CellState> by cells {

    // region Constructors
    constructor(width: Int, height: Int, numMines: Int) : this(
        mutableMapOf<Position, CellState>().apply {
            for (row in 0 until height) for (col in 0 until width)
                (row pos col).let { this[it] = CellState.UNKNOWN }
        }, numMines
    )
    //endregion

    // region Properties
    val isSolved get() = all { (_, state) -> state != CellState.UNKNOWN }
    val isNotSolved get() = !isSolved
    val numFlagged = count { (_, state) -> state == CellState.FLAG }


    val unknownCells = filter { (_, state) -> state == CellState.UNKNOWN }
    val unknownPositions = unknownCells.map { (position, _) -> position }
    // endregion

    // region Operators
    operator fun set(p: Position, state: CellState) = if (p in this) cells[p] = state else null
    // endregion

    // region Position extensions
    val Position.neighbouringCells get() = filterKeys(neighbours::contains)
    val Position.neighbouringUnknowns get() = neighbouringCells.filter { (_, state) -> state == CellState.UNKNOWN }
    val Position.numNeighbouringUnknowns get() = neighbouringUnknowns.count()
    val Position.neighbouringFlags get() = neighbouringCells.filter { (_, state) -> state == CellState.FLAG }
    val Position.numNeighbouringFlags get() = neighbouringFlags.count()

    val Position.topNeighbour get() = this@Grid[top]
    val Position.topRightNeighbour get() = this@Grid[topRight]
    val Position.topLeftNeighbour get() = this@Grid[topLeft]

    val Position.rightNeighbour get() = this@Grid[right]
    val Position.leftNeighbour get() = this@Grid[left]

    val Position.bottomNeighbour get() = this@Grid[bottom]
    val Position.bottomRightNeighbour get() = this@Grid[bottomRight]
    val Position.bottomLeftNeighbour get() = this@Grid[bottomLeft]
    // endregion
}

val Map<Position, CellState>.positions get() = keys