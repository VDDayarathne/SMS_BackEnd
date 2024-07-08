    package com.sms.sms.controller;

    import com.sms.sms.dto.EventDTO;
    import com.sms.sms.dto.ReqRes;
    import com.sms.sms.dto.ResponseData;
    import com.sms.sms.entity.*;
    import com.sms.sms.repo.EquipmentRepository;
    import com.sms.sms.service.*;
    import jakarta.persistence.EntityNotFoundException;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.web.bind.annotation.*;

    import java.util.Collections;
    import java.util.List;
    import java.util.Optional;

    @RequestMapping
    @RestController
    @CrossOrigin(origins = "http://192.168.56.1:3000")
    public class UserManagementController {

        @Autowired
        private UsersManagementService usersManagementService;

        @Autowired
        private NotificationService notificationService;

        @Autowired
        private NewsService newsService;

        @Autowired
        private TournamentService tournamentService;

        @PostMapping("/auth/register")
        public ResponseEntity<ReqRes> regeister(@RequestBody ReqRes reg){
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
            String email = authentication.getName();
            ReqRes response = usersManagementService.getMyInfo(email);
            return  ResponseEntity.status(response.getStatusCode()).body(response);
        }

        @PostMapping("/adminuser/create-event")
        public ResponseEntity<ReqRes> createEvent(@RequestBody EventDTO eventDTO) {
            return ResponseEntity.ok(usersManagementService.createEvent(eventDTO));
        }

        @GetMapping("/adminuser/get-events")
        public ResponseEntity<ReqRes> getEvents() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            ReqRes response = usersManagementService.getEvents(email);
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }

        @GetMapping("/adminuser/get-event/{eventId}")
        public ResponseEntity<ReqRes> getEvent(@PathVariable Long eventId) {
            return ResponseEntity.ok(usersManagementService.getEvent(eventId));
        }

        @PutMapping("/adminuser/update-event/{eventId}")
        public ResponseEntity<ReqRes> updateEvent(@PathVariable Long eventId, @RequestBody EventDTO eventDTO) {
            return ResponseEntity.ok(usersManagementService.updateEvent(eventId, eventDTO));
        }

        @DeleteMapping("/adminuser/delete-event/{eventId}")
        public ResponseEntity<ReqRes> deleteEvent(@PathVariable Long eventId) {
            return ResponseEntity.ok(usersManagementService.deleteEvent(eventId));
        }







        @GetMapping("/adminuser/get-notifications")
        public List<Notification> getNotifications() {
            return notificationService.getAllNotifications();
        }

        @PostMapping("/admin/create-notifications")
        @PreAuthorize("hasAuthority('ADMIN')")
        public Notification createNotification(@RequestBody Notification notification) {
            return notificationService.createNotification(notification.getMessage(), false, notification.getTitle());
        }

        @DeleteMapping("/admin/delete-notifications/{id}")
        @PreAuthorize("hasAuthority('ADMIN')")
        public void deleteNotification(@PathVariable Long id) {
            notificationService.deleteNotification(id);
        }

        @GetMapping("/adminuser/latest-news")
        public ResponseEntity<ResponseData> getLatestNews() {
            List<News> latestNews = newsService.getLatestNews(10);
            ResponseData response = new ResponseData("Latest News", latestNews);
            return ResponseEntity.ok(response);
        }

        @PostMapping("/admin/create-news")
        public ResponseEntity<ResponseData> createNews(@RequestBody News news) {
            newsService.createNews(news);
            ResponseData response = new ResponseData("News created successfully", Collections.singletonList(news));
            return ResponseEntity.ok(response);
        }

        @DeleteMapping("/admin/delete-news/{id}")
        @PreAuthorize("hasAuthority('ADMIN')")
        public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
            newsService.deleteNews(id);
            return ResponseEntity.noContent().build();
        }


        @GetMapping("/adminuser/get-tournaments")
        public List<Tournament> getTournaments() {
            return tournamentService.getLatestTournaments();
        }

        @PostMapping("/admin/create-tournament")
        @PreAuthorize("hasAuthority('ADMIN')")
        public Tournament createTournament(@RequestBody Tournament tournament) {
            return tournamentService.createTournament(tournament);
        }

        @DeleteMapping("/admin/delete-tournament/{id}")
        @PreAuthorize("hasAuthority('ADMIN')")
        public ResponseEntity<Void> deleteTournament(@PathVariable Long id) {
            tournamentService.deleteTournament(id);
            return ResponseEntity.noContent().build();
        }



        @Autowired
        private InventoryService inventoryService;
        @Autowired
        private EquipmentRepository equipmentRepository;

        @GetMapping("/adminuser/inventory")
        public List<Inventory> getInventories() {
            return inventoryService.getAllInventories();
        }

        @GetMapping("/admin/inventory/{id}")
        public Inventory getInventoryById(@PathVariable Long id) {
            return inventoryService.getInventoryById(id);
        }

        @PostMapping("/admin/inventory")
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
            try {
                Inventory createdInventory = inventoryService.createInventory(inventory);
                return new ResponseEntity<>(createdInventory, HttpStatus.CREATED);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }


        @PutMapping("/admin/inventory/{id}")
        @PreAuthorize("hasRole('ADMIN')")
        public Inventory updateInventory(@PathVariable Long id, @RequestBody Inventory inventory) {
            return inventoryService.updateInventory(id, inventory);
        }

        @DeleteMapping("/admin/inventory/{id}")
        @PreAuthorize("hasRole('ADMIN')")
        public void deleteInventory(@PathVariable Long id) {
            inventoryService.deleteInventory(id);
        }

        @Autowired
        private SportScheduleService sportScheduleService;

        @GetMapping("/adminuser/sport-schedules")
        public List<SportSchedule> getSportSchedules() {
            return sportScheduleService.getAllSchedules();
        }

        @PostMapping("/admin/sport-schedules")
        @PreAuthorize("hasAuthority('ADMIN')")
        public SportSchedule createSportSchedule(@RequestBody SportSchedule schedule) {
            return sportScheduleService.createSchedule(schedule);
        }

        @PutMapping("/admin/sport-schedules/{id}")
        @PreAuthorize("hasAuthority('ADMIN')")
        public SportSchedule updateSportSchedule(@PathVariable Long id, @RequestBody SportSchedule schedule) {
            return sportScheduleService.updateSchedule(id, schedule);
        }

        @DeleteMapping("/admin/sport-schedules/{id}")
        @PreAuthorize("hasAuthority('ADMIN')")
        public void deleteSportSchedule(@PathVariable Long id) {
            sportScheduleService.deleteSchedule(id);
        }



        @Autowired
        private ItemIssuingRegisterService itemIssuingRegisterService;

        @PostMapping("/admin/create-item-issuing-register")
        @PreAuthorize("hasAuthority('ADMIN')")
        public ItemIssuingRegister createItemIssuingRegister(@RequestBody ItemIssuingRegister itemIssuingRegister) {
            return itemIssuingRegisterService.createItemIssuingRegister(itemIssuingRegister);
        }

        @GetMapping("/admin/get-all-item-issuing-registers")
        @PreAuthorize("hasAuthority('ADMIN')")
        public List<ItemIssuingRegister> getAllItemIssuingRegisters() {
            return itemIssuingRegisterService.getAllItemIssuingRegisters();
        }

        @GetMapping("/admin/get-item-issuing-register/{id}")
        @PreAuthorize("hasAuthority('ADMIN')")
        public ItemIssuingRegister getItemIssuingRegisterById(@PathVariable Integer id) {
            return itemIssuingRegisterService.getItemIssuingRegisterById(id);
        }

        @PutMapping("/admin/update-item-issuing-register/{id}")
        @PreAuthorize("hasAuthority('ADMIN')")
        public ItemIssuingRegister updateItemIssuingRegister(@PathVariable Integer id, @RequestBody ItemIssuingRegister itemIssuingRegister) {
            return itemIssuingRegisterService.updateItemIssuingRegister(id, itemIssuingRegister);
        }

        @DeleteMapping("/admin/delete-item-issuing-register/{id}")
        @PreAuthorize("hasAuthority('ADMIN')")
        public void deleteItemIssuingRegister(@PathVariable Integer id) {
            itemIssuingRegisterService.deleteItemIssuingRegister(id);
        }





    }