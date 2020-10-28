package inner.adapters

import inner.Printer
import outside.XeroxPrinter

class XeroxPrinterAdapter : Printer {

    val xeroxPrinter: XeroxPrinter = XeroxPrinter("FancySpace")

    override fun print(): String {
        return xeroxPrinter.fancyPrintXerox()
    }
}