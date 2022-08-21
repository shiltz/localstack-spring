package za.co.shilton.localstackweb.service;

import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Consumer {

  @SqsListener("${cloud.aws.queue.name}")
  public void receiveMessage(String message) {
    log.info("SQS Message Received : {}", message);
  }
}
