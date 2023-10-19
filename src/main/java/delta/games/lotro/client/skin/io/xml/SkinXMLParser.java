package delta.games.lotro.client.skin.io.xml;

import java.io.File;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.w3c.dom.Element;

import delta.common.utils.skin.Skin;
import delta.common.utils.xml.DOMParsingTools;

/**
 * Parser for skin descriptions stored in XML.
 * @author MaxThlon
 */
public class SkinXMLParser
{
  //private static final Logger LOGGER=Logger.getLogger(SkinXMLParser.class);

  /**
   * Constructor.
   */
  public SkinXMLParser() {}

  /**
   * Parse a skin descriptions XML file.
   * @param source Source file.
   * @return skin name.
   */
  public String parseSkinName(File source)
  {
    String name="invalid";
    Element root=DOMParsingTools.parse(source);
    if (root!=null)
    {
      //Element optTag=DOMParsingTools.getChildTagByName(root,SkinXMLConstants.OPT_TAG);
      Element skinNameTag=DOMParsingTools.getChildTagByName(root,SkinXMLConstants.SKIN_NAME_TAG);
      
      name = DOMParsingTools.getStringAttribute(skinNameTag.getAttributes(), SkinXMLConstants.SKIN_NAME_NAME_ATTR, name);
      
    }
    return name;
  }
  
  /**
   * Parse a skin descriptions XML file.
   * @param source Source file.
   * @return A Skin.
   */
  public Skin parseSkinData(File source)
  {
    Element root=DOMParsingTools.parse(source);
    if (root!=null) {
      return new Skin(
        DOMParsingTools.getStringAttribute(
            DOMParsingTools.getChildTagByName(root,SkinXMLConstants.SKIN_NAME_TAG).getAttributes(),
            SkinXMLConstants.SKIN_NAME_NAME_ATTR,
            "invalid"
        ),

        DOMParsingTools.getChildTagsByName(root, SkinXMLConstants.MAPPING_TAG).parallelStream()
          .map(Element::getAttributes)
          .collect(Collectors.toMap(
            mappingTagAttributes-> DOMParsingTools.getStringAttribute(mappingTagAttributes,SkinXMLConstants.MAPPING_ARTASSETID_ATTR,null),
            mappingTagAttributes-> Paths.get(DOMParsingTools.getStringAttribute(mappingTagAttributes,SkinXMLConstants.MAPPING_FILENAME_ATTR,null)),
            (fileName1, fileName2) -> fileName2
           )),

        DOMParsingTools.getChildTagsByName(root, SkinXMLConstants.PANEL_FILE_TAG).parallelStream()
          .collect(Collectors.toMap(
              panelTag-> DOMParsingTools.getStringAttribute(panelTag.getAttributes(),SkinXMLConstants.ID_ATTR,null),
              panelTag-> new Skin.PanelFile(
                  DOMParsingTools.getChildTagsByName(panelTag, SkinXMLConstants.ELEMENT_TAG).stream()
                    .map(Element::getAttributes)
                    .collect(Collectors.toMap(
                        ElementTagAttributes-> DOMParsingTools.getStringAttribute(ElementTagAttributes,SkinXMLConstants.ID_ATTR,null),
                        ElementTagAttributes-> new Skin.PanelFile.Element(
                              DOMParsingTools.getIntAttribute(ElementTagAttributes,SkinXMLConstants.ELEMENT_X_ATTR,-1),
                              DOMParsingTools.getIntAttribute(ElementTagAttributes,SkinXMLConstants.ELEMENT_Y_ATTR,-1),
                              DOMParsingTools.getIntAttribute(ElementTagAttributes,SkinXMLConstants.ELEMENT_WIDTH_ATTR,-1),
                              DOMParsingTools.getIntAttribute(ElementTagAttributes,SkinXMLConstants.ELEMENT_HEIGHT_ATTR,-1)
                        ),
                        (element1, element2) -> element2
                  ))),
              (panelFile1, panelFile2) -> panelFile2
          ))
      );
    }
    return null;
  }
}
