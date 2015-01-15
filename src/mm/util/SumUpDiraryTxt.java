package mm.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SumUpDiraryTxt {

  public static void main(String[] args) throws FileNotFoundException {
    int nDays = 0;
    float nHours = 0;
    Scanner sc = new Scanner(new File("diary.txt"));
    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      if (line.matches("^[A-Z][a-z]{2} .*")) {
        nDays++;
      }
      if (line.matches(".*\\d\\d:\\d\\d  \\d\\d:\\d\\d.*")) {
        String[] t = line.substring(8, 8+12).split("  ");
        String[] t1 = t[0].split(":");
        int h1 = Integer.parseInt(t1[0]);
        int m1 = Integer.parseInt(t1[1]);
        String[] t2 = t[1].split(":");
        int h2 = Integer.parseInt(t2[0]);
        int m2 = Integer.parseInt(t2[1]);

        if (h1 > h2)
          h2 += 24;
        float h = (h2 - h1) + (m2 - m1) / 60f;
        nHours += h;
        System.out.println(t[0]+" - "+t[1]+" = "+h);
      }
    }
    System.out.println("Total number of hours: "+nHours);
    System.out.println("Total number of days invloved: "+nDays);
  }

}
