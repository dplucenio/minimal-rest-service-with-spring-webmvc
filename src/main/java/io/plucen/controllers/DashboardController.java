package io.plucen.controllers;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DashboardController {

  @GetMapping("/")
  @ResponseBody
  public Response index() {
    return new Response("hello spring Controller!");
  }

  @Data
  private static class Response {
    private final String message;
  }
}
