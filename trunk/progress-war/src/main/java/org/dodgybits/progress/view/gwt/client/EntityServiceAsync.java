package org.dodgybits.progress.view.gwt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EntityServiceAsync
{
  public void getEnvironmentTargetMap(AsyncCallback pCallback);
  
  /**
   * Return properties about all the current deployments for all environments.
   */
  public void getCurrentDeployTargets(String[] pProperties, AsyncCallback pCallback);

  public void getEnvironmentLinks(String[] pProperties, AsyncCallback pCallback);
  
  public void getDeveloperLinks(String[] pProperties, AsyncCallback pCallback);

}
