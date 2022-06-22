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
    void firtsParameterNullConstructorTypeEception(){
        Throwable exception = assertThrows(IllegalArgumentException.class, ()->{new Horse(null,1.0,1.0);} );
    }
    @Test
    void firtsParameterNullConstructorTextEception(){
        Throwable exception = new Throwable();
        try{
            new Horse(null,1.0,1.0);
        }catch (Exception e){
            exception = e;
        }

        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("argsProviderFactory")
    void firtsParameterWhiteSpaceConstructorTypeEception(String argument) {
        Throwable exception = assertThrows(IllegalArgumentException.class, ()->{new Horse(argument,1.0,1.0);} );
    }
    @ParameterizedTest
    @MethodSource("argsProviderFactory")
    void firtsParameterWhiteSpaceConstructorTextEception(String argument) {
        Throwable exception = new Throwable();
        try{
            new Horse(argument,1.0,1.0);
        }catch (Exception e){
            exception = e;
        }

        assertEquals("Name cannot be blank.", exception.getMessage());
    }
    static Stream<String> argsProviderFactory() {
        return Stream.of("", "  ", "        ");
    }

    @Test
    void secondParameterNegativeNumberConstructorTypeEception(){
        Throwable exception = assertThrows(IllegalArgumentException.class, ()->{new Horse("second",-1.0,1.0);} );
    }
    @Test
    void secondParameterNegativeNumberConstructorTextEception(){
        Throwable exception = new Throwable();
        try{
            new Horse("second",-1.0,1.0);
        }catch (Exception e){
            exception = e;
        }

        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void thirdParameterNegativeNumberConstructorTypeEception(){
        Throwable exception = assertThrows(IllegalArgumentException.class, ()->{new Horse("second",1.0,-1.0);} );
    }
    @Test
    void thirdParameterNegativeNumberConstructorTextEception(){
        Throwable exception = new Throwable();
        try{
            new Horse("second",1.0,-1.0);
        }catch (Exception e){
            exception = e;
        }

        assertEquals( "Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void testGetName(){
        String name = new String("Name");
        Horse horse = new Horse(name,1.0,1.0);

        assertEquals(name, horse.getName());
    }

    @Test
    void testGetSpeed(){
        double speed = 3.0;
        Horse horse = new Horse("testSpeed",speed,1.0);

        assertEquals(speed, horse.getSpeed());
    }

    @Test
    void testGetDistanceWithNull(){
        Horse horse = new Horse("testSpeed",1.0);

        assertEquals(0,horse.getDistance());
    }

    @Test
    void testGetDistance(){
        double distance = 3.0;
        Horse horse = new Horse("testSpeed",1.0,distance);

        assertEquals(distance, horse.getDistance());
    }

    @Test
    void testMoveMockObject() {
        try (MockedStatic<Horse> mockhorse =  Mockito.mockStatic( Horse.class)) {
            //добавляем правило
            Horse horse = new Horse("test",1.0,1.0);
            horse.move();
            mockhorse.verify(()->Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @MethodSource("argsDistanse")
    void testMoveMockObject(Double param) {
        try (MockedStatic<Horse> mockhorse =  Mockito.mockStatic( Horse.class)) {
            //добавляем правило
            mockhorse.when(()->Horse.getRandomDouble(0.2, 0.9)).thenReturn(param);
            Horse horse = new Horse("test",1.0);
            horse.move();
            double distance = horse.getSpeed() * param;
            assertEquals(distance, horse.getDistance());
        }
    }
    static Stream<Double> argsDistanse() {
        return Stream.of(1.0, 2.0, 3.0);
    }
}