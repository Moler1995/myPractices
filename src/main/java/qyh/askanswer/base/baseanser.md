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
###3.9 线程的 run()和 start()有什么区别？
线程的run方法只是一个普通方法,调用这个方法并不会启动新的线程
start方法会调用start0本地方法,调用这个方法才会启动一个新的线程来执行run中的代码逻辑。
###3，10 创建线程池有哪几种方式？
ExecutorService: newFixedThreadPool,newCachedThreadPool,newScheduledThreadPool,newSingleThreadPool,newSingleThreadScheduledExecutor
###3.11 线程池都有哪些状态？
Running:正常状态,等待新的任务提交执行
ShutDown:关闭状态,不接受新的任务提交,但是会继续执行已提交的任务
Stop:关闭状态,不接受新的任务并且停止当前正在执行的任务,通过shutdownNow方法
Tidying:所有任务都已销毁,workerCount为0,此时调用hook方法terminated
Terminated:执行完terminated()方法之后线程池进入终结态。
###3.12 线程池中 submit()和 execute()方法有什么区别？****
submit是提交一个任务,可以提交实现了Runnable或者Callable接口的线程任务,可以通过提交后返回的Future获得线程任务的执行结果。
execute不能提交有返回值的线程任务,实现Callable接口的线程。
###3.13 在 java 程序中怎么保证多线程的运行安全？（原子性,可见性,有序性）
* 使用synchronize或者Lock,锁住可能会对变量进行读写的代码块。
* 使用volatile修饰可能会被多线程访问的变量,解决变量的可见性问题
###3.14 多线程锁的升级原理是什么？*****
无锁->偏向锁->轻量级锁->重量级锁
* 无锁: 所有线程都可以操作该资源,但同一时间只能有一个修改成功
* 偏向锁: 同一段代码被同一个线程访问时,会自动获得锁,默认不存在锁竞争,当有线程与该线程竞争锁时,偏向锁会被释放
* 轻量级锁:当存在锁竞争时,偏向锁会升级为轻量级锁,未获得锁的线程会不断自旋直到获取到锁。
* 重量级锁:当存在大量的获取锁的需求时间,轻量级锁升级为重量级锁,由操作系统负责锁的调度（monitor enter/monitor exit基于mutex互斥锁）,
未获得锁的线程将进入阻塞状态,性能较低
###3.15 什么是死锁？*****
多个线程再执行时由于竞争资源或者通信问题导致的线程阻塞,如果没有外力的左右,他们都将无法推进下去时就形成了死锁。
Java中两个线程相互需要获取对方已经获取的锁时,会形成死锁
死锁发生的必要条件:
* 互斥,一个资源同一时刻仅能被一个线程使用
* 不可抢占,资源请求这不能从资源占有者手中剥夺资源
* 请求和保持,资源请求者再请求其他资源时同时保持对原有资源的占有
* 循环等待,资源的请求之间存在这循环等待l1->l2->l3->l1
###3.16 怎么防止死锁？
破坏死锁发生的必要条件(按特定的顺序获得锁,限定时间内获取到锁不然就放弃,),银行家算法
###3.17 ThreadLocal 是什么？有哪些使用场景？
自己理解: 是存储在虚拟机栈中，只属于当前线程的局部（xxx全局xxx）变量，需要手动回收否则可能会有内存泄漏风险。可以用于存储当前线程的一些状态变量。
答案: ThreadLocal是线程的本地存储，每个线程中都有一个ThreadLocalMap对象，每个线程只能访问自己的ThreadLocalMap对象。
     ThreadLocal为每个线程提供一个变量的副本，每个线程都可以独立的更改该变量而不会影响其他线程
      应用场景: 为每个线程分配一个JDBC Connection,使用ThreadLocal进行管理,从而不会因为A线程影响B线程的数据库连接。
###3.18 说一下 synchronized 底层实现原理？
自己理解: synchronized地层使用monitor进行锁管理，锁升级顺序为无锁->偏向锁->轻量级锁->重量级锁
答案: 每个对象有一个监视器锁，当monitor被占用时即为锁定状态，线程执行monitorenter获取锁，monitorexit释放锁
获取锁的过程： 如果monitor的进入数为0，可直接进入monitorenter，将monitor进入数设置为1
          ： 如果是同一线程重入情况，则直接为monitor的进入数+1
          ： 如果是需要获取已经被占用的的monitor，则会阻塞等待，直到其他线程用完monitor数量置零
###3.19 synchronized 和 volatile 的区别是什么？
自己理解：synchronized用来解决变量同步问题，volatile用来解决共享变量的可见性问题
        1. synchronized用来保证同一时刻仅有一个线程能对变量进行修改
        2. volatile通过禁止指令重排，保证线程在对变量修改时拿到的都是该变量的最新值
答案:    1. 被synchronized修饰的代码段保证在同一时刻仅仅只有一个线程能执行，可以做到保证线程的原子性，可见性，有序性。
        2. volatile只能修饰变量，通过禁止指令重排，对变量修改之后立即刷新内存中该对象的值，且让缓存了该变量的线程清空数据，重新从堆内存获取值，保证了可见性和有序性
###3.20 synchronized 和 Lock 有什么区别？
自己理解： 1. synchronized不能响应线程中断，Lock可以
         2. Lock获取锁时可以设置超时时间.
         3. Lock锁的获取和释放都需要手动执行，synchronized锁的获取和释放由JVM管理
答案： 1. synchronized作为修饰符可以加载方法上，也可以加在需要同步的代码块之前。
      2. synchronized托管给JVM执行，Lock时Java编写的控制代码
      3, synchronized性能较低，属于重量级操作（JDK6之前，6之后做了优化）
###3.21 synchronized 和 ReentrantLock 区别是什么？
自己理解: 区别和Lock差不多,ReentrantLock实现了Lock接口，synchronized时java关键字。
补充： ReentrantLock可以实现公平锁，synchronized不能实现
###3.22 说一下 atomic 的原理？
自己理解: atomic利用CAS来保证原子性
答案： 通过CAS原理来保证高并发下的原子性，线程在对变量进行操作后，通过比较内存中变量的当前值和该线程操作之前存储的副本中的值，如果一样则更新，不一样则重新计算。
      在并发量很高的情况下会有很多CAS操作失败。
#4 反射
###4.1 什么是反射？
自己理解：JVM虚拟机在运行期间可以获得所有类及其属性和方法
答案: JAVA反射机制是指在运行状态中，对于任意一个类，都能够知道这个类所有的属性和方法。允许在运行时获得任何一个类的内部信息，动态的创建类调用类的方法。
###4.2 什么是 java 序列化？什么情况下需要序列化？
不会
答案：1. 序列化时只将JAVA对象转换为字节流的过程
     2. 在需要将JAVA对象在网络上传输或者持久化存储时需要序列化。
###4.3 动态代理是什么？有哪些应用？
在代码执行过程中，动态地目标类的过程，可以在运行时动态地为目标类进行扩展。
Spring AOP,定制统一地日志输出
###4.4 怎么实现动态代理？
1. JDK动态代理，基于接口的动态代理，动态代理类和被代理类必须实现同一接口，动态代理只能对接口中的方法进行代理。
2. CGLIB动态代理，基于字节码生成库，允许在运行时生成或修改字节码，通过继承被代理的类实现代理。
Enhancer 指定要代理的目标对象，通过create方法得到对象，
调用MethodInterceptor的intercept方法执行目标方法。