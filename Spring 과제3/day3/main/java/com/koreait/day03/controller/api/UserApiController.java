package com.koreait.day03.controller.api;


import com.koreait.day03.ifs.CrudInterface;
import com.koreait.day03.model.network.Header;
import com.koreait.day03.model.network.request.UserApiRequest;
import com.koreait.day03.model.network.response.UserApiResponse;
import com.koreait.day03.service.UserApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {

    private final UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("")    // api/user (post)
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        System.out.println(request);
        return userApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<UserApiResponse> read(@PathVariable(name="id") Long id) {
        return userApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
        System.out.println(request);
        return userApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header<UserApiResponse> delete(@PathVariable(name="id") Long id) {
        return userApiLogicService.delete(id);
    }
}