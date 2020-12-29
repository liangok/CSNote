### 1_交换两个指针的几种方法

```
#include<stdio.h>

//通过二级指针改变一级指针指向的位置 
//直接改变数值
void swap1(int** ppa, int** ppb ){
		int tmp = **ppa;
		**ppa = **ppb;
		**ppb = tmp;
} 
//改变一级指针
void swap2(int** ppa, int** ppb ){
		int *tmp = *ppa;
		*ppa = *ppb;
		*ppb = tmp;
} 
//通过定义三级指针改变二级指针
void swap3(int** ppa, int** ppb ){
		int ***pppa = &ppa;
		int ***pppb = &ppb;
		int ***tmp = pppa;
		pppa = pppb;
		pppb = tmp;
} 
//这个用处不大，是在强行用三级指针，而在真正用的时候，用的还是二级指针
void swap4(int** ppa, int** ppb ){
		int ***pppa;
		int ***pppb;
		int *tmp;
		*pppa = ppa;
		*pppb = ppb;
		tmp = **pppa;
		**pppa = **pppb;
		**pppb = **pppa;
} 
/*
*:一级指针
**：二级指针
***：三级指针
指针的符号有栈的意思 
*/ 
int main(void){
	int a = 2;
	int b = 3;
	int *pa = &a;
	int *pb = &b;
	printf("%d\n", *pa);
	printf("%d\n", *pb);
	printf("============\n");
	//现在要把pa指向b, pb指向a
	int *tmp = pa;
	pa = pb;
	pb = tmp;
	printf("%d\n", *pa);
	printf("%d\n", *pb);
	printf("============\n");
	swap1(&pa, &pb);
	printf("%d\n", *pa);
	printf("%d\n", *pb);
	printf("============\n");
	swap2(&pa, &pb);
	printf("%d\n", *pa);
	printf("%d\n", *pb);
	printf("============\n");
	swap3(&pa, &pb);
	printf("%d\n", *pa);
	printf("%d\n", *pb);
	printf("============\n");
	swap4(&pa, &pb);
	printf("%d\n", *pa);
	printf("%d\n", *pb);
	
	return 0;
} 

```

结果：
```
2
3
============
3
2
============
2
3
============
3
2
============
3
2
============

```

### 2_Java的参数传递
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

