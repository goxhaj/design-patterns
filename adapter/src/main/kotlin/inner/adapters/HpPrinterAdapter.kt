package inner.adapters

import inner.Printer
import outside.HpPrinter

class HpPrinterAdapter: Printer {

    val hpPrinter: HpPrinter = HpPrinter("Colibri", 400, 200)




    override fun print():String {
        return hpPrinter.fancyPrintHp()
    }
}