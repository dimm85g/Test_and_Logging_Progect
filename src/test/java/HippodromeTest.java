import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class HippodromeTest {
    @Test
    public void ifNullName(){
        assertThrows(IllegalArgumentException.class,() -> new Hippodrome(null));
    }
}
