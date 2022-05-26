package solver

import grid.IPosition

enum class ActionType { OPEN, FLAG }

data class CellAction(val type: ActionType, val position: IPosition){
    override fun toString() = "$type $position"
}