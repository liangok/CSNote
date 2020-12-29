# pyenv 使用
### 安装
```
pyenv install 2.7.8
```
### 更新
```
pyenv update
```
###  pyenv的常用命令



- pyenv version # 查看当前系统的python版本

- pyenv versions # 查看当前系统中所有存在的python

- pyenv install --list # 查看当前可供安装的python版本

- pyenv install x.x.x #

- pyenv unintall x.x.x

- pyenv local x.x.x # 告诉某个本地文件夹使用那个版本

- pyenv global x.x.x # 设置当前全局的python版本

- pyenv global system # 设置为系统的python版本

- pyenv shell x.x.x

优先级：shell > local > global

pyenv rehash # 当你安装一个新的python版本时要重新哈希一下

 

更新：

cd ~/.pyenv 或者 cd $(pyenv root)

git pull

 

#### step6

背景：pyenv通过使用virtualenv 创建虚拟环境，实现了真正的环境隔离，每一个项目都使用一个单独的环境即虚拟环境
