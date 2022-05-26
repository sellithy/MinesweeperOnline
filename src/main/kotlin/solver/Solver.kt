package solver

import grid.*

// TODO Change to context(Grid, ActionQueue) when bug is fixed
typealias CellRule = Grid.(ActionQueue, GridCell) -> Unit

val rules = listOf<CellRule>(Grid::openWhenDone, Grid::flagWhenDone, Grid::`1-1+`)

fun Grid.solve(): Set<CellAction> {
    val queue = ActionQueue()
    var prevCount = -1
    while (prevCount != queue.count()) {
        prevCount = queue.count()

        for (cell in this) {
            if (cell.hintFollows { it != 0 }) {
                rules.forEach { it(queue, cell) }
            }
        }
    }


    if (numFlagged == numMines)
        with(queue) { unknownCells.openAll() }

    return queue
}

fun Grid.openWhenDone(queue: ActionQueue, cell: GridCell) {
    if (cell.flaggedNeighbours.count() == cell.hintNumber)
        cell.unknownNeighbours.forEach(queue::open)
}

fun Grid.flagWhenDone(queue: ActionQueue, cell: GridCell) {
    if (cell.flaggedNeighbours.count() + cell.unknownNeighbours.count() == cell.hintNumber)
        cell.unknownNeighbours.forEach(queue::flag)
}
