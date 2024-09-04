# operationBasic
    查询、修改、删除一体的工具类maven项目,可以对特定的表进行查询、修改、删除等操作
    特定表需满足以下条件：
    1、主键为id
    2、连接的数据库需要是【Mysql】、【Doris】、【DM】、【PostgreSql】其中一个
    3、操作前需要导入std_table_config表，在此表中自定义配置表的查询条件

operationBasic项目有以下方法：
```
1.返回表需要展示的字段以及查询条件【getDynamicTableInfo】
2.根据表名以及查询条件查询动态表【getListByPage】
3.更新动态表数据【updateById】
4.删除动态表数据【deleteById】
```

需要加的表
mysql：
```sql
CREATE TABLE `std_table_config` (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `table_name` varchar(255) DEFAULT NULL COMMENT '表名',
  `query_field` varchar(50) DEFAULT NULL COMMENT '查询字段',
  `query_name` varchar(255) DEFAULT NULL COMMENT '字段名称',
  `query_type` varchar(50) DEFAULT NULL COMMENT '字段类型',
  `query_sort` int(10) DEFAULT NULL COMMENT '查询字段排序',
  `valid` varchar(1) DEFAULT NULL COMMENT '有效标识;Y-有效、N-无效',
  `create_time` datetime(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='std自定义表配置';
```

DM:
```sql
CREATE TABLE QYFX_INIT.STD_TABLE_CONFIG
(
"id" VARCHAR(32) NOT NULL,
"table_name" VARCHAR(50),
"query_field" VARCHAR(50),
"query_type" VARCHAR(50),
"query_sort" VARCHAR(10),
"valid" VARCHAR(1),
"create_time" DATETIME(0) DEFAULT CURRENT_TIMESTAMP(),
"query_name" VARCHAR(255),
NOT CLUSTER PRIMARY KEY("id")) STORAGE(ON "QYFX_INIT", CLUSTERBTR) ;

COMMENT ON TABLE "QYFX_INIT"."STD_TABLE_CONFIG" IS 'std自定义表配置';
COMMENT ON COLUMN "QYFX_INIT"."STD_TABLE_CONFIG"."create_time" IS '创建时间';
COMMENT ON COLUMN "QYFX_INIT"."STD_TABLE_CONFIG"."id" IS '主键id';
COMMENT ON COLUMN "QYFX_INIT"."STD_TABLE_CONFIG"."query_field" IS '查询字段';
COMMENT ON COLUMN "QYFX_INIT"."STD_TABLE_CONFIG"."query_name" IS '字段名称';
COMMENT ON COLUMN "QYFX_INIT"."STD_TABLE_CONFIG"."query_sort" IS '查询字段排序';
COMMENT ON COLUMN "QYFX_INIT"."STD_TABLE_CONFIG"."query_type" IS '字段类型';
COMMENT ON COLUMN "QYFX_INIT"."STD_TABLE_CONFIG"."table_name" IS '表名';
COMMENT ON COLUMN "QYFX_INIT"."STD_TABLE_CONFIG"."valid" IS '有效标识;Y-有效、N-无效';
```