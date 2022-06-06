import grid.*
import solver.solve

fun main() {
//    MinesweeperOnlineSolver(Resources.FirefoxDriverPath.path, true)
//        .runSolver()
    test()
}

fun test(){
    val grid = ActualGrid(4,4).apply {
        this[0, 0] = RealCell.UNKNOWN
        this[0, 1] = RealCell.UNKNOWN
        this[0, 2] = RealCell.UNKNOWN
        this[0, 3] = RealCell.UNKNOWN

        this[1, 0] = RealCell.ONE
        this[1, 1] = RealCell.ONE
        this[1, 2] = RealCell.UNKNOWN
        this[1, 3] = RealCell.UNKNOWN

        this[2, 0] = RealCell.ZERO
        this[2, 1] = RealCell.ZERO
        this[2, 2] = RealCell.UNKNOWN
        this[2, 3] = RealCell.UNKNOWN

        this[3, 0] = RealCell.UNKNOWN
        this[3, 1] = RealCell.UNKNOWN
        this[3, 2] = RealCell.UNKNOWN
        this[3, 3] = RealCell.UNKNOWN
    }

    println(grid.solve(1))
}