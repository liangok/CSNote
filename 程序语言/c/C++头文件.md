## 头文件不可相互引用或重复引用

## 头文件里不可以定义变量



```c++
#ifdef
#endif
```

### 防止头文件被重复引用

```cpp
#ifndef HeaderName_h
#define HeaderName_h
```

