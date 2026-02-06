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

package com.nhnacademy.customer.domain;

import com.nhnacademy.customer.exception.InsufficientFundsException;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class Customer {

    private final long id;
    private final String name;
    private int money;

    public Customer(long id, String name, int money) {
        // TODO#1-1 id < 1 또는 name이 null이거나 ""인 경우, 또는 money < 0이면 IllegalArgumentException이 발생합니다.
        if (id < 1 || name == null || name.trim().isEmpty() || money < 0) {
            throw new IllegalArgumentException("Invalid customer information provided.");
        }
        // TODO#1-2 id, name, money를 초기화합니다.
        this.id = id;
        this.name = name;
        this.money = money;
    }

    public long getId() {
        // TODO#1-3 메서드를 구현하세요. id를 반환합니다.
        return id;
    }

    public String getName() {
        // TODO#1-4 메서드를 구현하세요. name을 반환합니다.
        return name;
    }

    public int getMoney() {
        // TODO#1-5 메서드를 구현하세요. money를 반환합니다.
        return money;
    }

    public void pay(int amount) throws InsufficientFundsException {
        // TODO#1-6 amount(결제할 금액) < 0이면 IllegalArgumentException이 발생합니다.
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }

        // TODO#1-7 money(회원 보유 금액) < amount(결제할 금액)이면 InsufficientFundsException이 발생합니다.
        if (this.money < amount) {
            throw new InsufficientFundsException();
        }

        // TODO#1-8 메서드를 구현합니다. money에서 amount만큼 차감합니다.
        this.money -= amount;

        log.debug("customer: {}, pay : {}", this, amount);
    }

    @Override
    public String toString() {
        // TODO#1-9 id, name, money가 반환될 수 있도록 구현합니다.
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }

    // TODO#1-10 Customer 객체 비교를 위해 구현합니다. (비교 기준: id, name, money 일치)
    @Override
    public boolean equals(Object o) {
        // 비교대상이 나 자신이면 무조건 true
        if (this == o) return true;
        // 대상이 없거나 다른 클래스 종류라면 무조건 false
        if (o == null || getClass() != o.getClass()) return false;
        // (형변환) Object o를 Custom 타임으로 변환
        Customer customer = (Customer) o;
        // 데이터가 전부 같은지 확인
        return id == customer.id &&
                money == customer.money &&
                Objects.equals(name, customer.name);
    }
    /* 왜 만드는가? -> equals를 구현하지 않으면 데이터가 전부 같은 custom 객체 2개가 만들어져도 다른 사람으로 인식 */


    // TODO#1-11 (id, name, money) 기준으로 hashCode()를 구현합니다.
    @Override
    public int hashCode() {
        return Objects.hash(id, name, money);
    }
}