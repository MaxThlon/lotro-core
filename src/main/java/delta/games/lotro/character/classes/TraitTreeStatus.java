package delta.games.lotro.character.classes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import delta.common.utils.NumericTools;
import delta.common.utils.misc.IntegerHolder;
import delta.games.lotro.character.stats.buffs.Buff;
import delta.games.lotro.character.stats.buffs.BuffInstance;
import delta.games.lotro.character.stats.buffs.BuffsManager;
import delta.games.lotro.character.traits.TraitDescription;

/**
 * Trait tree status.
 * @author DAM
 */
public class TraitTreeStatus
{
  private static final Logger LOGGER=Logger.getLogger(TraitTreeStatus.class);

  private TraitTree _tree;
  private TraitTreeBranch _selectedBranch;
  private Map<Integer,IntegerHolder> _treeRanks;

  /**
   * Constructor.
   * @param tree Trait tree to use.
   */
  public TraitTreeStatus(TraitTree tree)
  {
    _tree=tree;
    _selectedBranch=_tree.getBranches().get(0);
    initRanks();
  }

  private void initRanks()
  {
    _treeRanks=new HashMap<Integer,IntegerHolder>();
    for(TraitTreeBranch branch : _tree.getBranches())
    {
      for(String cellId : branch.getCells())
      {
        TraitDescription trait=branch.getTraitForCell(cellId);
        Integer key=Integer.valueOf(trait.getIdentifier());
        _treeRanks.put(key,new IntegerHolder());
      }
    }
  }

  /**
   * Initialize this trait tree status from a buffs manager.
   * @param buffs Buffs manager to use.
   */
  public void initFromBuffs(BuffsManager buffs)
  {
    int nbBuffs=buffs.getBuffsCount();
    for(int i=0;i<nbBuffs;i++)
    {
      BuffInstance buffInstance=buffs.getBuffAt(i);
      Buff buff=buffInstance.getBuff();
      String buffId=buff.getId();
      Integer traitId=NumericTools.parseInteger(buffId,false);
      if (traitId!=null)
      {
        IntegerHolder intValue=_treeRanks.get(traitId);
        if (intValue!=null)
        {
          Integer rank=buffInstance.getTier();
          int value=rank!=null?rank.intValue():1;
          intValue.setInt(value);
        }
      }
    }
    if (LOGGER.isDebugEnabled())
    {
      LOGGER.debug("Loaded tree from buffs: "+_treeRanks);
    }
  }

  /**
   * Get the selected branch.
   * @return the selected branch.
   */
  public TraitTreeBranch getSelectedBranch()
  {
    return _selectedBranch;
  }

  /**
   * Set the selected branch.
   * @param selectedBranch Branch to set.
   */
  public void setSelectedBranch(TraitTreeBranch selectedBranch)
  {
    _selectedBranch=selectedBranch;
  }

  /**
   * Get the total number of activated ranks in the whole tree. 
   * @return A ranks count.
   */
  public int getTotalRanksInTree()
  {
    int totalRanks=0;
    for(IntegerHolder rank : _treeRanks.values())
    {
      totalRanks+=rank.getInt();
    }
    return totalRanks;
  }

  /**
   * Get the number of activated ranks in the specified row of the specified branch.
   * @param branch Branch to use.
   * @param row Row, starting at 1.
   * @return A ranks count.
   */
  private int getRanksForRow(TraitTreeBranch branch, int row)
  {
    int totalRanks=0;
    String seed=String.valueOf(row)+"_";
    for(String cellId : branch.getCells())
    {
      if (cellId.startsWith(seed))
      {
        TraitTreeCell cell=branch.getCell(cellId);
        TraitDescription trait=cell.getTrait();
        Integer key=Integer.valueOf(trait.getIdentifier());
        int ranks=_treeRanks.get(key).getInt();
        totalRanks+=ranks;
      }
    }
    return totalRanks;
  }

  /**
   * Get the number of activated ranks in the given first rows of the specified branch.
   * @param branch Branch to use.
   * @param rows Rows to use.
   * @return A ranks count.
   */
  private int getRanksForRows(TraitTreeBranch branch, int rows)
  {
    int totalRanks=0;
    for(int row=1;row<=rows;row++)
    {
      totalRanks+=getRanksForRow(branch,row);
    }
    return totalRanks;
  }

  /**
   * Get the rank for the given cell.
   * @param cellId Cell identifier.
   * @return A rank value.
   */
  public int getRankForCell(String cellId)
  {
    TraitTreeCell cell=getCellById(cellId);
    TraitDescription trait=cell.getTrait();
    Integer key=Integer.valueOf(trait.getIdentifier());
    IntegerHolder ret=_treeRanks.get(key);
    return ret!=null?ret.getInt():0;
  }

  /**
   * Set the rank for the specified cell.
   * @param cellId Cell identifier.
   * @param rank Rank to set.
   */
  public void setRankForCell(String cellId, int rank)
  {
    TraitTreeCell cell=getCellById(cellId);
    TraitDescription trait=cell.getTrait();
    Integer key=Integer.valueOf(trait.getIdentifier());
    IntegerHolder intHolder=_treeRanks.get(key);
    intHolder.setInt(rank);
  }

  /**
   * Get the total cost for this tree.
   * @return a cost in trait points.
   */
  public int getCost()
  {
    int total=0;
    for(TraitTreeBranch branch : _tree.getBranches())
    {
      int ranks=getRanksForRows(branch,10); // Assume max 10 ranks
      int factor=(branch==_selectedBranch)?1:2;
      total+=factor*ranks;
    }
    return total;
  }

  /**
   * Indicates if a cell is enabled or not.
   * @param cellId Identifier of the cell to test.
   * @return <code>true</code> if enabled, <code>false</code> otherwise.
   */
  public boolean isEnabled(String cellId)
  {
    return checkDependencies(cellId) && checkRanksToEnableCell(cellId);
  }

  private boolean checkRanksToEnableCell(String cellId)
  {
    int row=getRowForCell(cellId);
    if (row==1)
    {
      return true;
    }
    TraitTreeBranch branch=getBranchForCell(cellId);
    int neededRanks=branch.getProgression().getSteps().get(row-2).intValue();
    int gotRanks=getRanksForRows(branch,row-1);
    if (LOGGER.isDebugEnabled())
    {
      LOGGER.debug("Cell "+cellId+" got ranks: "+gotRanks+", needed: "+neededRanks);
    }
    return gotRanks>=neededRanks;
  }

  private boolean checkDependencies(String cellId)
  {
    TraitTreeCell cell=getCellById(cellId);
    if (cell==null)
    {
      return false;
    }
    List<TraitTreeCellDependency> dependencies=cell.getDependencies();
    for(TraitTreeCellDependency dependency : dependencies)
    {
      String depCellId=dependency.getCellId();
      TraitTreeCell depCell=getCellById(depCellId);
      TraitDescription trait=depCell.getTrait();
      Integer key=Integer.valueOf(trait.getIdentifier());
      int ranks=_treeRanks.get(key).getInt();
      if (ranks<dependency.getRank())
      {
        if (LOGGER.isDebugEnabled())
        {
          LOGGER.debug("Cell "+cellId+": dependency not met on trait "+trait+": ranks="+ranks+", requires: "+dependency.getRank());
        }
        return false;
      }
    }
    return true;
  }

  private TraitTreeBranch getBranchForCell(String cellId)
  {
    for(TraitTreeBranch branch : _tree.getBranches())
    {
      TraitTreeCell cell=branch.getCell(cellId);
      if (cell!=null)
      {
        return branch;
      }
    }
    return null;
  }

  private TraitTreeCell getCellById(String cellId)
  {
    for(TraitTreeBranch branch : _tree.getBranches())
    {
      TraitTreeCell cell=branch.getCell(cellId);
      if (cell!=null)
      {
        return cell;
      }
    }
    return null;
  }

  private int getRowForCell(String cellId)
  {
    int index=cellId.indexOf('_');
    int y=NumericTools.parseInt(cellId.substring(0,index),0);
    return y;
  }
}
