/*
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright 2024. NHN Academy Corp. All rights reserved.
 * + * While every precaution has been taken in the preparation of this resource,  assumes no
 * + responsibility for errors or omissions, or for damages resulting from the use of the information
 * + contained herein
 * + No part of this resource may be reproduced, stored in a retrieval system, or transmitted, in any
 * + form or by any means, electronic, mechanical, photocopying, recording, or otherwise, without the
 * + prior written permission.
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */

package com.nhnacademy.customer.cart;

import com.nhnacademy.customer.exception.ProductAlreadyExistsException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart implements Serializable {

    private List<CartItem> cartItems;

    public Cart() {
        // TODO#2-1 장바구니 아이템을 담을 수 있는 cartItems를 초기화합니다.
        // 동기화 처리를 위해 Collections.synchronizedList를 이용합니다.
        this.cartItems = Collections.synchronizedList(new ArrayList<>());
    }

    public void tryAddItem(CartItem cartItem) throws ProductAlreadyExistsException {
        // TODO#2-2 장바구니에 아이템이 이미 존재한다면 ProductAlreadyExistsException 예외가 발생합니다.
        // 여기서 존재 여부는 productId를 기준으로 판단하는 것이 일반적입니다.
        for (CartItem item : cartItems) {
            if (item.getProductId() == cartItem.getProductId()) {
                throw new ProductAlreadyExistsException(cartItem.getProductId());
            }
        }

        // TODO#2-3 cartItem에 아이템을 추가하는 코드를 작성하세요.
        cartItems.add(cartItem);
    }

    public void clear(){
        // TODO#2-4 장바구니 cartItems를 초기화(비우기)합니다.
        cartItems.clear();
    }

    public List<CartItem> getCartItems() {
        // TODO#2-5 장바구니 아이템을 반환합니다.
        return cartItems;
    }
}
