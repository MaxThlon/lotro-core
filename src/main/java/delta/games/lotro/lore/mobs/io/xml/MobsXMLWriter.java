package delta.games.lotro.lore.mobs.io.xml;

import java.io.File;
import java.util.List;

import javax.xml.transform.sax.TransformerHandler;

import org.xml.sax.helpers.AttributesImpl;

import delta.common.utils.io.xml.XmlFileWriterHelper;
import delta.common.utils.io.xml.XmlWriter;
import delta.common.utils.text.EncodingNames;
import delta.games.lotro.lore.mobs.MobDescription;

/**
 * Writes mobs to XML files.
 * @author DAM
 */
public class MobsXMLWriter
{
  /**
   * Write a file with mobs.
   * @param toFile Output file.
   * @param data Data to write.
   * @return <code>true</code> if it succeeds, <code>false</code> otherwise.
   */
  public static boolean writeMobsFile(File toFile, List<MobDescription> data)
  {
    MobsXMLWriter writer=new MobsXMLWriter();
    boolean ok=writer.writeMobs(toFile,data,EncodingNames.UTF_8);
    return ok;
  }

  /**
   * Write mobs to a XML file.
   * @param outFile Output file.
   * @param data Data to write.
   * @param encoding Encoding to use.
   * @return <code>true</code> if it succeeds, <code>false</code> otherwise.
   */
  public boolean writeMobs(File outFile, final List<MobDescription> data, String encoding)
  {
    XmlFileWriterHelper helper=new XmlFileWriterHelper();
    XmlWriter writer=new XmlWriter()
    {
      @Override
      public void writeXml(TransformerHandler hd) throws Exception
      {
        writeMobs(hd,data);
      }
    };
    boolean ret=helper.write(outFile,encoding,writer);
    return ret;
  }

  private void writeMobs(TransformerHandler hd, List<MobDescription> data) throws Exception
  {
    hd.startElement("","",MobsXMLConstants.MOBS_TAG,new AttributesImpl());
    for(MobDescription mob : data)
    {
      writeMob(hd,mob);
    }
    hd.endElement("","",MobsXMLConstants.MOBS_TAG);
  }

  private void writeMob(TransformerHandler hd, MobDescription mob) throws Exception
  {
    AttributesImpl attrs=new AttributesImpl();

    // In-game identifier
    int id=mob.getIdentifier();
    attrs.addAttribute("","",MobsXMLConstants.ID_ATTR,XmlWriter.CDATA,String.valueOf(id));
    // Name
    String name=mob.getName();
    attrs.addAttribute("","",MobsXMLConstants.NAME_ATTR,XmlWriter.CDATA,name);
    hd.startElement("","",MobsXMLConstants.MOB_TAG,attrs);
    hd.endElement("","",MobsXMLConstants.MOB_TAG);
  }
}
