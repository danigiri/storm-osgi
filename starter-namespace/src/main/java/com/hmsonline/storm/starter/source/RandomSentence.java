package com.hmsonline.storm.starter.source;

import com.hmsonline.storm.osgi.spout.ITupleSource;
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
  private List<String> sentences;
  private Queue queue = null;

  @PostConstruct
  public void init() {
    rand = new Random();
  }

  @Override
  public void execute() {
    String sentence = sentences.get(rand.nextInt(sentences.size()));
    this.queue.add(sentence);
  }

  @Override
  public void setTupleQueue(Queue queue) {
    this.queue = queue;
  }

  /**
   * @return the sentences
   */
  public List<String> getSentences() {
    return sentences;
  }

  /**
   * @param sentences the sentences to set
   */
  public void setSentences(List<String> sentences) {
    this.sentences = sentences;
  }
}
