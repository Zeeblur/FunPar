package c09

import org.jcsp.lang.*
import org.jcsp.groovy.*

class EventValidation implements CSProcess {

	def ChannelInput inChannel
	def ChannelOutput outChannel
	def missed = 0
	def EventData previous
	
    void run()
	{
		def event = inChannel.read()
	}

}