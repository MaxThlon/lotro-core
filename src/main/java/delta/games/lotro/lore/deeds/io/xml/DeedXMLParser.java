package delta.games.lotro.lore.deeds.io.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import delta.common.utils.xml.DOMParsingTools;
import delta.games.lotro.common.requirements.io.xml.UsageRequirementsXMLParser;
import delta.games.lotro.common.rewards.io.xml.RewardsXMLParser;
import delta.games.lotro.lore.deeds.DeedDescription;
import delta.games.lotro.lore.deeds.DeedType;
import delta.games.lotro.lore.quests.io.xml.AchievableXMLConstants;
import delta.games.lotro.lore.quests.io.xml.AchievableXMLParser;
import delta.games.lotro.lore.quests.objectives.io.xml.ObjectivesXMLParser;
import delta.games.lotro.lore.webStore.WebStoreItem;
import delta.games.lotro.lore.webStore.WebStoreItemsManager;

/**
 * Parser for deed descriptions stored in XML.
 * @author DAM
 */
public class DeedXMLParser extends AchievableXMLParser
{
  /**
   * Parse the XML file.
   * @param source Source file.
   * @return Parsed deed or <code>null</code>.
   */
  public List<DeedDescription> parseXML(File source)
  {
    List<DeedDescription> ret=new ArrayList<DeedDescription>();
    Element root=DOMParsingTools.parse(source);
    if (root!=null)
    {
      String tagName=root.getTagName();
      if (DeedXMLConstants.DEED_TAG.equals(tagName))
      {
        DeedDescription deed=parseDeed(root);
        ret.add(deed);
      }
      else
      {
        List<Element> deedTags=DOMParsingTools.getChildTagsByName(root,DeedXMLConstants.DEED_TAG);
        for(Element deedTag : deedTags)
        {
          DeedDescription deed=parseDeed(deedTag);
          ret.add(deed);
        }
      }
    }
    return ret;
  }

  private DeedDescription parseDeed(Element root)
  {
    DeedDescription deed=new DeedDescription();

    NamedNodeMap attrs=root.getAttributes();

    // Shared attributes
    parseAchievableAttributes(attrs,deed);
    // Key
    String key=DOMParsingTools.getStringAttribute(attrs,DeedXMLConstants.DEED_KEY_ATTR,null);
    deed.setKey(key);
    // Type
    DeedType type=null;
    String typeStr=DOMParsingTools.getStringAttribute(attrs,DeedXMLConstants.DEED_TYPE_ATTR,null);
    if (typeStr!=null)
    {
      try
      {
        type=DeedType.valueOf(typeStr);
      }
      catch(Exception e)
      {
        // Ignored
      }
    }
    deed.setType(type);
    // Requirements
    UsageRequirementsXMLParser.parseRequirements(deed.getUsageRequirement(),root);
    parseAchievablesRequirements(root,deed);
    parseWorldEventsRequirements(root,deed);
    // Web store item
    int webStoreItemID=DOMParsingTools.getIntAttribute(attrs,AchievableXMLConstants.WEB_STORE_ITEM_ID_ATTR,0);
    if (webStoreItemID>0)
    {
      WebStoreItem webStoreItem=WebStoreItemsManager.getInstance().getWebStoreItem(webStoreItemID);
      deed.setWebStoreItem(webStoreItem);
    }
    // Maps
    parseMaps(root,deed);
    // Objectives
    ObjectivesXMLParser.loadObjectives(root,deed.getObjectives());
    // Rewards
    RewardsXMLParser.loadRewards(root,deed.getRewards());
    return deed;
  }
}
