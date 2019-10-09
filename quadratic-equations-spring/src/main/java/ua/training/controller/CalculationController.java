package ua.training.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.training.entities.Calculation;

import ua.training.exceptions.CalculationException;
import ua.training.services.CalculationService;

@Controller
public class CalculationController {

    private final Logger logger = LoggerFactory.getLogger(CalculationController.class);

    @Autowired
    private CalculationService calculationService;

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }

    @PostMapping(value = "/calculate")
    public String calculate(@ModelAttribute("calculation") Calculation calculation, Model model) throws CalculationException {

        BigDecimal paramA = calculation.getParamA();
        BigDecimal paramB = calculation.getParamB();
        BigDecimal paramC = calculation.getParamC();

        BigDecimal discriminant = calculationService.calcDiscriminant(paramB, paramA, paramC);
        calculation.setDiscriminant(discriminant);
        calculation.setX1(calculationService.calcX1(paramB, paramA, discriminant));
        calculation.setX2(calculationService.calcX2(paramB, paramA, discriminant));

        model.addAttribute("calculation", calculation);
        return "welcome";
    }

    @ExceptionHandler(CalculationException.class)
    public String handleEmployeeNotFoundException(Model model, Exception exception) {
        logger.error("negative discriminant: " + exception.getMessage());
        model.addAttribute("discriminantIsNegative", true);
        return "welcome";
    }

}







//        model.addAttribute("x1", x1);
//        model.addAttribute("x2", x2);
//        model.addAttribute("discriminant", discriminant);

//
//    @RequestParam(required = false) BigDecimal x1,
//    @RequestParam(required = false) BigDecimal x2,
//    @RequestParam(required = false) BigDecimal discriminant)
