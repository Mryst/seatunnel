/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.interestinglab.waterdrop.spark.source

import org.apache.spark.sql.{Dataset, Row}

import io.github.interestinglab.waterdrop.common.config.CheckResult
import io.github.interestinglab.waterdrop.spark.SparkEnvironment
import io.github.interestinglab.waterdrop.spark.batch.SparkBatchSource

class Hive extends SparkBatchSource {

  override def prepare(env: SparkEnvironment): Unit = {}

  override def checkConfig(): CheckResult = {
    config.hasPath("pre_sql") match {
      case true => new CheckResult(true, "")
      case false => new CheckResult(false, "please specify [pre_sql]")
    }
  }

  override def getData(env: SparkEnvironment): Dataset[Row] = {
    env.getSparkSession.sql(config.getString("pre_sql"))
  }
}
