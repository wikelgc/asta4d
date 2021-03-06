/*
 * Copyright 2012 astamuse company,Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.astamuse.asta4d.util;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * we use this Exception to combine multiple exceptions to a single one
 * 
 * @author e-ryu
 * 
 */
public class GroupedException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private List<Exception> exceptionList = null;

    public void setExceptionList(List<Exception> exceptionList) {
        this.exceptionList = exceptionList;
    }

    @Override
    public String getMessage() {
        StringBuffer sb = new StringBuffer("There are multiple exceptions ocurred. Please check them one by one.\n");
        for (Exception ex : exceptionList) {
            sb.append(ex.getClass().getName()).append(":").append(ex.getMessage()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String getLocalizedMessage() {
        StringBuffer sb = new StringBuffer("There are multiple exceptions ocurred. Please check them one by one.\n");
        for (Exception ex : exceptionList) {
            sb.append(ex.getClass().getName()).append(":").append(ex.getLocalizedMessage()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public void printStackTrace() {
        for (Exception ex : exceptionList) {
            System.err.println(ex.getClass().getName() + ":");
            ex.printStackTrace();
        }
        System.err.println();
    }

    @Override
    public void printStackTrace(PrintStream s) {
        for (Exception ex : exceptionList) {
            s.println(ex.getClass().getName() + ":");
            ex.printStackTrace(s);
        }
        s.println();
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        for (Exception ex : exceptionList) {
            s.println(ex.getClass().getName() + ":");
            ex.printStackTrace(s);
        }
        s.println();
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        List<StackTraceElement> list = new LinkedList<>();
        for (Exception ex : exceptionList) {
            list.addAll(Arrays.asList(ex.getStackTrace()));
        }
        return list.toArray(new StackTraceElement[list.size()]);
    }

}
