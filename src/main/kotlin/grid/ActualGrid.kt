package grid

@Suppress("Unused")
open class ActualGrid private constructor(
    cells: MutableMap<Position, RealCell>,
) : AbstractGrid<RealCell>(cells) {

    // region Constructors
    constructor(width: Int, height: Int) : this(
        mutableMapOf<Position, RealCell>().apply {
            for (row in 0 until height) for (col in 0 until width)
                (row pos col).let { this[it] = RealCell.UNKNOWN }
        }
    )
    //endregion

    // region Properties
    val isSolved get() = all { (_, state) -> state != RealCell.UNKNOWN }
    val isNotSolved get() = !isSolved
    val numFlagged = count { (_, state) -> state == RealCell.FLAGGED }


    val unknownCells = filter { (_, state) -> state == RealCell.UNKNOWN }
    val unknownPositions = unknownCells.map { (position, _) -> position }
    // endregion
}