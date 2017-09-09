

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD.rddToOrderedRDDFunctions
import org.apache.spark.rdd.RDD.rddToPairRDDFunctions

object TopKSearchKeyWords {
  def main(args: Array[String]) {
    if (args.length < 2) {
      println("Usage:TopKSearchKeyWords KeyWordsFile K");
      System.exit(1)
    }
    val conf = new SparkConf().setAppName("Spark Exercise:Top K Searching Key Words")
    val sc = new SparkContext(conf)
    val srcData = sc.textFile(args(0))
    val countedData = srcData.map(line => (line.toLowerCase(), 1)).reduceByKey((a, b) => a + b)
    //for debug use
    //countedData.foreach(x => println(x))
    val sortedData = countedData.map { case (k, v) => (v, k) }.sortByKey(false)
    val topKData = sortedData.take(args(1).toInt).map { case (v, k) => (k, v) }
    topKData.foreach(println)
  }
}