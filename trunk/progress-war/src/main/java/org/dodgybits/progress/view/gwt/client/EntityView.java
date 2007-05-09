package org.dodgybits.progress.view.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;

public abstract class EntityView extends Composite
{
  protected EntityInfo aEntityInfo;
  
  public class EntityInfo 
  {
    public EntityInfo(String pName, String pDescription)
    {
      aName = pName;
      aDescription = pDescription;
    }
    
    private String aName;
    private String aDescription;
    
    public String getName()
    {
      return aName;
    }
    
    public String getDescription()
    {
      return aDescription;
    }
    
  }
  
  public EntityInfo getEntityInfo()
  {
    return aEntityInfo;
  }
  
  /**
   * Called just before this sink is hidden.
   */
  public void onHide() {
  }

  /**
   * Called just after this sink is shown.
   */
  public void onShow() {
  }
  
  protected static String getModuleBaseURL()
  {
    return GWT.getModuleBaseURL();
    // use following for testing in hosted mode
    //return "http://localhost:8081/progress/org.dodgybits.progress.view.gwt.Progress/";
  }
}
