package delta.games.lotro.character.storage.currencies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import delta.games.lotro.common.Scope;
import delta.games.lotro.common.comparators.NamedComparator;
import delta.games.lotro.common.enums.PaperItemCategory;
import delta.games.lotro.lore.items.WellKnownItems;
import delta.games.lotro.lore.items.paper.PaperItem;
import delta.games.lotro.lore.items.paper.PaperItemsManager;

/**
 * Access to all currencies.
 * @author DAM
 */
public class Currencies
{
  private static Currencies _instance=new Currencies();
  private HashMap<String,Currency> _currenciesMap;
  private List<Currency> _currencies;

  /**
   * Get the sole instance of this class.
   * @return the sole instance of this class.
   */
  public static Currencies get()
  {
    return _instance;
  }

  /**
   * Private constructor.
   */
  private Currencies()
  {
    _currencies=new ArrayList<Currency>();
    _currenciesMap=new HashMap<String,Currency>();
    init();
  }

  private void init()
  {
    // Gold
    Currency gold=new Currency(CurrencyKeys.GOLD,"Gold",Scope.CHARACTER,"World");
    gold.setFactor(100*1000);
    registerCurrency(gold);
    // XP
    Currency xp=new Currency(CurrencyKeys.XP,"XP",Scope.CHARACTER,"World");
    registerCurrency(xp);
    // Paper items
    List<PaperItem> paperItems=PaperItemsManager.getInstance().getAll();
    for(PaperItem paperItem : paperItems)
    {
      String id=String.valueOf(paperItem.getIdentifier());
      String name=paperItem.getName();
      boolean shared=paperItem.isShared();
      Scope scope=(shared?Scope.SERVER:Scope.CHARACTER);
      PaperItemCategory category=paperItem.getCategory();
      Currency currency=new Currency(id,name,scope,category.getLabel());
      registerCurrency(currency);
    }
    // Marks
    setupCurrencyLegacy(getByKey(String.valueOf(WellKnownItems.MARK)),CurrencyKeys.MARKS);
    // Medallions
    setupCurrencyLegacy(getByKey(String.valueOf(WellKnownItems.MEDALLION)),CurrencyKeys.MEDALLIONS);
    // Seals
    setupCurrencyLegacy(getByKey(String.valueOf(WellKnownItems.SEAL)),CurrencyKeys.SEALS);
    // Destiny points
    Currency destinyPoints=new Currency(CurrencyKeys.DESTINY_POINTS,"Destiny Points",Scope.SERVER,"World");
    registerCurrency(destinyPoints);
    // LOTRO points
    Currency lotroPoints=new Currency("lotroPoints","LOTRO Points",Scope.ACCOUNT,"World");
    registerCurrency(lotroPoints);
    // In-game time
    Currency inGameTime=new Currency(CurrencyKeys.IN_GAME_TIME,"In-game Time",Scope.CHARACTER,"In-game Time");
    registerCurrency(inGameTime);
  }

  private void setupCurrencyLegacy(Currency currency, String key)
  {
    currency.setLegacyKey(key);
    _currenciesMap.put(key,currency);
  }

  private void registerCurrency(Currency currency)
  {
    String key=currency.getKey();
    _currenciesMap.put(key,currency);
    _currencies.add(currency);
  }

  /**
   * Get a list of all known currencies.
   * @return a list of currencies.
   */
  public List<Currency> getCurrencies()
  {
    List<Currency> ret=new ArrayList<Currency>(_currencies);
    Collections.sort(ret,new CurrencyKeyComparator());
    return _currencies;
  }

  /**
   * Get a currency using its identifying key.
   * @param key Key of the currency to get.
   * @return A currency or <code>null</code> if not found.
   */
  public Currency getByKey(String key)
  {
    return _currenciesMap.get(key);
  }

  /**
   * Get a list of available currencies, including the specified scopes.
   * @param includeCharacter Include character currencies.
   * @param includeServer Include account/server currencies.
   * @param includeAccount Include account currencies.
   * @return A list of currencies, sorted by name.
   */
  public static List<Currency> getAvailableCurrencies(boolean includeCharacter, boolean includeServer, boolean includeAccount)
  {
    Set<Scope> scopes=new HashSet<Scope>();
    if (includeCharacter)
    {
      scopes.add(Scope.CHARACTER);
    }
    if (includeServer)
    {
      scopes.add(Scope.SERVER);
    }
    if (includeAccount)
    {
      scopes.add(Scope.ACCOUNT);
    }
    return getCurrencies(scopes);
  }

  private static List<Currency> getCurrencies(Set<Scope> scopes)
  {
    List<Currency> ret=new ArrayList<Currency>();
    for(Currency currency : Currencies.get().getCurrencies())
    {
      if (scopes.contains(currency.getScope()))
      {
        ret.add(currency);
      }
    }
    Collections.sort(ret,new NamedComparator());
    return ret;
  }
}
