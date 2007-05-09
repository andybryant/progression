package org.dodgybits.progress.view.gwt.client;

import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;

public interface EntityService extends RemoteService
{
  /**
   * Returns list of environment names mapped to target names.
   * 
   * @gwt.typeArgs <java.lang.String,java.util.List<java.lang.String>>
   */
  public Map getEnvironmentTargetMap();
  
  /**
   * Return properties about all the current deployments for all environments.
   * 
   * @param pProperties list of properties to retrieve (may be nested)
   * @return a map from environment name to a list of String[] containing properties specified for each <code>DeployTarget</code>
   * @gwt.typeArgs <java.lang.String,java.util.List<java.lang.String[]>>
   */
  public Map getCurrentDeployTargets(String[] pProperties);
  
  /**
   * Return properties about all the current links for all environments.
   * 
   * @param pProperties list of properties to retrieve (may be nested)
   * @return a map from environment name to a list of String[] containing properties specified for each <code>Link</code>
   * @gwt.typeArgs <java.lang.String,java.util.List<java.lang.String[]>>
   */
  public Map getEnvironmentLinks(String[] pProperties);
  
  /**
   * Return properties about all the developer links sorted by topic.
   * 
   * @param pProperties list of properties to retrieve (may be nested)
   * @return a map from topic name to a list of String[] containing properties specified for each <code>Link</code>
   * @gwt.typeArgs <java.lang.String,java.util.List<java.lang.String[]>>
   */
  public Map getDeveloperLinks(String[] pProperties);
}
