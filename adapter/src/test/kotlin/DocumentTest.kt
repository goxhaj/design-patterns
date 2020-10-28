import inner.adapters.HpPrinterAdapter
import inner.adapters.XeroxPrinterAdapter
import inner.Document
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class DocumentTest {

    @Test
    fun `Test Hp Printer Adapter`() {
        val doc = Document(HpPrinterAdapter())
        Assertions.assertEquals(
            "Print from Hp fancy printer with better font: Colibri, width: 400, height: 200",
            doc.print()
        )
    }

    @Test
    fun `Test Xerox Printer Adapter`() {
        val doc = Document(XeroxPrinterAdapter())
        Assertions.assertEquals("Print from Xerox fancy printer with better spacing: FancySpace", doc.print())
    }
}