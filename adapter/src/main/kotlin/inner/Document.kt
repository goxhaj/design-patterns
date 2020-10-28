package inner

class Document(var printer: Printer) {
    fun print():String {
        return printer.print()
    }
}
