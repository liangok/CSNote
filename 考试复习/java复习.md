# Java期末复习

[TOC]



### 构造方法 :smile: 

java在写了一个有参构造函数后不会再默认生成一个无参构造函数，除非自己写一个



```java
public class demoOne {
	public static void main(String[] args) {
		A a = new A();
		a.print();
	}
}

class A{
	String s;
	
	A(String newS){
		s = newS;
	}
	
	public void print() {
		System.out.print(s);
	}
}
```

下列默认输出什么？

```java
public class demoOne {
	boolean x;
	public static void main(String[] args) {
		demoOne d = new demoOne();
		System.out.print(d.x);
	}
}
```

答：输出false，当没有主动对变量初始化时，编译器会自动给初始化，int初始值是0，String是null

###          static关键词的使用问题  :grimacing: 

静态方法的一些注意事项 

写出下段代码的输出结果

```java
public class demoOne {
	public static void main(String[] args) {
		T t1 = new T();
		T t2 = new T();
		System.out.println("t1: " + "i: " + t1.i + " j: " + t1.j);
		System.out.println("t2: " + "i: " + t2.i + " j: " + t2.j);
	}
	
}

class T{
	static int i = 0;
	int j = 0;
	
	T(){
		i++;
		j = 1;
	}
}
```

结果：t1:  i: 2  j: 1
			t2:  i: 2  j: 1

### 方法调用 :stuck_out_tongue_winking_eye: 

传递基本类型参数传递的是值，而传递引用类型参数传递的是引用

```java
public class demoOne {
	
	public static void main(String[] args) {
		Circle x = new Circle(1);
		Circle y = new Circle(2);
		demoOne.swap1(x, y);
		System.out.println("x: " + x.radius + " y: " + y.radius);
		
		demoOne.swap2(x, y);
		System.out.println("x: " + x.radius + " y: " + y.radius);
	}
	
	
	public static void swap1(Circle x, Circle y) {
		Circle tmp = x;
		x = y;
		y = tmp;
	}
	
	public static void swap2(Circle x, Circle y) {
		double tmp = x.radius;
		x.radius = y.radius;
		y.radius = tmp;
	}
}

class Circle{
	double radius;
	
	Circle(double radius){
		this.radius = radius;
	}
}

```

> 结果：x: 1.0 y: 2.0
> 			x: 2.0 y: 1.0

----

分析：再swap1（）中，传入了两个对象x, y相当于c中的指针，因此无法更改指针

而再swap2（）中，传入了两个对象x，y但改的是radius，相当于改的是指针所指的内容，因此可以交换



```java
public class Test{
    public static void main(String[] args) {
        Date date = null;
        m1(date);
        System.out.println(date);
    }

    public static void m1(Date date){
        date = new Date();
        System.out.println(date);
    }
}

/*
output:
Wed Jul 15 11:26:07 CST 2020
null
*/

```

> 如果不加this则改的是传入的参数。

```java
public class Test{
    public static void main(String[] args) {
        Date date = new Date(1234567);
        m1(date);
        System.out.println(date);
    }

    public static void m1(Date date){
        date = new Date(7654321);
        System.out.println(date);
    }
}
/*
output:
Thu Jan 01 10:07:34 CST 1970
Thu Jan 01 08:20:34 CST 1970
*/

public class Test{
    public static void main(String[] args) {
        Date date = new Date(1234567);
        m1(date);
        System.out.println(date);
    }

    public static void m1(Date date){
        date.setTime(7654321);
        System.out.println(date);
    }
}

/*
output:
Thu Jan 01 10:07:34 CST 1970
Thu Jan 01 10:07:34 CST 1970
8/

```

> 第一个，因为传递的是一个对象，而无法更改对象。第二个传递的也是一个对象，但改的是数据域，故可以更改。

### this关键字的使用 :sweat_smile: 

用法：

- 用于引用正在被构建的对象的数据域，防止与参数冲突
- 用于调用另一个构造函数
- this可以在一个构造器里调用另一个构造器，但是要放在构造器的最前面

```java
class B{
    private int num;

    public B() {
        System.out.println("B");
        this(0);
    }

    public void setNum(int num) {
        this.num = num;
    }
}
```

> 错误：this没有放在最前，并且这里没有其他的构造器



###          有继承时的构造函数问题   :sunglasses: 



```java
class A{
	public A(int x) {
		System.out.println(x);
	}
}

class B extends A{
	public B() {
		super(1);
	}
	
	public B(int b) {
		super(b);
	}
}
public class C {
	public static void main(String[] args) {
		B b1 = new B();
		B b2 = new B(2);
	}
}
```

> 结果：1
> 			2

### 方法重写:sunny: 

子类重写父类的方法



```java
public class Circle {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double getArea() {
        return radius * radius * Math.PI;
    }

}

class B extends Circle{
    private double length;

    public B(double radius, double length) {
        super(radius);
        this.length = length;
    }

    @Override
    public double getArea() {
        return getArea() * length;
    }
}

```

> 这样会出现递归调用的问题

### 多态和动态绑定 :cat: 



```java
public class Test {
	public static void main(String[] args) {
		Integer[] list1 = {12, 244, 55, 1};
		Double[] list2 = {2.3, 5.6, 7.3, 8.0};
		int[] list3 = {1, 2, 3, 4};
		printArray(list1);
		printArray(list2);
		printArray(list3);
	}
	
	public static void printArray(Object[] list) {
		for(Object o: list) {
			System.out.print(0 + " ");
		}
		System.out.println();
	}
}

```

错误：int是基本类型，并没有继承Object

方法重写：

````java
//block_1:
package 构造方法;

public class Test {
	public static void main(String[] args) {
		new Person().printPerson();
		new Student().printPerson();
		
	}
}

class Person{
	private String getInfo() {
		return "Person";
	}
	
	public void printPerson() {
		System.out.println(getInfo());
	}
}

class Student extends Person{
	private String getInfo() {
		return "Student";
	}
}

//Output: Person
//    	  Person

//block_2
package 构造方法;

public class Test {
	public static void main(String[] args) {
		new Person().printPerson();
		new Student().printPerson();
		
	}
}

class Person{
	public String getInfo() {
		return "Person";
	}
	
	public void printPerson() {
		System.out.println(getInfo());
	}
}

class Student extends Person{
	@Override
	public String getInfo() {
		return "Student";
	}
}
//Output:Person
//		 Student

````

----

分析：在block_2中，getInfo用的是private方法，因此，在Person类中的printPerson（）无法访问，只能用父类的，无法实现重写

如果不用父类中的printPerson()就算是私有的也可以访问

```java
package 构造方法;

public class Test {
	public static void main(String[] args) {
		Person p = new Person();
		Student s = new Student();
		p.printInfo();
		s.printInfo();
	}
}

class Student extends Person{
	private String getInfo() {
		return "Student";
	}
	
	public void printInfo() {
		System.out.println(this.getInfo());
	}
}

class Person{
	private String getInfo() {
		return "Person";
	}
	
	public void printInfo() {
		System.out.println(this.getInfo());
	}
}

//Output:
//Person
//Student
```

动态引用：父类引用子类对象，方法用子类的，数据域用父类的

```java
package 构造方法;

public class Test {
	public static void main(String[] args) {
		new A();
		new B();
	}
}

class A{
	int i = 7;
	
	public A() {
		setI(20);
		System.out.println("i from A is " + i);
	}
	
	public void setI(int i) {
		this.i = 2 * i;
	}
}

class B extends A{
	
	public B() {
		System.out.println("i from B is " + i);
	}
	
	public void setI(int i) {
		this.i = 3 * i;
	}
}
/*output:
i from A is 40
i from A is 60
i from B is 60
*/
```



### instanceof用法 :tada: 

必须前面是对象，后面是类

如：a instanceof Fruit

```java
package 构造方法;

public class Test {
	public static void main(String[] args) {
		Apple a = new Apple();
		Fruit f = new Fruit();
		HongFuShi h = new HongFuShi();
		System.out.println(a instanceof Fruit);
		//System.out.println(Apple instanceof Fruit);必须前面是对象，后面是类
		System.out.println(f instanceof Apple);
		System.out.println(h instanceof Fruit);
	}
}

class HongFuShi extends Apple{}
class Apple extends Fruit{}
class Fruit{}
```

HongFuShi honfushi  = new Apple();   :heavy_multiplication_x: 



Apple apple = new Apple();

HongFuShi hongFuShi = (HongFuShi)apple;:heavy_multiplication_x:  这个错误编译器是检查不出来的。



Apple apple = new HongFuShi();

HongFuShi hongFuShi = (HongFuShi)apple; :heavy_check_mark:



 ​A​p​p​l​e​ ​ap​p​le​ ​= ​ne​w ​H​o​n​g​F​u​S​h​i​(​)​;​ :heavy_check_mark: 



Apple apple = new HongFuShi();
System.out.println(apple instanceof HongFuShi);:heavy_check_mark: 

```java
public class Test{
    public static void main(String[] args) {
        Object fruit = new Fruit();
        Object apple = (Apple)fruit;
    }
}

class Fruit{}
class Apple extends Fruit {}

```

>  可以向上造型，把子类强制类型转换为父类。（用父类接收）但是不可以把父类强制类型转换为子类。也就是Apple不可以变为HongFuShi。除非这个父类对象本来就是由子类new出来的。

父类可以接受子类对象（多态） :thumbsup: 

而子类在接受父类对象时必须强制类型转化为子类

```java
public class Test{
    public static void main(String[] args) {
        Fruit fruit = (Fruit)new Apple();
        fruit.fun();
    }
}

class Fruit{
    public void fun(){
        System.out.println("This is Fruit");
    }
}
class Apple extends Fruit {
    public void fun(){
        System.out.println("This is Apple");
    }
    public void fun2(){
        System.out.println("This is fun2");
    }
}

/*
output:
This is Apple
*/

```

fruit.fun2()  :heavy_multiplication_x: 

fruit已经向上造型了，而Fruit中没有fun2，故不可以调用

 强制类型转换的只是引用类型，真正指向的对象是不会发生变化的，可以将引用看作看待对象的角度，层次。这就像可以将红富士看作苹果，也可以看作水果一样，看待的角度、层次虽然变了，但苹果还是那个苹果。  :star: 

### 数组问题 :watermelon: 

```java
public static void main(String[] args){
    char[] a = {'3', '4', '5', '8', 'd', 'h'};
        for (int i = a.length - 1; i >= 2 - 1; i--) {
            System.out.println(a[i]);
        }
}

output:
h
d
8
5
4
```

如果从第5个达到第1个，则打了 5 - 1 + 1 个数。

### 输入单个字符 :yum: 

```java
public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char ch = in.nextLine().charAt(0);
        System.out.println(ch);
}
/*input:
342
output:
3
*/
```

