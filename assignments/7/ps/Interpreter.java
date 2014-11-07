import structure5.SetVector;
import structure5.Hashtable;
import structure5.List;
import structure5.StackList;

import java.util.Iterator;
import java.util.Scanner;


public class Interpreter {
  
  private static boolean debug = false;

  private static final boolean DEMO = false;
  public DemoInterpreter di;

  protected static enum TokenType {
    INTEGER, DOUBLE, STRING, SYMBOL, BOOLEAN, PROCEDURE, INSTRUCTION
  }

  protected StackList<Token> stack = new StackList<Token>();
  private SymbolTable userSymbols = new SymbolTable();
  private Hashtable<String, Instruction> systemSymbols = new Hashtable<String, Instruction>(){{
    put("pstack", new PrintStack());
    put("quit", new Quit());
    put("sub", new Subtract());
    put("pop", new Pop());
    put("dup", new Duplicate());
    put("exch", new Exchange());
    put("ptable", new PrintTable());
    put("def", new Define());
    put("add", new Add());
    put("mul", new Multiply());
    put("div", new Divide());
    put("eq", new Equals());
    put("ne", new NotEquals());
    put("lt", new LessThan());
    put("gt", new GreaterThan());
    put("not", new Not());
    put("if", new If());
  }};


  public void interpretLine(Iterator<Token> cmd) {
    

    while (cmd.hasNext()) {
      Token token = cmd.next();

      addToStack(token);
      if (debug)
        System.out.println("Token (" + token.kind() + "): " + token);
    }
    
  }

  protected void addToStack(Token token) {
    switch (getTokenType(token)) {
      case INSTRUCTION:
        executeInstruction(token.getSymbol());
        break;
      default:
        stack.push(token);
    }
  }


  protected void executeInstruction(String symbol) {
    if (userSymbols.contains(symbol)) {
      Token returnItem = userSymbols.get(symbol);
      addReturnItem(returnItem);
      return;
    }
    if (systemSymbols.containsKey(symbol)) {
      Instruction instruction = systemSymbols.get(symbol);
      instruction.execute(this);
    }

  }

  /**
   * If an item is returned by an an instruction, it can't just be added to
   * the stack. Instead, if it is a procedure, each item needs to be added
   * separately
   * @param value the token returned by the instruction
   */
  private void addReturnItem(Token value) {
    if (value.isProcedure()) {
      for (Token procedureValue : value.getProcedure().values()) {
        addToStack(procedureValue);
      }
    } else {
      addToStack(value);
    }
  }
  /**
   * The token class has some pretty crappy types. First off, they are not
   * `enum`s, but integers, so that's no fun to work with. Second, they don't
   * differentiate between instructions (`if`, `dup`, `pi`) and symbols (`\somethign`).
   * So this method analyzes a token to find it's more specific type and returns that.
   * @param token the token to analyze
   * @return the type of the token
   */
  protected static TokenType getTokenType(Token token) {
    // cant use the defined number variables from `token` because
    // switch requires a constant value
    switch(token.kind()) {
      case 1: // NUMBER_KIND
        if (isInteger(token.getNumber())) {
          return TokenType.INTEGER;
        }
        return TokenType.DOUBLE;
      case 2: // BOOLEAN_KIND
        return TokenType.BOOLEAN;
      case 3: // SYMBOL_KIND
        if (isSymbol(token.getSymbol())) {
          return TokenType.SYMBOL;
        }
        return TokenType.INSTRUCTION;
      case 4: // PROCEDURE_KIND
        return TokenType.PROCEDURE;
    }
    assert false;
    return null;
  }

  private static boolean isInteger(Double n) {
    return n % 10 == 0;
  }

  private static boolean isSymbol(String s) {
    return s.startsWith("/");
  }

  private void printPrompt() {
    if (stack.isEmpty()) {
      System.out.print("PS> ");
    } else {
      System.out.print("PS<" + Integer.toString(stack.size()) + "> ");
    }

  }
  
  public static void main(String[] args)
  {
    
    if (DEMO) 
    {
      System.out.println("Running demo");
      new DemoInterpreter();
    }
    else 
    { 
      if (args.length > 0) 
        if (args[0].equals("-debug")) 
           debug = true;
      
      Interpreter inter = new Interpreter();
      
      Scanner sc = new Scanner(System.in);
      
      inter.printPrompt();
      
      while (sc.hasNextLine())
      {
        inter.interpretLine(new Reader(sc.nextLine()));
        inter.printPrompt();
      }
    }
    
  }
}

abstract class Instruction {
  abstract void execute(Interpreter interpreter);
}

class PrintStack extends Instruction {
  @Override
  void execute(Interpreter interpreter) {
    for (Token item : interpreter.stack) {
      System.out.println(item.toString());
    }
  }
}

class Quit extends Instruction {
  @Override
  void execute(Interpreter interpreter) {
    System.exit(0);
  }
}

abstract class RequiresItems extends Instruction {
  abstract List<Interpreter.TokenType> getRequiredTypes();
  abstract void execute(Iterator<Token> tokens);

  @Override
  void execute(Interpreter interpreter) {
    if (!hasRequiredTypes(interpreter)) {
      System.out.print("dosent have the right items on the stack");
      return;
    }
    execute(interpreter.stack.iterator());
  }

  private boolean hasRequiredTypes(Interpreter interpreter) {
    Iterator stackIterator = interpreter.stack.iterator();
    for (Interpreter.TokenType requiredType : getRequiredTypes()) {
      Interpreter.TokenType itemType = Interpreter.getTokenType((Token)stackIterator.next());
      if (!itemType.equals(requiredType)) {
        return false;
      }
    }
    return true;
  }


}
class Subtract extends RequiresItems {

  @Override
  List<Interpreter.TokenType> getRequiredTypes() {
    return new SetVector<Interpreter.TokenType>() {{
      add(Interpreter.TokenType.INTEGER);
    }}
  }

  @Override
  void execute(Iterator<Token> tokens) {

  }
}