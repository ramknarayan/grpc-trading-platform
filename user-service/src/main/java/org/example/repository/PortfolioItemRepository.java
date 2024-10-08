package org.example.user.tests.repository;

import org.example.user.tests.entitiy.PortfolioItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortfolioItemRepository extends CrudRepository<PortfolioItem,Integer> {

    List<PortfolioItem> findAllByUserId(Integer userid);
    Optional<PortfolioItem> findByUserIdAndTicker(Integer userid, org.example.common.Ticker ticker);
}
