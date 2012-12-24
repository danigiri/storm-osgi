package com.hmsonline.storm.osgi.spout;

import java.io.Serializable;

/**
 * Represents a polling tuple source for a Storm Spout whose polling logic will be invoked at a configured interval as a Runnable.
 * 
 * @author rmoquin
 */
public interface IPollingTupleSource extends Runnable, ITupleSource, Serializable  {

}
