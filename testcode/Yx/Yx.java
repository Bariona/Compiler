import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.*;

import java.io.*;

import antlrYx.*;

import utility.YxErrorListener;
import utility.Error;

public class Yx {
  public static void main(String[] args) throws Exception {
    System.out.println("Current directory: " + System.getProperty("user.dir"));

    String filename = "src/test/Yx/testcases/testyx.txt";
    InputStream input = new FileInputStream(filename);

    try {
      YxLexer lexer = new YxLexer(CharStreams.fromStream(input)); // lexer
      lexer.removeErrorListeners();
      lexer.addErrorListener(new YxErrorListener());

      CommonTokenStream tokens = new CommonTokenStream(lexer);
      YxParser parser = new YxParser(tokens); // parser
      parser.removeErrorListeners();
      parser.addErrorListener(new YxErrorListener());
      ParseTree tree = parser.program();

      System.out.println("\033[33m🎉  Done successfully.\033[0m");
    } catch (Error e) {
      e.printStackTrace();
      System.out.println("\033[31m😢 Process terminated with error.\033[0m");
      throw new RuntimeException("Compiling failed.");
    }
    System.out.println("!");
  }
}
