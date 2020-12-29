# HD_Problem 2062
## 题目：[Subset sequence](http://acm.hdu.edu.cn/showproblem.php?pid=2062)
**题解来源：[HDU 2062 Subset sequence](https://blog.csdn.net/qq_33266889/article/details/53468509)**

这道题要从字典序列中寻找规律，字典序列按顺序排规律大致是这样的：
- n = 1，subset = {{1}}
- n = 2, subset = {{1}, {1, 2}, {2}, {2, 1}}
- n = 3, subset = {{1}, {1, 2}, {1, 2, 3}, {1, 3}, {1, 3, 2}, {2}
{2,1}, {2,1,3}, {2,3}, {2,3,1}, {3}, {3,1}, {3,1,2}, {3,2}, {3,2,1}}

这里拿n = 3, m = 10来分析。它的子集按照字典序排列会有如下顺序：
```
{1}
{1,2}
{1,2,3}	
{1,3}
{1,3,2}

{2}
{2,1}
{2,1,3}
{2,3}
{2,3,1}

{3}
{3,1}
{3,1,2}
{3,2}
{3,2,1}
```
分为$3$组，每一组第一个数字一样，且去掉开头数字就是$n=2$时的情况数$+1$(空集)这样就找到了递归规律。

**设$f(n)$是$n$个数字按照字典序所产生的子集个数**
$$ f(n) = \left\{
\begin{aligned}
1& &, n = 1\\
n*( f(n-1) + 1 )& &, n > 1
\end{aligned}
\right.$$


**设$g(n)$是每一组子集的个数**

$$g(n)=f(n)/n$$
$$g(n-1)=f(n-1)/(n-1)$$
$$f(n) = n*( f(n-1) + 1 )$$
$$g(n)=(n-1)*g(n-1)+1$$

故
$$g(n) = \left\{\begin{aligned}
     1& &, n = 1\\
     (n-1)*g(n-1)+1& &, n > 1
\end{aligned}
\right.$$

故我们可以先输出开头数字，然后把问题规模缩小到$( n-1 , m-(t-1)*g(n)-1 )$，不断缩小规模直至找到答案。

怎么得到的 $m-(t-1)*g(n)-1$ ？ $t$代表所求子集所在的组，每次问题规模缩小时，m都需要减去多余的子集，多余的子集数就是上面1~t-1组所含子集数和t组去掉开头数字后剩余的空集。

$$\left\{
    \begin{aligned}
        t&= \lceil m / g(n)\rceil\\
        (n, m)& = (n - 1, m - (t - 1) * g(n) - 1)\\
    \end{aligned}
\right.$$

**步骤：**
1. 求出所在的组$t$，输出所在组$t$的首元素$s[t]$
1. 将该子集的下一个元素到最后一个的值$+1$
2. 缩减问题规模继续执行步骤1~2，直到$m <= 0$

```cpp
#include<stdio.h>

int s[21];
long long g[21] = {0};

void set_table(){
    for (int i=1;i<21;i++)
        g[i]=g[i-1]*(i-1)+1;
}

int main(){
    int n, t;
    long long m;
    set_table();
    while(scanf("%d %lld", &n, &m) != EOF){
        for(int i = 0; i < 21; i++){
            s[i] = i;
        }
        while(m > 0){
            t = m/g[n]+(m % g[n]>0?1:0);
            if(t > 0){
                printf("%d", s[t]);
                for(int i = t; i <= n; i++){
                    s[i] = s[i + 1];
                }
                m = m - (t - 1) * g[n] - 1;
                putchar(m==0?'\n':' ');
            }
            n--;
        }
    }
}

```

# HD_Problem 1087
## 题目：[Super Jumping! Jumping! Jumping!](http://acm.hdu.edu.cn/showproblem.php?pid=1087)

这道题仔细分析一下其实是求和最大的递增子序列。应该用动态规划的方法。
$$DP_i=\left\{\begin{aligned}
    &\max^{i-1}_{j=1}(DP_j + V_i)& &,&&DP_j+V_i>DP_i&& \And &&V_i>V_j\\
    &DP_i& &,&&Others
\end{aligned}
\right.$$

```cpp
#include<stdio.h>
#include <string.h>

const static int MAX_LENGTH = 1002;

int main(){
    int v[MAX_LENGTH];
    int dp[MAX_LENGTH];
    int length;
    int max_sum;
    while(~scanf("%d",&length)&&length){
        memset(v, 0, MAX_LENGTH);
        memset(dp, 0, MAX_LENGTH);
        max_sum = -0x3f3f3f3f;
        for (int i = 1; i <= length; ++i) {
            scanf("%d", &v[i]);
        }

        for (int i = 1; i < length; ++i) {
            dp[i] = v[i];
            for(int j = 1; j < i; j++){
                if(v[i] > v[j] && dp[j] + v[i] > dp[i]){
                    dp[i] = dp[j] + v[i];
                }
            }
            max_sum = max_sum > dp[i] ? max_sum : dp[i];
        }
        printf("%d\n", max_sum);
    }

}

```