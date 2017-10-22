package experiment1

import Constructor.RND
import cwinter.codecraft.util.maths.Vector2

import scala.util.Random


object Constructor {
  val RND = new Random()
}


class Constructor extends Mothership {

  override def onTick(): Unit = {
    if (!isConstructing) {

      val r = RND.nextDouble()
      if (r < 0.5) {
        buildDrone(new Harvester(this), storageModules = 2)
        nHarvesters += 1
      }
      else  {
        buildDrone(new Soldier, missileBatteries = 2, shieldGenerators = 1)
        nSoldiers += 1
      }

      if (!isMoving) {
        val randomDirection = Vector2(2 * math.Pi * Random.nextDouble())
        val targetPosition = position + 100 * randomDirection
        moveTo(targetPosition)
      }
    }
  }
}
