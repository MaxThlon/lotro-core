package delta.games.lotro.lore.rewardsTrack;

import delta.games.lotro.lore.items.Item;
import delta.games.lotro.utils.Proxy;

/**
 * Step of a rewards track.
 * @author DAM
 */
public class RewardsTrackStep
{
  private float _xpCostMultiplier;
  private Proxy<Item> _reward;
  private int _uiElementID;

  /**
   * Constructor.
   */
  public RewardsTrackStep()
  {
    _xpCostMultiplier=1.0f;
    _reward=null;
    _uiElementID=0;
  }

  /**
   * Get the XP cost multiplier.
   * @return the xpCostMultiplier
   */
  public float getXpCostMultiplier()
  {
    return _xpCostMultiplier;
  }

  /**
   * Set the XP cost multiplier.
   * @param xpCostMultiplier the multiplier to set.
   */
  public void setXpCostMultiplier(float xpCostMultiplier)
  {
    _xpCostMultiplier=xpCostMultiplier;
  }

  /**
   * Get the reward for this step.
   * @return A reward.
   */
  public Proxy<Item> getReward()
  {
    return _reward;
  }

  /**
   * Set the reward for this step.
   * @param reward the reward to set.
   */
  public void setReward(Proxy<Item> reward)
  {
    _reward=reward;
  }

  /**
   * Get the UI element ID for this step.
   * @return A UI element ID.
   */
  public int getUiElementID()
  {
    return _uiElementID;
  }

  /**
   * Set the UI element ID for this step.
   * @param uiElementID the UI element ID to set.
   */
  public void setUiElementID(int uiElementID)
  {
    _uiElementID=uiElementID;
  }
}
