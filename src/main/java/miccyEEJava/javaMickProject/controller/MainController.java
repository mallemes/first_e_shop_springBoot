package miccyEEJava.javaMickProject.controller;

import miccyEEJava.javaMickProject.db.Category;
import miccyEEJava.javaMickProject.db.CorzItems;
import miccyEEJava.javaMickProject.db.Phone;
import miccyEEJava.javaMickProject.repository.CategoryRepository;
import miccyEEJava.javaMickProject.repository.CorzItemsRepository;
import miccyEEJava.javaMickProject.repository.PhoneRepository;
import miccyEEJava.javaMickProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CorzItemsRepository corzItemsRepository;
    @Autowired
    private UserRepository userRepository;
//    private UserService userService;



    @GetMapping(value = "/")
    public String addItem(Model model){

        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);

        List<Phone> items = phoneRepository.findAll();
        model.addAttribute("additem",items);

        return "index";
    }
    @GetMapping(value = "/shop")
    public String shop(Model model){

        List<Category> categg = categoryRepository.findAll();
        model.addAttribute("catego", categg);

       List<Phone> items = phoneRepository.findAll();
       model.addAttribute("shop", items);


        return "shop";

    }



    @GetMapping(value = "/korzina")
    public String korzPage(Model model){

        List<CorzItems> corzItems = corzItemsRepository.findAll();
        model.addAttribute("korzina",corzItems);
         
        return "korzina";
    }


    @GetMapping(value = "/addphone")
    public String indexPage(Model model){
        List<Category> categor = categoryRepository.findAll();
        model.addAttribute("categor", categor);

        List<Phone> phones = phoneRepository.findAll();
        model.addAttribute("addphone",phones);

        return "addphone";
    }
    @GetMapping(value = "/aplication")

    public String aplication(Model model){
        List<Category> cat = categoryRepository.findAll();
        model.addAttribute("cat", cat);

        List<Phone> phones = phoneRepository.findAll();
        model.addAttribute("aplication",phones);

        return "aplication";
    }
    @GetMapping(value = "/login")
    public String login(Model model){
        return "login";
    }






    @GetMapping(value = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model){
        //model.addAttribute("currentuser",getCurrentUser());
        return "profile";

    }

    @GetMapping(value = "/403")
    public String accessDeniedPage(Model model){


        return "403";
    }



    @PostMapping(value = "/addphone")
    public String addItemPage(@RequestParam(name = "name") String name,
                              @RequestParam(name = "marka")String marka,
                              @RequestParam(name = "photo") String photo,
                              @RequestParam(name = "price")int price,
                              @RequestParam(name = "description")String description,
                              @RequestParam(name = "country")String country,
                              @RequestParam(name ="category" ) Long categoryId){
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if(category!=null) {
            Phone phone = new Phone();
            phone.setName(name);
            phone.setMarka(marka);

            phone.setPhoto(photo);
            phone.setPrice(price);
            phone.setDescription(description);
            phone.setCountry(country);
            phone.setCategory(category);
            phoneRepository.save(phone);
        }
        return "redirect:/";
    }




    @GetMapping(value = "/details/{itemId}")
    public String itemDetails(
            @PathVariable(name = "itemId")Long id , Model model){
        Phone phone = phoneRepository.findById(id).orElse(null);
        model.addAttribute("home",phone);
        return "details";
    }


    @GetMapping(value = "/korzina/{tovId}")
    public String tovKorzina(
            @PathVariable(name = "tovId")Long id , Model model){
        Phone phone = phoneRepository.findById(id).orElse(null);
        model.addAttribute("tovar",phone);

        if(phone!= null){
            CorzItems corzItems = new CorzItems();
            corzItems.setName(phone.getName());
            corzItems.setPhoto(phone.getPhoto());
            corzItems.setPrice(phone.getPrice());
            corzItems.setDescription(phone.getDescription());

            corzItemsRepository.save(corzItems);
        }
        return "redirect:/shop";
    }
//    @GetMapping(value = "/register")
//    public String register(Model model){
//
//        return "register";
//    }
//    @PostMapping(value = "/register")
//    public String toRegister(@RequestParam(name = "re_email")String email,
//                             @RequestParam(name = "re_password")String password,
//                             @RequestParam(name = "gre_password")String rePassword){
//        if (password.equals(rePassword)){
//        User newUser = new User();
//        newUser.setFullName(email);
//        newUser.setPassword(password);
//        newUser.setEmail(email);
//        if (userRepository.createUser(newUser)!=null){
//            return "redirect:/register?success";
//        }
//        }
//        return "redirect:/register?error";
//
//    }





    @PostMapping(value = "/savephone")
    public String savePhone(@RequestParam(name = "id") Long id,
                            @RequestParam(name = "name") String name,
                            @RequestParam(name = "marka")String marka,
                            @RequestParam(name = "price")int price,
                            @RequestParam(name = "description")String description,
                            @RequestParam(name = "country")String country){

        Phone phone = phoneRepository.findById(id).orElse(null);
        if(phone!= null){
            phone.setName(name);
            phone.setMarka(marka);
            phone.setPrice(price);
            phone.setDescription(description);
            phone.setCountry(country);
            phoneRepository.save(phone);
            return "redirect:/addphone";
        }
        return "redirect:/";
    }

    @PostMapping(value = "/deletephone")
    public String deletePhone(@RequestParam(name = "id")Long id ){
        Phone phone = phoneRepository.findById(id).orElse(null);
        if(phone!=null){
            phoneRepository.delete(phone);
        }
        return "redirect:/";
    }
    @GetMapping(value = "/korzinao")
    public String CorItemPage(@RequestParam(name = "id") Long id,
                              @RequestParam(name = "price")int price,
                              @RequestParam(name = "name") String name,
                              @RequestParam(name = "photo") String photo,
                              @RequestParam(name = "description")String description){
        Phone phone = phoneRepository.findById(id).orElse(null);
        if(phone!=null) {
            CorzItems corzItems = new CorzItems();
            corzItems.setName(name);
            corzItems.setPhoto(photo);
            corzItems.setPrice(price);
            corzItems.setDescription(description);

            corzItemsRepository.save(corzItems);
        }
        return "redirect: /";
    }
    @PostMapping(value = "/deleteKorz")
    public String deleteKorz(@RequestParam(name = "id")Long id ){
        CorzItems corzItems = corzItemsRepository.findById(id).orElse(null);
        if(corzItems!=null){
            corzItemsRepository.delete(corzItems);
        }
        return "redirect:/korzina";
    }

//        private User getCurrentUser(){
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(!(authentication instanceof AnonymousAuthenticationToken)){
//            User currentUser =(User) authentication.getPrincipal();
//            return currentUser;
//        }
//     return null;
//        }
//    private User getUserData(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if(!(authentication instanceof AnonymousAuthenticationToken)){
//            org.springframework.security.core.userdetails.User secUser = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
//            User User = userService.getUserByEmail(secUser.getUsername());
//            return User;
//        }
//        return null;
//    }

}
