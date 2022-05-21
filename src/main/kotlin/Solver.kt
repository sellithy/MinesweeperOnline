fun Grid.solve() = mutableSetOf<CellAction>().apply {
    var prevCount = -1
    while (prevCount != count()) {
        prevCount = count()
        for (cell in this@solve) {
            if (cell.state.isHint && cell.state.hintNumber != 0) {
                if (cell.flaggedNeighbours.count() == cell.state.hintNumber)
                    cell.unknownNeighbours.forEach { add(CellAction(ActionType.OPEN, it.position)) }


                if (cell.flaggedNeighbours.count() + cell.unknownNeighbours.count() == cell.state.hintNumber)
                    cell.unknownNeighbours.forEach { this@solve[it.position] = CellState.FLAG }
            }
        }
    }

    if (this@solve.count { it.state == CellState.FLAG } == 10)
        this@solve.filter { it.state == CellState.UNKNOWN }
            .forEach { add(CellAction(ActionType.OPEN, it.position)) }
}