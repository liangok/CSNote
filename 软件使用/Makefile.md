Makefile 一种脚本语言

### 显式规则

1. 格式：

   目标文件:依赖文件
   [TAB]

2. 第一个目标文件是最终目标

3. 伪目标：.PHONY

```makefile
hello:hello.o
	gcc hello.o -o hello
	
hello.o:hello.s
	gcc -C hello.S -o hello.o
	
hello.S:hello.i
	gcc -S hello.i -o hello.S
	
hello.i:hello.c
    gcc -E hello.c -o hello.i
    

.PHONY:
clear:
	rm -rf hello.c hello.o hello.S hello.i
clearall:
	rm -rf hello.o hello.S hello.i hello

    
```



```makefile
# circle.c circle.h cube.c cube.h main.c main.h

test:circle.o cube.o main.o
	gcc circle.o cube.o main.o -o test
	 
circle.o:circle.c
	gcc -c circle.c -o circle.o

main.o:main.c
	gcc -c main.c -o main.o
	
.PHONY:
clearall:
	rm -rf circle.o cube.o main.o test
clear:
	rm -rf circle.o cube.o main.o
	
	
```

### 变量

1. =（替换）
2. +=（追加）
3. :=（恒等于）

```makefile
TAR = test
CC := gcc
OBJ = circle.o cube.o main.o

$(TAR):$(OBJ)
	$(CC) -c circle.c -o circle.o
	
circle.o:circle.c
	$(CC) -c circle.c -o circle.o
	
cube.o:cube.c
	$(CC) -c main.c -o main.o
	
main.o:main.c
	$(CC) -c main.c -o mian.
	 
.PHONY:
clearall:
	rm -rf $(OBJ) test
clear:
	rm -rf $(OBJ)
```



### 隐含规则



1. %.c %.o 任意的.c或.o
2. *.c *.o 所有的.c或.o

```makefile
TAR = test
CC := gcc
OBJ = circle.o cube.o main.o

$(TAR):$(OBJ)
	$(CC) -c circle.c -o circle.o
	
%.o:%.c
	$(CC) -c %.c -o %.o

	 
.PHONY:
clearall:
	rm -rf $(OBJ) $(TAR)
clear:
	rm -rf $(OBJ)
```



### 通配符

1. $^ 所有的目标文件
2. $@ 所有的依赖文件
3. $< 所有的依赖文件的第一个文件

```makefile
TAR = test
CC := gcc
OBJ = circle.o cube.o main.o
RMRF := rm -rf
$(TAR):$(OBJ)
	$(CC) $@ -o $^
	
%.o:%.c
	$(CC) -c $@ -o $^

	 
.PHONY:
clearall:
	$(RMRF) $(OBJ) $(TAR)
clear:
	$(RMRF) $(OBJ)
```

### 函数

