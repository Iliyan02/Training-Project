package example.dbinit;

import example.service.SingerService;
import example.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit implements CommandLineRunner {

    private final SingerService singerService;
    private final UserService userService;

    public DatabaseInit(SingerService singerService, UserService userService) {
        this.singerService = singerService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        singerService.seedSinger();
        userService.seedUsers();
    }
}
