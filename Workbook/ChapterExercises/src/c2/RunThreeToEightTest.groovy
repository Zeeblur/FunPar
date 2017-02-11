package c2;

import groovy.util.GroovyTestCase
import org.jcsp.lang.*
import org.jcsp.groovy.*

class RunThreeToEightTest extends GroovyTestCase {

	void testThreeToEight()
	{
		One2OneChannel genToStream = Channel.one2one()
		One2OneChannel streamToEight = Channel.one2one()
		
		def gen = new GenerateSetsOfThree ( outChannel: genToStream.out())
		def list = new ListToStream ( inChannel: genToStream.in(), outChannel: streamToEight.out())
		def eight = new CreateSetsOfEight ( inChannel: streamToEight.in())
		
		def testRunList = [gen, list, eight]
		new PAR(testRunList).run()
		
		// test output is correct from eight
		def expectedList = list.inTest
		def actualList = eight.outTest
		
		println "exp ${expectedList} + act ${actualList}"
		
		assertTrue(expectedList == actualList)
	}
}
