package delta.games.lotro.lore.buffs.io.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import delta.common.utils.i18n.SingleLocaleLabelsManager;
import delta.common.utils.xml.DOMParsingTools;
import delta.games.lotro.common.effects.Effect;
import delta.games.lotro.common.effects.io.xml.EffectXMLConstants;
import delta.games.lotro.common.effects.io.xml.EffectXMLParser;
import delta.games.lotro.common.requirements.io.xml.UsageRequirementsXMLParser;
import delta.games.lotro.lore.buffs.EffectBuff;
import delta.games.lotro.utils.i18n.I18nFacade;

/**
 * Parser for effect buffs stored in XML.
 * @author DAM
 */
public class EffectBuffXMLParser
{
  private SingleLocaleLabelsManager _i18n;

  /**
   * Constructor.
   */
  public EffectBuffXMLParser()
  {
    _i18n=I18nFacade.getLabelsMgr("buffs");
  }

  /**
   * Parse effect-based buffs from an XML file.
   * @param source Source file.
   * @return List of parsed buffs.
   */
  public List<EffectBuff> parseEffectsFile(File source)
  {
    List<EffectBuff> buffs=new ArrayList<EffectBuff>();
    Element root=DOMParsingTools.parse(source);
    if (root!=null)
    {
      List<Element> buffTags=DOMParsingTools.getChildTagsByName(root,EffectBuffXMLConstants.BUFF_TAG);
      for(Element buffTag : buffTags)
      {
        EffectBuff buff=parseEffectBuff(buffTag);
        buffs.add(buff);
      }
    }
    return buffs;
  }

  /**
   * Build an effect-based buff from an XML tag.
   * @param root Root XML tag.
   * @return An effect-based buff.
   */
  private EffectBuff parseEffectBuff(Element root)
  {
    EffectBuff buff=new EffectBuff();
    // Key
    String key=DOMParsingTools.getStringAttribute(root.getAttributes(),EffectBuffXMLConstants.BUFF_KEY_ATTR,"");
    buff.setKey(key);
    // Requirements
    UsageRequirementsXMLParser.parseRequirements(buff.getUsageRequirements(),root);
    // Effect
    Element effectTag=DOMParsingTools.getChildTagByName(root,EffectXMLConstants.EFFECT_TAG);
    if (effectTag!=null)
    {
      Effect effect=EffectXMLParser.parseEffect(effectTag,_i18n);
      buff.setEffect(effect);
    }
    return buff;
  }
}
