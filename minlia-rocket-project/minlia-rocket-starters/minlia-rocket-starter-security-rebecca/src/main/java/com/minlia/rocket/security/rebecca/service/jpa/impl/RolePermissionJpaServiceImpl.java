package com.minlia.rocket.security.rebecca.service.jpa.impl;

import com.minlia.rocket.data.jpa.abstraction.AbstractRepository;
import com.minlia.rocket.security.rebecca.body.RolePermissionQueryRequestBody;
import com.minlia.rocket.security.rebecca.dao.PermissionDao;
import com.minlia.rocket.security.rebecca.entity.RolePermission;
import com.minlia.rocket.security.rebecca.repository.RolePermissionRepository;
import com.minlia.rocket.security.rebecca.service.jpa.RolePermissionJpaService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户接口实现
 *
 * @author rdteam
 */
@Slf4j
@Transactional
public class RolePermissionJpaServiceImpl implements RolePermissionJpaService {

  @Autowired
  private RolePermissionRepository rolePermissionRepository;


  @Override
  public AbstractRepository<RolePermission, Long> getJpaRepository() {
    return this.rolePermissionRepository;
  }

  @Override
  public List<RolePermission> findByPermissionId(Long permissionId) {

    return rolePermissionRepository.findByPermissionId(permissionId);
  }

  @Override
  public void deleteByRoleId(Long roleId) {

    rolePermissionRepository.deleteByRoleId(roleId);
  }



















  @Override
  public Specification<RolePermission> getFindAllSpecification(
      RolePermissionQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }

  @Override
  public Specification<RolePermission> getCountSpecification(
      RolePermissionQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }

  @Override
  public Specification<RolePermission> getExistsSpecification(
      RolePermissionQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }

  @Override
  public Specification<RolePermission> getDeleteByConditionSpecification(
      RolePermissionQueryRequestBody queryRequestBody) {
    return this.getConditions(queryRequestBody);
  }


  public Specification<RolePermission> getConditions(
      RolePermissionQueryRequestBody queryRequestBody) {
    return new Specification<RolePermission>() {
      @Nullable
      @Override

      public Predicate toPredicate(Root<RolePermission> root, CriteriaQuery<?> cq,
          CriteriaBuilder cb) {

//        Path<String> usernameField = root.get("username");
//        Path<String> mobileField = root.get("mobile");
//        Path<String> emailField = root.get("email");
//        Path<Gender> genderField = root.get("gender");
//        Path<Integer> typeField = root.get("type");
//        Path<Status> statusField = root.get("status");
//        Path<Date> createdDateField = root.get("createdDate");

        List<Predicate> list = new ArrayList<Predicate>();

        //模糊搜素
//        if (StringUtils.isNotBlank(queryRequestBody.getUsername())) {
//          list.add(cb.like(usernameField, '%' + queryRequestBody.getUsername() + '%'));
//        }
//        if (StringUtils.isNotBlank(queryRequestBody.getMobile())) {
//          list.add(cb.like(mobileField, '%' + queryRequestBody.getMobile() + '%'));
//        }
//        if (StringUtils.isNotBlank(queryRequestBody.getEmail())) {
//          list.add(cb.like(emailField, '%' + queryRequestBody.getEmail() + '%'));
//        }
//
//        //性别
//        if (queryRequestBody.getGender() != null) {
//          list.add(cb.equal(genderField, queryRequestBody.getGender()));
//        }
//        //类型
//        if (queryRequestBody.getType() != null) {
//          list.add(cb.equal(typeField, queryRequestBody.getType()));
//        }
//        //状态
//        if (queryRequestBody.getStatus() != null) {
//          list.add(cb.equal(statusField, queryRequestBody.getStatus()));
//        }
//        //创建时间
//        if (StringUtils.isNotBlank(queryRequestBody.getStartDate()) && StringUtils
//            .isNotBlank(queryRequestBody.getEndDate())) {
//          Date start = DateUtil.parse(queryRequestBody.getStartDate());
//          Date end = DateUtil.parse(queryRequestBody.getEndDate());
//          list.add(cb.between(createdDateField, start, DateUtil.endOfDay(end)));
//        }

        Predicate[] arr = new Predicate[list.size()];
        cq.where(list.toArray(arr));
        return null;
      }
    };
  }

}
