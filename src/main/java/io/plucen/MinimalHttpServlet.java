package io.plucen;

import static io.plucen.utils.HttpMethods.GET;
import static io.plucen.utils.HttpMethods.POST;

import io.plucen.controllers.Controller;
import io.plucen.controllers.DashboardController;
import io.plucen.controllers.StudentsController;
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
  private final DashboardController dashboardController;
  private final StudentsController studentsController;

  @Autowired
  MinimalHttpServlet(
      DashboardController dashboardController, StudentsController studentsController) {
    this.dashboardController = dashboardController;
    this.studentsController = studentsController;
    controllers =
        Map.of(
            Pair.of("/", GET), dashboardController::get,
            Pair.of("/students", GET), studentsController::get,
            Pair.of("/students", POST), studentsController::post);
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
