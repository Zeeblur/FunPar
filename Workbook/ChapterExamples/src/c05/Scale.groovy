package c05

// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel


import org.jcsp.lang.*
import org.jcsp.groovy.*

class Scale implements CSProcess {

	def int scaling = 2
	def ChannelOutput outChannel
	def ChannelInput inChannel
	def ChannelInput suspend
	def ChannelInput injector

	def ChannelOutput toButton
	def ChannelOutput toConsole

	void run () {
		def SUSPEND  = 0
		def INJECT   = 1
		def INPUT    = 2

		def normalAlt = new ALT ( [ suspend, injector, inChannel ] )
		def suspended = false

		while (true) {
			switch ( normalAlt.priSelect() ) {
				case SUSPEND :
					//  deal with button input
				
					// if suspend change button text and bool to stop scaling
					if (suspend.read() == "SUSPEND")
					{
						toButton.write("RESTART")
						outChannel.write("Suspended\n")
						suspended = true
					}
					else // resume, change flag and button 
					{
						toButton.write("SUSPEND")
						outChannel.write("Resumed\n")
						suspended = false
					}

					break
				case INJECT:
					//  deal with inject input from console 
					scaling = Integer.parseInt(injector.read() ) // try parse data
					outChannel.write("Injected scaling is $scaling \n")
					
					// clear text field
					toConsole.write("")
					
					// ensure scaling is resumed
					toButton.write("SUSPEND")
					suspended = false
					break
				case INPUT:
				//   deal with Input channel
					
					// read from timed data					
					def inValue = inChannel.read()
					def result = new ScaledData()
					result.original = inValue
					
					// flag set by UI button if suspended, don't scale
					if (suspended)
					{
						result.scaled = inValue
					}
					else
					{
						result.scaled = inValue * scaling
					}

					outChannel.write( result )
					break
			} //end-switch
		} //end-while
	} //end-run
} // end Scale
