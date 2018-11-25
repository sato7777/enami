package com.example.demo.controller;

import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Calc;
import com.example.demo.service.CalcService;

@Controller
@RequestMapping("/calcs")
public class CalcController {
	@Autowired
	private CalcService calcService;

	@GetMapping
	public String index(Model model) {
		List<Calc> calclist = calcService.findAll();
		calclist.forEach(s -> {
			s.setCalctargetname(calcService.convertCodeToName(s.getCalctarget()));
		});
		model.addAttribute("calclist", calclist);
		return "calcs/index";
	}

	@PostMapping("search")
	public String searchCalcP(Model model, @ModelAttribute("basedate") String inputBasedate) {
		List<Calc> calclist = calcService.findAll();
		calclist.forEach(s -> {
			s.setCalctargetname(calcService.convertCodeToName(s.getCalctarget()));
			s.setBasedate(inputBasedate);
			s.setCalcdate(calcService.calcDate(inputBasedate, s.getCalctarget(), s.getCalcvalue()));
		});

		model.addAttribute("calclist", calclist);
		model.addAttribute("inputBasedate", inputBasedate);
		return "calcs/index";
	}

	@GetMapping("new")
	public String newCalc(Model model) {
		return "calcs/new";
	}

	@GetMapping("{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("calc", calcService.findOne(id).get());
		return "calcs/edit";
	}

	@PostMapping
	public String create(@ModelAttribute Calc calc) throws ParseException {
		calcService.save(calc);
		return "redirect:/calcs";
	}

	@PutMapping("{id}")
	public String update(@PathVariable Long id, @ModelAttribute Calc calc) throws ParseException {
		calc.setId(id);
		calcService.save(calc);
		return "redirect:/calcs";
	}

	@DeleteMapping("{id}")
	public String destroy(@PathVariable Calc id) {
		calcService.delete(id);
		return "redirect:/calcs";
	}

}
