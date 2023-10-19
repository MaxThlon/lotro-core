package delta.games.lotro.config.path;

import java.nio.file.Path;

import delta.common.utils.application.config.path.ApplicationPathConfiguration;
import delta.common.utils.misc.Preferences;
import delta.games.lotro.config.DataConfiguration;
import delta.games.lotro.config.UserConfig;

/**
 * Configuration for lotro companion application paths.
 * @author MaxThlon
 */
public class CompanionApplicationPathConfiguration implements ApplicationPathConfiguration
{
  private static final String APPLICATION_PATH_CONFIGURATION="ApplicationPath";
  private Path _path;
  private Path _userPath;
  private DataConfiguration _dataConfiguration;

  /**
   * Constructor.
   * @param userPath
   * @param userDataPath
   */
  public CompanionApplicationPathConfiguration(Path userPath, Path userDataPath)
  {
    _userPath=userPath;
    _dataConfiguration=new DataConfiguration(userDataPath.toFile());
  }

  protected String getPreferencesSetName()
  {
    return APPLICATION_PATH_CONFIGURATION;
  }

  @Override
  public Path getPath()
  {
    return _path;
  }

  @Override
  public void setPath(Path path)
  {
    _path=path;
  }

  @Override
  public Path getUserPath()
  {
    return _userPath;
  }

  @Override
  public void setUserPath(Path userPath)
  {
    _userPath=userPath;
  }

  /**
   * Get the user path.
   * @return a directory path.
   */
  public DataConfiguration getDataConfiguration()
  {
    return _dataConfiguration;
  }

  /**
   * Initialize from preferences.
   * @param preferences Preferences to use.
   */
  public void fromPreferences(Preferences preferences)
  {
    _dataConfiguration.fromPreferences(preferences);
  }

  /**
   * Save configuration.
   * @param userCfg User configuration.
   */
  public void save(UserConfig userCfg)
  {
    _dataConfiguration.save(userCfg);
  }
}
