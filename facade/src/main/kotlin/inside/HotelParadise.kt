package inside


import java.time.LocalDate
import java.util.stream.Collectors

class HotelParadise : FancyHotels {
    val promotions = Promotions()

    //open July-September
    var openFrom: LocalDate = LocalDate.of(2020, 6, 1)
    var openTo: LocalDate = LocalDate.of(2020, 9, 1)

    var avg: Double = 0.0


    companion object {
        const val roomNotAvailMsg = "Room is not available"
        const val closedMsg: String = "Hotel is closed due to covid-19"
    }

    var reviews = mutableListOf(
        Reviews(5.0),
        Reviews(4.0),
        Reviews(3.0),
        Reviews(2.0),
        Reviews(1.0)
    )


    //db data :)
    var rooms = mutableListOf(
        Room(1, "Room 01", LocalDate.of(2020, 8, 2), true, true, 100.0),
        Room(2, "Room 02", LocalDate.of(2020, 8, 3), true, true, 120.0),
        Room(3, "Room 03", LocalDate.of(2020, 8, 4), true, true, 150.0),
        Room(4, "Room 04", LocalDate.of(2020, 8, 5), true, true, 180.0),
        Room(5, "Room 05", LocalDate.of(2020, 8, 6), true, true, 80.0),//cheapest price
        Room(6, "Room 06", LocalDate.of(2020, 5, 2), true, true, 80.0),//not able to book due to covid-19
        Room(7, "Room 07", LocalDate.now(), false, false, 100.0),
        Room(8, "Room 08", LocalDate.now(), false, false, 100.0),
        Room(9, "Room 09", LocalDate.now(), false, false, 100.0),
        Room(10, "Room 10", LocalDate.now(), false, false, 60.0),
        Room(11, "Room 11", LocalDate.now(), false, false, 50.0),
        Room(12, "Room 12", LocalDate.now(), false, false, 80.0),
        Room(13, "Room 13", LocalDate.now(), false, false, 40.0),
        Room(14, "Room 14", LocalDate.now(), false, false, 55.0),
        Room(15, "Room 15", LocalDate.now(), false, false, 700.0),
    )


    override fun findRooms(from: LocalDate, to: LocalDate): List<Room> {
        if (!(shouldAcceptBookingCovid19(from) && shouldAcceptBookingCovid19(to)))
            return throw  RuntimeException(closedMsg)

        return rooms.stream().filter { rm ->
            rm.free && rm.date!!.isAfter(from) && rm.date!!.isBefore(to)
        }
            .map { promotions.applyDiscount(it) }.collect(Collectors.toList()).sortedBy { room: Room -> room.price }
    }

    override fun bookRoom(room: Room): Room {
        return if (isRoomAvailable(room) && shouldAcceptBookingCovid19(room.date)) {
            room.free = false
            room
        } else throw RuntimeException(roomNotAvailMsg)
    }

    override fun showAvgReview(): Double {
        calculateAvgRate()
        return avg
    }

    override fun addReview(review: Double) {
        val review = Reviews(review)
        reviews.add(review)
        calculateAvgRate()
    }


    fun calculateAvgRate(): Unit {
        avg = reviews.map { it.review }.average()
    }

    //other methods
    private fun isRoomAvailable(room: Room): Boolean {
        return rooms.any { rm -> rm.id == room.id && rm.free }
    }


    private fun shouldAcceptBookingCovid19(bookingDate: LocalDate?): Boolean {
        return if (bookingDate != null) {
            bookingDate.isAfter(openFrom) && bookingDate.isBefore(openTo)
        } else return false
    }

//many many more methods checking room business logic
}