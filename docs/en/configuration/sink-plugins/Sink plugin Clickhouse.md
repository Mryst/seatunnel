# Sink plugin: Doirs

### Description:
Use Spark Batch Engine ETL Data to Clickhouse.

### Options
| name         | type   | required | default value | engine |
| ------------ | ------ | -------- | ------------- | ------ |
| bulk_size    | number | yes      | 20000         | Spark  |
| database     | string | yes      | -             | Spark  |
| table        | string | yes      | -             | Spark  |
| username     | string | yes      | -             | Spark  |
| password     | string | yes      | -             | Spark  |
| ck_sql       | string | no       | -             | Spark  |
| clickhouse.* | string | no       | -             | Spark  |

**bulk_size** [number]
每次通过ClickHouse JDBC写入数据的条数，默认为20000。

**database** [string]
ClickHouse database

**fields** [array]
需要输出到ClickHouse的数据字段，若不配置将会自动根据数据的Schema适配。

**host** [string]
ClickHouse集群地址，格式为host:port，允许指定多个host。如"host1:8123,host2:8123"。

**password** [string]
ClickHouse用户密码，仅当ClickHouse中开启权限时需要此字段。

**retry** [number]
重试次数，默认为1次

**retry_codes** [array]
出现异常时，会重试操作的ClickHouse异常错误码。详细错误码列表参考 ClickHouseErrorCode

如果多次重试都失败，将会丢弃这个批次的数据，慎用！！

**table** [string]
ClickHouse 表名

**username** [string]
ClickHouse用户用户名，仅当ClickHouse中开启权限时需要此字段

**clickhouse.*** [string]
除了以上必备的 clickhouse-jdbc须指定的参数外，用户还可以指定多个非必须参数，覆盖了clickhouse-jdbc提供的所有参数.

指定参数的方式是在原参数名称上加上前缀"clickhouse."，如指定socket_timeout的方式是: clickhouse.socket_timeout = 50000。如果不指定这些非必须参数，它们将使用clickhouse-jdbc给出的默认值。

### Examples

```
ClickHouse {
    host = "localhost:8123"
    database = "dwt"
    table = "dwt_wide_table"
    username = "username"
    password = "password"
    bulk_size = 20000
    #同步数据前执行指定删除(初始化)语句
    ck_sql = "ALTER TABLE dwt.dwt_wide_table DELETE WHERE dt <= '${dt}'"
    retry_codes = [209, 210]
    retry = 3
}
```

