## 什么是MySQL？
数据的所有存储，检索，管理和处理实际上是由数据库软件——DBMS（数据库管理系统）完成的，而MySQL就是一种DBMS。

MySQL， Oracle， Microsoft SQL Server等数据库是基于客户机-服务器的数据库，服务器部分是负责所有数据访问和处理的一个软件，这个软件运行在成为数据库服务器的计算机上，而客户机是与用户打交道的软件，如请求按字母顺序列出产品表，客户机软件通过网络像服务器软件提交请求，服务器软件再对这个请求进行处理，根据需要过滤，丢弃和排序数据，再把结果送回到客户机软件。

本来我是在电脑上安了mysql用来学习，后来嫌数据库后台运行占用后台资源过多，就索性删了，开了一个阿里的云数据库通过Navicat远程链接来学习，记下来记录一下我的链接步骤。

在开通rds后有几个必要的步骤才能进行远程链接

 1. 创建实列
 2. 把对数据库进行访问的客户端IP加入白名单
 3. 设置白名单后就可以申请外网地址了
 4. 创建数据库与账号
 5. 用Navicat进行远程连接，只需要输入申请的外网地址和自己创建的账号密码就可以了

![使用流程图](https://imgconvert.csdnimg.cn/aHR0cDovL3N0YXRpYy1hbGl5dW4tZG9jLm9zcy1jbi1oYW5nemhvdS5hbGl5dW5jcy5jb20vYXNzZXRzL2ltZy96aC1DTi8xODg3MTY1NzUxL3AxNzc2LnBuZw?x-oss-process=image/format,png)
具体步骤可以参考阿里的官方教程：
[云数据库 RDS > RDS MySQL 数据库 > 快速入门 > 使用流程
](https://help.aliyun.com/document_detail/26124.html)

接下来进入正题，**MySQL的基本语法**
*（实例来自《MySQL必知必会》）*

首先要了解一些基本术语:

 1. 表：数据库就像是一个文件柜，将资料放入文件柜时，我们会先在文件柜中创建文件，让后再把相关资料放入文件柜中，而这个文件就是表，表是某种特定类型程序的结构化清单。
 2. 列（column）：表中的一个字段，表由一个或多个列组成。
 3. 行（row）：表中的一个记录。
 4. 主键（primary key）：一列（或一组列），其值能区分为一区分表中每个行。

连接到MySQL后首先要选择一个数据库， 如crashcourse

```
输入：USE crashcourse；
输出：Database changed
```
如果不知道数据库的名字，则可以先使用SHOW命令显示数据库

```
SHOW DATABASES；
```

获得数据库内的列表

```
SHOW TABLES；
```
若要返回每一行，每一行中包含字段名，数据类型，以及是否允许NULL， 键信息， 默认值以及其他信息则用

```
SHOW COLUMNS FROM customers；
```

## 检索数据
SELECT语句，使用SELECT时首要要给出至少两条信息：选择什么，从哪里选择

```
检索单个列：
SELECT prod_name
FROM products;

检索多个列：
SELECT pro_Id, prod_name, prod_price
FROM products;

检索所有列：
SELECT *
FROM products;

检索不同行：
SELECT vend_id
FROM products;

只返回不同的行：
SELECT DISTINCT vend_id
FROM products;

限制行数：
SELECT prod_name
FROM products
LIMIT 5;
//显示前5行

SELECT prod_name
FROM products
LIMIT 5， 5；
//显示从第五行到第10行

//按特定顺序排序
SELECT prod_name
FROM products
ORDER BY prod_name;

//按多个列排序
SELECT prod_id, prod_price, prod_name
FROM products
ORDER BY prod_price, prod_name;
//对于上述列子，仅在多行具有相同的prod_price值时才按prod_name进行排序。

//指定排序方向
SELECT prod_id, prod_price, prod_name
FROM products
ODER BY prod_price, DESC;
//按降序排序
或
SELECT prod_id, prod_price, prod_name
FROM products
ORDER BY prod_price DESC, prod_name;
//以降序排序产品价格，再正常的按升序给名字排名，DESC作用于其前面的关键字

//找出最贵的物品
SELECT prod_id
FROM products
ORDER BY prod_price DESC
LIMIT 1;
```


## 过滤数据
WHERE子句
|操作符|说明  |
|:--:|:--:|
|=  | 等于 |
|<> 或 !=|不等于|
|<|小于|
|<=|小于等于|
|>|大于|
|>=|大于等于|
|BETWEEN|再两个指定值间|

```
SELECT prod_name, prod_price
FROM products
WHERE prod_name = 'fuses';

SELECT prod_name, prod_price
FROM products
WHERE prod_price < 10;

SELECT prod_name, prod_price
FROM products
WHERE vend_id <> 1003;

SELECT prod_name, prod_price
FROM products
WHERE prod_id BETWEEN 5 AND 10;

SELECT prod_name, prod_price
FROM products
WHERE prod_price IS NULL;

SELECT prod_name, prod_price
FROM products
WHERE vend_id = 1003 AND prod_price <=10;

SELECT prod_name, prod_price
FROM products
WHERE vend_id = 1002 OR vend_id = 1003

//计算次序
SELECT prod_name, prod_price
FROM products
WHERE vend_id = 1002 OR vend_id = 1003 AND prod_price >=10;
//AND优先级大于OR，除非带括号。

//IN指定条件范围
SELECT prod_name, prod_price
FROM products
WHERE vend_id IN (1002, 1003)
ORDER BY prod_name;
//IN 的作用与OR 类似

SELECT prod_name, prod_price
FROM products
WHERE vend_id NOT IN (1002, 1003)
ORDER BY prod_name;

//通配符
//LIKE
SELECT prod_name, prod_price
FROM products
WHERE  prod_name LIKE 'jet%';
//以jet起头的词，%告诉接受jet后的任意字符

SELECT prod_name, prod_price
FROM products
WHERE prod_name LIKE '%anvil%';
//带有anvil的任意词

SELECT prod_name, prod_price
FROM products
WHERE prod_name LIKE 's%e'
//以s起头， e结尾的词

//下划线_: 用途与LIKE一样，但只识别单个字符
```

## 正则表达式
正则表达式的作用就是检索数据，Linux等等很多都会用到正则表达式
**基本字符匹配**

```
SELECT prod_name
FROM products
WHERE prod_name REGEXP '1000'
ORDER BY name;
//检索包含文本100的所有行
```
注意，LIKE与REGEXP还是有区别的，LIKE匹配列，如果匹配的文本在列值中则不会被检索到，而REGEXP在列值中进行匹配。

```
//OR匹配
SELECT prod_name
FROM products
WHERE prod_name REGEXP '1000|2000'
ORDER BY name;
```
- [1-4]：1到4中任意一个数都可以，或[1234]，
- ^是除了接下来的内容。
- .匹配任意字符
- 特殊字符前加\\

如匹配

```
SELECT prod_name
FROM products
WHERE prod_name REGEXP '\\([1-9] sticks?\\'
ORDER BY name;
```

**定位符**
- ^：文本的开始
- $：文本的结尾
- [[:<:]]：词的开始
- [[:>:]]：词的结尾

如
```
SELECT prod_name
FROM products
WHERE prod_name REGEXP '^-0-9]\\.]'
ORDER BY name;
```

## 创建计算字段
**拼接**
Concat 与 As关键字
```
SELECT Concat(vend_name, '(', vend_contry, ')') AS vend_title
FROM vendors
ORDER BY name;
```

## 使用数据处理函数
作用：对文本数据进行处理
>Upper()
>Left()
>Length()
>Locate()
>Lower()
> //去掉左边的空格：
>LTrim()                   
>RTrim()
>....

写一个示例

```
SELECT vend_name, Upper(vend_name) AS vend_name_upcase
FROM vendors
OR
```
# Mysql学习（二）
先回顾一下之前的内容：

- 检索数据
- 排序检索
- 过滤数据（WHERE）
- 通配符（LIKE）
- 正则表达式（REGEXP）
- 创建计算字段
- 数据处理函数
- 子查询


# 联结表
#### 内部联结

场景：
假如有一个包含产品目录的数据库表，其中每种类别的物品占一行，这一行里有对物品的描述信息，如价格，类别，生产日期等等，以即 **供应商信息**

现在假如有同一供应商生产的多种物品，那么在何处存储供应商信息呢（供应商姓名，地址，联系方式等）？将这些供应商的信息与商品分开存储有以下几个理由：

- 减少存储空间
- 减少耦合性，修改商家信息时不用在每个商品后修改

在这个例子里可以创建两个表，一个存供应商信息，一个存商品信息。vendors表包含所有供应商信息，每个供应商占一行，有一个主键，products表则只存商品信息，它除了存储供应商的ID（vendors的主键）外不存储供应商的其他信息，通过主键可将两个表关联起来。

```mysql
SELECT vend_name, prod_name, prod_price
FROM vendors, products
WHERE vendors.vend_id = products.vend_id
ORDER BY vend_name, prod_name;


或者

SELECT vend_name, prod_name, prod_price
FROM vendors INNER JOIN products
ON vendors.vend_id = products.vend_id;

```
结果如下
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200606122158816.PNG?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjU5NzA1Nw==,size_16,color_FFFFFF,t_70)

*注意： 

如果漏掉了WHERE，则得到的结果为两个表做笛卡尔积*

同样的，可以联结多个表

```mysql
SELECT prod_name, vend_name, prod_price, quantity
FROM orderitems, products, vendors
WHERE products.vend_id = vendors.vend_id
 AND orderitems.prod_id = products.prod_id
 AND order_num = 20005; 
```


###### 使用列表别名
```mysql
SELECT cust_name, cust_contact
FROM customers AS c, orders AS o, orderitems AS oi
WHERE c.cust_id = o.cust_id
AND oi.order_num = o.order_num
AND prod_id = 'TNT2';
```

#### 自联结
如果你发现某种商品（ID为DTNTR）存在问题，想要知道该供应商的其他物品是否也存在这样的问题，该查询要求先找ID为DTNTR的物品的供应商，再找出它的其他物品。如下

```mysql
SELECT prod_id, prod_name
FROM products
WHERE vend_id = (SELECT vend_id 
                 FROM products
                 WHERE prod_id = 'DTNTR');
                 
```

也可以使用自联结
```mysql
SELECT p1.prod_id, p1.prod_name
FROM products AS p1, products AS p2
WHERE p1.vend_id = p2.vend_id
AND p2.prod_id = 'DTNTR';
```


#### 外部联结
许多联结可以将一个表中的行与另一个表中的行联结，但有时要包含没有关联行的那些行，如

- 对每个客户下了多少订单进行计数，包括至今未下订单的客户
- 列出所有产品以及订购数量，包括没有人订购的商品
- 计算平均销售规模，包括至今未下单的客户

```mysql
SELECT customers.cust_id, orders.order_num
FROM customers LEFT OUTER JOIN orders
ON customers.cust_id = orders.cust_id;


```