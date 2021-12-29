package com.planet.dashboard.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface CrudController<T>{

    String read(Model model);

    String create(T src , BindingResult bindingResult , Model model);

    String update(T src);

    String delete(T src);
}
