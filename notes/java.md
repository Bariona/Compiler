<img alt="jdk, jre, jvm" height="200" src="https://upload-images.jianshu.io/upload_images/4622762-f8effb081e6b935e.png?imageMogr2/auto-orient/strip|imageView2/2/w/658/format/webp" width="200"/>

<img alt="jdk" height="200" src="https://upload-images.jianshu.io/upload_images/4622762-513a6830fb10ace5.png?imageMogr2/auto-orient/strip|imageView2/2/w/684/format/webp" width="200"/>

查看java bytecode:
```
    javap -c <filename>.class
```

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

### interface & abstract class

- 除非实现接口的类是abstract class，否则该类要定义interface中所有method.

- 一般流程?: 定义interface→定义abstract class→定义普通类

  - 为什么抽象类不必（是不必，不是不能）定义接口中所有方法?

    ​	抽象类和接口的定义虽然不同，但是其目的是一致的，通过继承抽象类/实现接口来继承抽象类/接口中的抽象方法，并定义其具体实现。

    ​	当抽象类实现接口时，虽然继承了接口所有方法，但是根据实际情况，接口中有些（甚至所有）抽象方法是不必定义的，那么便按需定义。

    ​	当另一个类继承该抽象类时，再去定义这些方法。

  个人理解: interface可以不严谨地理解为Rust里的trait(虽然trait里的method可以实现), 可以实现值传递(向上传递).

### 访问者模式

在Yx的repo中可见的用法, 需自行google一下确定用法.

### 构造函数 constructor

一旦父类实现了构造函数, 那么继承的子类也必须要有constructor, 因为此时默认构造函数已经失效了

最简单解决办法可以直接override父类的constructor, 然后调用super(para1, para2, ...)

### Naïve Questions

1. main() 一定要public static修饰

   static函数调用的时候不需要实例化, 和public两个的最终目的都是为了方便JVM能够直接调用, 不需要实例化.

2. - [x] 在调用FileInputStream()的时候最好还是用**绝对路径**, 原因是因为相对路径可能定位到的是project的根目录(即工程目录)...

   ~~蹲一个有缘人解决问题~~