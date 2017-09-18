package io.github.adamelliotfields.controller;

import io.github.adamelliotfields.dto.UserDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Log
@Controller
public class LoginController {
  @RequestMapping(path = {"/login", "/login/"}, method = RequestMethod.GET)
  public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView modelAndView = new ModelAndView();
    Object flash = request.getSession().getAttribute("flash");
    UserDto userDto = new UserDto();

    modelAndView.setViewName("login");
    modelAndView.addObject("userDto", userDto);
    modelAndView.addObject("flash", flash);

    request.getSession().removeAttribute("flash");

    return modelAndView;
  }
}
