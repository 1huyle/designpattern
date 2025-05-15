package nongsansach.controller;

import jakarta.validation.Valid;
import nongsansach.Entity.Builder.UserEntiryBuilder;
import nongsansach.Entity.Facade.Data.Update;
import nongsansach.Entity.Facade.UserFacade;
import nongsansach.Entity.UsersEntity;
import nongsansach.payload.request.UserRequest;
import nongsansach.payload.response.BaseResponse;
import nongsansach.service.Imp.RoleServiceImp;
import nongsansach.service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleServiceImp roleServiceImp;

    @GetMapping()
    public ResponseEntity<?> getProfileUser(Authentication authentication) {
        BaseResponse baseResponse = new BaseResponse();

        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('User') or hasAuthority('Admin')")
    @PostMapping("/updateB")
    public ResponseEntity<?> updateUserAccount(@RequestPart("data") UserRequest userRequest) {
        System.out.print(userRequest.toString());
        UsersEntity usersEntity = userServiceImp.find_user_email(userRequest.getEmail());

        UsersEntity userEntityBuilder = new UserEntiryBuilder().buildAddress(userRequest.getAddress()).buildPhone(userRequest.getPhone()).buildPassword(userRequest.getPassword()).buildFullName(userRequest.getName()).build();
        usersEntity.setFullname(userEntityBuilder.getFullname());
        usersEntity.setAddress(userEntityBuilder.getAddress());
        usersEntity.setPhone(userEntityBuilder.getPhone());
        usersEntity.setPassword(passwordEncoder.encode(usersEntity.getPassword()));
        userServiceImp.saveUserEntity(usersEntity);
        return new ResponseEntity<>("Cập nhật thành công", HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority('User') or hasAuthority('Admin')")
    @PostMapping("/updateF")
    public ResponseEntity<?> updateUser(@Valid UserRequest userRequest) {
        UserFacade userFacade = new UserFacade();
        Update update = new Update();
        update.setEmail(userRequest.getEmail());
        update.setUsername(userRequest.getName());
        update.setAddress(userRequest.getAddress());
        update.setPhone(userRequest.getPhone());
        update.setPassword(userRequest.getPassword());
        userFacade.UserFacadeActionUpdate(userServiceImp, passwordEncoder, update);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(userFacade);
        baseResponse.setMessage("Thành công");
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }
}
