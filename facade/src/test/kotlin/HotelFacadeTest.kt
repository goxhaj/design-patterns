import inside.HotelFacade
import inside.HotelParadise
import inside.Hotels
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate


class HotelFacadeTest {

    private lateinit var hotel: HotelFacade

    @BeforeEach
    fun setup() {
        hotel = HotelFacade()
    }

    @Test
    fun `Test room with cheapest price`() {
        val rooms = hotel.findRoomsByHotel(Hotels.PARADISE, LocalDate.of(2020, 8, 1), LocalDate.of(2020, 8, 30))
        val roomToBook = rooms[0]
        assertEquals(roomToBook.id, 5)
        assertEquals(roomToBook.free, true)
        assertEquals(roomToBook.price, 64.00)

        val roomBooked = hotel.bookRoom(Hotels.PARADISE, roomToBook)
        assertEquals(roomBooked.id, 5)
        assertEquals(roomBooked.free, false)
        assertEquals(roomBooked.price, 64.00)
    }

    @Test
    fun `Test room with sea view`() {
        val rooms = hotel.findRoomsByHotel(Hotels.PARADISE, LocalDate.of(2020, 8, 1), LocalDate.of(2020, 8, 3))
        val roomToBook = rooms[0]
        assertEquals(roomToBook.id, 1)
        assertEquals(roomToBook.free, true)
        assertEquals(roomToBook.price, 80.00)

        val roomBooked = hotel.bookRoom(Hotels.PARADISE, roomToBook)
        assertEquals(roomBooked.id, 1)
        assertEquals(roomBooked.free, false)
        assertEquals(roomBooked.price, 80.00)
    }

    @Test
    fun `Test not able to book room due to covid-19`() {
        val thrown: RuntimeException = assertThrows(
            RuntimeException::class.java
        ) {
            val rooms = hotel.findRoomsByHotel(Hotels.PARADISE, LocalDate.of(2020, 5, 1), LocalDate.of(2020, 5, 1))
            val room = rooms[5]
            assertEquals(room.id, 6)
            hotel.bookRoom(Hotels.PARADISE, room)

        }
        assertTrue(thrown.message.equals(HotelParadise.closedMsg))
    }


    @Test
    fun `Test book room twice`() {
        val thrown: RuntimeException = assertThrows(
            RuntimeException::class.java
        ) {
            val rooms = hotel.findRoomsByHotel(Hotels.PARADISE, LocalDate.of(2020, 8, 1), LocalDate.of(2020, 8, 5))
            val room = rooms[1]
            assertEquals(room.id, 2)
            val roomBooked = hotel.bookRoom(Hotels.PARADISE, room)
            assertEquals(roomBooked.id, 2)
            //should throw an exception
            hotel.bookRoom(Hotels.PARADISE, room)

        }
        assertTrue(thrown.message.equals(HotelParadise.roomNotAvailMsg));
    }

    @Test
    fun `Test average of reviews`() {
        assertEquals(3.0, hotel.showAvgReview(Hotels.PARADISE))
    }

    @Test
    fun `Test add review and test average`() {
        hotel.addReview(Hotels.PARADISE, 3.0)
        assertEquals(3.0, hotel.showAvgReview(Hotels.PARADISE))

        hotel.addReview(Hotels.PARADISE, 1.0)
        assertEquals(2.7142857142857144, hotel.showAvgReview(Hotels.PARADISE))
    }
}