package grid

class Grid private constructor(
    private val cells: MutableList<GridCell>,
    val width: Int,
    val height: Int,
    val numMines: Int
) : MutableList<GridCell> by cells {
    constructor(width: Int, height: Int, numMines: Int) : this(
        MutableList(height * width) { index ->
            GridCell(CellState.UNKNOWN, (index / width) pos (index % width))
        }, width, height, numMines
    )

    val IPosition.asIndex get() = row * width + column
    val IPosition.isWithinBounds get() = row in 0 until width && column in 0 until height

    val Int.asPosition get() = (this / width) pos (this % width)

    val GridCell.neighbours
        get() = position.neighbours
            .filter { it.isWithinBounds }
            .map { get(it) }

    val GridCell.unknownNeighbours get() = neighbours.filter { it.state == CellState.UNKNOWN }
    val GridCell.flaggedNeighbours get() = neighbours.filter { it.state == CellState.FLAG }

    val isSolved get() = all { it.state != CellState.UNKNOWN }
    val isNotSolved get() = !isSolved

    operator fun get(position: IPosition) = this[position.asIndex]
    operator fun set(position: IPosition, state: CellState) = cells.set(position.asIndex, GridCell(state, position))

    val unknownCells = filter { it.state == CellState.UNKNOWN }
    val unknownPositions = unknownCells.map { it.position }

    val numFlagged = count { it.state == CellState.FLAG }

    // Cell positions
    val GridCell.topNeighbour get() = this@Grid[position.top]
    val GridCell.topRightNeighbour get() = this@Grid[position.topRight]
    val GridCell.topLeftNeighbour get() = this@Grid[position.topLeft]

    val GridCell.rightNeighbour get() = this@Grid[position.right]
    val GridCell.leftNeighbour get() = this@Grid[position.left]

    val GridCell.bottomNeighbour get() = this@Grid[position.bottom]
    val GridCell.bottomRightNeighbour get() = this@Grid[position.bottomRight]
    val GridCell.bottomLeftNeighbour get() = this@Grid[position.bottomLeft]

}


