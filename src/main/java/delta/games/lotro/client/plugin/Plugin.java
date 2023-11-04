package delta.games.lotro.client.plugin;

import delta.common.utils.context.Context;
import delta.common.utils.context.SimpleContextImpl;

/**
 * Plugin
 * @author MaxThlon
 */
public class Plugin {
  /**
   * Information
   * @author MaxThlon
   */
  public static class Information {
    @SuppressWarnings("javadoc")
    public String _name, _author, _version, _description, _image;

    /**
     * @param name
     * @param author
     * @param version
     * @param description
     * @param image
     */
    public Information(String name, String author, String version, String description, String image) {
      _name=name; _author=author; _version=version; _description=description; _image=image;
     }
  }
  
  /**
   * Configuration
   * @author MaxThlon
   */
  public static class Configuration {
    /**
     * Apartment
     */
    public String _apartment;

    /**
     * @param apartment
     */
    public Configuration(String apartment) {
      _apartment=apartment;
     }
  }

  /**
   * Information
   */
  public Information _information;
  /**
   * Package
   */
  public String _package;
  /**
   * Configuration
   */
  public Configuration _configuration;
  /**
   * Configuration
   */
  public Context _context;

  /**
   * @param information .
   * @param packageName .
   * @param configuration .
   */
  public Plugin(Information information, String packageName, Configuration configuration) {
    _information=information;
    _package=packageName;
    _configuration=configuration;
    _context=new SimpleContextImpl();
  }
  
  /**
   * @return plugin information.
   */
  public Information getInformation() {
    return _information;
  }
  
  /**
   * @return plugin package name.
   */
  public String getPackageName() {
    return _package;
  }
  
  /**
   * @return plugin configuration.
   */
  public Configuration getConfiguration() {
    return _configuration;
  }
}
