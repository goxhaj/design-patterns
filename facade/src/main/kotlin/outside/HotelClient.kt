package outside

import inside.HotelFacade
import inside.Hotels
import inside.Room
import java.time.LocalDate

class HotelClient {

    var hotelFacade = HotelFacade()

    fun findRooms(from:LocalDate, to:LocalDate): List<Room>{
        return hotelFacade.findRooms(from, to)
    }

    fun findRoomsHotelPanama(from:LocalDate, to:LocalDate): List<Room>{
        return hotelFacade.findRoomsByHotel(Hotels.PANAMA, from, to)
    }
}