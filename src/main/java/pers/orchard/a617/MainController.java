package pers.orchard.a617;

import com.alibaba.fastjson.JSON;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.orchard.a617.bean.Device;

import java.util.List;

@Controller
public class MainController {
    private final MainService service;

    @Autowired
    public MainController(MainService service) {
        this.service = service;
    }

    @Contract(pure = true)
    @RequestMapping("")
    @ResponseBody
    private @NotNull String info() {
        List<Device> devices = service.getAllDevice();
        String json = JSON.toJSONString(devices);
        ;
        return json;
    }
}
