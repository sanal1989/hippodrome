

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.isNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hippodrome {
    static Logger logger = LogManager.getLogger();
    private final List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        if (isNull(horses)) {
            logger.error("Horses list is null");
            throw new IllegalArgumentException("Horses cannot be null.");
        } else if (horses.isEmpty()) {
            logger.error("Horses list is empty");
            throw new IllegalArgumentException("Horses cannot be empty.");
        }

        this.horses = horses;
        logger.debug(" Создание Hippodrome, лошадей ["+horses.size()+"]");
    }

    public List<Horse> getHorses() {
        return Collections.unmodifiableList(horses);
    }

    public void move() {
        horses.forEach(Horse::move);
    }

    public Horse getWinner() {
        return horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();
    }
}
