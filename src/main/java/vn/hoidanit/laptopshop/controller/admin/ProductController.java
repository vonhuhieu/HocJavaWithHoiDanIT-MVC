package vn.hoidanit.laptopshop.controller.admin;

import java.io.File;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ProductController {
    private final UploadService uploadService;
    private final ProductService productService;
    private final ServletContext servletContext;

    public ProductController(UploadService uploadService,
            ProductService productService, ServletContext servletContext) {
        this.uploadService = uploadService;
        this.productService = productService;
        this.servletContext = servletContext;
    }

    @GetMapping("/admin/product")
    public String getProductPage(Model model) {
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin/product/show";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String postCreateProduct(Model model,
            @ModelAttribute("newProduct") @Valid Product newProduct,
            BindingResult newUserBindingResult,
            @RequestParam("productImageFile") MultipartFile file) {
        List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getField() + " - " + error.getDefaultMessage());
        }
        // validate
        if (newUserBindingResult.hasErrors()) {
            // không dùng redirect vì khi submit có lỗi nó sẽ tự reset và mất hết input vừa
            // nhập
            return "admin/product/create";
        }
        String productImageFile = this.uploadService.handleSaveUploadFile(file, "product");
        newProduct.setImage(productImageFile);
        this.productService.handleSaveProduct(newProduct);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/detail/{id}")
    public String getProductDetailPage(Model model,
            @PathVariable long id) {
        model.addAttribute("id", id);
        Product product = this.productService.getProductByID(id);
        model.addAttribute("product", product);
        return "admin/product/detail";
    }

    @GetMapping("/admin/product/update/{id}")
    public String getUpdateProductPage(Model model,
            @PathVariable long id) {
        Product currentProduct = this.productService.getProductByID(id);
        model.addAttribute("id", id);
        model.addAttribute("currentProduct", currentProduct);
        return "admin/product/update";
    }

    @PostMapping("/admin/product/update/{id}")
    public String postUpdateProduct(Model model,
            @ModelAttribute("currentProduct") @Valid Product updateProduct,
            BindingResult newUserBindingResult,
            @PathVariable long id,
            @RequestParam("productImageFile") MultipartFile file) {
        List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getField() + " - " + error.getDefaultMessage());
        }
        // validate
        if (newUserBindingResult.hasErrors()) {
            // không dùng redirect vì khi submit có lỗi nó sẽ tự reset và mất hết input vừa
            // nhập
            return "admin/product/update";
        }
        String productImageFile = this.uploadService.handleSaveUploadFile(file, "product");
        Product currentProduct = this.productService.getProductByID(updateProduct.getId());
        if (currentProduct != null) {
            currentProduct.setDetailDesc(updateProduct.getDetailDesc());
            currentProduct.setTarget(updateProduct.getTarget());
            currentProduct.setFactory(updateProduct.getFactory());
            currentProduct.setShortDesc(updateProduct.getShortDesc());
            currentProduct.setName(updateProduct.getName());
            currentProduct.setPrice(updateProduct.getPrice());
            currentProduct.setQuantity(updateProduct.getQuantity());
            currentProduct.setSold(updateProduct.getSold());
            if (productImageFile != null && !productImageFile.trim().isEmpty()) {
                String oldImage = currentProduct.getImage();
                if (oldImage != null && !oldImage.trim().isEmpty()) {
                    String imagePath = this.servletContext.getRealPath("/resources/images/product/") + oldImage;
                    // trỏ đến avar cũ
                    File oldFile = new File(imagePath);
                    if (oldFile.exists()) {
                        boolean deleted = oldFile.delete();
                        if (!deleted) {
                            System.err.println("Không thể xóa file avatar cũ: " + imagePath);
                        }
                    }
                }
                // avatar không null, không rỗng, và không chứa toàn khoảng trắng
                currentProduct.setImage(productImageFile);
            }
            this.productService.handleSaveProduct(currentProduct);
        }
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        Product currentProduct = this.productService.getProductByID(id);
        model.addAttribute("currentProduct", currentProduct);
        return "admin/product/delete";
    }

    @PostMapping("admin/product/delete")
    public String postDeleteUser(Model model,
            @ModelAttribute("currentProduct") Product deleteProduct) {
        Product currentProduct = this.productService.getProductByID(deleteProduct.getId());
        if (currentProduct != null) {
            String oldImage = currentProduct.getImage();
            if (oldImage != null && !oldImage.trim().isEmpty()) {
                String imagePath = this.servletContext.getRealPath("/resources/images/product/") + oldImage;
                // trỏ đến avar cũ
                File oldFile = new File(imagePath);
                if (oldFile.exists()) {
                    boolean deleted = oldFile.delete();
                    if (!deleted) {
                        System.err.println("Không thể xóa file avatar cũ: " + imagePath);
                    }
                }
            }
            this.productService.deleteProductByID(currentProduct.getId());
        }
        return "redirect:/admin/product";
    }
}
