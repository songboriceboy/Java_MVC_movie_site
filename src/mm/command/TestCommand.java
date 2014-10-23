package mm.command;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mm.model.*;
import mm.util.Util;

public class TestCommand extends Command {

  public static void main(String[] args) throws ParseException {
    
    List<String> ls = new ArrayList<String>();
    ls.add(null); ls.add("a"); ls.add(null); ls.add(null); 
    System.out.println(ls);
    System.out.println(ls.size());
    
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // MM/dd/yyyy HH:mm:ss
    Date d1 = format.parse("2014-10-23");
    Date d2 = format.parse("2014-10-31");
    System.out.println(Util.getDaysInBetween_Inclusive_AtLeast7(d1, d2));
  }
  
  public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    List<Pair<Integer,String>> pairs = new ArrayList<Pair<Integer,String>>();
    pairs.add(new Pair<Integer,String>(1,"one"));
    pairs.add(new Pair<Integer,String>(2,"two"));
    pairs.add(new Pair<Integer,String>(3,"three"));
    pairs.add(new Pair<Integer,String>(4,"four"));
    request.setAttribute("pairs", pairs);
    System.out.println(pairs);

    return "/_test.jsp";
  }
}
