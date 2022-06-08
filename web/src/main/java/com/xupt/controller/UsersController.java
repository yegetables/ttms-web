package com.xupt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.common.ApiController;
import com.xupt.common.R;
import com.xupt.pojo.Users;
import com.xupt.service.UsersService;
import com.xupt.utils.PHPass;
import com.xupt.utils.UsersUtils;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * (Users)表控制层
 *
 * @author ajian
 * @since 2022-06-04 19:40:45
 */
@Controller
@RequestMapping("/users")
@ResponseBody
public class UsersController extends ApiController {
  /** 服务对象 */
  @Resource private UsersService usersService;

  @Resource private PHPass phPass;

  /**
   * 分页查询所有数据
   *
   * @param usersAndPage(page) 分页对象
   * @param usersAndPage(users) 查询实体
   * @return 所有数据
   */
  @PostMapping
  public R selectAll(@RequestBody UsersAndPage<Users> usersAndPage) {
    Users users = usersAndPage.getUsers();
    Page<Users> page = usersAndPage.getPage();
    var pp = usersService.getBaseMapper().selectPage(page, new QueryWrapper<>(users));
    // 脱敏
    pp.getRecords().forEach(UsersUtils::removeSecret);
    return success(pp);
  }

  /**
   * 通过主键查询单条数据
   *
   * @param id 主键
   * @return 单条数据
   */
  @GetMapping("{id}")
  public R selectOne(@PathVariable Serializable id) {
    var u = this.usersService.getById(id);
    UsersUtils.removeSecret(u);
    return success(u);
  }

  /**
   * 新增数据
   *
   * @param users 实体对象
   * @return 新增结果
   */
  @PostMapping("/new")
  public R insert(@RequestBody Users users) {
    if (users != null && users.getPassword() != null) {
      users.setPassword(phPass.HashPassword(users.getPassword()));
    }
    boolean isSuccess = this.usersService.save(users);
    if (isSuccess) {
      UsersUtils.removeSecret(users);
      return success(users);
    }
    return failed("新增失败");
  }

  /**
   * 修改数据
   *
   * @param users 实体对象
   * @return 修改结果
   */
  @PutMapping
  public R update(@RequestBody Users users) {
    if (users != null && users.getPassword() != null) {
      users.setPassword(phPass.HashPassword(users.getPassword()));
    }
    boolean isSuccess = this.usersService.updateById(users);
    if (isSuccess) {
      users = this.usersService.getById(users.getUid());
      UsersUtils.removeSecret(users);
      return success(users);
    }
    return failed("修改失败");
  }

  /**
   * 删除数据
   *
   * @param idList 主键结合
   * @return 删除结果
   */
  @DeleteMapping
  public R delete(@RequestParam("idList") List<Long> idList) {
    boolean isSuccess = this.usersService.removeByIds(idList);
    if (isSuccess) {
      return success("删除成功");
    }
    return failed("删除失败");
  }
}

@Data
class UsersAndPage<T> {
  private Page<T> page;
  private Users users;
}
