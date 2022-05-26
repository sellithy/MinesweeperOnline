package grid

data class GridCell(val state: CellState, val position: IPosition) : ICellState by state, IPosition by position