package vn.hoidanit.laptopshop.controller.admin;

import java.io.File;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;

@Controller
public class UserController {
    // DI: dependency injection
    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;
    private final ServletContext servletContext;

    public UserController(UserService userService, UploadService uploadService, PasswordEncoder passwordEncoder,
            ServletContext servletContext) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
        this.servletContext = servletContext;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        String test = this.userService.handleHello();
        model.addAttribute("eric", "test");
        model.addAttribute("hoidanit", "from controller with model");
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users1", users);
        return "admin/user/show";
    }

    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        User user = this.userService.getUserByID(id);
        model.addAttribute("user", user);
        return "admin/user/detail";
    }

    @GetMapping("/admin/user/create") // GET
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping(value = "/admin/user/create")
    public String createUserPage(Model model,
            @ModelAttribute("newUser") @Valid User hoidanit,
            BindingResult newUserBindingResult,
            @RequestParam("hoidanitFile") MultipartFile file) {
        List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getField() + " - " + error.getDefaultMessage());
        }
        // validate
        if (newUserBindingResult.hasErrors()) {
            // không dùng redirect vì khi submit có lỗi nó sẽ tự reset và mất hết input vừa
            // nhập
            return "admin/user/create";
        }
        String hashPassword = this.passwordEncoder.encode(hoidanit.getPassword());
        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
        hoidanit.setAvatar(avatar);
        hoidanit.setPassword(hashPassword);
        hoidanit.setRole(this.userService.getRoleByName(hoidanit.getRole().getName()));
        this.userService.handleSaveUser(hoidanit);
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/update/{id}") // GET
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User currentUser = this.userService.getUserByID(id);
        model.addAttribute("id", id);
        model.addAttribute("newUser", currentUser);
        return "admin/user/update";
    }

    @PostMapping("admin/user/update/{id}")
    public String postUpdateUser(Model model,
            @ModelAttribute("newUser") @Valid User hoidanit,
            BindingResult newUserBindingResult,
            @PathVariable long id,
            @RequestParam("hoidanitFile") MultipartFile file) {
        List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getField() + " - " + error.getDefaultMessage());
        }
        // validate
        if (newUserBindingResult.hasErrors()) {
            // không dùng redirect vì khi submit có lỗi nó sẽ tự reset và mất hết input vừa
            // nhập
            return "admin/user/update";
        }
        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
        User currentUser = this.userService.getUserByID(hoidanit.getId());
        if (currentUser != null) {

            currentUser.setAddress(hoidanit.getAddress());
            currentUser.setFullName(hoidanit.getFullName());
            currentUser.setPhone(hoidanit.getPhone());
            if (avatar != null && !avatar.trim().isEmpty()) {
                String oldAvatar = currentUser.getAvatar();
                if (oldAvatar != null && !oldAvatar.trim().isEmpty()) {
                    String avatarPath = this.servletContext.getRealPath("/resources/images/avatar/") + oldAvatar;
                    // trỏ đến avar cũ
                    File oldFile = new File(avatarPath);
                    if (oldFile.exists()) {
                        boolean deleted = oldFile.delete();
                        if (!deleted) {
                            System.err.println("Không thể xóa file avatar cũ: " + avatarPath);
                        }
                    }
                }
                // avatar không null, không rỗng, và không chứa toàn khoảng trắng
                currentUser.setAvatar(avatar);
            }
            currentUser.setRole(this.userService.getRoleByName(hoidanit.getRole().getName()));
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        User currentUser = this.userService.getUserByID(id);
        model.addAttribute("currentUser", currentUser);
        return "admin/user/delete";
    }

    @PostMapping("admin/user/delete")
    public String postDeleteUser(Model model,
            @ModelAttribute("currentUser") User hoidanit) {
        User currentUser = this.userService.getUserByID(hoidanit.getId());
        if (currentUser != null) {
            String oldAvatar = currentUser.getAvatar();
            if (oldAvatar != null && !oldAvatar.trim().isEmpty()) {
                String avatarPath = this.servletContext.getRealPath("/resources/images/avatar/") + oldAvatar;
                // trỏ đến avar cũ
                File oldFile = new File(avatarPath);
                if (oldFile.exists()) {
                    boolean deleted = oldFile.delete();
                    if (!deleted) {
                        System.err.println("Không thể xóa file avatar cũ: " + avatarPath);
                    }
                }
            }
            this.userService.deleteUserByID(currentUser.getId());
        }
        return "redirect:/admin/user";
    }
}
