package com.example.application;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Response.Success.class),
    @JsonSubTypes.Type(value = Response.Failure.class)})
public sealed interface Response {

  record Success(String message) implements Response {
    public static Success of(String message) {
      return new Success(message);
    }
  }

  record Failure(String message) implements Response {
    public static Failure of(String message) {
      return new Failure(message);
    }
  }
}
