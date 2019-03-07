package me.leckie.juggling.mvel;

import java.util.HashMap;
import java.util.Map;
import org.mvel2.MVEL;

/**
 * @author Leckie
 * @version $Id: MvelTests.java, v0.1 2018/12/27 16:45 john Exp $$
 */
public class MvelTests {

  public static void main(String[] args) {
    String expression = "a == ratingRequest.id && b == empty;ratingRequest;3";
    Map<String, Object> paramMap = new HashMap<>();
    Map<String, Object> ratingRequest = new HashMap<>();
    // ratingRequest.put("id", "12345");
    paramMap.put("a", "12345");
    paramMap.put("b", null);
    paramMap.put("ratingRequest", ratingRequest);
    Object object = MVEL.eval("ratingRequest.?id==nil?a:2", paramMap);
    System.out.println(object);
  }
}
