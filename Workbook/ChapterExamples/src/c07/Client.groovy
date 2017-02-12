package c07

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.lang.*

import java.util.concurrent.ThreadPoolExecutor.AbortPolicy

import org.jcsp.groovy.*


class Client implements CSProcess{  
	
  def ChannelInput receiveChannel
  def ChannelOutput requestChannel
  def clientNumber   
  def selectList = [ ] 
  
  def actualList = [ ]
  def expectedList = [ ]
   
  void run () {
    def iterations = selectList.size
    println "Client $clientNumber has $iterations values in $selectList"
	
    for ( i in 0 ..< iterations) {
      def key = selectList[i]
	  println "Client number $clientNumber requests $key"
      requestChannel.write(key)
      def v = receiveChannel.read()
	  
	  // add response from server to actual response list
	  actualList << v
    }
	
	println "Client $clientNumber has finished"
		
	// multiply each value by ten and add in order from selectList = expected value from server
	for(i in 0 ..< iterations)expectedList << selectList[i]*10
	
	// check if actual equals expected 
	if (actualList.equals(expectedList))
		println "test passed"
	else
		println "test failed"
  }
}
