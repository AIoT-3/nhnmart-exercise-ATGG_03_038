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

package com.nhnacademy.nhnmart.product.parser;

import com.nhnacademy.nhnmart.product.domain.Product;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/*
     TODO-NOTE#6-13 ProductParser 인터페이스입니다.
     - /src/main/resources/product_data.csv 파일을 파싱합니다.
*/
public interface ProductParser extends Closeable {
    // 파싱할 대상의 파일 이름 정의
    String PRODUCTS_DATA= "product_data.csv";

    // 핵심기능: "파싱해서 상품 리스트를 내놔라 (추상클래스)
    List<Product> parse();

    // 모든 구현체가 공통으로 쓸 "파일 불러오기" 로직을 여기에 미리 짜둔 것
    default InputStream getProductsStream(){
        return this.getClass()
                .getClassLoader()
                .getResourceAsStream(PRODUCTS_DATA);
    }
}
