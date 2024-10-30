package com.order.rush_order.order.repository.wishlist;


import com.order.rush_order.member.entity.Users;
import com.order.rush_order.order.dto.response.WishlistItemDto;
import com.order.rush_order.order.entity.Wishlist;
import com.order.rush_order.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist,Long> {

    boolean existsByUserAndProduct(Users user, Product product);



    Optional<Wishlist> findByUserIdAndProductId(long userId, long productId);


    void deleteAllByUser(Users user);


    // User의 Wishlist와 Product 정보를 JOIN FETCH로 한 번에 가져오기
    @Query("""
    SELECT new com.order.rush_order.order.dto.response.WishlistItemDto(
        w.id, p.id, p.name, p.price
    )
    FROM Wishlist w
    JOIN w.product p
    WHERE w.user = :user
""")
    List<WishlistItemDto> findWishlistItemsByUser(@Param("user")Users user);
}
