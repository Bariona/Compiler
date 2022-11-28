import frontend.ASTPrinter;
import frontend.ast.RootNode;
import frontend.ASTBuilder;
import frontend.SemanticChecker;
import middleend.IRBuilder;
import middleend.IRPrinter;
import middleend.hierarchy.IRModule;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import frontend.parser.MxLexer;
import frontend.parser.MxParser;
import utility.MxErrorListener;
import utility.error.Error;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class Compiler {
  public static void main(String[] args) throws Exception {
    System.out.println("Current directory: " + System.getProperty("user.dir"));

    String filename = "testspace/data.in";
    String outputFile = "testspace/data.out";
    String irFile = "testspace/data.ll";

    InputStream input = new FileInputStream(filename);
//    InputStream input = System.in;

    try {
      MxLexer lexer = new MxLexer(CharStreams.fromStream(input)); // lexer
      lexer.removeErrorListeners();
      lexer.addErrorListener(new MxErrorListener());
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      MxParser parser = new MxParser(tokens); // frontend.parser
      parser.removeErrorListeners();
      parser.addErrorListener(new MxErrorListener());

      ParseTree tree = parser.program();
      ASTBuilder ast = new ASTBuilder(); // new visitor for AST making
      RootNode root = (RootNode) ast.visit(tree);

      // Print AST
      ASTPrinter printer = new ASTPrinter(new PrintStream(outputFile));
      printer.visit(root);

      // Semantic Part
      SemanticChecker checker = new SemanticChecker();
      checker.visit(root);

      IRModule module = new IRModule("data.ll");
      IRBuilder irBuilder = new IRBuilder(module, root);
      IRPrinter irPrinter = new IRPrinter(new PrintStream(irFile));
      irPrinter.printModule(module);

      System.out.println("\033[33m🎉  Done successfully.\033[0m");
    } catch (Error e) {
      e.printStackTrace();
      System.out.println("\033[31m😢 Process terminated with error.\033[0m");
      throw new RuntimeException("Compiling failed.");
    }
//    System.out.println("!");
  }
}