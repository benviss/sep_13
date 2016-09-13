import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.ArrayList;
// import java.util.Arrays.asList;
import java.util.Arrays;


public class App {
  public static void main(String[] args) {
    Map<String, ArrayList<String>> genreBand = new HashMap<String, ArrayList<String>>();
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

  get("/", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("template", "templates/index.vtl");
    model.put("bands", request.session().attribute("bands"));
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());

  get("/refresh", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/refresh.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


  post("/bands", (request, response) -> {
    Map<String, Object> model = new HashMap<String, Object>();

    ArrayList<CD> bands = request.session().attribute("bands");
    if (bands == null) {
      bands = new ArrayList<CD>();
      request.session().attribute("bands", bands);
    }

    String genre = request.queryParams("genre");
    String description = request.queryParams("band");
    CD newCD = new CD(description, genre);
    bands.add(newCD);

    if (genreBand.get(genre) == null) {
      genreBand.put(genre, new ArrayList<String>());
    }

    genreBand.get(genre).add(newCD);
    System.out.println(genreBand);
    Set<String> keys = genreBand.keySet();
    for (String i: keys) {
      System.out.println(genreBand.get(i));
    }
    model.put("genreBand", genreBand);
    model.put("genre", genreBand.keySet());
    model.put("template", "templates/index.vtl");
    return new ModelAndView(model, layout);
  }, new VelocityTemplateEngine());




  }
}
