package com.springcourse.dto;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestSavedto {

    @NotBlank(message = "Subject required")
    private String subject;

    private String description;

    @NotNull(message = "Owner required")
    private User owner;

    private List<RequestStage> stages = new ArrayList<RequestStage>();

    public Request transformeToRequest(){
        Request request = new Request(null, this.subject, this.description, null, null, this.owner, this.stages);

        return request;
    }

}
