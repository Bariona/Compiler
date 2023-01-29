import backend.asm.ASMBuilder;
import backend.asm.ASMPrinter;
import backend.asm.RegAllocator;
import backend.asm.hierarchy.ASMModule;
import frontend.ast.astnode.RootNode;
import frontend.ast.ASTBuilder;
import frontend.ast.SemanticChecker;
import frontend.ir.IRBuilder;
import frontend.ir.IRPrinter;
import frontend.ir.hierarchy.IRModule;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import frontend.parser.MxLexer;
import frontend.parser.MxParser;
import utility.BuiltinPrinter;
import utility.MxErrorListener;
import utility.error.Error;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class Compiler {
  public static void main(String[] args) throws Exception {
    System.out.println("Current directory: " + System.getProperty("user.dir"));

    boolean ONLINE_JUDGE = false;
    String prefix = ONLINE_JUDGE ? "testspace/" : "src/testspace/";
    String filename = prefix + "test.mx";
    String outputFile = prefix + "test.out";
    String irFile = prefix + "test.ll";
    String asmFile = prefix + "test.s";

    InputStream input = (ONLINE_JUDGE) ? System.in : new FileInputStream(filename);

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
      // ASTPrinter printer = new ASTPrinter(new PrintStream(outputFile));
      // printer.visit(root);

      // Semantic Part
      SemanticChecker checker = new SemanticChecker();
      checker.visit(root);

      // LLVM IR
      IRModule irModule = new IRModule("test.ll");
      new IRBuilder(irModule, root);
      new IRPrinter(new PrintStream(irFile)).printModule(irModule);

      // naive Codegen
      ASMModule asmModule = new ASMModule();
      new ASMBuilder(asmModule, irModule);
      if (!ONLINE_JUDGE)
        new ASMPrinter(new PrintStream(prefix + "tmp.s")).printModule(asmModule);

      new RegAllocator().doit(asmModule);
      new ASMPrinter(new PrintStream(asmFile)).printModule(asmModule);

      if (ONLINE_JUDGE) new BuiltinPrinter("builtin.s");
      System.out.println("\033[33m🎉  Done successfully.\033[0m");
    } catch (Error e) {
      e.printStackTrace();
      System.out.println("\033[31m😢 Process terminated with error.\033[0m");
      throw new RuntimeException("Compiling failed.");
    }
  }

}