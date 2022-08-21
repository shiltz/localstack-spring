package za.co.shilton.localstackweb.controller;

import java.math.BigDecimal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StockExchangeController {

  @GetMapping("/shareprice/{shareCode}")
  public BigDecimal getShareprice(@PathVariable String shareCode) {
    return BigDecimal.TEN;
  }
}
