package solver

import grid.Position

class ActionQueue private constructor(private val actions: MutableSet<CellAction>) : Set<CellAction> by actions {

    constructor() : this(mutableSetOf())

    fun open(position: Position) = actions.add(CellAction(ActionType.OPEN, position))
    fun flag(position: Position) = actions.add(CellAction(ActionType.FLAG, position))

    override fun toString() = actions.toString()
}

context(ActionQueue)
fun Iterable<Position>.openAll() = forEach { it.open() }

context(ActionQueue)
fun Iterable<Position>.flagAll() = forEach { it.flag() }

context(ActionQueue)
fun Position.open() = this@ActionQueue.open(this)

context(ActionQueue)
fun Position.flag() = this@ActionQueue.flag(this)

