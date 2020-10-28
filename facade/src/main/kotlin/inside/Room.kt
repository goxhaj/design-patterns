package inside

import java.time.LocalDate

class Room(
    var id: Long,
    var name: String,
    var date: LocalDate?,
    var free: Boolean,
    var seaView: Boolean,
    var price: Double
)
