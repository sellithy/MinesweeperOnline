package grid

class Grid private constructor(private val cells: MutableList<GridCell>, val width: Int, val height: Int) :
    MutableList<GridCell> by cells {
    constructor(width: Int, height: Int) : this(
        MutableList(height * width) { index ->
            GridCell(CellState.UNKNOWN, (index / width) pos (index % width))
        }, width, height
    )

    val Position.asIndex get() = row * width + column
    val Position.isWithinBounds get() = row in 0 until width && column in 0 until height

    val Int.asPosition get() = (this / width) pos (this % width)

    val GridCell.neighbours
        get() = position.neighbours
            .filter { it.isWithinBounds }
            .map { get(it) }

    val GridCell.unknownNeighbours get() = neighbours.filter { it.state == CellState.UNKNOWN }
    val GridCell.flaggedNeighbours get() = neighbours.filter { it.state == CellState.FLAG }

    val isSolved get() = all { it.state != CellState.UNKNOWN }
    val isNotSolved get() = !isSolved

    operator fun get(position: Position) = this[position.asIndex]
    operator fun set(position: Position, state: CellState) = cells.set(position.asIndex, GridCell(state, position))

    val unknownCells = filter { it.state == CellState.UNKNOWN }
    val unknownPositions = unknownCells.map { it.position }

    val numFlagged = count { it.state == CellState.FLAG }
}


