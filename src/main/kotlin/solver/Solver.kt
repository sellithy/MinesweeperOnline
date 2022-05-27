package solver

import grid.*

// TODO Change to context(Grid, ActionQueue) when bug is fixed
// This should be a rule that is applied on a cell with a hint and that hint is not 0
typealias HintRule = Grid.(ActionQueue, Position, CellState) -> Unit

val rules = listOf<CellRule>(Grid::openWhenDone, Grid::flagWhenDone)

fun Grid.solve(): Set<CellAction> {
    val queue = ActionQueue()
    var prevCount = -1
    while (prevCount != queue.count()) {
        prevCount = queue.count()

        for ((position, state) in this) {
            if (state.hintFollows { it != 0 }) {
                rules.forEach { it(this, queue, position, state) }
            }
        }
    }

    if (numFlagged == numMines)
        with(queue) { unknownPositions.openAll() }

    return queue
}

fun Grid.openWhenDone(queue: ActionQueue, position: Position, state: CellState) {
    if (state.hintFollows(position.numNeighbouringFlags::equals))
        with(queue) { position.neighbouringUnknowns.positions.openAll() }
}

fun Grid.flagWhenDone(queue: ActionQueue, position: Position, state: CellState) {
    if (state.hintFollows((position.numNeighbouringFlags + position.numNeighbouringUnknowns)::equals))
        with(queue) { position.neighbouringUnknowns.positions.flagAll() }
}