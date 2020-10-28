package inside

import java.time.LocalDate

class Promotions {

    //some complex business logic
    var openFrom: LocalDate = LocalDate.of(2020, 8, 1)
    var openTo: LocalDate = LocalDate.of(2020, 9, 1)

    fun applyDiscount(room: Room): Room {
        if (room.date!!.isAfter(openFrom) && room.date!!.isBefore(openTo)) {
            val discount = 0.20
            room.price -= (room.price * discount)
        }
        return room
    }
}