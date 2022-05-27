package solver

import grid.Position

class ActionQueue private constructor(private val actions: MutableSet<CellAction>) : Set<CellAction> by actions {

    constructor() : this(mutableSetOf())

    fun open(position: Position) = actions.add(CellAction(ActionType.OPEN, position))
    fun flag(position: Position) = actions.add(CellAction(ActionType.FLAG, position))
}

context(ActionQueue)
fun Iterable<Position>.openAll() = forEach(::open)

context(ActionQueue)
fun Iterable<Position>.flagAll() = forEach(::flag)