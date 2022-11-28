package utility;

public class Debugger {

  static public void print(String str) {
    System.out.println("==== debug ====");
    System.out.println(str);
  }

  static public void printPause(String str) {
    System.out.println("==== debug ====");
    System.out.println(str);
    System.exit(0);
  }

  static public void error(String info) {
    System.out.println("==== debug ====");
    System.out.println(info);
    assert false;
  }

}
