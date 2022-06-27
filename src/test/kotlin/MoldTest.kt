import grid.*
import solver.ActionType
import solver.CellAction
import solver.solve
import kotlin.test.Test
import kotlin.test.assertEquals

class MoldTest {
    @Test
    fun `1-1 and normal flagging`() {
        val grid = ActualGrid(4, 4).apply {
            this[0, 0] = RealCell.UNKNOWN
            this[0, 1] = RealCell.UNKNOWN
            this[0, 2] = RealCell.UNKNOWN
            this[0, 3] = RealCell.UNKNOWN

            this[1, 0] = RealCell.ONE
            this[1, 1] = RealCell.ONE
            this[1, 2] = RealCell.UNKNOWN
            this[1, 3] = RealCell.UNKNOWN

            this[2, 0] = RealCell.TWO
            this[2, 1] = RealCell.ZERO
            this[2, 2] = RealCell.UNKNOWN
            this[2, 3] = RealCell.UNKNOWN

            this[3, 0] = RealCell.UNKNOWN
            this[3, 1] = RealCell.UNKNOWN
            this[3, 2] = RealCell.UNKNOWN
            this[3, 3] = RealCell.UNKNOWN
        }

        assertEquals(setOf(
            CellAction(ActionType.OPEN, 0 pos 2),
            CellAction(ActionType.OPEN, 1 pos 2),
            CellAction(ActionType.OPEN, 2 pos 2),
            CellAction(ActionType.FLAG, 3 pos 0),
            CellAction(ActionType.FLAG, 3 pos 1),
        ), grid.solve(1).toSet())
    }
}