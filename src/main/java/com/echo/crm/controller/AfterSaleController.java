package com.echo.crm.controller;

import com.echo.crm.entry.AfterSale;
import com.echo.crm.service.AfterSaleService;
import com.echo.crm.utils.Page;
import com.echo.crm.utils.PageUtils;
import com.echo.crm.utils.ResultInfo;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @author yucheng
 * @description
 * @create 2019-09-17 09:42
 */

@Slf4j
@RestController
public class AfterSaleController implements BaseController<AfterSale> {
    @Autowired
    private AfterSaleService aftersaleService;

    @Override
    @GetMapping("/aftersale/{id:\\d+}")
    public ResultInfo<AfterSale> findById(@PathVariable("id") Long id) {
        return ResultInfo.createResult(aftersaleService.findById(id));
    }

    @Override
    @GetMapping("/aftersales")
    public ResultInfo<Page<AfterSale>> findByKeyword(@RequestParam(value = "page") Integer page,
                                                     @RequestParam(value = "limit") Integer limit,
                                                     @RequestParam(value = "q", required = false) String key) {
        PageList<AfterSale> aftersales = aftersaleService.findByKeyword(key, PageUtils.createPageBounds(page, limit));
        return ResultInfo.createResult(PageUtils.createPage(aftersales));
    }

    @Override
    @PostMapping("/aftersale")
    public ResultInfo<AfterSale> add(@Valid @RequestBody AfterSale aftersale) {
        return ResultInfo.createResult(aftersaleService.add(aftersale));
    }

    @Override
    @PutMapping("/aftersale")
    public ResultInfo<AfterSale> update(@RequestBody AfterSale aftersale) {
        return ResultInfo.createResult(aftersaleService.update(aftersale));
    }

    @Override
    @DeleteMapping("/aftersale/{id:\\d+}")
    public ResultInfo<AfterSale> delete(@PathVariable("id") Long id) {
        return ResultInfo.createResult(aftersaleService.delete(id));
    }
}
