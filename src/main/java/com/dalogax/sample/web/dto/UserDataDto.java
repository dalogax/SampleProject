package com.dalogax.sample.web.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDataDto
{
    @Getter
    @Setter(AccessLevel.PROTECTED) 
    private String name;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    private String email;
}