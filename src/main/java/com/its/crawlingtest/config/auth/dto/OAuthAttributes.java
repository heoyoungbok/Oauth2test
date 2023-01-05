package com.its.crawlingtest.config.auth.dto;


import com.its.crawlingtest.domain.user.Role;
import com.its.crawlingtest.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Getter
@Builder
@RequiredArgsConstructor
public class OAuthAttributes {

    private final Map<String, Object> attributes;

    private final String nameAttributeKey;
    private final String name;

    private final String nickname;
    private final String email;
    private final String picture;
//    private final String gender;
//    private final String age;

//    @Builder
//    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture){
//        this.attributes = attributes;
//        this.nameAttributeKey = nameAttributeKey;
//        this.name = name;
//        this.email = email;
//        this.picture = picture;
//    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }
        else {
            return ofGoogle("id", attributes);
        }
    }
    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }


    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");


        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .nickname((String) response.get("nickname"))
//                .gender((String) response.get("gender"))
//                .age((String) response.get("age"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity(){
        return User.builder()
                .name(name)
                .nickname(nickname)
                .email(email)
                .picture(picture)
//                .gender(gender)
//                .age(age)
                .role(Role.GUEST)
                .build();
    }
}
