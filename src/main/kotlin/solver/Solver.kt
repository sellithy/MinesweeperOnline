package solver

import grid.Grid
import grid.GridCell
import grid.hintNumber
import grid.isHint

// TODO Change to context(Grid, ActionQueue) when bug is fixed
typealias CellRule = Grid.(ActionQueue, GridCell) -> Unit

val rules = listOf<CellRule>(Grid::cellRule1, Grid::cellRule2)

fun Grid.solve(): MutableSet<CellAction> {
    val q = ActionQueue()
    var prevCount = -1
    while (prevCount != q.count()) {
        prevCount = q.count()

        for (cell in this) {
            if (cell.state.isHint && cell.state.hintNumber != 0) {
                rules.map { it(q, cell) }
            }
        }
    }

    if (numFlagged == 10)
        unknownCells.forEach { q.add(CellAction(ActionType.OPEN, it.position)) }

    return q
}

fun Grid.cellRule1(queue: ActionQueue, cell: GridCell) {
    if (cell.flaggedNeighbours.count() == cell.state.hintNumber)
        cell.unknownNeighbours.forEach { queue.open(it.position) }
}

fun Grid.cellRule2(queue: ActionQueue, cell: GridCell) {
    if (cell.flaggedNeighbours.count() + cell.unknownNeighbours.count() == cell.state.hintNumber)
        cell.unknownNeighbours.forEach { queue.flag(it.position) }
}
