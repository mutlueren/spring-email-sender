package rc.mail.feedback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rc.mail.feedback.model.Feedback;
import rc.mail.feedback.service.FeedBackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    FeedBackService feedBackService;

    @PostMapping
    public void sendFeedback(@RequestBody Feedback feedback, BindingResult bindingResult){
        feedBackService.sendFeedback(feedback, bindingResult);
    }
}
