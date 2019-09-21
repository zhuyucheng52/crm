package com.echo.crm.controller;

import com.echo.crm.entry.Finance;
import com.echo.crm.service.FinanceService;
import com.echo.crm.utils.Page;
import com.echo.crm.utils.PageUtils;
import com.echo.crm.utils.ResultInfo;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
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
 * @create 2019-09-21 22:36
 */

@RestController
public class FinanceController implements BaseController<Finance> {
    @Autowired
    private FinanceService financeService;

    @Override
    @GetMapping("/finance/{id:\\d+}")
    public ResultInfo<Finance> findById(@PathVariable("id") Long id) {
        return ResultInfo.createResult(financeService.findById(id));
    }

    @Override
    @GetMapping("/finances")
    public ResultInfo<Page<Finance>> findByKeyword(@RequestParam(value = "page") Integer page,
                                                   @RequestParam(value = "limit") Integer limit,
                                                   @RequestParam(value = "q") String key) {
        PageList<Finance> finances = financeService.findByKeyword(key, PageUtils.createPageBounds(page, limit));
        return ResultInfo.createResult(PageUtils.createPage(finances));
    }

    @Override
    @PostMapping("/finance")
    public ResultInfo<Finance> add(@Valid @RequestBody Finance finance) {
        return ResultInfo.createResult(financeService.add(finance));
    }

    @Override
    @PutMapping("/finance")
    public ResultInfo<Finance> update(@RequestBody Finance finance) {
        return ResultInfo.createResult(financeService.update(finance));
    }

    @Override
    @DeleteMapping("/finance/{id:\\d+}")
    public ResultInfo<Finance> delete(@PathVariable("id") Long id) {
        return ResultInfo.createResult(financeService.delete(id));
    }
}
