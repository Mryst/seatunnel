# Sink plugin: Clickhouse

### Description:
Use Spark Batch Engine ETL Data to Clickhouse.

### Options
| name         | type   | required | default value | engine |
| ------------ | ------ | -------- | ------------- | ------ |
| bulk_size    | number | no       | 20000         | Spark  |
| database     | string | yes      | -             | Spark  |
| table        | string | yes      | -             | Spark  |
| username     | string | yes      | -             | Spark  |
| password     | string | yes      | -             | Spark  |
| ck_sql       | string | no       | -             | Spark  |
| clickhouse.* | string | no       | -             | Spark  |

**bulk_size** [number]

**database** [string]
ClickHouse database

**fields** [array]

**host** [string]

**password** [string]

**retry** [number]

**retry_codes** [array]

**table** [string]

**username** [string]

**clickhouse.*** [string]

### Examples

```
ClickHouse {
    host = "localhost:8123"
    database = "dwt"
    table = "dwt_wide_table"
    username = "username"
    password = "password"
    bulk_size = 20000
    ck_sql = "ALTER TABLE dwt.dwt_wide_table DELETE WHERE dt <= '${dt}'"
    retry_codes = [209, 210]
    retry = 3
}
```

