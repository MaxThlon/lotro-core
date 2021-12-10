package delta.games.lotro.character.storage.carryalls.io.xml;

import java.io.File;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import delta.common.utils.xml.DOMParsingTools;
import delta.games.lotro.character.storage.carryalls.CarryAllDefinition;
import delta.games.lotro.character.storage.carryalls.CarryAllsDefinitionsManager;
import delta.games.lotro.lore.items.Item;
import delta.games.lotro.lore.items.ItemsManager;

/**
 * Parser for carry-all definitions stored in XML.
 * @author DAM
 */
public class CarryAllDefinitionXMLParser
{
  /**
   * Parse the XML file.
   * @param source Source file.
   * @return Parsed carry-alls.
   */
  public CarryAllsDefinitionsManager parseXML(File source)
  {
    CarryAllsDefinitionsManager ret=new CarryAllsDefinitionsManager();
    Element root=DOMParsingTools.parse(source);
    if (root!=null)
    {
      // Carry-alls
      List<Element> carryAllTags=DOMParsingTools.getChildTagsByName(root,CarryAllDefinitionXMLConstants.CARRY_ALL_TAG);
      for(Element carryAllTag : carryAllTags)
      {
        CarryAllDefinition carryAll=parseCarryAll(carryAllTag);
        ret.addCarryAll(carryAll);
      }
    }
    return ret;
  }

  private CarryAllDefinition parseCarryAll(Element root)
  {
    NamedNodeMap attrs=root.getAttributes();

    // Identifier
    int id=DOMParsingTools.getIntAttribute(attrs,CarryAllDefinitionXMLConstants.CARRY_ALL_ID_ATTR,0);
    Item item=ItemsManager.getInstance().getItem(id);
    // Max items max
    int maxItems=DOMParsingTools.getIntAttribute(attrs,CarryAllDefinitionXMLConstants.CARRY_ALL_MAX_ITEMS_ATTR,0);
    // Stack max
    int stackMax=DOMParsingTools.getIntAttribute(attrs,CarryAllDefinitionXMLConstants.CARRY_ALL_STACK_MAX_ATTR,0);
    CarryAllDefinition ret=new CarryAllDefinition(item,maxItems,stackMax);
    return ret;
  }
}
