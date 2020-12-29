# Comparable使用方法
```
class Radius implements Comparable<Radius> {
    double x;
    double y;
    double r;

    public Radius(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setR(double r) {
        this.r = r;
    }
    @Override
    public int compareTo(Radius o) {
        return (int) (this.r - o.r);
    }

    //@Override
    //public int compareTo(Radius o) {
    //    return compare(this.r, o.r);
    //}
    //public static int compare(double r1, double r2) {
    //    return (r1 > r1 ? 1 :
    //            (r1 == r1 ? 0 : -1));
    //}
}
```
# Comparator 使用方法
```
class Person{
    int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(int age) {
        this.age = age;
    }
}

class PersonComparator implements Comparator<Person>{
    @Override
    public int compare(Person o1, Person o2) {
        return o1.age - o2.age;
    }
}
```

# 区别
Comparable 是在集合内部定义的方法实现的排序，Comparator 是在集合外部实现的排序，所以，如想实现排序，就需要在集合外定义 Comparator 接口的方法或在集合内实现 Comparable 接口的方法。
- Comparable：内比较器
- Comparator：外比较器
