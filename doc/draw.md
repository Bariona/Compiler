info: name, type, value

FuncInfo: FuncType(ret, paraType), paraList\<info\>

scope: HashMap\<String, info\>

table: HashMap\<String, value\>

1. Root Node

   - Function 
   - class def (add to ~~Info Table~~  or scopeManager)
   - var def (add to ~~Info Table~~  or scopeManager)

2. scan Root Node:

   - get class def, class.method def %struct.a = type {i1, i32} (not accept.this!!)
   - get Functions def (not accept.this!!)
   - get **global** variable def (accept.this)

   > Let all value to be stored in their nodes.

3. traverse Root Node

   - ↑ global variable
   - class
   - function



attention: phi 指令必须在 basic block 的最前面，也就是在一个 basic block 中，在 phi 指令之前不允许有非 phi 指令。

- Think about Basic Blocks: where show I place an instruction in a block ?


- why we have to load & store, 

  we do load/store operation only because it the variable that we want to use.

  add/sub instruction will only get a immediate result in register, there is no `storage unit` to insure its availability.

- \

- if atomExpr isMethod = true, then it must be in a function.

Short-Circuit Evaluation:

`binary node:`a = b && c



- [ ] assign 语句并不是完美的, 因为访问lhs节点的时候会有load指令?

- [ ] [New Expression reference](https://www.runoob.com/cplusplus/cpp-dynamic-memory.html): 因为是动态分配的内存, 所以不能用alloca

- [x] IRFunction并不需要addOperand, call的时候记得就行

  IRFunction的addOperand纯纯是为了输出.ll文件, 展示函数签名 

  所以function的add parameters可以随便一点, 直接add new Value就行了.

- [x] resolve Operand type... → `recordPtr` 

  目的: 如果一个变量是左值, 比如++x, 那么就要记录x的位置在哪里.

  用处: ++x, assign的时候(因为x=y, lhs返回的是x的值, 而不是一个指向x的ptr)

- [ ] init Function entry 为空的问题 (或者问题并不只是init function的问题...)

  Malloc 只能分配1个byte吗? i.e. 如果int[30] 需要malloc [120]?

- [ ] call init function在哪里? (mx_main, initFunction)

- [x] class类成员的初始化放在哪里? 

  ps: 应该是没有这个问题的, mx只会声明该变量, 而不会赋初值

  但没有分配内存吗???? (A: alloca/globaldef 的时候就已经分配了

- [x] 构造函数的返回值应该是void

- [x] declare的时候function的parameter

- [x] \n和\\\0A的关系

- [x] while和for语句都要记录Condition和Exit → break/ continue

- [x] 头部加 一堆的phi指令 (哭

  应该是不用的, 短路求值才需要

