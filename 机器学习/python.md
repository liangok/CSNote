#### yield
迭代器，可返回数据，通过next可获得

```

def foo():
    print("starting...")
    while True:
        res = yield 4
        print("res:",res)
g = foo()
print(next(g))
print("*"*20)
```
输出
```

starting...
4
********************
res: None
4
```