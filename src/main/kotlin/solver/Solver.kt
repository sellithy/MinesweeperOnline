package solver

import grid.*

// TODO Change to context(Grid, ActionQueue) when bug is fixed
// This should be a rule that is applied on a cell with a hint and that hint is not 0
typealias HintRule = ActualGrid.(ActionQueue, Position, RealCell) -> Unit

val nonZeroRules = listOf<HintRule>(ActualGrid::openWhenDone, ActualGrid::flagWhenDone)
val zeroRules = listOf<HintRule>(ActualGrid::`1-1+`)

fun ActualGrid.solve(numMines: Int): Set<CellAction> {
    val queue = ActionQueue()
    var prevCount = -1
    while (prevCount != queue.count()) {
        prevCount = queue.count()

        for ((position, state) in this) {
            if (state.hintFollows { it != 0 }) {
                nonZeroRules.forEach { it(this, queue, position, state) }
            } else {
                zeroRules.forEach { it(this, queue, position, state) }
            }
        }
    }

    if (numFlagged == numMines)
        with(queue) { unknownPositions.openAll() }

    return queue
}

fun ActualGrid.openWhenDone(queue: ActionQueue, position: Position, state: RealCell) {
    if (state.hintFollows(position.numNeighbouringFlags::equals))
        with(queue) { position.neighbouringUnknowns.positions.openAll() }
}

fun ActualGrid.flagWhenDone(queue: ActionQueue, position: Position, state: RealCell) {
    if (state.hintFollows((position.numNeighbouringFlags + position.numNeighbouringUnknowns)::equals))
        with(queue) { position.neighbouringUnknowns.positions.flagAll() }
}

val m = Mold {
    row {
//        +ImaginaryCell.OUTSIDE
        +RealCell.UNKNOWN
        +RealCell.UNKNOWN
    }
    row {
//        +ImaginaryCell.OUTSIDE
        +RealCell.ONE
        +RealCell.ONE
    }
    row {
//        +ImaginaryCell.OUTSIDE
        +RealCell.ZERO
        +RealCell.ZERO
    }
}

@Suppress("FunctionName")
fun ActualGrid.`1-1+`(queue: ActionQueue, position: Position, state: RealCell) {
    with(queue) {
        if (m.matches(this@ActualGrid, position)) {
            position.right.right.open()
            position.right.right.bottom.open()
            position.right.right.bottom.bottom.open()
        }
    }
}
