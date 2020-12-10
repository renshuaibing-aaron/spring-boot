import com.aaron.ren.tinyid.client.utils.TinyId;
import org.junit.Test;

/**
 * @Author du_imba
 */

public class ClientTest {

    @Test
    public void testNextId() {
        for (int i = 0; i < 1000000; i++) {
            Long id = TinyId.nextId("test3");
            System.out.println("current id is: " + id);
        }
    }
}
