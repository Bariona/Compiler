### 类和对象

- 一个源文件中只能有一个 public 类
- 一个源文件可以有多个非 public 类
- 源文件的名称应该和 public 类的类名保持一致。例如：源文件中 public 类的类名是 Employee，那么源文件应该命名为Employee.java。



### 遍历args

1. 在terminal中直接输入
2. 在IDEA中的`Edit Configuration`中arguments传入参数

```java
 for (int i = 0; i < args.length; ++i) {
   System.out.println(args[i]);
 }
```

### Packages

学习一下package(名字要和文件夹**同名**)和import用法方便整理.g4生成的各类文件

### Errors

 Java中凡是继承自Exception但不是继承自`RuntimeException`的类都是非运行时异常。

### Naive Questions

1. main() 一定要public static修饰

   static函数调用的时候不需要实例化, 和public两个的最终目的都是为了方便JVM能够直接调用, 不需要实例化.

2. - [x] 在调用FileInputStream()的时候最好还是用**绝对路径**, 原因是因为相对路径可能定位到的是project的根目录(即工程目录)...

   ~~蹲一个有缘人解决问题~~