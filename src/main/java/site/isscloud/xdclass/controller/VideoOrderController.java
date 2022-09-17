package site.isscloud.xdclass.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.isscloud.xdclass.model.entity.VideoOrder;
import site.isscloud.xdclass.model.request.VideoOrderRequest;
import site.isscloud.xdclass.service.VideoOrderService;
import site.isscloud.xdclass.utils.JsonData;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pri/order")
public class VideoOrderController {

    @Autowired
    private VideoOrderService videoOrderService;

    private UserController userController;

    @PostMapping("save")
    public JsonData saveOrder(@RequestBody VideoOrderRequest videoOrderRequest, HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("user_id");
        if(videoOrderRequest.getUserId() != null && videoOrderRequest.getUserId().intValue() != userId.intValue()) {
            return JsonData.buildError("您无权新增该用户的订单");
        }
        VideoOrder order = new VideoOrder();
        // 注意使用BeanUtil.copyProgerties方法方便将videoOrderRequest中的内容拷贝到实体对象order中，以便进行业务及数据库存储
        BeanUtils.copyProperties(videoOrderRequest, order);
        order.setUserId(userId);

        // mybatis将order存入数据库后，会自动将id反存储到order对象中，因此现在返回给前端的order是含有order.id的
        return videoOrderService.save(order) == 1 ? JsonData.buildSuccess(order):JsonData.buildError("新增订单失败");
    }

    // 获取当前用户的所有订单信息
    @GetMapping("list")
    public JsonData selectVideoOrdersByUserId(@PathVariable("user_id") Integer usreId, HttpServletRequest request) {
        return userController.selectVideoOrdersByUserId(usreId,request);
    }
}
