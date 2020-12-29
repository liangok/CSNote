### IO流

Reader->FileReaderStream->FileReader
1. 字符流是什么
    - 字符流是可以直接读写字符的IO流
    - 字符流读取字符，先读取到字节数据，再转化为字符，如要输出字符，要把字符转为字节再写出(只读或只写)
2. FileReader
        Demo1_FileReader

### IO流（字符流FileWriter）
Writer->FileWriterStream->FileWriter
    
FileWriter类的writer()方法可以自动把字符转为字节写出
    
### IO流（什么情况下使用字符流）
- 字符流也可以拷贝文本文件，但不推荐使用，因为读取时会把字节转化为
字符，写出时还要把字符转化为字节


    FileReader-->Java程序接受字符-->FileWriter

    xxx.txt------>Java程序---------->zzz.txt
- 程序需要读取一段文本时，或者需要写出一段文本时可以使用字符流

- 而字节流


    FilInputStream-->Java程序接受字节-->FilOutputStream

    xxx.txt------->Java程序---------->zzz.txt
    
### IO流（自定义字符数组的拷贝）

- BufferedReader的read()方法读取字符时会一次读取若干字符到缓冲区，然后逐个返回给
程序，降低读取文件的次数，提高效率
- BufferedWriter的write()方法去除字符时会先写到缓冲区，缓冲区写满时才会写道文件
，降低写文件的次数，提高效率

### IO流(readLine()和newLine())

- BufferedReader的readLine()方法可以读取一行字符
不包括换行号(遇到\r或\n停止读，并返回null)
- BufferedWriter的newLine()方法可以输出一个跨平台的
换行号"\r\n"

### IO流(LineNumberReader)

- LinNumberReader是BufferedReader的子类，具有相同的功能，并可以统计行号
    - 调用getLineNumber()方法获得当前行号
    - 调用detLineNumber()方法设置当前行号
    
### IO流(装饰设计模式)

- 把房子装修，化妆，原来功能不够强大，装饰后让功能更强大
- 对一个对象进行包装
  - BufferedReader(Reader r); //对Reader进行包装
  - HarvardStudent(Student s);  //对学生进行包装

1. 获取被装饰类引用
2. 在构造方法传入被装饰类的对象
3. 对原有的功能进行升级

- 不用继承关系，所以可以降低耦合性，降低Student和HarvardStudent的耦合性


### IO流(使用指定码表读取字符)
- 如果要用指定的码表读，可以用InputStreamReader(字节流，编码表)
    - 字节通向字符的桥梁
    - Reader的子类
- 如果要用指定的码表写出，可以用OutPutStreamWriter(字节流，编码表)
    - 字符流通向字节流的桥梁
    - Writer的子类

### IO流(转化流)


文件->FileInputStream(字节流)->InputStreamReader(字节流,码表)字节通向字符的桥梁->
BufferedReader->Java程序->BufferedWriter->OutputStreamWriter->FileOutputStream->文件
