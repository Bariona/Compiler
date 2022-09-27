//ShortToUnicodeString.java
public class ShortToUnicodeString extends HelloBaseListener {
    @Override
    public void enterInit(HelloParser.InitContext ctx){
        System.out.print('"');
    }

    @Override
    public void exitInit(HelloParser.InitContext ctx){
        System.out.print('"');
    }

    @Override
    public void enterValue(HelloParser.ValueContext ctx){
        int value=Integer.valueOf(ctx.INT().getText());
        System.out.printf("\\u%04x",value);
    }
}
