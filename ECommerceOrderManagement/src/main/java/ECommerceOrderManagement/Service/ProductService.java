package ECommerceOrderManagement.Service;

import ECommerceOrderManagement.Dto.ProductRequestDto;
import ECommerceOrderManagement.Dto.ProductResponseDto;
import ECommerceOrderManagement.Dto.ProductUpdateRequestDto;
import ECommerceOrderManagement.Entity.Product;
import ECommerceOrderManagement.Exception.ProductNotFoundException;
import ECommerceOrderManagement.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponseDto> getAllProductList(){
        List<Product> products= productRepository.findAll();
        return products.stream().map(this::MapToDto).toList();
    }

    public ProductResponseDto getById(int id){
        Product p= productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException(id));

        return MapToDto(p);
    }

    public List<ProductResponseDto> getByCategory(String category){
        List<Product> products= productRepository.getByCategory(category);
        return products.stream().map(this::MapToDto).toList();
    }

    public List<ProductResponseDto> getByProductName(String productName){
       List<Product> products= productRepository.getByProductName(productName);
       return products.stream().map(this::MapToDto).toList();
    }

    // add product
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto){
        Product p= MapToEntity(productRequestDto);
         productRepository.save(p);

         return MapToDto(p);
    }

    //update product
    public ProductResponseDto updateProduct(ProductUpdateRequestDto productUpdateRequestDto, int id){
        Product p= productRepository.findById(id).orElseThrow(()->new ProductNotFoundException(id));
        p.setProductName(productUpdateRequestDto.getProductName());
        p.setPrice(productUpdateRequestDto.getPrice());
        p.setQuantity(productUpdateRequestDto.getQuantity());
        productRepository.save(p);

        ProductResponseDto updatedProduct=MapToDto(p);
        return updatedProduct;
    }

    public String deleteProduct(int id){
       Product p= productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException(id));
         productRepository.delete(p);
         return "Product deleted";
    }

    // Map to Entity
    public Product MapToEntity(ProductRequestDto productRequestDto){
        Product p= new Product();
        p.setProductName(productRequestDto.getProductName());
        p.setCategory(productRequestDto.getCategory());
        p.setPrice(productRequestDto.getPrice());
        p.setQuantity(productRequestDto.getQuantity());
        return p;
    }

    // Map to dto
    public ProductResponseDto MapToDto(Product product){
        ProductResponseDto productResponseDto= new ProductResponseDto();
        productResponseDto.setProductId(product.getProductId());
        productResponseDto.setProductName(product.getProductName());
        productResponseDto.setCategory(product.getCategory());
        productResponseDto.setPrice(product.getPrice());

        return productResponseDto;
    }


}
