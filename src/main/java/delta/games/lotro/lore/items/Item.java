package delta.games.lotro.lore.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import delta.common.utils.NumericTools;
import delta.common.utils.text.EndOfLine;
import delta.games.lotro.character.stats.BasicStatsSet;
import delta.games.lotro.common.CharacterClass;
import delta.games.lotro.common.Identifiable;
import delta.games.lotro.common.money.Money;
import delta.games.lotro.common.stats.StatsProvider;

/**
 * Item description.
 * @author DAM
 */
public class Item implements Identifiable
{
  // Item identifier
  private int _identifier;
  // Icon name: iconID-backgroundIconID
  private String _icon;
  // Items set identifier (may be null)
  private String _setKey;
  // Associated set (may be null)
  private ItemsSet _set;
  // Slot
  private EquipmentLocation _equipmentLocation;
  // Item name "Jacket of the Impossible Shot"
  private String _name;
  // Item category: Armour, Tool, ...
  private ItemCategory _category;
  // TODO Enum or String constants for sub-categories
  private String _subCategory;
  // Item binding: "Bind on Acquire", ...
  private ItemBinding _binding;
  // Is item unique or not?
  private boolean _unique;
  // Bonuses
  private List<String> _bonus;
  // Stats
  private BasicStatsSet _stats;
  private StatsProvider _statsProvider;
  // Essences
  private int _essenceSlots;
  // Durability
  private Integer _durability;
  // Sturdiness (may be null)
  private ItemSturdiness _sturdiness;
  // Item level (may be null)
  private Integer _itemLevel;

  // Requirements:
  // Minimum level (may be null)
  private Integer _minLevel;
  // Maximum level (may be null)
  private Integer _maxLevel;
  // Class (may be null)
  // TODO list of classes
  private CharacterClass _class;
  // TODO list of races
  // TODO list of factions
  // TODO <gloryRank/>
  // TODO <traits/>
  // Full description (may be empty but not <code>null</code>)
  private String _description;
  // Value
  private Money _value;
  // Stacking information
  private Integer _stackMax;

  // TODO Missing attrs: isItemAdvancement="0" consumedOnUse="0" cooldown="" decoration="" instrument=""
  private ItemQuality _quality;
  private HashMap<String,String> _properties;

  /**
   * Constructor.
   */
  public Item()
  {
    super();
    _identifier=0;
    _icon=null;
    _setKey=null;
    _equipmentLocation=null;
    _name="";
    _category=ItemCategory.ITEM;
    _subCategory=null;
    _binding=null;
    _unique=false;
    _bonus=new ArrayList<String>();
    _stats=new BasicStatsSet();
    _statsProvider=null;
    _essenceSlots=0;
    _durability=null;
    _sturdiness=null;
    _minLevel=null;
    _maxLevel=null;
    _itemLevel=null;
    _class=null;
    _description="";
    _value=new Money();
    _stackMax=null;
    _quality=null;
    _properties=new HashMap<String,String>();
  }

  /**
   * Copy constructor.
   * @param source Source.
   */
  public Item(Item source)
  {
    this();
    copyFrom(source);
  }

  /**
   * Get the identifier of this item.
   * @return an item identifier.
   */
  public int getIdentifier()
  {
    return _identifier;
  }

  /**
   * Set the identifier of this item.
   * @param identifier the identifier to set.
   */
  public void setIdentifier(int identifier)
  {
    _identifier=identifier;
  }

  /**
   * Get the icon for this item.
   * @return an icon name.
   */
  public String getIcon()
  {
    return _icon;
  }

  /**
   * Set the icon for this item.
   * @param icon Icon name to set.
   */
  public void setIcon(String icon)
  {
    _icon=icon;
  }

  /**
   * Get the icon ID for this item.
   * @return an icon ID.
   */
  public int getIconId()
  {
    int ret=0;
    if (_icon!=null)
    {
      ret=NumericTools.parseInt(_icon.substring(0,_icon.indexOf('-')),0);
    }
    return ret;
  }

  /**
   * Get the background icon ID for this item.
   * @return an icon ID.
   */
  public int getBackgroundIconId()
  {
    int ret=0;
    if (_icon!=null)
    {
      ret=NumericTools.parseInt(_icon.substring(_icon.indexOf('-')+1),0);
    }
    return ret;
  }

  /**
   * Set the key of the set this item belongs to.
   * @param setKey the set key to set (<code>null</code> if item belongs to no set).
   */
  public void setSetKey(String setKey)
  {
    _setKey=setKey;
  }

  /**
   * Get the identifier of the set this item belongs to.
   * @return a items set identifier or <code>null</code>.
   */
  public String getSetKey()
  {
    return _setKey;
  }

  /**
   * Get the associated items set.
   * @return an items set or <code>null</code>.
   */
  public ItemsSet getSet()
  {
    return _set;
  }

  /**
   * Set the associated items set.
   * @param set Items set.
   */
  public void setItemsSet(ItemsSet set)
  {
    _set=set;
  }

  /**
   * Get equipment location.
   * @return an equimment location. 
   */
  public EquipmentLocation getEquipmentLocation()
  {
    return _equipmentLocation; 
  }

  /**
   * Set the equipment location.
   * @param equipmentLocation Location to set.
   */
  public void setEquipmentLocation(EquipmentLocation equipmentLocation) {
    _equipmentLocation=equipmentLocation;
  }

  /**
   * Get the name of this item.
   * @return an item name.
   */
  public String getName()
  {
    return _name;
  }

  /**
   * Set the name of this item.
   * @param name the name to set.
   */
  public void setName(String name)
  {
    _name=name;
  }

  /**
   * Get the category of this item.
   * @return an item category.
   */
  public ItemCategory getCategory()
  {
    return _category;
  }

  /**
   * Set the category of this item.
   * @param category the category to set.
   */
  public void setCategory(ItemCategory category)
  {
    _category=category;
  }

  /**
   * Get the sub-category of this item.
   * @return a sub-category.
   */
  public String getSubCategory()
  {
    return _subCategory;
  }

  /**
   * Set the sub-category of this item.
   * @param subCategory the sub-category to set.
   */
  public void setSubCategory(String subCategory)
  {
    _subCategory=subCategory;
  }

  /**
   * Get the item binding.
   * @return an item binding.
   */
  public ItemBinding getBinding()
  {
    return _binding;
  }

  /**
   * Set the binding of this item.
   * @param binding the binding to set.
   */
  public void setBinding(ItemBinding binding)
  {
    _binding=binding;
  }

  /**
   * Indicates if this item is unique or not.
   * @return <code>true</code> if it is, <code>false</code> otherwise.
   */
  public boolean isUnique()
  {
    return _unique;
  }

  /**
   * Set the unicity of this item.
   * @param unique <code>true</code> to make this item unique, <code>false</code> otherwise.
   */
  public void setUnique(boolean unique)
  {
    _unique=unique;
  }

  /**
   * Add a bonus.
   * @param bonus Bonus to add.
   */
  public void addBonus(String bonus)
  {
    _bonus.add(bonus);
  }

  /**
   * Get the list of bonus for this item.
   * @return a list of bonus.
   */
  public List<String> getBonus()
  {
    // TODO encapsulation
    return _bonus;
  }

  /**
   * Set the list of bonus for this item.
   * @param bonuses the bonus to set.
   */
  public void setBonus(List<String> bonuses)
  {
    _bonus.clear();
    if (bonuses!=null)
    {
      _bonus.addAll(bonuses);
    }
  }

  /**
   * Get the item stats.
   * @return a set of stats.
   */
  public BasicStatsSet getStats()
  {
    return _stats;
  }

  /**
   * Get the stats provider.
   * @return the stats provider (may be <code>null</code>).
   */
  public StatsProvider getStatsProvider()
  {
    return _statsProvider;
  }

  /**
   * Set the stats provider.
   * @param statsProvider Provider to set.
   */
  public void setStatsProvider(StatsProvider statsProvider)
  {
    _statsProvider=statsProvider;
  }

  /**
   * Get the number of essence slots.
   * @return the number of essence slots.
   */
  public int getEssenceSlots()
  {
    return _essenceSlots;
  }

  /**
   * Set the number of available essence slots.
   * @param essenceSlots Slot count.
   */
  public void setEssenceSlots(int essenceSlots)
  {
    _essenceSlots=essenceSlots;
  }

  /**
   * Get the durability of this item.
   * @return a durability value.
   */
  public Integer getDurability()
  {
    return _durability;
  }

  /**
   * Set the durability of this item.
   * @param durability the durability to set.
   */
  public void setDurability(Integer durability)
  {
    _durability=durability;
  }

  /**
   * Get the sturdiness of this item.
   * @return a sturdiness value.
   */
  public ItemSturdiness getSturdiness()
  {
    return _sturdiness;
  }

  /**
   * Set the sturdiness of this item.
   * @param sturdiness the sturdiness to set.
   */
  public void setSturdiness(ItemSturdiness sturdiness)
  {
    _sturdiness=sturdiness;
  }

  /**
   * Get the minimum level required to use this item.
   * @return a minimum level value or <code>null</code>.
   */
  public Integer getMinLevel()
  {
    return _minLevel;
  }

  /**
   * Set the minimum level required to use this item.
   * @param minLevel the minimum level as an integer value,
   * or <code>null</code> for no restriction.
   */
  public void setMinLevel(Integer minLevel)
  {
    _minLevel=minLevel;
  }

  /**
   * Get the maximum level to use this item.
   * @return a maximum level value or <code>null</code>.
   */
  public Integer getMaxLevel()
  {
    return _maxLevel;
  }

  /**
   * Set the maximum level to use this item.
   * @param maxLevel the maximum level as an integer value,
   * or <code>null</code> for no restriction.
   */
  public void setMaxLevel(Integer maxLevel)
  {
    _maxLevel=maxLevel;
  }

  /**
   * Get the item level.
   * @return a level value or <code>null</code>.
   */
  public Integer getItemLevel()
  {
    return _itemLevel;
  }

  /**
   * Set the item level.
   * @param itemLevel the item level as an integer value, or <code>null</code>.
   */
  public void setItemLevel(Integer itemLevel)
  {
	  _itemLevel=itemLevel;
  }

  /**
   * Get the required class to use this item.
   * @return a character class or <code>null</code>.
   */
  public CharacterClass getRequiredClass()
  {
    return _class;
  }

  /**
   * Set the required class for this item.
   * @param toonClass a character class or <code>null</code> for no restriction.
   */
  public void setRequiredClass(CharacterClass toonClass)
  {
    _class=toonClass;
  }

  /**
   * Get description of this item.
   * @return an item description.
   */
  public String getDescription()
  {
    return _description;
  }

  /**
   * Set the description of this item.
   * @param description the description to set.
   */
  public void setDescription(String description)
  {
    if (description==null) description="";
    _description=description;
  }

  /**
   * Get the value of this item.
   * @return an amount of money.
   */
  public Money getValue()
  {
    return _value;
  }

  /**
   * Set the value of this item.
   * @param value the value to set.
   */
  public void setValue(Money value)
  {
    _value=value;
  }

  /**
   * Get the maximum stackability of this item.
   * @return an integer value or <code>null</code> if it is not stackable.
   */
  public Integer getStackMax()
  {
    return _stackMax;
  }

  /**
   * Set the maximum stackability of this item.
   * @param stackMax the maximum size of stacks as an integer,
   * or <code>null</code> if it is not stackable.
   */
  public void setStackMax(Integer stackMax)
  {
    _stackMax=stackMax;
  }

  /**
   * Get the quality of this item.
   * @return a quality value.
   */
  public ItemQuality getQuality()
  {
    return _quality;
  }

  /**
   * Set the quality of this item.
   * @param quality the quality to set.
   */
  public void setQuality(ItemQuality quality)
  {
    _quality=quality;
  }

  /**
   * Set a property for this item.
   * @param key Property key.
   * @param value Property value.
   */
  public void setProperty(String key, String value)
  {
    _properties.put(key,value);
  }

  /**
   * Remove a property.
   * @param key Property key.
   */
  public void removeProperty(String key)
  {
    _properties.remove(key);
  }

  /**
   * Get the value of a property.
   * @param key Property name.
   * @return A value or <code>null</code> if not set.
   */
  public String getProperty(String key)
  {
    return _properties.get(key);
  }

  /**
   * Get all properties for this item.
   * @return A properties map or <code>null</code>.
   */
  public Map<String,String> getProperties()
  {
    return _properties;
  }

  /**
   * Copy item data from a source.
   * @param item Source item.
   */
  public void copyFrom(Item item)
  {
    _identifier=item._identifier;
    _icon=item._icon;
    _setKey=item._setKey;
    _set=item._set;
    _equipmentLocation=item._equipmentLocation;
    _name=item._name;
    //_category=item._category;
    _subCategory=item._subCategory;
    _binding=item._binding;
    _unique=item._unique;
    _bonus.clear();
    _bonus.addAll(item._bonus);
    _stats=new BasicStatsSet(item._stats);
    _statsProvider=item._statsProvider;
    _essenceSlots=item._essenceSlots;
    _durability=item._durability;
    _sturdiness=item._sturdiness;
    _itemLevel=item._itemLevel;
    _minLevel=item._minLevel;
    _maxLevel=item._maxLevel;
    _class=item._class;
    _description=item._description;
    _value=new Money(item._value);
    _stackMax=item._stackMax;
    _stackMax=item._stackMax;
    _quality=item._quality;
    _properties.clear();
    _properties.putAll(item._properties);
  }

  /**
   * Dump the contents of this item as a string.
   * @return A readable string.
   */
  public String dump()
  {
    StringBuilder sb=new StringBuilder();
    sb.append("Name: ").append(_name);
    if (_identifier!=0)
    {
      sb.append(" (id=");
      sb.append(_identifier);
      sb.append(')');
    }
    if (_icon!=null)
    {
      sb.append(" (icon=");
      sb.append(_icon);
      sb.append(')');
    }
    if (_equipmentLocation!=null)
    {
      sb.append(" (");
      sb.append(_equipmentLocation);
      sb.append(')');
    }
    if (_essenceSlots!=0)
    {
      sb.append(" (");
      sb.append(_essenceSlots);
      sb.append(" slot(s))");
    }
    if (_category!=null)
    {
      sb.append(" (");
      sb.append(_category);
      sb.append(')');
    }
    if (_subCategory!=null)
    {
      sb.append(" (");
      sb.append(_subCategory);
      sb.append(')');
    }
    if (_durability!=null)
    {
      sb.append(" (Durability=");
      sb.append(_durability);
      sb.append(')');
    }
    if (_sturdiness!=null)
    {
      sb.append(" (");
      sb.append(_sturdiness);
      sb.append(')');
    }
    if (_quality!=null)
    {
      sb.append(" (Quality=");
      sb.append(_quality);
      sb.append(')');
    }
    if (_unique)
    {
      sb.append(" (unique)");
    }
    if (_binding!=null)
    {
      sb.append(" (");
      sb.append(_binding);
      sb.append(')');
    }
    if (_minLevel!=null)
    {
      sb.append(" (Min level=");
      sb.append(_minLevel);
      sb.append(')');
    }
    if (_maxLevel!=null)
    {
      sb.append(" (Max level=");
      sb.append(_maxLevel);
      sb.append(')');
    }
    if (_itemLevel!=null)
    {
      sb.append(" (Item level=");
      sb.append(_itemLevel);
      sb.append(')');
    }
    if (_value!=null)
    {
      sb.append(" (Value=");
      sb.append(_value);
      sb.append(')');
    }
    if (_stackMax!=null)
    {
      sb.append(" (Stacks=");
      sb.append(_stackMax);
      sb.append(')');
    }
    if (_class!=null)
    {
      sb.append(" (Required class=");
      sb.append(_class);
      sb.append(')');
    }
    sb.append(EndOfLine.NATIVE_EOL);
    // Bonus
    if ((_bonus!=null) && (_bonus.size()>0))
    {
      for(String bonus : _bonus)
      {
        sb.append(bonus).append(EndOfLine.NATIVE_EOL);
      }
    }
    // Properties
    if (_properties.size()>0)
    {
      List<String> propertyNames=new ArrayList<String>(_properties.keySet());
      Collections.sort(propertyNames);
      for(String propertyName : propertyNames)
      {
        String value=_properties.get(propertyName);
        sb.append(propertyName).append(": ").append(value).append(EndOfLine.NATIVE_EOL);
      }
    }
    // Description
    if ((_description!=null) && (_description.length()>0))
    {
      sb.append(_description).append(EndOfLine.NATIVE_EOL);
    }
    // Stats
    sb.append("Stats: ").append(_stats).append(EndOfLine.NATIVE_EOL);
    return sb.toString().trim();
  }

  @Override
  public String toString()
  {
    return _identifier+": "+_name+" ("+_itemLevel+")";
  }
}
