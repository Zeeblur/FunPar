package c2

import org.jcsp.lang.*

class ListToStream implements CSProcess{
	
	def ChannelInput inChannel
	def ChannelOutput outChannel
	
	def inTest = [8]
	
	void run (){
		def inList = inChannel.read()
		while (inList[0] != -1) {
			// hint: output	list elements as single integers
			for ( i in 0 ..< inList.size)
			{
				outChannel.write(inList[i])
				
				inTest[i] = (inList[i])
			}
			inList = inChannel.read()
		}
		outChannel.write(-1)
	}
}