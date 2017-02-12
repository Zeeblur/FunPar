package c07

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel

import org.jcsp.lang.*
import org.jcsp.groovy.*


class Server implements CSProcess{
	  
  def ChannelInput clientRequest
  def ChannelOutput clientSend  
  def ChannelOutput thisServerRequest
  def ChannelInput thisServerReceive  
  def ChannelInput otherServerRequest
  def ChannelOutput otherServerSend  
  def dataMap = [ : ]    
                
  // server id to identify 
  def serverID
  
  void run () {
    def CLIENT = 0
    def OTHER_REQUEST = 1
    def THIS_RECEIVE = 2
    def serverAlt = new ALT ([clientRequest, 
		                      otherServerRequest, 
							  thisServerReceive])
    while (true) {
      def index = serverAlt.select()
	  
      switch (index) {		  
        case CLIENT :
		  
          def key = clientRequest.read()
		  println "Server $serverID recieves request for $key from CLIENT"
          if ( dataMap.containsKey(key) ) 
            clientSend.write(dataMap[key])       
          else 
	      {
		    println "Server $serverID requests other server for $key"
			  
            thisServerRequest.write(key)
			
	      }
		  
          //end if 
          break
        case OTHER_REQUEST :
		 
          def key = otherServerRequest.read()
		  println "Server $serverID recieves request from other server."
          if ( dataMap.containsKey(key) ) 
            otherServerSend.write(dataMap[key])          
          else 
            otherServerSend.write(-1)
          //end if 
          break
        case THIS_RECEIVE :
		  println "server $serverID gets request from server"
          clientSend.write(thisServerReceive.read() )
		  println "send $serverID as a client"
          break
      } // end switch              
    } //end while   
  } //end run
}
