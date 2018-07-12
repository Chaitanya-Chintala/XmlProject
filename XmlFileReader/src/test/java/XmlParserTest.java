import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.chaitanya.model.XmlParsingImpl;

public class XmlParserTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	private XmlParsingImpl object;

	@Before
	public void init() {
		object = new XmlParsingImpl();
	}

	@Test
	public void checkForElementAfterParsingTest() {
		ArrayList<String> result = object.XmlReader("Data.xml");
		assertNotNull(result);
		assertEquals(true, result.contains("John"));
	}

	@Test
	public void checkFileNotFoundExceptionTest() {
		exception.expect(RuntimeException.class);
		object.XmlReader("Data1.xml");

	}
	
	@Test
	public void checkForEmptyXmlFileTest() {
		ClassLoader classLoader = getClass().getClassLoader();
		File xmlFile = new File(classLoader.getResource("Data.xml").getFile());
		assertNotNull(xmlFile.length());
	}

}
