package experiment1

import cwinter.codecraft.core.api._
import cwinter.codecraft.util.maths.Vector2

import scala.util.Random


class Harvester(mothership: Mothership) extends DroneController {
  override def onTick(): Unit = {
    if (!isMoving && !isHarvesting) {
      if (availableStorage == 0) moveTo(mothership)
      else {
        val randomDirection = Vector2(2 * math.Pi * Random.nextDouble())
        val targetPosition = position + 400 * randomDirection
        moveTo(targetPosition)
      }
    }
  }

  override def onMineralEntersVision(mineral: MineralCrystal) =
    if (availableStorage > 0) moveTo(mineral)

  override def onArrivesAtMineral(mineral: MineralCrystal) = harvest(mineral)

  override def onArrivesAtDrone(drone: Drone) = giveResourcesTo(drone)

  override def onDeath(): Unit = {
    mothership.nHarvesters -= 1
  }
}

