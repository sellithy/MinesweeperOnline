package solver

import grid.Position

enum class ActionType { OPEN, FLAG }

data class CellAction(val type: ActionType, val position: Position){
    override fun toString() = "$type $position"
}

fun open(position: Position) = CellAction(ActionType.OPEN, position)
fun flag(position: Position) = CellAction(ActionType.FLAG, position)