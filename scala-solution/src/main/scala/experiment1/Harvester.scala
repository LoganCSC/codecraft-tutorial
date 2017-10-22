package experiment1

import cwinter.codecraft.core.api._
import cwinter.codecraft.util.maths.Vector2
import Harvester._
import scala.util.Random

object Harvester {
  var KNOWN_MINERALS: Set[MineralCrystal] = Set()

}

class Harvester(mothership: Mothership) extends DroneController {

  override def onTick(): Unit = {
    if (!isMoving && !isHarvesting) {
      if (availableStorage == 0) moveTo(mothership)
      else {
        val candidateMinerals = KNOWN_MINERALS.filter(m => m.size > 0 && !m.harvested)
        if (candidateMinerals.isEmpty) {
          val randomDirection = Vector2(2 * math.Pi * Random.nextDouble())
          val targetPosition = position + 400 * randomDirection
          moveTo(targetPosition)
        } else {
          // instead of moving randomly go to known mineral
          val targetMineral = candidateMinerals.minBy(m => (m.position - position).lengthSquared)
          moveTo(targetMineral)
          KNOWN_MINERALS = candidateMinerals - targetMineral
          println("new num minerals = " + KNOWN_MINERALS.size)
        }
      }
    }
  }

  override def onMineralEntersVision(mineral: MineralCrystal) = {
    if (mineral.size > 0 && !mineral.harvested) {
      KNOWN_MINERALS += mineral
      if (availableStorage > 0) moveTo(mineral)
    }
  }

  override def onArrivesAtMineral(mineral: MineralCrystal) = harvest(mineral)

  override def onArrivesAtDrone(drone: Drone) = giveResourcesTo(drone)

  override def onDeath(): Unit = {
    mothership.nHarvesters -= 1
  }
}

