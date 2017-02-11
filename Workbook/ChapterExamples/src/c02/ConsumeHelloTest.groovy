package c02;

import groovy.util.GroovyTestCase
import org.jcsp.groovy.*
import org.jcsp.lang.*

class ConsumeHelloTest extends GroovyTestCase {

	void testMessage()
	{
		def connect = Channel.one2one()
		
		def producer = new ProduceHW (outChannel: connect.out())

		def consumer = new ConsumeHello(inChannel: connect.in())
		
		def processList = [producer, consumer]
		new PAR (processList).run()
		
		def expected = "Hello World!\n"
		def actual = consumer.message

		assertTrue(expected == actual)
	}
}
