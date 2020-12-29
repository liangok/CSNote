 最近在学习Java，顺便把学习笔记整理出来，所有代码均在[代码测试](http://note.youdao.com/noteshare?id=589c0b377eacd2016eac8210221ee281&sub=B87F294056FB426CB535A0A5D78627BA)
 
# 对象导论
写的特别易懂，适合精读一遍。
# 初始化与清理
首先了解一下一个对象诞生的过程

```
graph LR
构造类-->创建对象
创建对象-->分配存储空间
分配存储空间-->调用对应的构造器
```
在这一章里我主要记录了方法重载，this与static两个关键子，还有成员的初始化顺序。

### 方法重载

在创建对象的同时，也就给对象分配到的空间取了个名字，而方法就是给动作取得名字，构造器的名字与类的名字相同，如果我们想要用不同的方式创建一个对象该怎么办呢？这时就用到了方法重载，对构造器赋予不同的参数，就可以创建多个构造器，除了构造器，普通方方法也可以进行重载。但是这样就要求重载的方法都有一个**独一无二**的参数类型列表。

基本类型可以自动提升到一个“较大”的类型，如果这个过程涉及到重载会发生什么呢？
1. 如果传入的数据类型（实参类型）小于方法中的形式参数类型，实际数据类型提升。
2. 如果无法找到接受char参数的方法就会把char提升为int形。
3. 如果实际参数大于重载方法声明的形式参数，就会报错，除非强制类型转换。


### this

this指调用该方法的当前对象，this主要有以下几个用途

1. 在return中使用this可以在一条语句中对同一对象执行多次操作。实列见《Thinking in Java》。
2. 将当前对象传给其他方法。（传递自己这个对象）
3. 在一个构造器中调用另一个构造器。（this可以调用一个构造器，且必须放在最起始处）
4. 当参数名字和数据成员的名字相同时，可用this代表数据成员从而消除歧义。

注意除了构造器外，其他方法不可以调用其他构造器。

### static

我们在创建类时只是在描述类的对象的外观，只有在new那个类时，才会获的对象，才会分配空间。但如果我们想要<html><font color=blue>只是为特定区域分配单一存储空间而不考虑创建对象或者希望某个方法不与包含它的那个类的任何对象实例关联在一起，也就是说没有创建对象也能调用这个方法</font></html>这时就需要static关键字。

把static放在字段或方法前就可以将字段或方法定义为static，引用static的字段或方法又两种方法：
1. 通过对象去定位它。
2. 直接通过类引用。

结合刚才所说的this，static方法就是没有this的方法。static方法内不能调用非静态方法（除非传递一个对象的引用到静态方法里，然后用这个引用来访问非静态的方法和数据成员，但这样就没有必要创建这个静态方法了。）反过来可以。static的主要用途是在没有对象的前提下，仅仅通过类本身来调用static方法。而且无论创建多少对象，静态对象只占用一份存储域。

### 成员初始化
Java尽力保证，所有的变量在使用前都能得到初始化。<html><font color=green>对于方法的局部变量，若未进行初始化，则编译器会报错，因为未初始化的局部变量很有可能是程序员的错误，采用默认值则会掩盖这个错误；</font></html><html><font color=blue>而类中的成员变量则会自动初始化；</font></html><html><font color=	#00FFFF>若在类里定义一个对象引用时，未将其初始化，则会给一个默认值null。</font></html>
##### 构造器的初始化
在类的内部，变量按定义的先后顺序来初始化，即使变量定义散布于方法定义之间，它们仍然会在任何方法（包括构造器）被调用之前得到初始化，若一个在构造器外部创造了一个对象，在构造器内部又创造了这个对象，则这会个引用会被初始化两次，一次在构造器调用前，一次在调用期间。
##### 静态数据的初始化
static不能作用于局部变量，因此它只能作用于域。静态初始化只在必要时（用的到它）才会初始化，且只能初始化一次。
初始化顺序为：

```
graph LR
静态对象-->非静态对象

```

总结一下对象的创建过程，以创建Dog类为例
1. 构造器实际上是静态方法，因此，当首次创建Dog的对象时，或Dog的静态方法/静态域首次被访问时，Java解释器要查找类路径，定为Java.class文件
2. 载入Dog.class（这将创建一个Class对象），有关讲台初始化的所有动作都会执行，因此静态初始化只在Class对象首次加载的时候进行一次。
3. 当new Dog()创建对象时，首先在堆上为Dog对象分配足够的存储空间。
4. 这块存储空间会被清零，自动将Dog对象中所有的基本类型设置成了默认值，引用被设为null
5. 执行所有出现于字段定义处的初始化动作。
6. 执行构造器。


>总结一下：
>1. [类加载]
    a. 基类静态代码块，基类静态成员字段（并列优先级，按代码中出现先后顺序执行）（只有第一次加载类时执行）
    b. 派生类静态代码块，派生类静态成员字段 （并列优先级，按代码中出现先后顺序执行）（只有第一次加载类时执行）
>2. [分配空间]  创建对象时，首先在堆上为对象分配足够的存储空间，这块存储空间会被清零，自动对象中所有的基本类型设置成了默认值，引用被设为null。
>3. 基类普通代码块，基类普通成员字段 （并列优先级，按代码中出现先后顺序执行）
>4. 基类构造函数
>5. 派生类普通代码块，派生类普通成员字段 （并列优先级，按代码中出现先后顺序执行）
>6. 派生类构造函数
静态成员变量--->static 代码块--->普通成员变量--->普通代码块
>一二阶段是类加载，三四是对象创建
>在static阶段还没有初始化普通成员变量


##### 数组初始化
数组是形态相同的，用一个标识符名称封装到一起的一个对象序列或基本类型数据序列。要定义一个数组只需要在类名后加一个[]即可。

编译器不允许指定数组的大小，现在拥有的只是对数组的一个引用，而且并没有为数组对象分配任何空间。

数组的初始化有两种方法：直接用{},或用new初始化 *(new int[8])*。
# 复用类
复用就是对于重复的代码不在重复编写，而是以复用的方法打到一次书写，多次使用的目地。
而实现复用又两种方法：组合与继承。组合只需将对象引用置于新类中即可。


在这一章中，我记录了继承，代理，向上转型，以及fianl关键字。

### 继承
在创建一个类时，继承总是会发生的，若没有指出从其它类中继承，就是从Object中继承。继承时，一般所有数据成员为private，而方法为public。<html><font color=#00FFFF>在创建一个导出类时，该对象包含了一个基类的子对象。这个子对象与使用基类直接创建时一样的，区别在于后者来自外部，而基类的子对象被包装在导出类对象内部。构建过程是从“基类”向外扩散的，基类在导出类构造器可以访问它之前就已经完成了初始化。</font></html><html><font color=red>对于默认构造器，或不带参数的构造器，编译器可以自动调用，但对于有参数的构造器，要用super语句显示的编写调用基类构造器的语句，而且调用基类构造器必须是在导出类构造器中要做的第一件事，</font></html>

了解了继承，在来看一下protected，protected是指对于类用户来说这是private的，对于继承于此类的导出类或其他为于同一个包中的类来说它是可以访问的。

组合与继承都允许在新类中放置子对象，组合是显示的这样做，而继承是隐式的。那么我们怎样在这两种之间做出选择呢？如果想在新的类中使用现有类的功能而非它的接口这种情形，也就是说只是在用别的类来构建这个对象。而继承是使用现有的类，开发一个它的新版本。


**重载与重写：** 由于继承关系，在清理时也要先清理子类在清理父类。如果Java的基类拥有某个已被多次重载的方法名称并不会屏蔽其在基类中的版本，无论是在该层或者它的基类中对方法进行定义，重载都可以正常工作。若只想重写而不重载可以用@Override



### 代理
代理是继承与组合之间的中庸之道，我们将一个成员对象置于所要构造的类中（就像组合），并且在我们的新类中暴露了该成员对象的所有方法（就像继承），看一个实例会比较清楚。 *（测试在《代码测试》）*

### 向上转型

为什么要向上转型呢？

向上转型更好的体现了类的多态性，增强了程序的间接性以及提高了代码的可扩展性。当需要用到子类特有的方法时可以向下转型，这也就是为什么要向下转型。

### final关键字
根据上下文环境，final有者不同的意思，但通常指的是“这是无改变的”，接下来将会依次介绍使用final的三种情况。

**final数据**

当我们需要
<html><font color = red>

- 一个永不改变的编译时常量。

- 一个在运行时被初始化的值，而你不希望它被改变。

</font></html>

这时就需要用到final。

注意一个既是static又是final的域（一般用大写表示）只占据一段不可改变的的存储空间。

对于基本类型，final使数值恒定不变，而对于一个对象，final使引用恒定不变。一旦引用被初始化指向一个对象，就无法把它再指向另一个对象。

我们不能认为某数据是final就认为再编译时期可以知道它的值，再运行时使用随机生成的数据来初始化就不可以。

###### 空白final
被声明为final却没有赋予初始值，这样，一个类中的final域就可以做到根据对象而有所不同，却又保持恒定不变的特征。举个列子

```
class PiedPiper{
    private int i;
    PiedPiper(int ii){i = ii;}

    public int getI() {
        return i;
    }
}
public class BlankFinal {
    private final int j;
    private final PiedPiper p ;
    public BlankFinal(){
        j = 1;
        p = new PiedPiper(32);
        System.out.println(j + " / " + p.getI());
    }
    public BlankFinal(int x){
        j = x;
        p = new PiedPiper(x);
        System.out.println(j + " / " + p.getI());
    }

    public static void main(String[] args) {
        new BlankFinal();
        new BlankFinal(54);
    }
}

```
注意构造器中一定要给空final赋值，否则会报错。

**final参数**

使用了就意味着无法再方法中更改参数引用的对象，可以读取参数但无法改变参数。

**final方法**

<html><font color=	#FF00FF>类中所有的private方法都隐式的指定为final，由于无法取用private方法，也就无法覆盖它，可以对private加final，但没有额外的意义。</font></html>
使用final方法的原因是把方法锁定，以防任何继承类修改它的含义。同时如果父类中的这个方法使用了private那么在子类也是不能重写这个方法。
<br><br>
覆盖只有在某方法是基类的接口的一部分时才会出现（必须能将一个对象向上转型为它的基本类型并调用相同的方法。）
<br><br>

**final类**

当使用final类时，表明了你不打算继承该类，而且也不允许别人这样做。final类中给方法加final没有意义。

**初始化及类的加载**

每个类的编译代码都存在于他自己的独立文件中，该文件只有在需要使用程序代码时才会加载，初次使用时所有的static对象和代码都会在加载时按顺序依次初始化。

对于继承的类，编译器通过extends注意到它有一个基类，于是他会隐式的生成一个基类的对象，顺序为根基类中的static初始化会被执行，然后时下一个导出类，以此类推。类加载完毕后，开始创建对象，对象中所有的基本类型都会被设置为默认值，对象引用被设置为null，基类构造器被调用，创建对象。

```
graph LR
按顺序初始化static数据或方法-->调用基类构造器
调用基类构造器-->调用子类构造器
```

# 多态

面对对象的设计语言，有三种基本特征

- 抽象
- 继承
- 多态

多态通过分离做什么和怎么做，从另一角度将接口和实现分离开来，作用则是消除类型之间的耦合关系。多态是同一个行为具有多个不同表现形式或形态的能力，或者说同一个接口，使用不同的实例而执行不同操作

我们知道继承中允许将多种从基类中导出的类型视为同一类型处理，同一份代码也就可以无差别的运行在不同的类型（通过向上造型）。多态方法则允许一种类型通过方法行为不同表现出于其他相似的类型的区别，虽然这些方法都可以通过同一个基类来调用。

注意区分多态与重写，多态是调用父类的方法。多态的实现方法有
- 重写
- 接口
- 抽象类和抽象方法

**方法调用绑定**

将一个方法调用同一个方法主体关联叫做绑定，若在程序前期由编译程序实现的绑定叫前期绑定，后期绑定则是在运行时根据对象的类型绑定。Java中除了static和final（private属于final）外，其他的方法都是后期绑定

**注意**

- ###### 覆盖私有方法：因为private方法自动认为是final方法，且对导出类屏蔽，则无法被覆盖。
- ###### 如果某个方法时静态的，它的行为就不具有多态性。
- ###### 域与静态方法：只有普通方法调用可以是多态的。这个要看一下列子。


```
class Super{
    public int field = 1;
    public int getField(){return field;}
}
class Sub extends Super{
    public int field = 2;

    //@Override
    public int getField() {
        return field;
    }
    public int getSuperField(){return super.field;}
}
public class FieldAccess {
    public static void main(String[] args) {
        Super sub = new Sub();  //Upcast
        System.out.println("sup.field = " + sub.field + ", sub.getField() = " + sub.getField());
        Sub sub1 = new Sub();
        System.out.println("sup.field = " + sub1.field + ", sub.getField() = " + sub1.getField() + ", sub.getSuperField() = " + sub1.getSuperField());
    }
}
/*
输出:
sup.field = 1, sub.getField() = 2
sup.field = 2, sub.getField() = 2, sub.getSuperField() = 1
*/
```
在这个例子里，基类的方法和域都是public的，在这种情况下，向上造型时，域用基类的，方法用自己的（但是一般会将域设置为private的，不能直接访问）

**构造器和多态**
构造器方法一般被隐式的声明为static方法，在调用导出类构造器之前，必须先调用基类构造器，从而对基类中private成员初始化。在清理时，不要忘了清理基类对象。销毁顺序要与初始化顺序相反。如果成员对象中存在于其他一个或多个对象共享的情况时，需要引用计数器跟踪访问着共享对象的数量。
*(代码见代码示例)*

**构造器内部的多态方法的行为**

创建一个子类对象，在调用父类构造器时，构造器内部的方法A如果在子类中被覆盖，则在调用父类构造器时，调用的时子类的这个方法A<font color = blue>在构造器中唯一能安全调用的方法是基类中的final方法，或private（属于final），这些方法不可以被覆盖。</font>

注意，初始化的实际过程是
<font color = red>
1. 在其他任何事物发生前，将分配给对象的存储空间初始化为二进制的0。
2. 调用基类构造器。
3. 按照声明的顺序调用成员的初始化方法。
4. 调用导出类的构造器主体。
</font>

**协变返回类型**

第164页

**继承设计**

- 优先选则组合，这回减少我们的设计负担。
- 引用在运行时可以与另一个不同的对象重新绑定起来。
- 尽量使用纯粹的继承。向下转型可以获取类型的信息，注意向下转型时，会进行"RTTI"(运行时类型识别)。


# 接口


**抽象类和抽象方法**

如果想要仅表示一个接口，没有具体的内容实现，并且直接调用其中方法时会产生错误，这是就用抽象方法。包含抽象方法的类必须限定为抽象类。

注意：抽象类无法实列化。否则会报错。且抽象方法必需在导出类中实现，否则要将导出类设为abstract。

**接口与抽象类的区别**
- 抽象类要被子类继承，接口要被类实现。
- 接口只能做方法声明，抽象类中可以作方法声明，也可以做方法实现。
- 接口里定义的变量只能是公共的静态的常量，抽象类中的变量是普通变量。
- 接口是设计的结果，抽象类是重构的结果。
- 抽象类和接口都是用来抽象具体对象的，但是接口的抽象级别最高。
- 抽象类可以有具体的方法和属性，接口只能有抽象方法和不可变常量。
- 抽象类主要用来抽象类别，接口主要用来抽象功能。


**接口**

abstract允许在类中创建一个或多个没有任何定义的方法，提供了接口部分，而interfac则产生一个完全抽象的类，没有任何具体实现，只允许确定
- 方法名
- 参数列表
- 返回类型

注意：
- 创建接口时要用interfa代替class关键字。
- toString（）方法是根类Object的一部分因此不需要出现在接口中。
- 接口可以包含域，但这些域隐式的是static和final的。
- 子类必需要继承父类的抽象方法，否则就要将类定义为抽象的。
- 抽象类继承接口时可以不用实现所有方法，但继承抽象类的子类必须要实现。
<font color = red> 
#### 接口中方法是public的，这是强制的，因为如果不这样，在方法继承过程时，可访问权限会被降低。</font>


测试心得

-使用接口时无法向上造型，因为向上造型后方法都不可用。这时可以向上造型为Object类。再在对应的方法中进行强制类型转换。 *(见代码测试)*

**完全解耦**

接口可以突破继承关系，在别的包里用。

**策略设计模式**

创建一个能够根据所传递的参数对象的不同而具有不同行为的方法。策略包含变化的部分，就是传进去的参数对象。在以下例子里策略是Processor对象。有两种不同的策略用到了s上。

实际上，策略就是一个基类，负责接收子类对象，再向上造型,方法还是用子类的.这个设计模式也可以让客户端依据接口设计自己的类。

```
import java.util.Arrays;

class Processor{
    public String name(){
        return getClass().getSimpleName();
    }
    Object process(Object input){return input;}
}
class Upcase extends Processor{
    String process(Object input){
        return ((String)input).toUpperCase();
    }
}
class Splitter extends Processor{
    String process(Object input){
        return Arrays.toString(((String) input).split(" "));
    }
}
public class Apply {
    public static void processor(Processor p, Object s){
        System.out.println("Using Processor" + p.name());
        System.out.println(p.process(s));
    }
    public static String s = "Effective Java";
    public static void main(String[] args){
        processor(new Upcase(), s);
        processor(new Splitter(), s);
    }
}

```
再来看一段代码，这个代码在filters包中。


```
package interfaces.filters;

public class Waveform {
    private static long counter;
    private final long id = counter++;

    @Override
    public String toString() {
        return "Waveform{" +
                "id=" + id +
                '}';
    }
}




package interfaces.filters;

public class Filter {
    public String name() {
        return getClass().getSimpleName();
    }
    public Waveform process(Waveform input){return input;}
}





package interfaces.filters;

public class LowPass extends Filter{
    double cutoff;
    public LowPass(double cutoff){this.cutoff = cutoff;}
    public Waveform process(Waveform input){return input;}
}




package interfaces.filters;

public class HighPass extends Filter {
    double cutoff;
    public HighPass(double cutoff){this.cutoff = cutoff;}
    public Waveform processor(Waveform input){return input;}
}




package interfaces.filters;

public class BandPass extends Filter {
    double lowCutoff, highCutoff;
    public BandPass(double lowCut, double highCut){
        lowCutoff = lowCut;
        highCutoff = highCut;
    }
    public Waveform processor(Waveform input){return input;}
}

```
Filter和Processor具有相同的接口元素，但是因为不是继承关系，所以不能将Filter用于Apply.process()方法。这是因为Apply.process()与Processor之间耦合过紧。但如果Processor是一个接口，耦合就会大大减少。

再来看一下Peocessor和Apply的修改版本，接下来的内容都在interfaceprocessor中。


```
package interfaces.interfaceprocessor;

public interface Processor {
    String name();
    Object process(Object input);
}





package interfaces.interfaceprocessor;

public class Apply {
    public static void processor(Processor p, Object s){
        System.out.println("Using Processor " + p.name());
        System.out.println(p.process(s));
    }
}



这个是客户端程序员按该接口编写的类：

package interfaces.interfaceprocessor;

import java.util.Arrays;

public abstract class StringProcessor implements Processor{
    public String name(){
        return getClass().getSimpleName();
    }
    public abstract String process(Object input);
    public static String s = "If she weighs the same as a duck, she's made of wood";

    public static void main(String[] args) {
        Apply.processor(new Upcase(), s);
        Apply.processor(new Downcase(), s);
        Apply.processor(new Splitter(), s);
    }
}
class Upcase extends StringProcessor{
    @Override
    public String process(Object input) {
        return ((String)input).toUpperCase();
    }
}

class Downcase extends StringProcessor{
    @Override
    public String process(Object input) {
        return ((String)input).toUpperCase();
    }
}

class Splitter extends StringProcessor{
    @Override
    public String process(Object input) {
        return Arrays.toString(((String) input).split(" "));
    }
}

```
这时，无法修改我想要使用的类，在电子滤波器中类是被发现而不是被创造的。因此可以使用适配器模式。

**适配器策略模式**

适配器中的代码可以接受你所拥有的接口，并产生你所需要的接口。


```
package interfaces.interfaceprocessor;
import interfaces.filters.*;

/*我有一个Filter，我想让Filter用于Processor的接口，怎么办呢？
* 这时，以FilterAdapter的构造器作为承接的桥梁，注意啊，FilterAdapter实现了Processor接口，而他的构造器又调用了Filter
* 同时，FilterAdapter中的方法可以对Filter进行操作*/
class FilterAdapter implements Processor{       //FilterAdapter的构造器接受你所拥有的接口，Filter，用于你所要用的Processor接口的对象。同时FilterAdapter也用到了代理。
    Filter filter;
    public FilterAdapter(Filter filter){
        this.filter = filter;
    }
    public String name(){return filter.name();}

    public Waveform process(Object input){
        return filter.process((Waveform)input);
    }
}
public class FilterProcessor {
    public static void main(String[] args) {
        Waveform w = new Waveform();
        Apply.processor(new FilterAdapter(new LowPass(1.0)), w);
        Apply.processor(new FilterAdapter(new HighPass(2.0)), w);
        Apply.processor(new FilterAdapter(new BandPass(3.0, 4.0)), w);
    }
}

```


**多重继承**

如果要从一个非接口的类继承，那么只能从一个类去继承，其余的必须是接口。具体见代码示例。当然还可以同过继承在新接口中组合数个接口。

在实现多重继承时，不要给组合的接口起同一个名字，否则覆盖，实现，重载就会搅在一起。

**适配接口**
见Thinking in Java 183页

**嵌套接口**

接口可以嵌套在其他的接口中。来看一段代码

```
class A{
    interface B{
        void f();
    }
    public class BImp implements B{
        @Override
        public void f() {}
    }
    public interface C{
        void f();
    }
    class CImp implements C{
        @Override
        public void f(){}
    }
    private class CImp2 implements C{
        @Override
        public void f() {

        }
    }
    private interface D{
        void f();
    }
    private class DImp implements D{
        @Override
        public void f() {

        }
    }

    public class DImp2 implements D{
        @Override
        public void f() {

        }
    }

    public D getD(){return new DImp2();}
    private D dRef;
    public void receiveD(D d){
        dRef = d;
        dRef.f();
    }
}

interface E{
    interface G{
        void f();
    }
    public interface H{     //Redundant "public"
        void f();
    }
    void g();
    //Cannot be private within an interface
    //private interface I {} ;    Cannot write like this.
    //嵌套在另一个接口中的接口自动被认为是public的，不可以设为private的

}
public class Adventure{
    public class BImp implements A.B{
        @Override
        public void f() {

        }
    }
    class CImp implements A.C{
        @Override
        public void f() {

        }
    }

    /*Cannot implement a private interface except
    * within that interface's defining class(不能实现私有接口，除非在接口的定义类中）
    * class DImp implement A.D{
    *   public void f(){}
    * }*/
    class EImp implements E{
        public void g(){}
    }
    class EGImp implements E.G{
        public void f(){}
    }
    class EImp2 implements E {
        @Override
        public void g() {

        }

        class EG implements E.G {
            @Override
            public void f() {

            }
        }
    }
    public static void main(String[] args) {
        A a = new A();
        /*Cannot access A.D
        * ! A.D as = a.getD();
        * Dosen;t return anything but A.D
        * A.DImp2 di2 = a.getD();
        * Cannot access a member of the interface
        * a.getD().f();
        * Only another A cando anything with getD()*/
        A a2 = new A();
        a2.receiveD(a.getD());
    }
}


```

接口可以被时限为private的，private的接口只能被时限为DImp中的一个private内部类，同时由A.DImp2可知它也可以被实现位public类的。但是他只能被自身使用，无法说它实现了一个private接口，实现一个private接口只是一种方式，可以强制该接口中方法不可以添加任何类型信息（也就是说不可以向上转型）

getD()方法只有另一个A可以接受。


**工厂方法设计模式**


```
interface Service{
    void method1();
    void method2();
}
interface ServiceFactory{
    Service getService();
}
class Implementation1 implements Service{
    Implementation1(){};

    @Override
    public void method1() {
        System.out.println("Implementation1 method1");
    }

    @Override
    public void method2() {
        System.out.println("Implementation1 method2");
    }
}
class Implementation1Factory implements ServiceFactory{
    @Override
    public Service getService() {
        return new Implementation1();
    }
}

class Implementation2 implements Service{
    Implementation2(){};

    @Override
    public void method1() {
        System.out.println("Implementation2 method1");
    }

    @Override
    public void method2() {
        System.out.println("Implementation2 method2");
    }
}
class Implementation2Factory implements ServiceFactory{
    @Override
    public Service getService() {
        return new Implementation2();
    }
}
public class Factories {
    public static void ServiceConsumer(ServiceFactory fact){
        Service s = fact.getService();
        s.method1();
        s.method2();
    }

    public static void main(String[] args) {
        ServiceConsumer(new Implementation1Factory());
        ServiceConsumer(new Implementation2Factory());
    }
}

```

生成遵循某个接口对象的典型方式是工厂方法设计模式。

我们在工厂对象上调用的是创建方法，而该工厂对象生成接口的一个实现的对象，在这里就是返回一个实现的Service对象。这里有一篇文章可以帮助理解一下工厂设计模式。

[工厂模式理解了没有？](https://zhuanlan.zhihu.com/p/37095996)


# 内部类

为什么要用到内部类呢？首先，内部类额可以拥有外围类的所有元素的访问限权。

简单的看一下内部类的写法

```
class Outer{
    class Inner{
        int i = 3;
        public void printi(){
            System.out.println("i is " + i);
        }
    }

    public Inner getInner(){
        return new Inner();
    }
}
public class OuterTest {
    public static void main(String[] args){
        Outer p = new Outer();
        Outer.Inner in = p.getInner();
        //

    }
}
```

当我们在创建外围类的一个内部对象时，内部对象会获得一个外围类的对象的引用，用此引用来访问外部成员。

**.this与.new** ( *p193* )

在内部类中生成对外部类对象的引用：外部类名 + .this。

```
class Outer{

    class Inner{
        public Outer outer(){
            return Outer.this;
        }
    }
    
    public Inner getInner() {
        return new Inner();
    }
    
    @Override
    public String toString() {
        return "Hello";
    }
    
    public static void main(String[] args){
        Outer p = new Outer();
        Outer.Inner in = p.getInner();
        System.out.println(in.outer().toString());
    }
}
```

让其他对象去创建某个内部类的对象，用.new。
```
public class DotNew{
    public class Inner{}
    public static void main(String[] args){
        DotNew dn = new DotNew();
        DotNew.Innwe dni = dn.new Ineer();
    }
}
```

在创建内部类时，必须用外部对象引用，除非是嵌套类（内部静态类）。

**内部类与向上转型**
当内部类在向上转型为基类，尤其是接口时，就可以说是实现了某个接口的对象，得到对此接口的引用，与向上转型功能一样。同时，因为内部类（某个接口的实现可以完全不可见，不可用，得到的只是指向该基类的接口或引用，所以可以隐藏细节。

看看一个例子

```
package innerclass;

public interface Destination {
    String readLabel();
}




package innerclass;

public interface Contents {
    int value();
}




package innerclass;
class Parcel{
    private class PContents implements Contents{
        private int i = 11;

        @Override
        public int value() {
            return i;
        }
    }
    protected class PDestination implements Destination{
        private String label;
        private PDestination(String whereTo){
            label = whereTo;
        }

        @Override
        public String readLabel() {
            return label;
        }
    }
    public Destination destination(String s){
        return new PDestination(s);
    }
    public Contents contents(){
        return new PContents();
    }
}
public class TestParcel {
    public static void main(String[] args){
        Parcel p = new Parcel();
        Contents c = p.contents();
        Destination d = p.destination("Tasmania");
        //! Parcel.PContents pc = p.new PContenst();    Can't access private class
    }
}
```


在这个例子里，我们可以看到，不能直接创建Parcel.PContents 的对象，只能通过p的方法创建。

这样有一个好处，客户端程序员无法了解或访问这些成员，也不能向下转型为private或protect（除非继承自它的子类）内部类，因为不能访问其名字，这样可以阻止依赖类型的编码，并完全隐藏了实现的细节。而客户端程序员也无法访问任何新增的原本不属于公共接口的方法，所以扩展接口是没有价值的。

**在方法和作用域内的内部类**

在之前所提到的都只是内部类的典型用途，如果所读，写的代码包含了内部类，那么它们都是平凡的内部类。

接下来就是在方法里或在任意作用域内定义的内部类。原因如下
<font color = red>
- 创建并返回某个类型的接口的引用。
- 为了解决一个复杂问题，想要创建一个类来辅助你的解决方案，但又不希望这个类是公用的。
</font>

接下来将会有一些应用。

- 一个定义在方法中的类（局部内部类）。<font  color = green>这时内部类是方法的一部分，因此在方法外部不可以访问这个局部类</font>
- 一个定义在作用域中的类，此作用域在方法的内部。
- 一个实现了接口的匿名类。
- 一个匿名类，扩展了有非默认构造器的类
- 一个匿名类，执行字段初始化
- 一个匿名类，通过实例初始化实现构造（匿名类不可以有构造器）


**匿名内部类**


<font color = red>如果匿名内部类使用了局部变量，那么编译器会将使用的值拷贝一份，作为构造函数的一个参数传递进来（构造函数是编译器自动添加），假如传递到匿名内部类的局部变量，不加final修饰，那么意味着局部变量可以改变，这就意味着匿名内部类里面值的变更和外部的变量的变更不能同步，虽然内部类持有的是局部变量值的拷贝，但是语义应该保持一致，语义保持一致的前提是值要能同步，因为java编译器的设计无法提供两边同步变更的机制，所以直接锁死，不允许内外变更</font>

先看个例子
```
public class Parcel{
    public Contents contents(){
        return new Contents{
            private int i = 11;
            public int value(){return i}
        }
    }
    public static void main(string[] args){
        Parcel p = new Parcel();
        Contents c = p.Contents();
    }
}

```
在这里，看起来好像要造一个Contents对象，却又说“等一等，我想在这里插入一个类的定义”


如果我们的基类需要一个有参数的构造器时，就要用一下的办法

```
public class Parcel{
    public Wrapping wrapping(int x){
        return new Wrapping (x){
            public int value(){
                return super.value() * 47;
            }
        };
    }
    public static void main (String[] args){
        Parcel p = new Parcel();
        Wrapping w = p.wrapping(10);
    }
}
```

如果想在匿名内部类中使用一个在其外部定义的对象，那么参数需要是final的。

###### 内部类构造器
如果内部类想要实现构造器的功能时，可以使用实列初始化。

**再访工厂方法**

内部类可以改进工厂方法，从而变得更简单。

```
interface Service{
    void method1();
    void method2();
}
interface ServiceFactory{
    Service getService();
}

class Implementation1 implements Service{
    private Implementation1(){}

    @Override
    public void method1() {
        System.out.println("Method1 :  1");
    }

    @Override
    public void method2() {
        System.out.println("Method2 : 1");
    }
    public static ServiceFactory factory =
            new ServiceFactory() {
                @Override
                public Service getService() {
                    return new Implementation1();
                }
            };
}
class Implementation2 implements Service{
    private Implementation2(){}

    @Override
    public void method1() {
        System.out.println("Method1 : 2");
    }

    @Override
    public void method2() {
        System.out.println("Method2 : 2");
    }
    public static ServiceFactory factory =
            new ServiceFactory() {
                @Override
                public Service getService() {
                    return new Implementation2();
                }
            };
}
public class Factories {
    public static void ServiceCustom(ServiceFactory factory){
        Service s = factory.getService();
        s.method1();
        s.method2();
    }

    public static void main(String[] args) {
        ServiceCustom(Implementation1.factory);
        ServiceCustom(Implementation2.factory);
    }
}
```

**嵌套类**

如果不想内部类与外部类有关系，就用static。但这样意味着
- 嵌套类对象不需要访问外围类的对象。
- 不能从嵌套类的对象中访问非静态的对象。
- 普通类不可以有static数据和字段，也不能包含嵌套类。
- 关于这些在p201的例子上。


- 接口内部也可以有类，只需要定义为static即可，也就是嵌套类。如果你想创建一些公共代码，使得他们可以被某个接口的所有不同实现所供公用，就可以这样使用。
- 嵌套类可以用来测试代码。
- 一个内部类无论被嵌套多少次，都可以轻松透明的访问外围类的所有成员。


**为什么需要内部类**
我们再次回答一下开头的问题，内部类中最吸引人的就是<font color = green>每个内部类都可以独立的继承自一个（接口）的实现，无论外围类是否已经继承了某个（接口）的实现，对内部类无影响。</font> 内部类允许继成多个非接口类型。

如果必需在一个类中实现两个接口时，用单一类和内部类都可以，但是如果拥有的是抽象类，或者具体的类，而不是接口，就只能用内部类实现多重继承。

```
class D{}
abstract class E {}
class Z extend D{
    E makeE(){retun new E(){};}
}

public class MultiImplementation{
    static void takesD(D d){}
    static void takesE(E e){}
    public static void main(String[] args){
        Z z = new Z();
        takesD(z);
        takesE{z.makeE();}
    }
}
```

说了这么多，如果你不需要解决“多重继承”的问题，就可以不用使用内部类。
总结一下内部类的好处

- 可以让多个内部类以不同方式实现同一个接口，或继承同一个类。
- 没有is - a 的关系。


**闭包与回调**
```
interface Incrementable{
    void increment();
}

class Callee1 implements Incrementable{
    private int i = 0;

    @Override
    public void increment() {
        i++;
        System.out.println(i);
    }
}

class MyIncrement{
    public void increment(){System.out.println("Other operation");}
    static void f(MyIncrement mi){mi.increment();}
}

/*如果你的类需要以其他方式实现increment（）你必须要使用内部类*/
class Callee2 extends MyIncrement{
    private int i = 0;
    public void increment(){
        super.increment();
        i++;
        System.out.println(i);
    }
    private class Closure implements Incrementable{
        public void increment(){
            //指定外类方法，否则会得到一个无限递归
            Callee2.this.increment();
        }
    }

    Incrementable getCallbackReference(){
        return new Closure();
    }
}
class Caller{
    private Incrementable callbackReference;
    Caller(Incrementable cbh){callbackReference = cbh;}
    void go (){callbackReference.increment();}
}
public class Callbacks {
    public static void main(String[] args) {
        Callee1 c1 = new Callee1();
        Callee2 c2 = new Callee2();
        MyIncrement.f(c2);
        Caller caller1 = new Caller(c1);
        Caller caller2 = new Caller(c2.getCallbackReference());
        caller1.go();
        caller1.go();
        caller2.go();
        caller2.go();
    }
}
```

来分析下这段代码
- 可以看到MyIncrement和Incrementable中都有increment方法，且方法不同，如果我想继承自MyIncrement但又想使用Incrementable的increment方法时，就要用内部类独立的实现Incrementable。
- Closure不但实现了Incrementable，还提供了一个返回Callee2的钩子。


# 持有对象

用泛型可以保证到ArrayList中元素的类型
方法：get(), add(), 

**添加一组元素**

其中Collections和Arrays都是工具类。

Collection的构造器可以接受另一个Collection，使用Array.List()为这个构造器产生输入。

Collection.addAll()更块，一般都是先构建一个不含元素的Conlletcion再用Colletcion.addAll()。但是，addAll只可以以Conlletion对象作为参数，不如Arrays.asList()或Collections.addAll)灵活。

也可以使用Arrays.asList()的输出，将其当作List，但这样底层表示的是数组，不能调整尺寸

 Arrays.asList()
 
 *Returns a fixed-size list backed by the specified array*
 
 
 
 看一个例子
 ```
 import java.util.*;

public class Test{
    public static void main(String[] args) {
        Collection<Integer> collection = new ArrayList<>(Arrays.asList(1, 2, 3 ,4 ,5 ));
        Integer [] morInts = {6, 7, 8};
        collection.addAll(Arrays.asList(morInts));
        System.out.println(collection.toString());
        Collections.addAll(collection, 11, 12, 13, 14);
        Collections.addAll(collection, morInts);
        List<Integer> list = Arrays.asList(16, 17, 18);
        System.out.println(list.toString());
        list.set(1, 99);
        System.out.println(list.toString());
    }
}
```

接下来就是重点说一下Arrays.asList()

```
import java.util.*;
class Snow{}
class Power extends Snow{}
class Light extends Power{}
class Heavy extends Power{}
class Crusty extends Snow{}
class Slush extends Snow{}
public class Test{
    public static void main(String[] args) {
        List<Snow> snow1 =  Arrays.asList(new Crusty(), new Slush(), new Power());
        //无法编译
        //List<Snow> snow2 = Arrays.asList(new Light(), new Heavy());
        List<Snow> snow3 = new ArrayList<Snow>();
        Collections.addAll(snow3, new Light(), new Heavy());
        List<Snow> snow4 = Arrays.<Snow>asList(new Light(), new Heavy());
    }
}
```
可以看到snow2因为Arrays.asList()中只有Powder类型，因此他会创建Powder而不是Snow类型。再snow4中，加入了一条线索（显示类型参数说明）


接下来讲一下其他的一些容器。
先看代码
```
import java.util.*;

public class Test{
    static Collection fill(Collection<String> collection){
        collection.add("rat");
        collection.add("cat");
        collection.add("dog");
        collection.add("Dog");
        return collection;
    }
    static Map fill(Map<String, String> map){
        map.put("rat", "Fuzzy");
        map.put("cat", "Rags");
        map.put("dog", "Bosco");
        map.put("Dog", "Spot");
        return map;
    }

    public static void main(String[] args) {
        System.out.println(fill(new ArrayList<String>()));
        System.out.println(fill(new LinkedList<String>()));
        System.out.println(fill(new HashSet<String>()));
        System.out.println(fill(new TreeSet<String>()));
        System.out.println(fill(new LinkedHashSet<String>()));
        System.out.println(fill(new HashMap<String, String>()));
        System.out.println(fill(new TreeMap<String, String>()));
        System.out.println(fill(new LinkedHashMap<String, String>()));
    }
}
```

 Collection（一个槽）|用法||Map（两个槽）|用法（Map.get(key)|
---|---|---|---|---
**List**：以特定顺序保存一组元素|**ArrayList**||**HashMap**|提供了最快的查找技术
||**LinkedList**：操作多于ArrayList|
|||
**Set**：元素不能重复|**HashSet**：存储方式较为复杂，但获取元素是最快的||**TreeMap**|按照比较结果的升序保存键
||**TreeSet**：一般的Set是不管顺序的，如果考虑顺序就用这个（按升序排序）|
||**LinkedHashSet**：按被添加的顺序保存对象||**LinkedHashMap**|按照插入顺序保存
|||
**Queue**：只允许再容器的一“端”插入对象，从另一端移除|||

#

接下来就一个个介绍一下这些内容

**List**

-  ArrayList：

擅长访问随机元素，但是插入和移除较慢。

- LinkedList

插入和删除时代价底，提供了优化的顺序访问，但是随机访问时较慢。


接下来的代码包括了一些ArrayList的可用操作

```
import java.util.*;

public class Test{
    public static void main(String[] args) {
        Random random = new Random(47);
        List<Integer> integers = new ArrayList<Integer>(7 );    //初始容量
        for(int i = 0; i < 7; i++){
            integers.add(random.nextInt(100));
        }

        System.out.println("1: " + integers);

        Integer i1 = 12;
        integers.add(i1);   //自动扩容
        System.out.println("2: " + integers);
        System.out.println("3: " + integers.contains(i1));

        integers.remove(i1);
        Integer i = integers.get(2);
        System.out.println("4: " + i + " " + integers.indexOf(i));

        integers.remove(i);
        System.out.println("5: " + i);

        integers.add(3, 101);
        System.out.println("6: " + integers);

        List<Integer> sub = integers.subList(0, 4);
        System.out.println("7: " + sub);
        System.out.println("8: " + integers.contains(sub));

        Collections.sort(sub);
        System.out.println("9: " + sub);
        System.out.println("10: " + integers.containsAll(sub));

        Collections.shuffle(sub, random);
        System.out.println("11: " + sub);
        System.out.println("12: " + integers.containsAll(sub));

        List<Integer> copy = new ArrayList<Integer>(integers);
        sub = Arrays.asList(integers.get(1), integers.get(2));
        System.out.println("13: " + sub);

        copy.retainAll(sub);    //取交集
        System.out.println("14: " + sub);

        copy = new ArrayList<Integer>(integers);
        copy.remove(2);
        System.out.println("15: " + copy);

        copy.removeAll(sub);
        copy.set(1, 102);
        copy.addAll(2, sub);
        integers.clear();
        System.out.println("Is integers Empty? " + integers.isEmpty());

        integers.addAll(sub);

        Object [] objects = integers.toArray();
        System.out.println("16: " + objects[1]);

        Integer[] in = integers.toArray(new Integer[0]);
        System.out.println("17: " + in[1]);
    }
}
```

方法|功能
---|---
add()|插入
get()|取出
contains()|对象是否在列表中
remove()|移除对象
indexof()|对象下标
subList(int i1, int i2)|获得从i1到i2列表片段
containsAll|是否含有所有所有元素
sort()|排序
shuffle()|打乱
retainAll()|取交集
removeAll(x)|移走列表中所有含x的元素
addAll(int index, Collection c)|把c全部插入到index处


**迭代器设计模式**

我们发现如果使用容器要先知道容器的类型，但如果原本是对着List编码的，但后来发现如果用Set会更好，这时需要重写吗？

迭代器也是一种设计模式，迭代器是一个对象，负责遍历并选择序列中的对象，迭代器被称为轻量级对象，因为创建它的代价很小。Java的Iterator只能单向移动。可以用来
- 使用iterator()要求容器返回一个Iterator。
- 使用next()获得下一个元素
- 使用hasNext()检查是否还有元素
- 使用remove()将新近返回的元素删除。


```
import java.util.*;

public class Test{
    public static void dispaly(Iterator<Integer> integerIterator){
        while (integerIterator.hasNext()){
            Integer in = integerIterator.next();
            System.out.print(in);
        }
        System.out.println();
    }
    public static void birth(Collection collection){
        Random random = new Random(47);
        for(int i = 0; i < 7; i++){
            collection.add(random.nextInt(10));
        }
    }
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        birth(arrayList);
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        birth(linkedList);
        HashSet<Integer> hashSet = new HashSet<Integer>();
        birth(hashSet);
        TreeSet<Integer> treeSet = new TreeSet<Integer>();
        birth(treeSet);
        dispaly(arrayList.iterator());
        dispaly(linkedList.iterator());
        dispaly(hashSet.iterator());
        dispaly(treeSet.iterator());

    }
}
```

```
public class Test{
    public static void main(String[] args){
        Random random = new Randow(47);
        List<Integer> integers = new ArrayList<Integer>();
        
        for(int i = 0; i < 7; i++){
            integers.add(random);
        }
        Iterator<Integer> it = integers.iterator();
        
        while(integers.hasNxt()){
            Integer in = integers.next();
            System.out.print(in);
        }
        System.out.println();
        for(int i = 0; i < 6; i++){
            it.next();
            it.remove();
        }
        System.out.println(integers);
    }
}
```

###### ListIterator
ListIterator是一个更加强大的Iterator的子类型。可以双向移动。还可指出前一个与后一个的索引，还可以用set()替换它访问过的最后一个元素。
```
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class Test{
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> integers = new ArrayList<Integer>();
        for(int i = 0; i < 7; i++){
            integers.add(random.nextInt(10));
        }
        ListIterator<Integer> it = integers.listIterator();
        while(it.hasNext()){
            System.out.println(" next: " + it.next() + ", " + " nextIndex: " + it.nextIndex() + ", " + " previousIndex: " + it.previousIndex());
        }
        System.out.println();

        while (it.hasPrevious()){
            System.out.print(it.previous());
        }
        System.out.println();

        System.out.println(integers);
        it = integers.listIterator(3);
        while (it.hasNext()){
            it.next();
            it.set(11);
        }
        System.out.println(integers);

        while(it.hasPrevious()){
            System.out.print(it.previous() + " ");
        }
    }
}
```



**LinkedList**

- peek(), element(): Retrieves, but does not remove, the head (first element) of this list.
- poll(), remove(): Retrieves and removes the head (first element) of this list.
- public boolean offer(E e): Adds the specified element as the tail (last element) of this list.
- public boolean offerFirst(E e): Inserts the specified element at the front of this list.
- public boolean offerLast(E e): Inserts the specified element at the end of this list.

See the code:

```
import java.util.LinkedList;
import java.util.Random;

public class Test{
    public static void main(String[] args) {
        Random random = new Random(47) ;
        LinkedList<Integer> integers = new LinkedList<Integer>();
        for(int i = 0; i < 8; i++){
            integers.add(random.nextInt(10));
        }
        System.out.println(integers);

        System.out.println(".getFirst " + integers.getFirst());
        System.out.println(".element() " + integers.element());
        System.out.println(".peek() " + integers.peek());
        System.out.println(".remove()" + integers.remove());
        System.out.println(".removeFirst" + integers.removeFirst());
        System.out.println(".poll()" + integers.poll());
        System.out.println(integers);
        integers.addFirst(11);
        System.out.println("After addFirst(): " + integers);
        integers.offer(12);
        System.out.println("After offer(): " + integers);
        integers.addLast(13);
        System.out.println("After addLast(): " + integers);
        integers.removeLast();
        System.out.println(".removeLast()" + integers);
    }
}
```

**Stack**

先进后出，LinkedList具有栈的全部功能（好像也能实现队列的全部功能）。而Stack也是有LinkedList实现的。


**Set**
HashSet使用散列表，因此输出毫无规律可言。
TreeSet使用了红黑树。
LinkedSet使用了散列，但是用了链表来维护元素的插入顺序。

See the code

```
import java.util.*;

public class Test{
    public static void birth(Collection collection){
        Random random = new Random(47);
        for(int i = 0; i < 10; i++){
            collection.add(random.nextInt(10));
        }
    }
    public static void main(String[] args) {
        SortedSet<Integer> integerSortedSet = new TreeSet<Integer>();
        birth(integerSortedSet);
        System.out.println(integerSortedSet);

        Set<Integer> integerSet = new HashSet<Integer>();
        birth(integerSet);
        System.out.println(integerSet);

        Set<String> set1 = new HashSet<String>();
        Collections.addAll(set1, "A B C D E F G H".split( " "));
        set1.add("M");
        System.out.println("H: " + set1.contains("H"));
        System.out.println(set1);
        Set<String> set2 = new HashSet<String>();
        Collections.addAll(set2, "D E F G".split(" "));
        System.out.println(set1.containsAll(set2));

    }
}
```

**Map**

See the code

```
import typeinfo.pets.*;

import java.util.HashMap;
import java.util.Map;

public class Test{
    public static void main(String[] args) {
        Map<String, Pet> petMap = new HashMap<String, Pet>();
        petMap.put("My Cat", new Cat("Molly"));
        petMap.put("My Dog", new Dog("Molly"));
        petMap.put("My Hamster", new Hamster("Bosco"));
        System.out.println(petMap);
        Pet dog = petMap.get("My Dog");
        System.out.println(dog);
        System.out.println(petMap.containsKey("My Dog"));
        System.out.println(petMap.containsValue(dog));
    }
}
```

.put(key, value)

.get(key)

.containsKey(key)

.containsValue(value);


See the code

```
import typeinfo.pets.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test{
    public static Map<Person , List<? extends Pet>> petpeople = new HashMap<Person, List<? extends Pet>>();
    static {
        petpeople.put(new Person("Dawn"), Arrays.asList(new Cymric("Molly"), new Mutt("Spot")));
        petpeople.put(new Person("Marrily"), Arrays.asList(new Pug("Lucy"), new Cat("Kitty"), new Cat("Captain Cat")));
        petpeople.put(new Person("Luke"), Arrays.asList(new Rat("Big White"), new Rat("Fizzy")));
        petpeople.put(new Person("Isaac"), Arrays.asList(new Rat("Elizble")));
    }
    public static void main(String[] args) {
        System.out.println("People: " + petpeople.keySet());
        System.out.println("Pet: " + petpeople.values());
        for(Person person : petpeople.keySet()){
            System.out.println(person + " has: ");
            for (Pet pet : petpeople.get(person)){
                System.out.print(pet + " ");
            }
            System.out.println();
        }
    }
}
```

.keySet(): get keys
.valus(): get valus


**Queue**

Queue会窄化LinkedList方法的访问权限。1

###### PriorityQueue

通过堆实现，具体说是通过完全二叉树（complete binary tree）实现的小顶堆（任意一个非叶子节点的权值，都不大于其左右子节点的权值），也就意味着可以通过数组来作为 PriorityQueue 的底层实现。

The head of this queue is the least element with respect to the specified ordering.If multiple elements are tied for least value, the head is one of those elements -- ties are broken arbitrarily. The queue retrieval operations poll, remove, peek, and element access the element at the head of the queue.


See the code

```
import java.util.*;

public class Test{
    public static void printQ(Queue queue){
        while (queue.peek() != null){
            System.out.print(queue.remove() + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
        Random random = new Random(47);
        for(int i = 0; i < 10; i++){
            priorityQueue.offer(random.nextInt(i + 10));
        }
        printQ(priorityQueue);

        List<Integer> ints = Arrays.asList(25, 22, 20, 18, 14, 9, 3, 1, 1, 2, 3, 9, 14, 18, 21, 23, 25);
        priorityQueue = new PriorityQueue<Integer>(ints.size(), Collections.reverseOrder());
        priorityQueue.addAll(ints);
        printQ(priorityQueue);

        String fact = "EDUCATION SHOULD ESCHEW OBFUSCATION";
        List<String> strings = Arrays.asList(fact.split(" "));
        PriorityQueue<String> PQ = new PriorityQueue<String>(strings);
        printQ(PQ);

        PQ = new PriorityQueue<String>(strings.size(), Collections.reverseOrder());
        PQ.addAll(strings);

        Set<Character> CS = new HashSet<Character>();
        for(char c : fact.toCharArray()){
            CS.add(c);
        }
        PriorityQueue<Character> characterPriorityQueue = new PriorityQueue<Character>(CS);
        printQ(characterPriorityQueue);
    }
}
```


# 通过异常来处理错误

抛出异常后，Java会使用new再堆上创建对象。然后，当前的执行路径被终止，并从当前环境中弹出对异常对象的引用。然后异常处理机制接管程序。看一下代码

```
class MyException extends Exception{
    public MyException(){}
    public MyException(String msg){super(msg);}
}

public class Test{
    public static void f() throws MyException{
        System.out.println("Throwing MyException from f()");
        throw new MyException();
    }

    public static void g() throws MyException{
        System.out.println("Throwing MyException from g()");
        throw new MyException("Originated in g()");
    }

    public static void main(String[] args) {
        try {
            f();
        }catch (MyException e){
            e.printStackTrace(System.out);
        }
        System.out.println("Hello");
        try{
            g();
        }catch (MyException e){
            e.printStackTrace(System.out);
        }
        System.out.println("Yea");
    }
}


/*
output:
Throwing MyException from f()
MyException
	at Test.f(Test.java:9)
	at Test.main(Test.java:19)
Hello
Throwing MyException from g()
MyException: Originated in g()
	at Test.g(Test.java:14)
	at Test.main(Test.java:25)
Yea
*/
```

Java鼓励人们把方法发可能会抛出的异常告知使用这个方法的客户端程序员。客户端程序眼可以在源码中查找throw语句来获知相关的信息。如果没有开源可以通过异常说明。

形式如下

```
//异常说明
void f() throws TooBig, TooSmall, DivZero{//...

//不会抛出异常
void f(){//...

```

**捕获所有异常**

```
catch(Exception e){
    System.out.println("Caught an exception");
}
```

因为Exception是与编程有关的所有异常的基类，所以不会含有很多具体信息，但可以调用它从基类Throwable继承的方法。

如
- getMessage()
- getLocalizedMessage()
- toString()
- printStackTrace()
- printStackTrace(PrintStream)


Code is in the code test

**栈轨迹**：See the code in code test

**重新抛出异常**

```
catch(Exception e){
    System.out.println("An exception was thrown");
    throw e;
}
```

重抛异常会把异常抛给上一级环境中的异常处理程序。原有的信息得以保持，更高一级的环境中捕获此异常的处理程序可以获得这个异常对象的所有信息。

See the code

```
import java.security.spec.ECField;

public class Test{
    public static void f() throws Exception{
        System.out.println("originating the exception in f()");
        throw new Exception("throw from f()");
    }
    public static void g() throws Exception {
        try{
            f();
        }catch (Exception e){
            System.out.println("Inside g(), e.printStackTrace()");
            e.printStackTrace(System.out);
            throw e;
        }
    }
    public static void h() throws Exception{
        try {
            f();
        }catch (Exception e){
            System.out.println("Inside h(), e.printStackTrace()");
            e.printStackTrace(System.out);
            throw (Exception) e.fillInStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            g();
        }catch (Exception e){
            System.out.println("main: printStackTrace");
            e.printStackTrace(System.out);
        }
        System.out.println("-----------------------------------");
        try {
            h();
        }catch (Exception e){
            System.out.println("main: printStackTrace");
            e.printStackTrace(System.out);
        }
    }
}
```

这里h()在重新抛出是用了一个fillInStackTrace():

Fills in the execution stack trace. This method records within this Throwable object information about the current state of the stack frames for the current thread.

If the stack trace of this Throwable is not writable, calling this method has no effect.

这样在更高级的try中的信息就不再是原来的信息。


###### 在捕获异常后抛出另一种异常，这样得到的效果与fillInStackTrace()相同，原来的异常发生点的信息丢失，剩下与新抛出点有关的信息。

use InnerClass


See the code

```
public class Test{
    public static void f() throws OneException{
        System.out.println("originating the exception in ");
        throw new OneException("throw from f()");
    }

    public static void main(String[] args) {
        try {
            try {
                f();
            }catch (OneException e){
                System.out.println("Caught in inner try, e.printStackTrace()");
                e.printStackTrace(System.out);
                throw new TwoException("from inner try");
            }
        }catch (Exception e){
            System.out.println("Caught in outer try, e.printStaciTrace()");
            e.printStackTrace(System.out);
        }
    }
}

class OneException extends Exception{
    public OneException(String s){super(s);}
}

class TwoException extends Exception{
    public TwoException(String s ){super(s);}
}
```

最后的异常只知道自己来自mian，对f()一无所知。


**异常链**

需要在捕获一个异常后抛出另一个异常，并希望把原始信息保存下来时，就可以使用异常链。在JDK 1.4以前，程序员必须自己编写代码来保存原始异常的信息。现在所有Throwable的子类在构造器中都可以接受一个cause对象作为参数，这个cause就用来表示原始异常，这样把原始异常传给新的异常，使得即使在当前位置创建并抛出了新的异常，也能通过这个异常链追踪到异常最开始发生的位置。
在Throwable中只有三种基本的异常类提供了带cause参数的构造器。他们时Error， Exception， RuntimeException。要把其他的异常链连起来，要用initCause()方法，而不是构造器。

See the code

```
class DynamicFieldsException extends Exception{}

public class DynamicFields{
    private Object[][] fields;
    public DynamicFields(int initialSize){
        fields = new Object[initialSize][2];
        for(int i = 0; i < initialSize; i++)
            fields[i] = new Object[] {null, null};
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        for(Object[] obj : fields){
            result.append(obj[0]);
            result.append(": ");
            result.append(obj[1]);
            result.append("\n");
        }
        return result.toString();
    }

    private int hasField(String id){
        for(int i = 0; i < fields.length; i++){
            if(id.equals(fields[i][0]))
                return i;
        }
        return -1;
    }

    private int getFieldNumber(String id) throws NoSuchFieldException{
        int fieldNum = hasField(id);
        if(fieldNum == -1){
            throw new NoSuchFieldException();
        }
        return fieldNum;
    }

    private int makeField(String id){
        for(int i = 0; i < fields.length; i++)
            if(fields[i][0] == null){
                fields[i][0] = id;
                return i;
            }
        //No empty fields. add one.
        Object[][] tmp = new Object[fields.length + 1][2];
        for(int i = fields.length; i < tmp.length; i++)
            tmp[i] = new Object[]{null, null};
        fields = tmp;
        return makeField(id);
    }
    public Object getField(String id)throws NoSuchFieldException{
        return fields[getFieldNumber(id)][1];
    }

    public Object setField(String id, Object value) throws DynamicFieldsException{
        if (value == null) {
            //Most exceptions don't have a "cause" constructor
            //In these cases you must use initCause()
            //available in all Throwable subclasses
            DynamicFieldsException def = new DynamicFieldsException();
            def.initCause(new NullPointerException());
            throw def;
        }
        int fieldNumber = hasField(id);
        if(fieldNumber == -1)
            fieldNumber = makeField(id);
        Object result = null;
        try{
            result = getField(id);  //Get old value
        }catch (NoSuchFieldException e){
            //use constructor that takes "cause"
            throw new RuntimeException(e);
        }

        fields[fieldNumber][1] = value;
        return result;
    }

    public static void main(String[] args) {
        DynamicFields df = new DynamicFields(3);
        System.out.println(df);
        try{
            df.setField("d", "A value for d");
            df.setField("number", 47);
            df.setField("number2", 48);
            System.out.println(df);
            df.setField("d", "A new value of d");
            df.setField("number3", 11);
            System.out.println("df: " + df);
            System.out.println("df.getField(\"d\"): " + df.getFieldNumber("d"));
            Object field = df.setField("d", null);   //Exception
        }catch (NoSuchFieldException e){
            e.printStackTrace(System.out);
        }catch (DynamicFieldsException e){
            e.printStackTrace(System.out);
        }
    }
}
```

这个例子中，可以说是出了一个NullpointerException异常，但是在setField中默认的是抛出一个DynamicFieldException，于是它又执行了一个dfe.initCause(new NullPointerException)并给抛出，于是就记录了它的错误轨迹。


finally可以保证不管是reuturn还是什么，finally中的一定会被执行。

**异常的限制**

当我们覆盖方法时，只能抛出在基类方法中说明中列出的异常。这意味着基类使用的代码应用到其派生类对象时，一样可以工作。

See the code

```
class BaseballException extends Exception{}
class Foul extends BaseballException{}
class Strike extends BaseballException{}

abstract class Inning{
    public Inning() throws BaseballException{}
    public void event() throws BaseballException{}
    public abstract void atBat() throws Strike, Foul;
    public void walk(){}
}

class StormException extends Exception{}
class RainedOut extends StormException{}
class PopFoul extends Foul{}

interface Storm{
    public void event() throws RainedOut;
    public void rainHard() throws RainedOut;
}

public class StormyInning extends Inning implements Storm{
    //OK to add new exceptions for constructors, but you must deal with the base constructor exception
    public StormyInning() throws RainedOut, BaseballException{}
    public StormyInning(String s)throws Foul, BaseballException{}
    //Regular methods must conform to base class;
    //!void walk() throws PopFoul()
    //Interface CANNOT add exception to existing methods form the base class
    //!public void event() throw RainedOut{}
    //If the method doesn't already exist in the base class, the  exception is OK

    @Override
    public void rainHard() throws RainedOut {

    }

    //You can choose to not throw any exceptions even if the base version does
    public void event(){}
    //Overridden methods can throw inherited exceptions
    @Override
    public void atBat() throws Foul { throw  new Foul();}

    public static void main(String[] args) {
        try{
            StormyInning si = new StormyInning();
            si.atBat();
        }catch (PopFoul e){
            System.out.println("Pop foul");
        }catch (RainedOut e){
            System.out.println("rained Out");
        }catch (BaseballException e){
            System.out.println("Generic baseball exception");
        }

        try {
            //Upcast
            Inning i  = new StormyInning();
            i.atBat();
        }catch (Strike e){
            System.out.println("Strike");
        }catch (Foul e){
            System.out.println("Foul");
        }catch (RainedOut e){
            System.out.println("Rained Out");
        }catch (BaseballException e){
            System.out.println("Generic baseball exception");
        }
    }
}
```


**构造器**

See the code

```
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputFile {
    private BufferedReader in;
    public  InputFile(String fname) throws Exception{
        try {
            in = new BufferedReader(new FileReader(fname));
        }catch (FileNotFoundException e){
            System.out.println("Could not open " + fname);
            //Wasn't open, so don't close there
            throw e;
        }catch (Exception e){
            //All other exception must close it
            try {
                in.close();
            }catch (IOException e1){
                System.out.println("in.close unsuccessful");
            }
            throw e;
        }finally {
            //Don't close it there, because it will be used soon
        }
    }

    public String getLine(){
        String s;
        try {
            s = in.readLine();
        }catch (IOException e){
            throw new RuntimeException("readLine() failed");
        }
        return s;
    }

    public void dispose(){
        try {
            in.close();
            System.out.println("dispose() successful");
        }catch (IOException e){
            throw new RuntimeException("in.close() failed");
        }
    }
    public static void main(String[] args) {
        //对于在构造阶段可能会抛出的异常，并要求清理的类，最安全的方式是使用嵌套的try子句
        try {
            InputFile inputFile = new InputFile("Cleanup.java");
            try {
                String s;
                int i = 1;
                while ((s = inputFile.getLine()) != null)
                    ;   //Perform line-by-line processing here...
            }catch (Exception e){
                System.out.println("Caught Exception in main");
                e.printStackTrace();
            }finally {
                inputFile.dispose();
            }
        }catch (Exception e){
            System.out.println("InputFile construction failed");
        }
    }
}

```

当构造器出错时，可以通过在try中嵌套多个try，多次初始化对象来保证对象的创建，但不要忘了回收创建失败的对象

toString中不能用this，这会造成无限递归。



# 字符串



Formatter

```
System.out.format("%-3s, %-1d", s, 1);
        Formatter f = new Formatter(System.out);
        f.format("%s", s);
```


See the code

```
import java.util.Arrays;

public class StringTest {
    public static String s = "Hello I am LiangAokai, I studying computer science";
    public static void split(String regex){
        System.out.println(Arrays.toString(s.split(regex)));
    }
    public static void main(String[] args) {
        split(" ");
        split("\\W+");
        split("m\\W+");

    }
}

/*Output
[Hello, I, am, LiangAokai,, I, studying, computer, science]
[Hello, I, am, LiangAokai, I, studying, computer, science]
[Hell
*/
```

**Pattern和Matcher**

[Pattern和Matcher](https://www.cnblogs.com/yongdaimi/p/8883390.html/)

**Pattern:**

A compiled representation of a regular expression.

A regular expression, specified as a string, must first be compiled into an instance of this class. The resulting pattern can then be used to create a Matcher object that can match arbitrary character sequences against the regular expression. 

All of the state involved in performing a match resides in the matcher, so many matchers can share the same pattern.


###### cmpile:

  **public static Pattern compile(String regex)**

  Compiles the given regular expression into a pattern.

  regex - The expression to be compiled

*Returns:*

the given regular expression compiled into a pattern
  
**public static Pattern compile(String regex,int flags)**
  
Compiles the given regular expression into a pattern with the given flags.

*Parameters:*

*regex* - The expression to be compiled

*flags* - Match flags, a bit mask that may include
CASE_INSENSITIVE, MULTILINE, DOTALL, UNICODE_CASE, CANON_EQ, UNIX_LINES, LITERAL, UNICODE_CHARACTER_CLASS and COMMENTS

*Returns:*
the given regular expression compiled into a pattern with the given flags

Code

```
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        String s = "abcabcabcdefabc";
        String [] ss = new String[]{"abc","abc" , "abcabc"};
        for(String p : ss){
            Pattern a = Pattern.compile(p);
            Matcher m = a.matcher(s);
            while(m.find()){
                System.out.println("m.group: " + m.group() + " m.start: " + m.start() + " m.end: " + m.end());
            }
            System.out.println();
        }
    }
}

/*OUTPUT
m.group: abc m.start: 0 m.end: 3
m.group: abc m.start: 3 m.end: 6
m.group: abc m.start: 6 m.end: 9
m.group: abc m.start: 12 m.end: 15

m.group: abc m.start: 0 m.end: 3
m.group: abc m.start: 3 m.end: 6
m.group: abc m.start: 6 m.end: 9
m.group: abc m.start: 12 m.end: 15

m.group: abcabc m.start: 0 m.end: 6

*/


```

Pattrn对象也有static方法，可以这样用：

static boolean matches(String regex, CharSequence input)

**组**

See the code

```
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test{
    static public final String sentence = "When you are hungry,\n" + "you need eat food\n" + "everybody is the same\n";
    public static void main(String[] args) {
        Matcher m = Pattern.compile("(?u)(\\S+)\\s+((\\S+)\\s+(\\S+))$").matcher(sentence);
        while (m.find()){
            for(int j = 0; j <= m.groupCount(); j++)
                System.out.println(j + " " + m.group(j));
            System.out.println();
        }
    }
}

*/Output
0 is the same
1 is
2 the same
3 the
4 same
*/
```


**split()**

See the code

```
import java.util.Arrays;
import java.util.regex.Pattern;

public class Test{
    public static void main(String[] args) {
        String input = "This!!unusual use!!of exclamation!!points";
        System.out.println(Arrays.toString(Pattern.compile("!!").split(input)));
        //only do the first three
        System.out.println(Arrays.toString(Pattern.compile("!!").split(input, 3)));
        Pattern p = Pattern.compile("!!");
        System.out.println(p);
        System.out.println(Arrays.toString(p.split(input)));
        System.out.println(Arrays.toString(input.split("!!")));
    }
}

/*OUTPUT
[This, unusual use, of exclamation, points]
[This, unusual use, of exclamation!!points]
!!
[This, unusual use, of exclamation, points]
[This, unusual use, of exclamation, points]
*/
```

replaceAll();
replaceFirst();

reset()

```
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test{
    public static void main(String[] args) {
        Matcher m = Pattern.compile("[frb][aiu][gx]").matcher("fix the rug with bags");
        while (m.find()){
            System.out.print(m.group() + " ");
        }
        System.out.println();
        m.reset("fix the rig with bags");
        while (m.find()){
            System.out.print(m.group() + " ");
        }
    }
}

/*Output
fix rug bag 
fix rig bag 
*/
```


###### matcher
public Matcher matcher(CharSequence input)

Creates a matcher that will match the given input against this pattern.

##### Parameters:

input - The character sequence to be matched

##### Returns:

A new matcher for this pattern

**Matcher and Pattern**
Pattern是一个正则表达式经编译后的表现模式。

Matcher 一个Matcher对象是一个状态机器，它依据Pattern对象做为匹配模式对字符串展开匹配检查。 

Pattern.matcher():根据编译过的Pattern对象，以mathcer()中文本作为匹配对象。


# 类型信息

See the code

```
public class Test{
    public static void main(String[] args) {
        Class c = null;
        try {
            c = Class.forName("Candy");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        System.out.println(c.getSimpleName());
        Class up = c.getSuperclass();
        Object object = null;
        try {
            object = up.newInstance();
        }catch (InstantiationException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }

        System.out.println(c.isInterface());
        System.out.println(c.getCanonicalName());
        System.out.println(c.getConstructors());
        System.out.println(object.getClass());
    }
}

class Candy{
    static {
        System.out.println("Candy");
    }
}

class sugar{
    static {
        System.out.println("Sugar!");
    }
}
```

其中的newInstance()是实现虚拟构造器的一种途径。虚拟构造器允许你声明：我不知道具体的类型，但是无论如何，正确的创建你自己。

Class initable = Initable.class;
Class initable1 = Initable1.class;
Class initable2 = Initable2.class;

以上不会初始化，只是获得Initable...的类对象。

Class c = Class.forName("Initable1");

这个会初始化

static finall是编译时常量，static不是。



See the code

```
public class Test{
    public static void main(String[] args) {
        Class<? extends Number> num = int.class;
        System.out.println(num.getName());
        num = double.class;
        System.out.println(num.getName());
        num = Number.class;
        System.out.println(num.getName());
    }
}
/*Output
int
double
java.lang.Number
*/
```

Class.forName("")返回的是类 <br>
Class.forName("").newInstance()返回的是object


See the code

```
import java.util.ArrayList;
import java.util.List;

class CountedInteger{
    private static long counter;
    private final long id = counter++;

    @Override
    public String toString() {
        return "CountedInteger{" +
                "id=" + id +
                '}';
    }
}

public class Test<T>{
    private Class<T> type;
    public Test(Class<T> type){this.type = type;}
    public List<T> creat(int n){
        List<T> result = new ArrayList<T>();
        try {
            for(int i = 0; i < n; i++)
                result.add(type.newInstance());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        Test<CountedInteger> fl = new Test<CountedInteger>(CountedInteger.class);
        System.out.println(fl.creat(15));
    }
}
```


# 反射

See the code

```
package demo;

public class Demo {
    private String s = "Hello";
    private int num = 11;

    public double number = 2.5;

    public Demo(){}

    public Demo(String s, int num) {
        this.s = s;
        this.num = num;
    }

    public void f(){
        System.out.println("This is demo.Demo f()");
    }

    private void g(String st){
        System.out.println("This is demo.Demo g()" + st);

    }


    @Override
    public String toString() {
        return "Demo{" +
                "s='" + s + '\'' +
                ", num=" + num +
                ", number=" + number +
                '}';
    }
}


```

```
/*
\pro.properties
*/

className=demo.Demo
methodName=f()
```

```
import demo.Demo;

import javax.accessibility.Accessible;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflection {
    public static void main(String[] args) throws Exception{
        //1.全类名,多用于配置文件
        Class c1 = Class.forName("demo.Demo");
        System.out.println(c1.toString());
        //2.类名.class，多用于参数传递
        Class c2 = Demo.class;
        System.out.println(c2);
        //3.对象.getClass()，多用于对象的获取字节码的方式
        Demo d = new Demo();
        Class c3 = d.getClass();
        System.out.println(c3);
        System.out.println("==============================");
        System.out.println("获取成员变量");
        System.out.println("===============================");
        //1.获取成员变量们
        Field[] fields = c2.getDeclaredFields();

        for(Field field : fields){
            System.out.println(field);
        }

        Field c2DeclaredField = c2.getDeclaredField("s");
        c2DeclaredField.setAccessible(true);    //暴力反射
        System.out.println(c2DeclaredField);    //域名
        System.out.println();
        //获取成员变量number的值
        Object obj = c2DeclaredField.get(d);    //特定对象的域值
        System.out.println(obj);
        c2DeclaredField.set(d, "张三");
        System.out.println(d);

        System.out.println("==============================");
        System.out.println("获取构造方法");
        System.out.println("===============================");
        //2.获取构造方法
        Constructor constructor = c2.getConstructor(String.class, int.class);
        System.out.println(constructor);
        Object demo1 = constructor.newInstance("张三", 3);
        System.out.println(demo1);

        System.out.println("===============================");
        System.out.println("获取方法");
        System.out.println("===============================");
        //3.获取方法
        Method method = c2.getDeclaredMethod("f");
        Demo demo2 = new Demo();
        method.invoke(demo2);

        Method method1 = c2.getDeclaredMethod("g", String.class);
        method1.setAccessible(true);
        method1.invoke(demo2, "haha");
        System.out.println("==============================");
        Method[] methods = c2.getMethods();
        for (Method m : methods) {
            System.out.println(m);
            String name = m.getName();
            System.out.println(name);
            method.setAccessible(true);
        }
    }
}
```

```
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class ReflectTest {
    public static void main(String[] args) throws Exception{
        //1.加载配置文件
        //1.1创建Properties对象
        Properties pro = new Properties();

        //1.2加载配置文件，转换为一个集合
        //1.2.1获取class目录下的配置文件
        ClassLoader classLoader = ReflectTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("pro.properties");
        pro.load(is);

        //2.获取配置文件中定义的数据
        String className = pro.getProperty("className");
        String methodName = pro.getProperty("methodName");

        //3.加载该类进内存
        Class cls = Class.forName(className);
        //4.创建对象
        Object obj = cls.newInstance();
        //5.获取方法对象
        Method method = cls.getMethod(methodName);
        //6.执行方法
        method.invoke(obj);
    }
}

```


# 泛型类

- \<T>   泛型类标识符--形参类型

    你放进去的类形名相当于实参。
- 泛型类如果没有指定具体的数据类型，此时操作类型时Object
- 泛型类参数只能是类类型，不能是基本数据类型
- 泛型类型在逻辑上可以看作多个不同的类型，但实际上都是形同类型   


# 继承

- 子类也是泛型类，子类与父类泛型类型要一致
  
  class ChildGeneric<T> extends Generic<T>
  
- 子类不是泛型类，父类要明确泛型的数据类型

  class ChildGeneric extends Generic<String>


# 接口

定义语法：
```
interface 接口名称 <泛型标识，泛型标识，...>{
    泛型标识 方法名();
    ......
}
```

- 实现类不是泛型类，接口要确定数据类型
- 接口也是泛型类，实现类和接口的泛型要一致

# 泛型方法

定义语法

```
修饰符 <T, E, ...> 返回值类型 方法名(形参列表){
    方法体...
}
```

- public与返回值中间的<T>非常重要，可以理解为声明此方法为泛型方法
- 只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法
- <T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T
- 与泛型类的定义一样，此处T可以写为任意标识符，常见的为T, E, K, V的形式的参数常用于表示泛型

# 类型通配符

##### "?"通配符
- 类型通配符一般用"?"代替具体实参
- 所以，类型通配符时类型实参，而不是类型形参

##### 类型通配符的上限

语法

```
类/接口<? extends 实参类型>
```

- 要求该泛型的类型，只能是实参类型，后实参类型的子类型

##### 类型通配符的下限

语法

```
类/接口<? super 实参类型>
```

- 要求改泛型的类型，只能是实参类型，或者实参类型的父类类型

# 类型擦除

##### 概念

泛型代码能很好地和之前版本兼容。那是因为，泛型信息只存在于代码编译阶段，在进入JVM之前，于泛型
相关的信息会被擦除，我们成为类型擦除

- 无限制类型擦除
![avatar](https://img-blog.csdnimg.cn/20200321165711898.jpg)
- 有限制类型擦除
![avatar](https://img-blog.csdnimg.cn/20200321170159967.jpg)
- 擦除方法中类型定义的参数
![avatar](https://img-blog.csdnimg.cn/20200321170245514.jpg)
- 桥接方法
![avatar](https://img-blog.csdnimg.cn/20200321165817894.jpg)


# 泛型与数组

- 可以声明带泛型的数组引用，但是不能直接创建带泛型的数组对象

- 可以通过java.reflect.Array的newInstance(Class<T>, int n)创建T[]数组


# 反射常用的泛型

- Class<T>
- Constructor<T>


# 容器深入研究

![avatar](https://img-blog.csdnimg.cn/20190213131312518.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjU3NDE0Mg==,size_16,color_FFFFFF,t_70#pic_center)

上图是JAVA常见的各个容器的继承关系，接下来就依次分析一下

