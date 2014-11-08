package mm.util;

import java.util.HashMap;
import java.util.Map;

import mm.model.Pair;

public class Constants {
  
  public static final Pair[] tspA = { // timeslot pair array (TIMESLOT_KEY, DISPLAY_STRING), Pair<String,String>
    new Pair("0900","9:00am"), new Pair("1200","12:00pm"), new Pair("1500","3:00pm"), new Pair("1800","6:00pm") 
  };
  
  public static final Map<String,String> tsMap = new HashMap<String,String>() {{
    put("0900","9:00am"); put("1200","12:00pm"); put("1500","3:00pm"); put("1800","6:00pm");
  }};

}
