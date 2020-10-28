package inside

import java.time.LocalDate

class HotelFacade {

    private val hotel = mutableListOf<FancyHotels>()
    private var rooms = mutableListOf<Room>()

    constructor() {
        hotel.add(HotelPanama())
        hotel.add(HotelParadise())
        //add other hotels if needed
    }

    fun findRooms(from: LocalDate, to: LocalDate): List<Room> {
        hotel.forEach { rooms.addAll(it.findRooms(from, to)) }
        return rooms.sortedBy { room: Room -> room.price }
    }

    fun findRoomsByHotel(hotelName: Hotels, from: LocalDate, to: LocalDate): List<Room> {
        return when (hotelName) {
            Hotels.PANAMA -> hotel[0].findRooms(from, to)
            Hotels.PARADISE -> hotel[1].findRooms(from, to)

        }
    }

    fun bookRoom(hotelName: Hotels, room: Room): Room {
        return when (hotelName) {
            Hotels.PANAMA -> hotel[0].bookRoom(room)
            Hotels.PARADISE -> hotel[1].bookRoom(room)
        }
    }

    fun addReview(hotelName: Hotels, review: Double) {
        return when (hotelName) {
            Hotels.PANAMA -> hotel[0].addReview(review)
            Hotels.PARADISE -> hotel[1].addReview(review)
        }

    }

    fun showAvgReview(hotelName: Hotels): Double {
        return when (hotelName) {
            Hotels.PANAMA -> hotel[0].showAvgReview()
            Hotels.PARADISE -> hotel[1].showAvgReview()
        }
    }

}