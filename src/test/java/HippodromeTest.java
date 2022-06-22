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
    void parameterNullConstructorTypeEception(){
        Throwable exception = assertThrows(IllegalArgumentException.class, ()->{new Hippodrome(null);} );
    }
    @Test
    void parameterNullConstructorTextEception(){
        Throwable exception = new Throwable();
        try{
            new Hippodrome(null);
        }catch (Exception e){
            exception = e;
        }

        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void parameterZeroSizeListConstructorTypeEception(){
        Throwable exception = assertThrows(IllegalArgumentException.class, ()->{new Hippodrome(new ArrayList<Horse>());} );
    }
    @Test
    void parameterZeroSizeListConstructorTextEception(){
        Throwable exception = new Throwable();
        try{
            new Hippodrome(new ArrayList<Horse>());
        }catch (Exception e){
            exception = e;
        }

        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void testGetHorses(){
        List<Horse> list = new ArrayList<>();
        for(int i = 0; i < 30; i++){
            int x = i + 1;
            list.add(new Horse("Number horse" + x,10.0*x,10.0*x));
        }
        Hippodrome hippodrome = new Hippodrome(list);

        assertEquals(list,hippodrome.getHorses());
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
        assertEquals(list.get(2),hippodrome.getWinner());
    }
}