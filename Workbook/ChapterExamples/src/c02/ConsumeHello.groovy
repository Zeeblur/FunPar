package c02

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel



import org.jcsp.lang.*

class ConsumeHello implements CSProcess {
  
  def ChannelInput inChannel
  
  // test
  def message
  
  void run() {
    def first = inChannel.read()
    def second = inChannel.read()
	
	message = "${first} ${second}!\n"
	println message
  }
}

