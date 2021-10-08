package delta.games.lotro.lore.items.legendary2.filters;

import delta.common.utils.misc.TypedProperties;
import delta.games.lotro.lore.items.ItemQuality;
import delta.games.lotro.lore.items.filters.ItemNameFilter;
import delta.games.lotro.lore.items.filters.ItemQualityFilter;

/**
 * I/O methods for the tracery chooser filter.
 * @author DAM
 */
public class TraceryFilterIo
{
  private static final String NAME_PATTERN="namePattern";
  private static final String TIER="tier";
  private static final String QUALITY="quality";

  /**
   * Load filter data from the given properties.
   * @param filter Filter to update.
   * @param props Properties to load from.
   */
  public static void loadFrom(TraceryFilter filter, TypedProperties props)
  {
    if (props==null)
    {
      return;
    }
    // Name
    ItemNameFilter nameFilter=filter.getNameFilter();
    if (nameFilter!=null)
    {
      String namePattern=props.getStringProperty(NAME_PATTERN,null);
      nameFilter.setPattern(namePattern);
    }
    // Tier
    TraceryTierFilter tierFilter=filter.getTierFilter();
    if (tierFilter!=null)
    {
      Integer tier=props.getIntegerProperty(TIER);
      tierFilter.setTier(tier);
    }
    // Quality
    ItemQualityFilter qualityFilter=filter.getQualityFilter();
    if (qualityFilter!=null)
    {
      String qualityKey=props.getStringProperty(QUALITY,null);
      ItemQuality quality=ItemQuality.fromCode(qualityKey);
      qualityFilter.setQuality(quality);
    }
  }

  /**
   * Save filter data to the given properties.
   * @param filter Source filter.
   * @param props Properties to update.
   */
  public static void saveTo(TraceryFilter filter, TypedProperties props)
  {
    // Name
    ItemNameFilter nameFilter=filter.getNameFilter();
    if (nameFilter!=null)
    {
      String namePattern=nameFilter.getPattern();
      props.setStringProperty(NAME_PATTERN,namePattern);
    }
    // Tier
    TraceryTierFilter tierFilter=filter.getTierFilter();
    if (tierFilter!=null)
    {
      Integer tier=tierFilter.getTier();
      if (tier!=null)
      {
        props.setIntProperty(TIER,tier.intValue());
      }
      else
      {
        props.removeProperty(TIER);
      }
    }
    // Quality
    ItemQualityFilter qualityFilter=filter.getQualityFilter();
    if (qualityFilter!=null)
    {
      ItemQuality quality=qualityFilter.getQuality();
      if (quality!=null)
      {
        props.setStringProperty(QUALITY,quality.getKey());
      }
      else
      {
        props.removeProperty(QUALITY);
      }
    }
  }
}
