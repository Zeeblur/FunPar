package c2
 
import phw.util.*
import org.jcsp.lang.*


class Consumer implements CSProcess {
  
  def ChannelInput inChannel
  
  void run() {
    def i = inChannel.read()
    while ( i > 0 ) {
      //insert a modified println statement
      println "The output is : ${i}"
      i = inChannel.read()
    }
    println "Finished"
  }
}

