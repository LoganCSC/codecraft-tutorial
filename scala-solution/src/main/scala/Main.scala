import cwinter.codecraft.core.api._


object Main {
  def main(args: Array[String]): Unit = {
    // Select runLevelN where N goes from 1 to 5 and gets progressively harder.
    TheGameMaster.runLevel2(new tutorial.Mothership)
  }
}

