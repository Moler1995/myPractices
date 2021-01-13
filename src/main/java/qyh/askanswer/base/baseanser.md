#1 Java 基础
###1.1 JDK 和 JRE 有什么区别？
_Ans_：JDK是JAVA开发环境，JRE是JAVA的运行环境；
JDK面向JAVA程序开发人员，JRE面向需要运行JAVA程序的环境；
JDK包含了JRE所没有的一些调试和编译功能
###1.2 ==和equals的区别
_Ans_：==直接比较对象的内存地址，equals一般是比较两个实现了equals和hashCode方法的对象的hash值
###1.3 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？
_Ans_：不对，得看equals对象的equals方法的实现方式；
###1.4 final 在 java 中有什么作用？
_Ans_： 
* 如果final修饰类，则该类不能被继承
* 如果final修饰一个类变量，则这个类变量的值为类初始化时赋予的值，且不能改变
* 如果final修饰一个方法变量，则这个变量不能被重新赋值
* 如果final修饰一个方法，则这个方法不能被重写
###1.5 java 中的 Math.round(-1.5) 等于多少？
_Ans_: -1 
###1.6 String属于基础数据类型吗？
_Ans_: 不属于，String属于被final修饰的类。
###1.7 java中操作字符串都有哪些类？它们之间有什么区别？
_Ans_: String, StringBuffer, StringBuilder
String类是一个final类，类中的方法都是返回一个新的String对象，不会影响原来的String，String s = "1111"，会在虚拟机栈中创建一个字符串
然后会在常量池中生成一份，JDK8前在永久代，之后再元数据区；每次
StringBuilder和StringBuffer都可以操作字符串，但是StringBuffer是线程安全的。
###1.8 String str="i"与 String str=new String(“i”)一样吗？
_Ans_: 前者会在虚拟机栈中创建字符串之后调用native方法intern查询常量池中StringTable是否有该字符串，如果有则返回该字符串的引用，如果没有则创建；如果Java堆中已经
存在该字符串对象，则不会在常量池中创建，而是直接在StringTable中添加该对象的引用。第二个则会将字符串放入常量池之后，在堆中创建一个新的对象，放入StringTable，并返回堆中的引用；
###1.9 如何将字符串反转？
* 使用StringBuffer或者StringBuilder调用reverse函数
* 将String分解为char数组后重新拼接
###1.10 String 类的常用方法都有那些？
contains,charAt,toCharArray,equals,toLowerCase,toUpperCase,replace,subString....
###1.11 抽象类必须要有抽象方法吗？
不一定，抽象类可以没有抽象方法，抽象类由abstract修饰，不能被实例化；
###1.12 普通类和抽象类有哪些区别？
普通类不能包含抽象类，普通类如果继承抽象类必须实现其抽象方法，抽象类不能被实例化，抽象方法不能被声明成static或者final，抽象类不能用final修饰
###1.13 抽象类能使用 final 修饰吗？
不能
###1.14 接口和抽象类有什么区别？
接口中的的方法不能有实现，但JDK8之后可以有默认简单实现用default修饰。
抽象类可以包含普通方法
一个普通类只能继承一个抽象类，但是可以对多个接口进行实现
#1.15 java 中 IO 流分为几种？BIO,NIO,AIO
BIO:阻塞IO，在读取或者写入的时候会阻塞线程
NIO:非阻塞IO，基于IO多路复用，利用selector监控读写事件，线程创建通道，然后注册到selector中就可以继续执行后面的逻辑，等到真正有读写数据时对通道进行读写操作。
AIO:异步IO，不需要对通道进行注册，再有读写事件时会调用相应的处理方法。
###1.16 Files的常用方法都有哪些？
exists,createFile,createDirectory,delete,copy,move,size,read,write
#2 容器
###2.1 java 容器都有哪些？
