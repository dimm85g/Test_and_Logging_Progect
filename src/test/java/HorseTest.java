
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class HorseTest {
    @Test
    public void ifNullName(){
        assertThrows(IllegalArgumentException.class,() -> new Horse(null,2,2));
    }
    @Test
    public void ifNullNameToMessage(){
        Throwable throwable = assertThrows(IllegalArgumentException.class,()-> new Horse(null,2,2));
        assertEquals("Name cannot be null.",throwable.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {""," ","\t", "\n"})
    public void IfNullAndWhitespaceCharactersInName(String name){
        assertThrows(IllegalArgumentException.class, ()-> new Horse(name,2,2));
    }
    @ParameterizedTest
    @ValueSource(strings = {""," ","\t", "\n"})
    public void IfNullAndWhitespaceCharactersInNameToMassage(String name){
        Throwable throwable = assertThrows(IllegalArgumentException.class, ()-> new Horse(name,2,2));
        assertEquals("Name cannot be blank.", throwable.getMessage());
    }
    @Test
    public void ifNegativeSpeed(){
        assertThrows(IllegalArgumentException.class,() -> new Horse("Карагёз",-3,2));
    }
    @Test
    public void ifNegativeSpeedToMassage(){
        Throwable throwable = assertThrows(IllegalArgumentException.class, ()-> new Horse("Карагёз",-3,2));
        assertEquals("Speed cannot be negative.", throwable.getMessage());
    }
    @Test
    public void ifNegativeDistance(){
        assertThrows(IllegalArgumentException.class,() -> new Horse("Карагёз",2,-2));
    }
    @Test
    public void ifNegativeDistanceToMassage(){
        Throwable throwable = assertThrows(IllegalArgumentException.class, ()-> new Horse("Карагёз",2,-2));
        assertEquals("Distance cannot be negative.", throwable.getMessage());
    }
    @Test
    public void getName() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Карагёз",2,2);
        assertEquals("Карагёз",horse.getName());
    }
    @Test
    public void getSpeed() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Карагёз",2,2);
        assertEquals(2,horse.getSpeed());
    }
    @Test
    public void getDistance() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Карагёз",2,8);

        Field distance = Horse.class.getDeclaredField("distance");
        distance.setAccessible(true);
        double actualDistance = (double) distance.get(horse);

        assertEquals(8,actualDistance);


        Horse horseTwoParam = new Horse("Боливар",12);

        Field distanceTwoParam = Horse.class.getDeclaredField("distance");
        distanceTwoParam.setAccessible(true);
        double actualDistanceTwoParam = (double) distance.get(horseTwoParam);

        assertEquals(0,actualDistanceTwoParam);
    }
    @Test
    public void move(){
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            new Horse("Карагёз",2,8).move();

            horseMockedStatic.verify(()-> Horse.getRandomDouble(0.2,0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9})
    public void moveWithParam(double dist){
        double distance = 0.3;
        double speed = 2;
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)){
            mockedStatic.when(()-> Horse.getRandomDouble(0.2,0.9)).thenReturn(dist);

            Horse horse = new Horse("Карагёз",2,0.3);
            horse.move();
            double actualDistance = horse.getDistance();
            double currentDistance = distance + speed * dist;

            assertEquals(currentDistance,actualDistance);
        }
    }
}
