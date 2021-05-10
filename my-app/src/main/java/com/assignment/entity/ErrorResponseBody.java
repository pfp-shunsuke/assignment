package com.assignment.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

import java.time.ZonedDateTime;

@Setter
public class ErrorResponseBody {

    @JsonProperty("timestamp")
    ZonedDateTime exceptionOccurrenceTime;
    @JsonProperty("status")
    int status;
    @JsonProperty("error")
    String error;
    @JsonProperty("message")
    String message;
    @JsonProperty("path")
    String path;
}