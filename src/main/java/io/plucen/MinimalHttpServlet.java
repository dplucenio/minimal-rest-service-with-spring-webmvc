package io.plucen;

import static io.plucen.utils.HttpMethods.GET;
import static io.plucen.utils.HttpMethods.POST;

import io.plucen.controllers.Controller;
import io.plucen.controllers.RawDashboardController;
import io.plucen.controllers.RawStudentsController;
import io.plucen.utils.HttpMethods;
import io.plucen.utils.Pair;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MinimalHttpServlet extends HttpServlet {
  private final Map<Pair<String, HttpMethods>, Controller> controllers;
  private final RawDashboardController rawDashboardController;
  private final RawStudentsController rawStudentsController;

  @Autowired
  MinimalHttpServlet(
      RawDashboardController rawDashboardController, RawStudentsController rawStudentsController) {
    this.rawDashboardController = rawDashboardController;
    this.rawStudentsController = rawStudentsController;
    controllers =
        Map.of(
            Pair.of("/", GET), rawDashboardController::get,
            Pair.of("/students", GET), rawStudentsController::get,
            Pair.of("/students", POST), rawStudentsController::post);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String url = request.getRequestURI().toLowerCase();
    controllers.getOrDefault(Pair.of(url, GET), (req, res) -> {}).execute(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String url = request.getRequestURI().toLowerCase();
    controllers.getOrDefault(Pair.of(url, POST), (req, res) -> {}).execute(request, response);
  }
}
