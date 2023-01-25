package delta.games.lotro.character.stats.base;

import delta.games.lotro.character.classes.ClassDescription;
import delta.games.lotro.character.classes.ClassesManager;
import delta.games.lotro.character.classes.WellKnownCharacterClassKeys;
import delta.games.lotro.character.races.RaceDescription;
import delta.games.lotro.character.races.RacesManager;
import delta.games.lotro.character.stats.BasicStatsSet;

/**
 * Test class for the base stats manager.
 * @author DAM
 */
public class TestBaseStatsManager
{
  /**
   * Main method for this test.
   * @param args Not used.
   */
  public static void main(String[] args)
  {
    BaseStatsManager mgr = new BaseStatsManager();
    RaceDescription elf=RacesManager.getInstance().getByKey("elf");
    ClassDescription warden=ClassesManager.getInstance().getCharacterClassByKey(WellKnownCharacterClassKeys.WARDEN);
    BasicStatsSet set = mgr.getBaseStats(warden, elf, 1);
    DerivedStatsContributionsMgr derivatedMgr = new DerivedStatsContributionsMgr();
    BasicStatsSet derivedStats = derivatedMgr.getContribution(warden, set);
    set.addStats(derivedStats);
    System.out.println(set);
    /*
    for(int i=1;i<=100;i++) {
      BasicStatsSet set = mgr.getBaseStats(CharacterClass.CHAMPION, Race.MAN, i);
      System.out.println("#"+i+": "+set);
    }
    */

    /*
    BaseStatsManager statsMgr=new BaseStatsManager();
    StarterStatsManager starterStatsMgr=new StarterStatsManager();
    DerivatedStatsContributionsMgr derivatedMgr=new DerivatedStatsContributionsMgr();
    for(Race race : Race.ALL_RACES)
    {
      for(CharacterClass cClass : CharacterClass.ALL_CLASSES)
      {
        BasicStatsSet statsSet=statsMgr.getBaseStats(cClass,race,1);
        BasicStatsSet contrib=derivatedMgr.getContribution(cClass,statsSet);
        statsSet.addStats(contrib);
        for(CharacterStat stat : statsSet.getAllStats())
        {
          starterStatsMgr.setStat(race,cClass,stat);
        }
      }
    }
    File to2=new File("starter2.xml");
    writer.write(to2,starterStatsMgr,EncodingNames.UTF_8);
    System.out.println("Wrote file " + to2.getAbsolutePath());
    */
  }
}
