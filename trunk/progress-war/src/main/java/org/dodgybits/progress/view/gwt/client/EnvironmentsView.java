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
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EnvironmentsView extends EntityView
{
  // properties to fetch for the deploy target
  private final String[] cDeployTargetProperties = new String[] {
    "artifact.name", "target.name", "status", 
    "deploy.build.version", "deploy.contact.name", "comments"
  };
  private final String[] cTableHeaders = new String[] {
      "Artifact", "Host", "Status", "Version", "Contact", "Comments"
  };
  private Panel aPanel;
  
  public EnvironmentsView()
  {
    aEntityInfo = new EntityInfo("Environments", "Current state of deploys for all environments.");
    aPanel = new VerticalPanel();
    aPanel.setWidth("100%");
        
    // add data
    addEnvironments();
    
    initWidget(aPanel);
  }
  
  private void addEnvironments()
  {
    EntityServiceAsync lEntityService = (EntityServiceAsync) GWT.create(EntityService.class);
    ServiceDefTarget lEndpoint = (ServiceDefTarget) lEntityService;
    final String lModuleRelativeURL = getModuleBaseURL() + "gwt/entity";
    lEndpoint.setServiceEntryPoint(lModuleRelativeURL);

    AsyncCallback lCallback = new AsyncCallback() {
      public void onSuccess(Object result) {
        displayData((Map)result);
      }

      public void onFailure(Throwable caught) {
        displayData(getDummyEnvMap());
      }
    };
    lEntityService.getCurrentDeployTargets(cDeployTargetProperties, lCallback);
  }
  
  private Map getDummyEnvMap()
  {
    Map lEnvMap = new HashMap();
    List lTargetDeployProps = new ArrayList();
    String[] lProps = new String[] {"thingie", "rufus", "running", "0.1b", "bob@bubba.com", "yeah yeah"};
    lTargetDeployProps.add(lProps);
    lEnvMap.put("Way Out West", lTargetDeployProps);
    return lEnvMap;
  }
  
  private void displayData(Map pEnvMap)
  {
    for (Iterator it = pEnvMap.entrySet().iterator(); it.hasNext(); )
    {
      Entry lEntry = (Entry) it.next();
      String lEnvName = (String) lEntry.getKey();
      List lDeployTargetProps = (List) lEntry.getValue();
      HTML lTitle = new HTML(lEnvName);
      lTitle.setStyleName("progress-Table-Label");
      aPanel.add(lTitle);
      HTMLTable lTable = new FlexTable();
      lTable.setWidth("100%");
      for (int i = 0; i < cTableHeaders.length; i++)
      {
        lTable.setHTML(0, i, cTableHeaders[i]);
      }
      lTable.getRowFormatter().setStyleName(0, "progress-Table-Header");
      int lRowNum = 1;
      for (Iterator lPropIt = lDeployTargetProps.iterator(); lPropIt.hasNext(); lRowNum++)
      {
        String[] lProps = (String[]) lPropIt.next();
        for (int j = 0; j < lProps.length; j++)
        {
          lTable.setHTML(lRowNum, j, lProps[j]);
        }
        // following line assume Status is 3rd column (ugh)
        lTable.getRowFormatter().addStyleName(lRowNum, "progress-status-" + lProps[2]);
        if (lRowNum % 2 == 0)
        {
          lTable.getRowFormatter().addStyleName(lRowNum, "progress-Table-Even-Row");
        }
        else
        {
          lTable.getRowFormatter().addStyleName(lRowNum, "progress-Table-Odd-Row");
        }
      }
      aPanel.add(lTable);
    }
  }
  
}
