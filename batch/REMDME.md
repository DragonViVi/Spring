## **项目说明**


---

### src->main->java->constomDecider

##study1
- 初始学习SpringBatch 
 
## study2
-  学习使用决策者

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




    

