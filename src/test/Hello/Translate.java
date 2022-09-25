//Translate.java
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Translate {

	public static void main(String[] args) throws Exception{
		System.out.println("This is a test!");

		ANTLRInputStream input = new ANTLRInputStream(System.in);

		HelloLexer lexer = new HelloLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);


		HelloParser parse = new HelloParser(tokens);

		ParseTree tree = parse.init();

		System.out.println(parse.init().getClass() + " " + tree.getClass()); // debug

		ParseTreeWalker walker = new ParseTreeWalker();

		// 啊
		walker.walk(new ShortToUnicodeString(), tree);
		System.out.println();
	}
}
