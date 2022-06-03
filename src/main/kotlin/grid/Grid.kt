package grid

@Suppress("Unused")
open class Grid private constructor(
    private val cells: MutableMap<Position, RealCellState>
) : MutableMap<Position, RealCellState> by cells {

    // region Constructors
    constructor(width: Int, height: Int) : this(
        mutableMapOf<Position, RealCellState>().apply {
            for (row in 0 until height) for (col in 0 until width)
                (row pos col).let { this[it] = RealCellState.UNKNOWN }
        }
    )
    //endregion

    // region Properties
    val isSolved get() = all { (_, state) -> state != RealCellState.UNKNOWN }
    val isNotSolved get() = !isSolved
    val numFlagged = count { (_, state) -> state == RealCellState.FLAG }


    val unknownCells = filter { (_, state) -> state == RealCellState.UNKNOWN }
    val unknownPositions = unknownCells.map { (position, _) -> position }
    // endregion

    // region Operators
    operator fun set(p: Position, state: RealCellState) = if (p in this) cells[p] = state else null
    operator fun set(row: Int, col: Int, state: RealCellState) = set((row pos col), state)
    // endregion

    // region Position extensions
    val Position.neighbouringCells get() = filterKeys(neighbours::contains)
    val Position.neighbouringUnknowns get() = neighbouringCells.filter { (_, state) -> state == RealCellState.UNKNOWN }
    val Position.numNeighbouringUnknowns get() = neighbouringUnknowns.count()
    val Position.neighbouringFlags get() = neighbouringCells.filter { (_, state) -> state == RealCellState.FLAG }
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