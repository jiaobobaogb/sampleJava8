package com.britesnow.j8.DevTestBao;

/**
 * Created by Administrator on 2014/9/23.
 */
public class ValueAbsentException extends Throwable{

        public ValueAbsentException() {
            super();
        }

        public ValueAbsentException(String msg) {
            super(msg);
        }

        @Override
        public String getMessage() {
            return "No value present in the Optional instance";
        }
}
