# Shell基本教程

shell脚本是什么呢？首先它是一个脚本，并不能作为正式的编程语言。因为是跑在linux的shell中，所以叫shell脚本。说白了，shell脚本就是一些命令的集合。举个例子，我想实现这样的操作：1）进入到/tmp/目录；2）列出当前目录中所有的文件名；3）把所有当前的文件拷贝到/root/目录下；4）删除当前目录下所有的文件。简单的4步在shell窗口中需要你敲4次命令，按4次回车。这样是不是很麻烦？当然这4步操作非常简单，如果是更加复杂的命令设置需要几十次操作呢？那样的话一次一次敲键盘会很麻烦。所以不妨把所有的操作都记录到一个文档中，然后去调用文档中的命令，这样一步操作就可以完成。其实这个文档呢就是shell脚本了，只是这个shell脚本有它特殊的格式。

Shell脚本能帮助我们很方便的去管理服务器，因为我们可以指定一个任务计划定时去执行某一个shell脚本实现我们想要需求。这对于linux系统管理员来说是一件非常值得自豪的事情。现在的139邮箱很好用，发邮件的同时还可以发一条邮件通知的短信给用户，利用这点，我们就可以在我们的linux服务器上部署监控的shell脚本，比如网卡流量有异常了或者服务器web服务器停止了就可以发一封邮件给管理员，同时发送给管理员一个报警短信这样可以让我们及时的知道服务器出问题了。

有一个问题需要约定一下，凡是自定义的脚本建议放到/usr/local/sbin/目录下，这样做的目的是，一来可以更好的管理文档；二来以后接管你的管理员都知道自定义脚本放在哪里，方便维护。

## 第一个shell脚本

```shell
#! /bin/bash
## author:Liang Aokai
## This is my first shell script
```

打开方式由两种

```bash
sh test.sh
#或者
chmod +x test.sh
./test.sh
```

## date的使用

```shell
$ date "+%Y-%m/%d %H%M%S"
2020-09/03 211404

$ date "+%y-%m/%d %H%M%S"
20-09/03 211623


```

-d 选项也是经常要用到的，它可以打印n天前或者n天后的日期，当然也可以打印n个月/年前或者后的日期。

```shell
$ date -d "+1 month" "+%Y-%m/%d"
2020-10/03

$ date -d "+1 year" "+%Y-%m/%d"
2021-09/03
```

## 变量的使用

```shell
#! /bin/bash
## aughor:Liang Aokai
## This is mu first shell scrpit

d=`date +%H:%M:%S`
echo "the script begin at $d"
echo "now we will sleep 2 seconds"
sleep 2
d1=`date +%H:%M:%S`
echo "the script end at $d1"

```

```
output:
the script begin at 21:24:16
now we will sleep 2 seconds
the script end at 21:24:18
```



在test2.sh中使用到了反引号，你是否还记得它的作用？’d’和’d1’在脚本中作为变量出现，定义变量的格式为 “变量名=变量的值”。当在脚本中引用变量时需要加上’$’符号，这跟前面讲的在shell中自定义变量是一致的。下面看看脚本执行结果吧。

下面我们用shell计算两个数的和。



```shell
#! /bin/bash
## author:Liang Aokai
## for get two numbers' sum
a=1
b=2
sum=$[$a+$b]
echo "sum is $sum"

```

```
output
sum is 3

```

数学计算要用’[ ]’括起来并且外头要带一个’$’。

## 用户交互

```shell
#! /bin/bash
# author:Liang Aokai
# use read in the script

echo "Please input a number..."
read x
echo "Please input anothre number"
read y
sum=$[$x+$y]
echo "sum is $sum"
```

或者用这种方法

```shell
read -p "Read one"
read -p "Read two"
sum=$[$x+$y]
echo "sum is $sum"
```

Shell 脚本名字后也可跟参数

```shell
#! /bin/bash
## author:Liang Aokai

sum=$[$1+$2]
echo "sum is $sum"
```

通过这种方式

```
//方法一
sh readByArgv.sh 5 6

Output:
sum is 10

//方法二
sh -x readByArgv.sh 9 4

Output:
+ sum=11
+ echo 'sum is 11'
sum is 11

```

其中\$1,\$2是预设的值，用于传递参数，\$0就是文件名本身