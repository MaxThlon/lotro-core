package delta.games.lotro.character;

import delta.common.utils.text.EndOfLine;
import delta.games.lotro.character.stats.BasicStatsSet;
import delta.games.lotro.character.stats.tomes.TomesSet;
import delta.games.lotro.character.stats.virtues.VirtuesSet;
import delta.games.lotro.common.CharacterClass;
import delta.games.lotro.common.Race;

/**
 * Storage class for a LOTRO character definition.
 * @author DAM
 */
public class CharacterData
{
  private Long _date;
  private String _shortDescription;
  private String _description;
  private String _characterName;
  private String _server;
  private CharacterClass _class;
  private Race _race;
  private String _region;
  private int _level;
  private BasicStatsSet _stats;
  private CharacterEquipment _equipment;
  private VirtuesSet _virtues;
  private TomesSet _tomes;
  private BasicStatsSet _additionalStats;

  /**
   * Constructor.
   */
  public CharacterData()
  {
    _date=null;
    _shortDescription="";
    _description="";
    _characterName="";
    _server="";
    _class=null;
    _race=null;
    _region="";
    _level=0;
    _stats=new BasicStatsSet();
    _equipment=new CharacterEquipment();
    _virtues=new VirtuesSet();
    _tomes=new TomesSet();
    _additionalStats=new BasicStatsSet();
  }

  /**
   * Get the date for this character data.
   * @return a date or <code>null</code> if not set.
   */
  public Long getDate()
  {
    return _date;
  }

  /**
   * Set the date for this character data.
   * @param date Date to set.
   */
  public void setDate(Long date)
  {
    _date=date;
  }

  /**
   * Get the short description.
   * @return the short description.
   */
  public String getShortDescription()
  {
    return _shortDescription;
  }

  /**
   * Set the short description.
   * @param shortDescription the short description to set.
   */
  public void setShortDescription(String shortDescription)
  {
    if (shortDescription==null)
    {
      shortDescription="";
    }
    _shortDescription=shortDescription;
  }

  /**
   * Get the description.
   * @return the description.
   */
  public String getDescription()
  {
    return _description;
  }

  /**
   * Set the description.
   * @param description the description to set.
   */
  public void setDescription(String description)
  {
    if (description==null)
    {
      description="";
    }
    _description=description;
  }

  /**
   * Get the character's name.
   * @return the character's name.
   */
  public String getName()
  {
    return _characterName;
  }

  /**
   * Set the character's name.
   * @param name the name to set.
   */
  public void setName(String name)
  {
    if (name==null)
    {
      name="";
    }
    _characterName=name;
  }

  /**
   * Get the character's server.
   * @return the character's server.
   */
  public String getServer()
  {
    return _server;
  }

  /**
   * Set the character's server.
   * @param server the server to set. 
   */
  public void setServer(String server)
  {
    if (server==null)
    {
      server="";
    }
    _server=server;
  }

  /**
   * Get the character's class.
   * @return the character's class.
   */
  public CharacterClass getCharacterClass()
  {
    return _class;
  }

  /**
   * Set the character's class.
   * @param characterClass the class to set.
   */
  public void setCharacterClass(CharacterClass characterClass)
  {
    _class=characterClass;
  }

  /**
   * Get the character's race.
   * @return the character's race.
   */
  public Race getRace()
  {
    return _race;
  }

  /**
   * Set the character's race.
   * @param race the race to set.
   */
  public void setRace(Race race)
  {
    _race=race;
  }

  /**
   * Set the character's region.
   * @return the character's region.
   */
  public String getRegion()
  {
    return _region;
  }

  /**
   * Set the character's region.
   * @param region the region to set.
   */
  public void setRegion(String region)
  {
    if (region==null)
    {
      region="";
    }
    _region=region;
  }

  /**
   * Get the character's level.
   * @return the character's level.
   */
  public int getLevel()
  {
    return _level;
  }

  /**
   * Set the character's level.
   * @param level the level to set.
   */
  public void setLevel(int level)
  {
    _level=level;
  }

  /**
   * Get character stats.
   * @return The stats storage..
   */
  public BasicStatsSet getStats()
  {
    return _stats;
  }

  /**
   * Get additional stats (stats to add to automatically
   * computed stats: base/virtues/tomes/equipment).
   * @return The additional stats storage.
   */
  public BasicStatsSet getAdditionalStats()
  {
    return _additionalStats;
  }

  /**
   * Get the character's equipment.
   * @return the character's equipment.
   */
  public CharacterEquipment getEquipment()
  {
    return _equipment;
  }

  /**
   * Get the character's virtues.
   * @return the character's virtues.
   */
  public VirtuesSet getVirtues()
  {
    return _virtues;
  }

  /**
   * Get the character's tomes.
   * @return the character's tomes.
   */
  public TomesSet getTomes()
  {
    return _tomes;
  }

  @Override
  public String toString()
  {
    StringBuilder sb=new StringBuilder();
    sb.append("Name [").append(_characterName).append("], ");
    sb.append("Server [").append(_server).append("], ");
    sb.append("Race [").append(_race).append("], ");
    sb.append("Region [").append(_region).append("], ");
    sb.append("Class [").append(_class).append("], ");
    sb.append("Race [").append(_race).append("], ");
    sb.append("Date [").append(_date).append("], ");
    sb.append("Level [").append(_level).append("], ");
    sb.append("Short description: ").append(_shortDescription).append(", ");
    sb.append("Description: ").append(_description).append(", ");
    sb.append("Stats: ").append(_stats).append(", ");
    sb.append("Equipment:").append(_equipment).append(EndOfLine.NATIVE_EOL);
    sb.append("Virtues:").append(_virtues).append(EndOfLine.NATIVE_EOL);
    sb.append("Tomes:").append(_tomes).append(EndOfLine.NATIVE_EOL);
    sb.append("Additional stats:").append(_additionalStats).append(EndOfLine.NATIVE_EOL);
    return sb.toString().trim();
  }
}
