package delta.games.lotro.plugins;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Access to some plugin constants.
 * @author DAM
 */
public class PluginConstants
{
  /**
   * Get the plugins data directory.
   * @return the plugins data directory.
   */
  public static File getPluginsDataDir()
  {
    File lotroDir=getLOTRODataDir();
    return new File(lotroDir,"PluginData");
  }

  private static File getLOTRODataDir()
  {
    File homeDir=new File(System.getProperty("user.home"));
    File documentsDir=new File(homeDir,"Documents");
    File lotroDir=new File(documentsDir,"The Lord of the Rings Online");
    return lotroDir;
  }

  /**
   * Get the list of accounts as found in the plugins data.
   * @return A list of account IDs.
   */
  public static List<String> getAccounts()
  {
    List<String> ret=new ArrayList<String>();
    File pluginDataDir=getLOTRODataDir();
    String[] files=pluginDataDir.list();
    if (files!=null)
    {
      for(String file : files)
      {
        ret.add(file);
      }
    }
    return ret;
  }

  /**
   * Get the list of servers for an account.
   * @param account Account ID.
   * @return A list of servers.
   */
  public static List<String> getServersForAccount(String account)
  {
    List<String> ret=new ArrayList<String>();
    File pluginDataDir=getLOTRODataDir();
    File accountDir=new File(pluginDataDir,account);
    String[] files=accountDir.list();
    if (files!=null)
    {
      for(String file : files)
      {
        if (!"AllServers".equals(files))
        {
          ret.add(file);
        }
      }
    }
    return ret;
  }

  /**
   * Get the directory for the plugin data for an account/server couple.
   * @param account Targeted account ID.
   * @param server Server name.
   * @return A directory.
   */
  public static File getServerAccount(String account, String server)
  {
    File pluginDataDir=getPluginsDataDir();
    File accountDir=new File(pluginDataDir,account);
    File serverDir=new File(accountDir,server);
    return serverDir;
  }

  /**
   * Get all the characters for a given account/server couple.
   * @param account Targeted account ID.
   * @param server Server name.
   * @param includeSessionPlay Include session play characters (that start with "~").
   * @return A possibly empty list of characters.
   */
  public static List<String> getCharacters(String account, String server, boolean includeSessionPlay)
  {
    List<String> ret=new ArrayList<String>();
    File dir=getServerAccount(account,server);
    File[] characterDirs=dir.listFiles();
    if (characterDirs!=null)
    {
      for(File characterDir : characterDirs)
      {
        String name=characterDir.getName();
        if (!"AllCharacters".equals(name))
        {
          boolean isSessionPlay=(name.startsWith("~"));
          if ((includeSessionPlay) || (!isSessionPlay))
          {
            ret.add(name);
          }
        }
      }
    }
    return ret;
  }

  /**
   * Get the name of the most recently updated character.
   * @param account Targeted account ID.
   * @param server Server name.
   * @return A character name or <code>null</code> if none.
   */
  public static String getMostRecentLoggedInCharacter(String account, String server)
  {
    File rootDir=getServerAccount(account,server);
    List<String> characters=getCharacters(account,server,false);
    String selectedCharacter=null;
    Long bestDate=null;
    for(String character : characters)
    {
      File characterDir=new File(rootDir,character);
      Long mostRecentCharacterFile=getMostRecentFileDate(characterDir);
      if (mostRecentCharacterFile!=null)
      {
        boolean better=((bestDate==null) || (mostRecentCharacterFile.longValue()>bestDate.longValue()));
        if (better)
        {
          selectedCharacter=character;
          bestDate=mostRecentCharacterFile;
        }
      }
    }
    return selectedCharacter;
  }

  private static Long getMostRecentFileDate(File directory)
  {
    Long ret=null;
    if (directory.exists())
    {
      File[] files=directory.listFiles();
      if (files!=null)
      {
        for(File file : files)
        {
          long lastModified=file.lastModified();
          if ((ret==null) || (lastModified>ret.longValue()))
          {
            ret=Long.valueOf(lastModified);
          }
        }
      }
    }
    return ret;
  }
}
