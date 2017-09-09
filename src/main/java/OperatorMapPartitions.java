
import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class OperatorMapPartitions {

	private static JavaSparkContext sc;

	public static void main(String[] args) {
		SparkConf conf = new SparkConf();
		conf.set("spark.testing.memory", "2147480000"); // 因为jvm无法获得足够的资源
		sc = new JavaSparkContext("local", "Spark App", conf); // 本地模式使用local
		List<Integer> list = Arrays.asList(1, 2, 3, 4);
		List<Integer> list2 = Arrays.asList(5, 6, 7, 1);
		JavaRDD<Integer> arrayRDD = sc.parallelize(list);
		JavaRDD<Integer> arrayRDD2 = sc.parallelize(list2);
		// 算子cartesian就是用来求两个RDD的笛卡尔积的。
		JavaPairRDD<Integer, Integer> result = arrayRDD.cartesian(arrayRDD2);
		System.out.println(result.collect());

	}
}