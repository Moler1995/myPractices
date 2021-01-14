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
Collection:Set,List,Queue,Stack,Vector
Map:HashMap,TreeMap,ConcurrentHashMap,HashTable
###2.2 Collection 和 Collections 有什么区别？
Collection是集合类的接口，提供了Java集合类基础的操作如size,isEmpty,add,remove等等
Collections是java.util包下的一个对Java集合类的操作工具,可以用来生成一种Java集合对象,或者对集合对象进行增删改查等操作.
###2.3 List、Set、Map 之间的区别是什么？
* List和Set都是继承自Collection接口，Map是与Collection接口平级的容器接口
* List中的元素是可以重复的，Set中的元素不能重复，Map的键不能重复值可以重复
* List中的元素按照元素的插入顺序进行排序，Set中的元素默认是无序的(LinkedHashMap按照插入顺序进行排序)
  Map中的元素也是无序的(TreeMap根据键进行排序，默认根据hashCode排序)
* List可以有任意数量的空值，Set只能有一个空值，Map只能有一个空键但是可以有多个空值
###2.4 HashMap 和 Hashtable 有什么区别？
联系:都是基于散列表的键值对快速查询结构,都实现了Map接口
区别:
* HashMap继承自AbstractMap,HashTable继承自Dictionary
* HashMap是线程不安全的,HashTable是线程安全的
* HashMap允许值为null,HashTable不允许
###2.5 如何决定使用 HashMap 还是 TreeMap？
如果需要得到一个有序的Map来按序遍历key,可以使用TreeMap。
###2.6 说一下 HashMap 的实现原理？
* HashMap实现Map了接口,底层使用数组+链表/红黑树存储键值对条目,存储的索引根据键计算出的哈希值得到;
* 如果遇到hash冲突,扩展链表存储新的条目,JDK8以后当链表存储的条目大于树化阈值时(默认是8)会将条目的存储结构改为红黑树
###2.7 说一下 HashSet 的实现原理？
* HashSet是一种实现了Collection接口的Java集合类
* 基于HashMap实现,Set中的元素用HashMap的键来存储
###2.8 ArrayList 和 LinkedList 的区别是什么？
* ArrayList基于数组实现,LinkedList基于双向链表实现
* ArrayList随机读取效率高,LinkedList插入和删除效率高
###2.8 如何实现数组和 List 之间的转换？
Arrays.asList()方法数组转List
List.toArray()实现List转数组
###2.9 ArrayList 和 Vector 的区别是什么？
* ArraysList是线程不安全的,Vector是线程安全的
###2.10 Array 和 ArrayList 有何区别？
Array数组是一种数据存储结构,ArrayList基于数组实现。
数组在声明时需要确定大小,ArraysList不需要。
数组不支持扩容,ArrayList可以扩容。
ArrayList支持存储异构对象
###2.11 在 Queue 中 poll()和 remove()有什么区别？
在队列为空时,poll会返回null,remove会报错
###2.12 哪些集合类是线程安全的？
Vector,ConcurrentHashMap,ArrayBlockingQueue,Stack,ConcurrentLinkedQueue...
###2.13 迭代器 Iterator 是什么？
迭代器是一种设计模式,它是一个对象,可以用来遍历选择容器中的对象,而程序员不需要知道该容器的底层的实现。
###2.14 Iterator 怎么使用？有什么特点？
Iterator 用来遍历容器中的对象,有next,hasNext,remove方法
通过调用容器对象的iterator方法返回一个迭代器,然后调用迭代器的方法对容器进行操作。
###2.15 Iterator 和 ListIterator 有什么区别？
ListIterator专门用来对列表类进行迭代遍历,Iterator可以遍历所有容器对象
ListIterator有更多操作方法,可以add,向前遍历
###2.16 怎么确保一个集合不能被修改？
Collections.unmodifiableList(List)创建一个无法修改的列表
#3 多线程
###3.1 并行和并发有什么区别？
并行是指在不同实体上同时有多个任务在一起执行
并发是值在同一个实体上同一时间内有多个事件需要执行。
###3.2 线程和进程的区别？
* 线程是程序执行的最小单位,进程是资源分配的最小单位
* 同一进程内的不同线程共享资源,不同进程不共享资源
* 线程是处理机调度的基本单位
* 每个进程有一个程序运行的入口,每个线程需要由应用程序调度
###3.3 守护线程是什么？
主线程退出之后才会退出的线程,如垃圾回收线程,用于服务其他线程。
###3.4 创建线程有哪几种方式？
继承Thread类,重写run方法,实现Runnable接口,实现run方法,实现Callable的call方法,用FutureTask封装Callable对象后,通过get方法获得返回值
###3.5 说一下 runnable 和 callable 有什么区别？
Runnable没有返回值
Callable带返回值,可以获得程序执行结果
###3.6 线程有哪些状态？
New:创建
Runnable:可执行,等待调度器调度
Running:执行
Block:阻塞
Dead:死亡,可能是正常执行退出或者异常退出
###3.7 sleep() 和 wait() 有什么区别？
sleep不会放弃锁,wait让出当前线程的锁,等待其他线程调用notify并且获得锁继续执行。
sleep是线程的方法,wait是Object的方法。
sleep需要捕获异常
###3.8 notify()和 notifyAll()有什么区别？
notify在线程等待池中随机选择一个线程将其移入锁池获得锁
notifyAll会将等待池中的所有线程移入锁池去竞争锁。
