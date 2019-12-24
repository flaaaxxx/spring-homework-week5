package pl.flaaaxxx.springhomeworkweek5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.flaaaxxx.springhomeworkweek5.service.CurrencyService;

import java.math.BigDecimal;
import java.math.RoundingMode;


@Controller
public class CurrencyController {

    private int tries;
    private CurrencyService currencyService;

    public CurrencyController() {
    }

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
        tries = 0;
    }

    @GetMapping(path = {"/currency", "/currency/{status}"})
    public String showCurrency(Model model, @PathVariable(value = "status", required = false) String status) {
        model.addAttribute("symbol", currencyService.getDrawCode());
        model.addAttribute("tries", tries);

        if (status != null) {
            if (status.equals("equals"))
                model.addAttribute("status", "equals");
            if (status.equals("low"))
                model.addAttribute("status", "low");
            if (status.equals("high"))
                model.addAttribute("status", "high");
        }

        return "currency";
    }

    @GetMapping("/currency/check")
    public String tryCurrency(@RequestParam Double rates) {

        BigDecimal currencyRate = new BigDecimal(rates);
        currencyRate = currencyRate.setScale(2, RoundingMode.HALF_UP);

//        number of tries
        tries++;

        if (currencyRate.compareTo(currencyService.getCurrencyPLN()) == 0) {
            return "redirect:/currency/" + "equals";
        }
        if (currencyRate.compareTo(currencyService.getCurrencyPLN()) == 1) {
            return "redirect:/currency/" + "high";
        }
        if (currencyRate.compareTo(currencyService.getCurrencyPLN()) == -1) {
            return "redirect:/currency/" + "low";
        }
        return "redirect:/currency";
    }

    @GetMapping("/currency/losuj")
    public String get() {
        currencyService.draw();
        tries = 0;
        return "redirect:/currency";
    }
}
