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
_Ans_: String， StringBuffer， StringBuilder
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
contains，charAt，toCharArray，equals，toLowerCase，toUpperCase，replace，subString....
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
###1.15 java 中 IO 流分为几种？BIO，NIO，AIO
BIO:阻塞IO，在读取或者写入的时候会阻塞线程
NIO:非阻塞IO，基于IO多路复用，利用selector监控读写事件，线程创建通道，然后注册到selector中就可以继续执行后面的逻辑，等到真正有读写数据时对通道进行读写操作。
AIO:异步IO，不需要对通道进行注册，再有读写事件时会调用相应的处理方法。
###1.16 Files的常用方法都有哪些？
exists，createFile，createDirectory，delete，copy，move，size，read，write
#2 容器
###2.1 java 容器都有哪些？
Collection:Set，List，Queue，Stack，Vector
Map:HashMap，TreeMap，ConcurrentHashMap，HashTable
###2.2 Collection 和 Collections 有什么区别？
Collection是集合类的接口，提供了Java集合类基础的操作如size，isEmpty，add，remove等等
Collections是java.util包下的一个对Java集合类的操作工具，可以用来生成一种Java集合对象，或者对集合对象进行增删改查等操作.
###2.3 List、Set、Map 之间的区别是什么？
* List和Set都是继承自Collection接口，Map是与Collection接口平级的容器接口
* List中的元素是可以重复的，Set中的元素不能重复，Map的键不能重复值可以重复
* List中的元素按照元素的插入顺序进行排序，Set中的元素默认是无序的(LinkedHashMap按照插入顺序进行排序)
  Map中的元素也是无序的(TreeMap根据键进行排序，默认根据hashCode排序)
* List可以有任意数量的空值，Set只能有一个空值，Map只能有一个空键但是可以有多个空值
###2.4 HashMap 和 Hashtable 有什么区别？
联系:都是基于散列表的键值对快速查询结构，都实现了Map接口
区别:
* HashMap继承自AbstractMap，HashTable继承自Dictionary
* HashMap是线程不安全的，HashTable是线程安全的
* HashMap允许值为null，HashTable不允许
###2.5 如何决定使用 HashMap 还是 TreeMap？
如果需要得到一个有序的Map来按序遍历key，可以使用TreeMap。
###2.6 说一下 HashMap 的实现原理？
* HashMap实现Map了接口，底层使用数组+链表/红黑树存储键值对条目，存储的索引根据键计算出的哈希值得到;
* 如果遇到hash冲突，扩展链表存储新的条目，JDK8以后当链表存储的条目大于树化阈值时(默认是8)会将条目的存储结构改为红黑树
###2.7 说一下 HashSet 的实现原理？
* HashSet是一种实现了Collection接口的Java集合类
* 基于HashMap实现，Set中的元素用HashMap的键来存储
###2.8 ArrayList 和 LinkedList 的区别是什么？
* ArrayList基于数组实现，LinkedList基于双向链表实现
* ArrayList随机读取效率高，LinkedList插入和删除效率高
###2.8 如何实现数组和 List 之间的转换？
Arrays.asList()方法数组转List
List.toArray()实现List转数组
###2.9 ArrayList 和 Vector 的区别是什么？
* ArraysList是线程不安全的，Vector是线程安全的
###2.10 Array 和 ArrayList 有何区别？
Array数组是一种数据存储结构，ArrayList基于数组实现。
数组在声明时需要确定大小，ArraysList不需要。
数组不支持扩容，ArrayList可以扩容。
ArrayList支持存储异构对象
###2.11 在 Queue 中 poll()和 remove()有什么区别？
在队列为空时，poll会返回null，remove会报错
###2.12 哪些集合类是线程安全的？
Vector，ConcurrentHashMap，ArrayBlockingQueue，Stack，ConcurrentLinkedQueue...
###2.13 迭代器 Iterator 是什么？
迭代器是一种设计模式，它是一个对象，可以用来遍历选择容器中的对象，而程序员不需要知道该容器的底层的实现。
###2.14 Iterator 怎么使用？有什么特点？
Iterator 用来遍历容器中的对象，有next，hasNext，remove方法
通过调用容器对象的iterator方法返回一个迭代器，然后调用迭代器的方法对容器进行操作。
###2.15 Iterator 和 ListIterator 有什么区别？
ListIterator专门用来对列表类进行迭代遍历，Iterator可以遍历所有容器对象
ListIterator有更多操作方法，可以add，向前遍历
###2.16 怎么确保一个集合不能被修改？
Collections.unmodifiableList(List)创建一个无法修改的列表
#3 多线程
###3.1 并行和并发有什么区别？
并行是指在不同实体上同时有多个任务在一起执行
并发是值在同一个实体上同一时间内有多个事件需要执行。
###3.2 线程和进程的区别？
* 线程是程序执行的最小单位，进程是资源分配的最小单位
* 同一进程内的不同线程共享资源，不同进程不共享资源
* 线程是处理机调度的基本单位
* 每个进程有一个程序运行的入口，每个线程需要由应用程序调度
###3.3 守护线程是什么？
主线程退出之后才会退出的线程，如垃圾回收线程，用于服务其他线程。
###3.4 创建线程有哪几种方式？
继承Thread类，重写run方法，实现Runnable接口，实现run方法，实现Callable的call方法，用FutureTask封装Callable对象后，通过get方法获得返回值
###3.5 说一下 runnable 和 callable 有什么区别？
Runnable没有返回值
Callable带返回值，可以获得程序执行结果
###3.6 线程有哪些状态？
New:创建
Runnable:可执行，等待调度器调度
Running:执行
Block:阻塞
Dead:死亡，可能是正常执行退出或者异常退出
###3.7 sleep() 和 wait() 有什么区别？
sleep不会放弃锁，wait让出当前线程的锁，等待其他线程调用notify并且获得锁继续执行。
sleep是线程的方法，wait是Object的方法。
sleep需要捕获异常
###3.8 notify()和 notifyAll()有什么区别？
notify在线程等待池中随机选择一个线程将其移入锁池获得锁
notifyAll会将等待池中的所有线程移入锁池去竞争锁。
###3.9 线程的 run()和 start()有什么区别？
线程的run方法只是一个普通方法，调用这个方法并不会启动新的线程
start方法会调用start0本地方法，调用这个方法才会启动一个新的线程来执行run中的代码逻辑。
###3.10 创建线程池有哪几种方式？
ExecutorService: newFixedThreadPool，newCachedThreadPool，newScheduledThreadPool，newSingleThreadPool，newSingleThreadScheduledExecutor
###3.11 线程池都有哪些状态？
Running:正常状态，等待新的任务提交执行
ShutDown:关闭状态，不接受新的任务提交，但是会继续执行已提交的任务
Stop:关闭状态，不接受新的任务并且停止当前正在执行的任务，通过shutdownNow方法
Tidying:所有任务都已销毁，workerCount为0，此时调用hook方法terminated
Terminated:执行完terminated()方法之后线程池进入终结态。
###3.12 线程池中 submit()和 execute()方法有什么区别？****
submit是提交一个任务，可以提交实现了Runnable或者Callable接口的线程任务，可以通过提交后返回的Future获得线程任务的执行结果。
execute不能提交有返回值的线程任务，实现Callable接口的线程。
###3.13 在 java 程序中怎么保证多线程的运行安全？（原子性，可见性，有序性）
* 使用synchronize或者Lock，锁住可能会对变量进行读写的代码块。
* 使用volatile修饰可能会被多线程访问的变量，解决变量的可见性问题
###3.14 多线程锁的升级原理是什么？*****
无锁->偏向锁->轻量级锁->重量级锁
* 无锁: 所有线程都可以操作该资源，但同一时间只能有一个修改成功
* 偏向锁: 同一段代码被同一个线程访问时，会自动获得锁，默认不存在锁竞争，当有线程与该线程竞争锁时，偏向锁会被释放
* 轻量级锁:当存在锁竞争时，偏向锁会升级为轻量级锁，未获得锁的线程会不断自旋直到获取到锁。
* 重量级锁:当存在大量的获取锁的需求时间，轻量级锁升级为重量级锁，由操作系统负责锁的调度（monitor enter/monitor exit基于mutex互斥锁），
未获得锁的线程将进入阻塞状态，性能较低
###3.15 什么是死锁？*****
多个线程再执行时由于竞争资源或者通信问题导致的线程阻塞，如果没有外力的左右，他们都将无法推进下去时就形成了死锁。
Java中两个线程相互需要获取对方已经获取的锁时，会形成死锁
死锁发生的必要条件:
* 互斥，一个资源同一时刻仅能被一个线程使用
* 不可抢占，资源请求这不能从资源占有者手中剥夺资源
* 请求和保持，资源请求者再请求其他资源时同时保持对原有资源的占有
* 循环等待，资源的请求之间存在这循环等待l1->l2->l3->l1
###3.16 怎么防止死锁？
破坏死锁发生的必要条件(按特定的顺序获得锁，限定时间内获取到锁不然就放弃，)，银行家算法
###3.17 ThreadLocal 是什么？有哪些使用场景？
自己理解: 是存储在虚拟机栈中，只属于当前线程的局部（xxx全局xxx）变量，需要手动回收否则可能会有内存泄漏风险。可以用于存储当前线程的一些状态变量。
答案: ThreadLocal是线程的本地存储，每个线程中都有一个ThreadLocalMap对象，每个线程只能访问自己的ThreadLocalMap对象。
     ThreadLocal为每个线程提供一个变量的副本，每个线程都可以独立的更改该变量而不会影响其他线程
      应用场景: 为每个线程分配一个JDBC Connection，使用ThreadLocal进行管理，从而不会因为A线程影响B线程的数据库连接。
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
      3， synchronized性能较低，属于重量级操作（JDK6之前，6之后做了优化）
###3.21 synchronized 和 ReentrantLock 区别是什么？
自己理解: 区别和Lock差不多，ReentrantLock实现了Lock接口，synchronized时java关键字。
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
Spring AOP，定制统一地日志输出
###4.4 怎么实现动态代理？
1. JDK动态代理，基于接口的动态代理，动态代理类和被代理类必须实现同一接口，动态代理只能对接口中的方法进行代理。
2. CGLIB动态代理，基于字节码生成库，允许在运行时生成或修改字节码，通过继承被代理的类实现代理。
Enhancer 指定要代理的目标对象，通过create方法得到对象，
调用MethodInterceptor的intercept方法执行目标方法。
#5 对象拷贝
###5.1 为什么要使用克隆？
需要深度复制一个对象避免重复的set操作。 x
答案: 对对象进行处理时，需要保留原有对象的数据而进行处理操作需要用到对象克隆。
###5.2 如何实现对象克隆？
克隆分浅克隆和深克隆，浅克隆时克隆对象和原对象会指向同一块内存，因此对克隆对象的修改会影响原始对象。深克隆可以完成完全克隆，实现方法为反射或者序列化方式。
###5.3 深拷贝和浅拷贝区别是什么？
克隆分浅克隆和深克隆，浅克隆时克隆对象和原对象会指向同一块内存，因此对克隆对象的修改会影响原始对象。深克隆可以完成完全克隆，克隆对象的修改不会影响原始对象。
#6 JAVA WEB
###6.1 jsp 和 servlet 有什么区别？
不会
答案：
* Servlet是一种服务器端的Java应用程序，具有独立于平台和协议的特性，可生成动态Web页面，担当客户请求和服务器响应的中间层。
* Servlet是位于Web服务器内部的Java应用程序，由Web服务器进行加载，该Web服务器必须包含支持Servlet的Java虚拟机。
* JSP全名Java Server Page。其根本是简化Servlet设计，JSP使用Java语言编写类似XML的标签和脚本来封装页面动态处理的逻辑
JSP是一种动态页面技术，其将网页逻辑与页面设计的显示分离，支持可重用的基于组件的设计。
* JSP本质还是Servlet，经过web容器编译之后jsp代码将变成jvm能够识别的java类。
各自优缺点:
* Servlet能够很好地组织业务逻辑代码，但是在Java源文件中通过字符串拼接的方式生成动态html会导致代码维护困难
* JSP虽然规避了在Servlet中生成html的问题，但是在html中混入了大量的业务逻辑代码。
过程：
  当一个http请求经过jsp时，tomcat会调用service()方法将jsp编译成servlet，然后执行servlet。
- 通过MVC取长补短Controller(Servlet)负责接收转发请求和逻辑处理，Model根据请求入参和相应算法生成返回值模型，
  View(JSP)负责对返回的数据进行视图的生成操作。
###6.2 jsp 有哪些内置对象？作用分别是什么？
request: 是javax.servlet.HttpServletRequest，代表了客户端的请求信息，主要用于接收通过HTTP协议传送到服务器的数据，作用域为一次请求。
response: 代表客户端的响应，主要将JSP容器处理过的对象回传给客户端。只在JSP页面内有效。
session: 由服务器创建的与用户请求相关的对象。服务器为每个用户生成一个session，保存用户信息，跟踪用户状态。内部用Map存储
application: 可以将信息保存在服务器中，直到服务器关闭，作用域为整个应用，类似于系统的全局变量。
out: 用于在Web浏览器内输出信息，并且管理应用服务器上的输出缓冲区。
pageContext: 作用是取得任何范围的参数，可以获取JSP页面的out，request，response，session，application等对象。
config: config对象主要作用是获得服务器的配置信息。通过pageContext.getServletConfig()获取。
page: 类似于this指针，代表当前JSP对象，仅在当前JSP有效。
exception: 显示异常信息，只有在包含isErrorPage="true"的页面中才会被使用
###6.3 说一下 jsp 的 4 种作用域？
application: 所有应用中有效
session: 当前会话中有效
request: 当前请求中有效
page: 当前页面有效
###6.4 session 和 cookie 有什么区别？
cookie是客户端浏览器存储的用户访问信息，在发起请求的时候一起传输给服务器，服务器根据cookie进行相关处理
session是服务器端存储的用户信息，在会话中用于对不同用户进行识别和相应处理。
###6.5 说一下 session 的工作原理？
在用户第一次给服务器发送请求时，服务器创建一个与该用户相对应的session存储用户的登录信息，并将sessionId返回给客户端，
客户端在之后的每次请求中携带sessionId，服务器根据sessionId对查询到session之后进行后续的操作。
###6.6 如果客户端禁止 cookie 能实现 session 还能用吗？
看情况，如果sessionId存储在cookie中则不能，如果将sessionId放在其他地方如url或者header中依旧可以使用session机制。
###6.7 spring mvc 和 struts 的区别是什么？
###6.8 如何避免 sql 注入？
1. 使用PreparedStatement，对入参进行预编译
2. 白名单，某些对数据库增删改查请求只能包含特定的入参时可以使用
3. 使用正则表达式对入参进行校验
4. 黑名单，设定某些字段不能包含可能导致sql注入的字符
###6.9 什么是 XSS 攻击，如何避免？
###6.10 什么是 CSRF 攻击，如何避免？
#7 异常
#8 网络
###8.1 三次握手
客户端syn SeqNumC
服务端sync Ack=SeqNum + 1 SeqNumS
客户端syn Ack=SeqNumS + 1 SeqNum
#9 设计模式
###9.1 说一下你熟悉的设计模式？
自己：1. 单例模式，将对象实例化之后赋值为类的私有变量，并且提供一个可以获取该变量的public方法，避免了对象的重复创建。
     2. 工厂模式，对于相同类别的对象定义底层抽象类或者接口后利用多态实现多个类，然后编写工厂类根据入参创建所需要的类。（不符合开闭原则）
     2.1抽象工厂，工厂也是抽象类，具体的创建过程由子类实现。
     3. 模板模式，根据完成某些类似任务的流程顺序编写出流程模板，完成任务的部分或者每个步骤可以使用抽象方法，子类根据任务的具体情况实现抽象方法完成任务。
     4. 适配器模式，将某些类转换为统一的格式时或字段。
     5. 观察者模式，
     6. 监听模式
     7. 代理模式，Hibernate延迟加载
     8. 建造者模式，
###9.2 简单工厂和抽象工厂有什么区别？
简单工厂，实体类对象都是由工厂创建，不符合开闭原则，被创建的对象可能会由多种属性。这样就要修改
抽象工厂，提供了工厂类的抽象实现方法，生成具体的目标对象时需要创建抽象工厂的子类后再根据入参创建目标类，方便扩展。
#10 Spring/Spring MVC
###10.1 为什么要使用 spring？
1. Spring是一个轻量级的非侵入性的bean生命周期管理框架。利用IOC和DI进行bean实例的管理和注入，避免了bean对象的重复创建，
答案:
1. Spring时全面的模块化的具有分层结构的框架
2. 轻量级：一个完整的spring框架编译的可发布JAR包只有1M；
3. 非侵入：Spring中的对象不依赖于任何Spring框架的类。
4， IOC：bean的生命周期管理全部交给IOC容器，在需要依赖其他对象时，目标对象会被被动地传入，不需要自己new实例。
5. 面向切面：Spring提供了面向切面的编程，允许通过分离应用的业务逻辑与系统及服务。应用对象处理时仅实现自己的核心逻辑，
   其他类似日志或事务管理可以交给切面处理。
6. 框架：Spring可以整合其他组件和配置组成一个复杂的应用。
7. Spring MVC：客户端请求发送到服务器后，由DispatcherServlet负责查找HandlerMapping获得对应的处理器，调用处理器
   得到处理器返回的结果生成ModelAnView，后交给视图处理器处理得到对应的视图后将response返回给用户。
###10.2 解释一下什么是 aop？
AOP是面向切面编程，是OOP的延续。AOP是Spring的一大特色，它能够将业务中的核心逻辑与如进出口日志，事务管理等周边逻辑解耦，降低代码复杂度。
实现方式是动态代理。
###10.3 解释一下什么是 ioc？
IOC Inversion of Control 控制反转，Spring中的IOC是指在服务启动时实例化并管理所有的bean对象的声明周期，在需要依赖时自动注入。
###10.4 spring 有哪些主要模块？
不会
答案：
1. Spring AOP 面向切面
2. Spring ORM Hibernate、Mybatis、JDO 对象关系映射
3. Spring Core IOC、bean工厂
4. Spring Dao JDBC 规范应用程序与数据库如何访问的程序接口
5. Spring Context 提供了关于UI的支持
6. Spring Web 提供web支持
7. Spring MVC 提供webmvc，javax，jsp支持
###10.5 spring 常用的注入方式有哪些？
不会
答案：
1. 构造方法注入，service这个bean的构造方法中有一个入参类型是Constructor，constructor这个bean通过constructor-arg这个标签传参到service的有参构造方法中，完成bean的注入
注册
<bean id="service" class="a.b.c.Service"><constructor-arg ref="constructor"></constructor-arg></bean>
<bean id="constructor" class="a.b.c.Constructor></bean>
2. setter注入 property标签默认会根据name属性值拼接成set方法(对应set中的setCons(Constructor constructor)方法)，注入已经注入的constructor对象。
<bean id="service" class="a.b.c.Service"><property name="cons" ref="constructor"></property></bean>
<bean id="constructor" class="a.b.c.Constructor"></bean>
3. 注解注入
@Controller @Component @Service...注册bean
@Autowired @Resource
###10.6 spring 中的 bean 是线程安全的吗？
bean根据作用域默认是单例模式，如果bean存在状态，即类似于类变量的情况则不是线程安全的，
如果想得到线程安全的bean需要将其作用域改为prototype，这样每次注入的时候都会new一个新的对象出来
request：每次请求创建一个新的bean
session：每次会话创建一个新的bean
global-session：所有会话共享一个bean
###10.7 spring 支持几种 bean 的作用域？
1. prototype 每次需要依赖bean时都会重新创建一个新的对象
2. singleton 每次获取到的bean对象都是同一个对象，不会创建新的bean对象
3. request   用户每次请求会创建一个新的bean对象
4. session   服务器与用户的每次会话会创建一个新的bean对象
5. global-session 所有会话共享同一个bean
###10.8 spring 自动装配 bean 有哪些方式？
1. no 默认值，表示没有自动装配，需要使用显示的bean引用
1. byName 根据bean名称 @Resources默认
2. byType 根据要加载的bean的类型 @Autowired默认
3. autodetect 首先通过构造函数使用autowired，如果不能则通过byType装配
###10.9 spring 事务实现方式有哪些？
1. 编程式事务，通过java.sql.Connection，设置不提交事务，在执行成功之后调用commit()手动提交事务，在失败之后手动执行rollback()回滚
2. 声明式事务，
    2.1. 添加DataSourceTransactionManager事务管理器和事务代理类TransactionProxyFactoryBean 
    2.2. @Transactional注解声明事务，定义rollbackFor = ....
    2.3 基于AOP和事务管理器，配置和织入切面，在目标方法执行前后执行事务逻辑
###10.10 说一下 spring 的事务隔离？
不会
答案：
1. @Transactional(propagation=Propagation.REQUIRED)，如果有事务就新添加一条事务，没有就新增一个
2. @Transactional(propagation=Propagation.NOT_SUPPORT)，以非事务形式运行，如果存在当前事务，就把当前事务挂起
3. @Transactional(propagation=Propagation.REQUIRES_NEW)，不管是否存在事务直接新建一个事务
4. @Transactional(propagation=Propagation.MANDATORY)，必须等一个事务执行完毕，否则
5. @Transactional(propagation=Propagation.NEVER)，以非事务的方式执行，如果存在事务则抛出异常
6. @Transactional(propagation=Propagation.PROPAGATION_NESTED)，嵌套事务
###10.11 说一下 spring mvc 运行流程？
服务器接收到用户请求后交给DispatcherServlet，DispatcherServlet根据HandlerMapping查询到对应的处理器Handler并调用HandlerAdapter提取处理Handler入参，
Controller处理完结果后生成ModelAndView交还DispatcherServlet，DispatcherServlet调用ViewResolver处理和渲染数据后返回给用户。
###10.12 spring mvc 有哪些组件？ 
1. DispatcherServlet 请求分发
2. Controller        业务处理器
3. HandlerMapping    处理器映射
4. HandlerAdapter    处理器适配
5. HandlerInterceptor 拦截组件
6. ViewResolver      视图处理组件
7. View              视图组件
8. DataBinder        数据转换
9. HttpMessageConverter 消息转换组件
###10.13 @RequestMapping 的作用是什么？
创建方法或者类与url请求的映射关系，定义请求的方式，入参映射
###10.14 @Autowired 的作用是什么？
实现bean自动装配
#11 Spring Boot/Spring Cloud
###11.1 什么是 spring boot？
不会
Spring Boot是一个框架，简化了Spring众多框架中复杂的配置，SpringBoot是一个服务于框架的框架，服务范围是简化配置文件。
###11.2 为什么要用 spring boot？
1. Spring Boot简化了复杂的Spring框架配置，实现了自动化配置，不需要配置很多xml
2. 基于Spring构建，可以集成各式各样的框架
3. SpringBoot不依赖于容器，可以独立运行
4. 内置tomcat，可以直接放到tomcat中运行
###11.3 spring boot 核心配置文件是什么？
application.properties/bootstrap.properties
###11.4 spring boot 配置文件有哪几种类型？它们有什么区别？
.properties和.yml
properties是用key=value的形式
yml用的key:value的形式
yml格式不支持@PropertySource注解导入配置
###11.5 spring boot 有哪些方式可以实现热部署？
1. Spring Loaded
2. spring-boot-devtools
3. JRebel插件
###11.6 jpa 和 hibernate 有什么区别？
JPA是一个规范：JPA目标是标准化JAVA应用程序如何执行ORM
Hibernate是一个实现了JPA规范的ORM框架，
JPA通过JDK.5注解或者XML描述对象-关系表的映射关系，并将运行期的实体对象持久化到数据库中
作用：我们在编程时只需要针对JPA规范类编写，在运行时用Hibernate真正执行持久化工作。
###11.7 什么是 spring cloud？
* Spring Cloud是一个微服务框架，提供一整套微服务分布式系统解决方案
* Spring Cloud对Netflix的微服务框架进行了组装，同时又实现了和云端平台及和SpringBoot开发框架的集成。
* Spring Cloud涉及配置管理，服务治理，熔断机制，智能路由，微代理，控制总线(bus)，一次性token，全局一致性锁，leader选举，
  分布式session，集群状态。
* Spring Cloud提供了一种快速构建分布式微服务的工具。
###11.8 spring cloud 断路器的作用是什么？
在一个微服务出现拥堵或异常的时候，仍能给客户端一个合理的异常返回，防止发生服务雪崩。
###11.9 spring cloud 的核心组件有哪些？
1. Eureka服务注册中心，提供了一个基于rest的服务治理组件，包括服务注册中心，服务注册与发现机制，实现了云端负载均衡和中间层服务器的转移。
2. Hystrix实现断路器模式，通过控制服务器节点，对延迟和故障提供更大的容错能力。
3. Ribbon客户端负载均衡
4. Feign基于Hystrix与Ribbon的声明式服务间调用
5. Zuul微服务网关，提供动态路由，访问过滤，统一认证等服务，易于监控，易于认证
6. Config配置管理工具，支持应用配置的外部化存储，如git，实现动态配置
7. Bus事件，消息总线，用于在集群中传播状态变化。
8. Consul服务发现与配置
#12 Hibernate
###12.1 为什么要使用 hibernate？
Hibernate对JDBC访问数据库地代码进行了封装，大大简化了数据访问层地重复代码
Hibernate支持多种关系型数据库
Hibernate有一个对数据库crud地基础类HibernateTemplate，通过继承它进行特定表的增删改查
Hibernate支持事务管理
###12.2 什么是 ORM 框架？
不会
ORM (Object Relational Mapping)对象关系映射，支持对象与数据库字段的映射，方便对象在不同系统之间的数据的转换。
###12.3 hibernate 中如何在控制台查看打印的 sql 语句？
不会
Spring中可以配置application.properties里
spring.jpa.properties.hibernate.show_sql=true  
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
###12.4 hibernate 有几种查询方式？
不会
1. 使用QBC进行查询
2. 通过编写HQL进行查询
3. 通过自己编写SQL进行查询NativeSQL
###12.5 hibernate 实体类可以被定义为 final 吗？
不会
可以，但由于Hibernate会使用代理模式再延迟关联情况下提高性能，如果定义为final类，则不能使用代理，限制了性能的提升
###12.6 在 hibernate 中使用 Integer 和 int 做映射有什么区别？
查询字段如果是Integer，当没有值时会返回null，如果是int，在该字段没有值时(返回0  错)会报错。
###12.7 hibernate 是如何工作的？
不会
1. 读取解析配置文件hibernate.cfg.xml建立表和映射关系
2. 实例化Configuration对象建立sessionFactory实例后建立session
3. 打开session，创建事务
4. 进行持久化操作
5. 提交事务
6. 关闭session
7. 关闭sessionFactory
###12.8 get()和 load()的区别？
不会
1. get()使用ID查询，会返回一个User对象
   load()延迟加载，当调用时只会返回一个代理对象，代理对象中只存储了ID值，只有当调用ID值意外的属性时才会发出查询语句
2. get()和load()都会缓存，当查询不到时，get返回的时null，load返回的时代理对象

#13 Mybatis
###13.1 mybatis 中 #{}和 ${}的区别是什么？
自己理解：
* \#{}会对入参进行预编译，起到防止sql注入的作用
* ${}会将参数直接传入，不进行预编译，需要自己对入参进行校验
网络：
  \#{}类似与参数占位符?，会对sql进行预编译，执行流程为动态解析->预编译->执行
  ${}为字符串替换，sql拼接，动态解析->编译->执行
###13.2 mybatis 有几种分页方式？
自己回答：offset + limit
网络：
1. 自己从数据库中查出来截取
2. 用sql的分页语句，limit
3. 使用拦截器，拦截mybatis接口方法，
4. 使用PageHelper
###13.3 RowBounds 是一次性查询全部结果吗？为什么？
不会
RowBounds时mybatis用于分页的对象，可以动态构造sql语句，效率较高，不需要全部查询出来。
###13.4 mybatis 逻辑分页和物理分页的区别是什么？
不会
逻辑分页是先查询，然后使用代码去取需要的部分；
物理分页时利用sql自带的limit实现。
###13.5 mybatis 是否支持延迟加载？延迟加载的原理是什么？
支持
mybatis支持一对一和一对多查询，在mybatis配置文件中可以配置是否启用延迟加载，
原理是使用cglib创建目标对象的代理对象，当调用目标方法时，进入拦截器方法，当查询到null值时，会单独发送关联的sql语句进行查询。
###13.6 说一下 mybatis 的一级缓存和二级缓存？
mybatis一级缓存的作用域为一个sqlsession，二级缓存作用域时针对一个mapper。
一级缓存：
一次发起查询时，先从缓存中查询，如果没有则执行sql从数据库查询，查询后加入缓存
当执行写操作时清空缓存，防止脏读。
二级缓存
基于mapper的namespace的缓存。
当一次sqlsession使用完毕close时，会将查询到的数据存入二级缓存
如果其他sqlsession对该表执行了写操作，会清空二级缓存
###13.7 mybatis 和 hibernate 的区别有哪些？
1. mybatis需要手动执行sql语句，hibernate不需要
2. hibernate是全自动的ORM映射，而mybatis不是
3. hibernate数据库移植性较高，mybatis针对不同的数据库需要书写不同的sql执行语句
4. mybatis能比较方便地进行sql优化。
###13.8 mybatis 有哪些执行器（Executor）？
不会
答案:
1. SimpleExecutor: 每次执行update或select，就开启一个Statement对象，用完立刻关闭
2. ReuseExecutor: 执行update或select，以sql为键查找Statement对象，存在就使用，不存在就创建，使用完后存放在map内
3. BatchExecutor: 执行update时，将所有sql都添加到批处理中(addBatch)，等待统一执行(executeBatch)
###13.9 mybatis 分页插件的实现原理是什么？
自己：拦截器
答案: 在插件的拦截方法内拦截将要执行的sql，重写sql
###13.10 mybatis 如何编写一个自定义插件？
不会
新建类并实现Interceptor接口
#14 RabbitMQ
#15 Kafka
###15.1 kafka 可以脱离 zookeeper 单独使用吗？为什么？
不能
kafka集群使用zookeeper进行分布式管理
###15.2 kafka 有几种数据保留的策略？
不会
答案：两种，1.按照存储时间保留，2.按照存储信息大小进行保留
###15.3 kafka 同时设置了 7 天和 10G 清除数据，到第五天的时候消息达到了 10G，这个时候 kafka 将如何处理？
不管达到哪个条件都会删除消息
###15.4 什么情况会导致 kafka 运行变慢？
cpu瓶颈
磁盘瓶颈
网络瓶颈
###15.5 使用 kafka 集群需要注意什么？
集群节点数量不是越多越好，太多节点会导致消息复制时间变长，集群吞吐量降低。且集群数量最好时单数，因为超过半数节点宕机，集群就不能使用了

#17 数据库
###17.1 数据库的三范式是什么？
1NF: 表的每一列都是不可分割的
2NF: 每张表表达一个完整的对象，非主键字段依赖主键
3NF: 表之间不存在传递依赖：一个数据库表中的字段不包含其他表中已有的非主键字段
###17.2 mysql和postgresql的区别
#19 JVM
###19.1 说一下 jvm 的主要组成部分？及其作用？
主要组成：
1. 程序计数器:记录当前程序运行(答案:指示当前字节码行数)
2. 本地方法栈: 线程私有的存储当前本地方法的栈区(记录当前方法栈为虚拟机使用的Native方法服务)
3. 虚拟机栈:线程私有的存储指令的栈区(答案:运行java方法时，会创建一个栈帧用于存储局部变量表，操作数栈，动态链接，方法出口等信息)
4. 堆:线程共享的对象存储区域()
补:
   方法区:用于存储已被虚拟机加载的类信息，类变量信息
###19.2 说一下 jvm 运行时数据区？
JVM运行时数据区分为共享数据区(方法区，JAVA堆)，线程独占区(虚拟机栈，程序计数器，本地方法区)
###19.3 说一下堆栈的区别？
不会
堆内存：存储对象
栈内存：存储线程执行方法时的局部变量
###19.4 队列和栈是什么？有什么区别？
队列是一种先进先出的数据结构，遵从头进尾出的规则
栈是一种先进后出的数据结构，遵从头进头出的规则。
###19.5 什么是双亲委派模型？
在加载类时，类加载器先去寻找父类加载器，如果父类加载器能够加载类则将加载类的行为交给父类加载器；
如果直到启动类加载器都不能加载，则由当前类加载器进行加载。
###19.6 说一下类加载的执行过程？
类的加载分为 加载->验证->准备->解析->初始化->使用->卸载
###19.7 怎么判断对象是否可以被回收？
1. 引用计数法
2. GCRoot追踪（可达性分析）
###19.8 java 中都有哪些引用类型？
强引用、软引用、弱引用、虚引用
###19.9 说一下 jvm 有哪些垃圾回收算法？
复制算法
标记整理法
标记清除法
补充：分代收集算法
###19.10 说一下 jvm 有哪些垃圾回收器？
Serial
Parallel
CMS垃圾回收器
G1垃圾回收
###19.11 详细介绍一下 CMS 垃圾回收器？
CMS垃圾回收器会经过四个阶段：
1. 初始标记，Stop the world
2. 并发标记，不会暂停其他线程
3. 重新标记，stop the world
4. 并发清除，与其他用户线程一起执行
###19.12 新生代垃圾回收器和老生代垃圾回收器都有哪些？有什么区别？
新生代垃圾回收频繁，短时间内会有很多对象产生和回收，一般采用的是复制算法
老生代垃圾回收较少，一般采用标记整理算法
答案: 新生代垃圾回收器：Serial，ParNew，Parallel Scavenge
     老年代垃圾回收器：Serial Old, Parallel Old, CMS
###19.13 简述分代垃圾回收器是怎么工作的？
新产生的对象被存放在年轻代，经过一次垃圾回收后大部分年轻代对象会被回收，小部分经过复制算法会将存活的对象移到To Survivor区并清空Eden和From Survivor
在后面的垃圾回收中存活的对象会在From Survivor和To Survivor之间移动并且每次代计数加一，当达到15代时，依旧存货的对象会被转移到老年代，当老年代内存
占用达到一定值之后会出发FullGC
###19.14 说一下 jvm 调优的工具？
VisualVm
JStack
###19.15 常用的 jvm 调优的参数都有哪些？
1. -Xms起始堆内存
2. -Xmx最大堆内存
3. -XX:SurvivorRatio=8 Survivor区大小该值为Eden:Survivor=8:2



