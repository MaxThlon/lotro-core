package delta.games.lotro.lore.items.legendary2;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import delta.games.lotro.common.enums.SocketType;
import delta.games.lotro.config.DataFiles;
import delta.games.lotro.config.LotroCoreConfig;
import delta.games.lotro.lore.items.legendary2.io.xml.TraceriesXMLParser;

/**
 * Manager for all known traceries.
 * @author DAM
 */
public class TraceriesManager
{
  private static final Logger LOGGER=Logger.getLogger(TraceriesManager.class);

  private static TraceriesManager _instance=null;

  private HashMap<Integer,Tracery> _cache;

  /**
   * Get the sole instance of this class.
   * @return the sole instance of this class.
   */
  public static TraceriesManager getInstance()
  {
    if (_instance==null)
    {
      _instance=new TraceriesManager();
      _instance.loadAll();
    }
    return _instance;
  }

  /**
   * Constructor.
   */
  public TraceriesManager()
  {
    _cache=new HashMap<Integer,Tracery>(100);
  }

  /**
   * Load all.
   */
  private void loadAll()
  {
    _cache.clear();
    LotroCoreConfig cfg=LotroCoreConfig.getInstance();
    File traceriesFile=cfg.getFile(DataFiles.TRACERIES);
    long now=System.currentTimeMillis();
    List<Tracery> traceries=TraceriesXMLParser.parseTraceriesFile(traceriesFile);
    for(Tracery tracery : traceries)
    {
      registerTracery(tracery);
    }
    long now2=System.currentTimeMillis();
    long duration=now2-now;
    LOGGER.info("Loaded "+_cache.size()+" traceries in "+duration+"ms.");
  }

  /**
   * Register a new tracery.
   * @param tracery Tracery to register.
   */
  private void registerTracery(Tracery tracery)
  {
    _cache.put(Integer.valueOf(tracery.getIdentifier()),tracery);
  }

  /**
   * Get a tracery using its identifier.
   * @param id Tracery identifier.
   * @return A tracery or <code>null</code> if not found.
   */
  public Tracery getTracery(int id)
  {
    return _cache.get(Integer.valueOf(id));
  }

  /**
   * Get all the traceries for a given socket type.
   * @param type Socket type.
   * @return A list of traceries.
   */
  public List<Tracery> getTracery(SocketType type)
  {
    List<Tracery> ret=new ArrayList<Tracery>();
    for(Tracery tracery : _cache.values())
    {
      if (tracery.getType()==type)
      {
        ret.add(tracery);
      }
    }
    return ret;
  }
}
