package solver

import grid.*

typealias CellRule = Grid.(GridCell) -> Set<CellAction>

val cellRules = listOf<CellRule>(Grid::cellRule1, Grid::cellRule2)

fun Grid.solve(): MutableSet<CellAction> {
    val actions = mutableSetOf<CellAction>()
    var prevCount = -1
    while (prevCount != actions.count()) {
        prevCount = actions.count()

        for (cell in this) {
            if (cell.state.isHint && cell.state.hintNumber != 0) {
                cellRules.map { it(this, cell) }.forEach { actions += it }
            }
        }
    }

    if (numFlagged == 10)
        unknownCells.forEach { actions.add(CellAction(ActionType.OPEN, it.position)) }

    return actions
}

fun Grid.cellRule1(cell: GridCell) = mutableSetOf<CellAction>().also { actions ->
    if (cell.flaggedNeighbours.count() == cell.state.hintNumber)
        cell.unknownNeighbours.forEach { actions.add(open(it.position)) }
}

fun Grid.cellRule2(cell: GridCell) = mutableSetOf<CellAction>().also { actions ->
    if (cell.flaggedNeighbours.count() + cell.unknownNeighbours.count() == cell.state.hintNumber)
        cell.unknownNeighbours.forEach { actions.add(flag(it.position)) }
}