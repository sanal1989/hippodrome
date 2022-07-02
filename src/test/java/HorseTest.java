import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    @Test
    void firtsParameterNullConstructor(){
        Throwable exception = assertThrows(IllegalArgumentException.class, ()->{new Horse(null,1.0,1.0);} );

        assertEquals(exception.getMessage(),"Name cannot be null.");
    }

    @ParameterizedTest
    @MethodSource("argsProviderFactory")
    void firtsParameterWhiteSpaceConstructor(String argument) {
        Throwable exception = assertThrows(IllegalArgumentException.class, ()->{new Horse(argument,1.0,1.0);} );

        assertEquals(exception.getMessage(),"Name cannot be blank.");
    }

    static Stream<String> argsProviderFactory() {
        return Stream.of("", "  ", "        ");
    }

    @Test
    void secondParameterNegativeNumberConstructor(){
        Throwable exception = assertThrows(IllegalArgumentException.class, ()->{new Horse("second",-1.0,1.0);} );

        assertEquals(exception.getMessage(),"Speed cannot be negative.");
    }

    @Test
    void thirdParameterNegativeNumberConstructor(){
        Throwable exception = assertThrows(IllegalArgumentException.class, ()->{new Horse("second",1.0,-1.0);} );

        assertEquals( exception.getMessage(),"Distance cannot be negative.");
    }

    @Test
    void testGetName(){
        String name = new String("Name");
        Horse horse = new Horse(name,1.0,1.0);

        assertEquals(horse.getName(),name);
    }

    @Test
    void testGetSpeed(){
        double speed = 3.0;
        Horse horse = new Horse("testSpeed",speed,1.0);

        assertEquals(horse.getSpeed(),speed);
    }

    @Test
    void testGetDistanceWithNull(){
        Horse horse = new Horse("testSpeed",1.0);

        assertEquals(horse.getDistance(),0);
    }

    @Test
    void testGetDistance(){
        double distance = 3.0;
        Horse horse = new Horse("testSpeed",1.0,distance);

        assertEquals(horse.getDistance(),distance);
    }

    @Test
    void testMove() {


        try (MockedStatic<Horse> mockhorse =  Mockito.mockStatic( Horse.class)) {
            //добавляем правило

            double Mockresult = Horse.getRandomDouble(0.2, 0.9);
            mockhorse.when(()->Horse.getRandomDouble(0.2, 0.9)).thenReturn(Mockresult);


            mockhorse.verify(()->Horse.getRandomDouble(0.2, 0.9));

            assertEquals(Mockresult, Horse.getRandomDouble(0.2, 0.9));
        }
    }



}