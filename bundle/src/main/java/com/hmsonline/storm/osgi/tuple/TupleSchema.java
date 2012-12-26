package com.hmsonline.storm.osgi.tuple;

/**
 * Specifies a collection of fields that is outputted by a topology component.
 *
 * @org.apache.xbean.XBean element="schema" description="Specifies a collection of fields that is outputted by a topology component."
 *
 * @author rmoquin
 */
public class TupleSchema implements ITupleSchema{
  private String[] fields;

  /**
   * @return the fields
   */
  @Override
  public String[] getFields() {
    return fields;
  }

  /**
   * @param fields the fields to set
   */
  public void setFields(String[] fields) {
    this.fields = fields;
  }
}
