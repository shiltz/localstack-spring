package za.co.shilton.localstackweb.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@WebFluxTest
class StockExchangeControllerTest {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void getShareprice() {
    webTestClient.get()
        .uri("/api/shareprice/{sharecode}", "jse")
        .exchange()
        .expectBody()
        .json("10");
  }
}