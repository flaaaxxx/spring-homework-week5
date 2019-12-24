package pl.flaaaxxx.springhomeworkweek5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import pl.flaaaxxx.springhomeworkweek5.model.stock.StockInfo;

@Controller
public class StockController {

    private StockInfo stockInfo;

    public StockController() {
        getStockInfo();
    }

    private StockInfo getStockInfo() {
        RestTemplate restTemplate = new RestTemplate();
        stockInfo = restTemplate.getForObject("https://financialmodelingprep.com//api/v3/majors-indexes", StockInfo.class);

        return stockInfo;
    }

    @GetMapping
    public String showIndex(Model model) {
        model.addAttribute("stockInfoList", stockInfo.getMajorIndexesList());
        return "stock";
    }

}
