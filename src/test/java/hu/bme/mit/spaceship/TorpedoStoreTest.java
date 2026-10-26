package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(1);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertEquals(true, result);
    }

    @Test
    void constructor_SetsTorpedoCount() {
        TorpedoStore store = new TorpedoStore(3, 0.0);

        assertEquals(3, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }

    @Test
    void isEmpty_NoTorpedoes_True() {
        TorpedoStore store = new TorpedoStore(0, 0.0);

        assertTrue(store.isEmpty());
    }

    @Test
    void fire_One_DecreasesCountByOne() {
        TorpedoStore store = new TorpedoStore(3, 0.0);

        boolean result = store.fire(1);

        assertTrue(result);
        assertEquals(2, store.getTorpedoCount());
    }

    @Test
    void fire_Two_DecreasesCountByTwo() {
        TorpedoStore store = new TorpedoStore(3, 0.0);

        boolean result = store.fire(2);

        assertTrue(result);
        assertEquals(1, store.getTorpedoCount());
    }

    @Test
    void fire_AllTorpedoes_StoreBecomesEmpty() {
        TorpedoStore store = new TorpedoStore(3, 0.0);

        boolean result = store.fire(3);

        assertTrue(result);
        assertEquals(0, store.getTorpedoCount());
        assertTrue(store.isEmpty());
    }

    @Test
    void fire_MoreThanTorpedoCount_ThrowsException() {
        TorpedoStore store = new TorpedoStore(3, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(4));
        assertEquals(3, store.getTorpedoCount());
    }

    @Test
    void fire_Zero_ThrowsException() {
        TorpedoStore store = new TorpedoStore(3, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
        assertEquals(3, store.getTorpedoCount());
    }

    @Test
    void fire_EmptyStore_ThrowsException() {
        TorpedoStore store = new TorpedoStore(0, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(1));
    }

    @Test
    void fire_FailureRateOne_FailsAndKeepsTorpedoes() {
        TorpedoStore store = new TorpedoStore(3, 1.0);

        boolean result = store.fire(1);

        assertFalse(result);
        assertEquals(3, store.getTorpedoCount());
    }
}
