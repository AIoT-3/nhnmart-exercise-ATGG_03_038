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

package com.nhnacademy.nhnmart.product.parser.impl;

import com.nhnacademy.nhnmart.product.domain.Product;
import com.nhnacademy.nhnmart.product.exception.CsvParsingException;
import com.nhnacademy.nhnmart.product.parser.ProductParser;
import com.nhnacademy.nhnmart.product.util.ProductIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class CsvProductParser implements ProductParser {

    // 제품의 기본 수량 = 100개
    private final int DEFAULT_QUANTITY=100;
    private InputStream inputStream;

    public CsvProductParser() {
        // TODO#6-2-1 기본 생성자 구현, getProductsStream()을 이용해서 inputStream을 초기화합니다.
        this.inputStream = getProductsStream();
    }

    public CsvProductParser(InputStream inputStream){
        // TODO#6-2-2 inputStream parameter로 전달됩니다. 초기화합니다.
        this.inputStream = inputStream;
    }

    @Override
    public List<Product> parse() {
        /* TODO#6-2-3 parse() 메서드를 구현하세요
            [CSV Parser]
            - https://github.com/nhnacademy-bootcamp/java-dev-settings/blob/main/docs/06.maven/02.Maven/06.pom.xml.adoc 참고합니다.
            - ProductParser interface의 getProductsStream()를 이용해서 구현합니다.
         */
        List<Product> products = new ArrayList<>();
        if (Objects.isNull(inputStream)) {
            return products;
        }

        // CSV 포맷 설정: 첫 번째 레코드를 헤더로 사용 (Header: Item, Maker, Specification, Unit, Price)
        try (InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim().parse(reader)) {

            for (CSVRecord record : parser) {
                // 각 레코드에서 데이터 추출
                // CSV 컬럼: Item, Maker, Specification, Unit, Price
                long id = ProductIdGenerator.getNewId(); // ID는 Generator를 통해 생성
                String item = record.get("Item");
                String maker = record.get("Maker");
                String specification = record.get("Specification");
                String unit = record.get("Unit");
                int price = Integer.parseInt(record.get("Price"));

                // Product 객체 생성 및 리스트 추가 (수량은 DEFAULT_QUANTITY)
                Product product = new Product(id, item, maker, specification, unit, price, DEFAULT_QUANTITY);
                products.add(product);
            }

        } catch (Exception e) {
            // 파싱 중 발생하는 예외를 사용자 정의 예외로 감싸서 던짐
            throw new CsvParsingException();
        }

        return products;
    }

    @Override
    public void close() throws IOException {
        // TODO#6-2-5 inputStream 객체가 존재하면 close() 메서드를 호출해서 자원을 해제합니다.
        if (Objects.nonNull(inputStream)) {
            inputStream.close();
        }
    }
}
