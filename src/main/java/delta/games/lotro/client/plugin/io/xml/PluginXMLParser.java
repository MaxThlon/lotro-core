package delta.games.lotro.client.plugin.io.xml;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;

import delta.common.utils.xml.DOMParsingTools;
import delta.games.lotro.client.plugin.Plugin;

/**
 * Parser for skin descriptions stored in XML.
 * @author MaxThlon
 */
public class PluginXMLParser
{
  /**
   * Constructor.
   */
  public PluginXMLParser() {}
  
  /**
   * Parse a plugin descriptions XML file.
   * @param source Source file.
   * @return A Plugin.
   * @throws IOException 
   * @throws DOMException 
   */
  public Plugin parsePluginData(File source, String apartmentName)
  {
    Element root=DOMParsingTools.parse(source);
    if (root!=null) {
      Element informationElem = DOMParsingTools.getChildTagByName(root, PluginXMLConstants.INFORMATION_TAG);
      Element packageElem = DOMParsingTools.getChildTagByName(root, PluginXMLConstants.PACKAGE_TAG);
      Element configurationElem = DOMParsingTools.getChildTagByName(root, PluginXMLConstants.CONFIGURATION_TAG);
      
      Element NameElem=DOMParsingTools.getChildTagByName(informationElem, PluginXMLConstants.NAME_TAG);
      Element AuhtorElem=DOMParsingTools.getChildTagByName(informationElem, PluginXMLConstants.AUTHOR_TAG);
      Element VersionElem=DOMParsingTools.getChildTagByName(informationElem, PluginXMLConstants.VERSION_TAG);
      Element DescriptionElem=DOMParsingTools.getChildTagByName(informationElem, PluginXMLConstants.DESCRIPTION_TAG);
      Element ImageElem=DOMParsingTools.getChildTagByName(informationElem, PluginXMLConstants.IMAGE_TAG);
      
      return new Plugin(
          new Plugin.Information(
            (NameElem!=null)?NameElem.getTextContent():"Invalid",
            (AuhtorElem!=null)?AuhtorElem.getTextContent():null,
            (VersionElem!=null)?VersionElem.getTextContent():null,
            (DescriptionElem!=null)?DescriptionElem.getTextContent():null,
            (ImageElem!=null)?ImageElem.getTextContent():null
          ),
          apartmentName,
          (packageElem!=null)?packageElem.getTextContent():null,          
          (configurationElem==null)?null:
            new Plugin.Configuration(
                DOMParsingTools.getStringAttribute(configurationElem.getAttributes(),PluginXMLConstants.CONFIGURATION_APARTMENT_ATTR,null)
            )
      );
    }
    return null;
  }
}
