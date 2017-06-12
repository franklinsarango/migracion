/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.arcom.migracion.alfresco.util;

import ec.gob.arcom.migracion.alfresco.util.AlfrescoFileUtil;
import java.io.File;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.CTBorder;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.STBorder;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.TblBorders;
import org.docx4j.wml.TblPr;
import org.docx4j.wml.Tc;
import org.docx4j.wml.Tr;

/**
 *
 * @author Roddy Arana - Latinus
 */
public class WordUtil implements Serializable {

    private static WordprocessingMLPackage wordMLPackage;
    private static ObjectFactory factory;

    static {
        try {
            wordMLPackage = WordprocessingMLPackage.createPackage();
            factory = Context.getWmlObjectFactory();
        } catch (Exception ex) {
            Logger.getLogger(AlfrescoFileUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param filename
     * @param title
     * @param paragraphList
     * @return
     */
    @SuppressWarnings("UseSpecificCatch")
    public static File newWordDoc(String filename, String title, List<String> paragraphList) {
        File tempFile = null;

        try {
            tempFile = File.createTempFile(filename, ".doc");

            wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Title", title);

            for (String paragraph : paragraphList) {
                wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Normal", paragraph);
            }

            wordMLPackage.save(tempFile);

        } catch (Exception ex) {
            Logger.getLogger(AlfrescoFileUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tempFile;
    }

    /**
     *
     * @param filename
     * @param title
     * @param paragraphList
     * @param tablaList
     * @return
     */
    @SuppressWarnings("UseSpecificCatch")
    public static File newWordDocWithTable(String filename, String title, List<String> paragraphList, List<Tbl> tablaList) {
        File tempFile = null;

        try {
            tempFile = File.createTempFile(filename, ".doc");

            wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Title", title);
            // Parrafos
            for (String paragraph : paragraphList) {
                wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Normal", paragraph);
            }
            // Tablas
            for (Tbl tabla : tablaList) {
                wordMLPackage.getMainDocumentPart().addObject(tabla);
            }
            wordMLPackage.save(tempFile);

        } catch (Exception ex) {
            Logger.getLogger(AlfrescoFileUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tempFile;
    }

    private static void addBorders(Tbl table) {
        table.setTblPr(new TblPr());
        CTBorder border = new CTBorder();
        border.setColor("auto");
        border.setSz(new BigInteger("4"));
        border.setSpace(new BigInteger("0"));
        border.setVal(STBorder.SINGLE);

        TblBorders borders = new TblBorders();
        borders.setBottom(border);
        borders.setLeft(border);
        borders.setRight(border);
        borders.setTop(border);
        borders.setInsideH(border);
        borders.setInsideV(border);
        table.getTblPr().setTblBorders(borders);
    }

    public static Tbl createTableWithContent(List< List<String>> tabla) {

        Tbl table = factory.createTbl();

        for (List<String> fila : tabla) {
            Tr dataCell = factory.createTr();
            for (String data : fila) {
                addTableCell(dataCell, data);
            }
            table.getContent().add(dataCell);
        }
        addBorders(table);
        return table;
    }

    private static void addTableCell(Tr tableRow, String content) {
        try {
            Tc tableCell = factory.createTc();
            tableCell.getContent().add(
                    wordMLPackage.getMainDocumentPart().
                    createParagraphOfText(content));
            tableRow.getContent().add(tableCell);
        } catch (Exception ex) {
            Logger.getLogger(AlfrescoFileUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
