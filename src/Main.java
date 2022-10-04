import AST.RootNode;
import Frontend.ASTBuilder;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.*;
import java.io.*;

import Parser.*;
import Utility.MxErrorListener;
import Utility.Error.Error;

public class Main {
  public static void main(String[] args) throws Exception {
    System.out.println("Current directory: " + System.getProperty("user.dir"));

    String filename = "testcases/data.in";
    InputStream input = new FileInputStream(filename);

    try {
      MxLexer lexer = new MxLexer(CharStreams.fromStream(input)); // lexer
      lexer.removeErrorListeners();
      lexer.addErrorListener(new MxErrorListener());

      CommonTokenStream tokens = new CommonTokenStream(lexer);

      MxParser parser = new MxParser(tokens); // parser
      parser.removeErrorListeners();
      parser.addErrorListener(new MxErrorListener());

      ParseTree tree = parser.program();
      ASTBuilder ast = new ASTBuilder();
      RootNode root = (RootNode) ast.visit(tree);

      System.out.println("\033[33m🎉  Done successfully.\033[0m");
    } catch (Error e) {
      e.printStackTrace();
      System.out.println("\033[31m😢 Process terminated with error.\033[0m");
      throw new RuntimeException("Compiling failed.");
    }
//    System.out.println("!");
  }
}