/**
 * 
 */
package UnitTest;

import static org.junit.Assert.assertEquals;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JLayeredPane;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ui.SampleWindow;

/**
 * @author tuya
 * 
 */
public class TestSampleWindow {
	static SampleWindow unit;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		unit = new SampleWindow();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testComponetCount() {
		for (Component comp : unit.getRootPane().getComponents()) {
			if (comp instanceof JLayeredPane) {
				assertEquals(3,
						((Container) ((Container) comp).getComponent(0))
								.getComponentCount());
			}
		}
	}

}
