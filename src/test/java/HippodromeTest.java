import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.withSettings;

class HippodromeTest {

    @Test
    void parameterNullConstructor(){
        Throwable exception = assertThrows(IllegalArgumentException.class, ()->{new Hippodrome(null);} );

        assertEquals(exception.getMessage(),"Horses cannot be null.");
    }

    @Test
    void parameterZeroSizeListConstructor(){
        Throwable exception = assertThrows(IllegalArgumentException.class, ()->{new Hippodrome(new ArrayList<Horse>());} );

        assertEquals(exception.getMessage(),"Horses cannot be empty.");
    }

    @Test
    void testGetHorses(){
        List<Horse> list = new ArrayList<>();
        for(int i = 0; i < 30; i++){
            int x = i + 1;
            list.add(new Horse("Number horse" + x,10.0*x,10.0*x));
        }
        Hippodrome hippodrome = new Hippodrome(list);

        assertEquals(hippodrome.getHorses(), list);
    }

    @Test
    void testMove(){
        List<Horse> list = new ArrayList<>();
        for(int i = 0; i < 50; i++){
            list.add(Mockito.mock(Horse.class));
        }
        List<Horse> spyList = Mockito.spy(list);
        Hippodrome hippodrome = Mockito.spy(new Hippodrome(spyList));
        //Hippodrome hippodrome = Mockito.mock(Hippodrome.class, withSettings().useConstructor(list));
        Mockito.doAnswer((x)-> {
            for (Horse horse: spyList ) {
                horse.move();
                Mockito.verify(horse).move();
            }
            return null;
        }).when(hippodrome).move();



    }
    @Test
    void testGetWinner(){
        List<Horse> list = new ArrayList<>();
        list.add(new Horse("test",1.0,1.0));
        list.add(new Horse("test",1.0,1.0));
        list.add(new Horse("Win",1.0,5.0));
        Hippodrome hippodrome = Mockito.spy(new Hippodrome(list));
        assertEquals(hippodrome.getWinner(),list.get(2));
    }
}