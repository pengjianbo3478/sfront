package com.gl365.app.remote.sales;

import com.gl365.app.dto.command.FeedbackCommand;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ryan on 2017/6/17.
 */
@FeignClient(name="sales",url="${${env:}.url.sales:}")
public interface FeedbackService {

    @RequestMapping(value = "/api/feedback/addFeedback", method = RequestMethod.POST)
    void addFeedBack(@RequestBody FeedbackCommand command);
}
