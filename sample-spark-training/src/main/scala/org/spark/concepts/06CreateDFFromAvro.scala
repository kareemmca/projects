package org.spark.concepts

import org.apache.spark.sql.SparkSession

import java.io.File

import org.apache.avro.Schema
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.functions._



/****Apache Avro Advantages *****/
/*
Supports complex data structures like Arrays, Map, Array of map and map of array elements.
A compact, binary serialization format which provides fast while transferring data.
row-based data serialization system.
Support multi-languages, meaning data written by one language can be read by different languages.
Code generation is not required to read or write data files.
Simple integration with dynamic languages.

<dependency>
    <groupId>org.apache.spark</groupId>
    <artifactId>spark-avro_2.11</artifactId>
    <version>2.4.0</version>
</dependency>
*/


  /**
  * Spark Avro library example
  * Avro schema example
  * Avro file format
  *
  */
object CreateDFFromAvro {

  def main(args: Array[String]): Unit = {


    val spark: SparkSession = SparkSession.builder().master("local[1]")
      .appName("SparkByExamples.com")
      .getOrCreate()

    val data = Seq(("James ", "", "Smith", 2018, 1, "M", 3000),
      ("Michael ", "Rose", "", 2010, 3, "M", 4000),
      ("Robert ", "", "Williams", 2010, 3, "M", 4000),
      ("Maria ", "Anne", "Jones", 2005, 5, "F", 4000),
      ("Jen", "Mary", "Brown", 2010, 7, "", -1)
    )

    val columns = Seq("firstname", "middlename", "lastname", "dob_year",
      "dob_month", "gender", "salary")
    import spark.sqlContext.implicits._
    val df = data.toDF(columns: _*)

    /**
      * Spark DataFrame Write Avro File
      */
    df.write.format("avro")
      .mode(SaveMode.Overwrite)
      .save("C:\\tmp\\spark_out\\avro\\person.avro")

    /**
      * Spark DataFrame Read Avro File
      */
    spark.read.format("avro").load("/tmp/spark_out/avro/person.avro").show()

    /**
      * Write Avro Partition
      */
    df.write.partitionBy("dob_year","dob_month")
      .format("avro")
      .mode(SaveMode.Overwrite)
      .save("/tmp/spark_out/avro/person_partition.avro")

    /**
      * Reading Avro Partition
      */
    spark.read
      .format("avro")
      .load("/tmp/spark_out/avro/person_partition.avro")
      .where(col("dob_year") === 2010)
      .show()

    /**
      * Explicit Avro schema
      */
    val schemaAvro = new Schema.Parser().parse(new File("src/main/resources/person.avsc"))

    spark.read
      .format("avro")
      .option("avroSchema", schemaAvro.toString)
      .load("/tmp/spark_out/avro/person.avro")
      .show()

    /**
      * Avro Spark SQL
      */
    spark.sqlContext.sql("CREATE TEMPORARY VIEW PERSON USING avro OPTIONS (path \"/tmp/spark_out/avro/person.avro\")")
    spark.sqlContext.sql("SELECT * FROM PERSON").show()
  }
}
