package experiment1

import cwinter.codecraft.core.api._

import scala.util.Random
import Mothership._
import cwinter.codecraft.util.maths.Vector2

object Mothership {
  val RND = new Random()
}

class Mothership extends DroneController {
  var nHarvesters = 0
  var nSoldiers = 0
  private var maxHarvesters = 4
  private var mothers: Seq[Mothership] = Seq(this)

  override def onTick(): Unit = {
    if (!isConstructing) {
      if (nHarvesters < maxHarvesters) {
        buildDrone(new Harvester(getRandomMother), storageModules = 1)
        nHarvesters += 1
      }
      else if (nSoldiers < 1) {
        buildDrone(new Soldier, missileBatteries = 3, shieldGenerators = 2)
        nSoldiers += 1
      }
      else {
        val newMother = new Constructor
        buildDrone(newMother, missileBatteries = 1, shieldGenerators = 1, constructors = 2, storageModules = 2)
        mothers :+= newMother
        maxHarvesters += 2
      }
    }
    if (!isMoving) {
      val randomDirection = Vector2(2 * math.Pi * Random.nextDouble())
      val targetPosition = position + 50 * randomDirection
      moveTo(targetPosition)
    }
  }

  private def getRandomMother: Mothership = {
    mothers(RND.nextInt(mothers.length))
  }
}

