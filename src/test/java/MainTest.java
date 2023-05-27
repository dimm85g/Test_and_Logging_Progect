import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class MainTest {
    @Test
    @Timeout(22)
    @Disabled
    public void MainTest() throws Exception {
        Main.main(null);
    }
}
