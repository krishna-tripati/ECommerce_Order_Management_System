package ECommerceOrderManagement.Controller;

import ECommerceOrderManagement.Dto.ProductRequestDto;
import ECommerceOrderManagement.Dto.ProductResponseDto;
import ECommerceOrderManagement.Dto.ProductUpdateRequestDto;
import ECommerceOrderManagement.Dto.ResponseProductDto;
import ECommerceOrderManagement.Entity.Product;
import ECommerceOrderManagement.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/products/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

   //get all products
    @GetMapping
   public ResponseEntity<List<ProductResponseDto>> getAllProductList(){
        List<ProductResponseDto> response= productService.getAllProductList();
        return ResponseEntity.ok(response);
   }

   //find by id
    @GetMapping("/id/{id}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable ("id") int id){
        ProductResponseDto response=productService.getById(id);
        return ResponseEntity.ok(response);
    }

    //get by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponseDto>> getByCategory(@PathVariable ("category") String category){
        List<ProductResponseDto> response= productService.getByCategory(category);
        return ResponseEntity.ok(response);
    }

    //get by product Name
    @GetMapping("/name/{productName}")
    public ResponseEntity<List<ProductResponseDto>> getByProductName(@PathVariable ("productName") String productName){
        List<ProductResponseDto> response= productService.getByProductName(productName);
        return ResponseEntity.ok(response);
    }

  //add product
    @PostMapping
    public ResponseEntity<ProductResponseDto> addProduct(@Valid @RequestBody ProductRequestDto productRequestDto){

       ProductResponseDto productResponseDto= productService.addProduct(productRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseDto);
  }


  //update data
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@Valid @RequestBody ProductUpdateRequestDto updateRequestDto,
                                          @PathVariable ("id") int id){
        ProductResponseDto updatedProduct= productService.updateProduct(updateRequestDto, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedProduct);
    }

    //delete by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>  deleteProduct (@PathVariable ("id") int id){
        String msg= productService.deleteProduct(id);
        return ResponseEntity.ok(msg);
    }


}
