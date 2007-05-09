package org.dodgybits.progress.view.gwt.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class EditEnvironmentPanel implements ChangeListener
{
  private ListBox aEnvironmentList;
  private ListBox aTargetCombo;
  private FlowPanel aPanel;
  private Map aEnvTargetMap;
  
  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    getEnvMapping();
  }
  
  private void getEnvMapping()
  {
    EntityServiceAsync lEnvService = (EntityServiceAsync) GWT.create(EntityService.class);
    ServiceDefTarget lEndpoint = (ServiceDefTarget) lEnvService;
    final String moduleRelativeURL = GWT.getModuleBaseURL() + "gwt/environment";
    lEndpoint.setServiceEntryPoint(moduleRelativeURL);

    AsyncCallback lCallback = new AsyncCallback() {
      public void onSuccess(Object result) {
        aEnvTargetMap = (Map)result;
        if (result == null)
        {
          aEnvTargetMap = new HashMap();
          List lTargets = new ArrayList();
          lTargets.add("curious");
          aEnvTargetMap.put("empty", lTargets);
        }
        setupMenus();
      }

      public void onFailure(Throwable caught) {
        aEnvTargetMap = new HashMap();
        List lTargets = new ArrayList();
        lTargets.add("bugger: " + moduleRelativeURL);
        aEnvTargetMap.put(caught.getMessage(), lTargets);
        setupMenus();
      }
    };
    lEnvService.getEnvironmentTargetMap(lCallback);
  }
  
  private void setupMenus()
  {
    aEnvironmentList = new ListBox();
    aEnvironmentList.addItem("Select...");
    for (Iterator i = aEnvTargetMap.entrySet().iterator(); i.hasNext(); )
    {
      Entry lEntry = (Entry) i.next();
      List lTargets = (List)lEntry.getValue();
      if (lTargets != null && !lTargets.isEmpty())
      {
        String lEnvName = (String) lEntry.getKey();
        aEnvironmentList.addItem(lEnvName);
      }
    }
    aEnvironmentList.setName("environment");
    aEnvironmentList.addChangeListener(this);
    
    aTargetCombo = new ListBox();
    aTargetCombo.setVisible(false);
    aTargetCombo.setMultipleSelect(true);
    aTargetCombo.setName("target");
    
    aPanel = new FlowPanel();
    aPanel.add(aEnvironmentList);
    aPanel.add(aTargetCombo);
    
    
    
    // Assume that the host HTML has elements defined whose
    // IDs are "slot1", "slot2".  In a real app, you probably would not want
    // to hard-code IDs.  Instead, you could, for example, search for all 
    // elements with a particular CSS class and replace them with widgets.
    //
    RootPanel.get("slot2").add(aPanel);
  }
  
  public void onChange(Widget pSender)
  {
    if (pSender == aEnvironmentList)
    {
      String lEnvName = aEnvironmentList.getValue(aEnvironmentList.getSelectedIndex());
      aTargetCombo.clear();
      List lTargetNames = (List) aEnvTargetMap.get(lEnvName);
      for (Iterator i = lTargetNames.iterator(); i.hasNext(); )
      {
        aTargetCombo.addItem((String) i.next());
      }
      aTargetCombo.setVisible(true);
    }
  }
}
