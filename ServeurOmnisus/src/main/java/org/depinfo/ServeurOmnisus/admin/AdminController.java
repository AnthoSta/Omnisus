package org.depinfo.ServeurOmnisus.admin;

import org.depinfo.ServeurOmnisus.grade.GradeService;
import org.depinfo.ServeurOmnisus.grade.dto.ScoreBoardResponse;
import org.depinfo.ServeurOmnisus.user.UserService;
import org.depinfo.ServeurOmnisus.user.dto.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private GradeService gradeService;


    @PostMapping("/api/admin/student")
    public @ResponseBody String batchSignup(@RequestBody SignupRequest[] students)
            throws UserService.UsernameTooShort, UserService.PasswordTooShort, UserService.UsernameAlreadyTaken {

        gradeService.deleteAll();

        userService.deleteAll();
        for (SignupRequest student : students) {
            userService.signup(student);
        }
        return "ok!";
    }

    @GetMapping("/dashboard")
    public @ResponseBody List<ScoreBoardResponse> listStudents( ) {
        return this.gradeService.allDetails();
    }
}
