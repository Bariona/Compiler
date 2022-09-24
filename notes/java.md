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

