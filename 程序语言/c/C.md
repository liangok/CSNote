# C陷阱与缺陷
## EX1
看下面的例子
```cpp
#include<stdio.h>
int main(){
    int nums = 0;
    int phone[10];
    scanf("%d", &nums);
    for(int i = 0; i < nums; i++){
        for(int c = 0; c < 11; c++){
            scanf("%d", &phone[c]);
        }
        printf("6");
        for(int j = 5; j < 11; j++){
            printf("%d", phone[j]);
        }
        printf("\n");
    }
}
```
程序不知道为什么，就是莫名其妙的无法运行。初步判断错误发生在下面这一段中。
```cpp
for(int c = 0; c < 11; c++){
    scanf("%d", &phone[c]);
}
```
