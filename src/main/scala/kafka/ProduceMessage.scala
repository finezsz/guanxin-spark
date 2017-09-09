
import java.text.DecimalFormat
import java.util.Properties

import org.apache.kafka.clients.producer.{ KafkaProducer, ProducerRecord }

import scala.util.Random
/**
 * Created by http://www.fanlegefan.com on 17-7-21.
 */
object ProduceMessage {

  def main(args: Array[String]): Unit = {

    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("acks", "all")
    props.put("retries", "0")
    props.put("batch.size", "16384")
    props.put("linger.ms", "1")
    props.put("buffer.memory", "33554432")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String, String](props)

    val users = Array("jack", "leo", "andy", "lucy", "jim", "smith", "iverson", "andrew")
    val pages = Array("iphone4.html", "huawei.html", "mi.html", "mac.html", "note.html", "book.html", "fanlegefan.com")
    val df = new DecimalFormat("#.00")
    val random = new Random()
    val num = 10
    for (i <- 0 to num) {
      val message = users(random.nextInt(users.length)) + "," + pages(random.nextInt(pages.length)) +
        "," + df.format(random.nextDouble() * 1000) + "," + System.currentTimeMillis()
      producer.send(new ProducerRecord[String, String]("test", Integer.toString(i), message))
      println(message)
    }
    producer.close()
  }
}  