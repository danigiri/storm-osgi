package com.hmsonline.storm.osgi.spout;

/**
 * Represents a polling tuple source for a Storm Spout whose polling logic will be invoked at a configured interval as a Runnable.
 * 
 * @author rmoquin
 */
public interface IPollingTupleSource extends Runnable, ITupleSource {

}
