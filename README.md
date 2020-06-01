- 模块设计规划

- ![nginx  Spring cloud gateWay  Spr i ng  cloud  security  oath2  nacos  Service Providerl  Service Consuærl  redis  mysq I  nacos  Service Provider2  Service Consuer2  elasticsearch  mysq  nacos  Service provider3  Service Corsunr3  Sent in  Seat a  el  kafka  mysql ](file:///C:/Users/ADMINI~1/AppData/Local/Temp/msohtmlclip1/01/clip_image001.png)

-  

- - 采用spring cloud alibaba作为整体微架构
  - 初期规划模块

-  

- 主工程——单一Web服务

-  

- | web         | SpringCloud接口暴露层-提供给前端访问层                       |
  | ----------- | ------------------------------------------------------------ |
  | web-service | 服务提供层（初期可不考虑web层，直接以web-service返回）       |
  | common      | 公共配置、全局统一异常、全局响应、以及工具类支持模块         |
  | generator   | 代码生成模块                                                 |
  | config      | 基于nacos的配置中心（初期工程以及代码请忽略）                |
  | gateway     | 微服务网关                                                   |
  | task        | 定时任务模块                                                 |
  | seata       | seata-account-service、seata-order-service、seata-storage-service   分布式事务案例（初期工程以及代码请忽略） |
  | swagger     | 各服务引入本模块即可支持swagger扫描                          |

-  

-  

- 初期接入服务设计

- | nacos                          | 各服务注册中心、配置中心，可利用Nginx做集群 |
  | ------------------------------ | ------------------------------------------- |
  | spring cloud Security（Oath2） | 认证授权服务（认证、授权可拆分）            |
  | spring Cloud Gateway           | 服务网关                                    |
  | file Upload                    | 文件上传服务（各服务统一）                  |
  | feign Service                  | 远程服务                                    |
  | swagger service                | 接口文档在线可视化、调试                    |

-  

-  

- 初期接入中间件设计

-  

- | nginx         | 反向代理、指向前端独立应用   |
  | ------------- | ---------------------------- |
  | redis         | 全局缓存、oauth2认证存储容器 |
  | elasticsearch | 搜索引擎                     |
  | mysql         | 数据存储中心                 |

-  

-  

-  

- 本次架构设计/规范定义基本原则

- 以过往遇到的某个项目为例

-  

- 原生的SQL，开发效率慢，可引入ORM思想优化，实际落地采用MybatisPlus或者SpringData系列 需要考虑分页统一问题 尽量使用单一持久层

-  

- 部分存在性能问题的代码 考虑缓存 例如 持久化用户角色权限

-  

- 数据库表中的主要时间字段ctime utime，部分表存在空缺 考虑jdk8中的时间类

-  

- 数据库表主键 采用 全局唯一雪花算法生成的ID 框架提供 uuid性能较差 官方不推荐

-  

- 数据的响应、异常，目前代码混乱，没有一个统一的风格，存在大量硬编码

-  

- 以包分类区分模块，维护性很一般 考虑 拆分模块  构建单工程 多模块应用

-  

- 公用代码、工具类 需要统一，项目中大量使用 规范不一 避免自行创建 首先去common模块查找  所有公共文件类 移动到common模块 如果业务强依赖的工具类 则放置到自己的模块使用

-  

-  

-  

- 注释规范

- - 所有的类 必须有文件头注释 至少具备 （作者、描述）
  - 复杂业务建议带上一定注释
  - service接口需要有注释、包括入参 返回结果 
  - 类名首字母大写 
  - bean对象 需要具备swagger属性注释 以及属性注释

-  

-  

-  

- Code码

- 基础Code码全局统一，通过枚举类定义 例如

-  

- SUCCESS(0, "请求成功"),

- SYSTEM_BUSY(100, "系统繁忙"),

- REQUEST_TIME_OUT(300, "请求超时"),

- PARAMETER_ERROR(400, "参数错误"),

- NETWORK_ERROR(404, "网络异常"),

- DATA_NOT_EXISTS(600, "数据不存在"),

- ACCESSDENIED_ERROR(501,"你无权访问"),

- FAILURE(999, "未知错误");

-  

- 各业务Code码，自行定义 通过约定 不允许占用同一Code段

- PARAM_INVALID(3001, "菜单不存在"),

- MENU_ROLE_ERROR(3002, "菜单、权限对应数据有误"),

- ISEXIT_CHILDREN(3003, "当前节点下存在子菜单,禁止删除"),

- PARENT_NODE_QUERY_NULL(3004, "父节点查询不到数据、权限对应数据有误"),

- PARENT_IS_NOTEXIT(3005, "父菜单不存在"),

- ROLE_ONEMENU_ISNULL(3006, "当前用户角色无一级菜单,不允许添加子菜单");

-  

-  

- 返回数据格式（用于前端对接）：

-  

- 1.普通查询 GET

-  

- {"code":0,"msg":"请求成功","data":{"cuser":"black","ctime":null,"uuser":"black","utime":null,"id":"1","username":"admin","password":"$2a$10$ZsM5tnQRh0GR5CSg.Kdm.eXGVHcyoFwQB9rfgKSEEntAeo6A67yjO"}}

-  

- 2.提交POST 

-  

- {"code":0,"msg":"请求成功","data": "true"}

-  

- 3.修改PUT 

-  

- {"code":0,"msg":"请求成功","data": "true"}

-  

- 4.分页查询  案例 [?pageNum=1&pageSize=10&id=1](http://localhost:9200/web/user/list?pageNum=1&pageSize=10&id=1)           pageNum=起始页数  pageSize=页数量 

-  

- {"code":0,"msg":"请求成功","data":{"records":[{"cuser":"black","ctime":null,"uuser":"black","utime":null,"id":"1195225356584357890","username":"black","password":"$2a$10$ZsM5tnQRh0GR5CSg.Kdm.eXGVHcyoFwQB9rfgKSEEntAeo6A67yjO"},{"cuser":"black","ctime":null,"uuser":"black","utime":null,"id":"1195225358736035842","username":"black","password":"$2a$10$ZsM5tnQRh0GR5CSg.Kdm.eXGVHcyoFwQB9rfgKSEEntAeo6A67yjO"},{"cuser":"black","ctime":null,"uuser":"black","utime":null,"id":"1195225362192142338","username":"black","password":"$2a$10$ZsM5tnQRh0GR5CSg.Kdm.eXGVHcyoFwQB9rfgKSEEntAeo6A67yjO"}],"total":6,"size":10,"current":1,"orders":[],"searchCount":true,"pages":1}}

-  

- 返回结果  

-  

- | code    | 成功状态码       |
  | ------- | ---------------- |
  | msg     | 前台返回提示消息 |
  | data    | 数据整体         |
  | records | 仅数据           |
  | total   | 数据总数         |
  | size    | 当前页数量       |
  | current | 当前页           |
  | pages   | 共计页数         |

-  

-  

-  

- LOG日志——logBack

-  

- 不过多规范，每个访问方法 需要有 关键业务代码 需要有 Log记录  方便调用定位、问题定位、追踪

-  

-  

-  

- 备注： 

- 若采用mybatis plus等框架无法解决的业务 则手动创建xml配置 手写sql 分页则使用pagehelper等插件 最终统一分页的数据格式返回