package com.nnngu.web;

import com.nnngu.dto.Exposer;
import com.nnngu.dto.SeckillExecution;
import com.nnngu.dto.SeckillResult;
import com.nnngu.entity.Seckill;
import com.nnngu.enums.SeckillStatEnum;
import com.nnngu.exception.RepeatKillException;
import com.nnngu.exception.SeckillCloseException;
import com.nnngu.exception.SeckillException;
import com.nnngu.service.interfaces.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by nnngu
 */
@Component
@RequestMapping("/seckill")//url:模块/资源/{}/细分
public class SeckillController {
    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        //list.jsp+mode=ModelAndView
        //获取列表页
        List<Seckill> list = seckillService.getSeckillList();
        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }

        Seckill seckill = seckillService.getById(seckillId);
        if (seckill == null) {
            return "forward:/seckill/list";
        }

        model.addAttribute("seckill", seckill);

        return "detail";
    }

    //ajax，json暴露秒杀接口的方法
    @RequestMapping(value = "/{seckillId}/exposer",
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            e.printStackTrace();
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }

        return result;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/execution",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @CookieValue(value = "userPhone", required = false) Long userPhone) {
        // 如果用户的手机号码为空的说明没有填写手机号码
        if (userPhone == null) {
            return new SeckillResult<SeckillExecution>(false, "未注册");
        }

        // 根据用户的手机号码,秒杀商品的id跟md5进行秒杀商品,没异常就是秒杀成功，如果有异常就是秒杀失败
        try {
            // 这里换成储存过程
            SeckillExecution execution = seckillService.executeSeckill(seckillId, userPhone, md5);
//            SeckillExecution execution = seckillService.executeSeckillProcedure(seckillId, userPhone, md5);
            return new SeckillResult<>(true, execution);
        } catch (RepeatKillException e1) {
            // 重复秒杀
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
            return new SeckillResult<>(true, execution);
        } catch (SeckillCloseException e2) {
            // 秒杀关闭
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.END);
            return new SeckillResult<>(true, execution);
        } catch (SeckillException e) {
            // 不能判断的异常
            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
            return new SeckillResult<>(true, execution);
        }

    }

    //获取系统时间
    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time() {
        Date now = new Date();
        return new SeckillResult<Long>(true, now.getTime());
    }
}
