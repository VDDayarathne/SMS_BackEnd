    package com.sms.sms.controller;

    import com.sms.sms.dto.ReqRes;
    import com.sms.sms.entity.OurUsers;
    import com.sms.sms.service.UsersManagementService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.web.bind.annotation.*;

    @RequestMapping
    @RestController
    @CrossOrigin(origins = "http://192.168.56.1:3000")
    public class UserManagementController {

        @Autowired
        private UsersManagementService usersManagementService;

        @PostMapping("/auth/register")
        public ResponseEntity<ReqRes> register(@RequestBody ReqRes reg){
            return ResponseEntity.ok(usersManagementService.register(reg));
        }

        @PostMapping("/auth/login")
        public ResponseEntity<ReqRes> login(@RequestBody ReqRes req){
            return ResponseEntity.ok(usersManagementService.login(req));
        }

        @PostMapping("/auth/refresh")
        public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req){
            return ResponseEntity.ok(usersManagementService.refreshToken(req));
        }

        @GetMapping("/admin/get-all-users")
        public ResponseEntity<ReqRes> getAllUsers(){
            return ResponseEntity.ok(usersManagementService.getAllUsers());

        }

        @GetMapping("/admin/get-users/{userId}")
        public ResponseEntity<ReqRes> getUserByID(@PathVariable Integer userId){
            return ResponseEntity.ok(usersManagementService.getUsersById(userId));

        }

        @DeleteMapping("/admin/delete/{userId}")
        public ResponseEntity<ReqRes> deleteUSer(@PathVariable Integer userId){
            return ResponseEntity.ok(usersManagementService.deleteUser(userId));
        }

        @PutMapping("/adminuser/update/{userId}")
        public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody OurUsers reqres){
            return ResponseEntity.ok(usersManagementService.updateUser(userId, reqres));
        }

        @GetMapping("/adminuser/get-profile")
        public ResponseEntity<ReqRes> getMyProfile(){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getPrincipal().toString();
            ReqRes response = usersManagementService.getMyInfo(email);
            return  ResponseEntity.status(response.getStatusCode()).body(response);
        }


    }