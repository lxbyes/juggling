package me.lceckie.juggling.box.stream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class StreamSample {

  private List<String> stringList;

  public StreamSample() {
    int max = 20000000;
    stringList = new ArrayList<>(max);
    for (int i = 0; i < max; i++) {
      stringList.add(UUID.randomUUID().toString());
    }
  }

  public void sequentialSort() {
    long start = System.nanoTime();
    long count = stringList.stream().sorted().count();
    long end = System.nanoTime();
    System.out.println(String.format("sequential sort took: %s millis", TimeUnit.NANOSECONDS.toMillis(end - start)));
  }

  public void paralleSort() {
    long start = System.nanoTime();
    long count = stringList.parallelStream().sorted().count();
    long end = System.nanoTime();
    System.out.println(String.format("paralle sort took: %s millis", TimeUnit.NANOSECONDS.toMillis(end - start)));
  }

  public static void main(String[] args) throws IOException {
    StreamSample sample = new StreamSample();
    sample.sequentialSort();
    // sample.paralleSort();
    System.in.read();
  }

}
