import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
object AvgAgeCalculator {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("Spark Exercise:Average Age Calculator")
    val sc = new SparkContext(conf)
    val dataFile = sc.textFile("F:\\spark\\sample_age_data.txt", 5);
    val count = dataFile.count()
    val ageData = dataFile.map(line => line.split(" ")(1))
    val totalAge = {
      val extracted = ageData.map(age => Integer.parseInt(
        String.valueOf(age)))
      extracted.reduce((a, b) => a + b)
    }
    println("Total Age:" + totalAge + ";Number of People:" + count)
    val avgAge: Double = totalAge.toDouble / count.toDouble
    println("Average Age is " + avgAge)
  }
}