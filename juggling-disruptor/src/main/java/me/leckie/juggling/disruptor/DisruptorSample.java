package me.leckie.juggling.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.TimeoutException;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import java.util.Scanner;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author Leckie
 * @version $Id: DisruptorSample.java, v0.1 2019/5/21 14:03 john Exp $$
 */
public class DisruptorSample {

  static class MessageEvent {

    private String message;

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }
  }

  static class MessageEventFactory implements EventFactory<MessageEvent> {

    @Override
    public MessageEvent newInstance() {
      return new MessageEvent();
    }
  }

  static class MessageEventTranslator implements EventTranslatorOneArg<MessageEvent, String> {

    @Override
    public void translateTo(MessageEvent messageEvent, long l, String s) {
      messageEvent.setMessage(s);
    }
  }

  static class MessageThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
      return new Thread(r, "Disruptor-thread-" + (++threadCount));
    }
  }

  static class MessageEventHandler implements EventHandler<MessageEvent> {

    @Override
    public void onEvent(MessageEvent messageEvent, long l, boolean b) throws Exception {
      // delay the consumer
      Thread.currentThread().sleep(500);
      System.out.println(Thread.currentThread().getName() + ":" + messageEvent.getMessage());
    }
  }

  static class MessageExceptionHandler implements ExceptionHandler<MessageEvent> {

    @Override
    public void handleEventException(Throwable throwable, long l, MessageEvent messageEvent) {
      throwable.printStackTrace();
    }

    @Override
    public void handleOnStartException(Throwable throwable) {
      throwable.printStackTrace();
    }

    @Override
    public void handleOnShutdownException(Throwable throwable) {
      throwable.printStackTrace();
    }
  }

  static class MessageEventProducer {

    private RingBuffer<MessageEvent> ringBuffer;

    public MessageEventProducer(RingBuffer<MessageEvent> ringBuffer) {
      this.ringBuffer = ringBuffer;
    }

    public void onData(String message) {
      EventTranslatorOneArg<MessageEvent, String> translator = new MessageEventTranslator();
      ringBuffer.publishEvent(translator, message);
    }
  }

  private final static String EXIT_ORDER = "EXIT";

  private static int threadCount = 0;

  public static void main(String[] args) throws TimeoutException {
    int ringBufferSize = 2 ^ 10;
    Disruptor<MessageEvent> disruptor = new Disruptor<>(new MessageEventFactory(), ringBufferSize,
        new MessageThreadFactory(),
        ProducerType.SINGLE, new BlockingWaitStrategy());
    disruptor.handleEventsWith(new MessageEventHandler());
    disruptor.setDefaultExceptionHandler(new MessageExceptionHandler());
    RingBuffer<MessageEvent> ringBuffer = disruptor.start();
    MessageEventProducer producer = new MessageEventProducer(ringBuffer);
    while (true) {
      Scanner scanner = new Scanner(System.in);
      String message = scanner.nextLine();
      if (EXIT_ORDER.equalsIgnoreCase(message)) {
        System.out.println("Disruptor will exit.");
        disruptor.shutdown(2L, TimeUnit.SECONDS);
        break;
      }
      producer.onData(message);
    }
  }
}
