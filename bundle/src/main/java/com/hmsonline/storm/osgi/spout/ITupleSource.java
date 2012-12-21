package com.hmsonline.storm.osgi.spout;

import java.util.Queue;

/**
 *
 * @author rmoquin
 */
public interface ITupleSource {

  /**
   * The queue of tuples produced by this source which will be emitted by the Spout using this tuple source. It is the
   * responsibility of this input source to push tuples to be emitted onto this queue so they will be processed by the
   * spout.
   *
   * @param listener
   */
  void setTupleQueue(Queue queue);
}
