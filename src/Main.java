import ast.RootNode;
import frontend.ASTBuilder;

import frontend.ASTPrinter;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.*;

import java.io.*;

import parser.*;
import utility.MxErrorListener;
import utility.error.Error;

public class Main {
  public static void main(String[] args) throws Exception {
    System.out.println("Current directory: " + System.getProperty("user.dir"));

    String filename = "testcases/data.in";
    String outputFile = "data.out";
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
      ASTBuilder ast = new ASTBuilder(); // new visitor for AST making
      RootNode root = (RootNode) ast.visit(tree);

      // Debug Printer
      ASTPrinter printer = new ASTPrinter(new PrintStream(outputFile));
      printer.visit(root);

      // Semantic Part

      System.out.println("\033[33m🎉  Done successfully.\033[0m");
    } catch (Error e) {
      e.printStackTrace();
      System.out.println("\033[31m😢 Process terminated with error.\033[0m");
      throw new RuntimeException("Compiling failed.");
    }
//    System.out.println("!");
  }
}