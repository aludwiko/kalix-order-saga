package com.example.application.choreography;

import com.example.application.PaymentProcessorEntity;
import com.example.application.Response;
import com.example.domain.PaymentProcessorEvent.PaymentSucceeded;
import kalix.javasdk.action.Action;
import kalix.javasdk.annotations.Subscribe;
import kalix.spring.KalixClient;
import org.springframework.context.annotation.Profile;

@Profile("choreography")
@Subscribe.EventSourcedEntity(value = PaymentProcessorEntity.class, ignoreUnknown = true)
public class ConfirmOrderAction extends Action {

  private final KalixClient kalixClient;

  public ConfirmOrderAction(KalixClient kalixClient) {
    this.kalixClient = kalixClient;
  }

  public Effect<Response> onEvent(PaymentSucceeded paymentSucceeded) {
    return effects().forward(kalixClient.patch("/order/" + paymentSucceeded.orderId() + "/confirm", Response.class));
  }
}
