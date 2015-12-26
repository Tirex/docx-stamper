package org.wickedsource.docxstamper.walk;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Assert;
import org.junit.Test;
import org.wickedsource.docxstamper.poi.RunAggregator;
import org.wickedsource.docxstamper.poi.coordinates.ParagraphCoordinates;
import org.wickedsource.docxstamper.poi.coordinates.TableCellCoordinates;
import org.wickedsource.docxstamper.poi.coordinates.TableCoordinates;
import org.wickedsource.docxstamper.poi.coordinates.TableRowCoordinates;
import org.wickedsource.docxstamper.poi.walk.DocumentWalker;

import java.io.IOException;
import java.io.InputStream;

public class DocumentWalkerTest {

    @Test
    public void calculatesCorrectNestedCoordinates() throws IOException {

        InputStream in = getClass().getResourceAsStream("walker.docx");
        XWPFDocument document = new XWPFDocument(in);

        final Counter paragraphCount = new Counter();
        final Counter tableCount = new Counter();
        final Counter rowCount = new Counter();
        final Counter cellCount = new Counter();

        DocumentWalker walker = new DocumentWalker(document) {


            @Override
            protected void onParagraph(ParagraphCoordinates paragraphCoordinates) {
                paragraphCount.increment();
                switch (paragraphCount.getCount()) {
                    case 1:
                        Assert.assertEquals(0, paragraphCoordinates.getIndex());
                        Assert.assertEquals("The coordinates of this paragraph are: index 0.", new RunAggregator(paragraphCoordinates.getParagraph()).getText());
                        Assert.assertNull(paragraphCoordinates.getParentTableCellCoordinates());
                        break;
                    case 2:
                        Assert.assertEquals(1, paragraphCoordinates.getIndex());
                        Assert.assertEquals("The coordinates of this paragraph are: index 1.", new RunAggregator(paragraphCoordinates.getParagraph()).getText());
                        Assert.assertNull(paragraphCoordinates.getParentTableCellCoordinates());
                        break;
                    case 3:
                        Assert.assertEquals(0, paragraphCoordinates.getIndex());
                        Assert.assertEquals("The coordinates of this paragraph are: table 2, row 0, cell 0, index 0.", new RunAggregator(paragraphCoordinates.getParagraph()).getText());
                        Assert.assertEquals(0, paragraphCoordinates.getParentTableCellCoordinates().getIndex());
                        Assert.assertEquals(0, paragraphCoordinates.getParentTableCellCoordinates().getParentTableRowCoordinates().getIndex());
                        Assert.assertEquals(2, paragraphCoordinates.getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getIndex());
                        Assert.assertNull(paragraphCoordinates.getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates());
                        break;
                    case 4:
                        Assert.assertEquals(1, paragraphCoordinates.getIndex());
                        Assert.assertEquals("The coordinates of this paragraph are: table 2, row 0, cell 0, index 1.", new RunAggregator(paragraphCoordinates.getParagraph()).getText());
                        Assert.assertEquals(0, paragraphCoordinates.getParentTableCellCoordinates().getIndex());
                        Assert.assertEquals(0, paragraphCoordinates.getParentTableCellCoordinates().getParentTableRowCoordinates().getIndex());
                        Assert.assertEquals(2, paragraphCoordinates.getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getIndex());
                        Assert.assertNull(paragraphCoordinates.getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates());
                        break;
                    case 5:
                        Assert.assertEquals(0, paragraphCoordinates.getIndex());
                        Assert.assertEquals("The coordinates of this paragraph are: table 2, row 1, cell 0, table 0, row 0, cell 0, index 0.", new RunAggregator(paragraphCoordinates.getParagraph()).getText());
                        Assert.assertEquals(0, paragraphCoordinates.getParentTableCellCoordinates().getIndex());
                        Assert.assertEquals(0, paragraphCoordinates.getParentTableCellCoordinates().getParentTableRowCoordinates().getIndex());
                        Assert.assertEquals(0, paragraphCoordinates.getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getIndex());
                        Assert.assertEquals(0, paragraphCoordinates.getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates().getIndex());
                        Assert.assertEquals(1, paragraphCoordinates.getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates().getParentTableRowCoordinates().getIndex());
                        Assert.assertEquals(2, paragraphCoordinates.getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getIndex());
                        Assert.assertNull(paragraphCoordinates.getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates());
                        break;
                    case 6:
                        Assert.assertEquals(0, paragraphCoordinates.getIndex());
                        Assert.assertEquals("The coordinates of this paragraph are: table 2, row 1, cell 0, table 0, row 1, cell 0, index 0.", new RunAggregator(paragraphCoordinates.getParagraph()).getText());
                        Assert.assertEquals(0, paragraphCoordinates.getParentTableCellCoordinates().getIndex());
                        Assert.assertEquals(1, paragraphCoordinates.getParentTableCellCoordinates().getParentTableRowCoordinates().getIndex());
                        Assert.assertEquals(0, paragraphCoordinates.getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getIndex());
                        Assert.assertEquals(0, paragraphCoordinates.getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates().getIndex());
                        Assert.assertEquals(1, paragraphCoordinates.getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates().getParentTableRowCoordinates().getIndex());
                        Assert.assertEquals(2, paragraphCoordinates.getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getIndex());
                        Assert.assertNull(paragraphCoordinates.getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates());
                        break;
                    case 7:
                        Assert.assertEquals(1, paragraphCoordinates.getIndex());
                        Assert.assertEquals("The coordinates of this paragraph are table 2, row 1, cell 0, index 1.", new RunAggregator(paragraphCoordinates.getParagraph()).getText());
                        Assert.assertEquals(0, paragraphCoordinates.getParentTableCellCoordinates().getIndex());
                        Assert.assertEquals(1, paragraphCoordinates.getParentTableCellCoordinates().getParentTableRowCoordinates().getIndex());
                        Assert.assertEquals(2, paragraphCoordinates.getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getIndex());
                        Assert.assertNull(paragraphCoordinates.getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates());
                        break;
                    case 8:
                        Assert.assertEquals(3, paragraphCoordinates.getIndex());
                        Assert.assertEquals("The coordinates of this paragraph are index 3.", new RunAggregator(paragraphCoordinates.getParagraph()).getText());
                        Assert.assertNull(paragraphCoordinates.getParentTableCellCoordinates());
                        break;
                }
            }

            @Override
            protected void onTable(TableCoordinates tableCoordinates) {
                tableCount.increment();
                switch (tableCount.getCount()) {
                    case 1:
                        Assert.assertEquals(2, tableCoordinates.getIndex());
                        Assert.assertNull(tableCoordinates.getParentTableCellCoordinates());
                        break;
                    case 2:
                        Assert.assertEquals(0, tableCoordinates.getIndex());
                        Assert.assertEquals(0, tableCoordinates.getParentTableCellCoordinates().getIndex());
                        Assert.assertEquals(1, tableCoordinates.getParentTableCellCoordinates().getParentTableRowCoordinates().getIndex());
                        Assert.assertEquals(2, tableCoordinates.getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getIndex());
                        Assert.assertNull(tableCoordinates.getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates());
                        break;
                }
            }

            @Override
            protected void onTableRow(TableRowCoordinates tableRowCoordinates) {
                rowCount.increment();
                switch (rowCount.getCount()) {
                    case 1:
                        Assert.assertEquals(0, tableRowCoordinates.getIndex());
                        Assert.assertEquals(2, tableRowCoordinates.getParentTableCoordinates().getIndex());
                        Assert.assertNull(tableRowCoordinates.getParentTableCoordinates().getParentTableCellCoordinates());
                        break;
                    case 2:
                        Assert.assertEquals(1, tableRowCoordinates.getIndex());
                        Assert.assertEquals(2, tableRowCoordinates.getParentTableCoordinates().getIndex());
                        Assert.assertNull(tableRowCoordinates.getParentTableCoordinates().getParentTableCellCoordinates());
                        break;
                    case 3:
                        Assert.assertEquals(0, tableRowCoordinates.getIndex());
                        Assert.assertEquals(0, tableRowCoordinates.getParentTableCoordinates().getIndex());
                        Assert.assertEquals(0, tableRowCoordinates.getParentTableCoordinates().getParentTableCellCoordinates().getIndex());
                        Assert.assertEquals(1, tableRowCoordinates.getParentTableCoordinates().getParentTableCellCoordinates().getParentTableRowCoordinates().getIndex());
                        Assert.assertEquals(2, tableRowCoordinates.getParentTableCoordinates().getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getIndex());
                        Assert.assertNull(tableRowCoordinates.getParentTableCoordinates().getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates());
                        break;
                    case 4:
                        Assert.assertEquals(1, tableRowCoordinates.getIndex());
                        Assert.assertEquals(0, tableRowCoordinates.getParentTableCoordinates().getIndex());
                        Assert.assertEquals(0, tableRowCoordinates.getParentTableCoordinates().getParentTableCellCoordinates().getIndex());
                        Assert.assertEquals(1, tableRowCoordinates.getParentTableCoordinates().getParentTableCellCoordinates().getParentTableRowCoordinates().getIndex());
                        Assert.assertEquals(2, tableRowCoordinates.getParentTableCoordinates().getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getIndex());
                        Assert.assertNull(tableRowCoordinates.getParentTableCoordinates().getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates());
                        break;

                }
            }

            @Override
            protected void onTableCell(TableCellCoordinates tableCellCoordinates) {
                cellCount.increment();
                switch (cellCount.getCount()) {
                    case 1:
                        Assert.assertEquals(0, tableCellCoordinates.getIndex());
                        Assert.assertEquals(0, tableCellCoordinates.getParentTableRowCoordinates().getIndex());
                        Assert.assertEquals(2, tableCellCoordinates.getParentTableRowCoordinates().getParentTableCoordinates().getIndex());
                        Assert.assertNull(tableCellCoordinates.getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates());
                        break;
                    case 2:
                        Assert.assertEquals(0, tableCellCoordinates.getIndex());
                        Assert.assertEquals(1, tableCellCoordinates.getParentTableRowCoordinates().getIndex());
                        Assert.assertEquals(2, tableCellCoordinates.getParentTableRowCoordinates().getParentTableCoordinates().getIndex());
                        Assert.assertNull(tableCellCoordinates.getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates());
                        break;
                    case 3:
                        Assert.assertEquals(0, tableCellCoordinates.getIndex());
                        Assert.assertEquals(0, tableCellCoordinates.getParentTableRowCoordinates().getIndex());
                        Assert.assertEquals(0, tableCellCoordinates.getParentTableRowCoordinates().getParentTableCoordinates().getIndex());
                        Assert.assertEquals(0, tableCellCoordinates.getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates().getIndex());
                        Assert.assertEquals(1, tableCellCoordinates.getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates().getParentTableRowCoordinates().getIndex());
                        Assert.assertEquals(2, tableCellCoordinates.getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getIndex());
                        Assert.assertNull(tableCellCoordinates.getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates());
                        break;
                    case 4:
                        Assert.assertEquals(0, tableCellCoordinates.getIndex());
                        Assert.assertEquals(1, tableCellCoordinates.getParentTableRowCoordinates().getIndex());
                        Assert.assertEquals(0, tableCellCoordinates.getParentTableRowCoordinates().getParentTableCoordinates().getIndex());
                        Assert.assertEquals(0, tableCellCoordinates.getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates().getIndex());
                        Assert.assertEquals(1, tableCellCoordinates.getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates().getParentTableRowCoordinates().getIndex());
                        Assert.assertEquals(2, tableCellCoordinates.getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getIndex());
                        Assert.assertNull(tableCellCoordinates.getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates().getParentTableRowCoordinates().getParentTableCoordinates().getParentTableCellCoordinates());
                        break;

                }
            }
        };

        walker.walk();
        Assert.assertEquals(8, paragraphCount.getCount());
        Assert.assertEquals(2, tableCount.getCount());
        Assert.assertEquals(4, rowCount.getCount());
        Assert.assertEquals(4, cellCount.getCount());


    }

}