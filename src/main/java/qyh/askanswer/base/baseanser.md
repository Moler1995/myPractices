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
然后会在常量池中生成一份，JDK8之前在永久代，之后再元数据区；
###1.8 String str="i"与 String str=new String(“i”)一样吗？
_Ans_: 前者会在虚拟机栈中创建字符串之后调用native方法intern查询常量池中StringTable是否有该字符串，如果有则返回该字符串的引用，如果没有则创建；如果Java堆中已经
存在该字符串对象，则不会在常量池中创建，而是直接在StringTable中添加该对象的引用。第二个则会将字符串放入常量池之后，在堆中创建一个新的对象，放入StringTable，并返回堆中的引用；
