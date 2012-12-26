package com.hmsonline.storm.osgi.tuple;

/**
 *
 * @author rmoquin
 */
public class TupleStream implements ITupleStream {
  private String id;
  private ITupleSchema schema;

  /**
   * @return the id
   */
  @Override
  public String getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  @Override
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return the schema
   */
  @Override
  public ITupleSchema getSchema() {
    return schema;
  }

  /**
   * @param schema the schema to set
   */
  @Override
  public void setSchema(ITupleSchema schema) {
    this.schema = schema;
  }
}
