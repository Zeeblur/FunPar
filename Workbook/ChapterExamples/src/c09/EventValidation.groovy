package c09

import org.jcsp.lang.*
import org.jcsp.groovy.*

class EventValidation implements CSProcess {

	def ChannelInput inChannel
	def ChannelOutput outChannel
	def EventData previous
	
    void run()
	{
		while (true)
		{
			// read in current event data
			def currentEvent = (EventData)inChannel.read()
			
			// previous is null for first event
			if (previous != null)
			{
				def actualMissed = currentEvent.data - previous.data - 1
				
				// check if current missed event value is the same as the calculated missed value
				if (currentEvent.missed != actualMissed)
				{
					println "Error: Missed count inaccurate.\nEvent data: $currentEvent.data missed count = $currentEvent.missed, should be $actualMissed"
				}
			}
			
			// send along to GPrint and set new previous event
			outChannel.write(currentEvent)
			previous = currentEvent					
		}
		
	}

}