package vn.hoidanit.laptopshop.service;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // viết logic vào đây
        vn.hoidanit.laptopshop.domain.User user = this.userService.getUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        // cần phải trả ra UserDetails, tuy nhiên Spring có một class User kế thừa lại
        // UserDetails, nên chỉ cần trả ra thằng con, java sẽ tự ép kiểu về UserDetails
        // => tính đa hình
        return new User(
                user.getEmail(),
                user.getPassword(),
                // phải thêm tiền tố "ROLE_" để qua phần phân quyền spring sẽ tự bỏ đi, nếu
                // không thêm => mâu thuẫn logic
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName())));
    }
}
