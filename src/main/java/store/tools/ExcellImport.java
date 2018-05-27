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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Vadim Popov.
 * PopoWadim@yandex.ru
 **/
public class ExcellImport {
    public static List<ProductDTO> createProducts(File file) throws IOException {
        FileInputStream excelFile = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = datatypeSheet.iterator();
        List<ProductDTO> productList = new ArrayList<>();
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();
            ProductDTO product = new ProductDTO();
            String name = null;
            try {
                 name = cellIterator.next().getStringCellValue();
            } catch (NoSuchElementException e){
                return productList;
            }
            product.setName(name);
            product.setPrice(cellIterator.next().getNumericCellValue());
            product.setStockQuantity((int) cellIterator.next().getNumericCellValue());

            ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
            productCategoryDTO.setName(cellIterator.next().getStringCellValue());
            product.setProductCategoryDTO(productCategoryDTO);

            ProductVendorDTO productVendorDTO = new ProductVendorDTO();
            productVendorDTO.setName(cellIterator.next().getStringCellValue());
            product.setProductVendorDTO(productVendorDTO);

            product.setWeight(cellIterator.next().getNumericCellValue());
            product.setVolume(cellIterator.next().getNumericCellValue());
            product.setColor(cellIterator.next().getStringCellValue());
            product.setPower(cellIterator.next().getNumericCellValue());
            product.setDescription(cellIterator.next().getStringCellValue());
            product.setImagePath(cellIterator.next().getStringCellValue());
            productList.add(product);
        }
        return productList;
    }
}
