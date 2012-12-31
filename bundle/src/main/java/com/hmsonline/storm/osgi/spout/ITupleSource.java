package com.hmsonline.storm.osgi.spout;

import java.io.Serializable;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author rmoquin
 */
public interface ITupleSource extends Serializable {

  /**
   * The tuple source should execute some logic to, at a minimum, place a tuple on
   * the tuple queue set by the containing component.  This method can be polled by
   * the containing component, or could be invoked by a third party listener (a JMS client for example)
   * with data to process and put onto the tuple queue.
   */
  public void execute();
  
  /**
   * The tuple queue set by the containing component that this source should place
   * tuples on for the component to process.
   *
   * @param queue
   */
  void setTupleQueue(Queue<List<Object>> queue);
}
