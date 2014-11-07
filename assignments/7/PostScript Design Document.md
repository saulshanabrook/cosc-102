<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](http://doctoc.herokuapp.com/)*

- [PostScript Design Document](#postscript-design-document)
  - [Operation Diagrams](#operation-diagrams)
    - [`pstack`](#pstack)
    - [`quit`](#quit)
    - [`sub` ← `number, number` → `number`](#sub-←-number-number-→-number)
    - [`pop` ← `anything`](#pop-←-anything)
    - [`dup` ← `anything` → `anything`](#dup-←-anything-→-anything)
    - [`exch` ← `anything, anything` → `anything, anything`](#exch-←-anything-anything-→-anything-anything)
    - [`ptable`](#ptable)
    - [`def` ← `anything, symbol`](#def-←-anything-symbol)
    - [`add` ← `number, number` → `number`](#add-←-number-number-→-number)
    - [`mul` ← `number, number` → `number`](#mul-←-number-number-→-number)
    - [`div` ← `number, number` → `number`](#div-←-number-number-→-number)
    - [`eq` ← `anything, anything` → `boolean`](#eq-←-anything-anything-→-boolean)
    - [`ne` ← `anything, anything` → `boolean`](#ne-←-anything-anything-→-boolean)
    - [`lt` ← `number, number` → `boolean`](#lt-←-number-number-→-boolean)
    - [`gt` ← `number, number` → `boolean`](#gt-←-number-number-→-boolean)
    - [`not` ← `integer|boolean` → `integer|boolean`](#not-←-integer|boolean-→-integer|boolean)
    - [procedures](#procedures)
    - [`if` ← `anything, boolean`](#if-←-anything-boolean)
  - [Bailey’s Thought Questions](#bailey’s-thought-questions)
    - [1](#1)
    - [2](#2)
    - [5](#5)
  - [Interpreter Class](#interpreter-class)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

# PostScript Design Document
Saul Shanabrook

## Operation Diagrams
Provides the required types and the resulting type in the form:

> `← <top of stack req’d type>, … → <resulting type>`

Gives the description.

Then any simpler command combination it is equivalent to.

Then a list of lines showing the stack using this command in the form:

> `<instructions>` : `<resulting stack, with the top on the left>` [→ `<output>`]

### `pstack`
prints the stack

1. `1` : `1.0`
2. `pstack` : `1.0` → `1.0`
3. `2 3` : `3.0 2.0 1.0`
4. `pstack` : `3.0 2.0 1.0` → `3.0\n2.0\n1.0`

### `quit`
exits the program

1. `1` : `1.0`
2. `quit` : `1.0` → `␄`

### `sub` ← `number, number` → `number`
subtracts the top of the stack from the next item in the stack.

~ `-1 mul add`

1. `1` : `1.0`
2. `2` : `2.0 1.0`
3. `sub` : `-1.0`

### `pop` ← `anything`
removes the top item from the stack

1. `1` : `1.0`
2. `pop` : ` `
3. `1 2` : `2.0 1.0`
4. `pop` : `1.0`

### `dup` ← `anything` → `anything`
duplicates the top item from the stack

1. `1 2` : `2.0 1.0`
2. `dup` : `2.0 2.0 1.0`

### `exch` ← `anything, anything` → `anything, anything`
switches the top two items on the stack

1. `1 2` : `2.0 1.0`
2. `exch` : `1.0 2.0`

### `ptable`
prints the list of defined symbols

1. `ptable` : ` ` → ` `
2. `/pi` : `/pi`
3. `1` : `1.0 /pi`
3. `def` : ` `
4. `ptable` : ` ` → `pi=1.0`
5. `/twoNumber { 1 2 } def` : ` `
6. `table` : ` ` → `pi=1.0\ntwoNumber={  1.0 2.0 }`

### `def` ← `anything, symbol`
defines a symbol

1. `/pi 3.14` : `3.14 /pi`
2. `def` : ` `
3. `pi` : `pi`
4. `1` : `1 pi`
5. `add` : `4.14`

### `add` ← `number, number` → `number`
adds the top two numbers on the stack

1. `1 2` : `2.0 1.0`
2. `add` : `3.0`

### `mul` ← `number, number` → `number`
multiplies the top two numbers on the stack

1. `2 3` : `3.0 2.0`
2. `mul` : `6.0`

### `div` ← `number, number` → `number`
divides the second item in the stack by the first

1. `1 2` : `2.0 1.0`
2. `div` : `0.5`

### `eq` ← `anything, anything` → `boolean`
returns whether the top two items on the stack are equal in type and value

1. `1 1 eq` : `true`
2. `pop 1 2 eq` : `false`
3. `pop true true eq` : `true`
4. `pop true false eq` : `false`
5. `pop false false eq` : `true`
6. `pop /pi /pi eq` : `true`

### `ne` ← `anything, anything` → `boolean`
returns whether the top two items on the stack are not equal

~ `eq not`

1. `1 1 ne` : `false`
2. `pop 1 2 ne` : `true`
3. `pop true true ne` : `false`
4. `pop true false ne` : `true`
5. `pop false false ne` : `false`
6. `pop /pi /pi ne` : `false`

### `lt` ← `number, number` → `boolean`
whether the second item is less than top

1. `1 2` : `2.0 1.0`
2. `lt` : `true`
3. `pop 3 2` : `2.0 3.0`
4. `lt` : `false`

### `gt` ← `number, number` → `boolean`
whether the second item is greater than top

1. `1 2` : `2.0 1.0`
2. `gt` : `false`
3. `pop 3 2` : `2.0 3.0`
4. `gt` : `true`

### `not` ← `integer|boolean` → `integer|boolean`
If it is a boolean, returns the opposite.

If it is a integer, returns the inverse minus one.

~ `-1 mul -1 add`

1. `true not` : `false`
2. `pop false not` : `true`
3. `pop 3 not` : `-4.0`
4. `not` : `3.0`

### procedures
a list of commands inside of brackets. a symbol can be defined to hold a procedure. whenever that symbol is used, the procedure items are substituted instead.

1. `{ 1.0 }` : `{ 1.0 }`
2. `/num` : `/num { 1.0 }`
3. `exch def` : ` `
4. `num` : `1.0`
5. `pop /twonum { 1.0 2.0 } def twonum` : `2.0 1.0`


### `if` ← `anything, boolean`
returns the first item in the stack (expanded if a procedure) if the second item is true

1. ` { 1 } true` : ` { 1 } true`
2. `if` : `1`
3. `if { 1 } false pop` : ` `
4. `if { 1 2 } true` : `2 1`
5. `if 1 true pop pop` : `1`

## Bailey’s Thought Questions
### 1
> If we are performing an `eq` operation, is it necessary to assume that the
values on the top of the stack are, say, numbers?

No, any two types can be equal to another item of that type. Also, if they are of different types they can still be used as input for `eq` it will always just return `false`

### 2
> The `pstack` operation should print the contents of the operand stack without
destroying it. What is the most elegant way of doing this? (There are
many choices.)

Iterate through the stack, assuming it is a [`StackArray`](http://highered.mheducation.com/sites/dl/free/0072399090/26111/StackArray.txt), using the `.iterator()` method and call `System.out.println` on each item in the array in order.

### 5
> What does the following do?
> ```
> /count { dup 1 ne { dup 1 sub count } if } def
> 10 count stack
> ``` 

It will add each number from 0 to 9 to the stack, starting with 9, then 8, etc.


## Interpreter Class

This is the start of my interpreter class

```java
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
```
