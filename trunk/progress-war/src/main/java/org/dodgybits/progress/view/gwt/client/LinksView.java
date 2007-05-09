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

public class LinksView extends EntityView
{
  // properties to fetch for the environment links
  private final String[] cEnvLinkProperties = new String[] {
    "environment.name", "url", "name", 
  };
  private final String[] cEnvTableHeaders = new String[] {
    "Environment", "Link", "Description"
  };
  // properties to fetch for the developer links
  private final String[] cDevLinkProperties = new String[] {
    "name", "url",  
  };
  private Panel aEnvPanel;
  private Panel aDevPanel;

  public LinksView()
  {
    aEntityInfo = new EntityInfo("Links", "Useful environment and developer links.");
    VerticalPanel lPanel = new VerticalPanel();
    lPanel.setWidth("100%");

    aEnvPanel = new VerticalPanel();
    aEnvPanel.setWidth("100%");
    lPanel.add(aEnvPanel);
    aDevPanel = new VerticalPanel();
    aDevPanel.setWidth("100%");
    lPanel.add(aDevPanel);

    // add data
    addEnvironmentLinks();
    addDeveloperLinks();
    
    initWidget(lPanel);
  }
  
  private void addEnvironmentLinks()
  {
    HTML lTitle = new HTML("Environment Links");
    lTitle.setStyleName("progress-Table-Label");
    aEnvPanel.add(lTitle);

    final HTMLTable lTable = new FlexTable();
    lTable.setWidth("100%");
    for (int i = 0; i < cEnvTableHeaders.length; i++)
    {
      lTable.setHTML(0, i, cEnvTableHeaders[i]);
    }
    lTable.getRowFormatter().setStyleName(0, "progress-Table-Header");
    aEnvPanel.add(lTable);
    
    EntityServiceAsync lEntityService = (EntityServiceAsync) GWT.create(EntityService.class);
    ServiceDefTarget lEndpoint = (ServiceDefTarget) lEntityService;
    final String lModuleRelativeURL = getModuleBaseURL() + "gwt/entity";
    lEndpoint.setServiceEntryPoint(lModuleRelativeURL);

    AsyncCallback lCallback = new AsyncCallback() {
      public void onSuccess(Object result) {
        displayEnvData((Map)result, lTable);
      }

      public void onFailure(Throwable caught) {
        displayEnvData(getDummyEnvMap(), lTable);
      }
    };
    lEntityService.getEnvironmentLinks(cEnvLinkProperties, lCallback);
  }
  
  private Map getDummyEnvMap()
  {
    Map lEnvMap = new HashMap();
    List lLinkProps = new ArrayList();
    String[] lProps = new String[] {"Optus UAT", "http://1.2.3/", "Going to nowhere"};
    lLinkProps.add(lProps);
    lProps = new String[] {"Optus UAT", "http://1.2.4/", "Going to somewhere"};
    lLinkProps.add(lProps);
    lEnvMap.put("Optus UAT", lLinkProps);
    lLinkProps = new ArrayList();
    lProps = new String[] {"Optus prod", "http://1.2.44/", "Going blue"};
    lLinkProps.add(lProps);
    lEnvMap.put("Optus prod", lLinkProps);
    return lEnvMap;
  }
  
  private void displayEnvData(Map pEnvData, HTMLTable pTable)
  {
    for (Iterator it = pEnvData.values().iterator(); it.hasNext(); )
    {
      List lLinkProps = (List) it.next();
      int lRowNum = 1;
      for (Iterator lPropIt = lLinkProps.iterator(); lPropIt.hasNext(); lRowNum++)
      {
        String[] lProps = (String[]) lPropIt.next();
        for (int j = 0; j < lProps.length; j++)
        {
          pTable.setHTML(lRowNum, j, lProps[j]);
        }
        if (lRowNum % 2 == 0)
        {
          pTable.getRowFormatter().addStyleName(lRowNum, "progress-Table-Even-Row");
        }
        else
        {
          pTable.getRowFormatter().addStyleName(lRowNum, "progress-Table-Odd-Row");
        }
      }
    }
  }
  
  private void addDeveloperLinks()
  {
    HTML lTitle = new HTML("<br/>Developer Links");
    lTitle.setStyleName("progress-Table-Label");
    aDevPanel.add(lTitle);

    EntityServiceAsync lEntityService = (EntityServiceAsync) GWT.create(EntityService.class);
    ServiceDefTarget lEndpoint = (ServiceDefTarget) lEntityService;
    final String lModuleRelativeURL = getModuleBaseURL() + "gwt/entity";
    lEndpoint.setServiceEntryPoint(lModuleRelativeURL);

    AsyncCallback lCallback = new AsyncCallback() {
      public void onSuccess(Object result) {
        displayDevData((Map)result);
      }

      public void onFailure(Throwable caught) {
        displayDevData(getDummyDevMap());
      }
    };
    lEntityService.getDeveloperLinks(cDevLinkProperties, lCallback);
  }
  
  private Map getDummyDevMap()
  {
    Map lEnvMap = new HashMap();
    List lLinkProps = new ArrayList();
    String[] lProps = new String[] {"Going home", "http://google.com/", };
    lLinkProps.add(lProps);
    lProps = new String[] {"what's up", "http://apple.com/", };
    lLinkProps.add(lProps);
    lEnvMap.put("Tech", lLinkProps);
    lLinkProps = new ArrayList();
    lProps = new String[] {"Java home", "http://java.sun.com/", };
    lLinkProps.add(lProps);
    lEnvMap.put("Java", lLinkProps);
    return lEnvMap;

  }
  
  private void displayDevData(Map pDevData)
  {
    for (Iterator it = pDevData.entrySet().iterator(); it.hasNext(); )
    {
      Entry lEntry = (Entry) it.next();
      String lTopicName = (String) lEntry.getKey();
      List lDevLinkProps = (List) lEntry.getValue();
      HTML lTitle = new HTML(lTopicName);
      lTitle.setStyleName("progress-Table-Label");
      aDevPanel.add(lTitle);
      FlexTable lTable = new FlexTable();
      lTable.setWidth("100%");
      lTable.setHTML(0, 0, lTopicName);
      lTable.getFlexCellFormatter().setColSpan(0, 0, 2);
      lTable.getRowFormatter().setStyleName(0, "progress-Table-Header");
      int lRowNum = 1;
      for (Iterator lPropIt = lDevLinkProps.iterator(); lPropIt.hasNext(); lRowNum++)
      {
        String[] lProps = (String[]) lPropIt.next();
        for (int j = 0; j < lProps.length; j++)
        {
          lTable.setHTML(lRowNum, j, lProps[j]);
        }
        if (lRowNum % 2 == 0)
        {
          lTable.getRowFormatter().addStyleName(lRowNum, "progress-Table-Even-Row");
        }
        else
        {
          lTable.getRowFormatter().addStyleName(lRowNum, "progress-Table-Odd-Row");
        }
      }
      aDevPanel.add(lTable);
    }
  }
  
}
