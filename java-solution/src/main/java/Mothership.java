import cwinter.codecraft.core.api.*;

class Mothership extends JDroneController {
  private static final DroneSpec HARVESTER_SPEC = new DroneSpec().withStorageModules(2);
  private static final DroneSpec SOLDIER_SPEC = new DroneSpec().withMissileBatteries(3).withShieldGenerators(1);
  int nHarvesters = 0;


  @Override public void onTick() {
    if (!isConstructing()) {
      if (nHarvesters < 3) {
        buildDrone(new Harvester(this), HARVESTER_SPEC);
        nHarvesters++;
      } else buildDrone(new Soldier(), SOLDIER_SPEC);
    }
  }
}

