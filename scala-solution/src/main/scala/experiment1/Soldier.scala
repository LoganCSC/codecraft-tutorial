package experiment1

import cwinter.codecraft.core.api._
import cwinter.codecraft.util.maths.Vector2

import scala.util.Random


class Soldier extends DroneController {

  override def onTick(): Unit = {
    val enemies = dronesInSight.filter(_.isEnemy)

    enemies.headOption match {
      case Some(enemy) =>
        moveTo(enemy.position)
        if (missileCooldown <= 0 && isInMissileRange(enemy))
          fireMissilesAt(enemy)
      case None =>
        if (!isMoving) {
          val randomDirection = Vector2(2 * math.Pi * Random.nextDouble())
          val targetPosition = position + 300 * randomDirection
          moveTo(targetPosition)
        }
    }
  }
}

