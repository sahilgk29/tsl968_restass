package day1;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import java.util.concurrent.TimeUnit;

public class GitHubExample {
  @Test(enabled = false, description = "Getting all repos")
  public void getAllRepo() {
	  given()
	  	.auth()
	  	.oauth2("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")
	  .when()
	  	.get("https://api.github.com/user/repos")
	  .then()
	   .log()
	   .body()
	   .statusCode(200)
	   .time(Matchers.lessThan(2000L), TimeUnit.MILLISECONDS);
  }
  
  @Test(enabled = true, description = "adding repository")
  public void addRepository() {
	  JSONObject js = new JSONObject();
	  js.put("name", "tsl968-restAssured");
	  js.put("description", "I created my restAssured");
	  js.put("homepage", "https://github.com/sahilgk29");
	  
	  given()
	  	.auth()
	  	.oauth2("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")
	  	.header("Content-Type", "application/json")
	  	.body(js.toJSONString())
	  .when()
	  	.post("https://api.github.com/user/repos")
	  .then()
	   .log()
	   .body()
	   .statusCode(201)
	   .time(Matchers.lessThan(5000L), TimeUnit.MILLISECONDS);
  }
}
