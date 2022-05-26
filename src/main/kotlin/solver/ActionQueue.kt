package solver

import grid.IPosition

class ActionQueue private constructor(private val actions: MutableSet<CellAction>) :
    Set<CellAction> by actions {

    constructor(): this(mutableSetOf())

    fun open(position: IPosition) = actions.add(CellAction(ActionType.OPEN, position))
    fun flag(position: IPosition) = actions.add(CellAction(ActionType.FLAG, position))
}

context(ActionQueue)
fun Iterable<IPosition>.openAll() = forEach(::open)

context(ActionQueue)
fun Iterable<IPosition>.flagAll() = forEach(::flag)