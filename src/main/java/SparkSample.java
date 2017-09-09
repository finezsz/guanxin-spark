import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;
import org.apache.spark.api.java.function.Function; 

public class SparkSample {  
    private static final Pattern SPACE = Pattern.compile(" ");  
  
    public static void main(String args[]) {  
        SparkConf sparkConf = new SparkConf();  
        sparkConf.setAppName("SparkJavaWordCount");  
        sparkConf.setMaster("local");  
  
        JavaSparkContext context = new JavaSparkContext(sparkConf);  
  
        List<Integer> data = Arrays.asList(1,2,3,4,5);  
        JavaRDD<Integer> distData= context.parallelize(data);  
   
         
        JavaPairRDD<Integer, Integer> firstRDD = distData.mapToPair(a->{return new Tuple2(a, a*a);});  
        
        
        JavaPairRDD<Integer, String> secondRDD = distData.mapToPair(new PairFunction<Integer, Integer, String>() {  
            @Override  
            public Tuple2<Integer, String> call(Integer integer) throws Exception {  
                return new Tuple2(integer, String.valueOf((char)('a' + integer)));  
            };  
        });   
  
        JavaPairRDD<Integer, Tuple2<Integer, String>> joinRDD = firstRDD.join(secondRDD);  
   
        
        JavaRDD<String> result = joinRDD.map(new Function<Tuple2<Integer, Tuple2<Integer, String>>, String>() {  
            @Override  
            public String call(Tuple2<Integer, Tuple2<Integer, String>> integerTuple2Tuple2) throws Exception {  
                int key = integerTuple2Tuple2._1();  
                int value1 = integerTuple2Tuple2._2()._1();  
                String value2 = integerTuple2Tuple2._2()._2();  
                return key + " " + value1 + " " + value2;  
            }  
        });  
  
        List<String> reslist = result.collect();  
        for(String str : reslist) {  
            System.out.println(str);  
        }  
        context.stop();  
    }  
}  