matplotlib绘图的核心原理讲解

使用matplotlib绘图的原理，主要就是理解figure(画布)、axes(坐标系)、axis(坐标轴)三者之间的关系

![img](https://img-blog.csdnimg.cn/20200715104634181.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjU5NzA1Nw==,size_16,color_FFFFFF,t_70)

以“美院学生张三写生画画”为例，来说明这三者之间的关系。

#### 张三的画板
![img](https://img-blog.csdnimg.cn/20200715104707960.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjU5NzA1Nw==,size_16,color_FFFFFF,t_70)

首先，张三想要画画，是不是需要在画板上面准备一张画布。对比到matplotlib中，就相当于初始化了一张figure(画布)，我们画的任何图形，都是在这张figure(画布)上操作的。

接着，张三需要给figure(画布)分配不同的区域，指定哪一块儿究竟该画什么。对比到matplotlib中，就是需要指定axes(坐标系)，每一个axes(坐标系)相当于一张画布上的一块区域。一张画布上，可以分配不同区域，也就是说，一张画布，可以指定多个axes(坐标系)。

最后，张三就是在分配好的不同区域上进行图形绘制了，在一张画布上，画的最多的应该就是2D图，也可以画3D图，如图所示，张三在区域一画了一个小狗，在区域二画了一个小猫，在区域三画了一个光头强。对比到matplotlib中，我们在axes1中画了一个条形图，在axes2中画了一个饼图，在axes3中画了一个折线图。当是2D图时，都会有一个X轴和一个Y轴；当是3D图时，都会有一个X轴、一个Y轴和一个Z轴，这个轴就是我们所说的“坐标轴axis”。

### matplotlib绘图

![img](https://img-blog.csdnimg.cn/20200715104731519.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl80MjU5NzA1Nw==,size_16,color_FFFFFF,t_70)

通过上述分析，总结如下：一个figure(画布)上，可以有多个区域axes(坐标系)，我们在每个坐标系上绘图，也就是说每个axes(坐标系)中，都有一个axis(坐标轴)。

特别注意：在matplotlib中，figure画布和axes坐标轴并不能显示的看见，我们能够看到的就是一个axis坐标轴的各种图形。


# 实战

```
import numpy as np 
from matplotlib import pyplot as plt 
 
x = np.arange(1,11) 
y =  2  * x +  5 
plt.title("Matplotlib demo") 
plt.xlabel("x axis caption") 
plt.ylabel("y axis caption") 
plt.plot(x,y) plt.show()
```
以上实例中，np.arange() 函数创建 x 轴上的值。y 轴上的对应值存储在另一个数组对象 y 中。 这些值使用 matplotlib 软件包的 pyplot 子模块的 plot() 函数绘制。

图形由 show() 函数显示。

![img](https://www.runoob.com/wp-content/uploads/2018/10/matplotlib_demo.jpg)

### 图形中文显示
Matplotlib 默认情况不支持中文，我们可以使用以下简单的方法来解决：

首先下载字体（注意系统）：https://www.fontpalace.com/font-details/SimHei/

SimHei.ttf 文件放在当前执行的代码文件中：
```python
import numpy as np 
from matplotlib import pyplot as plt 
import matplotlib
 
# fname 为 你下载的字体库路径，注意 SimHei.ttf 字体的路径
zhfont1 = matplotlib.font_manager.FontProperties(fname="SimHei.ttf") 
 
x = np.arange(1,11) 
y =  2  * x +  5 
plt.title("线性函数", fontproperties=zhfont1) 
 
# fontproperties 设置中文显示，fontsize 设置字体大小
plt.xlabel("x 轴", fontproperties=zhfont1)
plt.ylabel("y 轴", fontproperties=zhfont1)
plt.plot(x,y) 
plt.show()
```
此外，我们还可以使用系统的字体：

```python
from matplotlib import pyplot as plt
import matplotlib
a=sorted([f.name for f in matplotlib.font_manager.fontManager.ttflist])

for i in a:
    print(i)
```
打印出你的 font_manager 的 ttflist 中所有注册的名字，找一个看中文字体例如：STFangsong(仿宋）,然后添加以下代码即可：

```python
plt.rcParams['font.family']=['STFangsong']
```
作为线性图的替代，可以通过向 plot() 函数添加格式字符串来显示离散值。 可以使用以下格式化字符。

|字符|描述|
|---|---|
|'-'|实线样式|
| '--' | 短横线样式 |
| '-.' | 点划线样式 |
| ':'  | 虚线样式 |
| '.'  | 点标记 |
| ','  | 像素标记 |
| 'o'  | 圆标记 |
| 'v'  | 倒三角标记 |
| '^'  | 正三角标记 |
| '&lt;' | 左三角标记 |
| '&gt;' | 右三角标记 |
| '1'  | 下箭头标记 |
| '2'  | 上箭头标记 |
| '3'  | 左箭头标记 |
| '4'  | 右箭头标记 |
| 's'  | 正方形标记 |
| 'p'  | 五边形标记 |
| '*'  | 星形标记 |
| 'h'  | 六边形标记 1 |
| 'H   | 六边形标记 2 |
| '+'  | 加号标记 |
| 'x'  | X 标记 |
| 'D'  | 菱形标记 |
| 'd'  | 窄菱形标记 |
| '&#124;' | 竖直线标记 |
|'_'|水平线标记|

一下是颜色的缩写

| 字符 | 颜色 |
| ---- | ---- |
| 'b'  | 蓝色 |
|'g'|绿色|
|'r'|红色|
|'c'|青色|
|'m'|品红色|
|'y'|黄色|
|'k'|黑色|
|'w'|白色|

 要显示圆来代表点，而不是上面示例中的线，请使用 ob 作为 plot() 函数中的格式字符串。 



```python
import numpy as np 
from matplotlib import pyplot as plt 
 
x = np.arange(1,11) 
y =  2  * x +  5 
plt.title("Matplotlib demo") 
plt.xlabel("x axis caption") 
plt.ylabel("y axis caption") 
plt.plot(x,y,"ob") 
plt.show()
```

### 绘制正弦波

以下实例使用 matplotlib 生成正弦波图。

```python
import numpy as np 
import matplotlib.pyplot as plt 
# 计算正弦曲线上点的 x 和 y 坐标
x = np.arange(0,  3  * np.pi,  0.1) 
y = np.sin(x)
plt.title("sine wave form")  
# 使用 matplotlib 来绘制点
plt.plot(x, y) 
plt.show()
```

### subplot()

subplot() 函数允许你在同一图中绘制不同的东西。

以下实例绘制正弦和余弦值:

```python
import numpy as np 
import matplotlib.pyplot as plt 
# 计算正弦和余弦曲线上的点的 x 和 y 坐标 
x = np.arange(0,  3  * np.pi,  0.1) 
y_sin = np.sin(x) 
y_cos = np.cos(x)  
# 建立 subplot 网格，高为 2，宽为 1  
# 激活第一个 subplot
plt.subplot(2,  1,  1)  
# 绘制第一个图像 
plt.plot(x, y_sin) 
plt.title('Sine')  
# 将第二个 subplot 激活，并绘制第二个图像
plt.subplot(2,  1,  2) 
plt.plot(x, y_cos) 
plt.title('Cosine')  
# 展示图像
plt.show()
```



```python
x = np.arange(0, 3 * np.pi, 0.1)
y_sin = np.sin(x)
y_cos = np.cos(x)
y_linear = 2 * x + 5
plt.subplot(3, 1, 1)
plt.plot(x, y_sin)
plt.title("Sine")
plt.subplot(3, 1, 2)
plt.plot(x, y_cos)
plt.title('Cosine')
plt.subplot(3, 1, 3)
plt.title("Linear")
plt.plot(x, y_linear)
plt.show()
```



### bar()

pyplot 子模块提供 bar() 函数来生成条形图。

以下实例生成两组 x 和 y 数组的条形图。



```python
from matplotlib import pyplot as plt 
x =  [5,8,10] 
y =  [12,16,6] 
x2 =  [6,9,11] 
y2 =  [6,15,7] 
plt.bar(x, y, align =  'center') 
plt.bar(x2, y2, color =  'g', align =  'center') 
plt.title('Bar graph') 
plt.ylabel('Y axis') 
plt.xlabel('X axis') 
plt.show()
```

### numpy.histogram()

numpy.histogram() 函数是数据的频率分布的图形表示。 水平尺寸相等的矩形对应于类间隔，称为 bin，变量 height 对应于频率。

numpy.histogram()函数将输入数组和 bin 作为两个参数。 bin 数组中的连续元素用作每个 bin 的边界。



```python
import numpy as np 
 
a = np.array([22,87,5,43,56,73,55,54,11,20,51,5,79,31,27])
np.histogram(a,bins =  [0,20,40,60,80,100]) 
hist,bins = np.histogram(a,bins =  [0,20,40,60,80,100])  
print (hist) 
print (bins)
```

### plt()

Matplotlib 可以将直方图的数字表示转换为图形。 pyplot 子模块的 plt() 函数将包含数据和 bin 数组的数组作为参数，并转换为直方图



```python
from matplotlib import pyplot as plt 
import numpy as np  
 
a = np.array([22,87,5,43,56,73,55,54,11,20,51,5,79,31,27]) 
plt.hist(a, bins =  [0,20,40,60,80,100]) 
plt.title("histogram") 
plt.show()
```

# 进阶

### 使用默认配置

Matplotlib 的默认配置都允许用户自定义。你可以调整大多数的默认配置：图片大小和分辨率（dpi）、线宽、颜色、风格、坐标轴、坐标轴以及网格的属性、文字与字体属性等。不过，matplotlib 的默认配置在大多数情况下已经做得足够好，你可能只在很少的情况下才会想更改这些默认配置。

```python
import numpy as np
import matplotlib.pyplot as plt

X = np.linspace(-np.pi, np.pi, 256, endpoint=True)
C,S = np.cos(X), np.sin(X)

plt.plot(X,C)
plt.plot(X,S)

plt.show()
```

### 默认配置的具体内容

下面的代码中，我们展现了 matplotlib 的默认配置并辅以注释说明，这部分配置包含了有关绘图样式的所有配置。代码中的配置与默认配置完全相同，你可以在交互模式中修改其中的值来观察效果。

```python
# 导入 matplotlib 的所有内容（nympy 可以用 np 这个名字来使用）
from pylab import *

# 创建一个 8 * 6 点（point）的图，并设置分辨率为 80
figure(figsize=(8,6), dpi=80)

# 创建一个新的 1 * 1 的子图，接下来的图样绘制在其中的第 1 块（也是唯一的一块）
subplot(1,1,1)

X = np.linspace(-np.pi, np.pi, 256,endpoint=True)
C,S = np.cos(X), np.sin(X)

# 绘制余弦曲线，使用蓝色的、连续的、宽度为 1 （像素）的线条
plot(X, C, color="blue", linewidth=1.0, linestyle="-")

# 绘制正弦曲线，使用绿色的、连续的、宽度为 1 （像素）的线条
plot(X, S, color="green", linewidth=1.0, linestyle="-")

# 设置横轴的上下限
xlim(-4.0,4.0)

# 设置横轴记号
xticks(np.linspace(-4,4,9,endpoint=True))

# 设置纵轴的上下限
ylim(-1.0,1.0)

# 设置纵轴记号
yticks(np.linspace(-1,1,5,endpoint=True))

# 以分辨率 72 来保存图片
# savefig("exercice_2.png",dpi=72)

# 在屏幕上显示
show()
```

### 改变线条的颜色和粗细

首先，我们以蓝色和红色分别表示余弦和正弦函数，而后将线条变粗一点。接下来，我们在水平方向拉伸一下整个图。

```python
...
figure(figsize=(10,6), dpi=80)
plot(X, C, color="blue", linewidth=2.5, linestyle="-")
plot(X, S, color="red",  linewidth=2.5, linestyle="-")
...
```

### 设置图片边界

当前的图片边界设置得不好，所以有些地方看得不是很清楚。

... xlim(X.min()*1.1, X.max()*1.1) ylim(C.min()*1.1, C.max()*1.1) ...

更好的方式是这样：

```python
xmin ,xmax = X.min(), X.max()
ymin, ymax = Y.min(), Y.max()

dx = (xmax - xmin) * 0.2
dy = (ymax - ymin) * 0.2

xlim(xmin - dx, xmax + dx)
ylim(ymin - dy, ymax + dy)
```

![img](https://www.runoob.com/wp-content/uploads/2018/10/1540010470-3522-exercice-4.png)

### 设置记号

我们讨论正弦和余弦函数的时候，通常希望知道函数在 ±π 和 ±π2 的值。这样看来，当前的设置就不那么理想了。

```python
xticks( [-np.pi, -np.pi/2, 0, np.pi/2, np.pi])
yticks([-1, 0, +1])
...
```

![img](https://www.runoob.com/wp-content/uploads/2018/10/1540010516-9654-exercice-5.png)

### 设置记号的标签

记号现在没问题了，不过标签却不大符合期望。我们可以把 3.142 当做是 π，但毕竟不够精确。当我们设置记号的时候，我们可以同时设置记号的标签。注意这里使用了 LaTeX。

```python
...
xticks([-np.pi, -np.pi/2, 0, np.pi/2, np.pi],
       [r'$-\pi$', r'$-\pi/2$', r'$0$', r'$+\pi/2$', r'$+\pi$'])

yticks([-1, 0, +1],
       [r'$-1$', r'$0$', r'$+1$'])
...
```

![img](https://www.runoob.com/wp-content/uploads/2018/10/1540010575-3091-exercice-6.png)

### 移动脊柱

坐标轴线和上面的记号连在一起就形成了脊柱（Spines，一条线段上有一系列的凸起，是不是很像脊柱骨啊~），它记录了数据区域的范围。它们可以放在任意位置，不过至今为止，我们都把它放在图的四边。

实际上每幅图有四条脊柱（上下左右），为了将脊柱放在图的中间，我们必须将其中的两条（上和右）设置为无色，然后调整剩下的两条到合适的位置——数据空间的 0 点。

```python
...
ax = gca()
ax.spines['right'].set_color('none')
ax.spines['top'].set_color('none')
ax.xaxis.set_ticks_position('bottom')
ax.spines['bottom'].set_position(('data',0))
ax.yaxis.set_ticks_position('left')
ax.spines['left'].set_position(('data',0))
...
```

![img](https://www.runoob.com/wp-content/uploads/2018/10/1540010626-7478-exercice-7.png)

### 添加图例

我们在图的左上角添加一个图例。为此，我们只需要在 plot 函数里以「键 - 值」的形式增加一个参数。

```python
...
plot(X, C, color="blue", linewidth=2.5, linestyle="-", label="cosine")
plot(X, S, color="red",  linewidth=2.5, linestyle="-", label="sine")

legend(loc='upper left')
...
```

![img](https://www.runoob.com/wp-content/uploads/2018/10/1540010676-8469-exercice-8.png)

### 给一些特殊点做注释

我们希望在 2π/3 的位置给两条函数曲线加上一个注释。首先，我们在对应的函数图像位置上画一个点；然后，向横轴引一条垂线，以虚线标记；最后，写上标签。

```python
...

t = 2*np.pi/3
plot([t,t],[0,np.cos(t)], color ='blue', linewidth=2.5, linestyle="--")
scatter([t,],[np.cos(t),], 50, color ='blue')

annotate(r'$\sin(\frac{2\pi}{3})=\frac{\sqrt{3}}{2}$',
         xy=(t, np.sin(t)), xycoords='data',
         xytext=(+10, +30), textcoords='offset points', fontsize=16,
         arrowprops=dict(arrowstyle="->", connectionstyle="arc3,rad=.2"))

plot([t,t],[0,np.sin(t)], color ='red', linewidth=2.5, linestyle="--")
scatter([t,],[np.sin(t),], 50, color ='red')

annotate(r'$\cos(\frac{2\pi}{3})=-\frac{1}{2}$',
         xy=(t, np.cos(t)), xycoords='data',
         xytext=(-90, -50), textcoords='offset points', fontsize=16,
         arrowprops=dict(arrowstyle="->", connectionstyle="arc3,rad=.2"))
...
```

![img](https://www.runoob.com/wp-content/uploads/2018/10/1540010726-1211-exercice-9.png)

### 精益求精

坐标轴上的记号标签被曲线挡住了，作为强迫症患者（雾）这是不能忍的。我们可以把它们放大，然后添加一个白色的半透明底色。这样可以保证标签和曲线同时可见。

```python
...
for label in ax.get_xticklabels() + ax.get_yticklabels():
    label.set_fontsize(16)
    label.set_bbox(dict(facecolor='white', edgecolor='None', alpha=0.65 ))
...
```

![img](https://www.runoob.com/wp-content/uploads/2018/10/1540010789-3592-exercice-10.png)

### 图像、子图、坐标轴和记号

到目前为止，我们都用隐式的方法来绘制图像和坐标轴。快速绘图中，这是很方便的。我们也可以显式地控制图像、子图、坐标轴。Matplotlib 中的「图像」指的是用户界面看到的整个窗口内容。在图像里面有所谓「子图」。子图的位置是由坐标网格确定的，而「坐标轴」却不受此限制，可以放在图像的任意位置。我们已经隐式地使用过图像和子图：当我们调用 plot 函数的时候，matplotlib 调用 gca() 函数以及 gcf() 函数来获取当前的坐标轴和图像；如果无法获取图像，则会调用 figure() 函数来创建一个——严格地说，是用 subplot(1,1,1) 创建一个只有一个子图的图像。

### 图像

所谓「图像」就是 GUI 里以「Figure #」为标题的那些窗口。图像编号从 1 开始，与 MATLAB 的风格一致，而于 Python 从 0 开始编号的风格不同。以下参数是图像的属性：

| 参数      | 默认值           | 描述                 |
| :-------- | :--------------- | :------------------- |
| num       | 1                | 图像的数量           |
| figsize   | figure.figsize   | 图像的长和宽（英寸） |
| dpi       | figure.dpi       | 分辨率（点/英寸）    |
| facecolor | figure.facecolor | 绘图区域的背景颜色   |
| edgecolor | figure.edgecolor | 绘图区域边缘的颜色   |
| frameon   | True             | 是否绘制图像边缘     |

这些默认值可以在源文件中指明。不过除了图像数量这个参数，其余的参数都很少修改。

你在图形界面中可以按下右上角的 X 来关闭窗口（OS X 系统是左上角）。Matplotlib 也提供了名为 close 的函数来关闭这个窗口。close 函数的具体行为取决于你提供的参数：

1. 不传递参数：关闭当前窗口；
2. 传递窗口编号或窗口实例（instance）作为参数：关闭指定的窗口；
3. all`：关闭所有窗口。

和其他对象一样，你可以使用 setp 或者是 set_something 这样的方法来设置图像的属性。

### 子图

你可以用子图来将图样（plot）放在均匀的坐标网格中。用 subplot 函数的时候，你需要指明网格的行列数量，以及你希望将图样放在哪一个网格区域中。此外，gridspec 的功能更强大，你也可以选择它来实现这个功能。

![img](https://www.runoob.com/wp-content/uploads/2018/10/1540010993-5537-subplot-horizontal.png)

![img](https://www.runoob.com/wp-content/uploads/2018/10/1540010994-9378-subplot-vertical.png)

![img](https://www.runoob.com/wp-content/uploads/2018/10/1540010994-5352-subplot-grid.png)

![img](https://www.runoob.com/wp-content/uploads/2018/10/1540010994-9663-gridspec.png)

### 坐标轴

坐标轴和子图功能类似，不过它可以放在图像的任意位置。因此，如果你希望在一副图中绘制一个小图，就可以用这个功能。

![img](https://www.runoob.com/wp-content/uploads/2018/10/1540011087-4955-axes.png)

![img](https://www.runoob.com/wp-content/uploads/2018/10/1540011087-8974-axes-2.png)

### 记号

良好的记号是图像的重要组成部分。Matplotlib 里的记号系统里的各个细节都是可以由用户个性化配置的。你可以用 Tick Locators 来指定在那些位置放置记号，用 Tick Formatters 来调整记号的样式。主要和次要的记号可以以不同的方式呈现。默认情况下，每一个次要的记号都是隐藏的，也就是说，默认情况下的次要记号列表是空的——NullLocator。

### Tick Locators

下面有为不同需求设计的一些 Locators。

| 类型              | 说明                                                         |
| :---------------- | :----------------------------------------------------------- |
| `NullLocator`     | No ticks. ![img](https://www.runoob.com/wp-content/uploads/2018/10/1540012569-1664-ticks-NullLocator.png) |
| `IndexLocator`    | Place a tick on every multiple of some base number of points plotted. ![img](https://www.runoob.com/wp-content/uploads/2018/10/1540012569-4591-ticks-IndexLocator.png) |
| `FixedLocator`    | Tick locations are fixed. ![img](https://www.runoob.com/wp-content/uploads/2018/10/1540012569-6368-ticks-FixedLocator.png) |
| `LinearLocator`   | Determine the tick locations. ![img](https://www.runoob.com/wp-content/uploads/2018/10/1540012570-7138-ticks-LinearLocator.png) |
| `MultipleLocator` | Set a tick on every integer that is multiple of some base. ![img](https://www.runoob.com/wp-content/uploads/2018/10/1540012570-8671-ticks-MultipleLocator.png) |
| `AutoLocator`     | Select no more than n intervals at nice locations. ![img](https://www.runoob.com/wp-content/uploads/2018/10/1540012570-3960-ticks-AutoLocator.png) |
| `LogLocator`      | Determine the tick locations for log axes. ![img](https://www.runoob.com/wp-content/uploads/2018/10/1540012571-8369-ticks-LogLocator.png) |

这些 Locators 都是 matplotlib.ticker.Locator 的子类，你可以据此定义自己的 Locator。以日期为 ticks 特别复杂，因此 Matplotlib 提供了 matplotlib.dates 来实现这一功能。

### 其他类型的图

接下来的内容是练习。请运用你学到的知识，从提供的代码开始，实现配图所示的效果。具体的答案可以点击配图下载。

### 普通图

```python
from pylab import *

n = 256
X = np.linspace(-np.pi,np.pi,n,endpoint=True)
Y = np.sin(2*X)

plot (X, Y+1, color='blue', alpha=1.00)
plot (X, Y-1, color='blue', alpha=1.00)
show()
```

![img](https://www.runoob.com/wp-content/uploads/2018/10/1540012800-8583-plot-ex.png)

### 散点图

```python
from pylab import *

n = 1024
X = np.random.normal(0,1,n)
Y = np.random.normal(0,1,n)

scatter(X,Y)
show()
```

![img](https://www.runoob.com/wp-content/uploads/2018/10/1540012848-2516-scatter-ex.png)

### 条形图

```python
from pylab import *

n = 12
X = np.arange(n)
Y1 = (1-X/float(n)) * np.random.uniform(0.5,1.0,n)
Y2 = (1-X/float(n)) * np.random.uniform(0.5,1.0,n)

bar(X, +Y1, facecolor='#9999ff', edgecolor='white')
bar(X, -Y2, facecolor='#ff9999', edgecolor='white')

for x,y in zip(X,Y1):
    text(x+0.4, y+0.05, '%.2f' % y, ha='center', va= 'bottom')

ylim(-1.25,+1.25)
show()
```

![img](https://www.runoob.com/wp-content/uploads/2018/10/1540012917-2001-bar-ex.png)

### 等高线图

```python
from pylab import *

def f(x,y): return (1-x/2+x**5+y**3)*np.exp(-x**2-y**2)

n = 256
x = np.linspace(-3,3,n)
y = np.linspace(-3,3,n)
X,Y = np.meshgrid(x,y)

contourf(X, Y, f(X,Y), 8, alpha=.75, cmap='jet')
C = contour(X, Y, f(X,Y), 8, colors='black', linewidth=.5)
show()
```

![img](https://www.runoob.com/wp-content/uploads/2018/10/1540012957-9070-contour-ex.png)

### 灰度图（Imshow）

```python
from pylab import *

def f(x,y): return (1-x/2+x**5+y**3)*np.exp(-x**2-y**2)

n = 10
x = np.linspace(-3,3,4*n)
y = np.linspace(-3,3,3*n)
X,Y = np.meshgrid(x,y)
imshow(f(X,Y)), show()
```

![img](https://www.runoob.com/wp-content/uploads/2018/10/1540013010-5008-imshow-ex.png)

### 饼状图

```python
from pylab import *

n = 20
Z = np.random.uniform(0,1,n)
pie(Z), show()
```

![img](https://www.runoob.com/wp-content/uploads/2018/10/1540013045-4425-pie-ex.png)

### 量场图（Quiver Plots）

```python
from pylab import *

n = 8
X,Y = np.mgrid[0:n,0:n]
quiver(X,Y), show()
```

![img](https://www.runoob.com/wp-content/uploads/2018/10/1540013096-4226-quiver-ex.png)

### 网格

```python
from pylab import *

axes = gca()
axes.set_xlim(0,4)
axes.set_ylim(0,3)
axes.set_xticklabels([])
axes.set_yticklabels([])

show()
```

![img](https://www.runoob.com/wp-content/uploads/2018/10/1540013134-2724-grid-ex.png)

### 多重网格

```python
from pylab import *

subplot(2,2,1)
subplot(2,2,3)
subplot(2,2,4)

show()
```

![img](https://www.runoob.com/wp-content/uploads/2018/10/1540013171-5292-multiplot-ex.png)

### 极轴图

```python
from pylab import *

axes([0,0,1,1])

N = 20
theta = np.arange(0.0, 2*np.pi, 2*np.pi/N)
radii = 10*np.random.rand(N)
width = np.pi/4*np.random.rand(N)
bars = bar(theta, radii, width=width, bottom=0.0)

for r,bar in zip(radii, bars):
    bar.set_facecolor( cm.jet(r/10.))
    bar.set_alpha(0.5)

show()
```

![img](https://www.runoob.com/wp-content/uploads/2018/10/1540013213-8176-polar-ex.png)

### 3D 图

```python
from pylab import *
from mpl_toolkits.mplot3d import Axes3D

fig = figure()
ax = Axes3D(fig)
X = np.arange(-4, 4, 0.25)
Y = np.arange(-4, 4, 0.25)
X, Y = np.meshgrid(X, Y)
R = np.sqrt(X**2 + Y**2)
Z = np.sin(R)

ax.plot_surface(X, Y, Z, rstride=1, cstride=1, cmap='hot')

show()
```

![img](https://www.runoob.com/wp-content/uploads/2018/10/1540013255-9612-plot3d-ex.png)

### 手稿图

```python
import numpy as np
import matplotlib.pyplot as plt

eqs = []
eqs.append((r"$W^{3\beta}_{\delta_1 \rho_1 \sigma_2} = U^{3\beta}_{\delta_1 \rho_1} + \frac{1}{8 \pi 2} \int^{\alpha_2}_{\alpha_2} d \alpha^\prime_2 \left[\frac{ U^{2\beta}_{\delta_1 \rho_1} - \alpha^\prime_2U^{1\beta}_{\rho_1 \sigma_2} }{U^{0\beta}_{\rho_1 \sigma_2}}\right]$"))
eqs.append((r"$\frac{d\rho}{d t} + \rho \vec{v}\cdot\nabla\vec{v} = -\nabla p + \mu\nabla^2 \vec{v} + \rho \vec{g}$"))
eqs.append((r"$\int_{-\infty}^\infty e^{-x^2}dx=\sqrt{\pi}$"))
eqs.append((r"$E = mc^2 = \sqrt{{m_0}^2c^4 + p^2c^2}$"))
eqs.append((r"$F_G = G\frac{m_1m_2}{r^2}$"))


plt.axes([0.025,0.025,0.95,0.95])

for i in range(24):
    index = np.random.randint(0,len(eqs))
    eq = eqs[index]
    size = np.random.uniform(12,32)
    x,y = np.random.uniform(0,1,2)
    alpha = np.random.uniform(0.25,.75)
    plt.text(x, y, eq, ha='center', va='center', color="#11557c", alpha=alpha,
             transform=plt.gca().transAxes, fontsize=size, clip_on=True)

plt.xticks([]), plt.yticks([])
# savefig('../figures/text_ex.png',dpi=48)
plt.show()
```

![img](https://www.runoob.com/wp-content/uploads/2018/10/1540013329-8438-text-ex.png)