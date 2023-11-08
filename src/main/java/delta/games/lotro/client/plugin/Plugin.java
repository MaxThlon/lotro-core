package delta.games.lotro.client.plugin;

import java.util.Map;

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
   * Information
   */
  private Information _information;
  /**
   * Package
   */
  private String _package;
  /**
   * Configuration
   */
  private Map<String, String> _configuration;
  /**
   * Context
   */
  private Context _context;

  /**
   * @param information .
   * @param packageName .
   * @param configuration .
   */
  public Plugin(Information information, String packageName, Map<String, String> configuration) {
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
   * @return plugin package.
   */
  public String getPackage() {
    return _package;
  }
  
  /**
   * @return plugin configuration.
   */
  public Map<String, String> getConfiguration() {
    return _configuration;
  }
  
  /**
   * @return Context .
   */
  public Context getContext() {
    return _context;
  }
}
