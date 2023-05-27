import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;


public class HippodromeTest {
    @Test
    public void ifNull(){
        assertThrows(IllegalArgumentException.class,() -> new Hippodrome(null));
    }
    @Test
    public void ifNullToMessage(){
        Throwable throwable = assertThrows(IllegalArgumentException.class,()-> new Hippodrome(null));
        assertEquals("Horses cannot be null.",throwable.getMessage());
    }
    @Test
    public void ifGetEmptyList(){
        List<Horse> list = new ArrayList<>();
        assertThrows(IllegalArgumentException.class,() -> new Hippodrome(list));
    }
    @Test
    public void ifGetEmptyListToMessage(){
        List<Horse> list = new ArrayList<>();
        Throwable throwable = assertThrows(IllegalArgumentException.class,() -> new Hippodrome(list));
        assertEquals("Horses cannot be empty.",throwable.getMessage());
    }
    @Test
    public void getHorse(){
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 30; i++){
            horseList.add(new Horse("Карагёз" + i,i,i));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);

        assertEquals(horseList,hippodrome.getHorses());
    }
    @Test
    public void move(){
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 50; i++){
            horses.add(Mockito.mock(Horse.class));
        }

        new Hippodrome(horses).move();

        for (Horse horse: horses) {
            verify(horse).move();
        }
    }

    @Test
    public void getWinnerTest() {
        List<Horse> horses = new ArrayList<>();

        Horse horse = new Horse("h1",1,1);
        Horse horse1 = new Horse("h2",2,2);
        Horse horse2 = new Horse("h3", 3,3);

        horses.add(horse);
        horses.add(horse1);
        horses.add(horse2);

        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horse2,hippodrome.getWinner());
    }
}
