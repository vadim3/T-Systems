package store.tools;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import store.dto.ProductCategoryDTO;
import store.dto.ProductDTO;
import store.dto.ProductVendorDTO;
import store.entities.Product;
import store.entities.ProductVendor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public class ExcellImport {
    public static void createProducts(File file){
        try {
            FileInputStream excelFile = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                ProductDTO product = new ProductDTO();
                product.setName(cellIterator.next().getStringCellValue());
                product.setPrice(cellIterator.next().getNumericCellValue());
                product.setStockQuantity((int) cellIterator.next().getNumericCellValue());
                ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
                productCategoryDTO.setName(cellIterator.next().getStringCellValue());
                product.setProductCategoryDTO(productCategoryDTO);
                ProductVendorDTO productVendorDTO = new ProductVendorDTO();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        System.out.print(currentCell.getStringCellValue() + "--");
                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        System.out.print(currentCell.getNumericCellValue() + "--");
                    }

                }
                System.out.println();

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
