package org.dodgybits.progress.view.gwt.client;

import org.dodgybits.progress.view.gwt.client.EntityView.EntityInfo;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ProgressEntryPoint implements EntryPoint, HistoryListener {
  protected EntityList aEntityList = new EntityList();
  private EntityInfo aCurrentInfo;
  private EntityView aCurrentView;
  private HTML aDescription = new HTML();
  private DockPanel aPanel = new DockPanel();
  private DockPanel aViewContainer;

  public void onModuleLoad()
  {
    // Load all entity views.
    loadEntityViews();

    // Put the entity view list on the left, and add the outer dock panel to the
    // root.
    aViewContainer = new DockPanel();
    aViewContainer.setStyleName("progress-Entity");

    VerticalPanel vp = new VerticalPanel();
    vp.setWidth("100%");
    vp.add(aDescription);
    vp.add(aViewContainer);

    aDescription.setStyleName("progress-Info");

    aPanel.add(aEntityList, DockPanel.WEST);
    aPanel.add(vp, DockPanel.CENTER);

    aPanel.setCellVerticalAlignment(aEntityList, HasAlignment.ALIGN_TOP);
    aPanel.setCellWidth(vp, "100%");

    History.addHistoryListener(this);
    RootPanel.get().add(aPanel);

    // Show the initial screen.
    String initToken = History.getToken();
    if (initToken.length() > 0) {
      onHistoryChanged(initToken);
    } else {
      onHistoryChanged(aEntityList.getDefaultView().getEntityInfo().getName());
    }
  }

  public void show(EntityView pEntityView, boolean pAffectHistory) {
    // Don't bother re-displaying the existing entity view. This can be an issue
    // in practice, because when the history context is set, our
    // onHistoryChanged() handler will attempt to show the currently-visible
    // entity view.
    if (pEntityView == aCurrentView) {
      return;
    }
    aCurrentInfo = pEntityView.getEntityInfo();

    // Remove the old entity view from the display area.
    if (aCurrentView != null) {
      aCurrentView.onHide();
      aViewContainer.remove(aCurrentView);
    }

    // Get the new entity view instance, and display its description in the
    // entity view list.
    aCurrentView = pEntityView;
    aEntityList.setEntitySelection(aCurrentInfo.getName());
    aDescription.setHTML(aCurrentInfo.getDescription());

    // If affectHistory is set, create a new item on the history stack. This
    // will ultimately result in onHistoryChanged() being called. It will call
    // show() again, but nothing will happen because it will request the exact
    // same entity view we're already showing.
    if (pAffectHistory) {
      History.newItem(aCurrentInfo.getName());
    }

    // Display the new entity view.
    aViewContainer.add(aCurrentView, DockPanel.CENTER);
    aViewContainer.setCellWidth(aCurrentView, "100%");
    aViewContainer.setCellHeight(aCurrentView, "100%");
    aViewContainer.setCellVerticalAlignment(aCurrentView, DockPanel.ALIGN_TOP);
    aCurrentView.onShow();
  }

  /**
   * Adds all entity views to the list. Note that this does not create actual instances
   * of all entity views yet (they are created on-demand). This can make a significant
   * difference in startup time.
   */
  protected void loadEntityViews() {
    aEntityList.addEntity(new LinksView());
    aEntityList.addEntity(new EnvironmentsView());
  }
  
  public void onHistoryChanged(String pHistoryToken)
  {
    // Find the EntityView associated with the history context. If one is
    // found, show it (It may not be found, for example, when the user mis-
    // types a URL, or on startup, when the first context will be "").
    EntityView lEntityView = aEntityList.find(pHistoryToken);
    if (lEntityView == null) 
    {
      lEntityView = aEntityList.getDefaultView();
    }
    show(lEntityView, false);
  }
  
}
