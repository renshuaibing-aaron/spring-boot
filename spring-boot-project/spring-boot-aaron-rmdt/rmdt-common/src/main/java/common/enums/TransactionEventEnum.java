/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE filter distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this filter to You under the Apache License, Version 2.0
 * (the "License"); you may not use this filter except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author luohaipeng
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TransactionEventEnum {


    INSERT(1, "插入事务"),
    UPDATE(2, "更新事务");

    private Integer code;

    private String desc;


}