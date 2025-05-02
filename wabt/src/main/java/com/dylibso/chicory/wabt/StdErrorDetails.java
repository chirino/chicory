package com.dylibso.chicory.wabt;

import com.dylibso.chicory.wasm.ChicoryException;

public class StdErrorDetails extends ChicoryException {

    public StdErrorDetails(String errorOutput) {
        super("\n" + errorOutput);
    }

    // no need to capture the Stack Trace
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
