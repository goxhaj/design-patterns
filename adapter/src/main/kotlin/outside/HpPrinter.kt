package outside

class HpPrinter(var betterFont: String, var width: Int, var height: Int) {
    
    fun fancyPrintHp(): String {
        return "Print1 from Hp fancy printer with better font: $betterFont, width: $width, height: $height"
    }
}