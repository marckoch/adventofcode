package util.point

import util.direction.Direction

data class Position(val point: Point, val direction: Direction) {
    fun turnLeft() = this.copy(direction = this.direction.turnLeft())
    fun turnRight() = this.copy(direction = this.direction.turnRight())
    fun moveForward(steps: Int) = this.copy(point = this.point.move(this.direction, steps))
}
