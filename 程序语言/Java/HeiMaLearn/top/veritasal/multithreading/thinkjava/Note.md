### 多线程引入
1. 什么是多线程
    - 线程是程序执行的一条路径，一个进程中可以包含多个线程
        - 进程：任务管理器里显示的
    - 多线程并发执行提高效率
        -在杀毒软件中可以同时查杀修复，垃圾清理...
        
        
2. 应用 
- 迅雷多线程一起下载(前面，中间，后面一起下)
- 服务器同时处理多个请求

### 多线程并行和并发的区别
- 并行就是两个任务同时进行，就是甲任务进行的同时，乙任务也在进行（需要多核CPU）
- 并发是指二哥任务都请求运行，而CPU只接受一个任务，就把这两个任务轮流安排进行
由于时间间隔较短，使人感觉两个任务都在并行
- 比如与别人聊天，左手和一个人聊，右手和一个人聊，叫并发；先给甲聊再给乙聊叫并发

### Java程序运行原理和JVM的启动是多线程的吗
- Java程序运行原理
    - Java命令会启动JVM，等于启动了一个应用程序，也就是启动了一个进程。
    该进程会自动启动一个“主线程”，然后主线程去调用main方法
- JVM的启动是多线程的吗
    - JVM至少启动了垃圾回收机制和主线程，因此是多线程的### Runnable
- 线程可以驱动任务，因此需要一种描述任务的方式，可以由Runnable接口提供
- 实现其中的run方法

### Executor
1. Executor框架包括3大部分：

    1. 任务。也就是工作单元，包括被执行任务需要实现的接口：Runnable接口或者Callable接口；

    2. 任务的执行。也就是把任务分派给多个线程的执行机制，包括Executor接口及继承自Executor接口的ExecutorService接口。

    3. 异步计算的结果。包括Future接口及实现了Future接口的FutureTask类。

    Executor框架的成员及其关系可以用一下的关系图表示：

![avatar](https://img-blog.csdn.net/20180319221031756)

2.Executor框架的使用示意图：
![avatar](https://img-blog.csdn.net/20180319222418739)

3.Executor框架成员
![avatar](https://img-blog.csdn.net/20180318215737261)

#### ThreadPoolExecutor
-CachedThreadPool

> CachedThreadPool适用于执行很多短期异步任务的小程序，或者是负载较轻的服务器。

CachedThreadPool使用SynchronizedQueue作为阻塞队列，SynchronizedQueue是不存储元素的阻塞队列，实现“一对一的交付”，也就是说，每次向队列中put一个任务必须等有线程来take这个任务，否则就会一直阻塞该任务，如果一个线程要take一个任务就要一直阻塞知道有任务被put进阻塞队列。

因为CachedThreadPool的maximumPoolSize为Integer.MUX_VALUE，因此CachedThreadPool是无界的线程池，也就是说可以一直不断的创建线程。corePoolSize为0 ，因此在CachedThreadPool中直接通过阻塞队列来进行任务的提交。

CachedThreadPool的工作流程大概如下：

- 首先执行SynchronizedQueue.offer(  )把任务提交给阻塞队列，如果这时候正好在线程池中有空闲的线程执行SynchronizedQueue.poll( )，那么offer操作和poll操作配对，线程执行任务；
- 如果执行SynchronizedQueue.offer(  )把任务提交给阻塞队列时maximumPoolSize=0.或者没有空闲线程来执行SynchronizedQueue.poll( )，那么步骤1失败，那么创建一个新线程来执行任务；
- 如果当前线程执行完任务则循环从阻塞队列中获取任务，如果当前队列中没有提交（offer）任务，那么线程等待keepAliveTime时间，在CacheThreadPool中为60秒，在keepAliveTime时间内如果有任务提交则获取并执行任务，如果没有则销毁线程，因此最后如果一直没有任务提交了，线程池中的线程数量最终为0。

> *注意：因为maximumPoolSize=Integer.MAX_VALUE，因此可以不断的创建新线程，这样可能会CPU和内存资源耗尽。


- FixedTreadPool

> FixedThreadPool是线程数量固定的线程池，适用于为了满足资源管理的需求，而需要适当限制当前线程数量的情景，适用于负载比较重的服务器。

<br>

可以看出它的实现就是把线程池最大线程数量maxmumPoolSize和核心线程池的数量corePoolSize设置为相等，并且使用LinkedBlockingQueue作为阻塞队列，
那么首先可以知道线程池的线程数量最多就是nThread，只会在核心线程池阶段创建，此外，因为LinkedBlockingQueue是无限的双向队列，因此当任务不能立刻执行时，
都会添加到阻塞队列中，因此可以得到FixedThreadPool的工作流程大致如下：

- 当前核心线程池总线程数量小于corePoolSize，那么创建线程并执行任务；
- 如果当前线程数量等于corePoolSize，那么把 任务添加到阻塞队列中；
- 如果线程池中的线程执行完任务，那么获取阻塞队列中的任务并执行；

*注意：因为阻塞队列是无限的双向队列，因此如果没有调用shutDownNow（）或者shutDown（）方法，线程池是不会拒绝任务的，如果线程池中的线程一直被占有，FixedThreadPool是不会拒绝任务的。

因为使用的是LinkedBlockingQueue，因此maximumPoolSize，keepAliveTime都是无效的，因为阻塞队列是无限的，因此线程数量肯定小于等于corePoolSize，因此keepAliveTime是无效的；


- SingleThreadExecutor

> SingleThreadExecutor是只有一个线程的线程池，常用于需要让线程顺序执行，并且在任意时间，只能有一个任务被执行，而不能有多个线程同时执行的场景。

因为阻塞队列使用的是LinkedBlockingQueue，因此和FixedThreadPool一样，maximumPoolSize，keepAliveTime都是无效的。corePoolSize为1，因此最多只能创建一个线程，SingleThreadPool的工作流程大概如下：

- 当前核心线程池总线程数量小于corePoolSize（1），那么创建线程并执行任务；
- 如果当前线程数量等于corePoolSize，那么把 任务添加到阻塞队列中；
- 如果线程池中的线程执行完任务，那么获取阻塞队列中的任务并执行；


##### Future
只有在运算出结果时才会结束，获得未来的计算结果