package controller;

import model.dto.ProductDto;
import model.entity.Product;
import model.service.ProductService;
import model.service.ProductServiceImpl;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ProductController {
    private final ProductService productService = new ProductServiceImpl();

    public void getAllProducts() {
        List<ProductDto> productDtoList = productService.getAllProducts();

        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        table.setColumnWidth(0, 10, 20);
        table.setColumnWidth(1, 20, 40);
        table.setColumnWidth(2, 30, 50);
        table.setColumnWidth(3, 40, 60);
        table.setColumnWidth(4, 30, 50);

        table.addCell("ID", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Product Code", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Product Name", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Product Description", new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell("Expired Date", new CellStyle(CellStyle.HorizontalAlign.CENTER));

        for (ProductDto productDto : productDtoList) {
            table.addCell(String.valueOf(productDto.id()), new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(productDto.productCode(), new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(productDto.productName(), new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(productDto.productDescription(), new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(String.valueOf(productDto.expiredDate()), new CellStyle(CellStyle.HorizontalAlign.CENTER));

        }

        System.out.println(table.render());
    }

    public void addNewProduct() {
        System.out.print("🛍🏷️ Insert Product Code: ");
        String pCode = new Scanner(System.in).next();
        System.out.print("🧋 Insert Product Name: ");
        String pName = new Scanner(System.in).next();
        System.out.print("📜 Insert Product Description: ");
        String pDescription = new Scanner(System.in).next();
        System.out.print("📅 Insert Product Expired Year: ");
        Integer pExpiredYear = new Scanner(System.in).nextInt();
        System.out.print("📅 Insert Product Expired Month: ");
        Integer pExpiredMonth = new Scanner(System.in).nextInt();
        System.out.print("📅 Insert Product Expired Day: ");
        Integer pExpiredDay = new Scanner(System.in).nextInt();

        productService.addProduct(new Product(
                null,pName,pCode,false, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.of(pExpiredYear,pExpiredMonth,pExpiredDay)),pDescription
        ));
    }

    public void deleteProduct() {
        System.out.print("🆔 Delete Product By ID: ");
        int productId = new Scanner(System.in).nextInt();
        productService.deleteProduct(productId);
    }

    public void updateProduct() {
        System.out.println("🛍️ Update Product By ID: ");
        int productId = new Scanner(System.in).nextInt();
        productService.updateProduct(productId);
    }

}
