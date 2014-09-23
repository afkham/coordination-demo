/*
 *  Copyright (c) 2005-2014, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */

import java.io.Serializable;

/**
 * TODO: class level comment
 */
public class TestRunnable implements Runnable, Serializable {
    private int i;

    public TestRunnable(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        for(long j = 0; j < 100000000L; j++){
            Math.log(j);
        }
        System.out.println("##### Execution " + i +" successful. Time:" + (System.currentTimeMillis() - start) + "ms");
    }
}
