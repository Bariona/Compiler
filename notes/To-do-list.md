- [ ] String Constant 的转义字符处理
- [x] 浅拷贝/深拷贝 : 程序鲁棒性
- [x] 异常处理报错
- [ ] System.out.println()对文本框输入code_text的影响
- [ ] 字符串长度的限制(64个, 255个), 添加负数, 运算范围等等
- [ ] 数组长度(return等时候)是否会越界
- [ ] testcode : gitignore
- [ ] 各个expression的type类型
- [ ] ++a++的判断: a++优先级高, 但是返回的不是引用, 所以这是一个错误用法
- [x] 10.12: 目前需要重新更新一下FuncDefNode等数据, 加上registery类型(这样能够更加匹配scope类型中的Hash)
  - [x] 改一下String 当做Class来处理?
  - [x] 函数名/变量名 重名情况
  - [ ] 多个构造函数的情况 :broken_heart:
  - [x] LambdaExpr (function scope + return type detect)
  - [ ] 好像没有不要ScopeManager里search function()? 因为一定是peek的位置
  - [ ] 未定义行为的处理
- [x] 去掉exprStmt, varExprNode
- 怪怪的做法: ASTNode → StmtNode → DefNode, ExprNode 




Semantic Hack Data:

```java
int x = A.B.f(1,2);
int [][][] a = new [1][][1]

{
  bool a;
  int x = [&](int a, int b) { } (1, a);
}

class A{int a;};
A foo(){
   A ret = new A;
   return new A();
}

int f(int f) {
  int f;
}

function-2.mx
```

