<p>最近看了很多有关KMP算法的文章与相关书籍，觉得对KMP算法大致有了一个直观的理解，就写一篇文章记录下来，这篇文章将会在直观层面与代码层面进行一个简单的分析。</p>
<p><br></p>
<h5><font color=blue>一：暴力算法</font></h5>
<p>KMP算法是由Knuth, Morris, Pratt三人设计的线性时间字符串匹配算法，这个算法无需回溯指针，通过辅助函数next可以在Θ(m)时间内根据模式预先计算出来，并储存在数组next[1...m]中。而next数组中存储的就是转移函数的值，也就是说当前内容不匹配时应该跳到哪里。</p>
<p>在暴力匹配算法中，时间复杂度最坏是m(n - m + 1)，它的缺点是进行了很多无用的匹配。</p>
<pre class="wp-block-code"><code>index: | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 |
P:     | a | b | b | a | b | b | a | b | a | b |
S:     | a | b | b | a | b | a | b |</code></pre>
<!-- /wp:code -->
<p>在5位置发生了错配，那么有没有必要在从2位置重新开始进行依次配对呢？既然从0到4位置都已发生了匹配，那么也就知道了P串中从0到4的内容，按着这个思路从第几个开始失配，那么对应的P串的之前的内容都是已经知道的。因此也就不需要再进这些多余的匹配。因此，以上的匹配再下一步可以直接进行到这一步</p>
<!-- wp:code -->
<pre class="wp-block-code"><code>index: | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |10 |
P:     | a | b | b | a | b | b | a | b | a | b |
S:                 | a | b | b | a | b | a | b |</code></pre>
<!-- /wp:code -->
<!-- wp:paragraph -->
<p>成功匹配！通过这种算法只进行了两次匹配。</p>
<h5><font color=blue>二：KMP算法</font></h5>
<p>为了便于理解，这里会有一些约定：</p>
<ul>
<li>i 代表主串中目前匹配位置</li>
<li>j 代表一匹配的长度</li>
<li>数组都从下标1开始而不是从0开始。</li>
<li>S为主串，T为模式串</li>
<li>代码采用伪代码格式书写</li>
</ul>
<p><br>
先看一下KMP大致过程</p>
<pre><code>|a|b|a|b|c|a|b|c|a|c|b|a|b|
     |       |       |  //第一步
     i = 3   |       |
 a b c       |       |
     j = 3   |       |
=============|=======|====  //第二步
     i-------i = 7   |
     a b c a c       |
             j = 5   |
=====================|====  //第三步
             i-------i = 7
          (a)b c a c
                     j = 5
==========================</code></pre>
<p>上面的图看起来可能会一头雾水，但它的确会对之后的理解由帮助
<br></p>
<ul>
<li><strong>要解决的问题</strong></li>
</ul>
<p>我们使用KMP算法还需要解决两个问题</p>
<ol>
<li>当匹配过程产生失配时，模式串可向右滑多远。</li>
<li>当主串中第j个字符与模式串字符第j个字符失配时，主串中第i个字符（i不回溯）需要与模式串中那个再比较</li>
</ol>
<p><em>conclude:</em></p>
<blockquote>
<p>为了解决重复比对的问题，同时也为了使指针不回溯，就可以设置一个next数组
<br></p>
</blockquote>
<ul>
<li><strong>解决方法</strong></li>
</ul>
<ol>
<li>匹配失败时，i不变，j回退到next[j]上</li>
<li>当j回退到0时，i,j同时加一（主串的第i个与模式串中第一个字符不等时，应该从主串第i + 1个开始比较</li>
</ol>
<p><em>conclude:</em></p>
<blockquote>
<p>如果j = 0 或 T[j] = S[j]，则i,j 都加一，否则j退到next[j]上</p>
</blockquote>
<p><em>eg:</em></p>
<pre><code>当next[j] = 1时，则从模式串中第1个继续与i比较
当next[j] = 3时，则从模式串中第3个继续与i比较

以上的原因是因为当next[j] = 1时，可以知道主串的匹配失配位置的前一个与字串的第一个是相等的
同理当next[j] = 3时，可以直到主串失配位置的前三个与字串的前三个相同
=================================================================================
当next[j] = 0时，即i与模式串第一个就不匹配，j也不用回溯了，i直接向前走</code></pre>
<p><br></p>
<p><strong>代码</strong></p>
<pre><code>int Inext_KMP(String S, String T, int pos){
    //从第pos个位置开始匹配
    //主串是S，字串是T
    i = pos, j = 1;
    while(i <= S.length && j <= T.length){
        if(j == 0 || S[i] == T[j]){
            i++, j++;
        }
        else
            j = next[j];
    }
    if(j > T.length)
        return i - T.length;    //返回主串匹配的第字符下标
    else
        return 0;
}</code></pre>
<h5><font color=blue>三：next数组求法</font></h5>
<p>理解了主串的模式匹配，next数组也就好求了，因为方法都是类似的</p>
<p>下面
用归纳法证明：</p>
<ol>
<li>让next[1] = 0</li>
<li>若next[j] = k，则T[1…k – 1] = T[j – k + 1 … j – 1]</li>
<li>接下来求next[j + 1]
<ul>
<li>Sk = Sj: next[j + 1] = next[j] + 1</li>
<li>Sk != Sj: 把求next函数值的问题看成一个模式匹配问题，也就是说在已经匹配的子串中找到合适的位置</li>
</ul></li>
</ol>
<p><em>Tips</em></p>
<blockquote>
<p>第三步的第二个步骤有点递归的意思，当失配了时不需要把指针回溯，重新再来，可以直接利用next数组中已经求得的值，从next[j]的位置继续接着匹配就行</p>
</blockquote>
<p>因此求next数组的算法和模式匹配算法类似</p>
<pre><code>void get_next(String T, int next[]){
    i = 1, next[1] = 0, j = 0;
    while(i < T.length){
        if(j == 0 || T[i] == T[j]){
            i++, j++;
            next[i] = j;
        }
        else
            j = next[j];
    }
}</code></pre>
<h5><font color=blue>四：改进后的next数组</font></h5>
<p>虽然以上算法相比于暴力算法已经快了很多，但还是有一些缺陷比如下面这个例子</p>
<pre><code>         j:  | 1 | 2 | 3 | 4 | 5 |
         T:  | a | a | a | a | b |
tmpnext[j]:  | 0 | 1 | 2 | 3 | 4 |
next[j]   :  | 0 | 0 | 0 | 0 | 4 |</code></pre>
<p>在上面的tmpnext中，因为tmpnext[j] = tmpnext[tmpnext[j]]，所以可以直接写让它滑动的更远，这样不就可以再省一点时间嘛，也就是让tmpnext[j] = tmpnext[tmpnext[j]]。</p>
<p>因此改进算法如下</p>
<pre><code>void get_next(String T, int next[]){
    i = 1, j = 0;
    next[i] = 0;
    while(i < T.length){
        if(j == 0 || T[i] == T[j]){
            i++, j++;

            if(T[i] != T[j])
                next[i] = j;
            else
                next[j] = next[j];
        }
        else
            j = next[j];
    }
}</code></pre>
<h5><font color=blue>五：时间复杂度</font></h5>
<p>O(m+n)=O( [T.length,2 <em> T.length]+ [S.length,2 </em> S.length] ) = 计算next数组的时间复杂度+遍历比较的复杂度。</p>