package c05

import org.jcsp.awt.*
import org.jcsp.groovy.*
import org.jcsp.lang.*
import java.awt.*

class UserInterface implements CSProcess
{

	// define inputs
	def ChannelInput toConsole
	def ChannelOutput fromConsole
	def ChannelInput clearInputArea
	
	// title of console
	def String frameLabel = "Scaler Console"

	def void run()
	{
		/*// define UI frames
		def main = new ActiveClosingFrame (frameLabel)
		def root = main.getActiveFrame()
		root.setLayout(new BorderLayout ())
		
		// draw label
		def outLabel = new Label ("Initial Values", Label.CENTER)
		outLabel.setFont(new Font("sans-serif", Font.BOLD, 20))
		
		// define output to console
		def outText = new ActiveTextArea(toConsole, null)
		def inText = new ActiveTextEnterField (clearInputArea, fromConsole)
		def originalConsole = new Container()
		
		originalConsole.setLayout( new GridLayout (4, 1))
		originalConsole.add ( outLabel )
		originalConsole.add ( outText )
		root.add(originalConsole, BorderLayout.CENTER)
		
		def scaledConsole = new Container()
		
		scaledConsole.setLayout( new GridLayout (4, 1))
		scaledConsole.add ( outLabel )
		scaledConsole.add ( outText )
		root.add(scaledConsole, BorderLayout.CENTER)
		root.pack()
		root.setVisible(true)
		
		def interfaceProcessList = [ main, outText ]
		new PAR (interfaceProcessList).run()*/
		def main = new ActiveClosingFrame (frameLabel)
		def root = main.getActiveFrame()
		root.setLayout ( new BorderLayout () )
		def outLabel = new Label ("Output Area", Label.CENTER)
		outLabel.setFont(new Font("sans-serif", Font.BOLD, 20))
		def inLabel = new Label ("Input Area", Label.CENTER)
		inLabel.setFont(new Font("sans-serif", Font.BOLD, 20))
		def outText = new ActiveTextArea ( toConsole, null )
		def inText = new ActiveTextEnterField ( clearInputArea, fromConsole )
		def console = new Container()
		console.setLayout ( new GridLayout ( 4, 1 ) )
		console.add ( outLabel )
		console.add ( outText )
		console.add ( inLabel )
		console.add ( inText.getActiveTextField() )
		root.add(console, BorderLayout.CENTER )
		root.pack()
		root.setVisible(true)
		def interfaceProcessList = [ main, outText, inText ]
		new PAR ( interfaceProcessList ).run()
	}
}
