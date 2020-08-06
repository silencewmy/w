[TOC]

## 日志功能如何合并

所有的日志相关功能逻辑都在zhgj/ops/src/main/java/com/jingtong/zhgj/ops/log下，只需要将log目录拷贝到可运行的项目中即可。同时要拷贝LogMapper.xml文件



## 日志内容提交说明

2020-8-06提交：提交到dev中

- 提交说明：所有的日志相关功能都在`zhgj/ops/src/main/java/com/jingtong/zhgj/ops/log`下
- 提交完成情况：将日志信息封装完成，日志的写入和查询的dao层测试完成。测试的例子位于`StationServiceImpl.updateStation`中
- 日志模块需要的表设计在`zhgj/ops/src/main/java/com/jingtong/zhgj/ops/log/sql`下。
- 未完成：将测试时写的sout代码删除；日志的查询（也就是根据前端条件查询并返回）

## 使用介绍+要求！！！！

### 参数要求（必需的参数）

*  argActorModel 传入的用户参数，为一个封装对象。
  * 构造方法为`public LogArgActorModel( String remark, TokenService tokenService )`
  * 第一个参数为日志备注，第二个为TokenService；如`new LogArgActorModel("这是日志的备注",tokenService);`
*  Operand 操作对象类型，参数为枚举类型，用来告知操作的对象所属类型
*  LogTypeEnum 日志类型，枚举类型
*  SubLogTypeEnum 操作行为的子类型，枚举类型
*  LogActEnum 操作对象类型系统划分，枚举类型
*  oldObject 被操作的之前的对象
*  newObject 操作之后的对象
*  方法的请求参数如下

```java
public void logObject (
                LogArgActorModel argActorModel,
                Operand operand, 
                LogTypeEnum logTypeEnum, 
                SubLogTypeEnum subLogTypeEnum,
                LogActEnum logActEnum, 
                Object oldObject, 
                Object newObject 
)
```

#### 参数说明

上述4个枚举类型根据实际操作的逻辑进行选择即可。

**新旧对象必须是相同的类型**。此外，进行属性复制的时候相同含义但属性名不同的字段是不能赋值的，需要显式的设置如下使用例子：



# 使用示例

以下是在`StationServiceImpl.updateStation`中进行日志的记录

## 进行修改的地方

```java
    //wmy添加
	//测试使用，也可以使用注解注入   
    @Autowired
    private TokenService tokenService;
    @Autowired
    private LogClient logClient;
 	
 public Ret<String> updateStation(Long tenantId, StationUpdateVo stationUpdateVo) {	
 	
 	 //修改之前的数据
        StationEntity byId = stationDao.getById(stationUpdateVo.getId(), tenantId);
     
      /**
         * 测试日志使用的，待删除
         */
        {
            StationEntity newObject = new StationEntity();
		//进行属性复制
        BeanUtils.copyProperties( stationUpdateVo, newObject );
     	//含义相同但属性名不同，因此需要显式赋值
        newObject.setStationName( stationUpdateVo.getName() );

            LogArgActorModel logArgActorModel = new LogArgActorModel("08-04测试",tokenService );

            logClient.logObject( logArgActorModel, Operand.BUS, LogTypeEnum.WEB, SubLogTypeEnum.CHANGEDRIVERPLAN, LogActEnum.MODIFY, byId, newObject );
        }
     
     
     
```



## 进行删除的地方，在上述地方进行修改得到的，仅仅展示用法

```java
  
   	 //要删除的数据
        StationEntity byId = stationDao.getById(stationUpdateVo.getId(), tenantId);
  
  /**
     * 测试日志使用的，待删除
     */
    {
    	//删除意味着修改之后的对象为空，因此不赋值
        StationEntity newObject = new StationEntity();

        LogArgActorModel logArgActorModel = new LogArgActorModel("08-04测试",tokenService );

        logClient.logObject( logArgActorModel, Operand.BUS, LogTypeEnum.WEB, SubLogTypeEnum.CHANGEDRIVERPLAN, LogActEnum.MODIFY, byId, newObject );
    }
```
## 进行增加的地方，在上述地方进行修改得到的，仅仅展示用法

```java
  
   //要新增的数据，在相对性上 。这里代表的是新的值
        StationEntity byId = stationDao.getById(stationUpdateVo.getId(), tenantId);
  /**
     * 测试日志使用的，待删除
     */
    {
        //因为在增加之前，数据库中是空的 。因此创建对象但不赋值
        StationEntity oldObject = new StationEntity();

        LogArgActorModel logArgActorModel = new LogArgActorModel("08-04测试",tokenService );

        logClient.logObject( logArgActorModel, Operand.BUS, LogTypeEnum.WEB, SubLogTypeEnum.CHANGEDRIVERPLAN, LogActEnum.MODIFY, oldObject, byId );
```




## 必要的注解-在操作的实体上增加(比如上述的StationEntity中增加注解)

```java
//wmy修改
@LogTag( attrType = AttributeName.ID )
Long id;

/**
 * 站点名称
 */
//wmy修改
@LogTag(attrType = AttributeName.PLANNAME)
String stationName;

/**
 * 站点编号
 */
//wmy修改
@LogTag(attrType = AttributeName.PLANID)
Long indexCode;

/**
     * 更新时间
     */
    //wmy修改
    @LogTag( attrType = AttributeName.IGNORE )
    LocalDateTime updateTime;
```

### 注解的含义-注解应该使用的地方

使用地点：被操作的实体类中（也就是新旧对象所属的类中的字段上面）

考虑到每个实体中的属性名不一致因此通过注解标注。

- @LogTag( attrType = AttributeName.ID )：标注在被操作的实体id上
- @LogTag(attrType = AttributeName.PLANNAME)：实体中的线路名称字段
- @LogTag(attrType = AttributeName.PLANID)：实体中的线路id
- @LogTag( attrType = AttributeName.IGNORE )：实体中即使变动也不需要记录的字段，例如更新时间等。



