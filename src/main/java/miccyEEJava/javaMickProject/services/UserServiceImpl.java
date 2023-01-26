package miccyEEJava.javaMickProject.services;

import miccyEEJava.javaMickProject.model.Roles;
import miccyEEJava.javaMickProject.repository.RoleRepository;
import miccyEEJava.javaMickProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@Service
public class UserServiceImpl implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        miccyEEJava.javaMickProject.model.User user = userRepository.findByEmail(username);
        if(user != null){
            org.springframework.security.core.userdetails.User secUser = new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),user.getRoles());
            return secUser;
        }else {
            throw new UsernameNotFoundException("User not found");
        }


    }

//    @Override
//    public miccyEEJava.javaMickProject.model.User findByEmail(String email) {
//        return userRepository.findByEmail(email);    }

//    @Override
//    public miccyEEJava.javaMickProject.model.User createUser(miccyEEJava.javaMickProject.model.User user){
//        miccyEEJava.javaMickProject.model.User checkUser =userRepository.findByEmail(user.getEmail());
//        if (checkUser==null){
//            Roles role = roleRepository.findByRole("user");
//            if (role !=null){
//                ArrayList<Roles> roles = new ArrayList<>();
//                roles.add(role);
//                user.setRoles(roles);
//                user.setPassword(passwordEncoder.encode(user.getPassword()));
//                return userRepository.save(user);
//            }
//        }
//        return null;
//    }




//    public miccyEEJava.javaMickProject.model.User getUserByEmail(String email) {
//        return userService.getUserByEmail(email);
//    }


//@Override
//    public miccyEEJava.javaMickProject.model.User  getUserByEmail(String email){
//        return userRepository.findByEmail(email);
//    }



}
