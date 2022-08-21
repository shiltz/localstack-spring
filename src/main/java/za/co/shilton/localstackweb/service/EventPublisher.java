package za.co.shilton.localstackweb.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EventPublisher {

  @Autowired
  private AmazonSQS amazonSQS;

  @Autowired
  private ObjectMapper objectMapper;

  @Value("${cloud.aws.end-point.uri}")
  private String sqsUrl;

  @Value("${cloud.aws.queue.name}")
  private String snsQueueName;

  public void publishEvent(JsonNode message) {
    log.info("Generating event : {}", message);
    SendMessageRequest sendMessageRequest = null;
    try {
      sendMessageRequest = new SendMessageRequest().withQueueUrl(sqsUrl + "000000000000/" + snsQueueName)
          .withMessageBody(objectMapper.writeValueAsString(message))
          .withMessageGroupId("za.co.shilton.stock.price.update")
          .withMessageDeduplicationId(UUID.randomUUID().toString());
      amazonSQS.sendMessage(sendMessageRequest);
      log.info("Event has been published in SQS.");
    } catch (JsonProcessingException e) {
      log.error("JsonProcessingException e : {} and stacktrace : {}", e.getMessage(), e);
    } catch (Exception e) {
      log.error("Exception occurred while pushing event to sqs : {} and stacktrace ; {}", e.getMessage(), e);
    }
  }
}
