import static org.junit.Assert.*;
import org.junit.Test;

public class helloworldTest {
	public helloworld hw = new helloworld();
	@Test
	public void testHello() {
		hw.hello();
		assertEquals("Are you pig?", hw.getStr());
	}
}
