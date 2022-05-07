package example.service.impl;

import example.model.entity.UserEntity;
import example.model.entity.UserRole;
import example.model.entity.UserRoleEntity;
import example.model.service.UserServiceModel;
import example.repository.UserRepository;
import example.repository.UserRoleRepository;
import example.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;
    private final SongDBUserService songDBUserService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository, SongDBUserService songDBUserService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.songDBUserService = songDBUserService;
    }

    @Override
    public boolean userExist(String username) {
        if (userRepository.findByUsername(username).isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public void registerAndLogin(UserServiceModel userServiceModel) {
        UserEntity newUser = modelMapper.map(userServiceModel, UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));

        UserRoleEntity userRole = userRoleRepository.findByRole(UserRole.USER).orElseThrow(
                () -> new IllegalStateException("USER role not found. Please seed the roles")
        );

        newUser.addRole(userRole);

        newUser = userRepository.save(newUser);

        UserDetails principal = songDBUserService.loadUserByUsername(newUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public void seedUsers() {
        if (userRepository.count() == 0){
            UserRoleEntity adminRole =new UserRoleEntity().setRole(UserRole.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRole.USER);

            userRoleRepository.saveAll(List.of(adminRole, userRole));

            UserEntity admin = new UserEntity().setUsername("admin").setPassword(passwordEncoder.encode("topsecret"));
            UserEntity user = new UserEntity().setUsername("user").setPassword(passwordEncoder.encode("topsecret"));

            admin.setRoles(List.of(adminRole, userRole));
            user.setRoles(List.of(userRole));

            userRepository.saveAll(List.of(admin, user));
        }
    }

    @Override
    public UserEntity findByName(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(IllegalArgumentException::new);
    }
}
