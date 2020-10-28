package inside

import java.time.LocalDate

interface FancyHotels {
    fun findRooms(from: LocalDate, to: LocalDate): List<Room>
    fun bookRoom(room: Room): Room
    fun addReview(review: Double)
    fun showAvgReview(): Double
}