### 第一章笔记
jps: 查看Java进程的pid  
jstack pid > xxx: 检查线程是否出现死锁  
grep java.lang.Thread.State xxx | awk '{print $2$3$4$5}'
| sort | uniq -c: 统计线程的信息信息 sort 排序 uniq -c 每列旁边显示该行重复出现的次数 grep 查找文件内容 awk对数据进行再次处理 print 打印 $n 第n列  
多线程不一定比单线程快: 上下文切换和资源调度需要时间