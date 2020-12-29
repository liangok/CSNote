[TOC]

# DQN与增强学习



## 前言 

深度增强学习Deep Reinforcement Learning是将深度学习与增强学习结合起来从而实现从Perception感知到Action动作的端对端学习End-to-End Learning的一种全新的算法。简单的说，就是和人类一样，输入感知信息比如视觉，然后通过深度神经网络，直接输出动作，中间没有hand-crafted engineering的工作。深度增强学习具备使机器人实现真正完全自主的学习一种甚至多种技能的潜力。

虽然将深度学习和增强学习结合的想法在几年前就有人尝试，但真正成功的开端就是DeepMind在NIPS 2013上发表的**[Playing Atari with Deep Reinforcement Learning](https://link.zhihu.com/?target=http%3A//arxiv.org/abs/1312.5602)**一文，在该文中第一次提出Deep Reinforcement Learning 这个名称，并且提出DQN（Deep Q-Network）算法，实现从纯图像输入完全通过学习来玩Atari游戏的成果。之后DeepMind在Nature上发表了改进版的DQN文章**Human-level Control through Deep Reinforcement Learning**，引起了广泛的关注，Deep Reinfocement Learning 从此成为深度学习领域的前沿研究方向。

![img](https://pic1.zhimg.com/d595ffc4ed8c8cbc8db99e15e0be1721_r.jpg)

而Hinton，Bengio及Lecun三位大神在Nature上发表的[Deep Learning综述](https://link.zhihu.com/?target=http%3A//www.cs.toronto.edu/~hinton/absps/NatureDeepReview.pdf)一文最后也将Deep Reinforcement Learning作为未来Deep Learning的发展方向。引用一下原文的说法：

> We expect much of the future progress in vision to come from systems that are trained end-to-end and combine ConvNets with RNNs that use reinforcement learning to decide where to look.

从上面的原文可见三位大神对于Deep Reinforcement Learning的期待。而显然这一年来的发展没有让大家失望，AlphaGo横空出世，将进一步推动Deep Reinforcement Learning的发展。

Deep Reinforcement Learning的重要关键在于其具备真正实现AI的潜力，它使得计算机能够完全通过自学来掌握一项任务，甚至超过人类的水平。也因此，DeepMind很早受到了Google等企业的关注。DeepMind 50多人的团队在2014年就被Google以4亿美元的价格收购。而15年12月份刚刚由Elon Musk牵头成立的OpenAI，则一开始就获得了10亿美元的投资，而OpenAI中的好几位成员都来自UC Berkerley的Pieter Abbeel团队。

Pieter Abbeel团队紧随DeepMind之后，采用基于引导式监督学习直接实现了机器人的End-to-End学习，其成果也引起了大量的媒体报道和广泛关注。去年的NIPS 2015 更是由Pieter Abbeel及DeepMind的David Silver联合组织了Deep Reinforcement Learning workshop。可以说，目前在Deep Reinforcement Learning取得开拓性进展的主要集中在DeepMind和UC Berkerley团队。

为了研究Deep Reinforcement Learning，DQN的学习是首当其冲的。只有真正理解了DQN算法，才能说对Deep Reinforcement Learning入门。要理解并掌握DQN算法，需要增强学习和深度学习的多方面知识，笔者在2014年底开始接触DQN，但由于对基础知识掌握不全，导致竟然花了近1年的时间才真正理解DQN的整个算法。因此，本专栏从今天开始推出 **DQN 从入门到放弃 系列**文章，意在通过对增强学习，深度学习等基础知识的讲解，以及基于Tensorflow的代码实现，使大家能够扎实地从零开始理解DQN，入门Deep Reinforcement Learning。本系列文章将以一周一篇的速度更新。另外要说明的一点是DQN已被Google申请专利，因此只能做研究用，不能商用。

## 预备条件

虽然说是从零开始，但是DQN毕竟也还属于深度学习领域的前沿算法，为了理解本系列的文章，知友们还是需要有一定的基础：

- 一定的概率论和线性代数基础（数学基础）
- 一定的Python编程基础（编程基础，后面的代码实现将完全基于Tensorflow实现）

考虑到目前理解深度学习的知友肯定比理解增强学习的知友多，并且专栏也在同步翻译CS231N的内容，本系列文章计划用极短的篇幅来介绍DQN所使用的深度学习知识，而用更多的篇幅介绍增强学习的知识。

如果知友们具备以上的基本预备条件，那么我们就可以开始DQN学习之旅了。

接下来本文将介绍增强学习的基础知识。

## 增强学习是什么

在人工智能领域，一般用**智能体Agent**来表示一个具备行为能力的物体，比如机器人，无人车，人等等。那么增强学习考虑的问题就是**智能体Agent**和**环境Environment**之间交互的任务。比如一个机械臂要拿起一个手机，那么机械臂周围的物体包括手机就是环境，机械臂通过外部的比如摄像头来感知环境，然后机械臂需要输出动作来实现拿起手机这个任务。再举玩游戏的例子，比如我们玩极品飞车游戏，我们只看到屏幕，这就是环境，然后我们输出动作（键盘操作）来控制车的运动。

那么，不管是什么样的任务，都包含了一系列的**动作Action**,**观察Observation**还有**反馈值Reward**。所谓的Reward就是Agent执行了动作与环境进行交互后，环境会发生变化，变化的好与坏就用Reward来表示。如上面的例子。如果机械臂离手机变近了，那么Reward就应该是正的，如果玩赛车游戏赛车越来越偏离跑道，那么Reward就是负的。接下来这里用了Observation观察一词而不是环境那是因为Agent不一定能得到环境的所有信息，比如机械臂上的摄像头就只能得到某个特定角度的画面。因此，只能用Observation来表示Agent获取的感知信息。



![img](https://pic3.zhimg.com/601390d3e432c05da7cc24f3f96bb9fb_r.jpg)

上面这张图（[来自David Silver的课程ppt](https://link.zhihu.com/?target=http%3A//www0.cs.ucl.ac.uk/staff/D.Silver/web/Teaching.html)![[公式]](https://www.zhihu.com/equation?tex=a_t)

那么知道了整个过程，任务的目标就出来了，那就是要能获取尽可能多的Reward。没有目标，控制也就无从谈起，因此，获取Reward就是一个量化的标准，Reward越多，就表示执行得越好。每个时间片，Agent都是根据当前的观察来确定下一步的动作。观察Observation的集合就作为Agent的所处的**状态State**，因此，**状态State**和**动作Action**存在映射关系，也就是一个state可以对应一个action，或者对应不同动作的概率（常常用概率来表示，概率最高的就是最值得执行的动作）。状态与动作的关系其实就是输入与输出的关系，而状态State到动作Action的过程就称之为一个**策略Policy，**一般用![[公式]](https://www.zhihu.com/equation?tex=%5Cpi+)表示，也就是需要找到以下关系：

![[公式]](https://www.zhihu.com/equation?tex=a%3D%5Cpi%28s%29)
![[公式]](https://www.zhihu.com/equation?tex=%5Cpi%28a%7Cs%29)

其中a是action，s是state。第一种是一一对应的表示，第二种是概率的表示。

> 增强学习的任务就是找到一个最优的策略Policy从而使Reward最多。

我们一开始并不知道最优的策略是什么，因此往往从随机的策略开始，使用随机的策略进行试验，就可以得到一系列的状态,动作和反馈：

![[公式]](https://www.zhihu.com/equation?tex=%5C%7Bs_1%2Ca_1%2Cr_1%2Cs_2%2Ca_2%2Cr_2%2C...s_t%2Ca_t%2Cr_t%5C%7D)



# 增强学习与MDP



## 增强学习的世界观

可能很少有人用世界观来看增强学习甚至人工智能的一些问题，但实际上任何问题的建立都是在一个基本的假设下进行构建的，也就是这个领域的世界观。

**那么，增强学习建立在怎样的世界观上呢？**

增强学习的研究依然建立在经典物理学的范畴上，也就是没有量子计算也没有相对论。这个世界的时间是可以分割成一个一个时间片的，并且有完全的先后顺序，因此可以形成

![[公式]](https://www.zhihu.com/equation?tex=%5C%7Bs_0%2Ca_0%2Cr_0%2Cs_1%2Ca_1%2Cr_1%2C...s_t%2Ca_t%2Cr_t%5C%7D)

另一个很重要的假设就是

**上帝不掷筛子！**

在增强学习的世界，我们相信如果输入是确定的，那么输出也一定是确定的。试想一下，有一个机械臂在练习掷筛子，以掷出6点作为目标。但是如果无论机械臂如何调整其关节的角度及扭矩，掷出的点数永远是随机的，那么无论如何也不可能通过算法使机械臂达成目标。因此，增强学习算法要有用，就是相信在增强学习中每一次参数的调整都会对世界造成确定性的影响。

以上两点便是增强学习算法建立的基础。当然了，基本上人工智能的研究都是在这样的基础上进行研究，探讨其世界观的意义并不是很大，但是意识到这一点可以有助于理解当前人工智能研究的局限性。

那么有了时间和确定性的假设，MDP（Markov Decision Process）便是为了描述这个世界而提出的概念。

## MDP（Markov Decision Process）马尔科夫决策过程

MDP基于这样一种假设：

> 未来只取决于当前

什么意思呢？

就是如果我们站在上帝视角下看，我们知道这个世界的每个物体的状态，那么未来的变化只跟当前的状态相关，和过去没有关系。

用数学的话来描述就是：

一个状态![[公式]](https://www.zhihu.com/equation?tex=S_t)是Markov当且仅当

![[公式]](https://www.zhihu.com/equation?tex=P%28s_%7Bt%2B1%7D%7Cs_t%29%3DP%28s_%7Bt%2B1%7D%7Cs_t%2Cs_%7Bt-1%7D%2C...s_1%2Cs_0%29)

往大的说，如果宇宙起始于大爆炸的奇点。那么奇点状态确定，如果上帝不掷筛子的话，那么宇宙就是一个MDP的过程，它的每一步都是确定的，推到人身上就是每个人的命运也都是确定的。换句话讲，我们现在的所思所想不过是由我们身上的每个细胞导致的精神状态，意识以及周围的环境所完全确定的。所以MDP的宇宙大概没什么意思，还好还有量子力学和相对论，我也相信这个世界是不确定的。

回到增强学习的范畴，增强学习的问题都可以模型化为MDP的问题。

一个基本的MDP可以用（S,A,P）来表示，S表示状态，A表示动作，P表示状态转移概率，也就是根据当前的状态![[公式]](https://www.zhihu.com/equation?tex=s_t)和![[公式]](https://www.zhihu.com/equation?tex=a_t)转移到![[公式]](https://www.zhihu.com/equation?tex=s_%7Bt%2B1%7D)的概率。如果我们知道了转移概率P，也就是称为我们获得了**模型Model**，有了模型，未来就可以求解，那么获取最优的动作也就有可能，这种通过模型来获取最优动作的方法也就称为Model-based的方法。但是现实情况下，很多问题是很难得到准确的模型的，因此就有Model-free的方法来寻找最优的动作。关于具体的方法这里不具体讨论。在以后的文章中我们会通过分析具体的算法对此有个明确的认识。

## 回报Result

既然一个状态对应一个动作，或者动作的概率，而有了动作，下一个状态也就确定了。这就意味着每个状态可以用一个确定的值来进行描述。可以由此判断一个状态是好的状态还是不好的状态。比如，向左边走就是悬崖，悬崖肯定不是好的状态，再走一步可能就挂了，而向右走就是黄金，那么右边的状态就是好的状态。

那么状态的好坏其实等价于对未来回报的期望。因此，引入**回报Return**来表示某个时刻t的状态将具备的回报：

![[公式]](https://www.zhihu.com/equation?tex=G_t+%3D+R_%7Bt%2B1%7D+%2B+%5Clambda+R_%7Bt%2B2%7D+%2B+...+%3D+%5Csum_%7Bk%3D0%7D%5E%5Cinfty%5Clambda%5EkR_%7Bt%2Bk%2B1%7D)

那么实际上除非整个过程结束，否则显然我们无法获取所有的reward来计算出每个状态的Return，因此，再引入一个概念价值函数Value Function,用value function ![[公式]](https://www.zhihu.com/equation?tex=v%28s%29)来表示一个状态未来的潜在价值。还是上面的例子，这里就变成是向左看感觉左边是悬崖那么左边的状态的估值就低。

从定义上看，value function就是回报的期望：

![[公式]](https://www.zhihu.com/equation?tex=v%28s%29+%3D+%5Cmathbb+E%5BG_t%7CS_t+%3D+s%5D)

- 直接优化策略![[公式]](https://www.zhihu.com/equation?tex=%5Cpi%28a%7Cs%29)或者![[公式]](https://www.zhihu.com/equation?tex=a+%3D+%5Cpi%28s%29)使得回报更高
- 通过估计value function来间接获得优化的策略。道理很简单，既然我知道每一种状态的优劣，那么我就知道我应该怎么选择了，而这种选择就是我们想要的策略。

当然了，还有第三种做法就是融合上面的两种做法，这也就是以后会讲到的actor-critic算法。但是现在为了理解DQN，我们将只关注第二种做法，就是估计value function的做法，因为DQN就是基于value function的算法。

# 价值函数与Bellman方程

## 上文回顾 

在上一篇文章[DQN 从入门到放弃 第二篇](https://zhuanlan.zhihu.com/p/21292697?refer=intelligentunit)中，我们探讨了增强学习问题的基本假设，然后引出了MDP马尔科夫决策过程。MDP只需要用一句话就可以说明白，就是“未来只取决于当前”，专业点说就是下一步的状态只取决于当前的状态，与过去的状态没有关系。这里大家要注意这里所说的状态是**完全可观察的**，也就是上帝眼中的世界。再举例说明一下完全可观察的意思就是比如我们的眼睛看到的世界，那就是不完全可观察的，我们并不清楚的知道眼前的每一个物体，比如人，车，动物的真实物理位置，因此也就是无法准确知道它们下一个时刻的状态（比如车的位置）只能通过估算的方法来估计。而在上帝眼中，那么每一个物体的位置和速度信息都是确定的，也因此下一个时刻的状态也就是完全确定的。

在引出了MDP之后，由于每一个时刻的状态是确定的，因此我们可以用Value Function价值函数来描述这个状态的价值，从而确定我们的决策方式。有知友表示不是很理解Value Function，那么下面我们再具体探讨一下。

## Value Function 价值函数

我们用一个例子来说明Value Function的含义与重要性。

这是一个投资决策问题：假如我们有一笔X美刀的资金，我们眼前有三种选择来使用这笔资金：

1. 使用资金进行股票投资
2. 使用资金进行买房投资
3. 使用资金购买书籍，科研设备等提升资金

那么，我们就面临如何做选择的问题。这里假设我们只能选择其中的一个做选择。

我们先来解释一下直接基于Policy的方法是怎样的。

直接基于Policy的意思就是我们有一套Policy策略，我们基于这个策略进行操作，比如可以有如下所示的策略：

```text
if 资金X > 500000:
   选择股票投资
else if 资金X > 100000:
   选择房产投资
else:
   选择买书，买设备自我提升
```

那么上面的伪代码就是表示一个极其简单的策略。这个策略只考虑资金量，输入资金量，输出决策方式。如果把Policy策略看做是一个黑箱，那么基于策略的方法就是：



![img](https://pic1.zhimg.com/10136153e873ef316ae6b4adddf39d69_r.jpg)

那么如果不是基于Policy策略直接做出决策，我们还有什么办法呢？



显然有，而且大家可以从上面的简单策略看到一个最大的缺陷，就是上面的策略完全不考虑每一种选择未来的价值。我们做决策是有目的的，那就是为了最大化未来的回报Result是不是？那么对于上面的投资选择问题，我们的目标就是希望我们的投资回报率最高。因此，上面的简单策略竟然完全不考虑每一种选择的价值，而仅考虑资金量，显然是一种欠考虑的方法。因此，我们是不是应该评估一下每一种选择的潜在价值呢？耶，价值Value出来了，是不是？通过对价值的评估，我们也就可以有如下的做决策的方法：



![img](https://pic3.zhimg.com/86c3aade0b8e3eb4b563d6927d9871ce_r.jpg)

我们就评估每一种状态（选择+资金量）的价值，然后选择价值最高的作为最后的决策。



比如说：

```text
if 投资股市:
    因为股市低迷，价值为-100
if 投资房产 + 资金量 > 100000:
    因为房产泡沫还没破，各地房价还在涨，价值为+500
if 提升自己 + 资金量 < 100000:
    当前人工智能潜力巨大，资金又不算太大，投资自己价值为+1000
...(更多的评估价值的方法）
```

OK, 假设现在我们有50000的资金，那么根据我们的价值估算方法，选择投资自己的价值最大，为+1000，因此我们就选择投资自己作为决策结果。

从数学的角度，我们常常会用一个函数![[公式]](https://www.zhihu.com/equation?tex=V%28s%29)来表示一个状态的价值，也可以用![[公式]](https://www.zhihu.com/equation?tex=Q%28s%2Ca%29)来表示状态及某一个动作的价值。我们上面的例子就是来评估某一个状态下动作的价值，然后根据价值做出判断。实际上我们这里也是有策略的，我们的策略更简单：

```text
if 某一个决策的价值最大：
    选择这个决策
```

这就是价值函数的意义。在后面的文章当中，我们还会发现，其实我们还可以同时使用策略加价值评估的方法来联合给出决策，这种算法就是所谓的**Actor-Critic**算法。这里就不多加介绍了。

## 再谈增强学习的意义

从上面的例子想必大家会发现，增强学习面向的决策与控制问题与我们的行为息息相关。我们想要让计算机自己能够学习做出某种决策，并且是可以不断改进的决策。对于人工智能，最大的目的不就是要创造智能，会自己思考，能模仿人类的行为，从而能够代替人类做事情吗？增强学习的目的就是希望计算机能够模仿人类的行为。这样一看，知友们是不是马上觉得增强学习意义非常之大。

实际上增强学习的算法都是基于人类的行为而构建的。比如上面的Value Function价值函数，实际上人类自己做决策的时候也就是那么做的。这里只不过是把它数学化了而已。

按照DeepMind的David Silver，也就是AlphaGo的第一作者所言：

> DL + RL = AI

DL深度学习给了计算机“神经网络大脑”，RL给了计算机学习机制。这两者结合起来，就能创造智能！

多说这么几句只是想让知友们真正意识到 Deep Reinforcement Learning深度增强学习为什么这么重要，这么值得研究。

接下来，我们介绍Bellman方程，增强学习领域最重要的一个方程。很多算法都是基于Bellman方程衍生而来。

## Bellman方程

在上文我们介绍了Value Function价值函数，所以为了解决增强学习的问题，一个显而易见的做法就是----

> 我们需要估算Value Function

是的，只要我们能够计算出价值函数，那么最优决策也就得到了。因此，问题就变成了如何计算Value Function？

怎么估算价值呢？

我们还是先想想我们人是怎么估算的？我们还是以上面的投资决策问题来作为例子

一般我们基于以下几种情况来做评估：

- 其他人的选择。比如有很多人投资股市失败，因此我们就会降低我们投资股票的价值。
- 自己的反复试验。我们常常不是只做一次选择，而是做了很多次选择，从而收获了所谓的“经验”的东西。我们根据经验来评估选择的价值。比如我们做了好几次投资楼市的选择，结果大获成功，因此我们就会认为投资楼市是不错的选择。
- 基于理性分析。我们根据我们已有的知识对当前的情况做分析，从而做出一定的判断。
- 基于感性的逻辑。比如选择投资自己到人工智能领域。虽然我们大约觉得人工智能前景很好，但是我们真正要投资自己到这个领域有时候仅仅是出于一种热爱或者说一种理想主义。就是不管别人觉得好不好，反正我觉得好，我就这么选了。

计算机要如何才能评估价值呢？

- 其他人的选择。不好意思，计算机只有自己，没有其他人。也许你会说多台计算机。如果是共用一个“大脑”做分布式计算，那还是只有自己。
- 基于理性分析。不好意思，计算机在面对问题时往往什么都不知道，比如基于屏幕玩Atari游戏，计算机压根不知道看到的像素是个什么东西。它没有人类的先验知识，无法分析。（当然啦，先使用监督学习然后再增强学习的AlphaGo就是有先验知识的情况下做的增强学习）
- 基于感性的逻辑。不好意思，计算机目前还产生不了感性。

那么，基于自己的反复试验呢？耶，这个可以啊。计算机这方面比人类强多了，可以24小时不分昼夜的反复试验，然后对价值做出正确的判断。

所以，Value Function从分析上是可以评估出来的，那具体该怎么评估呢？

我们下面将不得不引入点数学公式，虽然也会非常好理解。

还记得**回报Result**的基本定义吗？就是所有Reward的累加（带衰减系数discount factor）

![[公式]](https://www.zhihu.com/equation?tex=G_t+%3D+R_%7Bt%2B1%7D+%2B+%5Clambda+R_%7Bt%2B2%7D+%2B+...+%3D+%5Csum_%7Bk%3D0%7D%5E%5Cinfty%5Clambda%5EkR_%7Bt%2Bk%2B1%7D)

那么Value Function该如何定义？也很简单，就是期望的回报啊！期望的回报越高，价值显然也就越大，也就越值得去选择。用数学来定义就是如下：

![[公式]](https://www.zhihu.com/equation?tex=v%28s%29+%3D+%5Cmathbb+E%5BG_t%7CS_t+%3D+s%5D)
![[公式]](https://www.zhihu.com/equation?tex=%5Cbegin%7Balign%7D%0A+v%28s%29+%26+%3D+%5Cmathbb+E%5BG_t%7CS_t+%3D+s%5D+%5C%5C%5C%5C%0A++++++%26+%3D+%5Cmathbb+E%5BR_%7Bt%2B1%7D%2B%5Clambda+R_%7Bt%2B2%7D+%2B+%5Clambda+%5E2R_%7Bt%2B3%7D+%2B+...%7CS_t+%3D+s%5D+%5C%5C%5C%5C+%0A++++++%26+%3D+%5Cmathbb+E%5BR_%7Bt%2B1%7D%2B%5Clambda+%28R_%7Bt%2B2%7D+%2B+%5Clambda+R_%7Bt%2B3%7D+%2B+...%29%7CS_t+%3D+s%5D+%5C%5C%5C%5C%0A++++++%26+%3D+%5Cmathbb+E%5BR_%7Bt%2B1%7D+%2B+%5Clambda+G_%7Bt%2B1%7D%7CS_t+%3D+s%5D+%5C%5C%5C%5C+%0A++++++%26+%3D+%5Cmathbb+E%5BR_%7Bt%2B1%7D+%2B+%5Clambda+v%28S_%7Bt%2B1%7D%29%7CS_t+%3D+s%5D%0A%5Cend%7Balign%7D)

因此，

![[公式]](https://www.zhihu.com/equation?tex=v%28s%29+%3D+%5Cmathbb+E%5BR_%7Bt%2B1%7D+%2B+%5Clambda+v%28S_%7Bt%2B1%7D%29%7CS_t+%3D+s%5D)

> 它表明Value Function是可以通过迭代来进行计算的!!!

# 动态规划与Q-Learning

## 上文回顾

在上一篇文章[DQN从入门到放弃 第三篇](https://zhuanlan.zhihu.com/p/21340755?refer=intelligentunit)中，我们分析到了Bellman方程，其方程

![[公式]](https://www.zhihu.com/equation?tex=v%28s%29+%3D+%5Cmathbb+E%5BR_%7Bt%2B1%7D+%2B+%5Clambda+v%28S_%7Bt%2B1%7D%29%7CS_t+%3D+s%5D)

## Action-Value function 动作价值函数

前面我们引出了价值函数，考虑到每个状态之后都有多种动作可以选择，每个动作之下的状态又多不一样，我们更关心在某个状态下的不同动作的价值。显然。如果知道了每个动作的价值，那么就可以选择价值最大的一个动作去执行了。这就是Action-Value function![[公式]](https://www.zhihu.com/equation?tex=Q%5E%5Cpi%28s%2Ca%29)。那么同样的道理，也是使用reward来表示，只是这里的reward和之前的reward不一样，这里是执行完动作action之后得到的reward，之前state对应的reward则是多种动作对应的reward的期望值。显然，动作之后的reward更容易理解。

那么，有了上面的定义，动作价值函数就为如下表示：

![[公式]](https://www.zhihu.com/equation?tex=%5Cbegin%7Balign%7D%0AQ%5E%5Cpi%28s%2Ca%29+%26+%3D++%5Cmathbb+E%5Br_%7Bt%2B1%7D+%2B+%5Clambda+r_%7Bt%2B2%7D+%2B+%5Clambda%5E2r_%7Bt%2B3%7D+%2B+...+%7Cs%2Ca%5D+%5C%5C%5C%5C%0A%26+%3D+%5Cmathbb+E_%7Bs%5E%5Cprime%7D%5Br%2B%5Clambda+Q%5E%5Cpi%28s%5E%5Cprime%2Ca%5E%5Cprime%29%7Cs%2Ca%5D%0A%5Cend%7Balign%7D)

那么事实上我们会更多的使用动作价值函数而不是价值函数，因为动作价值函数更直观，更方便应用于算法当中。

## Optimal value function 最优价值函数 

能计算动作价值函数是不够的，因为我们需要的是最优策略，现在求解最优策略等价于求解最优的value function，找到了最优的value function，自然而然策略也就是找到。（当然，这只是求解最优策略的一种方法，也就是value-based approach，由于DQN就是value-based，因此这里只讲这部分，以后我们会看到还有policy-based和model-based方法。一个就是直接计算策略函数，一个是估计模型，也就是计算出状态转移函数，从而整个MDP过程得解）

这里以动作价值函数来分析。

首先是最优动作价值函数和一般的动作价值函数的关系：

![[公式]](https://www.zhihu.com/equation?tex=Q%5E%2A%28s%2Ca%29+%3D+%5Cmax_%5Cpi+Q%5E%5Cpi%28s%2Ca%29)

那么套用上一节得到的value function，可以得到

![[公式]](https://www.zhihu.com/equation?tex=Q%5E%2A%28s%2Ca%29+%3D+%5Cmathbb+E_%7Bs%5E%5Cprime%7D%5Br%2B%5Clambda+%5Cmax+_%7Ba%5E%5Cprime%7DQ%5E%2A%28s%5E%5Cprime%2Ca%5E%5Cprime%29%7Cs%2Ca%5D%0A)

下面介绍基于Bellman方程的两个最基本的算法，策略迭代和值迭代。

## 策略迭代Policy Iteration

Policy Iteration的目的是通过迭代计算value function 价值函数的方式来使policy收敛到最优。

Policy Iteration本质上就是直接使用Bellman方程而得到的：

![img](https://pic2.zhimg.com/0779070465d82cc108991b0c47bd03b4_r.jpg)

那么Policy Iteration一般分成两步：

1. Policy Evaluation 策略评估。目的是 更新Value Function，或者说更好的估计基于当前策略的价值
2. Policy Improvement 策略改进。 使用 greedy policy 产生新的样本用于第一步的策略评估。



![img](https://pic1.zhimg.com/aeb96f036bea2860a8e1cbde2eef4c04_r.jpg)

本质上就是使用当前策略产生新的样本，然后使用新的样本更好的估计策略的价值，然后利用策略的价值更新策略，然后不断反复。理论可以证明最终策略将收敛到最优。





![img](https://pic1.zhimg.com/bbe1f4c8a17a236ebefad6ef4a545448_r.jpg)

那么这里要注意的是policy evaluation部分。这里的迭代很重要的一点是需要知道state状态转移概率p。也就是说依赖于model模型。而且按照算法要反复迭代直到收敛为止。所以一般需要做限制。比如到某一个比率或者次数就停止迭代。那么需要特别说明的是不管是策略迭代还是值迭代都是在理想化的情况下（上帝视角）推导出来的算法，本质上并不能直接应用，因为依赖Model。

## Value Iteration 价值迭代

Value Iteration则是使用Bellman 最优方程得到



![img](https://pic2.zhimg.com/b5ab5f7f40e44cd730bfec4ca88ab3a2_r.jpg)

然后改变成迭代形式



![img](https://pic2.zhimg.com/f0ebe85027cd67e39048b51ec4943f1d_r.jpg)

value iteration的算法如下：

![img](https://pic4.zhimg.com/7c066b17d9955f036757c9e28f4076d7_r.jpg)

那么问题来了：

- Policy Iteration和Value Iteration有什么本质区别？
- 为什么一个叫policy iteration，一个叫value iteration呢？

原因其实很好理解，policy iteration使用bellman方程来更新value，最后收敛的value 即![[公式]](https://www.zhihu.com/equation?tex=v_%5Cpi)是当前policy下的value值（所以叫做对policy进行评估），目的是为了后面的policy improvement得到新的policy。

而value iteration是使用bellman 最优方程来更新value，最后收敛得到的value即![[公式]](https://www.zhihu.com/equation?tex=v_%2A)就是当前state状态下的最优的value值。因此，只要最后收敛，那么最优的policy也就得到的。因此这个方法是基于更新value的，所以叫value iteration。

从上面的分析看，value iteration较之policy iteration更直接。不过问题也都是一样，需要知道状态转移函数p才能计算。本质上依赖于模型，而且理想条件下需要遍历所有的状态，这在稍微复杂一点的问题上就基本不可能了。

那么上面引用的是价值函数的版本，那么如果是使用动作价值函数呢，公式基本是一样的：

![[公式]](https://www.zhihu.com/equation?tex=Q_%7Bi%2B1%7D%28s%2Ca%29+%3D+%5Cmathbb+E_%7Bs%5E%5Cprime%7D%5Br%2B%5Clambda+%5Cmax_%7Ba%5E%5Cprime%7DQ_i%28s%5E%5Cprime%2Ca%5E%5Cprime%29%7Cs%2Ca%5D)

我们还在计算当前的Q值，怎么能有下一个Q值呢？没有错。所以，我们只能用之前的Q值。也就是没次根据新得到的reward和原来的Q值来更新现在的Q值。理论上可以证明这样的value iteration能够使Q值收敛到最优的action-value function。

## Q-Learning

Q Learning的思想完全根据value iteration得到。但要明确一点是value iteration每次都对所有的Q值更新一遍，也就是所有的状态和动作。但事实上在实际情况下我们没办法遍历所有的状态，还有所有的动作，我们只能得到有限的系列样本。因此，只能使用有限的样本进行操作。那么，怎么处理？Q Learning提出了一种更新Q值的办法：

![[公式]](https://www.zhihu.com/equation?tex=Q%28S_%7Bt%7D%2CA_%7Bt%7D%29+%5Cleftarrow+Q%28S_%7Bt%7D%2CA_%7Bt%7D%29%2B%5Calpha%28%7BR_%7Bt%2B1%7D%2B%5Clambda+%5Cmax+_aQ%28S_%7Bt%2B1%7D%2Ca%29%7D+-+Q%28S_t%2CA_t%29%29)

具体的算法如下：

![img](https://pic3.zhimg.com/96ee91330ffbcc21b0ac399f41372e45_r.jpg)

### 7 Exploration and Exploitation 探索与利用

在上面的算法中，我们可以看到需要使用某一个policy来生成动作，也就是说这个policy不是优化的那个policy，所以Q-Learning算法叫做Off-policy的算法。另一方面，因为Q-Learning完全不考虑model模型也就是环境的具体情况，只考虑看到的环境及reward，因此是model-free的方法。

回到policy的问题，那么要选择怎样的policy来生成action呢？有两种做法：

\- 随机的生成一个动作

\- 根据当前的Q值计算出一个最优的动作，这个policy![[公式]](https://www.zhihu.com/equation?tex=%5Cpi)称之为greedy policy贪婪策略。也就是

![[公式]](https://www.zhihu.com/equation?tex=%5Cpi%28S_%7Bt%2B1%7D%29+%3D+arg%5Cmax+_aQ%28S_%7Bt%2B1%7D%2Ca%29%0A)

将两者结合起来就是所谓的![[公式]](https://www.zhihu.com/equation?tex=%5Cepsilon-greedy)策略，![[公式]](https://www.zhihu.com/equation?tex=%5Cepsilon)一般是一个很小的值，作为选取随机动作的概率值。可以更改![[公式]](https://www.zhihu.com/equation?tex=%5Cepsilon)的值从而得到不同的exploration和exploitation的比例。

这里需要说明的一点是使用![[公式]](https://www.zhihu.com/equation?tex=%5Cepsilon-greedy)策略是一种极其简单粗暴的方法，对于一些复杂的任务采用这种方法来探索未知空间是不可取的。因此，最近有越来越多的方法来改进这种探索机制。

# 深度解读DQN算法

## 详解Q-Learning

在上一篇文章中，我们分析了动态规划Dynamic Programming并且由此引出了Q-Learning算法。可能一些知友不是特别理解。那么这里我们再用简单的语言描述一下整个思路是什么。

为了得到最优策略Policy，我们考虑估算每一个状态下每一种选择的价值Value有多大。然后我们通过分析发现，每一个时间片的Q(s,a)和当前得到的Reward以及下一个时间片的Q(s,a)有关。有些知友想不通，在一个实验里，我们只可能知道当前的Q值，怎么知道下一个时刻的Q值呢？大家要记住这一点，Q-Learning建立在虚拟环境下无限次的实验。这意味着可以把上一次实验计算得到的Q值拿来使用呀。这样，不就可以根据当前的Reward及上一次实验中下一个时间片的Q值更新当前的Q值了吗？说起来真是很拗口。下面用比较形象的方法再具体分析一下Q-Learning。

Q-Learning的算法如下：

![img](https://pic1.zhimg.com/e6905f69595ed51e9a406a47603d49ef_r.jpg)

对于Q-Learning，首先就是要确定如何存储Q值，最简单的想法就是用矩阵，一个s一个a对应一个Q值，所以可以把Q值想象为一个很大的表格，横列代表s，纵列代表a，里面的数字代表Q值，如下表示：



![img](https://pic3.zhimg.com/153a0529f2b86c87d5827999f13a1709_r.jpg)

这样大家就很清楚Q值是怎样的 了。接下来就是看如何反复实验更新。



Step 1：初始化Q矩阵，比如都设置为0

Step 2：开始实验。根据当前Q矩阵及![[公式]](https://www.zhihu.com/equation?tex=%5Cepsilon-greedy)方法获取动作。比如当前处在状态s1，那么在s1一列每一个Q值都是0，那么这个时候随便选择都可以。



![img](https://pic3.zhimg.com/a2d3e2dd1d0dba1e3f3fb136e1c53e00_r.jpg)

假设我们选择a2动作，然后得到的reward是1，并且进入到s3状态，接下来我们要根据



![[公式]](https://www.zhihu.com/equation?tex=Q%28S_%7Bt%7D%2CA_%7Bt%7D%29+%5Cleftarrow+Q%28S_%7Bt%7D%2CA_%7Bt%7D%29%2B%5Calpha%28%7BR_%7Bt%2B1%7D%2B%5Clambda+%5Cmax+_aQ%28S_%7Bt%2B1%7D%2Ca%29%7D+-+Q%28S_t%2CA_t%29%29)
![[公式]](https://www.zhihu.com/equation?tex=Q%28S_t%2CA_t%29+%3D+R_%7Bt%2B1%7D+%2B+%5Cmax_a+Q%28S_%7Bt%2B1%7D%2Ca%29)

所以在这里，就是

![[公式]](https://www.zhihu.com/equation?tex=Q%28s_1%2Ca_2%29+%3D+1+%2B++%5Cmax_a+Q%28s_3%2Ca%29)



![img](https://pic3.zhimg.com/b5f21544706393b859b7380595356f9c_r.jpg)

Step 3：接下来就是进入下一次动作，这次的状态是s3，假设选择动作a3，然后得到1的reward，状态变成s1，那么我们同样进行更新：



![[公式]](https://www.zhihu.com/equation?tex=Q%28s_3%2Ca_3%29+%3D+2+%2B+%5Cmax_a+Q%28s_1%2Ca%29+%3D+2+%2B+1+%3D+3)

![img](https://pic2.zhimg.com/ebf8d1cff2b1365a91c3367c48a4c4c4_r.jpg)

Step 4： 反复上面的方法。

就是这样，Q值在试验的同时反复更新。直到收敛。

相信这次知友们可以很清楚Q-Learning的方法了。接下来，我们将Q-Learning拓展至DQN。

## 维度灾难

在上面的简单分析中，我们使用表格来表示Q(s,a)，但是这个在现实的很多问题上是几乎不可行的，因为状态实在是太多。使用表格的方式根本存不下。

举Atari为例子。



![img](https://picb.zhimg.com/80/e7df063e461eef1079943eceda1c3e2d_1440w.png)

计算机玩Atari游戏的要求是输入原始图像数据，也就是210x160像素的图片，然后输出几个按键动作。总之就是和人类的要求一样，纯视觉输入，然后让计算机自己玩游戏。那么这种情况下，到底有多少种状态呢？有可能每一秒钟的状态都不一样。因为，从理论上看，如果每一个像素都有256种选择，那么就有：



![[公式]](https://www.zhihu.com/equation?tex=256%5E%7B210%5Ctimes+160%7D)

## 价值函数近似Value Function Approximation

什么是价值函数近似呢？说起来很简单，就是用一个函数来表示Q(s,a)。即

![[公式]](https://www.zhihu.com/equation?tex=Q%28s%2Ca%29+%3D+f%28s%2Ca%29)

![[公式]](https://www.zhihu.com/equation?tex=Q%28s%2Ca%29+%3D+w_1s+%2B+w_2a+%2B+b) 其中![[公式]](https://www.zhihu.com/equation?tex=w_1%2Cw_2%2Cb)是函数f的参数。

大家看到了没有，通过函数表示，我们就可以无所谓s到底是多大的维度，反正最后都通过矩阵运算降维输出为单值的Q。

这就是价值函数近似的基本思路。

如果我们就用![[公式]](https://www.zhihu.com/equation?tex=w)来统一表示函数f的参数，那么就有

![[公式]](https://www.zhihu.com/equation?tex=Q%28s%2Ca%29+%3D+f%28s%2Ca%2Cw%29)
![[公式]](https://www.zhihu.com/equation?tex=Q%28s%2Ca%29%5Capprox+f%28s%2Ca%2Cw%29)

## 高维状态输入，低维动作输出的表示问题

对于Atari游戏而言，这是一个高维状态输入（原始图像），低维动作输出（只有几个离散的动作，比如上下左右）。那么怎么来表示这个函数f呢？

难道把高维s和低维a加在一起作为输入吗？

必须承认这样也是可以的。但总感觉有点别扭。特别是，其实我们只需要对高维状态进行降维，而不需要对动作也进行降维处理。

那么，有什么更好的表示方法吗？

当然有，怎么做呢？

其实就是![[公式]](https://www.zhihu.com/equation?tex=Q%28s%29+%5Capprox+f%28s%2Cw%29)，只把状态s作为输入，但是输出的时候输出每一个动作的Q值，也就是输出一个向量![[公式]](https://www.zhihu.com/equation?tex=%5BQ%28s%2Ca_1%29%2CQ%28s%2Ca_2%29%2CQ%28s%2Ca_3%29%2C...%2CQ%28s%2Ca_n%29%5D)，记住这里输出是一个值，只不过是包含了所有动作的Q值的向量而已。这样我们就只要输入状态s，而且还同时可以得到所有的动作Q值，也将更方便的进行Q-Learning中动作的选择与Q值更新（这一点后面大家会理解）。

## Q值神经网络化！

终于到了和深度学习相结合的一步了！

意思很清楚，就是我们用一个深度神经网络来表示这个函数f。

这里假设大家对深度学习特别是卷积神经网络已经有基本的理解。如果不是很清楚，欢迎阅读本专栏的CS231n翻译系列文章。



![img](https://picb.zhimg.com/82aa8bad9d6669907ba229a7c5490c58_r.jpg)

以DQN为例，输入是经过处理的4个连续的84x84图像，然后经过两个卷积层，两个全连接层，最后输出包含每一个动作Q值的向量。



对于这个网络的结构，针对不同的问题可以有不同的设置。如果大家熟悉Tensorflow，那么肯定知道创建一个网络是多么简单的一件事。这里我们就不具体介绍了。我们将在之后的DQN tensorflow实战篇进行讲解。

总之，用神经网络来表示Q值非常简单，Q值也就是变成用Q网络（Q-Network）来表示。接下来就到了很多人都会困惑的问题，那就是

> **怎么训练Q网络？？？
> **

## DQN算法

我们知道，神经网络的训练是一个最优化问题，最优化一个损失函数loss function，也就是标签和网络输出的偏差，目标是让损失函数最小化。为此，我们需要有样本，巨量的有标签数据，然后通过反向传播使用梯度下降的方法来更新神经网络的参数。

所以，要训练Q网络，我们要能够为Q网络提供有标签的样本。

所以，问题变成：

> **如何为Q网络提供有标签的样本？
> **

答案就是利用Q-Learning算法。

大家回想一下Q-Learning算法，Q值的更新依靠什么？依靠的是利用Reward和Q计算出来的目标Q值：

![[公式]](https://www.zhihu.com/equation?tex=R_%7Bt%2B1%7D%2B%5Clambda+%5Cmax+_aQ%28S_%7Bt%2B1%7D%2Ca%29)

因此，Q网络训练的损失函数就是



![img](https://pic4.zhimg.com/3858f07818d129668fc83d48d855bb1f_r.jpg)

上面公式是![[公式]](https://www.zhihu.com/equation?tex=s%5E%60%2Ca%5E%60)



既然确定了损失函数，也就是cost，确定了获取样本的方式。那么DQN的整个算法也就成型了！

接下来就是具体如何训练的问题了！

## DQN训练

我们这里分析第一个版本的DQN，也就是NIPS 2013提出的DQN。



![img](https://pic2.zhimg.com/c24454f472843ef5caef2733d50aba00_r.jpg)

我们分析了这么久终于到现在放上了DQN算法，真是不容易。如果没有一定基础直接上算法还真是搞不明白。



具体的算法主要涉及到Experience Replay，也就是经验池的技巧，就是如何存储样本及采样问题。

由于玩Atari采集的样本是一个时间序列，样本之间具有连续性，如果每次得到样本就更新Q值，受样本分布影响，效果会不好。因此，一个很直接的想法就是把样本先存起来，然后随机采样如何？这就是Experience Replay的意思。按照脑科学的观点，人的大脑也具有这样的机制，就是在回忆中学习。

那么上面的算法看起来那么长，其实就是反复试验，然后存储数据。接下来数据存到一定程度，就每次随机采用数据，进行梯度下降！

也就是

> 在DQN中增强学习Q-Learning算法和深度学习的SGD训练是同步进行的！

通过Q-Learning获取无限量的训练样本，然后对神经网络进行训练。

样本的获取关键是计算y，也就是标签。