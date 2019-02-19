package delta.games.lotro.lore.items.legendary;

import delta.games.lotro.character.stats.BasicStatsSet;
import delta.games.lotro.lore.items.ItemInstance;

/**
 * Legendary item instance.
 * @author DAM
 */
public class LegendaryItemInstance extends ItemInstance<LegendaryItem> implements LegendaryInstance
{
  // Legendary attributes.
  private LegendaryAttrs _attrs;

  /**
   * Constructor.
   */
  public LegendaryItemInstance()
  {
    _attrs=new LegendaryAttrs();
  }

  /**
   * Get the legendary attributes.
   * @return the legendary attributes.
   */
  public LegendaryAttrs getLegendaryAttributes()
  {
    return _attrs;
  }

  /**
   * Set the legendary attributes.
   * @param attrs Attributes to set.
   */
  public void setLegendaryAttributes(LegendaryAttrs attrs)
  {
    _attrs=attrs;
  }

  /**
   * Get the total stats for this item, including:
   * <ul>
   * <li>passives,
   * <li>title stats,
   * <li>relics stats.
   * </ul>
   * @return a set of stats.
   */
  public BasicStatsSet getTotalStats()
  {
    BasicStatsSet ret=new BasicStatsSet();
    Integer itemLevel=getItemLevel();
    int itemLevelValue=(itemLevel!=null)?itemLevel.intValue():0;
    BasicStatsSet legendaryStats=_attrs.getRawStats(itemLevelValue);
    ret.addStats(legendaryStats);
    return ret;
  }
}
