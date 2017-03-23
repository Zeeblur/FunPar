package c05
 
// copyright 2012-13 Jon Kerridge
// Let's Do It In Parallel
import org.jcsp.lang.*
import org.jcsp.groovy.*
import org.jcsp.groovy.plugAndPlay.*


def data = Channel.one2one()
def timedData = Channel.one2one()
def scaledData = Channel.one2one()
def newScale = Channel.one2one()
def pause = Channel.one2one() 

def scale2Btn = Channel.one2one()
def scale2Text = Channel.one2one()

def network = [ new GNumbers ( outChannel: data.out() ),
                new GFixedDelay ( delay: 1000, 
                		          inChannel: data.in(), 
                		          outChannel: timedData.out() ),
							  
                new Scale ( inChannel: timedData.in(),
                            outChannel: scaledData.out(),
                            suspend: pause.in(),
                            injector: newScale.in(),
                            scaling: 2,
							toButton: scale2Btn.out(),
							toConsole: scale2Text.out() ),
				
				new UserInterface(toConsole: scaledData.in(),
								  suspendButton: scale2Btn.in(),
								  suspend: pause.out(),
								  fromConsole: newScale.out(),
								  clearInputArea: scale2Text.in())
			   
              ]

new PAR ( network ).run()                                                            
