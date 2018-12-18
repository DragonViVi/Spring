## **项目说明**


---

### src->main->java->constomDecider

##study1
- 初始学习SpringBatch 
- FlowDemo 聚合多个Step
- SpiltDemo 多个Flow执行
 
## study2
-  决策器 ：实现JobExecutionDecider

## study3
- 学习监听器 StepExecutionListener
-  JobParameter

## study4
-  ItemReader 数据获取
在Step中处理可以使用Tasklet创建简单的步骤 也可以使用chunk+itemReader+itemWriter创建复杂的步骤

##study5
-Listener

##study6
-ItemReader
ItemReader 可以理解为：在批处理中，需要处理的数据。
这些数据通常是在 文本文件， xml 文件， 数据库 中存储
。在进行批处理的时候，需要从文件中获取数据。可以说，ItemReader 是整个批处理的入口。
> * ListItemReader：从集合中获取数据
> * FlatFileItemReader：从文本文件中获取数据
> * MultiResourceItemReader：从多个文件中获取数据
> * StaxEventItemReader：从 xml 文件中获取数据
> * AbstractPagingItemReader：从数据库中获取数据
 > > * JdbcPagingItemReader：使用基础的 jdbc 获取数据
 > > * HibernatePagingItemReader：使用 Hibernate 获取数据
 > > * JpaPagingItemReader：使用 JPA 获取数据


##study7
 -ItemWriter
 
 
##study9
> * 错误中断：重启后继续执行,在每次chunk后在ExecutionContext中打入标记
    ,在重启执行该任务的时候,半段ExecutionContext中是否存在标记，如果存在，则从标记位
    重新读取执行。
> * 错误重试： 在出现错误的时候，根据置顶的需要重试的异常，进行重新读写处理
> * 错误跳过： 在出现错误的时候，根据指定的需要跳过的异常，跳过该条数据，需要指定跳过的异常，跳过次数。



 
 

    

