## Intermediate Representation (IR)

​	The `front end` of the compiler does lexical analysis, parsing, semantic analysis, and translation to intermediate representation. The `back end` does optimization of the intermediate representation and translation to machine language.

>  This doc is waiting to be completed.

- [ ] User-Use-Value Relationship (Toronto U's slides)

  LLVM Class Hierarchy

### About

Based on *LLVM IR*, most LLVM operations, including all arithmetic and logical operations are in 3-address form.

- Three Address Code

  regarded as four tuple: opcode oprand1 operand2 result

### Basic Concepts

---

- [x] control flow & basic block: def, example

  [Toronto's explanation](https://www.cs.toronto.edu/~david/course-notes/csc110-111/15-graphs/07-control-flow-graphs.html)

- [Value Class](https://llvm.org/doxygen/Value_8h_source.html)
  - def: everything is treated as (or say... derived from) `Value`, including variable/constant/expression/symbol

<details>
	<summary> from LLVM doc </summary>
 	This is a very important LLVM class. It is the base class of all values
computed by a program that may be used as operands to other values. Value is
the super class of other important classes such as Instruction and Function.
All Values have a Type. Type is not a subclass of Value. Some values can
have a name and they belong to some Module.  Setting the name on the Value
automatically updates the module's symbol table.

 	Every value has a "use list" that keeps track of which other Values are
using this Value. 	
</details>



- Use & User

  `Use` is made to describe IR instructions' relations, including prev/next/parent(user node)

  i.e. represents the edge between a [Value](https://llvm.org/doxygen/classllvm_1_1Value.html) definition and its users.

  - Instructions are the largest class of Users.
  - Constants may be users of other constants (think arrays and stuff)

```
%2 = add %1, 0
%3 = mul %2, 2
```

the instruction %2 = add %1, 0 can be regarded as a value(usee), and the user is %3 = mul %2, 2

Thus, we can directly see that **User** (regarded as abstract class) is also a kind of **Value**.

~~Normally, the user of a value is always an instruction? (~~

### IR Type

1. void Type

2. Function Type

   ```
   <returntype> (<parameter list>)
   ```

   e.g. i32 (i32), i32 (i8*, ...) * /* it's a function pointer, i.e. points to a function*/

3. Fist class Type

   - Integer Type
   - Pointer Type: there is no void\* or label\*
   - etc.

4. Label Type

5. Aggregated Type

   - Array Type (e.g. [40 x i32])
   - Structure Type

### Constants

1. bool constant: true/false
2. int constant: contains neg number
3. null constant: null pointer


*mentioned that constants will be treated as **operands** in my repo


### General

- Value

  components: name, VTy (it's type), useList\<\>

- User (derived from value)

  component: operands (i.e. values)


```mermaid
graph LR
	V[Value]-->U[User];
	V-->O[Operands];
	U-->I[Instruction];
	I-->B[BasicBlocks];
	U-->F[IR Function];
	B--CFG-->F;
	F-->M[Module];
	O-->C[Constants];
end;
```

### Instruction

- br
- call
- icmp
- ret
- global variable: since global variable will be created during compiling stage, it must be initialized.

`toString():` 

​	e.g. ret_instr2\.toString = "%2 = br void"



### Function

- [def](https://llvm.org/docs/LangRef.html#functions)


- toString(): to print `.ll` file



### Module

​	LLVM programs are composed of `Module`’s, each of which is a translation unit of the input programs. Each module consists of functions, global variables, and symbol table entries. 

