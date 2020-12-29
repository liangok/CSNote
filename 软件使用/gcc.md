# 编译过程

![avatar](https://images2017.cnblogs.com/blog/1044665/201801/1044665-20180117212346053-1244954563.png)

# GCC使用方法

### 基本用法
对应编译的过程，由以下的gcc命令

- 预处理（pre-processing）E：插入头文件，替换宏
- 编译（Compiling）S：编译成汇编
- 汇编（Assembling） c：编译成目标文件
- 链接 （Linking）：链接到库中，变成可执行文件

```
gcc -E hello.c -o hello.i

gcc -S hello.i –o hello.s

gcc –c hello.s –o hello.o
```
也可一次执行
```
gcc hello.c –o hello
```

### 优化

-O 代表优化

```
gcc -Og add.c
```

### 联接

```
gcc -Og -o prog main.c add.c
```



# OBJDUMP

- 使用objdump进行反汇编
```
linux> objdump -d add.out > obj.txt
```

# GDB使用方法


|名字|作用|
|--|--|
|run|运行程序|
|break |设置断点  eg:(break phase_1)|
|b n|在第n行设置断点|
|x/s|查看内容  eg:(x/s $rdi)或(x/s 0x402400)|
