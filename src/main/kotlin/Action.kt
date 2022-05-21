
enum class ActionType { OPEN, FLAG }

data class CellAction(val type: ActionType, val position: Position){
    override fun toString() = "$type $position"
}