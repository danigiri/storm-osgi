package com.hmsonline.storm.starter.source;

import com.hmsonline.storm.osgi.spout.ITupleSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import javax.annotation.PostConstruct;

/**
 * An IPollingTupleSource which puts a random sentence on it's Spout's tuple
 * queue each poll interval.
 *
 * @org.apache.xbean.XBean element="randomSentence" description="A ITupleSource
 * which randomly puts one of the configured sentences onto it's Spout's tuple
 * queue on each call of execute."
 *
 * @author rmoquin
 */
public class RandomSentence implements ITupleSource {

  private Random rand;
  private String[] sentences;
  private Queue<List<Object>> queue = null;

  @PostConstruct
  public void init() {
    rand = new Random();
  }

  @Override
  public void execute() {
    String sentence = sentences[rand.nextInt(sentences.length)];
    List<Object> l = new ArrayList<Object>();
    l.add(sentence);
    this.queue.add(l);
  }

  @Override
  public void setTupleQueue(Queue<List<Object>> queue) {
    this.queue = queue;
  }

  /**
   * @return the sentences
   */
  public String[] getSentences() {
    return sentences;
  }

  /**
   * @param sentences the sentences to set
   */
  public void setSentences(String[] sentences) {
    this.sentences = sentences;
  }
}
