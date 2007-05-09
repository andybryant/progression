package org.dodgybits.progress.view.gwt.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * The left panel that contains all of the entities, along with a short description
 * of each.
 */
public class EntityList extends Composite {

  private VerticalPanel aList = new VerticalPanel();
  private List aEntities = new ArrayList();
  private int aSelectedEntity = -1;

  public EntityList() {
    initWidget(aList);
    setStyleName("progress-List");
  }

  public void addEntity(final EntityView pEntityView) {
    String lName = pEntityView.getEntityInfo().getName();
    Hyperlink lLink = new Hyperlink(lName, lName);
    lLink.setStyleName("progress-EntityItem");

    aList.add(lLink);
    aEntities.add(pEntityView);
  }

  public EntityView find(String pEntityName) {
    for (int i = 0; i < aEntities.size(); ++i) {
      EntityView lEntityView = (EntityView) aEntities.get(i);
      if (lEntityView.getEntityInfo().getName().equals(pEntityName)) {
        return lEntityView;
      }
    }

    return null;
  }
  
  public EntityView getDefaultView()
  {
    return (EntityView) aEntities.get(0);
  }

  public void setEntitySelection(String pEntityName) {
    if (aSelectedEntity != -1) {
      aList.getWidget(aSelectedEntity).removeStyleName("progress-EntityItem-selected");
    }
    
    for (int i = 0; i < aEntities.size(); ++i) {
      EntityView lEntityView = (EntityView) aEntities.get(i);
      if (lEntityView.getEntityInfo().getName().equals(pEntityName)) {
        aSelectedEntity = i;
        aList.getWidget(aSelectedEntity).addStyleName("progress-EntityItem-selected");
        break;
      }
    }
  }
  
  
}
