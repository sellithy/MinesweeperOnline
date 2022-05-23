package solver

import grid.Position

class ActionQueue private constructor(private val actions: MutableSet<CellAction>) :
    MutableSet<CellAction> by actions {

    constructor(): this(mutableSetOf())

    fun open(position: Position) = actions.add(CellAction(ActionType.OPEN, position))
    fun flag(position: Position) = actions.add(CellAction(ActionType.FLAG, position))
}