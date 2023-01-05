package com.its.crawlingtest.config.auth.controller;

import com.its.crawlingtest.config.auth.dto.SessionUser;
import com.its.crawlingtest.config.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ResolvableType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final UserService userService;
    private final HttpSession httpSession;
//    private static final String authorizationRequestBaseUri = "oauth2/authorization";
//    Map<String, String> oauth2AuthenticationUrls = new HashMap<>();
//    private final ClientRegistrationRepository clientRegistrationRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", userService.findAllDesc());

        SessionUser user = (SessionUser)httpSession.getAttribute("user");

        if(user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "login";
    }
//    @GetMapping("/login")
//    public String getLoginPage(Model model) throws Exception {
//        Iterable<ClientRegistration> clientRegistrations = null;
//        ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository)
//                .as(Iterable.class);
//        if (type != ResolvableType.NONE &&
//                ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
//            clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
//        }
//        assert clientRegistrations != null;
//        clientRegistrations.forEach(registration ->
//                oauth2AuthenticationUrls.put(registration.getClientName(),
//                        authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
//        model.addAttribute("urls", oauth2AuthenticationUrls);
//
//        return "login";
//    }



//    @GetMapping("/login")
//    public String loginForm(){
//        return "login";
//    }
}
